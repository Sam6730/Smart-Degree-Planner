package View;

import Model.Core.Course;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public void displaySuggestedCourses(List<Course> courses) {
        System.out.println("Suggested Courses:");
        for (Course course : courses) {
            System.out.println(course.getName() + " (" + course.getCourseCode() + ")");
        }
    }
}
