package com.example.coursemanagementsystem.databaseControllers;

import com.example.coursemanagementsystem.ds.Course;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CourseGsonSerializer implements JsonSerializer<Course> {
    @Override
    public JsonElement serialize(Course course, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject courseJson = new JsonObject();
        courseJson.addProperty("id", course.getId());
        courseJson.addProperty("title", course.getTitle());
        courseJson.addProperty("description", course.getDescription());
        courseJson.addProperty("start Date", course.getStartDate().toString());
        courseJson.addProperty("end Date", course.getEndDate().toString());
        return courseJson;
    }
}
