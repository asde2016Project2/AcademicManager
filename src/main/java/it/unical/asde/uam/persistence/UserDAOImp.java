/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.User;
import org.hibernate.Query;

/**
 *
 * @author Gezahegn
 */
public class UserDAOImp implements UserDAO {

    private DBHandler dBHandler;

    public DBHandler getdBHandler() {
        return dBHandler;
    }

    public void setdBHandler(DBHandler dBHandler) {
        this.dBHandler = dBHandler;
    }

    @Override
    public void create(User u) {
        dBHandler.create(u);
    }

    @Override
    public User retrieve(String username) {
        String hql = "from User where user_name =:username";

        Query query = dBHandler.getSession().createQuery(hql);
        query.setParameter("username", username);
        dBHandler.begin();
        User user = (User) query.uniqueResult();
        dBHandler.commit();
        return user;
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
    public void update(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
