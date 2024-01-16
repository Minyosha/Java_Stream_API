package hw_1.market;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public void cartBalancing() {
        boolean proteins = foodstuffs.stream().anyMatch(Food::getProteins);
        boolean fats = foodstuffs.stream().anyMatch(Food::getFats);
        boolean carbohydrates = foodstuffs.stream().anyMatch(Food::getCarbohydrates);

        if (proteins && fats && carbohydrates) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        if (!proteins) {
            Optional<T> proteinFood = market.getThings(clazz).stream()
                    .filter(Food::getProteins)
                    .findFirst();
            proteinFood.ifPresent(foodstuffs::add);
        }

        if (!fats) {
            Optional<T> fatFood = market.getThings(clazz).stream()
                    .filter(Food::getFats)
                    .findFirst();
            fatFood.ifPresent(foodstuffs::add);
        }

        if (!carbohydrates) {
            Optional<T> carbFood = market.getThings(clazz).stream()
                    .filter(Food::getCarbohydrates)
                    .findFirst();
            carbFood.ifPresent(foodstuffs::add);
        }

        // Повторная проверка после возможного добавления элементов
        proteins = proteins || foodstuffs.stream().anyMatch(Food::getProteins);
        fats = fats || foodstuffs.stream().anyMatch(Food::getFats);
        carbohydrates = carbohydrates || foodstuffs.stream().anyMatch(Food::getCarbohydrates);

        if (proteins && fats && carbohydrates)
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
    }

    //endregion

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }



    public void printFoodstuffs()
    {
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }
}
