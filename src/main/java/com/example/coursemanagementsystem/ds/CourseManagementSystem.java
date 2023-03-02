package com.example.coursemanagementsystem.ds;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseManagementSystem implements Serializable {

    private int id;
    private String version;
    private ArrayList<User> allSysUsers;
    private ArrayList<Course> allSysCourses;

    public CourseManagementSystem(int id, String version, ArrayList<User> allSysUsers, ArrayList<Course> allSysCourses) {
        this.id = id;
        this.version = version;
        this.allSysUsers = allSysUsers;
        this.allSysCourses = allSysCourses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<User> getAllSysUsers() {
        return allSysUsers;
    }

    public void setAllSysUsers(ArrayList<User> allSysUsers) {
        this.allSysUsers = allSysUsers;
    }

    public ArrayList<Course> getAllSysCourses() {
        return allSysCourses;
    }

    public void setAllSysCourses(ArrayList<Course> allSysCourses) {
        this.allSysCourses = allSysCourses;
    }


}
