package hw_3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainJSON {
    public static void main(String[] args) {
        // Создание объекта Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Student student = new Student("Stanislav", 20, 4.0);
        Student student1 = new Student("Sergey", 21, 5.0);
        Student student2 = new Student("Igor", 22, 3.0);

        List<Student> students = new ArrayList<Student>();
        List<Student> openedFromFile = null;

        students.add(student);
        students.add(student1);
        students.add(student2);

        // Сериализация списка студентов в JSON и запись в файл
        try (FileWriter fileWriter = new FileWriter("students.json")) {
            gson.toJson(students, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализация JSON из файла в список студентов
        try (FileReader fileReader = new FileReader("students.json")) {
            openedFromFile = gson.fromJson(fileReader, new TypeToken<List<Student>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Вывод информации о студентах из списка
        if (openedFromFile != null) {
            for (Student openedStudent : openedFromFile) {
                System.out.println(openedStudent.getName() + " " + openedStudent.getAge() + " " + openedStudent.getGPA());
            }
        }
    }
}
