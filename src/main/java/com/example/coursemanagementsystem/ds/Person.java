package com.example.coursemanagementsystem.ds;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Person extends User implements Serializable {

    private String name;
    private String surname;
    private String cardNumber;
    private String studentNumber;
    private String email;
    private UserType userType;
    @ManyToMany
    private List<Course> myCourses;

    public Person(String login, String password, LocalDate dateCreated, LocalDate dateModified,UserType userType, boolean isActive, String name, String surname, String cardNumber, String studentNumber, String email) {
        super(login, password, dateCreated, dateModified, isActive);
        this.name = name;
        this.surname = surname;
        this.cardNumber = cardNumber;
        this.studentNumber = studentNumber;
        this.email = email;
        this.userType = UserType.STUDENT;
    }

    public Person(String login, String password, String name, String surname, String cardNumber, String studentNumber, String email) {
        super(login, password);
        this.name = name;
        this.surname = surname;
        this.cardNumber = cardNumber;
        this.studentNumber = studentNumber;
        this.email = email;
        this.userType = UserType.STUDENT;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

    @Override
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
