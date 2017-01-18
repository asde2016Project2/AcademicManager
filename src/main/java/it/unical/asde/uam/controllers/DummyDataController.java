/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.controllers;

import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.Administrator;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.persistence.AdministratorDAO;
import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;
import it.unical.asde.uam.persistence.StudentDAO;
import it.unical.asde.uam.persistence.StudyPlanDAO;
import it.unical.asde.uam.persistence.StudyPlanExamDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            if(i==0)
            	p.setEmail("effe.sessa@gmail.com");
            else
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
            Student p = new Student("stud" + i, "666666", "pierino", "stecchino", true, businessStudyPlan);
            if(i==0){
              p.setEmail("effe.sessa" +  "@gmail.com");
            }
            else {
            	p.setEmail("stud" + i +  "@mat.unical.it");
            }
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
            if(i==0)
            	p.setEmail("effe.sessa@gmail.com");
            else
            	p.setEmail("admin" + i + "@mat.unical.it");
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


}
