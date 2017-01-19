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

import it.unical.asde.uam.helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.dto.ExamReserveFormDTO;
import it.unical.asde.uam.model.AcceptingStudentFormDTO;
import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.SendEmail;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.model.UserAttemptRegistration;
import it.unical.asde.uam.persistence.AttemptDAO;
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

	@RequestMapping(value = "detail/examBooking/{attemptId}", method = RequestMethod.GET,params="reserve")
	public String cancelOrSignupForExam(@PathVariable("attemptId") Integer attemptId, Model model,
			HttpServletRequest request, @RequestParam(value="reseve")String status) throws Exception {
		AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
		Attempt attempt = new Attempt();
		attempt = attemptDAO.getAttemptById(attemptId);

		model.addAttribute("examName", attempt.getExam().getName());
		model.addAttribute("startDate", attempt.getStartRegistrationDate());
		model.addAttribute("accademicSession", attempt.getExamSession().getAcademicYear());
		ArrayList<UserAttemptRegistration> userAttemptRegistrations = null;

		userAttemptRegistrations = new ArrayList<>();
		System.out.println("cheching if it is not null: " + attemptId);
		UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context
				.getBean("userAttemptRegistrationDAO");
		UserAttemptRegistration attemptRegistration = new UserAttemptRegistration();
		attemptRegistration = userAttemptRegistrationDAO.getUserAttemptRegByAttemptId(attemptId);
		//

		userAttemptRegistrations = userAttemptRegistrationDAO.getAttemptToUserAttemptReg(attemptId);
		if (userAttemptRegistrations.size() > 0 || !userAttemptRegistrations.isEmpty()) {
			model.addAttribute("listExamForSignuporCancel", userAttemptRegistrations);

			model.addAttribute("studentName", attemptRegistration.getStudent().getFirstName() + " "
					+ attemptRegistration.getStudent().getLastName());
			System.out.println("attemptId---------" + attemptId);

		} else {
			return "redirect:/";
		}
		    model.addAttribute("username", attemptRegistration.getStudent().getUsername());
	        model.addAttribute("examBookingForm", new ExamReserveFormDTO());
	        model.addAttribute("userAttRegId",attemptRegistration.getUserAtRegId());
		System.out.println("----------------" + userAttemptRegistrations.size());

		return "student/reserveExam";
	}

	/**
	 * Reserve for final exam
	 */
	@RequestMapping(value = "book/exam", method = RequestMethod.POST)
	public String reserveForExam(@Valid @ModelAttribute("examBookingForm") 
	ExamReserveFormDTO examReserveFormDTO, Model model) {
		
		UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context
				.getBean("userAttemptRegistrationDAO");
		UserAttemptRegistration result = new UserAttemptRegistration();
		int userId= examReserveFormDTO.getUserAttemptRegId();
		System.out.println("userAttemptId"+userId);
		result = userAttRegDAO.getUserAttemptRegById(userId);
		model.addAttribute("userAttemptRegistration", result);

		
		ArrayList<UserAttemptRegistration> userAttemptRegistrations = null;
		StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        Student student = studentDAO.retrieve(examReserveFormDTO.getUsername());
        
       
        result.setStatus(examReserveFormDTO.getStatus());
		boolean saved = userAttRegDAO.updateUserAttemptRegistration(result);
        
        sendEmail.sendEmailRegistration(student.getEmail(),student.getFirstName(),student.getLastName(),
        		SendEmail.SUBJECT_REQUEST_REGISTATION,SendEmail.TEXT_ACCEPTED_REGISTRATION);
        
        List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
        model.addAttribute("listStudents", listStudents);
		return "student/reserveExam";
	}

	/**
	 * Cancel for final exam
	 */
	@RequestMapping(value = "cancel/exam/{userAtRegId}", method = RequestMethod.POST)
	public String cancelForExam(@PathVariable("userAtRegId") Integer userAtRegId, Model model,
			HttpServletRequest request) throws Exception {
		UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context
				.getBean("userAttemptRegistrationDAO");
		UserAttemptRegistration result = new UserAttemptRegistration();
		result = userAttRegDAO.getUserAttemptRegById(userAtRegId);
		model.addAttribute("userAttemptRegistration", result);

		result.setStatus("caneled");
		boolean saved = userAttRegDAO.updateUserAttemptRegistration(result);
		ArrayList<UserAttemptRegistration> userAttemptRegistrations = null;

		userAttemptRegistrations = new ArrayList<>();
//		userAttemptRegistrations = userAttRegDAO.getUserAttemptRegByAttemptId(userAtRegId);
//		if (userAttemptRegistrations.size() > 0 || !userAttemptRegistrations.isEmpty()) {
//			model.addAttribute("listExamForSignuporCancel", userAttemptRegistrations);
//
//			model.addAttribute("studentName", result.getStudent().getFirstName() + " "
//					+ result.getStudent().getLastName());
//			System.out.println("attemptId---------" + result);
//
//		} else {
//			return "redirect:/";
//		}
		return "student/reserveExam";
	}

	@RequestMapping(value = "bookExam", method = RequestMethod.POST)
	public String cancelReservationExam(
			@ModelAttribute("userAttemptRegistration") @Valid UserAttemptRegistration userAttemptRegistration,
			BindingResult result, HttpServletRequest request, Model model) {
		UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context
				.getBean("userAttemptRegistrationDAO");
		// model.addAttribute("userAttemptRegistration",
		// userAttRegDAO.getUserAttemptRegById(userAtRegId));

		if (!result.hasErrors()) {

			userAttemptRegistration.setStatus("caneled");
			boolean saved = userAttRegDAO.updateUserAttemptRegistration(userAttemptRegistration);

		}

		return "student/reserveExam";
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
