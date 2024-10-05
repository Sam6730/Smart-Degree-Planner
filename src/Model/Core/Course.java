package Model.Core;

import java.util.List;

public class Course {
    private String courseCode;
    private String name;
    private String description;
    private int credits;
    private List<Course> prerequisites;

    public Course(String courseCode, String name, int credits, List<Course> prerequisites) {
        this.courseCode = courseCode;
        this.name = name;
        this.credits = credits;
        this.prerequisites = prerequisites;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getName() {
        return name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    // Getters and Setters
}