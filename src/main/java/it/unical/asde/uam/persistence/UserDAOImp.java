package it.unical.asde.uam.persistence;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import it.unical.asde.uam.dao.DAOImp;


/**
*
* @author Nello
*/



import it.unical.asde.uam.model.User;


public class UserDAOImp  extends DAOImp<User> {
	
	    
    public void create(User user) {

        begin();
        getSession().save(user);
        commit();
       
       // System.out.println("user creato: "+ user.toString());

    }

    // USING SQL
    public User getAdminById(int id) {

    	User usr = (User) getSession().createSQLQuery("SELECT * FROM administrator WHERE user_id=" + id).addEntity(User.class).uniqueResult();
      
        return usr;
    }
    
    // USING HIBERNATE QUERY LANGUAGE (more expressive. like object oriented programming)
    public User getAdminByIdHQL(int id) {
    	
    	String hql = "FROM User U WHERE U.userId = 1";
    	Query query = getSession().createQuery(hql);
    	List results = query.list();
      
        return (User) results.get(0);
    }
    
    

    // USING HIBERNATE QUERY LANGUAGE
    public List<User> getAllProfessors() {
    	
    	String hql = "FROM Professor";
    	Query query = getSession().createQuery(hql);
    	List results = query.list();
      
        return results;
    }


        
    public void saveUpdates(User usr) {
        begin();
        getSession().update(usr);
        commit();

    }

    

	 

}
