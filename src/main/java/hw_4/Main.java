package hw_4;


import java.sql.*;
import java.util.Collection;

import static hw_4.Course.random;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/SchoolDB";
        String user = "root";
        String password = "password";

        // Подключение к базе данных и создание таблицы
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Operations.createDatabase(connection);
            Operations.useSchoolDB(connection);
            Operations.createTable(connection);

            // Заполнение таблицы данными
            int count = random.nextInt(5, 10);
            for (int i = 0; i < count; i++) {
                Operations.fillDatabase(connection);
            }
            System.out.println("Table filled");


            // Создание коллекции и помещение туда прочитанных данных
            Collection<Course> coursesList = Operations.readData(connection);

            // Обновление данных
            for (var course : coursesList) {
                System.out.println("Updating course: " + course);
                course.updateTitle();
                course.updateDuration();
                Operations.updateCourses(connection, course);
            }

            // Печать обновленных данных
            System.out.println("Обновленные данные:");
            Operations.readData(connection);

            // Удаление записей
            for (var course : coursesList) {
                System.out.println("Deleting course: " + course);
                Operations.deleteById(connection, Math.toIntExact(course.getId()));
            }

            Operations.readData(connection);


            closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
