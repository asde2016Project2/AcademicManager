package it.unical.asde.uam.persistence;

import java.util.ArrayList;
import java.util.List;

import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.UserAttemptRegistration;

public interface UserAttemptRegistrationDAO {

	void create(UserAttemptRegistration userAttemptRegistration);

	void update(UserAttemptRegistration userAttemptRegistration);

	void delete(UserAttemptRegistration userAttemptRegistration);
	
	UserAttemptRegistration getUserAttemptRegById(int userAtRegId);

	boolean updateUserAttemptRegistration(UserAttemptRegistration userAttemptRegistration);

	ArrayList<UserAttemptRegistration> getAttemptToUserAttemptReg(Integer attemptId);

	UserAttemptRegistration getUserAttemptRegByAttemptId(Integer userAtRegId);

	List<UserAttemptRegistration> getUserAttemptRegistrationByAttempId(int attemptId);

	UserAttemptRegistration getAttemptRegistrationByStudentByAttempt(Attempt attempt, Student student);

	ArrayList<UserAttemptRegistration> getUserAttemptByStudentUserNames(int userId);

	UserAttemptRegistration getUserAttemptByStudentUserName(int userId);
	
	ArrayList<UserAttemptRegistration> getUserAttemptByStudentNum();
}
