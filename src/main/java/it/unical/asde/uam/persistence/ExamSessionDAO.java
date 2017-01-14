package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.ExamSession;

public interface ExamSessionDAO {
	
	 void create(ExamSession examSession);

	 void saveUpdates(ExamSession examSession);
	   
	 void deleteAttempt(ExamSession examSession);

}