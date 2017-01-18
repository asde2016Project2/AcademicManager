/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.StudyPlanExam;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gezahegn
 */
public class CareerExamDAOImp implements CareerExamDAO {

	private static final Logger logger = LoggerFactory.getLogger(CareerExamDAOImp.class);

	private DBHandler dbHandler;

	public DBHandler getDbHandler() {
		return dbHandler;
	}

	public void setDbHandler(DBHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	public CareerExamDAOImp() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void create(CareerExam careerExam) {
		dbHandler.create(careerExam);
	}

	@Override
	public void removeCareerExam(Integer careerId) {

		  // Return the persistent instance of the given entity class with the given identifier
		CareerExam careerExam = (CareerExam) dbHandler.getSession().load(CareerExam.class, new Integer(careerId));
        // Remove a persistent instance from the datastore.
        if (careerExam != null) dbHandler.getSession().delete(careerExam);
        System.out.println("finally it's works now ---"+careerExam);
        logger.info("CareerExam deleted successfully, CareerExam details= "+ careerExam);
	}

	@Override
	public void updateCareerExam(CareerExam careerExam) {
		// Retrieve session from Hibernate using dbHandler.getSession()

		// Create hql String
		String hql = "UPDATE CareerExam set user.username = :name Where id = :examId";
		// Create a Query instance for the given HQL query string.
		Query query = dbHandler.getSession().createQuery(hql);
		query.setParameter("name", careerExam.getStudent().getUsername());
		query.setParameter("examId", careerExam.getCareerExamId());
		// Persists to HSQLDB
		query.executeUpdate();
		logger.info("CareerExam updated successfully, CareerExam Details=" + careerExam);
	}

	
	
	// I changed this method because when i tried to update the class it insert
	// a new record
	// it doesn't keep the object but the object detached automatically and
	// instantiate a new
	// transient object
	// @Override
	// public void updateExam(CareerExam careerExam) {
	// dbHandler.update(careerExam);
	// }

	@Override
	public CareerExam retrieveCareerExam(CareerExam careerExam) {
		// Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from CareerExam as careerExam where careerExam.grade = :ex";
		Query query = dbHandler.getSession().createQuery(queryString);
		query.setParameter("ex", careerExam.getGrade());
		try {
			dbHandler.begin();
			CareerExam careerExams = (CareerExam) query.uniqueResult();
			System.out.println("when i am gone------" + careerExam.getGrade());
			dbHandler.commit();
			return careerExams;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean exists(CareerExam careerExam) {
		return retrieveCareerExam(careerExam) != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CareerExam> listCareerExams() {

		String queryString = "from CareerExam";
		Query query = dbHandler.getSession().createQuery(queryString);
		dbHandler.begin();
		List<CareerExam> careerExamList = (List<CareerExam>) query.list();
		for (CareerExam careerExam : careerExamList) {
			logger.info("CareerExam successfully populated" + careerExam);
		}
		dbHandler.commit();
		return careerExamList;
	}

	@Override
	public CareerExam getCareerExamById(int id) {
		String hql = "from CareerExam where careerExamById =:id";
		Query query = dbHandler.getSession().createQuery(hql);
		query.setParameter("id", id);
		dbHandler.begin();
		CareerExam careerExam = (CareerExam) query.uniqueResult();

		// Session session = dbHandler.getSessionFactory().getCurrentSession();
		// CareerExam careerExam = (CareerExam) session.load(CareerExam.class,
		// id);
		logger.info("careerExam successfully loaded, careerExam info: " + careerExam);
		dbHandler.commit();
		return careerExam;
	}
	
	// author: Nello
	@SuppressWarnings("unchecked")
	@Override
	public List<CareerExam> getDoneCareerExamsOfaStudent(int studentID) {
		
		String queryString = "from CareerExam c where c.student.id =:givenid AND c.done is true";
		Query query = dbHandler.getSession().createQuery(queryString);
		 query.setParameter("givenid", studentID);
		dbHandler.begin();
		List<CareerExam> careerExamList = (List<CareerExam>) query.list();
		//for (CareerExam careerExam : careerExamList) {
		//	logger.info("CareerExam successfully populated" + careerExam);
		//}
		dbHandler.commit();
		return careerExamList;
	}

}
