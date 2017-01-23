package it.unical.asde.uam.persistence;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	@Override
	public void create(ExamSession examSession) {
		dbHandler.create(examSession);
	}

	@Override
	public void saveUpdates(ExamSession examSession) {
		
		dbHandler.update(examSession);
	}

	@Override
	public void delete(ExamSession examSession) {
		
		dbHandler.delete(examSession);
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
	public ExamSession getExamSessionById(int examSessionId) {
		String hql = "from ExamSession where examSessionId =:examSessionId";
		Query query = dbHandler.getSession().createQuery(hql);
		query.setParameter("examSessionId", examSessionId);
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
				.createQuery("SELECT degreeCourse FROM ExamSession s WHERE s.examSessionId = :examSessionId");
		query.setParameter("examSessionId", examSessionId);

		List<DegreeCourse> getDegreeCourseToExamSession = query.list();

		return getDegreeCourseToExamSession;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ExamSession> getAllExamSession() {

		Query query = dbHandler.getSession().createQuery("from ExamSession");
		return query.list();
	}

  
	@Override
	public boolean checkExamSession(String startingDate, String endingDate, String academicYear) {
		
		String[] strsStart = startingDate.split("-");
		String[] strsEnd = endingDate.split("-");
		String[] years = academicYear.split("/");
		
		int dayStart = Integer.parseInt(strsStart[2]);
		int monthStart = Integer.parseInt(strsStart[1]);
		int yearStart = Integer.parseInt(strsStart[0]);
		
		int dayEnd = Integer.parseInt(strsEnd[2]);
		int monthEnd = Integer.parseInt(strsEnd[1]);
		int yearEnd = Integer.parseInt(strsEnd[0]);
		
		if(yearStart == yearEnd) {
			if(monthStart == monthEnd) {
				if(dayStart>=dayEnd)
					return false;
			}
			else if(monthStart > monthEnd)
				return false;
		}
		
		
		System.out.println("strs[0]: "+strsStart[0]+"....years[1]: "+years[1]);
		if(!(strsStart[0].equals(years[1]))){
			System.out.println("strs[0]: "+strsStart[0]+"....years[1]: "+years[1]);
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date endDate = sdf.parse(endingDate);
			Date startDate = sdf.parse(startingDate);
		} catch (ParseException e) {
			System.out.println("ERRORE inserimento data");
			return false;
		}
		return true;
	}


}

