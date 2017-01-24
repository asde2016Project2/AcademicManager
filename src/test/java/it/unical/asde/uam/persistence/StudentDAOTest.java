package it.unical.asde.uam.persistence;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
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
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:**/WEB-INF/spring/root-context.xml"})
public class StudentDAOTest {

    @Autowired
    private ApplicationContext context;

    @Before
    public void setUp() throws ParseException {

        DegreeCourse dG = new DegreeCourse("inform");
        DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        degreeCourseDAO.create(dG);

        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
        StudyPlan sP = new StudyPlan("piano", dG);
        studyPlanDAO.create(sP);

        Student s1 = new Student("ciccetto", "qqqre", "ciccio", "pasticcio", true, sP);
        s1.setAge(22);
        s1.setEmail("ciccio@gmail.com");
        String dateOfBirth = "11-11-1999";
        String dateOfBirthFormat = "dd-MM-yyyy";
        DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
        Date dateOfBirthObject = format.parse(dateOfBirth);
        s1.setDateOfBirth(dateOfBirthObject);

        Student s2 = new Student("ciccino", "qqqw", "bella", "tututututrtu", true, sP);
        s2.setAge(23);
        s2.setEmail("ciccio345@gmail.com");
        s2.setDateOfBirth(dateOfBirthObject);

        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");

        studentDAO.create(s1);
        studentDAO.create(s2);

        Professor p = new Professor("profffff", "fdfd", "cretino", "dsadsa", true);
        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        p.setDateOfBirth(dateOfBirthObject);
        p.setAge(23);
        p.setEmail("cicc@gmail.com");
        professorDAO.create(p);

        ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
        ExamSession examSession = new ExamSession(dateOfBirthObject, dateOfBirthObject, "2015");
        examSessionDAO.create(examSession);

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

        System.out.println("create attempts");

        CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        CareerExam c = new CareerExam(true, 25, true, s1, e);
        CareerExam c1 = new CareerExam(false, 25, true, s1, e1);
        CareerExam c2 = new CareerExam(true, 25, true, s1, e2);
        CareerExam c3 = new CareerExam(false, 25, true, s1, e3);
        CareerExam c4 = new CareerExam(true, 25, true, s1, e4);
        CareerExam c5 = new CareerExam(true, 25, true, s1, e5);
        CareerExam c6 = new CareerExam(true, 25, true, s1, e6);
        CareerExam c7 = new CareerExam(true, 25, true, s1, e7);
        CareerExam c8 = new CareerExam(true, 25, true, s1, e8);

        CareerExam c9 = new CareerExam(true, 25, true, s2, e);
        CareerExam c10 = new CareerExam(false, 25, true, s2, e1);
        CareerExam c20 = new CareerExam(true, 25, true, s2, e2);
        CareerExam c30 = new CareerExam(false, 25, true, s2, e3);
        CareerExam c40 = new CareerExam(true, 25, true, s2, e4);
        CareerExam c50 = new CareerExam(true, 25, true, s2, e5);
        CareerExam c60 = new CareerExam(false, 25, true, s2, e6);
        CareerExam c70 = new CareerExam(true, 25, true, s2, e7);
        CareerExam c80 = new CareerExam(true, 25, true, s2, e8);
        careerExamDAO.create(c);
        careerExamDAO.create(c1);
        careerExamDAO.create(c2);
        careerExamDAO.create(c3);
        careerExamDAO.create(c4);
        careerExamDAO.create(c5);
        careerExamDAO.create(c6);
        careerExamDAO.create(c7);
        careerExamDAO.create(c8);
        careerExamDAO.create(c9);
        careerExamDAO.create(c10);
        careerExamDAO.create(c20);
        careerExamDAO.create(c30);
        careerExamDAO.create(c40);
        careerExamDAO.create(c50);
        careerExamDAO.create(c60);
        careerExamDAO.create(c70);
        careerExamDAO.create(c80);
    }

    @Test
    public void testGetStudentStraordinayExamSession() {

        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        ArrayList<Student> s = studentDAO.getStudentForStraordinaryExamSession(studentDAO.getAllStudents());
        System.out.println("studenti: " + s.get(0).getFirstName());
        String ss = studentDAO.getAllStudents().get(0).getUsername();
        System.out.println("student username: " + ss);
        ArrayList<CareerExam> c = studentDAO.getInformationStudent(ss);
        for (int i = 0; i < c.size(); i++) {
            System.out.println("..." + c.get(i).getExam().getName() + "..." + c.get(i).isDone());
        }
    }
}
