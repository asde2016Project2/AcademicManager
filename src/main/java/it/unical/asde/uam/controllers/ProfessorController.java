package it.unical.asde.uam.controllers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//github.com/asde2016Project2/AcademicManager.git
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.unical.asde.uam.helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.UserAttemptRegistration;
import it.unical.asde.uam.persistence.AttemptDAO;
import it.unical.asde.uam.persistence.CareerExamDAO;
import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.ExamSessionDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;
import it.unical.asde.uam.persistence.StudentDAO;
import it.unical.asde.uam.persistence.UserAttemptRegistrationDAO;

/**
 *
 * @author Francesco Bruno
 */
@Controller
@RequestMapping("/professor")
public class ProfessorController extends BaseController {

  
    
    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String showDashboad(HttpServletRequest request, Model model) {
        
        model.addAttribute("pageTitle","Professor Area");     
        
        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/";
        }

        return "professor/dashboard";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String showRegisterForm(HttpServletRequest request, Model model) {

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
    public String openCreateNewSession(Model model) {
    	DegreeCourseDAO degreeCourseDao = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
    	ArrayList<DegreeCourse> allDegrees = (ArrayList<DegreeCourse>) degreeCourseDao.getAllDegrees();
    	model.addAttribute("degreeCourses", allDegrees);
    	
        return "professor/createSession";
    }
    
    @RequestMapping(value = "createSession", method = RequestMethod.POST)
    public String createNewSession(@RequestParam("startingDate") String startingDateString, @RequestParam("endingDate") String endingDateString,
    		@RequestParam("degreeCourse") String degreeCourseName, @RequestParam("academicYear") String academicYear,
    		HttpServletRequest request) throws ParseException{
    	
    	ProfessorDAO professorDao = (ProfessorDAO) context.getBean("professorDAO");
    	if(professorDao.checkExamSession(request.getParameter("startingDate"),request.getParameter("endingDate"), request.getParameter("academicYear")))
    	{
    		DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
    		DegreeCourse degreeCourse = degreeCourseDAO.getDegreeCourseByName(degreeCourseName);
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Date startingDate = null;
   			startingDate = sdf.parse(startingDateString);
   			Date endingDate = null;
   			endingDate = sdf.parse(endingDateString);
    		
   			ExamSessionDAO examSessionDao = (ExamSessionDAO) context.getBean("examSessionDAO");
    		ExamSession examSession = new ExamSession(startingDate, endingDate, academicYear, degreeCourse);

    		examSessionDao.create(examSession);
    		ArrayList<ExamSession> allExamSessions = professorDao.listAllSession();
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
    
    @RequestMapping(value ="studentExtraExamSession", method = RequestMethod.GET)
    public String viewStudentForExtraExamSession(Model model){
    	
    	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
    	ArrayList<Student> students = studentDAO.getStudentForStraordinaryExamSession(studentDAO.getAllStudents());
    	model.addAttribute("listaStudenti", students);
    	return "professor/listStudent";
    }
    
    @RequestMapping(value ="informationStudent", method = RequestMethod.GET)
    public String getInformationStudent(Model model){
    	
    	return "professor/informationStudent";
    }
    
    @RequestMapping(value ="informationStudent", method = RequestMethod.POST)
    public String getInformationStudentByUsername(Model model, @ModelAttribute("username") String username){
    	
    	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
    	ArrayList<CareerExam> students = studentDAO.getInformationStudent(username);
    	Student stud = studentDAO.retrieve(username);
    	model.addAttribute("infoStudent", students);
    	model.addAttribute("student", stud);
    	return "professor/informationStudent";
    }
 
    String examName = "";
    List<UserAttemptRegistration> uar = new ArrayList<>();
    
    @RequestMapping(value ="registerExam", method = RequestMethod.GET)
    public String getRegisterExam(Model model, HttpServletRequest request){
    	model.addAttribute("examName", examName);
    	model.addAttribute("userar", uar);
    	if(!(examName.equals("")))
    		doRegisterExam(model, examName, request);
    	return "professor/registerExam";
    }
    
    @RequestMapping(value ="registerExam", method = RequestMethod.POST)
    public String doRegisterExam(Model model, @ModelAttribute("examname") String examname, 
    		HttpServletRequest request){
    	
    	examName = examname;
    	
    	AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
    	ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
    	Exam e = examDAO.getExamByName(examName);
    	Professor p = SessionHelper.getUserProfessorLogged(request.getSession()); 
    	int attemptId = attemptDAO.getAttemptByProfessorByExam(p, e);
    	
    	UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
    	uar = userAttemptRegistrationDAO.getUserAttemptRegistrationByAttempId(attemptId);
    	model.addAttribute("examName", examname);
    	model.addAttribute("userar", uar);
    	
    	return "professor/registerExam";
    }
    
    @RequestMapping(value ="addCareerExam", method = RequestMethod.POST)
    public String addCareerExam(Model model, @ModelAttribute("grade") int grade,
    		@RequestParam("studentUsername") String studUsername2,
    		@RequestParam("attemptId") int attemptId){
    	
    	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
    	AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
    	ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
    	CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
    	CareerExam c = new CareerExam();
    	
    	if(grade>=18) {
    		c = new CareerExam(false, 25, true, studentDAO.retrieve(studUsername2), examDAO.getExamByName(examName));
    		c = careerExamDAO.getCareerExamByExamByStudent(studentDAO.retrieve(studUsername2), examDAO.getExamByName(examName));
    		c.setDone(true);
    		c.setGrade(grade);
    		careerExamDAO.update(c);
    	}
    	else {
    		c = new CareerExam(false, 25, true, studentDAO.retrieve(studUsername2), examDAO.getExamByName(examName));
    		careerExamDAO.update(c);
    	}

    	careerExamDAO.listCareerExams();
    	UserAttemptRegistration userAttemptRegistration = new UserAttemptRegistration();
    	UserAttemptRegistrationDAO attemptRegistrationDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
    	userAttemptRegistration = attemptRegistrationDAO.getAttemptRegistrationByStudentByAttempt(attemptDAO.getAttemptById(attemptId), studentDAO.retrieve(studUsername2));
    	attemptRegistrationDAO.delete(userAttemptRegistration);
    	
    	return "redirect:/professor/registerExam";
    	
    }


}