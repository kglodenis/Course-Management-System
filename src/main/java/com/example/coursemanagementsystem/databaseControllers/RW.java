package com.example.coursemanagementsystem.databaseControllers;

import com.example.coursemanagementsystem.ds.CourseManagementSystem;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import java.io.*;

public class RW {

    public static CourseManagementSystem loadFromFile(String fileName) {
        CourseManagementSystem courseManagementSystem = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            courseManagementSystem = (CourseManagementSystem) objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (Exception io) {
            io.printStackTrace();
        }
        return courseManagementSystem;
    }

    //Does not return anything just writes to file
    public static void writeToFile(String fileName, CourseManagementSystem courseManagementSystem) {
        //Write as object to given file
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(courseManagementSystem);
            outputStream.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
   /* public static String sysInJson(CourseManagementSystem courseManagementSystem) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(courseManagementSystem, new FileWriter("Json.txt"));
        return gson.toJson(courseManagementSystem);
    }*/
}
