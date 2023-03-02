package com.example.coursemanagementsystem.databaseControllers;

import com.example.coursemanagementsystem.ds.Company;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CompanyGsonSerializer implements JsonSerializer<Company> {
    @Override
    public JsonElement serialize(Company company, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject companyJson = new JsonObject();
        companyJson.addProperty("id", company.getId());
        companyJson.addProperty("login", company.getLogin());
        companyJson.addProperty("password", company.getPassword());
        companyJson.addProperty("name", company.getName());
        companyJson.addProperty("representative", company.getRepresentative());
        return companyJson;
    }
}
