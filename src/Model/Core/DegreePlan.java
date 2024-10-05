package Model.Core;

import java.util.List;

public class DegreePlan {
    private Student student;
    private List<Course> suggestedCourses;
    private List<Course> mandatoryCourses;

    public DegreePlan(Student student) {
        this.student = student;
    }

    public void generatePlan(Student student) {
        // Logic to generate plan
    }

    public void addElectivesBasedOnInterest(Student student) {
        // Logic to add electives based on interests
    }

    public void updatePlanBasedOnCoursesTaken(Student student) {
        // Logic to update plan
    }

    // Getters and Setters
}
