package persistence;

import Model.Core.Major;
import Model.Core.Course;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;

// Represents a reader that reads major and course data from JSON stored in a file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the Computer Science major from the JSON file and returns it
    // throws IOException if an error occurs reading data from file
    public Major read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMajor(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the major from JSON object and returns it
    private Major parseMajor(JSONObject jsonObject) {
        JSONObject majorJson = jsonObject.getJSONObject("major");
        String majorName = majorJson.getString("name");
        List<Course> requiredCourses = parseCourses(majorJson.getJSONArray("required_courses"));
        List<Course> electives = parseCourses(majorJson.getJSONArray("electives"));

        Map<String, List<String>> courseRelationships = parseCourseRelationships(jsonObject.getJSONObject("course_relationships"));

        Major major = new Major(majorName, requiredCourses, electives);
        return major;
    }

    // EFFECTS: parses courses from JSON array and returns a list of courses
    private List<Course> parseCourses(JSONArray jsonArray) {
        List<Course> courses = new ArrayList<>();
        for (Object json : jsonArray) {
            List<Course> prereq = new ArrayList<>();
            String courseCode = (String) json;
            Course course = new Course(courseCode, prereq); // Create course object with empty details
            courses.add(course);
        }
        return courses;
    }

    // EFFECTS: parses course relationships (pre-requisites) from JSON object
    private Map<String, List<String>> parseCourseRelationships(JSONObject jsonObject) {
        Map<String, List<String>> courseRelationships = new HashMap<>();

        for (String courseCode : jsonObject.keySet()) {
            JSONArray prerequisitesJson = jsonObject.getJSONObject(courseCode).getJSONArray("pre-requisites");
            List<String> prerequisites = new ArrayList<>();
            for (Object prerequisite : prerequisitesJson) {
                prerequisites.add((String) prerequisite);
            }
            courseRelationships.put(courseCode, prerequisites);
        }
        return courseRelationships;
    }
}