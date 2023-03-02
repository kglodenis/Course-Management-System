package com.example.coursemanagementsystem.webControllers;

import com.example.coursemanagementsystem.ds.Folder;
import com.example.coursemanagementsystem.hibernateControllers.FolderHibernateController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;
@Controller
public class FolderWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    FolderHibernateController folderHibernateController = new FolderHibernateController(entityManagerFactory);

    @RequestMapping(value = "/folder/searchFolder/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCourseById(@PathVariable(name = "id") int id)
    {
        Gson gson = new Gson();
        return gson.toJson(folderHibernateController.getFolderById(id).toString());

    }

    @RequestMapping(value = "/folder/allFolders", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllFolders()
    {
        Gson gson = new Gson();
        return gson.toJson(folderHibernateController.getAllFolders().toString());

    }

    @RequestMapping(value = "/folder/addFolder", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewFolder(@RequestBody String request)
    {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Folder folder = new Folder(properties.getProperty("folderName"), properties.getProperty("folderDescription"));
        folderHibernateController.createFolder(folder);
        return "Success";
    }


    @RequestMapping(value = "/folder/updateFolder/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFolder(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Folder folder = folderHibernateController.getFolderById(id);

        folder.setFolderName(properties.getProperty("folderName"));
        folder.setFolderDescription(properties.getProperty("folderDescription"));

        folderHibernateController.editFolder(folder);
        return "Success";
    }



    @RequestMapping(value = "/folder/deleteFolder/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteFolder(@PathVariable(name = "id") int id) {
        folderHibernateController.remove(id);
        if(folderHibernateController.getFolderById(id)!=null)
        {
            return "Folder was deleted";
        }
        else
        {
            return "Folder was not deleted";
        }

    }
}
