/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.helper.Accepted;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        dbHandler.begin();
        Professor professor = (Professor) query.uniqueResult();
        dbHandler.commit();
        return professor;
    }

    @Override
    public Professor retrieveForLogin(String username, String password) {
        String hql = "from Professor where username=:username AND password=:password";

        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        dbHandler.begin();
        Professor professor = (Professor) query.uniqueResult();
        dbHandler.commit();
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
        if (retrieve(u.getUsername()) == null) {
            return false;
        }

        return true;
    }

    @Override
    public Professor retrieveByEmail(String email) {
        String hql = "from Professor where email =:email";

        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("email", email);
        dbHandler.begin();
        Professor professor = (Professor) query.uniqueResult();
        dbHandler.commit();
        return professor;
    }

    @Override
    public ArrayList<ExamSession> listAllSession() {

        String hql = "from ExamSession";
        Query query = dbHandler.getSession().createQuery(hql);
        dbHandler.begin();
        ArrayList<ExamSession> examSessions = (ArrayList<ExamSession>) query.list();
        dbHandler.commit();
        return examSessions;
    }

    @Override
    public ArrayList<Professor> getAllProfessor() {
        String hql = "from Professor";
        Query query = dbHandler.getSession().createQuery(hql);
        dbHandler.begin();
        ArrayList<Professor> professors = (ArrayList<Professor>) query.list();
        dbHandler.commit();
        return professors;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Professor> geAllProfessorsToAcceptRefuse() {
        String hql = "from Professor where accepted =:value";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("value", Accepted.NOT_ACCEPTED);
        dbHandler.begin();        
        List<Professor> professors = (List<Professor>) query.list();
        dbHandler.commit();
        return professors;
    }

}
