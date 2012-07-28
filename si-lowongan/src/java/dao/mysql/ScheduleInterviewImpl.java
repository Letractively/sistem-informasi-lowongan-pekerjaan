/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.IScheduleInterviewDAO;
import entity.ScheduleInterview;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author WILLIAM
 */
public class ScheduleInterviewImpl extends GeneralDAOImpl implements IScheduleInterviewDAO{

    public ScheduleInterviewImpl(EntityManager em){
        super(em);
    }
    
    public List<ScheduleInterview> gets() throws Exception {
        List<ScheduleInterview> list = new ArrayList<ScheduleInterview>();
        try {
            this.em.getTransaction().begin();
            list = this.em.createQuery("SELECT s FROM ScheduleInterview s").getResultList();
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }
    
}
