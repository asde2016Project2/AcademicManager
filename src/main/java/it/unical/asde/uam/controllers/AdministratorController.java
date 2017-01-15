package it.unical.asde.uam.controllers;

import it.unical.asde.uam.controllers.core.BaseController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

@Controller
@RequestMapping("/admin")
public class AdministratorController extends BaseController{  

    @Autowired
    private WebApplicationContext context;
    
    ArrayList<StudyPlan> studyPlans = new ArrayList<>();
    
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
    
    private ArrayList<Student> students = new ArrayList<>();
    
    private void initStudents(){
    	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
    	for(int i=0;i<10;i++){
    		
    		Student p = new Student("student"+i,"psw"+i,"name"+i,"lastname"+i,false,studyPlans.get(0));
    		p.setEmail("stud" + i + "@mat.unical.it");
            p.setAge(19);
            String dateOfBirth = "11-11-1999";
            String dateOfBirthFormat = "dd-mm-yyyy";
            DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
            Date dateOfBirthObject = null;
			try {
				dateOfBirthObject = format.parse(dateOfBirth);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            p.setDateOfBirth(dateOfBirthObject);
            
    		students.add(p);
    		studentDAO.create(students.get(i));
    	}
    }

    @RequestMapping(value="studyplans",method = RequestMethod.GET)
    public String homeAdmin(Model model) {
    	initDB();
    	model.addAttribute("studyPlans",((StudyPlanDAO) context.getBean("studyPlanDAO")).getAllPlans());
    	return "admin/studyplans";
    }
    
    @RequestMapping(value="studyplans",method = RequestMethod.POST)
    public String detailsStudyPlan(@RequestParam Integer id,Model model) {
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
    	return "admin/detailsstudyplan";
    }
    
    @RequestMapping(value="newstudyplan",method = RequestMethod.GET)
    public String newStudyPlan(Model model) {
    	model.addAttribute("studyPlanForm",new StudyPlanFormDTO());
    	model.addAttribute("degreeCourseList",((DegreeCourseDAO)context.getBean("degreeCourseDAO")).getAllNameDegrees());
    	model.addAttribute("examList",((ExamDAO)context.getBean("examDAO")).getAllNameExams());
    	return "admin/newstudyplan";
    }
    
    @RequestMapping(value="newstudyplan",method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("studyPlanForm") StudyPlanFormDTO studyPlanFormDTO,Model model) {
    	StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
    	DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
    	StudyPlanExamDAO studyPlanExamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
    	ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
    	
    	DegreeCourse degreeCourse = degreeCourseDAO.retrieveByName(studyPlanFormDTO.getNameDegreeCourse());
    	StudyPlan studyPlan = new StudyPlan(studyPlanFormDTO.getNameStudyPlan(),degreeCourse);
    	studyPlanDAO.create(studyPlan);
    	
    	List<String> l = studyPlanFormDTO.getNameExams();
    	
    	for(String nameExam: l){
    		studyPlanExamDAO.create(new StudyPlanExam(studyPlan,examDAO.retrieve(nameExam),"period"));
    	}
    	
    	return "admin/dashboard";
    }
    
    @RequestMapping(value="registrations",method = RequestMethod.GET)
    public String registations(Model model) {
    	initDB();
    	initStudents();
    	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
    	List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
    	model.addAttribute("listStudents",listStudents);
    	return "admin/registrations";
    }
    
    @RequestMapping(value="registrations",method = RequestMethod.POST,params="accept")
    public String acceptStudent(@RequestParam(value="accept") String username,Model model) {
    	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO"); 
    	Student student = studentDAO.retrieve(username);
    	student.setStatus(true);
    	studentDAO.update(student);
    	List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
    	model.addAttribute("listStudents",listStudents);
    	return "admin/registrations";
    }
    
    @RequestMapping(value="registrations",method = RequestMethod.POST,params="refuse")
    public String refuseStudent(@RequestParam(value="refuse") String username,Model model) {
    	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO"); 
    	Student student = studentDAO.retrieve(username);
    	studentDAO.deleteStudent(student);
    	List<Student> listStudents = studentDAO.getAllStudentsToAcceptRefuse();
    	model.addAttribute("listStudents",listStudents);
    	return "admin/registrations";
    }
    
}
