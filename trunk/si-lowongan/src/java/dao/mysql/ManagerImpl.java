/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.IManagerDAO;
import entity.Manager;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author WILLIAM
 */
public class ManagerImpl extends GeneralDAOImpl implements IManagerDAO {

    public ManagerImpl(EntityManager em) {
        super(em);
    }

    

    public List<Manager> gets() throws Exception {
        List<Manager> list = new ArrayList<Manager>();
        try {
            this.em.getTransaction().begin();
            list = this.em.createQuery("SELECT m FROM Manager m").getResultList();
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }

    public Manager get(int id) throws Exception {
        Manager list = new Manager();
        try {
            this.em.getTransaction().begin();
            list = em.find(Manager.class, id);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }
}
