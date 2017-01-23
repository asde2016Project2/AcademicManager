/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import java.util.ArrayList;

import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;


/**
 * 
 * @author Francesco Bruno
 */
public interface ProfessorDAO {

    void create(Professor u);

    Professor retrieve(String username);
    
    Professor retrieveByEmail(String email);
    
    Professor retrieveForLogin(String username,String password);

    boolean exists(String username);

    Long numberOfUsers();

    void update(Professor u);

    void delete(Professor u);
    
    boolean register(Professor u);

    ArrayList<ExamSession> listAllSession();
    
    ArrayList<Professor> getAllProfessor();
}