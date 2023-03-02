package com.example.coursemanagementsystem;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RunTest {
    public static void main(String[] args) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    }
}
