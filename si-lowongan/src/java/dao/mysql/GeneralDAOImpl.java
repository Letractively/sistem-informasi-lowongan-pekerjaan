/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.IGeneralDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author WILLIAM
 */
public class GeneralDAOImpl implements IGeneralDAO {

    protected EntityManager em;
    
    public GeneralDAOImpl(EntityManager em){
        this.em = em;
    }

    public void insert(Object obj) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void delete(Object obj) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void update(Object obj) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
