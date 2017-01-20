/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.controllers;

import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.Administrator;
import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.model.UserAttemptRegistration;
import it.unical.asde.uam.persistence.AdministratorDAO;
import it.unical.asde.uam.persistence.AttemptDAO;
import it.unical.asde.uam.persistence.CareerExamDAO;
import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.ExamSessionDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;
import it.unical.asde.uam.persistence.StudentDAO;
import it.unical.asde.uam.persistence.StudyPlanDAO;
import it.unical.asde.uam.persistence.StudyPlanExamDAO;
import it.unical.asde.uam.persistence.UserAttemptRegistrationDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author kz
 */
@Controller
@RequestMapping("/dummyData")
public class DummyDataController extends BaseController {

    @RequestMapping(value = "registerProfessor", method = RequestMethod.GET)
    public String registerProfessor() throws ParseException {
        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        for (int i = 0; i < 5; i++) {
            Professor p = new Professor("prof" + i, "123456", "mario", "rossi", true);
            p.setEmail("prof" + i + "@mat.unical.it");
            p.setAge(21);

            String dateOfBirth = "01-01-1971";
            String dateOfBirthFormat = "dd-mm-yyyy";
            DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
            Date dateOfBirthObject = format.parse(dateOfBirth);

            p.setDateOfBirth(dateOfBirthObject);

            professorDAO.create(p);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "registerStudent", method = RequestMethod.GET)
    public String registerStudent() throws ParseException {

        DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");

        DegreeCourse dg = new DegreeCourse("computer science");
        degreeCourseDAO.create(dg);
        StudyPlan businessStudyPlan = new StudyPlan("business", dg);
        studyPlanDAO.create(businessStudyPlan);

        for (int i = 0; i < 5; i++) {
        	 Student p = new Student("stud"+i, "123456", "pro", "asde", true, businessStudyPlan);
        	 p.setEmail("stud" + i + "@mat.unical.it");
           p.setAge(19);

           String dateOfBirth = "11-11-1999";
           String dateOfBirthFormat = "dd-mm-yyyy";
           DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
           Date dateOfBirthObject = format.parse(dateOfBirth);

           p.setDateOfBirth(dateOfBirthObject);

           studentDAO.create(p);
        }
        return "redirect:/";
    }
    
    @RequestMapping(value = "addDegreeCourse", method = RequestMethod.GET)
    public String addDegreeCourse() {
    	DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
    	DegreeCourse d1 = new DegreeCourse("Computer Science");
    	DegreeCourse d2 = new DegreeCourse("Mathematics");
    	DegreeCourse d3 = new DegreeCourse("Physics");
    	DegreeCourse d4 = new DegreeCourse("Biology");
    	degreeCourseDAO.create(d1);
    	degreeCourseDAO.create(d2);
    	degreeCourseDAO.create(d3);
    	degreeCourseDAO.create(d4);
    	
    	return "redirect:/";
    }

    @RequestMapping(value = "registerStudyPlan", method = RequestMethod.GET)
    public String registerStudyPlan() {

        String viewToRender = "redirect:/";
        DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        if (!degreeCourseDAO.getAllDegrees().isEmpty()) {
            return viewToRender;
        }
        ArrayList<DegreeCourse> degreeCourses = new ArrayList<>();
        degreeCourses.add(new DegreeCourse("Computer Science"));
        degreeCourses.add(new DegreeCourse("Engineering"));
        degreeCourses.add(new DegreeCourse("Mathematics"));

        degreeCourseDAO.create(degreeCourses.get(0));
        degreeCourseDAO.create(degreeCourses.get(1));
        degreeCourseDAO.create(degreeCourses.get(2));

        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
        if (!studyPlanDAO.getAllPlans().isEmpty()) {
            return viewToRender;
        }
        ArrayList<StudyPlan> studyPlans = new ArrayList<>();
        studyPlans.add(new StudyPlan("Scientific Computing", degreeCourses.get(0)));
        studyPlans.add(new StudyPlan("Civil", degreeCourses.get(1)));
        studyPlans.add(new StudyPlan("Management", degreeCourses.get(1)));
        studyPlans.add(new StudyPlan("Numbers Theory", degreeCourses.get(2)));

        studyPlanDAO.create(studyPlans.get(0));
        studyPlanDAO.create(studyPlans.get(1));
        studyPlanDAO.create(studyPlans.get(2));
        studyPlanDAO.create(studyPlans.get(3));

        ArrayList<Exam> exams = new ArrayList<>();
        exams.add(new Exam("Theoretical Computer Science", 10, 23));
        exams.add(new Exam("Knowledge Management", 10, 21));
        exams.add(new Exam("Intelligent System", 5, 22));
        exams.add(new Exam("Network and Security", 10, 18));
        exams.add(new Exam("Mobile and Social Computing", 5, 19));
        exams.add(new Exam("Models and simulation", 5, 17));
        exams.add(new Exam("Data Mining and Data Warehouse", 10, 15));
        exams.add(new Exam("Social network and new media", 5, 12));
        exams.add(new Exam("Criptography", 5, 11));
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        for (Exam e : exams) {
            examDAO.create(e);
        }
        StudyPlanExamDAO studyPlanExamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
        for (Exam e : exams) {
            studyPlanExamDAO.create(new StudyPlanExam(studyPlans.get(0), e, "period"));
        }

        return viewToRender;
    }
    
    
    @RequestMapping(value = "registerAdmin", method = RequestMethod.GET)
    public String registerAdmin() throws ParseException {
        AdministratorDAO administratorDAO = (AdministratorDAO) context.getBean("administratorDAO");
        for (int i = 0; i < 5; i++) {
            Administrator p = new Administrator("admin" + i, "123456", "mario", "rossi", true);
            p.setEmail("prof" + i + "@mat.unical.it");
            p.setAge(21);

            String dateOfBirth = "01-01-1971";
            String dateOfBirthFormat = "dd-mm-yyyy";
            DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
            Date dateOfBirthObject = format.parse(dateOfBirth);

            p.setDateOfBirth(dateOfBirthObject);

            administratorDAO.create(p);
        }
        return "redirect:/";
    }
    
    @RequestMapping(value = "addExamSession", method = RequestMethod.GET)
    public String addExamSession() throws ParseException {
    	ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
    	
    	DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
    	List<DegreeCourse> degrees = degreeCourseDAO.getAllDegrees();
    	
    	String startingD = "23-01-2017";
    	String endingD = "04-03-2017";
        String dateOfBirthFormat = "dd-mm-yyyy";
        DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
        Date startingDate = format.parse(startingD);
        Date endingDate = format.parse(endingD);
    	
    	ExamSession e1 = new ExamSession(startingDate, endingDate, "2016/2017", degrees.get(0));
    	ExamSession e2 = new ExamSession(startingDate, endingDate, "2016/2017", degrees.get(1));
    	ExamSession e3 = new ExamSession(startingDate, endingDate, "2016/2017", degrees.get(2));
    	ExamSession e4 = new ExamSession(startingDate, endingDate, "2016/2017", degrees.get(3));
    	examSessionDAO.create(e1);
    	examSessionDAO.create(e2);
    	examSessionDAO.create(e3);
    	examSessionDAO.create(e4);
    	
    	return "redirect:/";
    }

    @RequestMapping(value = "addExam", method = RequestMethod.GET)
    public String addExam() {
    	
    	ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
    	
    	Exam e1 = new Exam("asde", 6, 21458);
    	Exam e2 = new Exam("asde1", 12, 21258);
    	Exam e3 = new Exam("asde2", 6, 26458);
    	Exam e4 = new Exam("asde3", 3, 21358);
    	Exam e5 = new Exam("asde4", 6, 21858);
    	Exam e6 = new Exam("asde5", 3, 21958);
    	Exam e7 = new Exam("asde6", 6, 21758);
    	Exam e8 = new Exam("asde7", 3, 21058);
    	examDAO.create(e1);
    	examDAO.create(e2);
    	examDAO.create(e3);
    	examDAO.create(e4);
    	examDAO.create(e5);
    	examDAO.create(e6);
    	examDAO.create(e7);
    	examDAO.create(e8);
    	
    	return "redirect:/";
    }
    
    @RequestMapping(value = "addCareerExam", method = RequestMethod.GET)
    public String addCareerExam() {
    	
    	CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
    	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
    	ArrayList<Student> students = (ArrayList<Student>) studentDAO.getAllStudents();
    	System.out.println("students size: "+students.size());
    	
    	ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
    	List<Exam> exams = examDAO.getAllExams();
    	
    	CareerExam cE1 = new CareerExam(true, 25, true, students.get(0), exams.get(0));
    	CareerExam cE2 = new CareerExam(false, 25, true, students.get(0), exams.get(1));
    	CareerExam cE3 = new CareerExam(false, 25, true, students.get(0), exams.get(2));
    	CareerExam cE4 = new CareerExam(true, 25, true, students.get(0), exams.get(3));
    	CareerExam cE5 = new CareerExam(true, 25, true, students.get(0), exams.get(4));
    	CareerExam cE6 = new CareerExam(true, 25, true, students.get(0), exams.get(5));
    	CareerExam cE7 = new CareerExam(true, 25, true, students.get(0), exams.get(6));
    	CareerExam cE8 = new CareerExam(true, 25, true, students.get(0), exams.get(7));
    	
    	CareerExam cE11 = new CareerExam(true, 25, true, students.get(1), exams.get(0));
    	CareerExam cE12 = new CareerExam(false, 25, true, students.get(1), exams.get(1));
    	CareerExam cE13 = new CareerExam(false, 25, true, students.get(1), exams.get(2));
    	CareerExam cE14 = new CareerExam(true, 25, true, students.get(1), exams.get(3));
    	CareerExam cE15 = new CareerExam(false, 25, true, students.get(1), exams.get(4));
    	CareerExam cE16 = new CareerExam(true, 25, true, students.get(1), exams.get(5));
    	CareerExam cE17 = new CareerExam(true, 25, true, students.get(1), exams.get(6));
    	CareerExam cE18 = new CareerExam(true, 25, true, students.get(1), exams.get(7));
    
    	CareerExam cE21 = new CareerExam(true, 25, true, students.get(2), exams.get(0));
    	CareerExam cE22 = new CareerExam(true, 25, true, students.get(2), exams.get(1));
    	CareerExam cE23 = new CareerExam(true, 25, true, students.get(2), exams.get(2));
    	CareerExam cE24 = new CareerExam(true, 25, true, students.get(2), exams.get(3));
    	CareerExam cE25 = new CareerExam(false, 25, true, students.get(2), exams.get(4));
    	CareerExam cE26 = new CareerExam(false, 25, true, students.get(2), exams.get(5));
    	CareerExam cE27 = new CareerExam(true, 25, true, students.get(2), exams.get(6));
    	CareerExam cE28 = new CareerExam(true, 25, true, students.get(2), exams.get(7));

    	careerExamDAO.create(cE1);
    	careerExamDAO.create(cE2);
    	careerExamDAO.create(cE3);
    	careerExamDAO.create(cE4);
    	careerExamDAO.create(cE5);
    	careerExamDAO.create(cE6);
    	careerExamDAO.create(cE7);
    	careerExamDAO.create(cE8);
    	careerExamDAO.create(cE11);
    	careerExamDAO.create(cE12);
    	careerExamDAO.create(cE13);
    	careerExamDAO.create(cE14);
    	careerExamDAO.create(cE15);
    	careerExamDAO.create(cE16);
    	careerExamDAO.create(cE17);
    	careerExamDAO.create(cE18);
    	careerExamDAO.create(cE21);
    	careerExamDAO.create(cE22);
    	careerExamDAO.create(cE23);
    	careerExamDAO.create(cE24);
    	careerExamDAO.create(cE25);
    	careerExamDAO.create(cE26);
    	careerExamDAO.create(cE27);
    	careerExamDAO.create(cE28);
    	
    	return "redirect:/";
    }

    @RequestMapping(value = "addAttempts", method = RequestMethod.GET)
    public String addAttempts() throws ParseException {
    	
    	String dateOfBirthFormat = "dd-MM-yyyy";
        DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);

    	AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
    	Date startingDate = format.parse("11-11-11");
     	Date endingDate = format.parse("11-11-12");
     	Date examDate = format.parse("25-11-12");
     	
     	ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
     	ArrayList<Exam> exams = (ArrayList<Exam>) examDAO.getAllExams();
     	
     	ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
     	ArrayList<Professor> profs = professorDAO.getAllProfessor();
     	
     	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
     	ArrayList<Student> studs = (ArrayList<Student>) studentDAO.getAllStudents();

     	System.out.println("profs 0: "+profs.get(0).getUsername());
     	System.out.println("profs 1: "+profs.get(1).getUsername());
     	System.out.println("profs 2: "+profs.get(2).getUsername());
     	System.out.println("profs 3: "+profs.get(3).getUsername());
     	System.out.println("profs 4: "+profs.get(4).getUsername());
     	DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        DegreeCourse dgC = new DegreeCourse("Informatica");
        degreeCourseDAO.create(dgC);
 
    	ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
        ExamSession es1 = new ExamSession(startingDate, endingDate, "anno1", dgC);
        examSessionDAO.create(es1);

        ArrayList<Attempt> attemtps2 = new ArrayList<>();
     	for(int i = 0; i < 20; i++) {
     		//da ricontrollare...non è fatto benissimo
     		Attempt a = new Attempt(examDate, "mt"+i, startingDate, endingDate, profs.get(i/5), exams.get(i/3), es1);
     		attemptDAO.create(a);
     		attemtps2.add(a);
     		System.out.println("nome prof: "+profs.get(i/5)+"...nome esame: "+exams.get(i/3).getName());
     	}
     	
     	UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");        

     	for(int i = 0; i < attemtps2.size(); i++) {
        	System.out.println("coppie");
        	for( int j = 0; j < studs.size(); j++) {
        		System.out.println("att: "+attemtps2.get(i).getClassroom()+"...stud: "+studs.get(j).getFirstName());
        		UserAttemptRegistration userAR = new UserAttemptRegistration(attemtps2.get(i), studs.get(j));
        		userAttemptRegistrationDAO.create(userAR);
        	}
        }
     	
     	
    	return "redirect:/";
    	
    }
 

}
