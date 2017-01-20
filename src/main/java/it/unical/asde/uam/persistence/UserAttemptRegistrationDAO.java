package it.unical.asde.uam.persistence;

import java.util.List;

import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.UserAttemptRegistration;

public interface UserAttemptRegistrationDAO {

	void create(UserAttemptRegistration userAttemptRegistration);

	void update(UserAttemptRegistration userAttemptRegistration);

	void delete(UserAttemptRegistration userAttemptRegistration);
	
	UserAttemptRegistration getUserAttemptRegById(int userAtRegId);

	void updateUserAttemptRegistration(UserAttemptRegistration userAttemptRegistration);

	List<UserAttemptRegistration> getAttemptToUserAttemptReg(Integer attemptId);

	Student getStudentToUserAttemptReg(Integer userAtRegId);

	List<UserAttemptRegistration> getUserAttemptRegistrationByAttempId(int attemptId);

	UserAttemptRegistration getAttemptRegistrationByStudentByAttempt(Attempt attempt, Student student);
}
