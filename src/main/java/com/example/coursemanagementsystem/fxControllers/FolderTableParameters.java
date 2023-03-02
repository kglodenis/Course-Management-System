package com.example.coursemanagementsystem.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FolderTableParameters {
    private SimpleIntegerProperty folderId = new SimpleIntegerProperty();
    private SimpleStringProperty folderName = new SimpleStringProperty();
    private SimpleStringProperty folderDescription = new SimpleStringProperty();
    private SimpleStringProperty folderDate = new SimpleStringProperty();

    public FolderTableParameters(SimpleIntegerProperty folderId, SimpleStringProperty folderName, SimpleStringProperty folderDescription, SimpleStringProperty folderDate) {
        this.folderId = folderId;
        this.folderName = folderName;
        this.folderDescription = folderDescription;
        this.folderDate = folderDate;
    }

    public FolderTableParameters() {
    }

    public int getFolderId() {
        return folderId.get();
    }

    public SimpleIntegerProperty folderIdProperty() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId.set(folderId);
    }

    public String getFolderName() {
        return folderName.get();
    }

    public SimpleStringProperty folderNameProperty() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName.set(folderName);
    }

    public String getFolderDescription() {
        return folderDescription.get();
    }

    public SimpleStringProperty folderDescriptionProperty() {
        return folderDescription;
    }

    public void setFolderDescription(String folderDescription) {
        this.folderDescription.set(folderDescription);
    }

    public String getFolderDate() {
        return folderDate.get();
    }

    public SimpleStringProperty folderDateProperty() {
        return folderDate;
    }

    public void setFolderDate(String folderDate) {
        this.folderDate.set(folderDate);
    }
}
