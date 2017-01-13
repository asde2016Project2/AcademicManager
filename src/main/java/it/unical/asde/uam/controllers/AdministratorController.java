package it.unical.asde.uam.controllers;

import it.unical.asde.uam.controllers.core.BaseController;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

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
public class AdministratorController extends BaseController{  

//    @Autowired
//    private WebApplicationContext context;
    
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

    @RequestMapping(value="dashboard",method = RequestMethod.GET)
    public String homeAdmin(Model model) {
    	initDB();
    	System.out.println("studyPlans");
    	model.addAttribute("studyPlans",((StudyPlanDAO) context.getBean("studyPlanDAO")).getAllPlans());
    	return "admin/dashboard";
    }
    
    @RequestMapping(value="dashboard",method = RequestMethod.POST)
    public String details(@RequestParam Integer id,Model model) {
    	StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
    	StudyPlan studyPlan;
    	ArrayList<StudyPlanExam> listStudyPlanExams = new ArrayList<>();
    	ArrayList<Exam> exams = new ArrayList<>();
    	if((studyPlan = studyPlanDAO.retrieve(id)) != null){
    		StudyPlanExamDAO studyPlanExamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
    		listStudyPlanExams = (ArrayList<StudyPlanExam>) studyPlanExamDAO.getAllExamsOfAstudyPlan(studyPlan);
    		for(StudyPlanExam sp: listStudyPlanExams)
    			exams.add(sp.getExam());
    	}
    	if(!listStudyPlanExams.isEmpty() && !exams.isEmpty())
    		model.addAttribute("exams",exams);
    	return "admin/details";
    }
    
    
    
    
    
    

	//-----------New Updated Creating and updating Exam,CareerExam -----//
	
	


	// @GetMapping("/examForm")
	@RequestMapping(value = "/examForm", method = RequestMethod.GET)
	public String addExams(Model model, Exam exam) {
		model.addAttribute("exam", exam);
		System.out.println("FirstCome==");
		return "admin/examForm";
	}

	@RequestMapping(value = "/examForm", method = RequestMethod.POST)
	public ModelAndView addExams(@ModelAttribute("exam") Exam exam) {
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		if (exam.getId() == 0) {
			examDAO.create(exam);
		}
		System.out.println("SecondCome==");
		return new ModelAndView("redirect:/admin/exams");
	}
	
	@RequestMapping(value = "exams", method = RequestMethod.GET)
	public String listExams(Model model) throws NullPointerException {
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		model.addAttribute("listExams", examDAO.getAllExams());

		return "admin/exams";
	}

	
	


	@RequestMapping(value = { "/examForm/edit/id={id:.+}",
			"/examForm/edit/{id:.+}" }, method = RequestMethod.POST)
	public String updateExamInfo(@Validated @ModelAttribute("exam") Exam exam, BindingResult bindingResult) {
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("/examForm");
			modelAndView.addObject("exam", examDAO.getExamById(exam.getId()));
			return modelAndView.getViewName();
		}

		examDAO.updateExam(exam);

		return "redirect:/admin/examForm/id=" + exam.getId();
	}

	@RequestMapping("/exams/delete/{id}")
	public String removeExam(@PathVariable("id") Integer id) {
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");

		examDAO.removeExam(id);
		return "redirect:/admin/exams";
	}

	@RequestMapping(value = "/examForm/edit/{id}")
	public String editExam(@PathVariable("id") int id, Model model) {
		ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
		model.addAttribute("exam", examDAO.getExamById(id));
		model.addAttribute("listExams", examDAO.getAllExams());
		return "admin/exams";
	}
	// </editor-fold>
	
	

	// ----------------------------CareerExam Registration ---------------//
	
	
	@RequestMapping(value = "/careerExamForm", method = RequestMethod.GET)
	public String addCareerExams(Model model, CareerExam careerExam) {
		model.addAttribute("careerExam", careerExam);
		System.out.println("FirstCome==");
		return "admin/careerExamForm";
	}

	@RequestMapping(value = "/careerExamForm", method = RequestMethod.POST)
	public ModelAndView addCareerExams(@ModelAttribute("careerExam") CareerExam careerExam) {
		CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
		if (careerExam.getCareerExamId() == 0) {
			careerExamDAO.create(careerExam);
		}
		System.out.println("SecondCome==");
		return new ModelAndView("redirect:/admin/careerExams");
	}

	

	
	
	
	
	@RequestMapping("/careerExams/delete/{careerExamId}")
	public String removeCareerExam(@PathVariable("careerExamId") Integer careerExamId) {
		CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");

		careerExamDAO.removeCareerExam(careerExamId);
		return "redirect:/admin/careerExams";
	}

	@RequestMapping(value = "/careerExamForm/edit/{careerExamId}")
	public String editCareerExam(@PathVariable("careerExamId") int careerExamId, Model model) {
		CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
		model.addAttribute("careerExam", careerExamDAO.getCareerExamById(careerExamId));
		model.addAttribute("careerExamList", careerExamDAO.listCareerExams());
		return "admin/careerExams";
	}

}
