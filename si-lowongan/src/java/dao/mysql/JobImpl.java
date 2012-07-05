/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.IJobDAO;
import entity.Job;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

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
            list = this.em.createQuery("SELECT j FROM Job").getResultList();
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }
}
