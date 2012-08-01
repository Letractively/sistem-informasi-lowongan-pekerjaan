/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.IJobVacancyDAO;
import entity.JobVacancy;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author WILLIAM
 */
public class JobVacancyImpl extends GeneralDAOImpl implements IJobVacancyDAO {

    public JobVacancyImpl(EntityManager em) {
        super(em);
    }

    public List<JobVacancy> gets() throws Exception {
        List<JobVacancy> list = new ArrayList<JobVacancy>();
        try {
            this.em.getTransaction().begin();
            list = this.em.createQuery("SELECT ja FROM JobVacancy ja").getResultList();
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }

    public JobVacancy get(String id) throws Exception {
        JobVacancy jv = new JobVacancy();
        this.em.getTransaction().begin();
        jv = (JobVacancy) this.em.createQuery("SELECT j FROM JobVacancy j WHERE j.idJobVacancy = '" + id + "'").getSingleResult();
        this.em.getTransaction().commit();
        return jv;
    }
}
