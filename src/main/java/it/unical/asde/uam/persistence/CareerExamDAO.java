/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import java.util.List;

import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Student;

/**
 *
 * @author Gezahegn
 */
public interface CareerExamDAO {

	void create(CareerExam careerExam);
	
	void update(CareerExam careerExam);
	
	void delete(CareerExam careerExam);

	CareerExam retrieveCareerExam(CareerExam careerExam);

	void updateCareerExam(CareerExam careerExam);

	CareerExam getCareerExamById(int id);

	boolean exists(CareerExam careerExam);

	List<CareerExam> listCareerExams();
	
	void removeCareerExam(Integer careerId);

	CareerExam getCareerExamByExamByStudent(Student s, Exam e);

}
