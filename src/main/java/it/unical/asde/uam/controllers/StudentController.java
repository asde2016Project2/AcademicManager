package it.unical.asde.uam.controllers;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.util.Iterator;

import java.util.List;
import java.util.Locale;

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

import it.unical.asde.uam.dto.StudentFormDTO;
import it.unical.asde.uam.dto.StudyPlanFormDTO;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.DegreeCourse;

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

    @RequestMapping(value = "projection", method = RequestMethod.GET)
    public String projection(Model model, HttpServletRequest request) {
        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
        CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        List<CareerExam> listCareerExam = (List<CareerExam>) careerExamDAO.getCareerExamsOfaStudent(loggedStudent.getUserId());
        double avgScore = 0;
        int cfuDone = 0;
        for (CareerExam careerExam : listCareerExam) {
            if (careerExam.isDone()) {
                avgScore += (careerExam.getGrade() * careerExam.getExam().getCfu());
                cfuDone += careerExam.getExam().getCfu();
            }
        }
        if (cfuDone != 0) {
            avgScore /= cfuDone;
        }
        model.addAttribute("pageTitle", "Student Projection");
        model.addAttribute("avgScore", avgScore);
        model.addAttribute("gbg",String.format( "%.2f",((avgScore * 11) / 3)));
        model.addAttribute("listCareerExam", listCareerExam);
        model.addAttribute("projectionForm", new ProjectionFormDTO());
        return "student/projection";
    }

    @RequestMapping(value = "projection", method = RequestMethod.POST)
    public String makeProjection(@Valid @ModelAttribute("projectionForm") ProjectionFormDTO projectionFormDTO, Model model, HttpServletRequest request) {
        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
        double avgScore = 0;
        int cfuDone = 0;
        for (int i = 0; i < projectionFormDTO.getGradeExams().size(); i++) {
            if (!projectionFormDTO.getGradeExams().get(i).equals("")) {
                avgScore += (Integer.parseInt(projectionFormDTO.getGradeExams().get(i))
                        * Integer.parseInt(projectionFormDTO.getCfuExams().get(i)));
                cfuDone += Integer.parseInt(projectionFormDTO.getCfuExams().get(i));
            }
        }
        avgScore /= cfuDone;
        model.addAttribute("pageTitle", "Projection result");
        model.addAttribute("avgScore", avgScore);
        model.addAttribute("gbg",String.format( "%.2f",((avgScore * 11) / 3)));
        model.addAttribute("nameExams", projectionFormDTO.getNameExams());
        model.addAttribute("cfuExams", projectionFormDTO.getCfuExams());
        model.addAttribute("gradeExams", projectionFormDTO.getGradeExams());
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

        }
        else {
            return "redirect:/";
        }

        return "student/listExamReservationBoard";
    }

    @RequestMapping(value = "list/ExamReserve", method = RequestMethod.GET)
    public String viewAttempt(Model model) throws NullPointerException {
        model.addAttribute("pageTitle", "Exam Board");
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        ArrayList<Attempt> listOfExamReservation = attemptDAO.listActiveExamforAttempt();
        ArrayList<Professor> professor = new ArrayList<>();

        System.out.println("ExamBoardInformaiton- return list of information-");
        for (Attempt attempt : listOfExamReservation) {
            professor.add(attempt.getProfessor());
        }

        // model.addAttribute("listOfExamReservation", exams);
        model.addAttribute("listOfExamReservation", listOfExamReservation);

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
        ArrayList<UserAttemptRegistration> userAttemptRegistrations = new ArrayList<>();
//		
        if (attempts.size() > 0) {

            model.addAttribute("attempts", attempts);
            model.addAttribute("attemptId", attempt.getAttemptId());
//	    	model.addAttribute("username", attemptRegistration.getStudent().getUsername());
            model.addAttribute("examBookingForm", new ExamReserveFormDTO());
            model.addAttribute("userAttRegId", attempt.getAttemptId());
        }
        else {
            return "redirect:/student/listExamReservationBoard";
        }

//		
        return "student/reserveExam";
    }

    /**
     * Reserve for final exam
     */
    @RequestMapping(value = "detail/examBooking/{userAttRegId}", method = RequestMethod.POST, params = "signup")
    public String reserveForExam(@RequestParam(value = "attemptId") int attemptId,
            @RequestParam(value = "signup") String status, Model model, HttpServletRequest request) {
        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");

        Attempt attempt = attemptDAO.getAttemptById(attemptId);
        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());

        int signedStudent = userAttRegDAO.getUserAttemptByStudentNum().size();
        model.addAttribute("signedStudent", signedStudent);

        if (attempt != null && loggedStudent != null) {
            UserAttemptRegistration attemptRegistration2 = new UserAttemptRegistration(attempt, loggedStudent);
            attemptRegistration2.setBooking(Booking.SIGNUP);
            attemptRegistration2.setStatus("SIGNUP");
            userAttRegDAO.create(attemptRegistration2);
            model.addAttribute("examName", attempt.getExam().getName());
            model.addAttribute("startDate", attempt.getStartRegistrationDate());
            model.addAttribute("accademicSession", attempt.getExamSession().getAcademicYear());
            String studFullName = loggedStudent.getFirstName() + " " + loggedStudent.getLastName();
            model.addAttribute("studFullName", studFullName);
            System.out.println("//////////////////////" + attemptRegistration2);

            sendEmail.sendEmailRegistration(loggedStudent.getEmail(), loggedStudent.getFirstName(),
                    loggedStudent.getLastName(), SendEmail.SUBJECT_EXAM_BOOKING, SendEmail.EXAM_SESSION_ATTEMPT_SIGNUP);
            int userId = loggedStudent.getUserId();
            System.out.println("userId====logger=" + userId);

            ArrayList<UserAttemptRegistration> listStudentBooked = userAttRegDAO.getUserAttemptByStudentUserNames(userId);
            model.addAttribute("listStudentBooked", listStudentBooked);
        }
        return "student/resultReserveExam";
    }

    /**
     * Cancel Reservation for final exam although it takes some parameters to
     * cancel the reservation
     */
    @RequestMapping(value = "detail/examBooking/{userAttRegId}", method = RequestMethod.POST, params = "cancel")
    public String cancelReservation(@RequestParam(value = "userAtRegId") int userAtRegId, @RequestParam(value = "cancel") String status,
            Model model, HttpServletRequest request) {

        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");

        System.out.println("userAtRegId====" + userAtRegId);

        UserAttemptRegistration result = userAttRegDAO.getUserAttemptRegById(userAtRegId);
//		model.addAttribute("userAttemptRegistration", result);
        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
        System.out.println("result======" + result.getStudent());
        Attempt attempt = new Attempt();
        attempt = result.getAttempt();
        UserAttemptRegistration attemptRegistration2 = new UserAttemptRegistration(attempt, loggedStudent);
        attemptRegistration2.setBooking(Booking.CANCEL);
        attemptRegistration2.setStatus("Cancel");
        System.out.println("//////////////////////" + attemptRegistration2);
        sendEmail.sendEmailRegistration(loggedStudent.getEmail(), loggedStudent.getFirstName(), loggedStudent.getLastName(),
                SendEmail.SUBJECT_EXAM_BOOKING, SendEmail.EXAM_SESSION_ATTEMPT_CANCELED);
        userAttRegDAO.delete(result);

        ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
        List<ExamSession> listExamSession = examSessionDAO.listExamRegAppeals();
        model.addAttribute("listExamSession", listExamSession);

        return "student/listExamReservationBoard";
    }

    /**
     * When the student login they will find the signup exam sessions Exam
     * reserved by students
     */
    @RequestMapping(value = "cancelExamBook", method = RequestMethod.GET)
    public String examReservedByStudent(Model model, HttpServletRequest request) {
        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
        int userIds = loggedStudent.getUserId();
        if (userIds > 0) {
            UserAttemptRegistration attemptRegistration = new UserAttemptRegistration();
            attemptRegistration = userAttRegDAO.getUserAttemptByStudentUserName(userIds);
            if (attemptRegistration != null) {
                model.addAttribute("examName", attemptRegistration.getAttempt().getExam().getName());
                model.addAttribute("startDate", attemptRegistration.getAttempt().getStartRegistrationDate());
                model.addAttribute("accademicSession", attemptRegistration.getAttempt().getExamSession().getAcademicYear());

                String studFullName = loggedStudent.getFirstName() + " " + loggedStudent.getLastName();
                model.addAttribute("studFullName", studFullName);
                System.out.println("user userIds----" + userIds);

                ArrayList<UserAttemptRegistration> listStudentBooked = userAttRegDAO.getUserAttemptByStudentUserNames(userIds);
                model.addAttribute("listStudentBooked", listStudentBooked);
                System.out.println("user booked----" + listStudentBooked.size());
            }

        }
        return "student/cancelExamBook";
    }

    @RequestMapping(value = "cancelExamBook", method = RequestMethod.POST)
    public String cancelSignup(Model model, HttpServletRequest request) {
        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
        int userIds = loggedStudent.getUserId();
        UserAttemptRegistration attemptRegistration = new UserAttemptRegistration();
        attemptRegistration = userAttRegDAO.getUserAttemptByStudentUserName(userIds);
        System.out.println("//////////////////////" + attemptRegistration);
        sendEmail.sendEmailRegistration(loggedStudent.getEmail(), loggedStudent.getFirstName(), loggedStudent.getLastName(),
                SendEmail.SUBJECT_EXAM_BOOKING, SendEmail.EXAM_SESSION_ATTEMPT_CANCELED);
        userAttRegDAO.delete(attemptRegistration);

        ArrayList<UserAttemptRegistration> listStudentBooked = userAttRegDAO.getUserAttemptByStudentUserNames(userIds);
        model.addAttribute("listStudentBooked", listStudentBooked);
        System.out.println("user booked----" + listStudentBooked.size());
        return "student/listExamReservationBoard";
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
    public String doRegister(@ModelAttribute("studentForm")
            @Valid StudentFormDTO studentFormDTO, BindingResult result, HttpServletRequest request, Model model) {

        model.addAttribute("pageTitle", "Student Register");
        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
        System.out.println("studentHasError: " + result.hasErrors());
        if (result.hasErrors()) {
            model.addAttribute("studyPlanList", studyPlanDAO.getAllPlans());

            return "student/register";
        }

        StudentDAO studentDao = (StudentDAO) context.getBean("studentDAO");

        StudyPlan sp = studyPlanDAO.retrieve(Integer.parseInt(studentFormDTO.getStudyPlanId()));
        Student student = new Student(studentFormDTO.getUsername(), studentFormDTO.getPassword(), studentFormDTO.getFirstName(), studentFormDTO.getLastName(), true, sp);
        //status is set to true
        student.setAge(studentFormDTO.getAge());
        student.setEmail(studentFormDTO.getEmail());
        student.setDateOfBirth(studentFormDTO.getDateOfBirth());

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
        model.addAttribute("studentName", loggedStudent.getFirstName() + " " + loggedStudent.getLastName());

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
        model.addAttribute("studentName", loggedStudent.getFirstName() + " " + loggedStudent.getLastName());

        int studID = loggedStudent.getUserId();

        CareerExamDAO cexamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        List<CareerExam> cexams = cexamDAO.getDoneCareerExamsOfaStudent(studID);

        model.addAttribute("listCareerExams", cexams);

        return "student/visualizeCareer";
    }

    @RequestMapping(value = "visualizeStatistics", method = RequestMethod.GET)
    public String listStatistics(Model model, HttpServletRequest request) throws NullPointerException {

        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
        model.addAttribute("studentName", loggedStudent.getFirstName() + " " + loggedStudent.getLastName());

        int studID = loggedStudent.getUserId();

        CareerExamDAO cexamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        List<CareerExam> cexams = cexamDAO.getDoneCareerExamsOfaStudent(studID);

        //average Weighted Score computing
        double numerator = 0;
        double denominator = 0;
        for (CareerExam ce : cexams) {
            int examCredits = ce.getExam().getCfu();
            int grade = ce.getGrade();

            if (ce.isHonours()) //lode
            {
                if (examCredits > 5) {
                    grade += 0.3;
                }

                else // <= 5
                {
                    grade += 0.15;
                }
            }

            numerator += examCredits * grade;
            denominator += examCredits;
        }
        double averageWeightedScore = (denominator != 0) ? numerator / denominator : 0;
        double graduationBaseGrade = (averageWeightedScore * 11) / 3;
        DecimalFormat dec = new DecimalFormat("#.##");

        int integerCredits = (int) denominator;
        model.addAttribute("averageWeightedScore", dec.format(averageWeightedScore));
        model.addAttribute("earnedCredits", integerCredits);
        model.addAttribute("graduationBaseGrade", dec.format(graduationBaseGrade));

        return "student/visualizeStatistics";
    }

    //details for the student in registration phase
    @RequestMapping(value = "showStudyPlans", method = RequestMethod.GET)
    public String showStudyPlans(Model model, HttpServletRequest request) throws NullPointerException {

        StudyPlanDAO spDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");

        model.addAttribute("pageTitle", "Available Study Plans");
        model.addAttribute("studyPlans", spDAO.getAllPlans());

        return "student/showStudyPlans";
    }

    //details for the student in registration phase
    @RequestMapping(value = "details/studyplan/{id}", method = RequestMethod.GET)
    public String showDetailStudyPlan(@PathVariable("id") int id, Model model) {

        StudyPlanDAO spDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");

        StudyPlan studyPlan = spDAO.retrieve(id);
        model.addAttribute("studyPlanName", studyPlan.getName());

        StudyPlanExamDAO spexamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
        List<StudyPlanExam> spexams = spexamDAO.getAllExamsOfAstudyPlan(studyPlan);

        model.addAttribute("listStudyPlanExams", spexams);

        return "student/showStudyPlanExams";
    }

}
