package Model.Helpers;

import Model.Core.Major;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterestMatcher {
    private Map<String, List<Major>> interestToMajors;

    // Constructor to initialize interestToMajors
    public InterestMatcher() {
        interestToMajors = new HashMap<>();

        // Initialize the majors
        Major psychology = new Major("Psychology", new ArrayList<>(), new ArrayList<>());
        Major sauder = new Major("Sauder", new ArrayList<>(), new ArrayList<>());
        Major computerScience = new Major("Computer Science", new ArrayList<>(), new ArrayList<>());
        Major bucs = new Major("BUCS", new ArrayList<>(), new ArrayList<>());
        Major mechanicalEngineering = new Major("Mechanical Engineering", new ArrayList<>(), new ArrayList<>());

        // Map interests to corresponding majors
        interestToMajors.put("Meditation", List.of(psychology));
        interestToMajors.put("Golfing", List.of(sauder));
        interestToMajors.put("Not taking showers", List.of(computerScience));
        interestToMajors.put("Tech startups", List.of(bucs));
        interestToMajors.put("Building F1 cars", List.of(mechanicalEngineering));
    }

    // Method to get suggested majors based on the interest
    public List<Major> getSuggestedMajors(String interest) {
        if (interestToMajors.containsKey(interest)) {
            return interestToMajors.get(interest);
        }
        return new ArrayList<>();
    }
}