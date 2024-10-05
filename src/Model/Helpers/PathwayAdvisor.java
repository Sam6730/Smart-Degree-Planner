package Model.Helpers;

import Model.Core.Course;
import Model.Core.Major;
import Model.Core.Student;

import java.util.List;
import java.util.Map;

public class PathwayAdvisor {
    private Map<Major, List<Course>> majorToPathways;

    public List<Course> getPathwayForMajor(Major major) {
        return majorToPathways.get(major);
    }

    public List<Course> suggestPathway(Student student) {
        Major major = student.getMajor();
        if (major != null) {
            return getPathwayForMajor(major);
        }
        return null;
    }

    // Getters and Setters
}