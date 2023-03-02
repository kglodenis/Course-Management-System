package com.example.coursemanagementsystem.fxControllers;

import com.example.coursemanagementsystem.HelloApplication;
import com.example.coursemanagementsystem.ds.Course;
import com.example.coursemanagementsystem.ds.Folder;
import com.example.coursemanagementsystem.hibernateControllers.CourseHibernateController;
import com.example.coursemanagementsystem.hibernateControllers.FolderHibernateController;
import com.example.coursemanagementsystem.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class FolderForm {
    public TextField folderTitle2;
    public TextField folderDescription2;

    private int folderId;
    private int userId;
    private int currentFolder;
    private int courseId;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);
    FolderHibernateController folderHibernateController = new FolderHibernateController(entityManagerFactory);
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);

    public void setData(boolean edit, int folderId, int courseId, int userId, int currentFolder) {
        this.folderId=folderId;
        this.courseId=courseId;
        this.userId=userId;
        this.currentFolder=currentFolder;
        if(edit){
            loadFormData();
        }
    }
    private void loadFormData() {

        Folder folder = folderHibernateController.getFolderById(currentFolder);
        folderTitle2.setText(folder.getFolderName());
        folderDescription2.setText(folder.getFolderDescription());

    }
    public void saveChanges(ActionEvent actionEvent) throws IOException {
        if (folderId != 0) {
            Folder parentFolder = folderHibernateController.getFolderById(folderId);
            Folder folder = new Folder(folderTitle2.getText(), folderDescription2.getText(), parentFolder);
            parentFolder.getSubFolders().add(folder);
            folderHibernateController.editFolder(parentFolder);
        } else if(courseId != 0){
            Course course = courseHibernateController.getCourseById(courseId);
            Folder folder = new Folder(folderTitle2.getText(), folderDescription2.getText(),  course);
            course.getFolders().add(folder);
            courseHibernateController.editCourse(course);
        } else {
            Folder folder = folderHibernateController.getFolderById(currentFolder);
            folder.setFolderName(folderTitle2.getText());
            folder.setFolderDescription(folderDescription2.getText());
            //ir t.t.
            folderHibernateController.editFolder(folder);
        }

        returnToMain();

    }

    public void returnToMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-courses-window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) folderTitle2.getScene().getWindow();
        stage.setScene(scene);
        stage.close();
    }


}
