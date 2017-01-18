package it.unical.asde.uam.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unical.asde.uam.helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
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

	// @RequestMapping(value = "detail/examaReservationBoard", method =
	// RequestMethod.GET)
	// public String viewExamAttempt(Model model) {
	// AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
	//
	// ArrayList<Attempt> attempts = new ArrayList<>();
	//
	// ArrayList<Attempt> listOfExamReservation =
	// attemptDAO.listActiveExamforAttempt();
	// model.addAttribute("listOfExamReservation", listOfExamReservation);
	//
	// return "student/examaReservationBoard";
	// }

	@RequestMapping(value = "registrationAppeals/examSession/{sessionId:.+}", method = RequestMethod.GET)
	public String viewAttempt(@PathVariable("sessionId") Integer sessionId, Model model) throws Exception {
		ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
		model.addAttribute("examSession", examSessionDAO.getExamSessionById(sessionId));
		ExamSession examSession = new ExamSession();
		examSession = examSessionDAO.getExamSessionById(sessionId);
//		AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
//		model.addAttribute("listOfExamReservation", attemptDAO.getExamSessionToAttempt(sessionId));

		if (examSession != null) {
			Iterator iterate = examSession.getAttempts().iterator();
			for (int i = 0; i < examSession.getAttempts().size(); i++) {
				Attempt attempt = (Attempt) iterate.next();
				model.addAttribute("listExamAttempt", attempt);
			}
		}

		return "student/examReservationBoard";
	}

	@RequestMapping(value = "examReservationBoard/attempt/{attemptId:.+}", method = RequestMethod.POST)
	public String cancelOrSignupForExam(@PathVariable("attemptId") Integer attemptId, Model model,
			HttpServletRequest request) throws Exception {
		UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context
				.getBean("userAttemptRegistrationDAO");
		model.addAttribute("userAttemptRegistration", userAttemptRegistrationDAO.getUserAttemptRegById(attemptId));
		AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
		ArrayList<UserAttemptRegistration> userAttemptRegistrations = new ArrayList<>();

		if (attemptId != null) {
			System.out.println("cheching if it is not null: " + attemptId);
			model.addAttribute("attempt", attemptDAO.getAttemptById(attemptId));
			userAttemptRegistrations = userAttemptRegistrationDAO.getAttemptToUserAttemptReg(attemptId);
			model.addAttribute("listExamForSignuporCancel", userAttemptRegistrations);
			StudentDAO studentDAO = (StudentDAO) context.getBean("Bean");
			logger.debug("attemptId---------" + attemptId);

		} else {
			return "redirect:/";
		}

		System.out.println(userAttemptRegistrations.size());
		System.out.println(attemptDAO.listActiveExamforAttempt());

		return "student/reserveExam";
	}

	/**
	 * Reserve for final exam
	 */
	@RequestMapping(value = { "userAttemptRegistration/id={userAtRegId:.+}",
			"reserveExam/userAttemptRegistration/{userAtRegId:.+}" }, method = RequestMethod.GET)
	public String reserveExam(@PathVariable("userAtRegId") Integer userAtRegId, Model model) throws Exception {
		UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context
				.getBean("userAttemptRegistrationDAO");
		model.addAttribute("userAttemptRegistration", userAttemptRegistrationDAO.getUserAttemptRegById(userAtRegId));

		// AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
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
