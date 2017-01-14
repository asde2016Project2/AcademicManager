package it.unical.asde.uam.controllers;

import it.unical.asde.uam.Helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.persistence.CareerExamDAO;
import it.unical.asde.uam.persistence.ExamDAO;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdministratorController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);

	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String showDashboad(HttpServletRequest request) {

		if (!SessionHelper.isAdmin(request.getSession())) {
			return "redirect:/";
		}

		return "admin/dashboard";
	}

	@RequestMapping(value = "createExam", method = RequestMethod.GET)
	public String createExam() {
		return "admin/createExam";
	}

	@RequestMapping(value = "examForm", method = RequestMethod.GET)
	public String addExams(Model model, Exam exam) {
		model.addAttribute("exam", exam);
		System.out.println("FirstCome==");
		return "admin/examForm";
	}

	@RequestMapping(value = "examForm", method = RequestMethod.POST)
	public ModelAndView addExams(@ModelAttribute("exam") Exam exam) {
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		if (exam.getId() == 0) {
			examDAO.create(exam);
		}
		System.out.println("SecondCome==");
		String examUrl = "examForm";
		return new ModelAndView("redirect:/admin/exams");
	}

	@RequestMapping(value = "exams", method = RequestMethod.GET)
	public String listExams(Model model) throws NullPointerException {
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		model.addAttribute("listExams", examDAO.getAllExams());

		return "admin/exams";
	}

//	@RequestMapping(value = { "examForm/edit/id={examId:.+}",
//			"examForm/edit/{examId:.+}" }, method = RequestMethod.POST)
//	public String updateExamInfo(@Validated @ModelAttribute("exam") Exam exam, BindingResult bindingResult) {
//		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
//		if (bindingResult.hasErrors()) {
//			ModelAndView modelAndView = new ModelAndView("/examForm");
//			modelAndView.addObject("exam", examDAO.getExamById(exam.getId()));
//			return modelAndView.getViewName();
//		}
//
//		examDAO.updateExam(exam);
//		logger.info("Update exam information");
//		return "redirect:/admin/examForm/id=" + exam.getId();
//	}

	@RequestMapping("exams/delete/{examId}")
	public String removeExam(@PathVariable("examId") Integer examId) {
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		examDAO.removeExam(examId);
		return "redirect:/admin/exams";
	}

	@RequestMapping(value = "exams/edit/{id}")
	public String editExam(@PathVariable("id") int id, Model model) {
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		model.addAttribute("exam", examDAO.getExamById(id));
		model.addAttribute("listExams", examDAO.getAllExams());
		return "admin/exams";
	}
	// </editor-fold>

	// ----------------------------CareerExam Registration ---------------//

	@RequestMapping(value = "careerExamForm", method = RequestMethod.GET)
	public String addCareerExam(CareerExam careerExam,Model model) {
		model.addAttribute("careerExam", careerExam);
		System.out.println("FirstCome==");
		return "admin/careerExamForm";
	}

	@RequestMapping(value = "careerExamForm", method = RequestMethod.POST)
	public String addCareerExam(@ModelAttribute("careerExam") CareerExam careerExam) {
		CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");

		if (careerExam.getCareerExamId() == 0) {
			careerExamDAO.create(careerExam);
		}

		return "redirect:/admin/careerExams";
	}

	
	//populate the career exam information 
	
	@RequestMapping(value = "careerExams", method = RequestMethod.GET)
	public String listCareerExam(Model model) throws NullPointerException {
		CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
		model.addAttribute("listCareerExams", careerExamDAO.listCareerExams());

		return "admin/careerExams";
	}
	
	
	@RequestMapping("careerExams/delete/{careerExamId}")
	public String removeCareerExam(@PathVariable("careerExamId") Integer careerExamId) {
		CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");

		careerExamDAO.removeCareerExam(careerExamId);
		return "redirect:/admin/careerExams";
	}

	@RequestMapping(value = "careerExams/edit/{careerId}")
	public String editCareerExam(@PathVariable("careerId") int careerId, Model model) {
		CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
		model.addAttribute("careerExam", careerExamDAO.getCareerExamById(careerId));
		model.addAttribute("careerExams", careerExamDAO.listCareerExams());

		return "admin/careerExams";
	}
}
