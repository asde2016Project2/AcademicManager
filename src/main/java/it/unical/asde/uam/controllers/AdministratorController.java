package it.unical.asde.uam.controllers;

import it.unical.asde.uam.Helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.AcceptingStudentFormDTO;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.LoginFormDTO;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.model.StudyPlanFormDTO;
import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.DegreeCourseDAOImp;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.StudentDAO;
import it.unical.asde.uam.persistence.StudyPlanDAO;
import it.unical.asde.uam.persistence.StudyPlanExamDAO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/admin")
public class AdministratorController extends BaseController {

    @Autowired
    private WebApplicationContext context;

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String showDashboad(HttpServletRequest request) {

        //if(!SessionHelper.isAdmin(request.getSession())){
        //    return "redirect:/";
        //}
        return "admin/dashboard";
    }

    @RequestMapping(value = "list/studyplan", method = RequestMethod.GET)
    public String showListStudyPlan(Model model) {
        model.addAttribute("studyPlans", ((StudyPlanDAO) context.getBean("studyPlanDAO")).getAllPlans());
        return "admin/list_studyplans";
    }

    @RequestMapping(value = "detail/studyplan/{id}", method = RequestMethod.GET)
    public String showDetailStudyPlan(@PathVariable("id") int id, Model model) {
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
    public String showCreateStudyPlan(Model model) {
        model.addAttribute("studyPlanForm", new StudyPlanFormDTO());
        model.addAttribute("degreeCourseList", ((DegreeCourseDAO) context.getBean("degreeCourseDAO")).getAllDegrees());
        model.addAttribute("examList", ((ExamDAO) context.getBean("examDAO")).getAllExams());
        return "admin/create_studyplan";
    }

    @RequestMapping(value = "create/studyplan", method = RequestMethod.POST)
    public String doCreateStudyPlan(@Valid @ModelAttribute("studyPlanForm") StudyPlanFormDTO studyPlanFormDTO, BindingResult result, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("degreeCourseList", ((DegreeCourseDAO) context.getBean("degreeCourseDAO")).getAllDegrees());
            model.addAttribute("examList", ((ExamDAO) context.getBean("examDAO")).getAllExams());
            return "admin/create_studyplan";
        }

        System.out.println("0-----------------------");
        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
        DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        StudyPlanExamDAO studyPlanExamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        System.out.println("1------------------------");
        DegreeCourse degreeCourse = degreeCourseDAO.retrieveById(Integer.parseInt(studyPlanFormDTO.getDegreeCourseId()));
        StudyPlan studyPlan = new StudyPlan(studyPlanFormDTO.getName(), degreeCourse);
        studyPlanDAO.create(studyPlan);

        System.out.println("2---------------------");
        ArrayList<String> examList = studyPlanFormDTO.getExamList();
        for (String examId : examList) {
            if (examId.matches("^\\d+$")) {
                Exam e = examDAO.getExamById(Integer.parseInt(examId));
                StudyPlanExam spe = new StudyPlanExam(studyPlan, e, "period");
                studyPlanExamDAO.create(spe);
                System.out.println("LOOOOOOOOOOOOOP");
            }
        }
        System.out.println("FINISH");
        return "admin/dashboard";

    }

    @RequestMapping(value = "registrations", method = RequestMethod.GET)
    public String registations(Model model) {        
        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
        model.addAttribute("listStudents", listStudents);
        return "admin/registrations";
    }

    @RequestMapping(value = "registrations", method = RequestMethod.POST, params = "accept")
    public String acceptStudent(@RequestParam(value = "accept") String username, Model model) {
        /*StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO"); 
    	Student student = studentDAO.retrieve(username);
    	student.setStatus(true);
    	studentDAO.update(student);
    	List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
    	model.addAttribute("listStudents",listStudents);
         */
        model.addAttribute("username", username);
        model.addAttribute("acceptingStudentForm", new AcceptingStudentFormDTO());
        return "admin/accepting";
    }

    @RequestMapping(value = "accepting", method = RequestMethod.POST)
    public String acceptingStudent(@Valid @ModelAttribute("acceptingStudentForm") AcceptingStudentFormDTO acceptingStudentFormDTO, Model model) {
        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        Student student = studentDAO.retrieve(acceptingStudentFormDTO.getUsername());
        System.out.println(acceptingStudentFormDTO.getUsername());
        System.out.println(acceptingStudentFormDTO.getPhoto());
        student.setPhoto(acceptingStudentFormDTO.getPhoto());
        studentDAO.update(student);
        model.addAttribute("username", student.getUsername());
        model.addAttribute("photo", studentDAO.retrieve(student.getUsername()).getPhoto());
        return "admin/dashboard";
    }

    @RequestMapping(value = "registrations", method = RequestMethod.POST, params = "refuse")
    public String refuseStudent(@RequestParam(value = "refuse") String username, Model model) {
        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        Student student = studentDAO.retrieve(username);
        studentDAO.deleteStudent(student);
        List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
        model.addAttribute("listStudents", listStudents);
        return "admin/registrations";
    }

}
