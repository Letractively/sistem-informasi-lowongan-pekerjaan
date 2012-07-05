/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ScheduleInterview;
import java.util.List;

/**
 *
 * @author WILLIAM
 */
public interface IScheduleInterviewDAO extends IGeneralDAO {

    public List<ScheduleInterview> gets() throws Exception;
}
