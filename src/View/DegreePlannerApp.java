package View;

import Model.Core.Course;
import Model.Core.Major;
import Model.Core.Student;
import Model.Helpers.InterestMatcher;
import persistence.JsonReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DegreePlannerApp {
    private static final String JSON_STORE = "./data/cpsc_course_data_with_major.json";
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
            askForMajorSelection();
        } else {
            askForInterestsAndSuggest();
        }

        createStudent();

        // If major was decided, display required courses
        displayRequiredCourses();

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
    // EFFECTS: prompts the user to select a major from the given list
    private void askForMajorSelection() {
        System.out.println("Please select your major from the following options:");
        System.out.println("1. Psychology");
        System.out.println("2. Sauder");
        System.out.println("3. Computer Science");
        System.out.println("4. BUCS");
        System.out.println("5. Mechanical Engineering");

        String selection = input.nextLine();
        switch (selection) {
            case "1":
                major = interestMatcher.getSuggestedMajors("Meditation").get(0);
                break;
            case "2":
                major = interestMatcher.getSuggestedMajors("Golfing").get(0);
                break;
            case "3":
                major = interestMatcher.getSuggestedMajors("Not taking showers").get(0);
                break;
            case "4":
                major = interestMatcher.getSuggestedMajors("Tech startups").get(0);
                break;
            case "5":
                major = interestMatcher.getSuggestedMajors("Building F1 cars").get(0);
                break;
            default:
                System.out.println("Invalid selection, defaulting to Computer Science.");
                major = interestMatcher.getSuggestedMajors("Not taking showers").get(0);
        }
    }

    // EFFECTS: Asks user for interests if the major is undecided and suggests courses/majors
    private void askForInterestsAndSuggest() {
        System.out.println("You haven't selected a major yet. Let's gather your interests.");
        System.out.println("Please select from the following interests (separated by commas):");
        System.out.println("1. Meditation");
        System.out.println("2. Golfing");
        System.out.println("3. Not taking showers");
        System.out.println("4. Tech startups");
        System.out.println("5. Building F1 cars");

        String selectedInterests = input.nextLine();
        List<String> interests = parseInterests(selectedInterests);
        suggestMajorsAndPromptSelection(interests);
    }

    // EFFECTS: Parses the user input for interests and returns a list of interests
    private List<String> parseInterests(String input) {
        String[] options = input.split(",");
        List<String> interests = new ArrayList<>();

        for (String option : options) {
            switch (option.trim()) {
                case "1":
                    interests.add("Meditation");
                    break;
                case "2":
                    interests.add("Golfing");
                    break;
                case "3":
                    interests.add("Not taking showers");
                    break;
                case "4":
                    interests.add("Tech startups");
                    break;
                case "5":
                    interests.add("Building F1 cars");
                    break;
                default:
                    System.out.println("Invalid option: " + option);
            }
        }

        return interests;
    }

    // EFFECTS: Suggests majors based on the student's interests and prompts the user to select one
    private void suggestMajorsAndPromptSelection(List<String> interests) {
        System.out.println("\nBased on your interests, we recommend the following majors:");

        // Use the InterestMatcher to suggest relevant majors
        List<Major> suggestedMajors = new ArrayList<>();
        for (String interest : interests) {
            suggestedMajors.addAll(interestMatcher.getSuggestedMajors(interest));
        }

        // Display the suggested majors
        if (suggestedMajors.isEmpty()) {
            System.out.println("No major found based on your interests.");
            return;
        } else {
            for (int i = 0; i < suggestedMajors.size(); i++) {
                System.out.println((i + 1) + ". " + suggestedMajors.get(i).getName());
            }
        }

        // Prompt the user to select a major from the suggestions
        System.out.println("\nPlease select a major from the list above (enter the number):");
        int selection = Integer.parseInt(input.nextLine());
        if (selection > 0 && selection <= suggestedMajors.size()) {
            major = suggestedMajors.get(selection - 1);
        } else {
            System.out.println("Invalid selection, defaulting to Computer Science.");
            major = new Major("Computer Science", new ArrayList<>(), new ArrayList<>());
        }
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

    // EFFECTS: Displays the required courses for the selected major
    private void displayRequiredCourses() {
        System.out.println("\nAs a " + major.getName() + " major, here are your required courses:");
        for (Course course : major.getRequiredCourses()) {
            System.out.println(course.getCourseCode());
        }
    }
}