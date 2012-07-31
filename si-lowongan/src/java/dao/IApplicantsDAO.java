/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Applicants;
import java.util.List;

/**
 *
 * @author WILLIAM
 */
public interface IApplicantsDAO extends IGeneralDAO {

    public Applicants get(int id) throws Exception;

    public List<Applicants> gets() throws Exception;
}
