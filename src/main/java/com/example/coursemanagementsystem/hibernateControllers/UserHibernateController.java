package com.example.coursemanagementsystem.hibernateControllers;

import com.example.coursemanagementsystem.ds.Company;
import com.example.coursemanagementsystem.ds.Person;
import com.example.coursemanagementsystem.ds.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserHibernateController {
    private EntityManagerFactory emf=null;

    public UserHibernateController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public void createUser(User user){
        EntityManager em= null;

        try{
            em=getEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(em != null)
            {
                em.close();
            }
        }
    }

    public void editUser(User user){
        EntityManager em= null;
        try{
            em=getEntityManager();
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(em!=null)
            {
                em.close();
            }
        }
    }

    public void removeUser(int id){
        EntityManager em= null;

        try{
            em=getEntityManager();
            em.getTransaction().begin();

            User user = null;
            try {
                user = em.getReference(User.class, id);
                user.getId();
            }catch (Exception E)
            {
                System.out.println("User not found");
            }
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(em != null)
            {
                em.close();
            }
        }
    }
    public List<User> getAllUsers() {
        return getAllUsers(true, -1, -1);
    }
    public List<User> getAllUsers(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(User.class));
            Query q = em.createQuery(query);

            if (!all) {
                q.setMaxResults(resMax);
                q.setFirstResult(resFirst);
            }

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public User getUserById(int id) {
        EntityManager em = null;
        User user = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            user = em.getReference(User.class, id);
            user.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return user;
    }
    public Company getCompanyById(int id) {
        EntityManager em = null;
        Company company = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            company = em.getReference(Company.class, id);
            company.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return company;
    }
    public Person getPersonById(int id) {
        EntityManager em = null;
        Person person = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            person = em.getReference(Person.class, id);
            person.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return person;
    }
    public User getUserByLogin(String login ) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(cb.like(root.get("login"), login));
        Query q = em.createQuery(query);
        return (User) q.getSingleResult();
    }
    public User getUserByLoginData(String login, String password) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Predicate predicate = cb.and(cb.like(root.get("password"), password), cb.like(root.get("login"), login));
        query.select(root).where(predicate);

        Query q = em.createQuery(query);
        try {
            return (User) q.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
    public User getUserIdByLoginData(String login, String password) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Predicate predicate = cb.and(cb.like(root.get("password"), password), cb.like(root.get("login"), login));
        query.select(root).where(predicate);

        Query q = em.createQuery(query);
        try {
            return (User) q.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }



}
