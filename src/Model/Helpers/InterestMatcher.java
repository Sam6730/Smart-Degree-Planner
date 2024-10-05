package Model.Helpers;

import Model.Core.Major;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InterestMatcher {
    private Map<String, List<Major>> interestToMajors;

    public List<Major> getSuggestedMajors(String interest) {
        if (interestToMajors.containsKey(interest)) {
            return interestToMajors.get(interest);
        }
        return new ArrayList<>();
    }

    // Getters and Setters
}