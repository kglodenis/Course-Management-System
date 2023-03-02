package com.example.coursemanagementsystem.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CourseTableParameters {

    private SimpleIntegerProperty courseId = new SimpleIntegerProperty();
    private SimpleStringProperty courseTitle = new SimpleStringProperty();
    private SimpleStringProperty courseDescription = new SimpleStringProperty();
    private SimpleStringProperty courseDateCreated = new SimpleStringProperty();
    private SimpleStringProperty courseStartDate = new SimpleStringProperty();
    private SimpleStringProperty courseEndDate = new SimpleStringProperty();

    public CourseTableParameters(SimpleIntegerProperty courseId, SimpleStringProperty courseTitle, SimpleStringProperty courseDescription, SimpleStringProperty courseDateCreated, SimpleStringProperty courseStartDate, SimpleStringProperty courseEndDate) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseDateCreated = courseDateCreated;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
    }

    public CourseTableParameters() {
    }

    public int getCourseId() {
        return courseId.get();
    }

    public SimpleIntegerProperty courseIdProperty() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId.set(courseId);
    }

    public String getCourseTitle() {
        return courseTitle.get();
    }

    public SimpleStringProperty courseTitleProperty() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle.set(courseTitle);
    }

    public String getCourseDescription() {
        return courseDescription.get();
    }

    public SimpleStringProperty courseDescriptionProperty() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription.set(courseDescription);
    }

    public String getCourseDateCreated() {
        return courseDateCreated.get();
    }

    public SimpleStringProperty courseDateCreatedProperty() {
        return courseDateCreated;
    }

    public void setCourseDateCreated(String courseDateCreated) {
        this.courseDateCreated.set(courseDateCreated);
    }

    public String getCourseStartDate() {
        return courseStartDate.get();
    }

    public SimpleStringProperty courseStartDateProperty() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate.set(courseStartDate);
    }

    public String getCourseEndDate() {
        return courseEndDate.get();
    }

    public SimpleStringProperty courseEndDateProperty() {
        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate.set(courseEndDate);
    }
}
