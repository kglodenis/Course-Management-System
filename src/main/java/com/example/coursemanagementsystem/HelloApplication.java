package com.example.coursemanagementsystem;

import com.example.coursemanagementsystem.ds.Course;
import com.example.coursemanagementsystem.ds.CourseManagementSystem;
import com.example.coursemanagementsystem.ds.Person;
import com.example.coursemanagementsystem.ds.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Course Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}