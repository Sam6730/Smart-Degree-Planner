package Helpers;

import Core.Course;
import Core.Major;
import Core.Student;

import java.util.List;
import java.util.Map;

public class PathwayAdvisor {
    private Map<Major, List<Course>> majorToPathways;

    public List<Course> getPathwayForMajor(Major major) {
        return majorToPathways.get(major);
    }

    public List<Course> suggestPathway(Student student) {
        // Logic to suggest a pathway for a student
        return null;
    }

    // Getters and Setters
}
