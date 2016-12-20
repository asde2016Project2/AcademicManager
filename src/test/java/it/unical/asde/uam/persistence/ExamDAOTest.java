/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.Exam;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class ExamDAOTest {

    @Autowired
    private ApplicationContext context;

    @Before
    public void setUp() {
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        for (int i = 0; i < 10; i++) {

            Exam e = new Exam("Enterprise" + i, 12 + i, 2 + i);
            examDAO.create(e);
        }
    }

//    @After
//    public void delete() {
//        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
//        for (int i = 0; i < 10; i++) {
//            Exam e = new Exam();
//            e.setName("Enterprise" + i);
//            examDAO.delete(e);
//        }
//    }

//    @Test
//    public void testNumberOfExams() {
//        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
//        assertEquals(new Long(10), examDAO.());
//    }

//    @Test
//    public void testGetUserByUsername() {
//        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
//        assertEquals("password1", examDAO.retrieve("Enterprise").getName());
//    }

    @Test
    public void testGetExamByName() {
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        assertNull(examDAO.retrieve("Enterprise"));
    }
}
