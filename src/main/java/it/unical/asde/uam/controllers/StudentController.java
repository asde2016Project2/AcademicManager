package it.unical.asde.uam.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.unical.asde.uam.helper.Accepted;
import it.unical.asde.uam.helper.Booking;
import it.unical.asde.uam.helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.dto.ExamReserveFormDTO;
import it.unical.asde.uam.model.AcceptingStudentFormDTO;
import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.dto.ProjectionFormDTO;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.SendEmail;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.model.UserAttemptRegistration;
import it.unical.asde.uam.persistence.AttemptDAO;
import it.unical.asde.uam.persistence.CareerExamDAO;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.ExamSessionDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;
import it.unical.asde.uam.persistence.StudentDAO;
import it.unical.asde.uam.persistence.StudyPlanExamDAO;
import it.unical.asde.uam.persistence.UserAttemptRegistrationDAO;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {
	@Autowired
	SendEmail sendEmail;
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String showDashboad(HttpServletRequest request, Model model) {

		if (!SessionHelper.isStudent(request.getSession())) {
			return "redirect:/";
		}
		model.addAttribute("pageTitle", "Student Area");
		return "student/dashboard";
	}
    
    @RequestMapping(value="projection", method = RequestMethod.GET)
    public String projection(Model model, HttpServletRequest request) {
    	Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
    	CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
    	List<CareerExam> listCareerExam = (List<CareerExam>) careerExamDAO.getCareerExamsOfaStudent(loggedStudent.getUserId());
    	double avgScore = 0;
    	int cfuDone = 0;
    	for(CareerExam careerExam : listCareerExam) {
    		if(careerExam.isDone()) {
    			avgScore+= (careerExam.getGrade() * careerExam.getExam().getCfu());
    			cfuDone+=careerExam.getExam().getCfu();
    		}
    	}
    	if(cfuDone != 0)
    		avgScore/=cfuDone;
    	model.addAttribute("pageTitle","Student Projection");
    	model.addAttribute("avgScore",avgScore);
    	model.addAttribute("gbg",((double)(avgScore*11)/3));
    	model.addAttribute("listCareerExam",listCareerExam);
    	model.addAttribute("projectionForm",new ProjectionFormDTO());
    	return "student/projection";
    }
    
    @RequestMapping(value="projection", method = RequestMethod.POST)
    public String makeProjection(@Valid @ModelAttribute("projectionForm") ProjectionFormDTO projectionFormDTO,Model model, HttpServletRequest request) {
    	Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
    	double avgScore = 0;
    	int cfuDone = 0;
    	for(int i=0;i<projectionFormDTO.getGradeExams().size();i++) {
    		if(!projectionFormDTO.getGradeExams().get(i).equals("")) {
    			avgScore+=(Integer.parseInt(projectionFormDTO.getGradeExams().get(i)) 
    					* Integer.parseInt(projectionFormDTO.getCfuExams().get(i)));
    			cfuDone+=Integer.parseInt(projectionFormDTO.getCfuExams().get(i));
    		}
    	}
    	avgScore/=cfuDone;
    	model.addAttribute("pageTitle","Projection result");
    	model.addAttribute("avgScore",avgScore);
    	model.addAttribute("gbg",((double)(avgScore*11)/3));
    	model.addAttribute("nameExams",projectionFormDTO.getNameExams());
    	model.addAttribute("cfuExams",projectionFormDTO.getCfuExams());
    	model.addAttribute("gradeExams",projectionFormDTO.getGradeExams());
    	return "student/projectionResult";
    
    }
		

	// -------------------Exam Reservation process-------//
	@RequestMapping(value = "registrationAppeals", method = RequestMethod.GET)
	public String viewExamSession(Model model) {
		model.addAttribute("pageTitle", "Registration Appeals");
		ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
		List<ExamSession> listExamSession = examSessionDAO.listExamRegAppeals();
		model.addAttribute("listExamSession", listExamSession);

		return "student/registrationAppeals";
	}

	@RequestMapping(value = "registrationAppeals/examSession/{sessionId:.+}", method = RequestMethod.GET)
	public String viewAttempt(@PathVariable("sessionId") Integer sessionId, Model model) throws Exception {
		ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
		model.addAttribute("examSession", examSessionDAO.getExamSessionById(sessionId));
		ExamSession examSession = new ExamSession();
		examSession = examSessionDAO.getExamSessionById(sessionId);
		//

		if (examSession != null) {
			model.addAttribute("pageTitle", "Exam Reservation View");
			AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
			model.addAttribute("listOfExamReservation", attemptDAO.getExamSessionToAttempt(sessionId));

		} else {
			return "redirect:/";
		}

		return "student/listExamReservationBoard";
	}

	/////////////////////////////////// this done

	@RequestMapping(value = "list/ExamReserve", method = RequestMethod.GET)
	public String viewAttempt(Model model) throws NullPointerException {
		model.addAttribute("pageTitle", "Exam Board");
		AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
		ArrayList<Attempt> listOfExamReservation = attemptDAO.listActiveExamforAttempt();
		ArrayList<Professor> professor = new ArrayList<>();

		if (listOfExamReservation.isEmpty()) {
			System.out.println("ExamBoardInformaiton--");
			return "redirect:/";

		} else {

			System.out.println("ExamBoardInformaiton- return list of information-");
			for (Attempt attempt : listOfExamReservation)
				professor.add(attempt.getProfessor());

			// model.addAttribute("listOfExamReservation", exams);
			model.addAttribute("listOfExamReservation", listOfExamReservation);
		}
		return "student/listExamReservationBoard";
	}
	

	@RequestMapping(value = "detail/examBooking/{attemptId}", method = RequestMethod.GET)
	public String cancelOrSignupForExam(@PathVariable("attemptId") Integer attemptId, Model model,
			HttpServletRequest request) throws Exception {
		AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
		Attempt attempt = new Attempt();
		attempt = attemptDAO.getAttemptById(attemptId);

		model.addAttribute("examName", attempt.getExam().getName());
		model.addAttribute("startDate", attempt.getStartRegistrationDate());
		model.addAttribute("accademicSession", attempt.getExamSession().getAcademicYear());
		
		
		System.out.println("cheching if it is not null: " + attemptId);
		 
		    ArrayList<Attempt> attempts = new ArrayList<>();
	    	attempts = attemptDAO.getNewExamSessionAttempt(attemptId);
	    	ArrayList<UserAttemptRegistration> userAttemptRegistrations =  new ArrayList<>();
//		
	    	if(attempts.size()>0){
	    		
	    	model.addAttribute("attempts",attempts);
	    	model.addAttribute("attemptId",attempt.getAttemptId());
//	    	model.addAttribute("username", attemptRegistration.getStudent().getUsername());
	        model.addAttribute("examBookingForm", new ExamReserveFormDTO());
	        model.addAttribute("userAttRegId",attempt.getAttemptId());
	    	}else{
	    		return "redirect:/student/listExamReservationBoard";
	    	}
	    	
//		
		return "student/reserveExam";
	}
 int signedStudent=0;
	/**
	 * Reserve for final exam
	 */
	@RequestMapping(value = "detail/examBooking/{userAttRegId}", method = RequestMethod.POST, params = "signup")
	public String reserveForExam(@RequestParam(value="attemptId") int attemptId,
			@RequestParam(value = "signup") String status, Model model, HttpServletRequest request) {
		UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
		AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
		
		Attempt attempt = attemptDAO.getAttemptById(attemptId);
		Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());

		
		model.addAttribute("signedStudent", userAttRegDAO.getUserAttemptByStudentNum().size());
		if(attempt !=null && loggedStudent != null ){
			UserAttemptRegistration attemptRegistration2 = new UserAttemptRegistration(attempt, loggedStudent);
			attemptRegistration2.setBooking(Booking.SIGNUP);
			attemptRegistration2.setStatus("SIGNUP");
			userAttRegDAO.create(attemptRegistration2);
			model.addAttribute("examName", attempt.getExam().getName());
			model.addAttribute("startDate", attempt.getStartRegistrationDate());
			model.addAttribute("accademicSession", attempt.getExamSession().getAcademicYear());
			String studFullName=loggedStudent.getFirstName() +" "+ loggedStudent.getLastName();
			model.addAttribute("studFullName",studFullName);
			System.out.println("//////////////////////" + attemptRegistration2);
			
			sendEmail.sendEmailRegistration(loggedStudent.getEmail(),loggedStudent.getFirstName(),
					loggedStudent.getLastName(),SendEmail.SUBJECT_REQUEST_REGISTATION,SendEmail.TEXT_ACCEPTED_REGISTRATION);
	    	int userId= loggedStudent.getUserId();
	    	System.out.println("userId====logger="+userId);
		
	    	ArrayList<UserAttemptRegistration> listStudentBooked = userAttRegDAO.getUserAttemptByStudentUserNames(userId);
	    	model.addAttribute("listStudentBooked", listStudentBooked);
		}
		return "student/resultReserveExam";
	}

	/**
	 * Cancel Reservation for final exam
	 */
	 @RequestMapping(value = "detail/examBooking/{userAttRegId}/resultReserveExam", method = RequestMethod.POST, params = "cancel")
	    public String cancelReservation(@RequestParam(value = "cancel") String status, 
	    		@RequestParam(value = "userAtRegId") int userAtRegId,Model model,HttpServletRequest  request) {
		 UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");

	       
	        
		UserAttemptRegistration result = userAttRegDAO.getUserAttemptRegById(userAtRegId);
//		model.addAttribute("userAttemptRegistration", result);
		Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
		
		Attempt attempt = new Attempt();
		attempt=result.getAttempt();
		UserAttemptRegistration attemptRegistration2 = new UserAttemptRegistration(attempt, loggedStudent);
		attemptRegistration2.setBooking(Booking.CANCEL);
		attemptRegistration2.setStatus("Cancel");
		System.out.println("//////////////////////" + attemptRegistration2);
		 sendEmail.sendEmailRegistration(loggedStudent.getEmail(),loggedStudent.getFirstName(),loggedStudent.getLastName(),
	        		SendEmail.SUBJECT_REQUEST_REGISTATION,SendEmail.TEXT_NOT_ACCEPTED_REGISTRATION);
		userAttRegDAO.delete(result);
		
		
		ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
		List<ExamSession> listExamSession = examSessionDAO.listExamRegAppeals();
		model.addAttribute("listExamSession", listExamSession);

		return "student/registrationAppeals";
	}



	
	
	

	// registration stuff
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String showRegisterForm(HttpServletRequest request, Model model) {

		model.addAttribute("pageTitle", "Student Register");

		Student p = new Student();
		model.addAttribute("student", p);

		return "student/register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("student") @Valid Student student, BindingResult result,
			HttpServletRequest request, Model model) {

		model.addAttribute("pageTitle", "Student Register");

		StudentDAO studentDao = (StudentDAO) context.getBean("studentDAO");

		System.out.println("studentHasError: " + result.hasErrors());
		if (!result.hasErrors()) {
			// Set to false in production or add a default value "false" for
			// this field
			student.setStatus(true);
			boolean saved = studentDao.register(student);
			if (!saved) {
				model.addAttribute("error", "Username or email already taken");
				return "student/register";
			} else {
				model.addAttribute("message",
						messageSource.getMessage("registration.ok", null, localeResolver.resolveLocale(request)));
				// we clean the model passed to view
				model.addAttribute("student", new Student());
				return "student/register";
			}

		}

		return "student/register";
	}

	@RequestMapping(value = "visualizeStudyPlan", method = RequestMethod.GET)
	public String listStudyPlanExams(Model model, HttpServletRequest request) throws NullPointerException {

		Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());

		StudyPlan studyPlan = loggedStudent.getStudyPlan();
		model.addAttribute("studyPlanName", studyPlan.getName());

		StudyPlanExamDAO spexamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
		List<StudyPlanExam> spexams = spexamDAO.getAllExamsOfAstudyPlan(studyPlan);
		List<Exam> exams = new ArrayList<>();

		for (StudyPlanExam spe : spexams)
			exams.add(spe.getExam());

		model.addAttribute("listStudyPlanExams", exams);

		return "student/visualizeStudyPlan";
	}

}
