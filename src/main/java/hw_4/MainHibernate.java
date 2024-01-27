package hw_4;

import static hw_4.Course.random;

// Предполагается, что база данных и таблица Courses уже созданы при помощи класса Main
public class MainHibernate {
    public static void main(String[] args) {
        int count = random.nextInt(5, 10);
        for (int i = 0; i < count; i++) {
            HibernateUtil.fillDatabase();
        }
        System.out.println("Table filled");

        // Печать всех курсов
        HibernateUtil.printAllCourses();
        System.out.println("Table printed");

        // Обновление курсов
        HibernateUtil.updateAllCourses();
        System.out.println("Table updated");

        // Печать обновленных курсов
        HibernateUtil.printAllCourses();

        // Удаление всех курсов
        HibernateUtil.deleteAllCourses();
        System.out.println("Table deleted");

    }
}
