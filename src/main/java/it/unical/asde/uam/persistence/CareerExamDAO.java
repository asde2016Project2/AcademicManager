/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import java.util.List;
import it.unical.asde.uam.dao.DAO;
import it.unical.asde.uam.model.CareerExam;

/**
 *
 * @author Gezahegn
 */
public interface CareerExamDAO extends DAO<CareerExam> {
    
    	public void create(CareerExam careerExam);
	
	public CareerExam getCareerExamById(int id);
	
	public List<CareerExam> getCareerExamList(int examDate);
	
	public void saveUpdates(CareerExam careerExam);
	
	public void deleteExam(CareerExam careerExam);
    
    
}
