package com.example.coursemanagementsystem.fxControllers;

import com.example.coursemanagementsystem.HelloApplication;
import com.example.coursemanagementsystem.databaseControllers.DbUtils;
import com.example.coursemanagementsystem.ds.*;
import com.example.coursemanagementsystem.hibernateControllers.CourseHibernateController;
import com.example.coursemanagementsystem.hibernateControllers.FileHibernateController;
import com.example.coursemanagementsystem.hibernateControllers.FolderHibernateController;
import com.example.coursemanagementsystem.hibernateControllers.UserHibernateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainCoursesWindow implements Initializable {
    public ComboBox allCourses;
    public TextField courseTitle;
    public TextField courseDescription;
    public DatePicker courseStartDate;
    public DatePicker courseEndDate;
    public TextField courseTitleTxt;
    public TextField courseDescriptionTxt;
    public DatePicker courseStartTxt;
    public DatePicker courseEndTxt;
    public ComboBox allCourses2;
    public ComboBox allFolders;
    public TextField folderDescription;
    public TextField folderName;
    public ComboBox allCourses1;
    public TableView usersTable;
    public TableColumn<UserTableParameters, Integer> colId;
    public TableColumn<UserTableParameters, String> colLogin;
    public TableColumn<UserTableParameters, String> colDate;
    public TableColumn<UserTableParameters, String> colType;
    public TableColumn<UserTableParameters, String> colCardNumber;
    public TableColumn<UserTableParameters, String> colStudNumber;
    public TableColumn<UserTableParameters, String> colEmail;
    public TableColumn<UserTableParameters, String> colAddress;
    public TableColumn<UserTableParameters, String> colPhone;
    public TableColumn<UserTableParameters, String> colRepres;
    public TableColumn<UserTableParameters, String> colName;
    public TableColumn<UserTableParameters, String> colSurname;
    public TableView courseTable;
    public TableColumn<CourseTableParameters, Integer> courseColId;
    public TableColumn<CourseTableParameters, String> courseColTitle;
    public TableColumn<CourseTableParameters, String> courseColDescription;
    public TableColumn<CourseTableParameters, String> courseColDateCreated;
    public TableColumn<CourseTableParameters, String> courseColStartDate;
    public TableColumn<CourseTableParameters, String> courseColEndDate;
    public ComboBox allUsersForDelete;
    public ComboBox allFiles;
    public TextField fileDescription;
    public TextField fileName;
    public TableView folderTable;
    public TableColumn<FolderTableParameters, Integer> folderColId;
    public TableColumn<FolderTableParameters, String> folderColName;
    public TableColumn<FolderTableParameters, String> folderColDescription;
    public TableColumn<FolderTableParameters, String> folderColDate;
    public TableView fileTable;
    public TableColumn<FileTableParameters, Integer> fileColId;
    public TableColumn<FileTableParameters, String> fileColName;
    public TableColumn<FileTableParameters, String> fileColDescription;
    public TableColumn<FileTableParameters, String> fileColDate;
    public Tab courseCreateButton;
    public Tab courseDeleteButton;
    public Tab courseViewButton;
    public Tab folderCreateButton;
    public Tab folderDeleteButton;
    public Tab fileCreateButton;
    public Tab fileDeleteButton;
    public Tab userTab;
    public Tab myModeratedCourses;
    public ListView myCourses;
    public TreeView courseFolders;
    public Tab folderTab;
    public Tab fileTab;
    private ObservableList<UserTableParameters> data = FXCollections.observableArrayList();
    private ObservableList<CourseTableParameters> courseData = FXCollections.observableArrayList();
    private ObservableList<FolderTableParameters> folderData = FXCollections.observableArrayList();
    private ObservableList<FileTableParameters> fileData = FXCollections.observableArrayList();
    private String login;
    private String password;
    private int userId;


    private Connection connection;
    private Statement statement;
    private CourseManagementSystem courseManagementSystem;


    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);
    FolderHibernateController folderHibernateController = new FolderHibernateController(entityManagerFactory);
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);
    FileHibernateController fileHibernateController = new FileHibernateController(entityManagerFactory);

    public void setCourseManagementSystem(CourseManagementSystem courseManagementSystem) {
        this.courseManagementSystem = courseManagementSystem;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        usersTable.setEditable(true);
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colLogin.setCellFactory(TextFieldTableCell.forTableColumn());
        colLogin.setOnEditCommit(t -> {
                    String newLogin = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setLogin(t.getNewValue());
                    DbUtils.updateField("user", "`login`", newLogin, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getUserId());
                }
        );
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        colType.setCellValueFactory(new PropertyValueFactory<>("position"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(t -> {
                    String newName = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue());
                    DbUtils.updateField("user", "`name`", newName, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getUserId());
                }
        );
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        colSurname.setOnEditCommit(t -> {
                    String newSurname = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setSurname(t.getNewValue());
                    DbUtils.updateField("user", "`surname`", newSurname, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getUserId());
                }
        );
        colCardNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        colCardNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        colCardNumber.setOnEditCommit(t -> {
                    String newCardNumber = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setCardNumber(t.getNewValue());
                    DbUtils.updateField("user", "`cardNumber`", newCardNumber, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getUserId());
                }
        );
        colStudNumber.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        colStudNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        colStudNumber.setOnEditCommit(t -> {
                    String newStudNumber = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setStudentNumber(t.getNewValue());
                    DbUtils.updateField("user", "`studentNumber`", newStudNumber, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getUserId());
                }
        );
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        colAddress.setOnEditCommit(t -> {
                    String newAddress = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setAddress(t.getNewValue());
                    DbUtils.updateField("user", "`address`", newAddress, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getUserId());
                }
        );
        colRepres.setCellValueFactory(new PropertyValueFactory<>("representative"));
        colRepres.setCellFactory(TextFieldTableCell.forTableColumn());
        colRepres.setOnEditCommit(t -> {
                    String newRepresentative = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setRepresentative(t.getNewValue());
                    DbUtils.updateField("user", "`representative`", newRepresentative, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getUserId());
                }
        );
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(t -> {
                    String newEmail = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setEmail(t.getNewValue());
                    DbUtils.updateField("user", "`email`", newEmail, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getUserId());
                }
        );

        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setOnEditCommit(t -> {
                    String newNumber = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setPhone(t.getNewValue());
                    DbUtils.updateField("user", "`phoneNum`", newNumber, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getUserId());
                }
        );

        try {
            loadAllUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadAllUsers() throws SQLException {
        usersTable.setEditable(true);
        usersTable.getItems().clear();
        connection = DbUtils.connectToDb();
        String sql = "SELECT * from user";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            UserTableParameters userTableParameters = new UserTableParameters();
            userTableParameters.setUserId(rs.getInt(2));
            userTableParameters.setLogin(rs.getString(3));
            userTableParameters.setDateCreated(rs.getString(7));
            userTableParameters.setPosition(rs.getString(1));
            userTableParameters.setName(rs.getString(5));
            userTableParameters.setSurname(rs.getString(6));
            userTableParameters.setCardNumber(rs.getString(10));
            userTableParameters.setStudentNumber(rs.getString(11));
            userTableParameters.setAddress(rs.getString(13));
            userTableParameters.setRepresentative(rs.getString(15));
            userTableParameters.setEmail(rs.getString(12));
            userTableParameters.setPhone(rs.getString(14));
            data.add(userTableParameters);
        }
        usersTable.setItems(data);
        DbUtils.disconnectFromDb(connection, statement);
    }

    public void disableOrEnable() {
        User currentUser = userHibernateController.getUserByLogin(login);
        String type = currentUser.getUserType().toString();

        if (type == "STUDENT") {
            courseCreateButton.setDisable(true);
            courseDeleteButton.setDisable(true);
            fileDeleteButton.setDisable(true);
            fileCreateButton.setDisable(true);
            userTab.setDisable(true);
            folderCreateButton.setDisable(true);
            folderDeleteButton.setDisable(true);
            myModeratedCourses.setDisable(true);
            folderTab.setDisable(true);
            fileTab.setDisable(true);
        } else if (type == "MODERATOR") {
            userTab.setDisable(true);
            MyModeratedCourses();
            folderTab.setDisable(true);
            courseDeleteButton.setDisable(true);
        } else {
            setEditableTable();
            myModeratedCourses.setDisable(true);
        }


    }

    public void MyModeratedCourses() {


        myCourses.getItems().clear();
        List<Course> myCoursesFromDb = courseHibernateController.getProjectByUserId(userId);
        for (Course p : myCoursesFromDb) {
           myCourses.getItems().add(p.getId() + ": " + p.getTitle());
        }
    }

    public void FileTableView(Event event) {
        fileTable.setEditable(true);
        fileColId.setCellValueFactory(new PropertyValueFactory<>("fileId"));
        fileColName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        fileColDescription.setCellValueFactory(new PropertyValueFactory<>("fileDescription"));
        fileColDate.setCellValueFactory(new PropertyValueFactory<>("fileDate"));
        try {
            loadAllFiles();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllFiles() throws SQLException {
        fileTable.setEditable(true);
        fileTable.getItems().clear();
        connection = DbUtils.connectToDb();
        String sql = "SELECT * FROM file";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            FileTableParameters fileTableParameters = new FileTableParameters();
            fileTableParameters.setFileId(rs.getInt(1));
            fileTableParameters.setFileName(rs.getString(2));
            fileTableParameters.setFileDescription(rs.getString(3));
            fileTableParameters.setFileDate(rs.getString(4));
            fileData.add(fileTableParameters);
        }
        fileTable.setItems(fileData);
        DbUtils.disconnectFromDb(connection, statement);
    }

    public void FolderTableView(Event event) {
        folderTable.setEditable(true);
        folderColId.setCellValueFactory(new PropertyValueFactory<>("folderId"));
        folderColName.setCellValueFactory(new PropertyValueFactory<>("folderName"));

        folderColDescription.setCellValueFactory(new PropertyValueFactory<>("folderDescription"));

        folderColDate.setCellValueFactory(new PropertyValueFactory<>("folderDate"));
        try {
            loadAllFolders();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadAllFolders() throws SQLException {
        folderTable.setEditable(true);
        folderTable.getItems().clear();
        connection = DbUtils.connectToDb();
        String sql = "SELECT * FROM folder";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            FolderTableParameters folderTableParameters = new FolderTableParameters();
            folderTableParameters.setFolderId(rs.getInt(1));
            folderTableParameters.setFolderName(rs.getString(2));
            folderTableParameters.setFolderDescription(rs.getString(3));
            folderTableParameters.setFolderDate(rs.getString(4));
            folderData.add(folderTableParameters);
        }
        folderTable.setItems(folderData);
        DbUtils.disconnectFromDb(connection, statement);
    }

    public void CourseTableView(Event event) {
        courseTable.setEditable(true);
        courseColId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseColTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        courseColDescription.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
        courseColDateCreated.setCellValueFactory(new PropertyValueFactory<>("courseDateCreated"));
        courseColStartDate.setCellValueFactory(new PropertyValueFactory<>("courseStartDate"));
        courseColEndDate.setCellValueFactory(new PropertyValueFactory<>("courseEndDate"));


        try {
            loadAllCourses();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadAllCourses() throws SQLException {
        courseTable.setEditable(true);
        courseTable.getItems().clear();
        connection = DbUtils.connectToDb();
        String sql = "SELECT * FROM course";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            CourseTableParameters courseTableParameters = new CourseTableParameters();
            courseTableParameters.setCourseId(rs.getInt(1));
            courseTableParameters.setCourseTitle(rs.getString(2));
            courseTableParameters.setCourseDescription(rs.getString(3));
            courseTableParameters.setCourseDateCreated(rs.getString(4));
            courseTableParameters.setCourseStartDate(rs.getString(5));
            courseTableParameters.setCourseEndDate(rs.getString(6));
            courseData.add(courseTableParameters);
        }
        courseTable.setItems(courseData);
        DbUtils.disconnectFromDb(connection, statement);
    }


    public void CreateCourse(ActionEvent actionEvent) {

        User currentUser = userHibernateController.getUserByLogin(login);
        String type = currentUser.getUserType().toString();


            List<User> moderators = new ArrayList<User>();
            //  User currentUser = userHibernateController.getUserByLogin(login);
            moderators.add(currentUser);
            Course course = new Course(courseTitle.getText(), courseDescription.getText(), courseStartDate.getValue(), courseEndDate.getValue());
            course.getModerators().add(currentUser);
            courseHibernateController.createCourse(course);
            currentUser.getMyModeratedCourses().add(course);
            userHibernateController.editUser(currentUser);
            alertMessage("Course has been created successfully");
            MyModeratedCourses();




        //courseManagementSystem.getAllSysCourses().add(course);

    }

    public void loadCourses(MouseEvent mouseEvent) throws SQLException {

        User currentUser = userHibernateController.getUserByLogin(login);
        String type = currentUser.getUserType().toString();
        if(type=="MODERATOR"){
            ObservableList data2 = FXCollections.observableArrayList();
            List<Course> myCoursesFromDb2 = courseHibernateController.getProjectByUserId(userId);
            for (Course p : myCoursesFromDb2) {
                data2.add(new String(p.getTitle()));
            }
            allCourses2.setItems(data2);
        }
        else if(type=="ADMIN")
        {
            try {
                connection = DbUtils.connectToDb();
                statement = connection.createStatement();

                ResultSet rs = statement.executeQuery("SELECT * FROM COURSE");
                ObservableList data = FXCollections.observableArrayList();

                while (rs.next()) {
                    data.add(new String(rs.getString(2)));
                }
                allCourses2.setItems(data);
                //allCourses1.setItems(data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

    public void deleteCourse(ActionEvent actionEvent) throws SQLException {
        String query = "SELECT * FROM course WHERE title = '" + allCourses2.getValue() + "'";
        ResultSet rs = statement.executeQuery(query);
        int courseID = 0;
        while (rs.next()) {
            //courseID = rs.getInt("id");
            courseHibernateController.removeCourse(rs.getInt("id"));
        }

       /* if (courseID != 0) {

        } else {
            alertMessage("Course not found");
        }*/
    }


    public void loadFolders(MouseEvent mouseEvent) {
        try {
            connection = DbUtils.connectToDb();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM FOLDER");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()) {
                data.add(new String(rs.getString(2)));
            }
            allFolders.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFolder(ActionEvent actionEvent) throws SQLException {
        String query = "SELECT * FROM FOLDER WHERE folderName = '" + allFolders.getValue() + "'";
        ResultSet rs = statement.executeQuery(query);
        int folderID = 0;
        while (rs.next()) {
            folderID = rs.getInt("id");
        }

        if (folderID != 0) {
            folderHibernateController.remove(folderID);
        } else {
            alertMessage("Folder not found");
        }
    }

    public void CreateFolder(ActionEvent actionEvent) {

        Folder folder = new Folder(folderName.getText(), folderDescription.getText());
        //List<Folder> courses = new ArrayList<>();
        //courses.add(parentCourse);
        folderHibernateController.createFolder(folder);
        alertMessage("Folder has been created successfully");

    }

    public void setLoginData(String passwords, String logins, int id) {

        this.password = passwords;
        this.login = logins;
        this.userId= id;

        disableOrEnable();


    }

    public void LogOut(ActionEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) courseDescription.getScene().getWindow();
        stage.setTitle("Course Management System");
        stage.setScene(scene);
        stage.show();
        /* Stage stage = (Stage) allCourses.getScene().getWindow();
        stage.close();*/
    }

    private static void alertMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Message text:");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    private static void errorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Message text:");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }


    public void deleteUser(ActionEvent actionEvent) throws SQLException {
        connection = DbUtils.connectToDb();
        statement = connection.createStatement();
        String query = "SELECT * FROM USER WHERE LOGIN = '" + allUsersForDelete.getValue() + "'";
        ResultSet rs = statement.executeQuery(query);
        int userID = 0;
        while (rs.next()) {
            userID = rs.getInt("id");
        }

        if (userID != 0) {
            userHibernateController.removeUser(userID);
        } else {
            alertMessage("User not found");
        }
        DbUtils.disconnectFromDb(connection, statement);
    }

    public void loadUser(MouseEvent mouseEvent) {
        try {
            connection = DbUtils.connectToDb();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM USER");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()) {
                data.add(new String(rs.getString(3)));
            }
            allUsersForDelete.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
        DbUtils.disconnectFromDb(connection, statement);
    }

    public void DeleteFile(ActionEvent actionEvent) throws SQLException {
        connection = DbUtils.connectToDb();
        statement = connection.createStatement();
        String query = "SELECT * FROM FILE WHERE fileName = '" + allFiles.getValue() + "'";
        ResultSet rs = statement.executeQuery(query);
        int fileID = 0;
        while (rs.next()) {
            fileID = rs.getInt("id");
        }

        if (fileID != 0) {
            fileHibernateController.removeFile(fileID);
        } else {
            alertMessage("File not found");
        }
        DbUtils.disconnectFromDb(connection, statement);
    }

    public void CreateFile(ActionEvent actionEvent) {
        File file = new File(fileName.getText(), fileDescription.getText());
        fileHibernateController.createFile(file);
        alertMessage("File has been created successfully");
    }

    public void loadFiles(MouseEvent mouseEvent) {
        try {
            connection = DbUtils.connectToDb();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM FILE");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()) {
                data.add(new String(rs.getString(2)));
            }
            allFiles.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
        DbUtils.disconnectFromDb(connection, statement);
    }

    private void setEditableTable() {
        folderColName.setCellFactory(TextFieldTableCell.forTableColumn());
        folderColName.setOnEditCommit(t -> {
                    String newName = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setFolderName(t.getNewValue());
                    DbUtils.updateField("folder", "`folderName`", newName, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getFolderId());
                }
        );
        folderColDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        folderColDescription.setOnEditCommit(t -> {
                    String newDescription = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setFolderDescription(t.getNewValue());
                    DbUtils.updateField("folder", "`folderDescription`", newDescription, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getFolderId());
                }
        );

        fileColName.setCellFactory(TextFieldTableCell.forTableColumn());
        fileColName.setOnEditCommit(t -> {
                    String newName = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setFileName(t.getNewValue());
                    DbUtils.updateField("file", "`fileName`", newName, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getFileId());
                }
        );
        fileColDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        fileColDescription.setOnEditCommit(t -> {
                    String newDescription = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setFileDescription(t.getNewValue());
                    DbUtils.updateField("file", "`fileDescription`", newDescription, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getFileId());
                }
        );

        courseColTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        courseColTitle.setOnEditCommit(t -> {
                    String newTitle = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setCourseTitle(t.getNewValue());
                    DbUtils.updateField("course", "`title`", newTitle, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getCourseId());
                }
        );
        courseColDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        courseColDescription.setOnEditCommit(t -> {
                    String newDescription = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setCourseDescription(t.getNewValue());
                    DbUtils.updateField("course", "`description`", newDescription, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getCourseId());
                }
        );
        courseColStartDate.setCellFactory(TextFieldTableCell.forTableColumn());
        courseColStartDate.setOnEditCommit(t -> {
                    String newStart = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setCourseStartDate(t.getNewValue());
                    DbUtils.updateField("course", "`startDate`", newStart, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getCourseId());
                }
        );
        courseColEndDate.setCellFactory(TextFieldTableCell.forTableColumn());
        courseColEndDate.setOnEditCommit(t -> {
                    String newEnd = t.getNewValue();
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setCourseEndDate(t.getNewValue());
                    DbUtils.updateField("course", "`endDate`", newEnd, t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getCourseId());
                }
        );
    }

    public void loadFolder() {
        courseFolders.refresh();
        Course selectedCourse = courseHibernateController.getCourseById(
                Integer.parseInt(myCourses.getSelectionModel().getSelectedItem().toString().split(":")[0])
        );

        courseFolders.setRoot(new TreeItem<String>("Course folders:"));
        courseFolders.setShowRoot(false);
        courseFolders.getRoot().setExpanded(true);
        selectedCourse.getFolders().forEach(folder -> addTreeItem(folder, courseFolders.getRoot()));

    }

    private void addTreeItem(Folder folder, TreeItem parentFolder)
    {
        TreeItem<Folder> treeItem = new TreeItem<>(folder);
        parentFolder.getChildren().add(treeItem);
        folder.getSubFolders().forEach(sub -> addTreeItem(sub, treeItem));
    }

    public void addFolder(ActionEvent actionEvent) throws IOException {


        TreeItem<Folder> taskTreeItem = (TreeItem<Folder>) courseFolders.getSelectionModel().getSelectedItem();
        if (taskTreeItem == null) {
            loadFolderForm(false, 0, Integer.parseInt(myCourses.getSelectionModel().getSelectedItem().toString().split(":")[0]), 0);
        } else {
            loadFolderForm(false, taskTreeItem.getValue().getId(), 0, 0);
        }
    }

    public void editFolder(ActionEvent actionEvent) throws IOException {
        TreeItem<Folder> taskTreeItem = (TreeItem<Folder>) courseFolders.getSelectionModel().getSelectedItem();
        loadFolderForm(true, 0, 0, taskTreeItem.getValue().getId());
    }

    public void deleteFolder2(ActionEvent actionEvent) {
        TreeItem<Folder> taskTreeItem = (TreeItem<Folder>) courseFolders.getSelectionModel().getSelectedItem();
        folderHibernateController.remove(taskTreeItem.getValue().getId());
    }
    private void loadFolderForm(boolean edit, int folderId, int courseId, int currentFolder) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("folder-form.fxml"));
        Parent root = fxmlLoader.load();
        FolderForm folderForm = fxmlLoader.getController();
        folderForm.setData(edit, folderId, courseId, userId, currentFolder);
        /*Scene scene = new Scene(root);
        Stage stage = (Stage) myCourses.getScene().getWindow();
        stage.setScene(scene);
        stage.show();*/


        Stage stage = new Stage();
        stage.setTitle("Folder form");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
    }


    public void editCourseFromTable(ActionEvent actionEvent) throws IOException {
        loadCourseForm(true, Integer.parseInt(
                myCourses.getSelectionModel().getSelectedItem().toString().split(":")[0])
        );
    }

    public void deleteCourseFromTable(ActionEvent actionEvent) {
        courseHibernateController.removeCourse(Integer.parseInt(myCourses.getSelectionModel().getSelectedItem().toString().split(":")[0]));
        MyModeratedCourses();

    }
    private void loadCourseForm(boolean edit, int courseId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("course-form.fxml"));
        Parent root = fxmlLoader.load();
        CourseForm courseForm = fxmlLoader.getController();
        courseForm.setCourseFormData(userId, edit, courseId);
        Stage stage = new Stage();
        stage.setTitle("Course form");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }
}
