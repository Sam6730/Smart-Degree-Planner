package Utility;

import Core.Course;
import Core.DegreePlan;
import Core.Student;
import Helpers.ElectiveRecommender;
import Helpers.InterestMatcher;
import Helpers.PathwayAdvisor;

import java.util.List;

public class PlannerController {
    private Student student;
    private DegreePlan degreePlan;
    private InterestMatcher interestMatcher;
    private PathwayAdvisor pathwayAdvisor;
    private ElectiveRecommender electiveRecommender;

    public PlannerController(Student student) {
        this.student = student;
        this.degreePlan = new DegreePlan(student);
        this.interestMatcher = new InterestMatcher();
        this.pathwayAdvisor = new PathwayAdvisor();
        this.electiveRecommender = new ElectiveRecommender();
    }

    public void updateMajor(String major) {
        student.setMajor(major);
        degreePlan.generatePlan(student);
    }

    public List<Course> getSuggestedCourses() {
        return degreePlan.getStudent().suggestCourses();
    }

    public void addInterest(String interest) {
        student.addInterest(interest);
        degreePlan.addElectivesBasedOnInterest(student);
    }

    // Additional methods for controlling the degree planning logic
}
