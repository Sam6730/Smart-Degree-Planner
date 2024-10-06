package Control;

import Model.Core.Course;
import Model.Core.DegreePlan;
import Model.Core.Major;
import Model.Core.Student;
import Model.Helpers.ElectiveRecommender;
import Model.Helpers.InterestMatcher;
import Model.Helpers.PathwayAdvisor;

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

    public void updateMajor(Major major) {
        student.setMajor(major);
        degreePlan.generatePlan();
    }

    public List<Course> getSuggestedCourses() {
        return degreePlan.getStudent().suggestCourses();
    }

    public void addInterest(String interest) {
        student.addInterest(interest);
        degreePlan.addElectivesBasedOnInterest();
    }
}
