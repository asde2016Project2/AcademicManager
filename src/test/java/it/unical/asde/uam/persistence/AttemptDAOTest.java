package it.unical.asde.uam.persistence;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.StudyPlan;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:**/WEB-INF/spring/root-context.xml"})
public class AttemptDAOTest {

	@Autowired
    private ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
		Professor p = new Professor("prof1", "123", "professore", "uno", true);
        Date birthDate = sdf.parse("11-11-56");
        p.setDateOfBirth(birthDate);
        p.setAge(60);
        p.setEmail("profes@gmail.com");
        
        Professor p2 = new Professor("prof2", "123", "professore2", "due", true);
        Date birthDate2 = sdf.parse("11-11-56");
        p2.setDateOfBirth(birthDate2);
        p2.setAge(60);
        p2.setEmail("profes2@gmail.com");

        professorDAO.create(p);
        professorDAO.create(p2);
        System.out.println("user id prof prima: "+p.getUserId());
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
     	Exam e = new Exam("asde", 6, 123);
     	Exam e1 = new Exam("asde1", 5, 124);
     	Exam e2 = new Exam("asde2", 10, 125);
     	Exam e3 = new Exam("asde3", 5, 126);
     	Exam e4 = new Exam("asde4", 12, 127);
     	Exam e5 = new Exam("asde5", 14, 128);
     	Exam e6 = new Exam("asde6", 2, 129);
     	Exam e7 = new Exam("asde7", 5, 122);
     	Exam e8 = new Exam("asde8", 6, 121);
     	examDAO.create(e);
     	examDAO.create(e1);
     	examDAO.create(e2);
     	examDAO.create(e3);
     	examDAO.create(e4);
     	examDAO.create(e5);
     	examDAO.create(e6);
     	examDAO.create(e7);
     	examDAO.create(e8);
		
     	DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        DegreeCourse dgC = new DegreeCourse("Informatica");
        degreeCourseDAO.create(dgC);
        
     	Date startingDate = sdf.parse("11-11-11");
     	Date endingDate = sdf.parse("11-11-12");
     	ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
        ExamSession es1 = new ExamSession(startingDate, endingDate, "anno1", dgC);
        ExamSession es2 = new ExamSession(startingDate, endingDate, "anno2", dgC);
        ExamSession es3 = new ExamSession(startingDate, endingDate, "anno3", dgC);
        ExamSession es4 = new ExamSession(startingDate, endingDate, "anno4", dgC);
        
        examSessionDAO.create(es1);
        examSessionDAO.create(es2);
        examSessionDAO.create(es3);
        examSessionDAO.create(es4);
     	
        System.out.println("user id exam session prima: "+es1.getExamSessionId());
        System.out.println("user id exam prima: "+e1.getId());
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
		
		Date examDate = sdf.parse("25-11-11");
		
		Attempt a1 = new Attempt(examDate, "mt5", startingDate, endingDate, p, e1, es1);
		Attempt a2 = new Attempt(examDate, "mt6", startingDate, endingDate, p, e, es1);
		Attempt a3 = new Attempt(examDate, "mt7", startingDate, endingDate, p, e2, es1);
		Attempt a4 = new Attempt(examDate, "mt8", startingDate, endingDate, p, e3, es1);
		Attempt a5 = new Attempt(examDate, "mt9", startingDate, endingDate, p2, e4, es1);
		Attempt a6 = new Attempt(examDate, "mt0", startingDate, endingDate, p2, e5, es1);
		Attempt a7 = new Attempt(examDate, "mt1", startingDate, endingDate, p2, e6, es1);
		Attempt a8 = new Attempt(examDate, "mt2", startingDate, endingDate, p2, e7, es1);
		Attempt a9 = new Attempt(examDate, "mt3", startingDate, endingDate, p2, e8, es1);
		attemptDAO.create(a1);
		attemptDAO.create(a2);
		attemptDAO.create(a3);
		attemptDAO.create(a4);
		attemptDAO.create(a5);
		attemptDAO.create(a6);
		attemptDAO.create(a7);
		attemptDAO.create(a8);
		attemptDAO.create(a9);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAttemptIdByProfByExam() {
		AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
		ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		int a = attemptDAO.getAttemptByProfessorByExam(professorDAO.retrieve("prof1"), examDAO.retrieve("asde1"));
		String classroom = attemptDAO.getAttemptById(a).getClassroom();
		System.out.println("aula: "+classroom);

	}
	
	
}
