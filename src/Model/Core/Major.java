package Model.Core;

import java.util.List;

public class Major {
    private String name;
    private List<Course> requiredCourses;
    private List<Course> electives;

    public Major(String name) {
        this.name = name;
    }

    public List<Course> getRequiredCourses() {
        return requiredCourses;
    }

    public List<Course> getElectiveSuggestions(List<String> interests) {
        // Return electives based on interests
        return electives;
    }

    // Getters and Setters

}
