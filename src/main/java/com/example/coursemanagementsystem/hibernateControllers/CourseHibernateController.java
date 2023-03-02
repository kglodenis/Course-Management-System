package com.example.coursemanagementsystem.hibernateControllers;

import com.example.coursemanagementsystem.ds.Course;
import com.example.coursemanagementsystem.ds.Folder;
import com.example.coursemanagementsystem.ds.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseHibernateController {
    private EntityManagerFactory emf=null;

    public CourseHibernateController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public void createCourse(Course course){
        EntityManager em= null;

        try{
            em=getEntityManager();
            em.getTransaction().begin();
            em.persist(course);
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

    public void editCourse(Course course){
        EntityManager em= null;
        try{
            em=getEntityManager();
            em.getTransaction().begin();
            em.merge(course);
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
    public void removeCourse(int id) {
        FolderHibernateController folderHibernateController = new FolderHibernateController(emf);
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Course course = null;
            try {
                course = em.getReference(Course.class, id);

                for (Folder t : course.getFolders()) {
                    folderHibernateController.remove(t.getId());
                }

                course.getFolders().clear();
                em.merge(course);

                for (User u : course.getModerators()) {
                    u.getMyModeratedCourses().remove(course);
                    em.merge(u);
                }

            } catch (Exception e) {
                System.out.println("No such user by given Id");
            }


            em.remove(course);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
/*
    public void removeCourse(int id){
        EntityManager em= null;

        try{
            em=getEntityManager();
            em.getTransaction().begin();

            Course course = null;
            try {
                course = em.getReference(Course.class, id);
                course.getId();
            }catch (Exception E)
            {
                System.out.println("Course not found");
            }
            em.remove(course);
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
*/

    public List<Course> getAllCourses() {
        return getAllCourses(true, -1, -1);
    }

    public List<Course> getAllCourses(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(Course.class));
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

    public Course getCourseById(int id) {
       // EntityManager em = null;
        EntityManager em;
        Course course = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            course = em.find(Course.class, id);
            //course = em.getReference(Course.class, id);
            //course.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such course by given Id");
        }
        return course;
    }
    public List<Course> getProjectByUserId(int id) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);

        Root<Course> root = query.from(Course.class);

        CriteriaBuilder.In<Integer> inClause = cb.in(root.get("id"));
        User user = em.getReference(User.class, id);
        for (Course p : user.getMyModeratedCourses()) {
            inClause.value(p.getId());
        }
        query.select(root).where(inClause);
        Query q;
        try {
            q = em.createQuery(query);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    public Course getCourseByTitle(String title) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root).where(cb.like(root.get("title"), title));
        Query q = em.createQuery(query);
        return (Course) q.getSingleResult();
    }
}
