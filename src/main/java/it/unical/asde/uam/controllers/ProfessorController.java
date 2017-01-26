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
import org.springframework.web.bind.annotation.PathVariable;
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

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        model.addAttribute("pageTitle", "Professor Area");
        Professor loggedProfessor = SessionHelper.getUserProfessorLogged(request.getSession());
        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
        ArrayList<UserAttemptRegistration> listStudentExamSignup = userAttRegDAO.getStudentSignupProfExamSession(loggedProfessor);
        if(listStudentExamSignup.size() > -1){
        	model.addAttribute("numberStudents", listStudentExamSignup.size());
        }
        return "professor/dashboard";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String showRegisterForm(HttpServletRequest request, Model model) {

        model.addAttribute("pageTitle", "Professor Register");

        Professor p = new Professor();
        model.addAttribute("professor", p);

        return "professor/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("professor") @Valid Professor professor, BindingResult result, HttpServletRequest request, Model model) {

        model.addAttribute("pageTitle", "Professor Register");

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
            else {
                model.addAttribute("message", messageSource.getMessage("registration.ok", null, localeResolver.resolveLocale(request)));
                //we clean the model passed to view
                model.addAttribute("professor", new Professor());
                return "professor/register";
            }

        }

        return "professor/register";
    }

    @RequestMapping(value = "listSession", method = RequestMethod.GET)
    public String viewAllSession(Model model, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        ExamSessionDAO examSessionDao = (ExamSessionDAO) context.getBean("examSessionDAO");
        ArrayList<ExamSession> allExamSessions = (ArrayList<ExamSession>) examSessionDao.getAllExamSession();//.listAllSession();
        model.addAttribute("lista", allExamSessions);
        return "professor/listSession";
    }

    @RequestMapping(value = "listAttempt", method = RequestMethod.GET)
    public String viewAllAttempts(Model model, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        Professor p = SessionHelper.getUserProfessorLogged(request.getSession());
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        model.addAttribute("attemptList", attemptDAO.getAttemptByProfessor(p));
        return "professor/listAttempt";
    }

    @RequestMapping(value = "studentExtraExamSession", method = RequestMethod.GET)
    public String viewStudentForExtraExamSession(Model model, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        ArrayList<Student> students = studentDAO.getStudentForStraordinaryExamSession(studentDAO.getAllStudents());
        if (students.size() == 0) {
            model.addAttribute("error", "There are no students that can partecipate to extra exam session");
        }
        else {
            model.addAttribute("listaStudenti", students);
        }
        return "professor/listStudent";
    }

    @RequestMapping(value = "informationStudent", method = RequestMethod.GET)
    public String getInformationStudent(Model model, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        return "professor/informationStudent";
    }

    @RequestMapping(value = "informationStudent", method = RequestMethod.POST)
    public String getInformationStudentByUsername(Model model, @ModelAttribute("username") String username, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        ArrayList<CareerExam> students = studentDAO.getInformationStudent(username);
        Student stud = studentDAO.retrieve(username);
        System.out.println("studdddd size info: " + students.size());
        model.addAttribute("infoStudent", students);
        model.addAttribute("student", stud);
        return "professor/informationStudent";
    }

    String examName = "";
    List<UserAttemptRegistration> uar = new ArrayList<>();

    @RequestMapping(value = "registerExam", method = RequestMethod.GET)
    public String getRegisterExam(Model model, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        Professor p = SessionHelper.getUserProfessorLogged(request.getSession());
        ArrayList<Attempt> attemptId = attemptDAO.getAttemptByProfessor(p);
        
        ArrayList<Exam> exams = new ArrayList<>();
        for(int  i = 0; i < attemptId.size(); i++) {
        	exams.add(attemptId.get(i).getExam());
        }
        model.addAttribute("userar", uar);
        model.addAttribute("exams", exams);
        if (!(examName.equals(""))) {
        	System.out.println("percheeeeeeeeeeee");
            doRegisterExam(model, examName, request);
        }
        return "professor/registerExam";
    }

    @RequestMapping(value = "registerExam", method = RequestMethod.POST)
    public String doRegisterExam(Model model, @ModelAttribute("examname") String examname,
            HttpServletRequest request) {

    	examName = "";
        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }
        if (examname.equals("Select an Exam") || examname.equals(null)) {
        	examName = "";
        	return "redirect:/professor/registerExam";
        }
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        Exam e = examDAO.getExamByName((examname));
        examName = examname;//.getName();

        Professor p = SessionHelper.getUserProfessorLogged(request.getSession());
        int attemptId = attemptDAO.getAttemptByProfessorByExam(p, e);

        UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
        uar = userAttemptRegistrationDAO.getUserAttemptRegistrationByAttempId(attemptId);
        model.addAttribute("examName", examname);
        model.addAttribute("userar", uar);

        return "redirect:/professor/registerExam";
    }


    @RequestMapping(value = "addCareerExam", method = RequestMethod.POST)
    public String addCareerExam(Model model, @ModelAttribute("grade") int grade,
            @RequestParam("studentUsername") String studUsername2,
            @RequestParam("attemptId") int attemptId, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        CareerExam c = new CareerExam();

        if (grade >= 18) {
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
    public String createNewAttempt(Model model, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

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

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

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

        if (attemptDAO.checkAttempt(startingDate, endingDate, examDate, examSessionStart, examSessionEnd)) {
            Attempt a = new Attempt(examDate, classRoom, startingDate, endingDate,
                    SessionHelper.getUserProfessorLogged(request.getSession()), examDAO.getExamByName(exam), examSessionDAO.getExamSessionById(examSessionId));
            a.setStatus("active");
            attemptDAO.create(a);
        }
        else {            
            model.addAttribute("examSessions", examSessionDAO.getAllExamSession());            
            model.addAttribute("exams", examDAO.getAllExams());
            model.addAttribute("error", "The dates or academic year are not ok");
            return "professor/createAttempt";
        }
        return "redirect:/professor/listAttempt";
    }
    
    
    /**
     * View students sign-up for exam session if the student meets the basic
     * requirement of professors list it will accepted to take the exam
     * otherwise it will rejected from the exam sessions
     */
    @RequestMapping(value = "viewStudentExamSignup", method = RequestMethod.GET)
    public String acceptStudentSignupForExamSession(Model model, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        Professor loggedProfessor = SessionHelper.getUserProfessorLogged(request.getSession());
        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
        ArrayList<UserAttemptRegistration> listStudentExamSignup = null;
        if (loggedProfessor != null) {
            listStudentExamSignup = userAttRegDAO.getStudentSignupProfExamSession(loggedProfessor);
            model.addAttribute("listStudentExamSignup", listStudentExamSignup);
        }
        else {
            model.addAttribute("listStudentExamSignup", listStudentExamSignup);
        }

        return "professor/viewStudentExamSignup";
    }


   

    @RequestMapping(value = "viewStudentExamSignup", method = RequestMethod.POST)
    public String acceptStudentSignupForExamSession(@RequestParam(value="userAtRegId") int userAtRegId,
    		Model model, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        Professor loggedProfessor = SessionHelper.getUserProfessorLogged(request.getSession());
        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
        UserAttemptRegistration userAttReg = userAttRegDAO.getUserAttemptByProfessorUserName(loggedProfessor);
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        UserAttemptRegistration attemptRegsitration = userAttRegDAO.getUserAttemptRegById(userAtRegId);
        System.out.println("user id cheking in refuse"+attemptRegsitration.getUserAtRegId());
        if (userAttReg !=null) {
        	userAttReg.setBooking(Booking.SIGNUP);
            userAttRegDAO.update(userAttReg);
//           Attempt attempt =attemptRegsitration.getAttempt();
//           attempt.setStatus("active");
//           attemptDAO.update(attempt);

            sendEmail.sendEmailRegistration(userAttReg.getStudent().getEmail(), userAttReg.getStudent().getFirstName(), userAttReg.getStudent().getLastName(),
                    SendEmail.SUBJECT_EXAM_BOOKING, SendEmail.EXAM_SESSION_ATTEMPT_ACCEPTED);

        }
        ArrayList<UserAttemptRegistration> listStudentExamSignup = userAttRegDAO.getStudentSignupProfExamSession(loggedProfessor);
        if(listStudentExamSignup.size() > -1){
        	model.addAttribute("listStudentExamSignup", listStudentExamSignup);
        }else{
        	model.addAttribute("norecord","there is no record found");
        }
        return "professor/viewStudentExamSignup";
    }


  
    
    @RequestMapping(value = "viewStudentExamSignup", method = RequestMethod.POST, params="refuse")
    public String rejectSignupExam(@RequestParam(value="refuse") int userAtRegId, Model model, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        Professor loggedProfessor = SessionHelper.getUserProfessorLogged(request.getSession());
        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
        UserAttemptRegistration userAttReg = userAttRegDAO.getUserAttemptByProfessorUserName(loggedProfessor);
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        UserAttemptRegistration attemptRegsitration = userAttRegDAO.getUserAttemptRegById(userAtRegId);
        System.out.println("user id cheking in refuse"+attemptRegsitration.getUserAtRegId());
        if (userAttReg !=null) {
           
           userAttRegDAO.delete(userAttReg);
//           Attempt attempt =attemptRegsitration.getAttempt();
//           attempt.setStatus("active");
//           attemptDAO.update(attempt);

            sendEmail.sendEmailRegistration(userAttReg.getStudent().getEmail(), userAttReg.getStudent().getFirstName(), userAttReg.getStudent().getLastName(),
                    SendEmail.SUBJECT_EXAM_BOOKING, SendEmail.EXAM_SESSION_ATTEMPT_CANCELED);

        }
        ArrayList<UserAttemptRegistration> listStudentExamSignup = userAttRegDAO.getStudentSignupProfExamSession(loggedProfessor);
        if(listStudentExamSignup.size() > -1){
        	model.addAttribute("listStudentExamSignup", listStudentExamSignup);
        }else{
        	model.addAttribute("norecord","there is no record found");
        }
        return "professor/viewStudentExamSignup";
    }
    
    
    
    /**
     * View students sign-up for exam session if the student meets the basic
     * requirement of professors list it will accepted to take the exam
     * otherwise it will rejected from the exam sessions
     */
    @RequestMapping(value = "viewBookedStudent", method = RequestMethod.GET)
    public String rejectSignupExam(Model model, HttpServletRequest request) {

        if (!SessionHelper.isProfessor(request.getSession())) {
            return "redirect:/logout";
        }

        Professor loggedProfessor = SessionHelper.getUserProfessorLogged(request.getSession());
        UserAttemptRegistrationDAO userAttRegDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");
        ArrayList<UserAttemptRegistration> listStudentExamSignup = null;
        if (loggedProfessor != null) {
            listStudentExamSignup = userAttRegDAO.getUserAttemptByProfessor(loggedProfessor);
            model.addAttribute("listStudentExamSignup", listStudentExamSignup);
        }
//        else {
//            model.addAttribute("listStudentExamSignup", listStudentExamSignup);
//        }

        return "professor/viewBookedStudent";
    }

}
