package Model.Core;

public class Course {
    private String courseCode;
    private String name;
    private String description;
    private int credits;
    private String[] prerequisites;
    private String category; // core or elective

    public boolean isElective() {
        return "elective".equalsIgnoreCase(category);
    }

    public boolean meetsPrerequisites(Student student) {
        // Check if student meets the prerequisites
        return true;
    }

    public int getCredits() {
        return credits;
    }

    // Getters and Setters
}
