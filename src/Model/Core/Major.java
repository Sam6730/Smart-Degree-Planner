package Model.Core;

import java.util.List;

public class Major {
    private String name;
    private List<Course> requiredCourses;
    private List<Course> electives;

    public Major(String name, List<Course> requiredCourses, List<Course> electives) {
        this.name = name;
        this.requiredCourses = requiredCourses;
        this.electives = electives;
    }

    public List<Course> getRequiredCourses() {
        return requiredCourses;
    }

    public List<Course> getElectiveSuggestions(List<String> interests) {
        // Suggest electives based on interests
        return electives;
    }

    public String getName() {
        return name;
    }

    public List<Course> getElectives() {
        return electives;
    }
}