package View;

import Model.Core.Course;
import Model.Core.Major;
import Model.Core.Student;
import persistence.JsonReader;
import Model.Helpers.InterestMatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DegreePlannerApp {
    private static final String JSON_STORE = "./data/computerScience.json";
    private JsonReader jsonReader;
    private Scanner input;
    private Major major;
    private Student student;
    private InterestMatcher interestMatcher;

    // EFFECTS: runs the degree planner application
    public DegreePlannerApp() {
        runPlanner();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPlanner() {
        setup();
        boolean majorDecided = askIfMajorDecided();

        if (majorDecided) {
            assignComputerScienceMajor();
        } else {
            askForInterestsAndSuggest();
        }

        createStudent();

        // If major was decided, display required courses
        if (majorDecided) {
            displayRequiredCourses();
        } else {
            suggestElectiveCourses();
        }

        System.out.println("\nThank you for using the Degree Planner App!");
    }

    // MODIFIES: this
    // EFFECTS: initializes the input, json reader and interest matcher
    private void setup() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonReader = new JsonReader(JSON_STORE);
        interestMatcher = new InterestMatcher(); // Initialize InterestMatcher
    }

    // EFFECTS: Asks if the user has decided on a major, returns true if decided, false otherwise
    private boolean askIfMajorDecided() {
        System.out.println("Have you decided your major? (yes/no): ");
        String command = input.nextLine().toLowerCase();
        return command.equals("yes");
    }

    // MODIFIES: this
    // EFFECTS: loads Computer Science major from the JSON file and assigns it to the student
    private void assignComputerScienceMajor() {
        System.out.println("You have selected Computer Science as your major.");
        try {
            major = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Error loading major data.");
        }
    }

    // EFFECTS: Asks user for interests if the major is undecided and suggests courses/majors
    private void askForInterestsAndSuggest() {
        System.out.println("You haven't selected a major yet. Let's gather your interests.");
        System.out.println("Please select from the following interests (separated by commas):");
        System.out.println("1. Artificial Intelligence (AI)");
        System.out.println("2. Software Development");
        System.out.println("3. Cybersecurity");
        System.out.println("4. Data Science");
        System.out.println("5. Human-Computer Interaction (HCI)");

        String selectedInterests = input.nextLine();
        List<String> interests = parseInterests(selectedInterests);
        suggestMajorsAndCourses(interests);
    }

    // EFFECTS: Parses the user input for interests and returns a list of interests
    private List<String> parseInterests(String input) {
        String[] options = input.split(",");
        List<String> interests = new ArrayList<>();

        for (String option : options) {
            switch (option.trim()) {
                case "1":
                    interests.add("Artificial Intelligence");
                    break;
                case "2":
                    interests.add("Software Development");
                    break;
                case "3":
                    interests.add("Cybersecurity");
                    break;
                case "4":
                    interests.add("Data Science");
                    break;
                case "5":
                    interests.add("Human-Computer Interaction");
                    break;
                default:
                    System.out.println("Invalid option: " + option);
            }
        }

        return interests;
    }

    // EFFECTS: Suggests majors and courses using InterestMatcher based on the student's interests
    private void suggestMajorsAndCourses(List<String> interests) {
        System.out.println("\nBased on your interests, we recommend the following majors:");

        // Use the InterestMatcher to suggest relevant majors
        List<Major> suggestedMajors = new ArrayList<>();
        for (String interest : interests) {
            suggestedMajors.addAll(interestMatcher.getSuggestedMajors(interest));
        }

        // Display the suggested majors
        for (Major suggestedMajor : suggestedMajors) {
            System.out.println(suggestedMajor.getName());
        }

        // For now, default to Computer Science and load its courses
        assignComputerScienceMajor();
    }

    // MODIFIES: this
    // EFFECTS: asks for student's details and creates a Student object
    private void createStudent() {
        System.out.println("Enter your name: ");
        String name = input.nextLine();

        System.out.println("Enter your username: ");
        String username = input.nextLine();

        System.out.println("Enter your year of study (1, 2, 3, 4): ");
        int yearOfStudy = Integer.parseInt(input.nextLine());

        student = new Student(name, username, yearOfStudy);

        if (major != null) {
            student.setMajor(major);
        }
    }

    // EFFECTS: Displays the required courses for the Computer Science major
    private void displayRequiredCourses() {
        System.out.println("\nAs a Computer Science major, here are your required courses:");
        for (Course course : major.getRequiredCourses()) {
            System.out.println(course.getCourseCode());
        }
    }

    // EFFECTS: Suggests elective courses based on the student's interests
    private void suggestElectiveCourses() {
        System.out.println("\nBased on your interests, here are some elective courses you may like:");
        for (Course course : major.getElectives()) {
            System.out.println(course.getCourseCode());
        }
    }
}