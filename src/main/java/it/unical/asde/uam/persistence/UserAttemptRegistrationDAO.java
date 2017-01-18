package it.unical.asde.uam.persistence;

import java.util.ArrayList;
import java.util.List;

import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.UserAttemptRegistration;

public interface UserAttemptRegistrationDAO {

	UserAttemptRegistration getUserAttemptRegById(int userAtRegId);

	void updateUserAttemptRegistration(UserAttemptRegistration userAttemptRegistration);

	ArrayList<UserAttemptRegistration> getAttemptToUserAttemptReg(Integer attemptId);

	Student getStudentToUserAttemptReg(Integer userAtRegId);

	void create(UserAttemptRegistration userAttemptRegistration);

}
