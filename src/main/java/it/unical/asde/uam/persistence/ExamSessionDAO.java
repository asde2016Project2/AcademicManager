package it.unical.asde.uam.persistence;


import java.util.List;

import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.ExamSession;

public interface ExamSessionDAO {

	List<ExamSession> listExamRegAppeals();

	ExamSession getExamSessionById(int id);

	Integer getTotalNumberOfExamSession();

	List<ExamSession> listExamSessions(Integer pageNumber, Integer examPerPage);

	List<DegreeCourse> getDegreeCourseToExamSession(Integer examSessionId);
	
	
	 void create(ExamSession examSession);

	 void saveUpdates(ExamSession examSession);
	   
	 void deleteAttempt(ExamSession examSession);

	List<ExamSession> getAllExamSession();

}

