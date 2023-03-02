package com.example.coursemanagementsystem.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileTableParameters {
    private SimpleIntegerProperty fileId = new SimpleIntegerProperty();
    private SimpleStringProperty fileName = new SimpleStringProperty();
    private SimpleStringProperty fileDescription = new SimpleStringProperty();
    private SimpleStringProperty fileDate = new SimpleStringProperty();

    public FileTableParameters(SimpleIntegerProperty fileId, SimpleStringProperty fileName, SimpleStringProperty fileDescription, SimpleStringProperty fileDate) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.fileDate = fileDate;
    }

    public FileTableParameters() {
    }

    public int getFileId() {
        return fileId.get();
    }

    public SimpleIntegerProperty fileIdProperty() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId.set(fileId);
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public String getFileDescription() {
        return fileDescription.get();
    }

    public SimpleStringProperty fileDescriptionProperty() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription.set(fileDescription);
    }

    public String getFileDate() {
        return fileDate.get();
    }

    public SimpleStringProperty fileDateProperty() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate.set(fileDate);
    }
}
