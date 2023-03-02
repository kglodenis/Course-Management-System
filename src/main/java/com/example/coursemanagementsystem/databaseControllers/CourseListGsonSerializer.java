package com.example.coursemanagementsystem.databaseControllers;

import com.example.coursemanagementsystem.ds.Course;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class CourseListGsonSerializer implements JsonSerializer<List<Course>> {
    @Override
    public JsonElement serialize(List<Course> courses, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Course.class, new CourseGsonSerializer());
        Gson parser = gsonBuilder.create();

        for(Course c: courses)
        {
            jsonArray.add(parser.toJson(c));
        }

        return jsonArray;
    }
}
