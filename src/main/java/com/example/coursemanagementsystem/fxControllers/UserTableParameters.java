package com.example.coursemanagementsystem.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserTableParameters {

    private SimpleIntegerProperty userId = new SimpleIntegerProperty();
    private SimpleStringProperty login = new SimpleStringProperty();
    private SimpleStringProperty dateCreated = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty surname = new SimpleStringProperty();
    private SimpleStringProperty position = new SimpleStringProperty();
    private SimpleStringProperty cardNumber = new SimpleStringProperty();
    private SimpleStringProperty studentNumber = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty company = new SimpleStringProperty();
    private SimpleStringProperty representative = new SimpleStringProperty();
    private SimpleStringProperty address = new SimpleStringProperty();
    private SimpleStringProperty phone = new SimpleStringProperty();

    public UserTableParameters(SimpleIntegerProperty userId, SimpleStringProperty login, SimpleStringProperty dateCreated, SimpleStringProperty name, SimpleStringProperty surname, SimpleStringProperty position, SimpleStringProperty cardNumber, SimpleStringProperty studentNumber, SimpleStringProperty email, SimpleStringProperty company, SimpleStringProperty representative, SimpleStringProperty address, SimpleStringProperty phone) {
        this.userId = userId;
        this.login = login;
        this.dateCreated = dateCreated;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.cardNumber = cardNumber;
        this.studentNumber = studentNumber;
        this.email = email;
        this.company = company;
        this.representative = representative;
        this.address = address;
        this.phone = phone;
    }

    public UserTableParameters() {
    }

    public int getUserId() {
        return userId.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getDateCreated() {
        return dateCreated.get();
    }

    public SimpleStringProperty dateCreatedProperty() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated.set(dateCreated);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }


    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public SimpleStringProperty cardNumberProperty() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.set(cardNumber);
    }

    public String getStudentNumber() {
        return studentNumber.get();
    }

    public SimpleStringProperty studentNumberProperty() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber.set(studentNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getCompany() {
        return company.get();
    }

    public SimpleStringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public String getRepresentative() {
        return representative.get();
    }

    public SimpleStringProperty representativeProperty() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative.set(representative);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}
