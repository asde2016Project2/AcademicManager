package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.CareerExam;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.persistence.CareerExamDAOImp;

import it.unical.asde.uam.persistence.ExamDAOImp;


public class ExamDAOTest {
	
	
	private static ExamDAOImp dao;
	private static CareerExamDAOImp careerExamDAO;
	private static ArrayList<Exam> dbExams = new ArrayList<>();
	private static ArrayList<CareerExam> careerExamlist = new ArrayList<>();
	
	private final static int generateRandomExamCode()
	{
		Random rand = new Random();



        return rand.nextInt(666) + 1;
        //666 is the maximum and the 1 is our minimum 
    }

}
