package com.example.coursemanagementsystem.databaseControllers;

import com.example.coursemanagementsystem.ds.Person;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PersonGsonSerializer implements JsonSerializer<Person> {


    @Override
    public JsonElement serialize(Person person, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject personJson = new JsonObject();
        personJson.addProperty("id", person.getId());
        personJson.addProperty("login", person.getLogin());
        personJson.addProperty("password", person.getPassword());
        personJson.addProperty("name", person.getName());
        personJson.addProperty("surname", person.getSurname());
        personJson.addProperty("dateCreated", person.getDateCreated().toString());

        return personJson;
    }
}
