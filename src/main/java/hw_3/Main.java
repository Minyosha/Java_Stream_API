package hw_3;

import hw_2.Animal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Student student = new Student("Stanislav", 20, 4.0);
        Student student1 = new Student("Sergey", 21, 5.0);
        Student student2 = new Student("Igor", 22, 3.0);

        List<Student> students = new ArrayList<Student>();
        List<Student> openedFromFile = null;

        students.add(student);
        students.add(student1);
        students.add(student2);

        // Сериализация списка студентов в бинарный файл
        try (ObjectOutputStream saveStudents = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            saveStudents.writeObject(students);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Десериализация бинарного файла в список студентов
        try (ObjectInputStream loadStudents = new ObjectInputStream(new FileInputStream("students.dat"))) {
            openedFromFile = (List<Student>) loadStudents.readObject();
        } catch (IOException | ClassNotFoundException e) {
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
