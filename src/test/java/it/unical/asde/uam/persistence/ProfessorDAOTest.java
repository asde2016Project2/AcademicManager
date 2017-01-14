/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @author Francesco Bruno
 */
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:**/WEB-INF/spring/root-context.xml"})
public class ProfessorDAOTest {

    @Autowired
    private ApplicationContext context;

   
    @Before
    public void setUp() throws ParseException {
        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        for (int i = 0; i < 5; i++) {
            Professor p = new Professor("prof" + i, "123456", "mario", "rossi", true);
            p.setEmail("prof" + i + "@mat.unical.it");
            p.setAge(21);
            
            String dateOfBirth = "11-11-2011";
            String dateOfBirthFormat = "dd-mm-yyyy";
            DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
            Date dateOfBirthObject = format.parse(dateOfBirth);
            
            p.setDateOfBirth(dateOfBirthObject);
            
            professorDAO.create(p);
        }
        DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        DegreeCourse dgC = new DegreeCourse("Informatica");
        degreeCourseDAO.create(dgC);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date endingDate = sdf.parse("11-11-11");
        Date startingDate = sdf.parse("12-12-11");
        ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
        ExamSession e1 = new ExamSession(startingDate, endingDate, "anno1", dgC);
        ExamSession e2 = new ExamSession(startingDate, endingDate, "anno2", dgC);
        ExamSession e3 = new ExamSession(startingDate, endingDate, "anno3", dgC);
        ExamSession e4 = new ExamSession(startingDate, endingDate, "anno4", dgC);
        
        examSessionDAO.create(e1);
        examSessionDAO.create(e2);
        examSessionDAO.create(e3);
        examSessionDAO.create(e4);
    }
    
       
    @After
    public void delete() {
        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        for (int i = 0; i < 5; i++) {
            Professor p = new Professor();
            p.setUsername("prof" + i);
            professorDAO.delete(p);
        }
    } 
     

    @Test
    public void testProfessorByUsername() {
    	ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
    	ArrayList<ExamSession> es = professorDAO.listAllSession();
    	System.out.println("ehy, l'anno è "+es.get(0).getAcademicYear());
//        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
//         for (int i = 0; i < 5; i++) {
//            assertNotNull(professorDAO.retrieveForLogin("prof"+i, "123456"));
//         }
    }
}