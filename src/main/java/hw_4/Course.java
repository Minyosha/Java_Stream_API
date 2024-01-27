package hw_4;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private String duration;
    private static final String[] listOfCourses = new String[]{"Java", "C++", "C#", "Python", "JavaScript", "PHP", "Ruby", "Swift", "Kotlin", "Rust"};
    private static final String[] listOfDurations = new String[]{"1 month", "3 months", "6 months", "12 months"};
    static final Random random = new Random();

    public Course() {
    }


    public static Course createRandomCourse(Long id) {
        String title = listOfCourses[random.nextInt(listOfCourses.length)];
        String duration = listOfDurations[random.nextInt(listOfDurations.length)];
        return new Course(id, title, duration);
    }

    public static Course createRandomCourse() {
        String title = listOfCourses[random.nextInt(listOfCourses.length)];
        String duration = listOfDurations[random.nextInt(listOfDurations.length)];
        return new Course(title, duration);
    }

    public Course(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    public Course(Long id, String title, String duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Duration: " + duration;
    }

    public void updateTitle() {
        this.title = listOfCourses[random.nextInt(listOfCourses.length)];
    }

    public void updateDuration() {
        this.duration = listOfDurations[random.nextInt(listOfDurations.length)];
    }
}
