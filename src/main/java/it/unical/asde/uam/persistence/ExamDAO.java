package it.unical.asde.uam.persistence;



import it.unical.asde.uam.dao.DAO;
import java.util.List;

import it.unical.asde.uam.model.Exam;


public interface ExamDAO extends DAO<Exam> {
	
	public void create(Exam exam);
	
	public Exam getExamById(int id);
	
	public Exam getExamByName(String name);
	
	public List<Exam> getExamsOfTotCredits(int cfu);
	
	public void saveUpdates(Exam exam);
	
	public void deleteExam(Exam exam);
	
}
