package com.example.coursemanagementsystem.webControllers;

import com.example.coursemanagementsystem.ds.File;
import com.example.coursemanagementsystem.ds.Folder;
import com.example.coursemanagementsystem.hibernateControllers.FileHibernateController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;
@Controller
public class FileWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    FileHibernateController fileHibernateController = new FileHibernateController(entityManagerFactory);

    @RequestMapping(value = "/file/searchFile/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getFileById(@PathVariable(name = "id") int id)
    {
        Gson gson = new Gson();
        return gson.toJson(fileHibernateController.getFileById(id).toString());

    }

    @RequestMapping(value = "/file/allFiles", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllFiles()
    {
        Gson gson = new Gson();
        return gson.toJson(fileHibernateController.getAllFiles().toString());

    }

    @RequestMapping(value = "/file/addFile", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewFile(@RequestBody String request)
    {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        File file = new File(properties.getProperty("fileName"), properties.getProperty("fileDescription"));
        fileHibernateController.createFile(file);
        return "Success";
    }


    @RequestMapping(value = "/file/updateFile/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFile(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        File file = fileHibernateController.getFileById(id);

        file.setFileName(properties.getProperty("fileName"));
        file.setFileDescription(properties.getProperty("fileDescription"));

        fileHibernateController.editFile(file);
        return "Success";
    }



    @RequestMapping(value = "/file/deleteFile/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteFile(@PathVariable(name = "id") int id) {
        fileHibernateController.removeFile(id);
        if(fileHibernateController.getFileById(id)!=null)
        {
            return "File was deleted";
        }
        else
        {
            return "File was not deleted";
        }

    }
}
