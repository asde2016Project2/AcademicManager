package it.unical.asde.uam.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unical.asde.uam.Helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.persistence.AttemptDAO;
import it.unical.asde.uam.persistence.ExamSessionDAO;
import it.unical.asde.uam.persistence.UserAttemptRegistrationDAO;

/**
 *
 * @author Gezahegn
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@RequestMapping(value = "studentDashboard", method = RequestMethod.GET)
	public String showDashboad(HttpServletRequest request) {

		if (!SessionHelper.isStudent(request.getSession())) {
			return "redirect:/";
		}

		return "student/studentDashboard";
	}

	



	// -------------Exam Booking source code----------------------------//

	@RequestMapping(value = "/registrationAppeals", method = RequestMethod.GET)
	public String viewExamBooking(Model model) {
		ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
		List<ExamSession> listExamSession = examSessionDAO.listExamRegAppeals();
		model.addAttribute("listExamSession", listExamSession);

		return "student/registrationAppeals";
	}

	@RequestMapping(value = { "/examSession/id={sessionId:.+}",
			"/registrationAppeals/examSession/{sessionId:.+}" }, method = RequestMethod.GET)
	public String examReservationView(@PathVariable("sessionId") Integer sessionId, Model model) throws Exception {
		ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
		model.addAttribute("examSession", examSessionDAO.getExamSessionById(sessionId));

		AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
		model.addAttribute("listOfExamReservation", attemptDAO.getExamSessionToAttempt(sessionId));

		return "student/examReservationBoard";
	}

	@RequestMapping(value = { "/attempt/id={attemptId:.+}",
			"/examReservationBoard/attempt/{attemptId:.+}" }, method = RequestMethod.GET)
	public String cancelExamReservationView(@PathVariable("attemptId") Integer attemptId, Model model)
			throws Exception {
		UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context
				.getBean("userAttemptRegistrationDAO");
		// model.addAttribute("userAttemptRegistration",
		// userAttemptRegistrationDAO.getUserAttemptRegById(attemptId));

		model.addAttribute("listResrveExams", userAttemptRegistrationDAO.getAttemptToUserAttemptReg(attemptId));

		return "student/reserveExam";
	}

	/**
	 * Reserve for final exam
	 */
	@RequestMapping(value = { "/userAttemptRegistration/id={userAtRegId:.+}",
			"/reserveExam/userAttemptRegistration/{userAtRegId:.+}" }, method = RequestMethod.GET)
	public String reserveExam(@PathVariable("userAtRegId") Integer userAtRegId, Model model) throws Exception {
		UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context
				.getBean("userAttemptRegistrationDAO");
		model.addAttribute("userAttemptRegistration", userAttemptRegistrationDAO.getUserAttemptRegById(userAtRegId));

		// AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");

		return "student/reserveExam";
	}

}
