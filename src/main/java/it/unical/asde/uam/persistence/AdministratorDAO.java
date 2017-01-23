/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import java.util.List;

import it.unical.asde.uam.model.Administrator;

/**
 *
 * @author Gezahegn
 */
public interface AdministratorDAO {
    
    public void create(Administrator administrator);
    public Administrator retrieveAdminForLogin(String username, String password);
    public List<Administrator> getAllAdminsToAcceptRefuse();
    public Administrator retrieve(String username);
    public void update(Administrator administrator);
	public void delete(Administrator administrator);
}
