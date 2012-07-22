/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author WILLIAM
 */
public class Manager {
    
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("si-lowonganPU");
    private EntityManager em = emf.createEntityManager();
    
    public EntityManager getEntityManager(){
        return em;
    }
    
}
