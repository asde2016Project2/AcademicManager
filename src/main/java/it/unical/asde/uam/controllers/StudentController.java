package it.unical.asde.uam.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unical.asde.uam.Helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.persistence.ExamDAO;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController{  


    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String showDashboad(HttpServletRequest request) {

        if (!SessionHelper.isStudent(request.getSession())) {
            return "redirect:/";
        }

        return "student/dashboard";
    }

    
    @RequestMapping(value = "exams", method = RequestMethod.GET)
    public String listExams(Model model) {
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        model.addAttribute("examForm", new Exam());
        model.addAttribute("listExams", examDAO.getAllExams());
        return "student/exams";

    }

    @RequestMapping(value = "exams/add", method = RequestMethod.POST)
    public String addExam(@ModelAttribute("examForm") Exam exam) {
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        if (exam.getId() == 0) {
            examDAO.create(exam);

        }
        else {
            examDAO.create(exam);
        }

        return "redirect:student/exams";

    }

    @RequestMapping(value = "examdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model) {
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        model.addAttribute("exam", examDAO.getExamById(id));

        return "student/examdata";
    }

}
