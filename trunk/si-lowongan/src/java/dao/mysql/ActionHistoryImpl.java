/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.IActionHistoryDAO;
import entity.ActionHistory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author WILLIAM
 */
public class ActionHistoryImpl extends GeneralDAOImpl implements IActionHistoryDAO {

    public ActionHistoryImpl(EntityManager em) {
        super(em);
    }

    public List<ActionHistory> gets() throws Exception {
        List<ActionHistory> list = new ArrayList<ActionHistory>();
        try {
            this.em.getTransaction().begin();
            list = this.em.createQuery("SELECT ah FROM ah").getResultList();
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }
}
