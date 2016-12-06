package it.unical.asde.uam.test;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.ExamDAOImpl;


public class ExamDAOTest {
	
	
	private static ExamDAO dao;
	private static ArrayList<Exam> dbExams = new ArrayList<>();
	
	private final static int generateRandomExamCode()
	{
		Random rand = new Random();

		return  rand.nextInt(666) + 1;
		//666 is the maximum and the 1 is our minimum 
	}
	
	@BeforeClass
	public static void initialize() {
		dao = new ExamDAOImpl();
		for(int i=0;i<10;i++) {
			Exam exam = new Exam("name"+i, 12, generateRandomExamCode());
			dao.create(exam);
			dbExams.add(exam);
		}		
	}
	
	@Test
	public void testCreate() {
		Exam savingExam = new Exam("ASDE", 6, generateRandomExamCode());
		dao.create(savingExam);
		assertEquals(dao.getExamByName("ASDE").getCfu(), 6);
		dao.deleteExam(savingExam);
	}
	
	@Test
	public void testList() {
		assertEquals(dao.getExamsOfTotCredits(12).size(), 10);
	}
	
	@Test 
	public void testDelete() {
		dao.deleteExam(dbExams.get(0));
		assertEquals(dao.getExamsOfTotCredits(12).size(), 9);
		dao.create(dbExams.get(0));
	}
	
	@Test 
	public void testUpdate() {
		dbExams.get(0).setCode(12345);
		dao.saveUpdates(dbExams.get(0));
		assertEquals(dao.getExamById(dbExams.get(0).getId()).getCode(), 12345);
	}

}
