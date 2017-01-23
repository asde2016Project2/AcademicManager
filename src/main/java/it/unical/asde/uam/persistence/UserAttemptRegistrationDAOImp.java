package it.unical.asde.uam.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.UserAttemptRegistration;
import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Student;

public class UserAttemptRegistrationDAOImp implements UserAttemptRegistrationDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserAttemptRegistrationDAOImp.class);
    private DBHandler dbHandler;

    public DBHandler getDbHandler() {
        return dbHandler;
    }

    public void setDbHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public void create(UserAttemptRegistration userAttemptRegistration) {
        dbHandler.create(userAttemptRegistration);
    }

    @Override
    public void update(UserAttemptRegistration userAttemptRegistration) {
        dbHandler.update(userAttemptRegistration);
    }

    @Override
    public void delete(UserAttemptRegistration userAttemptRegistration) {
        dbHandler.delete(userAttemptRegistration);
    }

    @Override
    public UserAttemptRegistration getUserAttemptRegById(int userAtRegId) {
        String hql = "from UserAttemptRegistration where userAtRegId=:userAtRegId";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("userAtRegId", userAtRegId);

        dbHandler.begin();
        UserAttemptRegistration userAttemptRegistration = (UserAttemptRegistration) query.uniqueResult();
        dbHandler.commit();
        return userAttemptRegistration;
    }

    @Override
    public boolean updateUserAttemptRegistration(UserAttemptRegistration userAttemptRegistration) {

        String hql = "UPDATE UserAttemptRegistration set status = :status Where userAtRegId = :userAtRegId";
        // Create a Query instance for the given HQL query string.
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("status", userAttemptRegistration.getStatus());
        query.setParameter("userAtRegId", userAttemptRegistration.getUserAtRegId());
        // Persists to HSQLDB
        try {
            dbHandler.begin();
            int result = query.executeUpdate();
            logger.info("UserAttemptRegistration updated successfully, UserAttemptRegistration Details="
                    + userAttemptRegistration);
            dbHandler.commit();
            return true;
        }
        catch (RuntimeException rex) {
            return false;
        }
    }

    @Override
    public UserAttemptRegistration getUserAttemptRegByAttemptId(Integer attemptId) {

        String hql = "SELECT userAttemptRegistration from UserAttemptRegistration AS userAttemptRegistration"
                + "  where userAttemptRegistration.attempt.attemptId=:attemptId";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("attemptId", attemptId);
        dbHandler.begin();
        UserAttemptRegistration attemptRegistration = (UserAttemptRegistration) query.uniqueResult();
        dbHandler.commit();

        return attemptRegistration;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<UserAttemptRegistration> getAttemptToUserAttemptReg(Integer attemptId) {

        Query query = dbHandler.getSession()
                .createQuery("SELECT userAttemptRegistration FROM UserAttemptRegistration AS userAttemptRegistration"
                        + " join fetch userAttemptRegistration.student"
                        + "  WHERE userAttemptRegistration.attempt.attemptId = :attemptId");
        query.setParameter("attemptId", attemptId);
        try {
            dbHandler.begin();
            ArrayList<UserAttemptRegistration> attempts = new ArrayList<>(query.list());
            dbHandler.commit();
            return attempts;
        }
        catch (Exception ex) {
            logger.debug("List of UserAttemptRegistrationt" + ex);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<UserAttemptRegistration> getUserAttemptByStudentNum() {
        Query query = dbHandler.getSession()
                .createQuery("FROM UserAttemptRegistration ");

        try {
            dbHandler.begin();
            ArrayList<UserAttemptRegistration> attemptRegistrations = new ArrayList<>(query.list());
            dbHandler.commit();
            return attemptRegistrations;
        }
        catch (Exception ex) {
            logger.debug("List of UserAttemptRegistrationt" + ex);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<UserAttemptRegistration> getUserAttemptByStudentUserNames(int userId) {

        Query query = dbHandler.getSession()
                .createQuery("SELECT userAttemptRegistration FROM UserAttemptRegistration AS userAttemptRegistration"
                        + "  WHERE userAttemptRegistration.student.userId = :userId");
        query.setParameter("userId", userId);
        try {
            dbHandler.begin();
            ArrayList<UserAttemptRegistration> attemptRegistrations = new ArrayList<>(query.list());
            dbHandler.commit();
            return attemptRegistrations;
        }
        catch (Exception ex) {
            logger.debug("List of UserAttemptRegistrationt" + ex);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserAttemptRegistration> getUserAttemptRegistrationByAttempId(int attemptId) {

//		int attemptId = attempt.getAttemptId();
        System.out.println("aTTEMPT id = " + attemptId);
        String hql = "from UserAttemptRegistration where attempt_id=:attemptID";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("attemptID", attemptId);
        List<UserAttemptRegistration> uAr = query.list();
        return uAr;

    }

    @Override
    public UserAttemptRegistration getAttemptRegistrationByStudentByAttempt(Attempt attempt, Student student) {

        String hql = "from UserAttemptRegistration where student.username=:studUsername and attempt.attemptId=:attemptId";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("studUsername", student.getUsername());
        query.setParameter("attemptId", attempt.getAttemptId());
        UserAttemptRegistration uar = (UserAttemptRegistration) query.uniqueResult();
        return uar;
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserAttemptRegistration getUserAttemptByStudentUserName(int userId) {
        Query query = dbHandler.getSession()
                .createQuery("SELECT userAttemptRegistration FROM UserAttemptRegistration AS userAttemptRegistration"
                        + "  WHERE userAttemptRegistration.student.userId =:userId");

        query.setParameter(
                "userId", userId);
        try {
            dbHandler.begin();
            UserAttemptRegistration attemptRegistration = (UserAttemptRegistration) query.uniqueResult();
            dbHandler.commit();
            return attemptRegistration;
        }
        catch (Exception ex) {
            logger.debug("List of UserAttemptRegistrationt" + ex);
            return null;
        }
    }

}
