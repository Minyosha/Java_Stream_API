package hw_2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Dog("Biter", 1));
        animals.add(new Dog("Runner", 3));
        animals.add(new Cat("Kitty", 2));
        animals.add(new Cat("Sleeper", 4));

        for (Animal animal : animals) {
            try {
                info(animal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static <T> void info(T obj) throws IllegalAccessException, NoSuchMethodException {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        Field[] superFields = objClass.getSuperclass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.printf("%s: %s\n", field.getName(), field.get(obj));
        }
        for (Field field : superFields) {
            field.setAccessible(true);
            System.out.printf("%s: %s\n", field.getName(), field.get(obj));
        }
        Animal animal = (Animal) obj;
        System.out.print("Make sound: ");
        animal.makeSound();
        System.out.println("******");

    }
}
