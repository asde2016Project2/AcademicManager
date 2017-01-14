package it.unical.asde.uam.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.unical.asde.uam.Helper.SessionHelper;
import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.persistence.CareerExamDAO;
import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.StudyPlanDAO;
import it.unical.asde.uam.persistence.StudyPlanExamDAO;

@Controller
@RequestMapping("/admin")
public class AdministratorController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);

	
public void initDB() {
    	
    	DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
    	if(!degreeCourseDAO.getAllDegrees().isEmpty())
    		return;
    	ArrayList<DegreeCourse> degreeCourses = new ArrayList<>();
    	degreeCourses.add(new DegreeCourse("Computer Science"));
    	degreeCourses.add(new DegreeCourse("Engineering"));
    	degreeCourses.add(new DegreeCourse("Mathematics"));
    	
		degreeCourseDAO.create(degreeCourses.get(0));
		degreeCourseDAO.create(degreeCourses.get(1));
		degreeCourseDAO.create(degreeCourses.get(2));
		
		StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
		if(!studyPlanDAO.getAllPlans().isEmpty())
			return;
		ArrayList<StudyPlan> studyPlans = new ArrayList<>();
		studyPlans.add(new StudyPlan("Scientific Computing", degreeCourses.get(0)));
		studyPlans.add(new StudyPlan("Civil", degreeCourses.get(1)));
		studyPlans.add(new StudyPlan("Management", degreeCourses.get(1)));
		studyPlans.add(new StudyPlan("Numbers Theory", degreeCourses.get(2)));
		
		studyPlanDAO.create(studyPlans.get(0));
		studyPlanDAO.create(studyPlans.get(1));
		studyPlanDAO.create(studyPlans.get(2));
		studyPlanDAO.create(studyPlans.get(3));
		
		ArrayList<Exam> exams = new ArrayList<>();
		exams.add(new Exam("Theoretical Computer Science",10,23));
		exams.add(new Exam("Knowledge Management",10,21));
		exams.add(new Exam("Intelligent System",5,22));
		exams.add(new Exam("Network and Security",10,18));
		exams.add(new Exam("Mobile and Social Computing",5,19));
		exams.add(new Exam("Models and simulation",5,17));
		exams.add(new Exam("Data Mining and Data Warehouse",10,15));
		exams.add(new Exam("Social network and new media",5,12));
		exams.add(new Exam("Criptography",5,11));
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		for(Exam e: exams){
			examDAO.create(e);
		}
		StudyPlanExamDAO studyPlanExamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
		for(Exam e: exams) {
			studyPlanExamDAO.create(new StudyPlanExam(studyPlans.get(0),e,"period"));
		}
	}

	
	
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
