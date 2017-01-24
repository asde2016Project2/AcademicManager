package it.unical.asde.uam.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;

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
    public void create(Attempt attempt) {
        dbHandler.create(attempt);
    }

    @Override
    public void update(Attempt attempt) {
        dbHandler.update(attempt);
    }

    @Override
    public void delete(Attempt attempt) {
        dbHandler.delete(attempt);
    }

    @Override
    public Attempt getAttemptById(int attemptId) {
        String hql = "SELECT attempt FROM Attempt AS attempt"
                + " join fetch attempt.exam "
                + " join fetch attempt.examSession"
                + " where attempt.attemptId=:attemptId";
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
        dbHandler.begin();
        // Persists to HSQLDB
        int result = query.executeUpdate();
        logger.info("Attempt updated successfully, Attempt Details=" + attempt);
        dbHandler.commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Professor> getProfessorToAttempt(Integer attemptId) {

        Query query = dbHandler.getSession()
                .createQuery("SELECT professor FROM Attempt s WHERE s.attemptId = :attemptId");
        query.setParameter("attemptId", attemptId);
        
        dbHandler.begin();        
        List<Professor> getProfessorToAttempt = query.list();
        dbHandler.commit();
        
        return getProfessorToAttempt;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Attempt> getExamSessionToAttempt(Integer examSessionId) {

        Query query = dbHandler.getSession()
                .createQuery("SELECT attempt FROM Attempt AS attempt "
                        + " join fetch attempt.professor "
                        + " join fetch attempt.exam WHERE attempt.examSession.examSessionId = :examSessionId");
        query.setParameter("examSessionId", examSessionId);
        try {
            dbHandler.begin();
            ArrayList<Attempt> getExamSessionToAttempt = new ArrayList(query.list());
            dbHandler.commit();
            return getExamSessionToAttempt;
        }
        catch (Exception ex) {
            logger.debug("List of exam attempt" + ex);
            return null;
        }
    }

    @Override
    public void updateAttempt(Attempt attempt) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getAttemptByProfessorByExam(Professor p, Exam e) {

        int profId = p.getUserId();
        int examId = e.getId();
        System.out.println("query prof id: " + p.getUserId());
        System.out.println("query exam id: " + e.getId());
        String hql = "from Attempt where exam_id=:examID and user_id=:profID";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("profID", profId);
        query.setParameter("examID", examId);
        
        dbHandler.begin();
        Attempt a = (Attempt) query.uniqueResult();
        System.out.println("nella query valore di attempt id: " + a.getAttemptId());
        dbHandler.commit();
        return a.getAttemptId();
    }

    @Override
    public List<Attempt> getAllAttempts() {

        String hql = "from Attempt";
        Query query = dbHandler.getSession().createQuery(hql);
        dbHandler.begin();
        List<Attempt> attempts = query.list();
        dbHandler.commit();
        return attempts;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Attempt> listActiveExamforAttempt() {
        try {
            dbHandler.begin();
            Query query = dbHandler.getSession().createQuery("SELECT attempt FROM Attempt AS attempt"
                    + " join fetch attempt.examSession"
                    + " join fetch attempt.professor"
                    + " join fetch attempt.exam ");

            ArrayList<Attempt> attempts = new ArrayList(query.list());
            dbHandler.commit();
            return attempts;
        }
        catch (Exception ex) {
            logger.debug("List of exam attempt" + ex);
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Attempt> getNewExamSessionAttempt(int attemptId) {
        try {

            Query query = dbHandler.getSession().createQuery("SELECT attempt FROM Attempt AS attempt"
                    + " join fetch attempt.examSession"
                    + " WHERE attempt.status ='active' AND attempt.attemptId=:attemptId");
            query.setParameter("attemptId", attemptId);
            dbHandler.begin();
            ArrayList<Attempt> attempts = new ArrayList(query.list());
            dbHandler.commit();
            return attempts;
        }
        catch (Exception ex) {
            logger.debug("List of exam attempt" + ex);
            return null;
        }
    }
    
    @Override
	public ArrayList<Attempt> getAttemptByProfessor(Professor p) {
		
		int profId = p.getUserId();
		System.out.println("query prof id: "+p.getUserId());
		String hql = "from Attempt where user_id=:profID";
		Query query = dbHandler.getSession().createQuery(hql);
		query.setParameter("profID", profId);
		dbHandler.begin();
		ArrayList<Attempt> a = (ArrayList<Attempt>) query.list();
		dbHandler.commit();
		return a;
	}

    @Override
   	public boolean checkAttempt(Date startingDate, Date endingDate, Date examDate,
				Date examSessionStarting, Date examSessionEnding) {

    	if(startingDate.before(endingDate) && examDate.after(endingDate) && examDate.after(startingDate)
    			&& (startingDate.after(examSessionStarting) || startingDate.equals(examSessionStarting))
    			&& startingDate.before(examSessionEnding) && endingDate.after(examSessionStarting)
    			&& (endingDate.before(examSessionEnding) || endingDate.equals(examSessionEnding)))
    		return true;
    	else return false;
	}

}
