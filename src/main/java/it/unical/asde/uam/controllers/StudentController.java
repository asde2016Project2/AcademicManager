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
            return "redirect:/logout";
        }

        model.addAttribute("pageTitle", "Student Area");
        return "student/dashboard";
    }

    @RequestMapping(value = "projection", method = RequestMethod.GET)
    public String projection(Model model, HttpServletRequest request) {

        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }

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
        DecimalFormat dec = new DecimalFormat("#.##");
        model.addAttribute("pageTitle", "Student Projection");
        model.addAttribute("avgScore", dec.format((avgScore)));
        model.addAttribute("gbg", dec.format(((avgScore * 11) / 3)));
        model.addAttribute("listCareerExam", listCareerExam);
        model.addAttribute("projectionForm", new ProjectionFormDTO());
        return "student/projection";
    }

    @RequestMapping(value = "projection", method = RequestMethod.POST)
    public String makeProjection(@Valid @ModelAttribute("projectionForm") ProjectionFormDTO projectionFormDTO, Model model, HttpServletRequest request) {

        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }

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
        DecimalFormat dec = new DecimalFormat("#.##");
        model.addAttribute("pageTitle", "Projection result");
        model.addAttribute("avgScore", dec.format(avgScore));
        model.addAttribute("gbg", dec.format(((avgScore * 11) / 3)));
        model.addAttribute("nameExams", projectionFormDTO.getNameExams());
        model.addAttribute("cfuExams", projectionFormDTO.getCfuExams());
        model.addAttribute("gradeExams", projectionFormDTO.getGradeExams());
        return "student/projectionResult";

    }

    // -------------------Exam Reservation process-------//
    @RequestMapping(value = "academicExamSessionList", method = RequestMethod.GET)   
    public String viewExamSession(Model model, HttpServletRequest request) {
         if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }
        
        model.addAttribute("pageTitle", "Academic Exam session list");
         
        ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
        List<ExamSession> listExamSession = examSessionDAO.listExamRegAppeals();
        model.addAttribute("listExamSession", listExamSession);

        return "student/academicExamSessionList";
    }
    
    int studyPlanId = 0;
    @RequestMapping(value = "academicExamSessionList/examSession/{sessionId:.+}", method = RequestMethod.GET)
    public String viewAttempt(@PathVariable("sessionId") Integer sessionId, Model model, HttpServletRequest request) throws Exception {

        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }
        model.addAttribute("pageTitle", "Exam Reservation View");
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
        ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
        model.addAttribute("examSession", examSessionDAO.getExamSessionById(sessionId));
        ExamSession examSession = new ExamSession();
        examSession = examSessionDAO.getExamSessionById(sessionId);
        
         StudyPlanExamDAO spexamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
         StudyPlan studyPlan = loggedStudent.getStudyPlan();
         System.out.println("list of studyplan id pls===="+studyPlan.getStudyPlanId());
        if (examSession != null && studyPlan.getStudyPlanId() > -1) {
             ArrayList<Exam> exams = new ArrayList<>();
             if(studyPlan.getStudyPlanId() > -1){
             List<StudyPlanExam> listStudentPlanExams = spexamDAO.getAllExamsOfAstudyPlan(studyPlan);
             ArrayList<Attempt> listOfExamReservation =null;
             for (StudyPlanExam sp : listStudentPlanExams) {
                 exams.add(sp.getExam());
                 Exam exam = sp.getExam();
                 System.out.println("list of Exam ===="+exam.getId());
                 listOfExamReservation = attemptDAO.getExamSessionToAttempt(sessionId,exam);
             }
            System.out.println("list of Exam Reservation======pls===="+listOfExamReservation.size());
            model.addAttribute("listOfExamReservation", listOfExamReservation);
             }
        }

        return "student/listExamReservationBoard";
    }
    
    //Get Values
    
    @RequestMapping(value = "list/ExamReserve", method = RequestMethod.GET)
    public String viewAttempt(Model model, HttpServletRequest request) throws NullPointerException {
    	        if (!SessionHelper.isStudent(request.getSession())) {
    	            return "redirect:/logout";
    	        }
    	        model.addAttribute("pageTitle", "Exam Board");
    	        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
    	        ArrayList<Attempt> listOfExamReservation = new ArrayList<>() ;
//    	        model.addAttribute("listOfExamReservation", listOfExamReservation);
    	        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
    	        StudyPlanExamDAO spexamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
    	        StudyPlan studyPlan = loggedStudent.getStudyPlan();
    	        System.out.println("list of studyplan id pls===="+studyPlan.getStudyPlanId());
    	       if (studyPlan.getStudyPlanId() > -1) {
    	            ArrayList<Exam> exams = new ArrayList<>();
//    	            if(studyPlan.getStudyPlanId() > -1){
    	            
    	            List<StudyPlanExam> listStudentPlanExams = spexamDAO.getAllExamsOfAstudyPlan(studyPlan);
    	           
    	            for (StudyPlanExam sp : listStudentPlanExams) {
//    	                exams.add(sp.getExam());
    	                Exam exam = sp.getExam();
    	                System.out.println("list of Exam ===="+ exam.getId());
    	                listOfExamReservation = attemptDAO.listActiveExamforAttempt(exam);
    	                System.out.println("list of Exam Reservation======pls===="+listOfExamReservation.size());
    	             }
    	            model.addAttribute("listOfExamReservation", listOfExamReservation);
    	            	
//    	           }
    	       }
    	     return  "student/listExamReservationBoard";
    
    }
    
    /**
     * select the reserved attempt
     * @param attemptId
     * @param model
     * @param request
     * @return
     * @throws NullPointerException
     */
    @RequestMapping(value = "list/ExamReserve/{attemptId}", method = RequestMethod.GET)
    public String bookAttempt(@PathVariable("attemptId") int attemptId, Model model,
    		HttpServletRequest request) throws NullPointerException {
    	        if (!SessionHelper.isStudent(request.getSession())) {
    	            return "redirect:/logout";
    	        }
    	        
    	        model.addAttribute("pageTitle", "Reserve Exam Now");
    	        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
    	        ArrayList<Attempt> attempts=attemptDAO.getAttemptByAttempt(attemptId);
    	        model.addAttribute("attempts",attempts);
    	    
    	        return  "student/reserveExam";
    
    }
    
    
    
    /**
     * Singup for Attempts
     * Reserve for final exam
     */
    @RequestMapping(value = "list/ExamReserve/{attemptId}", method = RequestMethod.POST)
    public String reserveForExam(@RequestParam(value = "attemptId") int attemptId,
            Model model, HttpServletRequest request) {

        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }

        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context
                .getBean("userAttemptRegistrationDAO");
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");

        Attempt attempt = attemptDAO.getAttemptById(attemptId);
        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());

        int signedStudent = userAttRegDAO.getUserAttemptByStudentNum().size();
        model.addAttribute("signedStudent", signedStudent);

        if (attempt != null && loggedStudent != null) {

            UserAttemptRegistration attemptRegistration2 = new UserAttemptRegistration(attempt, loggedStudent);
           

           
//            boolean saved = userAttRegDAO.register(attemptRegistration2);
//            if (!saved) {
//                model.addAttribute("error", "you already sign-up for this course");
//                
//            }
//            else {
            	  model.addAttribute("message", messageSource.getMessage("registration.ok", null, localeResolver.resolveLocale(request)));
            	  attemptRegistration2.setBooking(Booking.CANCEL);
                  attemptRegistration2.setStatus("SIGNUP");
//                attempt.setStatus("signed");
//                attemptDAO.update(attempt);
                  userAttRegDAO.create(attemptRegistration2);
   			       sendEmail.sendEmailRegistration(loggedStudent.getEmail(), loggedStudent.getFirstName(),
   					loggedStudent.getLastName(), SendEmail.SUBJECT_REQUEST_REGISTATION,
   				   SendEmail.TEXT_ACCEPTED_REGISTRATION);
          
//            }
            
        
//        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        ArrayList<Attempt> attempts=attemptDAO.getAttemptByAttempt(attemptId);
        model.addAttribute("attempts",attempts);
        }
        return "student/cancelExamBook";
    }

    
    /**
     * Cancel Reservation for final exam although it takes some parameters to
     * cancel the reservation
     */
    @RequestMapping(value = "detail/examBooking/{attemptId}", method = RequestMethod.POST, params = "cancel")
    public String cancelReservation(@RequestParam(value = "attemptId") int attemptId,
            @RequestParam(value = "cancel") String status, Model model, HttpServletRequest request) {

        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }

        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context
                .getBean("userAttemptRegistrationDAO");
        UserAttemptRegistration attemptRegistration = userAttRegDAO.getUserAttemptRegByAttemptId(attemptId);
        // model.addAttribute("userAttemptRegistration", result);

        Attempt attempt = attemptRegistration.getAttempt();
        System.out.println("attempt=====" + attempt.getAttemptId());

        attemptRegistration = new UserAttemptRegistration(attempt, loggedStudent);

        if (attemptRegistration != null) {
            userAttRegDAO.delete(attemptRegistration);

			sendEmail.sendEmailRegistration(loggedStudent.getEmail(), loggedStudent.getFirstName(),
					loggedStudent.getLastName(), SendEmail.SUBJECT_REQUEST_REGISTATION,
					SendEmail.TEXT_NOT_ACCEPTED_REGISTRATION);
        }

        ArrayList<UserAttemptRegistration> listStudentBooked = userAttRegDAO
                .getUserAttemptByStudentUserNames(loggedStudent);
        model.addAttribute("listStudentBooked", listStudentBooked);

        return "student/cancelExamBook";

    }

    /**
     * When the student login they will find the signup exam sessions Exam
     * reserved by students
     */
    @RequestMapping(value = "cancelExamBook", method = RequestMethod.GET)
    public String examReservedByStudent(Model model, HttpServletRequest request) {

        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }

        model.addAttribute("pageTitle", "View Reserved Attempt");

        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context
                .getBean("userAttemptRegistrationDAO");
        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());

        if (loggedStudent != null) {
            UserAttemptRegistration attemptRegistration = new UserAttemptRegistration();
            attemptRegistration = userAttRegDAO.getUserAttemptByStudentById(loggedStudent);
            if (attemptRegistration != null) {
                model.addAttribute("examName", attemptRegistration.getAttempt().getExam().getName());
                model.addAttribute("startDate", attemptRegistration.getAttempt().getStartRegistrationDate());
                model.addAttribute("accademicSession",
                        attemptRegistration.getAttempt().getExamSession().getAcademicYear());

                String studFullName = loggedStudent.getFirstName() + " " + loggedStudent.getLastName();
                model.addAttribute("studFullName", studFullName);
                System.out.println("user userIds----" + loggedStudent.getUserId());
            }

        }
        ArrayList<UserAttemptRegistration> listStudentBooked = userAttRegDAO
                .getUserAttemptByStudentUserNames(loggedStudent);
        if(!listStudentBooked.isEmpty()){
        model.addAttribute("listStudentBooked", listStudentBooked);
        System.out.println("user booked----" + listStudentBooked.size());
        }
        return "student/cancelExamBook";
    }

    /*
	 * Cancel after the student loging to the system
     */
    @RequestMapping(value = "cancelExamBook", method = RequestMethod.POST)
    public String cancelSignup(Model model, HttpServletRequest request) {

        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }

        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context
                .getBean("userAttemptRegistrationDAO");
        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        UserAttemptRegistration attemptRegistration = new UserAttemptRegistration();
        attemptRegistration = userAttRegDAO.getUserAttemptByStudentById(loggedStudent);
        if (attemptRegistration != null) {
            model.addAttribute("examName", attemptRegistration.getAttempt().getExam().getName());
            model.addAttribute("startDate", attemptRegistration.getAttempt().getStartRegistrationDate());
            model.addAttribute("accademicSession",
                    attemptRegistration.getAttempt().getExamSession().getAcademicYear());

            userAttRegDAO.delete(attemptRegistration);
//            Attempt attempt = attemptRegistration.getAttempt();
//            attempt.setStatus("active");
//            attemptDAO.update(attempt);

			sendEmail.sendEmailRegistration(loggedStudent.getEmail(), loggedStudent.getFirstName(),
					loggedStudent.getLastName(), SendEmail.SUBJECT_EXAM_BOOKING,
					SendEmail.EXAM_SESSION_ATTEMPT_CANCELED);
            model.addAttribute("recordDeleted", "The Booked Exam Canceled");
        }

        ArrayList<UserAttemptRegistration> listStudentBooked = userAttRegDAO
                .getUserAttemptByStudentUserNames(loggedStudent);
       
        
        if(listStudentBooked.size() > -1){
        	 model.addAttribute("listStudentBooked", listStudentBooked);
            System.out.println("user booked----" + listStudentBooked.size());
            }

        return "student/listExamReservationBoard";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String showRegisterForm(HttpServletRequest request, Model model) {

        model.addAttribute("pageTitle", "Student Register");

        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
        model.addAttribute("studentForm", new StudentFormDTO());
        model.addAttribute("studyPlanList", studyPlanDAO.getAllPlans());

        return "student/register";

    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("studentForm") @Valid StudentFormDTO studentFormDTO, BindingResult result,
            HttpServletRequest request, Model model) {

        model.addAttribute("pageTitle", "Student Register");
        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
        System.out.println("studentHasError: " + result.hasErrors());
        if (result.hasErrors()) {
            model.addAttribute("studyPlanList", studyPlanDAO.getAllPlans());

            return "student/register";
        }

        StudentDAO studentDao = (StudentDAO) context.getBean("studentDAO");

        StudyPlan sp = studyPlanDAO.retrieve(Integer.parseInt(studentFormDTO.getStudyPlanId()));
        Student student = new Student(studentFormDTO.getUsername(), studentFormDTO.getPassword(),
                studentFormDTO.getFirstName(), studentFormDTO.getLastName(), true, sp);
        // status is set to true
        student.setAge(studentFormDTO.getAge());
        student.setEmail(studentFormDTO.getEmail());
        student.setDateOfBirth(studentFormDTO.getDateOfBirth());

        // Set to false in production or add a default value "false" for this
        // field
        // student.setStatus(true);
        boolean saved = studentDao.register(student);
        if (!saved) {
            model.addAttribute("error", "Username or email already taken");
            return "student/register";
        }
        else {
            model.addAttribute("message",
                    messageSource.getMessage("registration.ok", null, localeResolver.resolveLocale(request)));
            // we clean the model passed to view
            model.addAttribute("studentForm", new StudentFormDTO());
            model.addAttribute("studyPlanList", studyPlanDAO.getAllPlans());
            return "student/register";
        }

    }

    @RequestMapping(value = "visualizeStudyPlan", method = RequestMethod.GET)
    public String listStudyPlanExams(Model model, HttpServletRequest request) throws NullPointerException {

        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }

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

        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }

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
        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/logout";
        }

        Student loggedStudent = SessionHelper.getUserStudentLogged(request.getSession());
        model.addAttribute("studentName", loggedStudent.getFirstName() + " " + loggedStudent.getLastName());

        int studID = loggedStudent.getUserId();

        CareerExamDAO cexamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        List<CareerExam> cexams = cexamDAO.getDoneCareerExamsOfaStudent(studID);

        // average Weighted Score computing
        double numerator = 0;
        double denominator = 0;
        for (CareerExam ce : cexams) {
            int examCredits = ce.getExam().getCfu();
            int grade = ce.getGrade();

            if (ce.isHonours()) // lode
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

}
