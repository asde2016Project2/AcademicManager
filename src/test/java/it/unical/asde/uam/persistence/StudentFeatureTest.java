package it.unical.asde.uam.persistence;

import static org.junit.Assert.assertEquals;

import java.util.List;

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

import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;

/**
*
* @author Nello
* 
*/


@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:**/WEB-INF/spring/root-context.xml" })
public class StudentFeatureTest {
	
	private ExamDAO examDAO;
	private DegreeCourseDAO degreeCourseDAO;
	private StudyPlanDAO studyPlanDAO;
	private StudyPlanExamDAO studyPlanExamDAO;
	private StudentDAO studentDAO;
	
	private StudyPlan businessStudyPlan;
	private Student loggedStudent;
	
	public StudentFeatureTest(){
	}
	
	
	@Autowired
	private ApplicationContext context;

	
	//note: everytime a @Test method is called, the @Before method is also called.
	// so, use just one @Test method, otherwise you will have duplicates
	
	@Before
	public void initialize() {
		
		examDAO = (ExamDAO) context.getBean("examDAO");
		degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
		studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
		studyPlanExamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
		studentDAO = (StudentDAO) context.getBean("studentDAO");
		
		Exam ex1 = new Exam("tcs", 12, 333);
		Exam ex2 = new Exam("physics", 6, 444);
		examDAO.create(ex1);
		examDAO.create(ex2);
		
		DegreeCourse dg = new DegreeCourse("computer science");
		degreeCourseDAO.create(dg);
		
		businessStudyPlan = new StudyPlan("business", dg);
		StudyPlan sp2 = new StudyPlan("networking", dg);
		studyPlanDAO.create(businessStudyPlan);
		studyPlanDAO.create(sp2);
		
		StudyPlanExam spe1 = new StudyPlanExam(businessStudyPlan, ex1, "second"); //tcs
		StudyPlanExam spe2 = new StudyPlanExam(businessStudyPlan, ex2, "first"); //physics
		studyPlanExamDAO.create(spe1);
		studyPlanExamDAO.create(spe2);
		
		loggedStudent = new Student("usr", "pwd", "ciccio", "pasticcio", true, businessStudyPlan);
		studentDAO.create(loggedStudent);
	}
	
	@Test
	public void testEverything()
	{
		//assertEquals(examDAO.retrieve("physics").getCfu(), 6);
		
		System.out.println("zzzzzzzzztest test 1....");		
		
		List<Exam> myExams = examDAO.getAllExams();		
		for(Exam e : myExams)
			System.out.println("exam--->  " + e.getName());	
		
		List<DegreeCourse> myDgs = degreeCourseDAO.getAllDegrees();
		for(DegreeCourse d : myDgs)
			System.out.println("degree course--->  " + d.getName());
		
		List<StudyPlan> mySps = studyPlanDAO.getAllPlans();		
		for(StudyPlan s : mySps)
			System.out.println("study plan--->  " + s.getName());
		
		//List<StudyPlanExam> mySpes = studyPlanExamDAO.getAllExamsOfAstudyPlan(businessStudyPlan);
		List<StudyPlanExam> mySpes = 
				studyPlanExamDAO.getAllExamsOfAstudyPlan(loggedStudent.getStudyPlan());
		for(StudyPlanExam spe : mySpes)
			System.out.println("logged student, study plan (business), exam--->  " + spe.getExam().getName());
		
		
		System.out.println("zzzzzzzzzzzzzz END --- test 1...");
	}
	
	
	
	

}
