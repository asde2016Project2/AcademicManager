/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Exam;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Gezahegn
 */
public class CareerExamDAOImp implements CareerExamDAO {

    public CareerExamDAOImp() {
    }

    private DBHandler dbHandler;

    public DBHandler getDbHandler() {
        return dbHandler;
    }

    public void setDbHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

   

    @Override
    public void create(CareerExam careerExam) {
        dbHandler.create(careerExam);
    }

    @Override
    public void update(CareerExam careerExam) {
        dbHandler.update(careerExam);
    }

    @Override
    public void deleteCareerExam(CareerExam careerExam) {
        dbHandler.delete(careerExam);
    }

  
    
    @Override
    public CareerExam retrieve(int grades) {
        Session session = dbHandler.getSessionFactory().openSession();
        String queryString = "from CareerExam where grade = :ex";
        Query query = session.createQuery(queryString);
        query.setParameter("ex", grades);
        CareerExam e = (CareerExam) query.uniqueResult();
        dbHandler.close();
        return e;
    }

}
