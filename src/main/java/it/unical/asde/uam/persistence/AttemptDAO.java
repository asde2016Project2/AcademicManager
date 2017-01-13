package it.unical.asde.uam.persistence;

import java.util.List;

import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Professor;

public interface AttemptDAO {

	List<Professor> getProfessorToAttempt(Integer attemptId);

	List<Attempt> getExamSessionToAttempt(Integer sessionId);

	Attempt getAttemptById(int attemptId);

	void updateAttempt(Attempt attempt);

	void updateAttemptExamReserv(Attempt attempt);

}
