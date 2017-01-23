package it.unical.asde.uam.persistence;


import java.util.List;

import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.ExamSession;

public interface ExamSessionDAO {

	void create(ExamSession examSession);

	void saveUpdates(ExamSession examSession);
	   
	void delete(ExamSession examSession);
	 
	List<ExamSession> listExamRegAppeals();

	ExamSession getExamSessionById(int id);

	Integer getTotalNumberOfExamSession();

	List<ExamSession> listExamSessions(Integer pageNumber, Integer examPerPage);

	List<DegreeCourse> getDegreeCourseToExamSession(Integer examSessionId);

	List<ExamSession> getAllExamSession();

	boolean checkExamSession(String startingDate, String endingDate, String academicYear);

}

