/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Manager;
import java.util.List;

/**
 *
 * @author WILLIAM
 */
public interface IManagerDAO extends IGeneralDAO {

    public List<Manager> gets() throws Exception;
    public Manager get(int id) throws Exception;
}
