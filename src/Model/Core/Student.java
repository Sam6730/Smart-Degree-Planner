package Model.Core;

import java.util.List;

public class Student {
    private String name;
    private String username;
    private int yearOfStudy;
    private boolean decidedMajor;
    private Major major;
    private List<Course> coursesTaken;
    private List<Course> coursesCompleted;
    private List<Course> suggestedCourses;
    private List<String> interests;

    public Student(String name, String username, int yearOfStudy) {
        this.name = name;
        this.username = username;
        this.yearOfStudy = yearOfStudy;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public void addInterest(String interest) {
        interests.add(interest);
    }

    public List<Course> suggestCourses() {
        // Suggest courses logic
        return suggestedCourses;
    }

    public List<String> suggestMajors() {
        // Suggest majors logic
        return null;
    }

    // Getters and Setters
}
