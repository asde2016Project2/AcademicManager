/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.Professor;


/**
 * 
 * @author Francesco Bruno
 */
public interface ProfessorDAO {

    void create(Professor u);

    Professor retrieve(String username);
    
    Professor retrieveForLogin(String username,String password);

    boolean exists(String username);

    Long numberOfUsers();

    void update(Professor u);

    void delete(Professor u);
}
