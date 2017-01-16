package it.unical.asde.uam.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DBHandler {

	private SessionFactory sessionFactory;
	private Transaction tx;
	private static Session session;

	private static enum Operation {
		CREATE, UPDATE, DELETE
	};

	public DBHandler() {
		sessionFactory = null;
	}

	private void performOperation(Object obj, Operation op) {

		try {
			begin();
			switch (op) {
			case CREATE:
				getSession().saveOrUpdate(obj);
				break;
			case UPDATE:
				getSession().update(obj);
				break;
			case DELETE:
				getSession().delete(obj);
				break;
			}
			commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void create(Object obj) {
		performOperation(obj, Operation.CREATE);
	}

	public void delete(Object obj) {
		performOperation(obj, Operation.DELETE);
	}

	public void update(Object obj) {
		performOperation(obj, Operation.UPDATE);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * opening sessions
	 *
	 * @return opening the sessions
	 */
	public Session getSession() {
		if ((session == null) || (!session.isOpen())) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	/**
	 * Closing the current sessions
	 */
	public void close() {
		getSession().close();
	}

	/**
	 * begin the transactions
	 */
	public void begin() {

		this.tx = getSession().beginTransaction();

	}

	/**
	 * commit to the database
	 */
	public void commit() {
		this.tx.commit();
	}
	
	public void flush() {
		session.flush();

	}

}
