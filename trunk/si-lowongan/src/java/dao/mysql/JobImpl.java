/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.IJobDAO;
import entity.Job;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author WILLIAM
 */
public class JobImpl extends GeneralDAOImpl implements IJobDAO {

    public JobImpl(EntityManager em) {
        super(em);
    }

    public List<Job> gets() throws Exception {
        List<Job> list = new ArrayList<Job>();
        try {
            this.em.getTransaction().begin();
            list = this.em.createQuery("SELECT j FROM Job j").getResultList();
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("si-lowonganPU");
        EntityManager em = emf.createEntityManager();
        try {
            List<Job> listJob = new JobImpl(em).gets();
            System.out.println(listJob.size() + " : size");
        } catch (Exception ex) {
            Logger.getLogger(JobImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
