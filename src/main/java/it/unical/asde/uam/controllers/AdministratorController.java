package it.unical.asde.uam.controllers;

import it.unical.asde.uam.model.Administrator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.dto.SessionFormDTO;
import it.unical.asde.uam.helper.Accepted;
import it.unical.asde.uam.helper.SessionHelper;

import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.model.AcceptingStudentFormDTO;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;

import it.unical.asde.uam.dto.StudyPlanFormDTO;
import it.unical.asde.uam.persistence.AttemptDAO;

import it.unical.asde.uam.model.SendEmail;
import it.unical.asde.uam.persistence.AdministratorDAO;
import it.unical.asde.uam.persistence.CareerExamDAO;
import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.ExamSessionDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;
import it.unical.asde.uam.persistence.StudentDAO;
import it.unical.asde.uam.persistence.StudyPlanDAO;
import it.unical.asde.uam.persistence.StudyPlanExamDAO;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fabrizio
 *
 */
@Controller
@RequestMapping("/admin")
public class AdministratorController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);

    @Autowired
    SendEmail sendEmail;

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String showDashboad(HttpServletRequest request, Model model) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        AdministratorDAO administratorDAO = (AdministratorDAO) context.getBean("administratorDAO");
        model.addAttribute("numberAdmins", administratorDAO.getAllAdminsToAcceptRefuse().size());
        model.addAttribute("numberStudents", studentDAO.getAllStudentsToAcceptRefuse().size());
        model.addAttribute("numberProfessors", professorDAO.geAllProfessorsToAcceptRefuse().size());
        model.addAttribute("pageTitle", "Admin Dashboard");
        return "admin/dashboard";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String showRegisterForm(HttpServletRequest request, Model model) {

        model.addAttribute("pageTitle", "Admin Register");

        Administrator a = new Administrator();
        model.addAttribute("administrator", a);

        return "admin/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("administrator") @Valid Administrator admin, BindingResult result, HttpServletRequest request, Model model) {

        model.addAttribute("pageTitle", "Admin Register");

        AdministratorDAO adminDao = (AdministratorDAO) context.getBean("administratorDAO");

        System.out.println("adminHasError: " + result.hasErrors());
        if (!result.hasErrors()) {
            //Set to false in production or add a default value "false" for this field
            admin.setStatus(true);
            boolean saved = adminDao.register(admin);
            if (!saved) {
                model.addAttribute("error", "Username or email already taken");
                return "admin/register";
            }
            else {
                model.addAttribute("message", messageSource.getMessage("registration.ok", null, localeResolver.resolveLocale(request)));
                //we clean the model passed to view
                model.addAttribute("administrator", new Administrator());
                return "admin/register";
            }

        }

        return "admin/register";
    }

    @RequestMapping(value = "list/studyplan", method = RequestMethod.GET)
    public String showListStudyPlan(Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        model.addAttribute("pageTitle", "List all Study Plan");
        model.addAttribute("studyPlans", ((StudyPlanDAO) context.getBean("studyPlanDAO")).getAllPlans());
        return "admin/list_studyplans";
    }

    @RequestMapping(value = "detail/studyplan/{id}", method = RequestMethod.GET)
    public String showDetailStudyPlan(@PathVariable("id") int id, Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        model.addAttribute("pageTitle", "Study Plan Detail");
        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
        StudyPlan studyPlan;
        ArrayList<StudyPlanExam> listStudyPlanExams = new ArrayList<>();
        ArrayList<Exam> exams = new ArrayList<>();
        if ((studyPlan = studyPlanDAO.retrieve(id)) != null) {
            StudyPlanExamDAO studyPlanExamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
            listStudyPlanExams = (ArrayList<StudyPlanExam>) studyPlanExamDAO.getAllExamsOfAstudyPlan(studyPlan);
            for (StudyPlanExam sp : listStudyPlanExams) {
                exams.add(sp.getExam());
            }
        }
        else {
            return "redirect:/";
        }
        if (!listStudyPlanExams.isEmpty() && !exams.isEmpty()) {
            System.out.println("Adding exams to model");
            model.addAttribute("exams", exams);
        }
        System.out.println(listStudyPlanExams.size());
        System.out.println(exams.size());
        return "admin/detail_studyplan";
    }

    @RequestMapping(value = "create/studyplan", method = RequestMethod.GET)
    public String showCreateStudyPlan(Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        model.addAttribute("pageTitle", "Create Study Plan");
        model.addAttribute("studyPlanForm", new StudyPlanFormDTO());
        model.addAttribute("degreeCourseList", ((DegreeCourseDAO) context.getBean("degreeCourseDAO")).getAllDegrees());
        model.addAttribute("examList", ((ExamDAO) context.getBean("examDAO")).getAllExams());
        return "admin/create_studyplan";
    }

    @RequestMapping(value = "create/studyplan", method = RequestMethod.POST)
    public String doCreateStudyPlan(@Valid @ModelAttribute("studyPlanForm") StudyPlanFormDTO studyPlanFormDTO, BindingResult result, HttpServletRequest request, Model model) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Create Study Plan");
            model.addAttribute("degreeCourseList", ((DegreeCourseDAO) context.getBean("degreeCourseDAO")).getAllDegrees());
            model.addAttribute("examList", ((ExamDAO) context.getBean("examDAO")).getAllExams());
            return "admin/create_studyplan";
        }

        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
        DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        StudyPlanExamDAO studyPlanExamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        DegreeCourse degreeCourse = degreeCourseDAO.retrieveById(Integer.parseInt(studyPlanFormDTO.getDegreeCourseId()));
        StudyPlan studyPlan = new StudyPlan(studyPlanFormDTO.getName(), degreeCourse);
        studyPlanDAO.create(studyPlan);

        ArrayList<String> examList = studyPlanFormDTO.getExamList();
        for (String examId : examList) {
            if (examId.matches("^\\d+$")) {
                Exam e = examDAO.getExamById(Integer.parseInt(examId));
                StudyPlanExam spe = new StudyPlanExam(studyPlan, e, "period");
                studyPlanExamDAO.create(spe);
            }
        }

        model.addAttribute("studyPlans", ((StudyPlanDAO) context.getBean("studyPlanDAO")).getAllPlans());
        return "redirect:/admin/list/studyplan";

    }

    @RequestMapping(value = "registrationStudent", method = RequestMethod.GET)
    public String registationStudent(Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
        model.addAttribute("listStudents", listStudents);
        return "admin/registrationStudent";
    }

    @RequestMapping(value = "registrationProfessor", method = RequestMethod.GET)
    public String registationProfessor(Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        List<Professor> listProfessors = professorDAO.geAllProfessorsToAcceptRefuse();
        model.addAttribute("listProfessors", listProfessors);
        return "admin/registrationProfessor";
    }

    @RequestMapping(value = "registrationAdmin", method = RequestMethod.GET)
    public String registationAdmin(Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        AdministratorDAO administratorDAO = (AdministratorDAO) context.getBean("administratorDAO");
        List<Administrator> listAdministrators = administratorDAO.getAllAdminsToAcceptRefuse();
        model.addAttribute("listAdministrators", listAdministrators);
        return "admin/registrationAdmin";
    }

    @RequestMapping(value = "registrationStudent", method = RequestMethod.POST, params = "accept")
    public String acceptStudent(@RequestParam(value = "accept") String username, Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        model.addAttribute("username", username);
        model.addAttribute("acceptingStudentForm", new AcceptingStudentFormDTO());
        return "admin/accepting";
    }

    @RequestMapping(value = "registrationProfessor", method = RequestMethod.POST, params = "accept")
    public String acceptProfessor(@RequestParam(value = "accept") String username, Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        Professor professor = professorDAO.retrieve(username);
        professor.setAccepted(Accepted.ACCEPTED);
        professorDAO.update(professor);
        sendEmail.sendEmailRegistration(professor.getEmail(), professor.getFirstName(), professor.getLastName(),
                SendEmail.SUBJECT_REQUEST_REGISTATION, SendEmail.TEXT_ACCEPTED_REGISTRATION);

        List<Professor> listProfessors = professorDAO.geAllProfessorsToAcceptRefuse();
        model.addAttribute("listProfessors", listProfessors);
        return "admin/registrationProfessor";
    }

    @RequestMapping(value = "registrationAdmin", method = RequestMethod.POST, params = "accept")
    public String acceptAdmin(@RequestParam(value = "accept") String username, Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        AdministratorDAO administratorDAO = (AdministratorDAO) context.getBean("administratorDAO");
        Administrator administrator = administratorDAO.retrieve(username);
        administrator.setAccepted(Accepted.ACCEPTED);
        administratorDAO.update(administrator);
        sendEmail.sendEmailRegistration(administrator.getEmail(), administrator.getFirstName(), administrator.getLastName(),
                SendEmail.SUBJECT_REQUEST_REGISTATION, SendEmail.TEXT_ACCEPTED_REGISTRATION);

        return "redirect:/admin/registrationAdmin";
    }

    @RequestMapping(value = "accepting", method = RequestMethod.GET)
    public String getAcc(HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        return "redirect:/admin/registrationStudent";
    }

    @RequestMapping(value = "accepting", method = RequestMethod.POST)
    public String acceptingStudent(@Valid @ModelAttribute("acceptingStudentForm") AcceptingStudentFormDTO acceptingStudentFormDTO, Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        Student student = studentDAO.retrieve(acceptingStudentFormDTO.getUsername());

        student.setPhoto(acceptingStudentFormDTO.decodeBase64());
        student.setAccepted(acceptingStudentFormDTO.getAccepted());
        studentDAO.update(student);

        //now we can create , for this student,  an instance of CareerExam for each StudPlanExam         	
        CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        StudyPlanExamDAO studyPlanExamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
        List<StudyPlanExam> spExams = studyPlanExamDAO.getAllExamsOfAstudyPlan(student.getStudyPlan());
        for (StudyPlanExam spe : spExams) {
            CareerExam ce = new CareerExam(false, 0, true, student, spe.getExam());
            careerExamDAO.create(ce);
        }

        sendEmail.sendEmailRegistration(student.getEmail(), student.getFirstName(), student.getLastName(),
                SendEmail.SUBJECT_REQUEST_REGISTATION, SendEmail.TEXT_ACCEPTED_REGISTRATION);

        return "redirect:/admin/registrationStudent";

    }

    @RequestMapping(value = "registrationStudent", method = RequestMethod.POST, params = "refuse")
    public String refuseStudent(@RequestParam(value = "refuse") String username, Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        Student student = studentDAO.retrieve(username);

        sendEmail.sendEmailRegistration(student.getEmail(), student.getFirstName(), student.getLastName(),
                SendEmail.SUBJECT_REQUEST_REGISTATION, SendEmail.TEXT_NOT_ACCEPTED_REGISTRATION);
        studentDAO.deleteStudent(student);

        return "redirect:/admin/registrationStudent";
    }

    @RequestMapping(value = "registrationProfessor", method = RequestMethod.POST, params = "refuse")
    public String refuseProfessor(@RequestParam(value = "refuse") String username, Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        Professor professor = professorDAO.retrieve(username);

        sendEmail.sendEmailRegistration(professor.getEmail(), professor.getFirstName(), professor.getLastName(),
                SendEmail.SUBJECT_REQUEST_REGISTATION, SendEmail.TEXT_NOT_ACCEPTED_REGISTRATION);
        professorDAO.delete(professor);
        return "redirect:/admin/registrationProfessor";
    }

    @RequestMapping(value = "registrationAdmin", method = RequestMethod.POST, params = "refuse")
    public String refuseAdmin(@RequestParam(value = "refuse") String username, Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        AdministratorDAO administratorDAO = (AdministratorDAO) context.getBean("administratorDAO");
        Administrator administrator = administratorDAO.retrieve(username);

        sendEmail.sendEmailRegistration(administrator.getEmail(), administrator.getFirstName(), administrator.getLastName(),
                SendEmail.SUBJECT_REQUEST_REGISTATION, SendEmail.TEXT_NOT_ACCEPTED_REGISTRATION);
        administratorDAO.delete(administrator);
        return "redirect:/admin/registrationAdmin";
    }

    @RequestMapping(value = "createExam", method = RequestMethod.GET)
    public String createExam(HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        return "admin/createExam";
    }

    @RequestMapping(value = "examForm", method = RequestMethod.GET)
    public String addExams(Model model, Exam exam, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        model.addAttribute("exam", exam);
        System.out.println("FirstCome==");
        return "admin/examForm";
    }

    @RequestMapping(value = "examForm", method = RequestMethod.POST)
    public ModelAndView addExams(@ModelAttribute("exam") Exam exam, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return new ModelAndView("redirect:/logout");
        }

        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        if (exam.getId() == 0) {
            examDAO.create(exam);
        }
        System.out.println("SecondCome==");
        String examUrl = "examForm";
        return new ModelAndView("redirect:/admin/exams");
    }

    @RequestMapping(value = "exams", method = RequestMethod.GET)
    public String listExams(Model model, HttpServletRequest request) throws NullPointerException {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        model.addAttribute("listExams", examDAO.getAllExams());

        return "admin/exams";
    }

    @RequestMapping("exams/delete/{examId}")
    public String removeExam(@PathVariable("examId") Integer examId, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        examDAO.removeExam(examId);
        return "redirect:/admin/exams";
    }

    @RequestMapping(value = "exams/edit/{id}")
    public String editExam(@PathVariable("id") int id, Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        model.addAttribute("exam", examDAO.getExamById(id));
        model.addAttribute("listExams", examDAO.getAllExams());
        return "admin/exams";
    }
    // </editor-fold>

    // ----------------------------CareerExam Registration ---------------//
    // ----------------------------Create Session ---------------//
    @RequestMapping(value = "createSession", method = RequestMethod.GET)
    public String openCreateNewSession(Model model, HttpServletRequest request) {

        model.addAttribute("pageTitle","Create New Session");
        
        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        DegreeCourseDAO degreeCourseDao = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        ArrayList<DegreeCourse> allDegrees = (ArrayList<DegreeCourse>) degreeCourseDao.getAllDegrees();
        model.addAttribute("degreeCourses", allDegrees);
        model.addAttribute("examSessionForm",new SessionFormDTO());

        return "admin/createSession";
    }

    @RequestMapping(value = "createSession", method = RequestMethod.POST)
    public String createNewSession(@ModelAttribute("examSessionForm") @Valid SessionFormDTO examSessionForm, BindingResult result, Model model, HttpServletRequest request) throws ParseException {

        model.addAttribute("pageTitle","Create New Session");
        
        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        ExamSessionDAO examSessionDao = (ExamSessionDAO) context.getBean("examSessionDAO");
        if(!result.hasErrors()){
            String inputFormat = "dd-MM-yyyy";
            String outputFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
            Date startingDate = sdf.parse(examSessionForm.getStartingDate());
            Date endingDate = sdf.parse(examSessionForm.getEndingDate());
            sdf.applyPattern(outputFormat);
            String startingDateString = sdf.format(startingDate);            
            String endingDateString = sdf.format(endingDate);            
                        
            if (examSessionDao.checkExamSession(startingDateString, endingDateString, examSessionForm.getAcademicYear())) {
                DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
                DegreeCourse degreeCourse = degreeCourseDAO.retrieveById(Integer.parseInt(examSessionForm.getDegreeCourse()));                
                ExamSession examSession = new ExamSession(startingDate, endingDate, examSessionForm.getAcademicYear(), degreeCourse);

                examSessionDao.create(examSession);                
                return "redirect:/admin/viewAllSession";
            }
            else{
                DegreeCourseDAO degreeCourseDao = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
                ArrayList<DegreeCourse> allDegrees = (ArrayList<DegreeCourse>) degreeCourseDao.getAllDegrees();
                model.addAttribute("degreeCourses", allDegrees);
                model.addAttribute("error", "The dates or academic year are not ok");
                return "admin/createSession";
            }
        }
        else {
            DegreeCourseDAO degreeCourseDao = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
            ArrayList<DegreeCourse> allDegrees = (ArrayList<DegreeCourse>) degreeCourseDao.getAllDegrees();
            model.addAttribute("degreeCourses", allDegrees);            
            return "admin/createSession";
        }
    }

    @RequestMapping(value = "viewAllSession", method = RequestMethod.GET)
    public String viewAllSession(Model model, HttpServletRequest request) {

        if (!SessionHelper.isAdmin(request.getSession())) {
            return "redirect:/logout";
        }

        ExamSessionDAO examSessionDao = (ExamSessionDAO) context.getBean("examSessionDAO");
        AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
        ArrayList<ExamSession> allExamSessions = (ArrayList<ExamSession>) examSessionDao.getAllExamSession();//.listAllSession();
        model.addAttribute("lista", allExamSessions);
        model.addAttribute("attemptList", attemptDAO.getAllAttempts());
        return "admin/listSession";
    }

}
