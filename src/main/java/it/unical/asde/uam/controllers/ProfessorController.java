package it.unical.asde.uam.controllers;

import it.unical.asde.uam.Helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.ExamSessionDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Francesco Bruno
 */
@Controller
@RequestMapping("/professor")
public class ProfessorController extends BaseController {

  
    
    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String showDashboad(HttpServletRequest request,Model model) {
        
        model.addAttribute("pageTitle","Professor Area");     
        
        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/";
        }

        return "professor/dashboard";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String showRegisterForm(Model model, HttpServletRequest request) {

        model.addAttribute("pageTitle","Professor Register");     
        
        Professor p = new Professor();
        model.addAttribute("professor", p);

        return "professor/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("professor") @Valid Professor professor, BindingResult result, HttpServletRequest request, Model model) {

        model.addAttribute("pageTitle","Professor Register");     
        
        ProfessorDAO professorDao = (ProfessorDAO) context.getBean("professorDAO");

        System.out.println("professorHasError: " + result.hasErrors());
        if (!result.hasErrors()) {
            //Set to false in production or add a default value "false" for this field
            professor.setStatus(true);
            boolean saved = professorDao.register(professor);
            if (!saved) {
                model.addAttribute("error", "Username or email already taken");
                return "professor/register";
            }
            else{
                model.addAttribute("message", messageSource.getMessage("registration.ok", null, localeResolver.resolveLocale(request)));
                //we clean the model passed to view
                model.addAttribute("professor",new Professor());
                return "professor/register";
            }

        } 
        
        return "professor/register";
    }
    
    
    
    
    @RequestMapping(value = "createSession", method = RequestMethod.GET)
    public String openCreateNewSession() {
    	System.out.println("sono in professor create session");
         return "professor/createSession";
    }
    
    @RequestMapping(value = "createSession", method = RequestMethod.POST)
    public String createNewSession(@RequestParam("startingDate") String startingDateString, @RequestParam("endingDate") String endingDateString,
    		@RequestParam("degreeCourse") String degreeCourseName, @RequestParam("academicYear") String academicYear,
    		HttpServletRequest request) throws ParseException{
    	
    	ProfessorDAO professorDao = (ProfessorDAO) context.getBean("professorDAO");
    	if(professorDao.checkExamSession(request.getParameter("startingDate"),request.getParameter("endingDate")))
    	{
    		DegreeCourse degreeCourse = new DegreeCourse(degreeCourseName);
    		DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
    		degreeCourseDAO.create(degreeCourse);
    		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
    		Date startingDate = null;
   			startingDate = sdf.parse(startingDateString);
   			Date endingDate = null;
   			endingDate = sdf.parse(endingDateString);
    		ExamSessionDAO examSessionDao = (ExamSessionDAO) context.getBean("examSessionDAO");
    		ExamSession examSession = new ExamSession(startingDate, endingDate, academicYear, degreeCourse);
    		examSessionDao.create(examSession);
    		return "professor/dashboard";
    	}
    	else return "professor/errorExamSession";
    }
    
    @RequestMapping(value ="viewAllSession", method = RequestMethod.GET)
    public String viewAllSession(Model model){
    	ProfessorDAO professorDao = (ProfessorDAO) context.getBean("professorDAO");
    	ArrayList<ExamSession> allExamSessions = professorDao.listAllSession();
    	model.addAttribute("lista", allExamSessions);
    	return "professor/listSession";
    }


}