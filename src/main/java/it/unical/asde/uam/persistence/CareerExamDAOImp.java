/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import java.util.List;
import it.unical.asde.uam.dao.DAOImp;
import it.unical.asde.uam.model.CareerExam;

/**
 *
 * @author Gezahegn
 */
public class CareerExamDAOImp extends DAOImp<CareerExam> implements CareerExamDAO {

     public CareerExamDAOImp() {
    }

 
 
    @Override
    public void create(CareerExam careerExam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerExam getCareerExamById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
