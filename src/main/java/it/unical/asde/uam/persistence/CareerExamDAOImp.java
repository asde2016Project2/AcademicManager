/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import java.util.List;
import it.unical.asde.uam.dao.DAOImp;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Exam;

/**
 *
 * @author Gezahegn
 */
public class CareerExamDAOImp extends DAOImp<CareerExam> implements CareerExamDAO {

    public CareerExamDAOImp() {
    }

    Exam examInformation = new Exam();

    @Override
    public void create(CareerExam careerExam) {

        begin();

        getSession().saveOrUpdate(careerExam);

        commit();

    }

    @Override
    public CareerExam getCareerExamById(int id) {
        CareerExam careerExam = new CareerExam();
        String hql = "from CareerExam where id=:id";
        begin();
        
        commit();
        return careerExam;
    }

    @Override
    public List<CareerExam> getCareerExamList(int examDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveUpdates(CareerExam careerExam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteExam(CareerExam careerExam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
