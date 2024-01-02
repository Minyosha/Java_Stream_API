package hw_1.average;

import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Stream.generate(() -> new Random().nextInt(100))
                .limit(10)
                .collect(Collectors.toList());

        System.out.println("Сгенерирован список: " + list);

        OptionalDouble result = list.stream()
                .filter(i -> i % 2 == 0)
                .mapToDouble(Integer::doubleValue)
                .average();

        System.out.println("Для списка " + list + " среднее значение всех четных чисел равно " + result.getAsDouble());
    }


}
