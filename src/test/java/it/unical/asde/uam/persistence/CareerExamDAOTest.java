/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Student;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @author Gezahegn
 */
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:**/WEB-INF/spring/root-context.xml"})
public class CareerExamDAOTest {

    public CareerExamDAOTest() {
    }

    @Autowired
    private ApplicationContext context;

    @Before
    public void setUp() {
        CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        for (int i = 0; i < 10; i++) {
            Exam exam = new Exam();
            exam.setName("exam name");
            Student student = new Student();
            student.setUsername("test");
            Calendar c = new GregorianCalendar();
            c.set(Calendar.YEAR, 1982);
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            c.set(Calendar.DAY_OF_MONTH, 25);
            Date d = c.getTime();
            boolean done = true;
            boolean mandatory = true;
            int grade = 30;
            CareerExam careerExam = new CareerExam(done, grade, d, mandatory);
//            careerExam.setDone(done);
//            careerExam.setGrade(30);
//            careerExam.setExamDate(d);
//            careerExam.setMandatory(mandatory);

            careerExamDAO.create(careerExam);
        }

    }

    @After
    public void tearDown() {

        CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        for (int i = 0; i < 10; i++) {
            CareerExam e = new CareerExam();
            e.setGrade(30 + i);
            careerExamDAO.deleteCareerExam(e);
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void fetchCareerExam() {
        CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        CareerExam e = new CareerExam();
        e.setGrade(30);
        int grades = e.getGrade();
        assertNull(careerExamDAO.fetchCareerExamInfo(grades));
    }
}
