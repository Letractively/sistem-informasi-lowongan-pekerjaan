/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.List;

/**
 *
 * @author WILLIAM
 */
public interface IUserDAO extends IGeneralDAO {

    public List<User> gets() throws Exception;
    public User getById(String pId_user) throws Exception;


}
