package it.unical.asde.uam.controllers;

import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.persistence.ExamDAO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		model.addAttribute("examForm", new Exam());
		return "home";

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

		} else {
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
