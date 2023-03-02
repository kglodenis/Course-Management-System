package com.example.coursemanagementsystem.ds;


import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Company extends User implements Serializable {

    private String name;
    private String representative;
    private String address;
    private String phoneNum;
    private UserType userType;

    public Company(String login, String password, LocalDate dateCreated, LocalDate dateModified,  boolean isActive, String name, String representative, String address, String phoneNum) {
        super(login, password, dateCreated, dateModified, isActive);
        this.name = name;
        this.representative = representative;
        this.address = address;
        this.phoneNum = phoneNum;
        this.userType = UserType.MODERATOR;
    }

    public Company(String login, String password, String name, String representative, String address, String phoneNum) {
        super(login, password);
        this.name = name;
        this.representative = representative;
        this.address = address;
        this.phoneNum = phoneNum;
        this.userType = UserType.MODERATOR;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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
