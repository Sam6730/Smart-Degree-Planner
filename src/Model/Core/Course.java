package Model.Core;

import java.util.List;

public class Course {
    private String courseCode;
    private String name;
    private String description;
    private int credits;
    private List<Course> prerequisites;

    public Course(String courseCode, String name, String description, int credits, List<Course> prerequisites) {
        this.courseCode = courseCode;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.prerequisites = prerequisites;
    }

    public boolean meetsPrerequisites(Student student) {
        for (Course prerequisite : prerequisites) {
            if (!student.getCoursesTaken().contains(prerequisite)) {
                return false;
            }
        }
        return true;
    }

    public int getCredits() {
        return credits;
    }

    // Getters and Setters
}