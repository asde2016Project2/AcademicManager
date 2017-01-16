/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.Administrator;

/**
 *
 * @author Gezahegn
 */
public interface AdministratorDAO {
    
     public void create(Administrator administrator);
     public Administrator retrieveAdminForLogin(String username, String password);
    
}
