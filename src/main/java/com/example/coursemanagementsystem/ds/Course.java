package com.example.coursemanagementsystem.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private LocalDate dateCreated;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "parentCourse", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> folders;
    @ManyToMany(mappedBy = "myModeratedCourses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> moderators;
    @ManyToMany(mappedBy = "myCourses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Person> students;

    public Course(String title, String description, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.dateCreated = LocalDate.now();
        this.startDate = startDate;
        this.endDate = endDate;
        this.moderators = new ArrayList<>();

    }

    /*public Course(String title, String description, LocalDate startDate, LocalDate endDate, List<User> moderators) {
        this.title = title;
        this.description = description;
        this.dateCreated = LocalDate.now();
        this.startDate = startDate;
        this.endDate = endDate;
        this.moderators = moderators;
    }*/

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public List<User> getModerators() {
        return moderators;
    }

    public void setModerators(List<User> moderators) {
        this.moderators = moderators;
    }

    public List<Person> getStudents() {
        return students;
    }

    public void setStudents(List<Person> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return
                "{ id=" + id +
                ", title=" + title +
                ", description=" + description +
                ", dateCreated=" + dateCreated +
                ", startDate=" + startDate +
                ", endDate=" + endDate+" }";
    }
}
