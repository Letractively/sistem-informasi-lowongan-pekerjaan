/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ActionHistory;
import java.util.List;

/**
 *
 * @author WILLIAM
 */
public interface IActionHistoryDAO extends IGeneralDAO {

    public List<ActionHistory> gets() throws Exception;
}
