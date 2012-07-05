/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.JobVacancy;
import java.util.List;

/**
 *
 * @author WILLIAM
 */
public interface IJobVacancyDAO extends IGeneralDAO {

    public List<JobVacancy> gets() throws Exception;
}
