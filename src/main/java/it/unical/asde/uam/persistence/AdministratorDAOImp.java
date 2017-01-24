/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.helper.Accepted;
import it.unical.asde.uam.model.Administrator;
import it.unical.asde.uam.model.Student;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gezahegn
 */
public class AdministratorDAOImp implements AdministratorDAO {

    private static final Logger logger = LoggerFactory.getLogger(StudentDAOImp.class);

    private DBHandler dbHandler;

    public DBHandler getDbHandler() {
        return dbHandler;
    }

    public void setDbHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public AdministratorDAOImp() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void create(Administrator administrator) {

        System.out.println("information==========");
        dbHandler.create(administrator);
    }

    @Override
    public Administrator retrieveAdminForLogin(String username, String password) {
        String hql = "from Administrator where username=:username AND password=:password";

        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        dbHandler.begin();
        Administrator administrator = (Administrator) query.uniqueResult();
        dbHandler.commit();
        return administrator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Administrator> getAllAdminsToAcceptRefuse() {
        String hql = "from Administrator where accepted=:value";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("value", Accepted.NOT_ACCEPTED);
        dbHandler.begin();
        List<Administrator> administrators = (List<Administrator>) query.list();
        dbHandler.commit();
        return administrators;
    }

    @Override
    public Administrator retrieve(String username) {
        String hql = "from Administrator where username=:username";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("username", username);
        dbHandler.begin();
        Administrator administrator = (Administrator) query.uniqueResult();
        dbHandler.commit();
        return administrator;
    }

    @Override
    public void update(Administrator administrator) {
        dbHandler.update(administrator);
    }

    @Override
    public void delete(Administrator administrator) {
        dbHandler.delete(administrator);
    }

    @Override
    public Administrator retrieveByEmail(String email) {
        String hql = "from Administrator where email =:email";

        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("email", email);
        dbHandler.begin();
        Administrator administrator = (Administrator) query.uniqueResult();
        dbHandler.commit();
        return administrator;
    }

    @Override
    public boolean register(Administrator u) {

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

}
