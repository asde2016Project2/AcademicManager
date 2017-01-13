/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.Exam;
import java.util.List;

/**
 *
 * @author Gezahegn
 */
public interface ExamDAO {

	void create(Exam exam);

	Exam getExamById(int id);

	Exam retrieve(Exam examName);

	void updateExam(Exam exam);

	void removeExam(Integer exam);

	boolean exists(Exam username);

	List<Exam> getAllExams();

}
