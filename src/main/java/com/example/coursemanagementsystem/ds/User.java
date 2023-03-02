package com.example.coursemanagementsystem.ds;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;
    private boolean isActive;
    @ManyToMany
    private List<Course> myModeratedCourses;
    @ManyToMany
    private List<Folder> editableFolders;

     @OneToMany(mappedBy = "creator", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
     @OrderBy("id ASC")
     @LazyCollection(LazyCollectionOption.FALSE)
     private List<File> myFiles;

    public User(String login, String password, LocalDate dateCreated, LocalDate dateModified,  boolean isActive) {
        this.login = login;
        this.password = password;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.isActive = isActive;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
        this.isActive = true;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getMyModeratedCourses() {
        return myModeratedCourses;
    }

    public void setMyModeratedCourses(List<Course> myModeratedCourses) {
        this.myModeratedCourses = myModeratedCourses;
    }


    public List<Folder> getEditableFolders() {
        return editableFolders;
    }

    public void setEditableFolders(List<Folder> editableFolders) {
        this.editableFolders = editableFolders;
    }

    public List<File> getMyFiles() {
        return myFiles;
    }

    public void setMyFiles(List<File> myFiles) {
        this.myFiles = myFiles;
    }

    @Override
    public String toString() {
        return  "{ id=" + id +
                ", login=" + login +
                ", password=" + password +
                ", dateCreated=" + dateCreated+" }";
    }
}
