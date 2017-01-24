package it.unical.asde.uam.controllers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//github.com/asde2016Project2/AcademicManager.git
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.asde.uam.helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.helper.Booking;
import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.SendEmail;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.UserAttemptRegistration;
import it.unical.asde.uam.persistence.AttemptDAO;
import it.unical.asde.uam.persistence.CareerExamDAO;
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

	 @Autowired
	  SendEmail sendEmail;
    
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
    
    @RequestMapping(value ="listSession", method = RequestMethod.GET)
    public String viewAllSession(Model model){
    	ExamSessionDAO examSessionDao = (ExamSessionDAO) context.getBean("examSessionDAO");
    	ArrayList<ExamSession> allExamSessions = (ArrayList<ExamSession>) examSessionDao.getAllExamSession();//.listAllSession();
    	model.addAttribute("lista", allExamSessions);
    	return "professor/listSession";
    }
    
    @RequestMapping(value ="listAttempt", method = RequestMethod.GET)
    public String viewAllAttempts(Model model, HttpServletRequest request){
    	Professor p = SessionHelper.getUserProfessorLogged(request.getSession()); 
    	AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
    	model.addAttribute("attemptList", attemptDAO.getAttemptByProfessor(p));
    	return "professor/listAttempt";
    }
    
    @RequestMapping(value ="studentExtraExamSession", method = RequestMethod.GET)
    public String viewStudentForExtraExamSession(Model model){
    	
    	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
    	ArrayList<Student> students = studentDAO.getStudentForStraordinaryExamSession(studentDAO.getAllStudents());
    	if(students.size() == 0)
    		model.addAttribute("error", "There are no students that can partecipate to extra exam session");
    	else model.addAttribute("listaStudenti", students);
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
    
    @RequestMapping(value = "createAttempt", method = RequestMethod.GET)
    public String createNewAttempt(Model model) {
    	ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
    	model.addAttribute("examSessions", examSessionDAO.getAllExamSession());
    	ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
    	model.addAttribute("exams", examDAO.getAllExams());	
    	System.out.println("create attempt getttt");
        return "professor/createAttempt";
    }


    @RequestMapping(value = "createAttempt", method = RequestMethod.POST)
    public String doCreateNewAttempt(Model model, @ModelAttribute("examDate") String examDateString,
    		@ModelAttribute("startingDate") String startingDateString, @ModelAttribute("endingDate") String endingDateString,
    		@ModelAttribute("classRoom") String classRoom, @ModelAttribute("examSession") String examSession,
    		@ModelAttribute("exam") String exam, HttpServletRequest request) throws ParseException {
    	
    	ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
    	ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
    	AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");

    	String[] examSessionIdStr = examSession.split("---");
    	int examSessionId = Integer.parseInt(examSessionIdStr[0]);

    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startingDate = sdf.parse(startingDateString);
		Date endingDate = sdf.parse(endingDateString);
		Date examDate = sdf.parse(examDateString);
		Date examSessionStart = examSessionDAO.getExamSessionById(examSessionId).getStartingDate();
		Date examSessionEnd = examSessionDAO.getExamSessionById(examSessionId).getEndingDate();
		
		if(attemptDAO.checkAttempt(startingDate, endingDate, examDate, examSessionStart, examSessionEnd)) {
			Attempt a = new Attempt(examDate, classRoom, startingDate, endingDate,
					SessionHelper.getUserProfessorLogged(request.getSession()), examDAO.getExamByName(exam), examSessionDAO.getExamSessionById(examSessionId));
    		attemptDAO.create(a);
		}
		else {
    		model.addAttribute("error", "The dates or academic year are not ok");
    		return "professor/createAttempt";
    	}
        return "professor/createAttempt";
    }
    
    
    
    
    
    
    

/**
 * View students sign-up for exam session 
 * if the student meets the basic requirement of professors list it will accepted to take the exam
 * otherwise it will rejected from the exam sessions
 */
@RequestMapping(value = "viewStudentExamSignup", method = RequestMethod.GET)
public String getStudentSignupForExamSession(Model model,HttpServletRequest request){
	  
	 Professor loggedProfessor = SessionHelper.getUserProfessorLogged(request.getSession());
	 UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
    
	 if(loggedProfessor !=null){
    	 List<UserAttemptRegistration> listStudentExamSignup = userAttRegDAO.getStudentSignupProfExamSession(loggedProfessor);
    	 model.addAttribute("listStudentExamSignup", listStudentExamSignup);
     }
	 
	return "professor/viewStudentExamSignup";
}




@RequestMapping(value = "viewStudentExamSignup", method = RequestMethod.POST)
public String acceptStudentSignupForExamSession(Model model, HttpServletRequest request){
//	System.out.println("prof user name==="+ username);
	Professor loggedProfessor = SessionHelper.getUserProfessorLogged(request.getSession());
	 UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
    UserAttemptRegistration userAttReg= userAttRegDAO.getUserAttemptByProfessorUserName(loggedProfessor);
    System.out.println("prof user name==="+ loggedProfessor.getUsername());
    userAttReg.setBooking(Booking.SIGNUP);
    userAttRegDAO.create(userAttReg);
	
    sendEmail.sendEmailRegistration(userAttReg.getStudent().getEmail(),userAttReg.getStudent().getFirstName(),userAttReg.getStudent().getLastName(),
    		SendEmail.SUBJECT_EXAM_BOOKING,SendEmail.EXAM_SESSION_ATTEMPT_SIGNUP);
    
	
    if(loggedProfessor !=null){
   	 List<UserAttemptRegistration> listStudentExamSignup = userAttRegDAO.getStudentSignupProfExamSession(loggedProfessor);
   	 model.addAttribute("listStudentExamSignup", listStudentExamSignup);
    }
    return "professor/viewStudentSignupExam";
}
//
//
//
@RequestMapping(value = "viewStudentExamSignup", method = RequestMethod.POST, params = "reject")
public String rejectStudentSignupforExam(@RequestParam(value = "reject") String username, Model model,HttpServletRequest request) {
	 Professor loggedProfessor = SessionHelper.getUserProfessorLogged(request.getSession());
	 UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
    UserAttemptRegistration userAttReg= userAttRegDAO.getUserAttemptByProfessorUserName(loggedProfessor);
    
    userAttRegDAO.delete(userAttReg);
    sendEmail.sendEmailRegistration(userAttReg.getStudent().getEmail(),userAttReg.getStudent().getFirstName(),userAttReg.getStudent().getLastName(),
    		SendEmail.SUBJECT_EXAM_BOOKING,SendEmail.EXAM_SESSION_ATTEMPT_CANCELED);
    

    if(loggedProfessor !=null){
   	 List<UserAttemptRegistration> listStudentExamSignup = userAttRegDAO.getStudentSignupProfExamSession(loggedProfessor);
   	 model.addAttribute("listStudentExamSignup", listStudentExamSignup);
    }
    return "admin/viewStudentExamSignup";
}
}