package Model.Helpers;

import Model.Core.Course;
import Model.Core.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElectiveRecommender {
    private Map<String, List<Course>> interestToElectives;

    public List<Course> getElectivesBasedOnInterest(Student student) {
        List<Course> electives = new ArrayList<>();
        for (String interest : student.getInterests()) {
            if (interestToElectives.containsKey(interest)) {
                electives.addAll(interestToElectives.get(interest));
            }
        }
        return electives;
    }

    // Getters and Setters
}