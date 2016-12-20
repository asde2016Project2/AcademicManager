package it.unical.asde.uam.persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.StudyPlan;

public class StudentPlanDAOTest {

	static DegreeCourseDAOImp degreeCourseDAOImp;
	static StudyPlanDAOImp studyPlanDAOImp;
	static ArrayList<DegreeCourse> degreeCourses = new ArrayList<DegreeCourse>();
	static ArrayList<StudyPlan> studyPlans = new ArrayList<StudyPlan>();
	
	@BeforeClass
	public static void init(){
		degreeCourseDAOImp = new DegreeCourseDAOImp();
		studyPlanDAOImp = new StudyPlanDAOImp();
		initDB();
	}
	
	static void initDB(){
//		degreeCourses.add(new DegreeCourse("Informatica"));
//		degreeCourses.add(new DegreeCourse("Matematica"));
//		degreeCourseDAOImp.create(degreeCourses.get(0));
//		degreeCourseDAOImp.create(degreeCourses.get(1));
//		for(int i=0;i<4;i++){
//			studyPlans.add(new StudyPlan("studyPlan"+i,degreeCourses.get(0)));
//			studyPlanDAOImp.create(studyPlans.get(i));
//		}
	}
	
	@Test
	public void testDegreeId(){
//		DegreeCourse degreeCourse = degreeCourseDAOImp.getDegreeCourseById(1);
//		assertEquals("Informatica",degreeCourse.getName());
	}
//	
//	@Test
//	public void testGetStudyPlanByName() {
//		StudyPlan studyPlan = studyPlanDAOImp.getStudyPlanByName("studyPlan3");
//		assertEquals("Informatica",studyPlan.getDegreeCourse().getName());
//	}


}
