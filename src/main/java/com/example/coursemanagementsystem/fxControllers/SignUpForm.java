package com.example.coursemanagementsystem.fxControllers;

import com.example.coursemanagementsystem.HelloApplication;
import com.example.coursemanagementsystem.databaseControllers.RW;
import com.example.coursemanagementsystem.ds.Company;
import com.example.coursemanagementsystem.ds.CourseManagementSystem;
import com.example.coursemanagementsystem.ds.Person;
import com.example.coursemanagementsystem.ds.User;
import com.example.coursemanagementsystem.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.coursemanagementsystem.databaseControllers.Constants.OUT_FILE;

public class SignUpForm implements Initializable {

    @FXML
    public ToggleGroup userType;
    @FXML
    public TextField usernameF;
    @FXML
    public PasswordField passwordF;
    @FXML
    public RadioButton radioP;
    @FXML
    public RadioButton radioC;
    @FXML
    public TextField personNameF;
    @FXML
    public TextField personSurnameF;
    @FXML
    public TextField personCardNumberF;
    @FXML
    public TextField personStudentNumberF;
    @FXML
    public TextField personEmailF;
    @FXML
    public TextField companyNameF;
    @FXML
    public TextField companyRepresentativeF;
    @FXML
    public TextField companyAddressF;
    @FXML
    public TextField companyPhoneF;
    List<User> users = new ArrayList<>();
    private CourseManagementSystem courseManagementSystem;
    private Connection connection;
    private PreparedStatement preparedStatement;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);

    public void setCourseManagementSystem(CourseManagementSystem courseManagementSystem) {
        this.courseManagementSystem = courseManagementSystem;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companyNameF.setDisable(true);
        companyRepresentativeF.setDisable(true);
        companyAddressF.setDisable(true);
        companyPhoneF.setDisable(true);

    }

    public void createUser(ActionEvent actionEvent) throws IOException, SQLException {
        if(radioP.isSelected())
        {
            Person person = new Person(usernameF.getText(),passwordF.getText(),personNameF.getText(),personSurnameF.getText(),personCardNumberF.getText(),personStudentNumberF.getText(),personEmailF.getText());
            //insert į duomenų bazę
            userHibernateController.createUser(person);
            users.add(person);
            /*connection = DbUtils.connectToDb();
            String insertString = "INSERT INTO user(`login`, `password`, `dateCreated`, `dateModified`, `isActive`, `name`, `surname`, `cardNumber`, `studentNumber`, `email`, `DTYPE`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertString);
            preparedStatement.setString(1, person.getLogin());
            preparedStatement.setString(2, person.getPassword());
            preparedStatement.setDate(3, Date.valueOf(person.getDateCreated()));
            preparedStatement.setDate(4, Date.valueOf(person.getDateModified()));
            preparedStatement.setBoolean(5, person.isActive());
            preparedStatement.setString(6, person.getName());
            preparedStatement.setString(7, person.getSurname());
            preparedStatement.setString(8, person.getCardNumber());
            preparedStatement.setString(9, person.getStudentNumber());
            preparedStatement.setString(10, person.getEmail());
            preparedStatement.setString(11,"Person");
            preparedStatement.execute();

            DbUtils.disconnectFromDb(connection, preparedStatement);*/


            //INSERT INTO ... VALUES (person.getUsername(), person.getPassword(), ..., person.getDateCreated();
            //courseManagementSystem.getAllSysUsers().add(person);
        }
        else
        {
            Company company = new Company(usernameF.getText(), passwordF.getText(), companyNameF.getText(),companyRepresentativeF.getText(),companyAddressF.getText(),companyPhoneF.getText());

            userHibernateController.createUser(company);
            //courseManagementSystem.getAllSysUsers().add(company);
            users.add(company);
        }
        RW.writeToFile(OUT_FILE, courseManagementSystem);
        returnToPrevious();
    }

    private void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-window.fxml"));
        Parent root=fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) usernameF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void returnToLogin(ActionEvent actionEvent) throws IOException {
        returnToPrevious();
    }

    public void enableFields(ActionEvent actionEvent) {
        if(radioP.isSelected())
        {
            personNameF.setDisable(false);
            personSurnameF.setDisable(false);
            personCardNumberF.setDisable(false);
            personStudentNumberF.setDisable(false);
            personEmailF.setDisable(false);
            companyNameF.setDisable(true);
            companyRepresentativeF.setDisable(true);
            companyAddressF.setDisable(true);
            companyPhoneF.setDisable(true);
        }
        else
        {
            personNameF.setDisable(true);
            personSurnameF.setDisable(true);
            personCardNumberF.setDisable(true);
            personStudentNumberF.setDisable(true);
            personEmailF.setDisable(true);
            companyNameF.setDisable(false);
            companyRepresentativeF.setDisable(false);
            companyAddressF.setDisable(false);
            companyPhoneF.setDisable(false);
        }
    }
}
