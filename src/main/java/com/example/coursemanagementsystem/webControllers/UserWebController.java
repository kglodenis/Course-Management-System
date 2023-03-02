package com.example.coursemanagementsystem.webControllers;

import com.example.coursemanagementsystem.databaseControllers.CompanyGsonSerializer;
import com.example.coursemanagementsystem.databaseControllers.LocalDateGsonSerializer;
import com.example.coursemanagementsystem.databaseControllers.PersonGsonSerializer;
import com.example.coursemanagementsystem.ds.Company;
import com.example.coursemanagementsystem.ds.Person;
import com.example.coursemanagementsystem.hibernateControllers.UserHibernateController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Properties;

@Controller
public class UserWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystem");
    UserHibernateController userHibernateController = new UserHibernateController(entityManagerFactory);

    @RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String checkUserByLogin(@RequestBody  String loginData)
    {
        Gson parser = new Gson();
        GsonBuilder gson = new GsonBuilder();
        Properties properties = parser.fromJson(loginData, Properties.class);
        Person person = null;
        Company company = null;
        if (properties.getProperty("type").equals("P")) {
            person = (Person) userHibernateController.getUserByLoginData(properties.getProperty("login"), properties.getProperty("password"));
            gson.registerTypeAdapter(Person.class, new PersonGsonSerializer()).registerTypeAdapter(LocalDate.class, new LocalDateGsonSerializer());
        } else if (properties.getProperty("type").equals("C")) {
            company = (Company) userHibernateController.getUserByLoginData(properties.getProperty("login"), properties.getProperty("password"));
            gson.registerTypeAdapter(Company.class, new CompanyGsonSerializer()).registerTypeAdapter(LocalDate.class, new LocalDateGsonSerializer());
        }



        if (person == null && company == null) {
            return "Wrong credentials or no such user";
        }


        Gson builder = gson.create();
        return person != null ? builder.toJson(person) : builder.toJson(company);
    }

    @RequestMapping(value = "/user/allUsers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllUsers()
    {
        Gson gson = new Gson();
        return gson.toJson(userHibernateController.getAllUsers().toString());


    }

    @RequestMapping(value = "/user/addPerson", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewPerson(@RequestBody String request)
    {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Person person = new Person(properties.getProperty("login"), properties.getProperty("password"), properties.getProperty("name"),properties.getProperty("surname"),properties.getProperty("cardNumber"),properties.getProperty("studentNumber"),properties.getProperty("email"));
        userHibernateController.createUser(person);
        return "Success!";
    }

    @RequestMapping(value = "/user/addCompany", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewCompany(@RequestBody String request)
    {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Company company = new Company(properties.getProperty("login"), properties.getProperty("password"), properties.getProperty("name"),properties.getProperty("representative"),properties.getProperty("address"),properties.getProperty("phoneNum"));
        userHibernateController.createUser(company);
        return "Success";
    }

    @RequestMapping(value = "/user/updatePerson/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updatePerson(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Person person = userHibernateController.getPersonById(id);

        person.setLogin(properties.getProperty("login"));
        person.setPassword(properties.getProperty("password"));
        person.setName(properties.getProperty("name"));
        person.setSurname(properties.getProperty("surname"));
        person.setCardNumber(properties.getProperty("cardNumber"));
        person.setStudentNumber(properties.getProperty("studentNumber"));
        person.setEmail(properties.getProperty("email"));

        userHibernateController.editUser(person);
        return "Success";
    }

    @RequestMapping(value = "/user/updateCompany/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCompany(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Company company = userHibernateController.getCompanyById(id);

        company.setLogin(properties.getProperty("login"));
        company.setPassword(properties.getProperty("password"));
        company.setName(properties.getProperty("name"));
        company.setRepresentative(properties.getProperty("representative"));
        company.setAddress(properties.getProperty("address"));
        company.setPhoneNum(properties.getProperty("phoneNum"));

        userHibernateController.editUser(company);
        return "Success";
    }

    @RequestMapping(value = "/user/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteUser(@PathVariable(name = "id") int id) {
        userHibernateController.removeUser(id);
        if(userHibernateController.getUserById(id)!=null)
        {
            return "User was deleted";
        }
        else
        {
            return "User was not deleted";
        }
    }
}
