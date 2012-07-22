/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.IUserDAO;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
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
public class UserDAOImpl extends GeneralDAOImpl implements IUserDAO {

    public UserDAOImpl(EntityManager em) {
        super(em);
    }

    public List<User> gets() throws Exception {
        List<User> list = new ArrayList<User>();
        try {
            this.em.getTransaction().begin();
            list = this.em.createQuery("SELECT u FROM User u").getResultList();
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

            List<User> listUser = new UserDAOImpl(em).gets();
            System.out.println("size : " + listUser.size());
        } catch (Exception ex) {
        }

        UserDAOImpl d = new UserDAOImpl(em);
        
        User user = new User();
        user.setIdUser("admin");
        user.setPassword("admin");
        user.setDateCreate(new Date());
        try {
            d.insert(user);
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
