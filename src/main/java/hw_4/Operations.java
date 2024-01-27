package hw_4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class Operations {
    public static void createDatabase(Connection connection) throws SQLException {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS SchoolDB;";
        PreparedStatement preparedStatement = connection.prepareStatement(createDatabaseSQL);
        preparedStatement.execute();
        System.out.println("Database SchoolDB created");
    }

    public static void useSchoolDB(Connection connection) throws SQLException {
        String useDatabaseSQL = "USE SchoolDB;";
        PreparedStatement preparedStatement = connection.prepareStatement(useDatabaseSQL);
        preparedStatement.execute();
        System.out.println("Database SchoolDB used");
    }

    public static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), duration VARCHAR(255));";
        PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL);
        preparedStatement.execute();
        System.out.println("Table Courses created");
    }

    public static void fillDatabase(Connection connection) throws SQLException {
        Course course = Course.createRandomCourse();
        String insertSQL = "INSERT INTO Courses (title, duration) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getDuration());
            preparedStatement.execute();
        }
    }

    public static Collection<Course> readData(Connection connection) throws SQLException {
        ArrayList<Course> coursesList = new ArrayList<>();
        String selectSQL = "SELECT * FROM Courses;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String duration = resultSet.getString("duration");
                System.out.println("ID: " + id + ", Title: " + title + ", Duration: " + duration);
                coursesList.add(new Course((long) id, title, duration));
            }
        }
        return coursesList;
    }

    public static void updateCourses(Connection connection, Course course) throws SQLException {
        String updateSQL = "UPDATE Courses SET title = ?, duration = ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getDuration());
            preparedStatement.setInt(3, Math.toIntExact(course.getId()));
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteById(Connection connection, int id) throws SQLException {
        String deleteSQL = "DELETE FROM Courses WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

}
