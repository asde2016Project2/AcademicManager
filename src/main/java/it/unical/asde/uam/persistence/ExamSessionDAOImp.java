package it.unical.asde.uam.persistence;


import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.ExamSession;

public class ExamSessionDAOImp implements ExamSessionDAO {

	private static final Logger logger = LoggerFactory.getLogger(ExamSessionDAOImp.class);
	private DBHandler dbHandler;

	public DBHandler getDbHandler() {
		return dbHandler;
	}

	public void setDbHandler(DBHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	public ExamSessionDAOImp() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamSession> listExamRegAppeals() {

		String queryString = "from ExamSession as examSession where examSession.status = 'active'";
		Query query = dbHandler.getSession().createQuery(queryString);
		dbHandler.begin();
		List<ExamSession> examSessions = (List<ExamSession>) query.list();
		dbHandler.commit();
		return examSessions;
	}

	@Override
	public ExamSession getExamSessionById(int id) {
		String hql = "from ExamSession where sessionId =:id";
		Query query = dbHandler.getSession().createQuery(hql);
		query.setParameter("id", id);
		dbHandler.begin();
		ExamSession examSession = (ExamSession) query.uniqueResult();
		dbHandler.commit();
		return examSession;
	}

	/*
	 * retrieving the total number of Exams from the db
	 */

	@Override
	public Integer getTotalNumberOfExamSession() {
		String hql = "SELECT COUNT(*) FROM ExamSession";
		Query query = dbHandler.getSession().createQuery(hql);
		Long singleResult = (Long) query.uniqueResult();
		Integer numOfExams = singleResult.intValue();
		logger.info("nr of examSessions is {} ", numOfExams);
		return numOfExams;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamSession> listExamSessions(Integer pageNumber, Integer examPerPage) {
		int start = examPerPage * (pageNumber - 1);
		Query query = dbHandler.getSession().createQuery("from ExamSession");
		query.setFirstResult(start);
		query.setMaxResults(examPerPage);

		List<ExamSession> examSessions = query.list();

		for (ExamSession examSession : examSessions) {
			logger.info("ExamSession list:" + examSessions);
		}
		return examSessions;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DegreeCourse> getDegreeCourseToExamSession(Integer examSessionId) {

		Query query = dbHandler.getSession()
				.createQuery("SELECT degreeCourse FROM ExamSession s WHERE s.sessionId = :sessionId");
		query.setParameter("sessionId", examSessionId);

		List<DegreeCourse> getDegreeCourseToExamSession = query.list();

		return getDegreeCourseToExamSession;
	}


  @Override
	public void create(ExamSession examSession) {
		dbHandler.create(examSession);
	}

	@Override
	public void saveUpdates(ExamSession examSession) {
		
		dbHandler.update(examSession);
	}

	@Override
	public void deleteAttempt(ExamSession examSession) {
		
		dbHandler.delete(examSession);
	}


}

