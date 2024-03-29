/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.IApplicantsDAO;
import entity.Applicants;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author WILLIAM
 */
public class ApplicantsImpl extends GeneralDAOImpl implements IApplicantsDAO {

    public ApplicantsImpl(EntityManager em) {
        super(em);
    }

    public Applicants get(int id) throws Exception {
        Applicants p = null;
        try {
            em.getTransaction().begin();
            p = em.find(Applicants.class, id);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
        return p;
    }

    public List<Applicants> gets() throws Exception {
        List<Applicants> list = new ArrayList<Applicants>();
        try {
            this.em.getTransaction().begin();
            list = this.em.createQuery("SELECT a FROM Applicants a").getResultList();
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }
}
