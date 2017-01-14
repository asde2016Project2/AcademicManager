package it.unical.asde.uam.persistence;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Professor;

public class AttemptDAOImp implements AttemptDAO {

	private static final Logger logger = LoggerFactory.getLogger(AttemptDAOImp.class);
	private DBHandler dbHandler;

	public DBHandler getDbHandler() {
		return dbHandler;
	}

	public void setDbHandler(DBHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	public AttemptDAOImp() {
	}

	@Override
	public Attempt getAttemptById(int attemptId) {
		String hql = "from Attempt where attemptId=:attemptId";
		Query query = dbHandler.getSession().createQuery(hql);
		query.setParameter("attemptId", attemptId);

		dbHandler.begin();
		Attempt attempt = (Attempt) query.uniqueResult();
		dbHandler.commit();
		return attempt;
	}

	@Override
	public void updateAttemptExamReserv(Attempt attempt) {

		String hql = "UPDATE Attempt set status = :status Where attemptId = :attemptId";
		// Create a Query instance for the given HQL query string.
		Query query = dbHandler.getSession().createQuery(hql);
		query.setParameter("status", attempt.getStatus());
		query.setParameter("attemptId", attempt.getAttemptId());
		// Persists to HSQLDB
		int result = query.executeUpdate();
		logger.info("Attempt updated successfully, Attempt Details=" + attempt);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Professor> getProfessorToAttempt(Integer attemptId) {

		Query query = dbHandler.getSession()
				.createQuery("SELECT professor FROM Attempt s WHERE s.attemptId = :attemptId");
		query.setParameter("attemptId", attemptId);

		List<Professor> getProfessorToAttempt = query.list();

		return getProfessorToAttempt;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Attempt> getExamSessionToAttempt(Integer sessionId) {

		Query query = dbHandler.getSession()
				.createQuery("FROM Attempt s WHERE s.examSession.sessionId = :sessionId");
		query.setParameter("sessionId", sessionId);

		List<Attempt> getExamSessionToAttempt = query.list();

		return getExamSessionToAttempt;
	}

	@Override
	public void updateAttempt(Attempt attempt) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
