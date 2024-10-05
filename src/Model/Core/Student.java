package Model.Core;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String username;
    private int yearOfStudy;
    private boolean decidedMajor;
    private Major major;
    private List<Course> coursesTaken = new ArrayList<>();
    private List<Course> coursesCompleted = new ArrayList<>();
    private List<Course> suggestedCourses = new ArrayList<>();
    private List<String> interests = new ArrayList<>();

    public Student(String name, String username, int yearOfStudy) {
        this.name = name;
        this.username = username;
        this.yearOfStudy = yearOfStudy;
    }

    public void setMajor(Major major) {
        this.major = major;
        this.decidedMajor = true;
    }

    public void addInterest(String interest) {
        if (!interests.contains(interest)) {
            interests.add(interest);
        }
    }

    public List<Course> suggestCourses() {
        ElectiveRecommender recommender = new ElectiveRecommender();
        return recommender.getElectivesBasedOnInterest(this);
    }

    public List<Major> suggestMajors() {
        InterestMatcher matcher = new InterestMatcher();
        List<Major> suggestedMajors = new ArrayList<>();
        for (String interest : interests) {
            suggestedMajors.addAll(matcher.getSuggestedMajors(interest));
        }
        return suggestedMajors;
    }

    public void addCourseTaken(Course course) {
        coursesTaken.add(course);
    }

    public void addCourseCompleted(Course course) {
        coursesCompleted.add(course);
    }

    // Getters and Setters for fields
}