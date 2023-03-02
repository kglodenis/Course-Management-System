module com.example.coursemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;
    requires spring.context;
    requires spring.web;
    requires com.google.gson;
    requires spring.core;


    opens com.example.coursemanagementsystem to javafx.fxml;
    exports com.example.coursemanagementsystem;
    exports com.example.coursemanagementsystem.fxControllers;
    opens com.example.coursemanagementsystem.fxControllers to javafx.fxml;
    exports com.example.coursemanagementsystem.databaseControllers;
    opens com.example.coursemanagementsystem.databaseControllers to javafx.fxml;
    opens com.example.coursemanagementsystem.ds to javafx.fxml, org.hibernate.orm.core, java.persistence;
    exports com.example.coursemanagementsystem.ds;
}