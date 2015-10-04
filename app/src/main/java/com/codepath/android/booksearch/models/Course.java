package com.codepath.android.booksearch.models;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private String openLibraryId;
    private String author;


    private String academicLevel;
    private String capacity;
    private String comments;
    private String courseNumber;
    private String credit;
    private String description;
    private String lineNumber;
    private String location;
    private String prefix;
    private String prerequisites;
    private String seatsAvailable;
    private String section;
    private String status;
    private String term;
    private String title;


    public String getOpenLibraryId() {
        return openLibraryId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // Get medium sized book cover from covers API
    public String getCoverUrl() {
        return "http://covers.openlibrary.org/b/olid/" + openLibraryId + "-M.jpg?default=false";
    }

    // Get large sized book cover from covers API
    public String getLargeCoverUrl() {
        return "http://covers.openlibrary.org/b/olid/" + openLibraryId + "-L.jpg?default=false";
    }

    // Returns a Book given the expected JSON
    public static Course fromJson(JSONObject jsonObject) {
        Course course = new Course();
        try {
            // Deserialize json into object fields
            // Check if a cover edition is available
            if(jsonObject.has("prefix")){
                course.prefix = jsonObject.getString("prefix");
            }
            if(jsonObject.has("courseNumber")){
                course.courseNumber = jsonObject.getString("courseNumber");
            }
            if(jsonObject.has("academicLevel")){
                course.academicLevel = jsonObject.getString("academicLevel");
            }
            if(jsonObject.has("capacity")){
                course.capacity = jsonObject.getString("capacity");
            }
            if(jsonObject.has("credit")){
                course.credit = jsonObject.getString("credit");
            }
            if(jsonObject.has("description")){
                course.description = jsonObject.getString("description");
            }
            if(jsonObject.has("prefix")){
                course.prefix = jsonObject.getString("prefix");
            }
            if(jsonObject.has("location")){
                course.location = jsonObject.getString("location");
            }
            if(jsonObject.has("status")){
                course.status = jsonObject.getString("status");
            }

            /*
            if (jsonObject.has("cover_edition_key")) {
                course.openLibraryId = jsonObject.getString("cover_edition_key");
            } else if(jsonObject.has("edition_key")) {
                final JSONArray ids = jsonObject.getJSONArray("edition_key");
                course.openLibraryId = ids.getString(0);
            }*/

            /*
            course.title = jsonObject.has("title_suggest") ? jsonObject.getString("title_suggest") : "";
            course.author = getAuthor(jsonObject);*/
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return course;
    }

    // Return comma separated author list when there is more than one author
    private static String getAuthor(final JSONObject jsonObject) {
        /*try {
            final JSONArray authors = jsonObject.getJSONArray("author_name");
            int numAuthors = authors.length();
            final String[] authorStrings = new String[numAuthors];
            for (int i = 0; i < numAuthors; ++i) {
                authorStrings[i] = authors.getString(i);
            }
            return TextUtils.join(", ", authorStrings);
        } catch (JSONException e) {
            return "";
        }*/
        return null;
    }

    // Decodes array of book json results into business model objects
    public static ArrayList<Course> fromJson(JSONArray jsonArray) {
        ArrayList<Course> courses = new ArrayList<Course>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject courseJson = null;
            try {
                courseJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Course course = Course.fromJson(courseJson);
            if (course != null) {
                courses.add(course);
            }
        }
        return courses;
    }
}
