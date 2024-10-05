package Helpers;

import Core.Major;

import java.util.List;
import java.util.Map;

public class InterestMatcher {
    private Map<String, List<Major>> interestToMajors;

    public List<Major> getSuggestedMajors(String interest) {
        return interestToMajors.get(interest);
    }

    // Getters and Setters
}
