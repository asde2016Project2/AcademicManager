package it.unical.asde.uam.controllers;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import it.unical.asde.uam.dto.StudentFormDTO;
import it.unical.asde.uam.dto.StudyPlanFormDTO;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
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
	public String viewExamBooking(Model model) {
		ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
		List<ExamSession> listExamSession = examSessionDAO.listExamRegAppeals();
		model.addAttribute("listExamSession", listExamSession);

		return "student/registrationAppeals";
	}

	@RequestMapping(value = {"examSession/id={sessionId:.+}",
	"registrationAppeals/examSession/{sessionId:.+}"}, method = RequestMethod.GET)
	public String examReservationView(@PathVariable("sessionId") Integer sessionId, Model model) throws Exception {
		ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
		model.addAttribute("examSession", examSessionDAO.getExamSessionById(sessionId));

		AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
		model.addAttribute("listOfExamReservation", attemptDAO.getExamSessionToAttempt(sessionId));

		return "student/examReservationBoard";
	}

	@RequestMapping(value = {"attempt/id={attemptId:.+}",
	"examReservationBoard/attempt/{attemptId:.+}"}, method = RequestMethod.GET)
	public String cancelExamReservationView(@PathVariable("attemptId") Integer attemptId, Model model)
			throws Exception {
		UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
		//		model.addAttribute("userAttemptRegistration", userAttemptRegistrationDAO.getUserAttemptRegById(attemptId));

		model.addAttribute("listResrveExams", userAttemptRegistrationDAO.getAttemptToUserAttemptReg(attemptId));

		return "student/reserveExam";
	}

	/**
	 * Reserve for final exam
	 */
	@RequestMapping(value = {"userAttemptRegistration/id={userAtRegId:.+}",
	"reserveExam/userAttemptRegistration/{userAtRegId:.+}"}, method = RequestMethod.GET)
	public String reserveExam(@PathVariable("userAtRegId") Integer userAtRegId, Model model) throws Exception {
		UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
		model.addAttribute("userAttemptRegistration", userAttemptRegistrationDAO.getUserAttemptRegById(userAtRegId));

		//		AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
		return "student/reserveExam";
	}

	// registration stuff
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String showRegisterForm(HttpServletRequest request, Model model) {

		model.addAttribute("pageTitle", "Student Register");

		StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
		model.addAttribute("studentForm", new StudentFormDTO());
		model.addAttribute("studyPlanList", studyPlanDAO.getAllPlans());

		return "student/register";



	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("studentForm") @Valid StudentFormDTO studentFormDTO, BindingResult result, HttpServletRequest request, Model model) {

		model.addAttribute("pageTitle", "Student Register");
		StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
		System.out.println("studentHasError: " + result.hasErrors());
		if(result.hasErrors())
		{
			model.addAttribute("studyPlanList", studyPlanDAO.getAllPlans());

			return "student/register";
		}


		StudentDAO studentDao = (StudentDAO) context.getBean("studentDAO");
		


		StudyPlan sp = studyPlanDAO.retrieve(Integer.parseInt(studentFormDTO.getStudyPlanId()));
		Student student = new Student(studentFormDTO.getUsername(), studentFormDTO.getPassword(), studentFormDTO.getFirstName(), studentFormDTO.getLastName(), true, sp);
		student.setAge(Integer.parseInt(studentFormDTO.getAge()));       
		student.setEmail(studentFormDTO.getEmail());		

		String dateOfBirth = studentFormDTO.getDateOfBirth();
		String dateOfBirthFormat = "dd-mm-yyyy";
		DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
		Date dateOfBirthObject=null;
		try {
			dateOfBirthObject = format.parse(dateOfBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		student.setDateOfBirth(dateOfBirthObject);



		//Set to false in production or add a default value "false" for this field
		//student.setStatus(true);
		boolean saved = studentDao.register(student);
		if (!saved) {
			model.addAttribute("error", "Username or email already taken");
			return "student/register";
		}
		else {
			model.addAttribute("message", messageSource.getMessage("registration.ok", null, localeResolver.resolveLocale(request)));
			//we clean the model passed to view
			model.addAttribute("studentForm", new StudentFormDTO());
			model.addAttribute("studyPlanList", studyPlanDAO.getAllPlans());
			return "student/register";
		}


	}


	@RequestMapping(value = "visualizeStudyPlan", method = RequestMethod.GET)
	public String listStudyPlanExams(Model model, HttpServletRequest request) throws NullPointerException {

		Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
		model.addAttribute("studentName", loggedStudent.getFirstName()+" "+loggedStudent.getLastName());

		StudyPlan studyPlan = loggedStudent.getStudyPlan();
		model.addAttribute("studyPlanName", studyPlan.getName());

		StudyPlanExamDAO spexamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
		List<StudyPlanExam> spexams = spexamDAO.getAllExamsOfAstudyPlan(studyPlan); 	 	

		model.addAttribute("listStudyPlanExams", spexams);

		return "student/visualizeStudyPlan";
	}


	@RequestMapping(value = "visualizeCareer", method = RequestMethod.GET)
	public String listCareerExams(Model model, HttpServletRequest request) throws NullPointerException {

		Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
		model.addAttribute("studentName", loggedStudent.getFirstName()+" "+loggedStudent.getLastName());

		int studID = loggedStudent.getUserId();

		CareerExamDAO cexamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
		List<CareerExam> cexams = cexamDAO.getDoneCareerExamsOfaStudent(studID);

		model.addAttribute("listCareerExams", cexams);

		return "student/visualizeCareer";
	}


	@RequestMapping(value = "visualizeStatistics", method = RequestMethod.GET)
	public String showStatistics(Model model, HttpServletRequest request) throws NullPointerException {

		Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
		model.addAttribute("studentName", loggedStudent.getFirstName()+" "+loggedStudent.getLastName());

		int studID = loggedStudent.getUserId();

		CareerExamDAO cexamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
		List<CareerExam> cexams = cexamDAO.getDoneCareerExamsOfaStudent(studID);


		//average Weighted Score computing
		double numerator = 0;
		double denominator = 0;	 	
		for(CareerExam ce : cexams)
		{
			int examCredits = ce.getExam().getCfu();	 		
			int grade = ce.getGrade();

			if(ce.isHonours()) //lode
			{
				if(examCredits > 5)
					grade += 0.3;

				else // <= 5
					grade += 0.15; 
			}	 		

			numerator += examCredits * grade;
			denominator += examCredits;	 		
		}	 	
		double averageWeightedScore = (denominator != 0) ? numerator/denominator : 0;
		double graduationBaseGrade = (averageWeightedScore * 11) / 3;
		DecimalFormat dec = new DecimalFormat("#.##");

		int integerCredits = (int) denominator;
		model.addAttribute("averageWeightedScore", dec.format(averageWeightedScore));
		model.addAttribute("earnedCredits", integerCredits);
		model.addAttribute("graduationBaseGrade", dec.format(graduationBaseGrade));


		return "student/visualizeStatistics";
	}

}
