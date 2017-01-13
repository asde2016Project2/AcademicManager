package it.unical.asde.uam.persistence;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.UserAttemptRegistration;
import it.unical.asde.uam.model.Student;

public class UserAttemptRegistrationDAOImp implements  UserAttemptRegistrationDAO{
	

	private static final Logger logger = LoggerFactory.getLogger(UserAttemptRegistrationDAOImp.class);
	private DBHandler dbHandler;

	public DBHandler getDbHandler() {
		return dbHandler;
	}

	public void setDbHandler(DBHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	public UserAttemptRegistrationDAOImp() {
	}

	@Override
	public UserAttemptRegistration getUserAttemptRegById(int userAtRegId) {
		String hql = "from UserAttemptRegistration where userAtRegId=:userAtRegId";
		Query query = dbHandler.getSession().createQuery(hql);
		query.setParameter("userAtRegId", userAtRegId);

		dbHandler.begin();
		UserAttemptRegistration userAttemptRegistration = (UserAttemptRegistration) query.uniqueResult();
		dbHandler.commit();
		return userAttemptRegistration;
	}

	@Override
	public void updateUserAttemptRegistration(UserAttemptRegistration userAttemptRegistration) {

		String hql = "UPDATE UserAttemptRegistration set status = :status Where userAtRegId = :userAtRegId";
		// Create a Query instance for the given HQL query string.
		Query query = dbHandler.getSession().createQuery(hql);
		query.setParameter("status", userAttemptRegistration.getStatus());
		query.setParameter("userAtRegId", userAttemptRegistration.getUserAtRegId());
		// Persists to HSQLDB
		int result = query.executeUpdate();
		logger.info("UserAttemptRegistration updated successfully, UserAttemptRegistration Details=" + userAttemptRegistration);
	}

	@Override
	public Student getStudentToUserAttemptReg(Integer userAtRegId) {

		Query query = dbHandler.getSession()
				.createQuery("SELECT professor FROM UserAttemptRegistration s WHERE s.userAtRegId = :userAtRegId");
		query.setParameter("userAtRegId", userAtRegId);

		Student student = (Student) query.uniqueResult();
		dbHandler.commit();

		return student;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserAttemptRegistration> getAttemptToUserAttemptReg(Integer attemptId) {

		Query query = dbHandler.getSession()
				.createQuery("FROM UserAttemptRegistration s WHERE s.attempt.attemptId = :attemptId");
		query.setParameter("attemptId", attemptId);

		
		List<UserAttemptRegistration> attempts = query.list();

		return attempts;
	}

	
	

}
