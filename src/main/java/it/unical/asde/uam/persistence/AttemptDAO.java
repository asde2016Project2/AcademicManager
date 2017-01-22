package it.unical.asde.uam.persistence;

import java.util.ArrayList;
import java.util.List;

import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Professor;

public interface AttemptDAO {

	void create(Attempt attempt);

	void update(Attempt attempt);

	void delete(Attempt attempt);
	
	List<Professor> getProfessorToAttempt(Integer attemptId);

	ArrayList<Attempt> getExamSessionToAttempt(Integer sessionId);

	Attempt getAttemptById(int attemptId);

	void updateAttempt(Attempt attempt);

	void updateAttemptExamReserv(Attempt attempt);

	void flush();

	ArrayList<Attempt> listActiveExamforAttempt();

	ArrayList<Attempt> getNewExamSessionAttempt(int attemptId);

	

}
