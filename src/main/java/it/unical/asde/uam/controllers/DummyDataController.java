/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.controllers;

import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;
import it.unical.asde.uam.persistence.StudentDAO;
import it.unical.asde.uam.persistence.StudyPlanDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            p.setEmail("prof" + i + "@mat.unical.it");
            p.setAge(21);

            String dateOfBirth = "11-11-2011";
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
    	StudyPlanDAO studyPlanDAO= (StudyPlanDAO) context.getBean("studyPlanDAO");    	
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

}
