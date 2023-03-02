package com.example.coursemanagementsystem.hibernateControllers;

import com.example.coursemanagementsystem.ds.File;
import com.example.coursemanagementsystem.ds.Folder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class FileHibernateController {
    private EntityManagerFactory emf=null;

    public FileHibernateController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public void createFile(File file){
        EntityManager em= null;

        try{
            em=getEntityManager();
            em.getTransaction().begin();
            em.persist(file);
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

    public void editFile(File file){
        EntityManager em= null;
        try{
            em=getEntityManager();
            em.getTransaction().begin();
            em.merge(file);
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

    public void removeFile(int id){
        EntityManager em= null;

        try{
            em=getEntityManager();
            em.getTransaction().begin();

            File file = null;
            try {
                file = em.getReference(File.class, id);
                file.getId();
            }catch (Exception E)
            {
                System.out.println("File not found");
            }
            em.remove(file);
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

    public List<File> getAllFiles() {
        return getAllFiles(true, -1, -1);
    }

    public List<File> getAllFiles(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(File.class));
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

    public File getFileById(int id) {
        EntityManager em = null;
        File file = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            file = em.getReference(File.class, id);
            file.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such file by given Id");
        }
        return file;
    }
}
