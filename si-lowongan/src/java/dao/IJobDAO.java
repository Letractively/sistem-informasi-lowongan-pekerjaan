/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Job;
import java.util.List;

/**
 *
 * @author WILLIAM
 */
public interface IJobDAO extends IGeneralDAO {

    public List<Job> gets() throws Exception;
}
