package it.unical.asde.uam.controllers;

import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.persistence.ExamDAO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class ExamController {

    
	@Autowired
	private WebApplicationContext context;

//	@RequestMapping(value = "/exams", method = RequestMethod.GET)
//	public String home(Model model) {
//		model.addAttribute("examForm", new Exam());
//		return "exams";
//	}

	//Order of BindingResult matters (it must be just after ModelAttribute)
	@RequestMapping(value = "/exams", method = RequestMethod.POST)
	public String executeOperation(@Valid @ModelAttribute("examForm") Exam exam, BindingResult result, Model model, HttpServletRequest request) {
		if(result.hasErrors()) {
			return "exams";
		}
		
		          ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		if (examDAO.exists(exam.getName())) {
			model.addAttribute("error", "exam name is already used");
			return "exams";
		} else {
			examDAO.create(exam);
			return "redirect:/";
		}
	}
}
