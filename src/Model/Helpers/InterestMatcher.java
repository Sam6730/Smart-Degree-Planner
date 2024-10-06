package Model.Helpers;

import Model.Core.Course;
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

        // Initialize the courses for each major
        List<Course> csRequiredCourses = new ArrayList<>();
        csRequiredCourses.add(new Course("CPSC110", new ArrayList<>()));
        csRequiredCourses.add(new Course("CPSC121", new ArrayList<>()));
        csRequiredCourses.add(new Course("CPSC210", new ArrayList<>()));
        csRequiredCourses.add(new Course("CPSC213", new ArrayList<>()));
        csRequiredCourses.add(new Course("CPSC221", new ArrayList<>()));
        csRequiredCourses.add(new Course("CPSC310", new ArrayList<>()));
        csRequiredCourses.add(new Course("CPSC313", new ArrayList<>()));
        csRequiredCourses.add(new Course("CPSC320", new ArrayList<>()));

        List<Course> sauderRequiredCourses = new ArrayList<>();
        sauderRequiredCourses.add(new Course("COMM101", new ArrayList<>()));
        sauderRequiredCourses.add(new Course("COMM105", new ArrayList<>()));
        sauderRequiredCourses.add(new Course("ECON101", new ArrayList<>()));
        sauderRequiredCourses.add(new Course("ECON102", new ArrayList<>()));

        List<Course> bucsRequiredCourses = new ArrayList<>();  // BUCS shares with CS
        bucsRequiredCourses.add(new Course("COMM105", new ArrayList<>()));
        bucsRequiredCourses.add(new Course("CPSC110", new ArrayList<>()));
        bucsRequiredCourses.add(new Course("COMM196", new ArrayList<>()));
        bucsRequiredCourses.add(new Course("MATH100", new ArrayList<>()));
        bucsRequiredCourses.add(new Course("ECON101", new ArrayList<>()));

        List<Course> psychologyRequiredCourses = new ArrayList<>();
        psychologyRequiredCourses.add(new Course("PSYC101", new ArrayList<>()));
        psychologyRequiredCourses.add(new Course("PSYC102", new ArrayList<>()));

        List<Course> meRequiredCourses = new ArrayList<>();
        meRequiredCourses.add(new Course("MECH101", new ArrayList<>()));
        meRequiredCourses.add(new Course("MECH102", new ArrayList<>()));

        // Initialize the majors with the required courses
        Major psychology = new Major("Psychology", psychologyRequiredCourses, new ArrayList<>());
        Major sauder = new Major("Sauder", sauderRequiredCourses, new ArrayList<>());
        Major computerScience = new Major("Computer Science", csRequiredCourses, new ArrayList<>());
        Major bucs = new Major("BUCS", bucsRequiredCourses, new ArrayList<>());
        Major mechanicalEngineering = new Major("Mechanical Engineering", meRequiredCourses, new ArrayList<>());

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