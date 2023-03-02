package com.example.coursemanagementsystem.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Folder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String folderName;
    private String folderDescription;
    private LocalDate dateCreated;
    @OneToMany(mappedBy = "parentFolder", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> subFolders;
    @ManyToOne
    private Folder parentFolder;
    @ManyToMany(mappedBy = "editableFolders", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> editors;
    @OneToMany(mappedBy = "parentFolder", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<File> files;
    @ManyToOne
    private Course parentCourse;

    public Folder( String folderName, String folderDescription, Course parentCourse) {

        this.folderName = folderName;
        this.folderDescription = folderDescription;
        this.dateCreated = LocalDate.now();
        this.parentCourse = parentCourse;
    }

    public Folder(String folderName, String folderDescription, Folder parentFolder) {
        this.folderName = folderName;
        this.folderDescription = folderDescription;
        this.parentFolder = parentFolder;
        this.dateCreated = LocalDate.now();
    }

    public Folder(String folderName) {
        this.folderName = folderName;
        this.dateCreated = LocalDate.now();
        this.files = new ArrayList<>();
    }

    public Folder(String folderName, String folderDescription) {
        this.folderName = folderName;
        this.folderDescription = folderDescription;
        this.dateCreated = LocalDate.now();
    }

    public Folder() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderDescription() {
        return folderDescription;
    }

    public void setFolderDescription(String folderDescription) {
        this.folderDescription = folderDescription;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Folder> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(List<Folder> subFolders) {
        this.subFolders = subFolders;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public List<User> getEditors() {
        return editors;
    }

    public void setEditors(List<User> editors) {
        this.editors = editors;
    }
    public Course getParentCourse() {
        return parentCourse;
    }

    public void setParentCourse(Course parentCourse) {
        this.parentCourse = parentCourse;
    }

    @Override
    public String toString() {
        return "{ id=" + id +
                ", folderName=" + folderName +
                ", folderDescription=" + folderDescription +
                ", dateCreated=" + dateCreated+" }";
    }
}
