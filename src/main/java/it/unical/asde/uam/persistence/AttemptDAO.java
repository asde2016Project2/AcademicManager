package it.unical.asde.uam.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;

public interface AttemptDAO {

	void create(Attempt attempt);

	void update(Attempt attempt);

	void delete(Attempt attempt);
	
	List<Professor> getProfessorToAttempt(Integer attemptId);

	ArrayList<Attempt> getExamSessionToAttempt(Integer sessionId,Exam exam);

	Attempt getAttemptById(int attemptId);

	void updateAttempt(Attempt attempt);

	void updateAttemptExamReserv(Attempt attempt);

	int getAttemptByProfessorByExam(Professor p, Exam e);

	List<Attempt> getAllAttempts();
        
	ArrayList<Attempt> listActiveExamforAttempt(Exam exam);

//	ArrayList<Attempt> getNewExamSessionAttempt(int attemptId);

	ArrayList<Attempt> getAttemptByProfessor(Professor p);

	boolean checkAttempt(Date startingDate, Date endingDate, Date examDate, Date examSessionStarting,
			Date examSessionEnding);

	ArrayList<Attempt> getNewExamSessionAttempt(Exam exam);

	ArrayList<Exam> getExamToAttempt(Integer examSessionId, Exam exam);

	ArrayList<Attempt> getAttemptByAttempt(int attemptId);
	

}
