package Model.Core;

import java.util.List;

public class Course {
    private String courseCode;
    private String name;
    private String description;
    private int credits;
    private List<Course> prerequisites;

    public boolean meetsPrerequisites(Student student) {
        // Check if student meets the prerequisites
        return true;
    }

    public int getCredits() {
        return credits;
    }

    // Getters and Setters
}
