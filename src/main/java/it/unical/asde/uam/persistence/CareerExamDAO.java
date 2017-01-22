/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import java.util.List;

import it.unical.asde.uam.model.CareerExam;

/**
 *
 * @author Gezahegn
 */
public interface CareerExamDAO {

	void create(CareerExam careerExam);

	CareerExam retrieveCareerExam(CareerExam careerExam);

	void updateCareerExam(CareerExam careerExam);

	CareerExam getCareerExamById(int id);

	boolean exists(CareerExam careerExam);

	List<CareerExam> listCareerExams();
	
	void removeCareerExam(Integer careerId);
	
	List<CareerExam> getDoneCareerExamsOfaStudent(int studentID);

	List<CareerExam> getCareerExamsOfaStudent(int studentId);
}
