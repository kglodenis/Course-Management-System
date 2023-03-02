package com.example.coursemanagementsystem.ds;


//iport lombok.Getter;
//import lombok.Setter;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

//@Getter
//@Setter
@Entity
public class File implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fileName;
    private String fileDescription;
    private LocalDate dateCreated;
    @ManyToOne
    private User creator;
    @ManyToOne
    private Folder parentFolder;


    public File(int id, String fileName, String fileDescription, LocalDate dateCreated, User creator) {
        this.id = id;
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.dateCreated = dateCreated;
        this.creator = creator;
    }

    public File(String fileName, String fileDescription) {
        this.id = id;
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.dateCreated = LocalDate.now();

    }

    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    @Override
    public String toString() {
        return  "{fileName=" + fileName +
                ", fileDescription=" + fileDescription +
                ", dateCreated=" + dateCreated +" }";
    }
}
