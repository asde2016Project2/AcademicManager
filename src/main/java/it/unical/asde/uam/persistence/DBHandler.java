package it.unical.asde.uam.persistence;


import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Repository
public class DBHandler {
	
	public enum Operation {SAVE, UPDATE, DELETE};
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public DBHandler() {
		
	}
	
//	@PostConstruct
//	public void initDB() {
//		performOperation(new Credentials("ciccio", "pasticcio"), Operation.SAVE);		
//	}

	public void performOperation(Object object, Operation op) {
		
		Session session = sessionFactory.openSession();
		Transaction tx= null;
		try {
			tx = session.beginTransaction();
			switch (op) {
			case SAVE:
				session.save(object);
				break;
			case UPDATE:
				session.update(object);
				break;
			case DELETE:
				session.delete(object);
				break;
			default:
				break;
			}
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		session.close();
		
	}
	
	public Session openSession() {
            System.out.println("sessionfactory information"+ sessionFactory.openSession());
		return sessionFactory.openSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
		
	

}
