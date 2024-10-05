package Model.Core;

import java.util.List;

public class DegreePlan {
    private Student student;
    private List<Course> suggestedCourses;
    private List<Course> mandatoryCourses;

    public DegreePlan(Student student) {
        this.student = student;
    }

    public void generatePlan() {
        // Add required courses from the student's major
        Major major = student.getMajor();
        if (major != null) {
            mandatoryCourses = major.getRequiredCourses();
        }

        // Suggest electives based on interests
        addElectivesBasedOnInterest();
    }

    public void addElectivesBasedOnInterest() {
        suggestedCourses = student.suggestCourses();
    }

    public void updatePlanBasedOnCoursesTaken() {
        for (Course course : student.getCoursesTaken()) {
            mandatoryCourses.remove(course);
            suggestedCourses.remove(course);
        }
    }

    // Getters and Setters
}