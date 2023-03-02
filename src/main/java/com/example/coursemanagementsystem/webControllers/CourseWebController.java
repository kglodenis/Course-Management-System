package com.example.coursemanagementsystem.webControllers;

import com.example.coursemanagementsystem.databaseControllers.CourseGsonSerializer;
import com.example.coursemanagementsystem.databaseControllers.CourseListGsonSerializer;
import com.example.coursemanagementsystem.databaseControllers.LocalDateGsonSerializer;
import com.example.coursemanagementsystem.ds.Course;
import com.example.coursemanagementsystem.hibernateControllers.CourseHibernateController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
@Controller
public class CourseWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    CourseHibernateController courseHibernateController = new CourseHibernateController(entityManagerFactory);

    @RequestMapping(value = "/course/searchCourse/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCourseById(@PathVariable(name = "id") int id)
    {
        Gson gson = new Gson();
        return gson.toJson(courseHibernateController.getCourseById(id).toString());

    }

    @RequestMapping(value = "/course/allCourses", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllCourses()
    {
        List<Course> allCourses = courseHibernateController.getAllCourses();
        GsonBuilder gson = new GsonBuilder();
        Type courseList = new TypeToken<List<Course>>(){
        }.getType();
        gson.registerTypeAdapter(Course.class, new CourseGsonSerializer()).registerTypeAdapter(LocalDate.class,new LocalDateGsonSerializer()).registerTypeAdapter(courseList, new CourseListGsonSerializer());
        Gson parser = gson.create();
        return parser.toJson(allCourses);

    }

    @RequestMapping(value = "/course/addCourse", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewCourse(@RequestBody String request)
    {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Course course = new Course(properties.getProperty("title"), properties.getProperty("description"), LocalDate.parse(properties.getProperty("startDate")),LocalDate.parse(properties.getProperty("endDate")));
        courseHibernateController.createCourse(course);
        return "Success";
    }


    @RequestMapping(value = "/course/updateCourse/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCourse(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Course course = courseHibernateController.getCourseById(id);

        course.setTitle(properties.getProperty("title"));
        course.setDescription(properties.getProperty("description"));
        course.setStartDate(LocalDate.parse(properties.getProperty("startDate")));
        course.setEndDate(LocalDate.parse(properties.getProperty("endDate")));

        courseHibernateController.editCourse(course);
        return "Success";
    }

   

    @RequestMapping(value = "/course/deleteCourse/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteCourse(@PathVariable(name = "id") int id) {
        courseHibernateController.removeCourse(id);
        if(courseHibernateController.getCourseById(id)!=null)
        {
            return "Course was not deleted";
        }
        else
        {
            return "Course was deleted";
        }

    }
}
