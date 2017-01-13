/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.Professor;
import org.hibernate.Query;

/**
 *
 * @author Gezahegn
 */
public class ProfessorDAOImp implements ProfessorDAO {

    private DBHandler dbHandler;

    public DBHandler getdbHandler() {
        return dbHandler;
    }

    public void setdbHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public void create(Professor u) {
        dbHandler.create(u);
    }

    @Override
    public Professor retrieve(String username) {
        String hql = "from Professor where username =:username";

        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("username", username);
        Professor professor = (Professor) query.uniqueResult();
        return professor;
    }

    @Override
    public Professor retrieveForLogin(String username, String password) {
        String hql = "from Professor where username=:username AND password=:password";

        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Professor professor = (Professor) query.uniqueResult();
        return professor;
    }

    @Override
    public boolean exists(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long numberOfUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Professor u) {
        dbHandler.update(u);
    }

    @Override
    public void delete(Professor u) {
        dbHandler.delete(u);
    }

    @Override
    public boolean register(Professor u) {
        
        //if already exist email or username
        if (retrieve(u.getUsername()) != null || retrieveByEmail(u.getEmail()) != null) {
            return false;
        }
        
        //create
        create(u);
        
        //check created
        if(retrieve(u.getUsername()) == null){
            return false;
        }
        
        return true;
    }

    @Override
    public Professor retrieveByEmail(String email) {
        String hql = "from Professor where email =:email";

        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("email", email);
        Professor professor = (Professor) query.uniqueResult();
        return professor;
    }

}
