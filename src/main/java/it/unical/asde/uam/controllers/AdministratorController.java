package it.unical.asde.uam.controllers;

import it.unical.asde.uam.model.AcceptingStudentFormDTO;
import it.unical.asde.uam.model.Administrator;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.helper.Accepted;
import it.unical.asde.uam.helper.SessionHelper;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.SendEmail;

import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;

import it.unical.asde.uam.dto.StudyPlanFormDTO;
import it.unical.asde.uam.persistence.AdministratorDAO;
import it.unical.asde.uam.persistence.CareerExamDAO;

import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;
import it.unical.asde.uam.persistence.StudentDAO;
import it.unical.asde.uam.persistence.StudyPlanDAO;
import it.unical.asde.uam.persistence.StudyPlanExamDAO;
import javax.servlet.http.HttpServletRequest;
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
                
        if(!SessionHelper.isAdmin(request.getSession())){
            return "redirect:/";
        }
      
        StudentDAO studentDAO  = (StudentDAO) context.getBean("studentDAO");
        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        AdministratorDAO administratorDAO = (AdministratorDAO) context.getBean("administratorDAO");
        model.addAttribute("numberAdmins",administratorDAO.getAllAdminsToAcceptRefuse().size());
    	model.addAttribute("numberStudents",studentDAO.getAllStudentsToAcceptRefuse().size());
    	model.addAttribute("numberProfessors",professorDAO.geAllProfessorsToAcceptRefuse().size());
        model.addAttribute("pageTitle","Admin Dashboard");
        return "admin/dashboard";
    }

    @RequestMapping(value = "list/studyplan", method = RequestMethod.GET)
    public String showListStudyPlan(Model model) {
        model.addAttribute("pageTitle","List all Study Plan");
        model.addAttribute("studyPlans", ((StudyPlanDAO) context.getBean("studyPlanDAO")).getAllPlans());
        return "admin/list_studyplans";
    }

    @RequestMapping(value = "detail/studyplan/{id}", method = RequestMethod.GET)
    public String showDetailStudyPlan(@PathVariable("id") int id, Model model) {
        model.addAttribute("pageTitle","Study Plan Detail");
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
        model.addAttribute("pageTitle","Create Study Plan");
        model.addAttribute("studyPlanForm", new StudyPlanFormDTO());
        model.addAttribute("degreeCourseList", ((DegreeCourseDAO) context.getBean("degreeCourseDAO")).getAllDegrees());
        model.addAttribute("examList", ((ExamDAO) context.getBean("examDAO")).getAllExams());
        return "admin/create_studyplan";
    }

    @RequestMapping(value = "create/studyplan", method = RequestMethod.POST)
    public String doCreateStudyPlan(@Valid @ModelAttribute("studyPlanForm") StudyPlanFormDTO studyPlanFormDTO, BindingResult result, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pageTitle","Create Study Plan");
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
        model.addAttribute("studyPlans", ((StudyPlanDAO) context.getBean("studyPlanDAO")).getAllPlans());
        return "admin/list_studyplans";

    }

    @RequestMapping(value = "registrationStudent", method = RequestMethod.GET)
    public String registationStudent(Model model) {        
        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
        model.addAttribute("listStudents", listStudents);
        return "admin/registrationStudent";
    }
    
    @RequestMapping(value = "registrationProfessor", method = RequestMethod.GET)
    public String registationProfessor(Model model) {        
        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        List<Professor> listProfessors = professorDAO.geAllProfessorsToAcceptRefuse();
        model.addAttribute("listProfessors", listProfessors);
        return "admin/registrationProfessor";
    }
    
    @RequestMapping(value = "registrationAdmin", method = RequestMethod.GET)
    public String registationAdmin(Model model) {        
        AdministratorDAO administratorDAO = (AdministratorDAO) context.getBean("administratorDAO");
        List<Administrator> listAdministrators = administratorDAO.getAllAdminsToAcceptRefuse();
        model.addAttribute("listAdministrators", listAdministrators);
        return "admin/registrationAdmin";
    }

    @RequestMapping(value = "registrationStudent", method = RequestMethod.POST, params = "accept")
    public String acceptStudent(@RequestParam(value = "accept") String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("acceptingStudentForm", new AcceptingStudentFormDTO());
        return "admin/accepting";
    }
    
    
    @RequestMapping(value = "registrationProfessor", method = RequestMethod.POST, params = "accept")
    public String acceptProfessor(@RequestParam(value = "accept") String username, Model model) {
    	ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
    	Professor professor = professorDAO.retrieve(username);
    	professor.setAccepted(Accepted.ACCEPTED);
    	professorDAO.update(professor);
    	sendEmail.sendEmailRegistration(professor.getEmail(), professor.getFirstName(), professor.getLastName(),
    			SendEmail.SUBJECT_REQUEST_REGISTATION,SendEmail.TEXT_ACCEPTED_REGISTRATION);
    	
    	List<Professor> listProfessors = professorDAO.geAllProfessorsToAcceptRefuse();
        model.addAttribute("listProfessors", listProfessors);
        return "admin/registrationProfessor";
    }
    
    @RequestMapping(value = "registrationAdmin", method = RequestMethod.POST, params = "accept")
    public String acceptAdmin(@RequestParam(value = "accept") String username, Model model) {
    	AdministratorDAO administratorDAO = (AdministratorDAO) context.getBean("administratorDAO");
        Administrator administrator = administratorDAO.retrieve(username);
    	administrator.setAccepted(Accepted.ACCEPTED);
    	administratorDAO.update(administrator);
    	sendEmail.sendEmailRegistration(administrator.getEmail(), administrator.getFirstName(), administrator.getLastName(),
    			SendEmail.SUBJECT_REQUEST_REGISTATION,SendEmail.TEXT_ACCEPTED_REGISTRATION);
    	
    	List<Administrator> listAdministrator = administratorDAO.getAllAdminsToAcceptRefuse();
        model.addAttribute("listProfessors", listAdministrator);
        return "admin/registrationAdmin";
    }

    @RequestMapping(value = "accepting", method = RequestMethod.POST)
    public String acceptingStudent(@Valid @ModelAttribute("acceptingStudentForm") AcceptingStudentFormDTO acceptingStudentFormDTO, Model model) {
        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        Student student = studentDAO.retrieve(acceptingStudentFormDTO.getUsername());
        
        student.setPhoto(acceptingStudentFormDTO.decodeBase64());
        student.setAccepted(acceptingStudentFormDTO.getAccepted());      
        studentDAO.update(student);
        
        sendEmail.sendEmailRegistration(student.getEmail(),student.getFirstName(),student.getLastName(),
        		SendEmail.SUBJECT_REQUEST_REGISTATION,SendEmail.TEXT_ACCEPTED_REGISTRATION);
        
        List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
        model.addAttribute("listStudents", listStudents);
        return "admin/registrationStudent";
    }
    
    @RequestMapping(value = "registrationStudent", method = RequestMethod.POST, params = "refuse")
    public String refuseStudent(@RequestParam(value = "refuse") String username, Model model) {
        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        Student student = studentDAO.retrieve(username);
        
        sendEmail.sendEmailRegistration(student.getEmail(),student.getFirstName(),student.getLastName(),
        		SendEmail.SUBJECT_REQUEST_REGISTATION,SendEmail.TEXT_NOT_ACCEPTED_REGISTRATION);
        studentDAO.deleteStudent(student);
        List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
        model.addAttribute("listStudents", listStudents);
        return "admin/registrationStudent";
    }
    
    @RequestMapping(value = "registrationProfessor", method = RequestMethod.POST, params = "refuse")
    public String refuseProfessor(@RequestParam(value = "refuse") String username, Model model) {
    	ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        Professor professor = professorDAO.retrieve(username);
        
        sendEmail.sendEmailRegistration(professor.getEmail(),professor.getFirstName(),professor.getLastName(),
        		SendEmail.SUBJECT_REQUEST_REGISTATION,SendEmail.TEXT_NOT_ACCEPTED_REGISTRATION);
        professorDAO.delete(professor);
        List<Professor> listProfessors = professorDAO.geAllProfessorsToAcceptRefuse();
        model.addAttribute("listProfessors", listProfessors);
        return "admin/registrationProfessor";
    }
    
    @RequestMapping(value = "registrationAdmin", method = RequestMethod.POST, params = "refuse")
    public String refuseAdmin(@RequestParam(value = "refuse") String username, Model model) {
    	AdministratorDAO administratorDAO = (AdministratorDAO) context.getBean("administratorDAO");
        Administrator administrator = administratorDAO.retrieve(username);
        
        sendEmail.sendEmailRegistration(administrator.getEmail(),administrator.getFirstName(),administrator.getLastName(),
        		SendEmail.SUBJECT_REQUEST_REGISTATION,SendEmail.TEXT_NOT_ACCEPTED_REGISTRATION);
        administratorDAO.delete(administrator);
        List<Administrator> listAdministrators = administratorDAO.getAllAdminsToAcceptRefuse();
        model.addAttribute("listAdministrators", listAdministrators);
        return "admin/registrationAdmin";
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
