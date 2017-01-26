package it.unical.asde.uam.persistence;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		dbHandler.begin();
		Long singleResult = (Long) query.uniqueResult();
		Integer numOfExams = singleResult.intValue();
		logger.info("nr of examSessions is {} ", numOfExams);
		dbHandler.commit();
		return numOfExams;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamSession> listExamSessions(Integer pageNumber, Integer examPerPage) {
		int start = examPerPage * (pageNumber - 1);
		Query query = dbHandler.getSession().createQuery("from ExamSession");
		query.setFirstResult(start);
		query.setMaxResults(examPerPage);

		dbHandler.begin();
		List<ExamSession> examSessions = query.list();

		for (ExamSession examSession : examSessions) {
			logger.info("ExamSession list:" + examSessions);
		}
		dbHandler.commit();
		return examSessions;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DegreeCourse> getDegreeCourseToExamSession(Integer examSessionId) {

		Query query = dbHandler.getSession()
				.createQuery("SELECT degreeCourse FROM ExamSession s WHERE s.examSessionId = :examSessionId");
		query.setParameter("examSessionId", examSessionId);

		dbHandler.begin();
		List<DegreeCourse> getDegreeCourseToExamSession = query.list();
		dbHandler.commit();
		
		return getDegreeCourseToExamSession;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ExamSession> getAllExamSession() {

		Query query = dbHandler.getSession().createQuery("from ExamSession");
		dbHandler.begin();
		List<ExamSession> allExamSessions =  query.list();
		dbHandler.commit();
		return allExamSessions;
	}

  
	@Override
	public boolean checkExamSession(Date startingDate, Date endingDate, String academicYear) {
		
		if(endingDate.before(startingDate))
			return false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String startingDateString = sdf.format(startingDate);            
        String endingDateString = sdf.format(endingDate);
		
        String[] academicYs = academicYear.split("/");
        String[] startD = startingDateString.split("-");
        String[] endD = endingDateString.split("-");
        
        int academicY1 = Integer.parseInt(academicYs[0]);
        int academicY2 = Integer.parseInt(academicYs[1]);
        
        int yearStart = Integer.parseInt(startD[2]);
        int yearEnd = Integer.parseInt(endD[2]);

        int monthStart = Integer.parseInt(startD[1]);
        int monthEnd = Integer.parseInt(endD[1]);
        
        if(yearStart != academicY1 && yearStart != academicY2)
        	return false;
        if(yearEnd != academicY1 && yearEnd != academicY2)
        	return false;
        if(monthStart< 9)
        	if(yearStart == academicY1)
        		return false;
        if(monthEnd< 9 & monthStart>=9)
           	if(yearEnd != academicY2)
           		return false;
		return true;
	}


}

