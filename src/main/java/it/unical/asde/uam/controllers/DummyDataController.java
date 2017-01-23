/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.controllers;

import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.Administrator;
import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.model.UserAttemptRegistration;
import it.unical.asde.uam.persistence.AdministratorDAO;
import it.unical.asde.uam.persistence.AttemptDAO;
import it.unical.asde.uam.persistence.CareerExamDAO;
import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;
import it.unical.asde.uam.persistence.StudentDAO;
import it.unical.asde.uam.persistence.StudyPlanDAO;
import it.unical.asde.uam.persistence.StudyPlanExamDAO;
import it.unical.asde.uam.persistence.UserAttemptRegistrationDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.GregorianCalendar;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unical.asde.uam.controllers.core.BaseController;
import it.unical.asde.uam.model.Administrator;
import it.unical.asde.uam.model.Attempt;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.ExamSession;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.model.UserAttemptRegistration;
import it.unical.asde.uam.persistence.AdministratorDAO;
import it.unical.asde.uam.persistence.AttemptDAO;
import it.unical.asde.uam.persistence.DegreeCourseDAO;
import it.unical.asde.uam.persistence.ExamDAO;
import it.unical.asde.uam.persistence.ExamSessionDAO;
import it.unical.asde.uam.persistence.ProfessorDAO;
import it.unical.asde.uam.persistence.StudentDAO;
import it.unical.asde.uam.persistence.StudyPlanDAO;
import it.unical.asde.uam.persistence.StudyPlanExamDAO;
import it.unical.asde.uam.persistence.UserAttemptRegistrationDAO;

/**
 *
 * @author kz
 */
@Controller
@RequestMapping("/dummyData")
public class DummyDataController extends BaseController {

    @RequestMapping(value = "registerProfessor", method = RequestMethod.GET)
    public String registerProfessor() throws ParseException {
        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        for (int i = 0; i < 5; i++) {
            Professor p = new Professor("prof" + i, "123456", "mario", "rossi", true);
            p.setEmail("prof" + i + "@mat.unical.it");
            p.setAge(21);

            String dateOfBirth = "01-01-1971";
            String dateOfBirthFormat = "dd-mm-yyyy";
            DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
            Date dateOfBirthObject = format.parse(dateOfBirth);

            p.setDateOfBirth(dateOfBirthObject);

            professorDAO.create(p);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "registerStudent", method = RequestMethod.GET)
    public String registerStudent() throws ParseException {

        registerStudyPlan();  //!!!!!!!!!!!!!!!!!!!!!!!!!!
        // registerStudent() calls registerStudyPlan() at the beginning,
        // so studyPlan stuff is already created!!!!

        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");

        //hardcoded
        StudyPlan scientific = studyPlanDAO.getAllPlans().get(3);

        for (int i = 0; i < 5; i++) {
            Student p = new Student("stud" + i, "123456", "Pierino", "Stecchino", true, scientific);
            if (i == 0) {
                p.setEmail("effe.sessa" + "@gmail.com");
            }
            else {
                p.setEmail("stud" + i + "@mat.unical.it");

            }

            p.setAge(19);

            String dateOfBirth = "11-11-1999";
            String dateOfBirthFormat = "dd-mm-yyyy";
            DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
            Date dateOfBirthObject = format.parse(dateOfBirth);

            p.setDateOfBirth(dateOfBirthObject);

            studentDAO.create(p);

            //I'm creating a career only for the 1st student!!!!!!!!!!!
            if (i == 0) {
                populateStudentCareer(p);
            }
        }

        return "redirect:/";
    }

    @RequestMapping(value = "addDegreeCourse", method = RequestMethod.GET)
    public String addDegreeCourse() {
        DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        DegreeCourse d1 = new DegreeCourse("Computer Science");
        DegreeCourse d2 = new DegreeCourse("Mathematics");
        DegreeCourse d3 = new DegreeCourse("Physics");
        DegreeCourse d4 = new DegreeCourse("Biology");
        degreeCourseDAO.create(d1);
        degreeCourseDAO.create(d2);
        degreeCourseDAO.create(d3);
        degreeCourseDAO.create(d4);
        return "redirect:/";
    }

    @RequestMapping(value = "registerStudyPlan", method = RequestMethod.GET)
    public String registerStudyPlan() {

        String viewToRender = "redirect:/";
        DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        if (!degreeCourseDAO.getAllDegrees().isEmpty()) {
            return viewToRender;
        }
        ArrayList<DegreeCourse> degreeCourses = new ArrayList<>();
        degreeCourses.add(new DegreeCourse("Computer Science"));
        degreeCourses.add(new DegreeCourse("Engineering"));
        degreeCourses.add(new DegreeCourse("Mathematics"));

        degreeCourseDAO.create(degreeCourses.get(0));
        degreeCourseDAO.create(degreeCourses.get(1));
        degreeCourseDAO.create(degreeCourses.get(2));

        StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
        if (!studyPlanDAO.getAllPlans().isEmpty()) {
            return viewToRender;
        }
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
        exams.add(new Exam("Theoretical Computer Science", 10, 23));
        exams.add(new Exam("Knowledge Management", 10, 21));
        exams.add(new Exam("Intelligent System", 5, 22));
        exams.add(new Exam("Network and Security", 10, 18));
        exams.add(new Exam("Mobile and Social Computing", 5, 19));
        exams.add(new Exam("Models and simulation", 5, 17));
        exams.add(new Exam("Data Mining and Data Warehouse", 10, 15));
        exams.add(new Exam("Social network and new media", 5, 12));
        exams.add(new Exam("Criptography", 5, 11));
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        for (Exam e : exams) {
            examDAO.create(e);
        }
        StudyPlanExamDAO studyPlanExamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
        for (Exam e : exams) {
            studyPlanExamDAO.create(new StudyPlanExam(studyPlans.get(0), e, "period"));
        }

        return viewToRender;
    }

    @RequestMapping(value = "registerAdmin", method = RequestMethod.GET)
    public String registerAdmin() throws ParseException {
        AdministratorDAO administratorDAO = (AdministratorDAO) context.getBean("administratorDAO");
        for (int i = 0; i < 5; i++) {
            Administrator p = new Administrator("admin" + i, "123456", "mario", "rossi", true);
            p.setEmail("prof" + i + "@mat.unical.it");
            p.setAge(21);

            String dateOfBirth = "01-01-1971";
            String dateOfBirthFormat = "dd-mm-yyyy";
            DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
            Date dateOfBirthObject = format.parse(dateOfBirth);

            p.setDateOfBirth(dateOfBirthObject);

            administratorDAO.create(p);
        }
        return "redirect:/";
    }

    //I'm creating a career only for the 1st student!!!!!!!!!!!
    private void populateStudentCareer(Student stud) {
        StudyPlanExamDAO spexamDAO = (StudyPlanExamDAO) context.getBean("studyPlanExamDAO");
        List<StudyPlanExam> spexams = spexamDAO.getAllExamsOfAstudyPlan(stud.getStudyPlan());

        CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");

        // I'm assuming that current user (the first, stud0) has done half exams of his studyplan
        Integer dummyDay = 10; // just to have different dates
        int index = 0; // just to identify exam position and set exam "done" or "not done"
        for (StudyPlanExam spex : spexams) {
            //just to have random grades
            int randomGrade = ThreadLocalRandom.current().nextInt(18, 31);

            //stud0 has done half exams of his studyplan
            boolean done = (index % 2 == 0) ? true : false;

            CareerExam ce;
            if (index == 0) // the first exam grade is 30 with honours
            {
                ce = new CareerExam(done, 30, true, stud, spex.getExam());
                ce.setHonours(true);
            }
            else // other exams grades are randomly generated
            {
                ce = new CareerExam(done, randomGrade, true, stud, spex.getExam());
                ce.setHonours(false);
            }

            StringBuilder sb = new StringBuilder();
            sb.append(dummyDay.toString());
            sb.append("-01-2017");
            String dateOfExam = sb.toString();
            dummyDay++;

            String dateOfExamFormat = "dd-mm-yyyy";
            DateFormat format = new SimpleDateFormat(dateOfExamFormat, Locale.ENGLISH);
            Date dateOfExamObject = null;
            try {
                dateOfExamObject = format.parse(dateOfExam);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            ce.setDateOfExam(dateOfExamObject);

            careerExamDAO.create(ce);

            index++;
        }

    }

    @RequestMapping(value = "addExam", method = RequestMethod.GET)
    public String addExam() {

        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");

        Exam e1 = new Exam("asde", 6, 21458);
        Exam e2 = new Exam("asde1", 12, 21258);
        Exam e3 = new Exam("asde2", 6, 26458);
        Exam e4 = new Exam("asde3", 3, 21358);
        Exam e5 = new Exam("asde4", 6, 21858);
        Exam e6 = new Exam("asde5", 3, 21958);
        Exam e7 = new Exam("asde6", 6, 21758);
        Exam e8 = new Exam("asde7", 3, 21058);
        examDAO.create(e1);
        examDAO.create(e2);
        examDAO.create(e3);
        examDAO.create(e4);
        examDAO.create(e5);
        examDAO.create(e6);
        examDAO.create(e7);
        examDAO.create(e8);

        return "redirect:/";
    }

    @RequestMapping(value = "addCareerExam", method = RequestMethod.GET)
    public String addCareerExam() {

        CareerExamDAO careerExamDAO = (CareerExamDAO) context.getBean("careerExamDAO");
        StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
        ArrayList<Student> students = (ArrayList<Student>) studentDAO.getAllStudents();
        System.out.println("students size: " + students.size());

        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        List<Exam> exams = examDAO.getAllExams();

        CareerExam cE1 = new CareerExam(true, 25, true, students.get(0), exams.get(0));
        CareerExam cE2 = new CareerExam(false, 25, true, students.get(0), exams.get(1));
        CareerExam cE3 = new CareerExam(false, 25, true, students.get(0), exams.get(2));
        CareerExam cE4 = new CareerExam(true, 25, true, students.get(0), exams.get(3));
        CareerExam cE5 = new CareerExam(true, 25, true, students.get(0), exams.get(4));
        CareerExam cE6 = new CareerExam(true, 25, true, students.get(0), exams.get(5));
        CareerExam cE7 = new CareerExam(true, 25, true, students.get(0), exams.get(6));
        CareerExam cE8 = new CareerExam(true, 25, true, students.get(0), exams.get(7));

        CareerExam cE11 = new CareerExam(true, 25, true, students.get(1), exams.get(0));
        CareerExam cE12 = new CareerExam(false, 25, true, students.get(1), exams.get(1));
        CareerExam cE13 = new CareerExam(false, 25, true, students.get(1), exams.get(2));
        CareerExam cE14 = new CareerExam(true, 25, true, students.get(1), exams.get(3));
        CareerExam cE15 = new CareerExam(false, 25, true, students.get(1), exams.get(4));
        CareerExam cE16 = new CareerExam(true, 25, true, students.get(1), exams.get(5));
        CareerExam cE17 = new CareerExam(true, 25, true, students.get(1), exams.get(6));
        CareerExam cE18 = new CareerExam(true, 25, true, students.get(1), exams.get(7));

        CareerExam cE21 = new CareerExam(true, 25, true, students.get(2), exams.get(0));
        CareerExam cE22 = new CareerExam(true, 25, true, students.get(2), exams.get(1));
        CareerExam cE23 = new CareerExam(true, 25, true, students.get(2), exams.get(2));
        CareerExam cE24 = new CareerExam(true, 25, true, students.get(2), exams.get(3));
        CareerExam cE25 = new CareerExam(false, 25, true, students.get(2), exams.get(4));
        CareerExam cE26 = new CareerExam(false, 25, true, students.get(2), exams.get(5));
        CareerExam cE27 = new CareerExam(true, 25, true, students.get(2), exams.get(6));
        CareerExam cE28 = new CareerExam(true, 25, true, students.get(2), exams.get(7));

        careerExamDAO.create(cE1);
        careerExamDAO.create(cE2);
        careerExamDAO.create(cE3);
        careerExamDAO.create(cE4);
        careerExamDAO.create(cE5);
        careerExamDAO.create(cE6);
        careerExamDAO.create(cE7);
        careerExamDAO.create(cE8);
        careerExamDAO.create(cE11);
        careerExamDAO.create(cE12);
        careerExamDAO.create(cE13);
        careerExamDAO.create(cE14);
        careerExamDAO.create(cE15);
        careerExamDAO.create(cE16);
        careerExamDAO.create(cE17);
        careerExamDAO.create(cE18);
        careerExamDAO.create(cE21);
        careerExamDAO.create(cE22);
        careerExamDAO.create(cE23);
        careerExamDAO.create(cE24);
        careerExamDAO.create(cE25);
        careerExamDAO.create(cE26);
        careerExamDAO.create(cE27);
        careerExamDAO.create(cE28);

        return "redirect:/";
    }

    protected Date dateValue() {
        GregorianCalendar calendar2 = new GregorianCalendar();
        // Calendar calendar2 = Calendar.getInstance(Locale.ENGLISH);
        calendar2.setLenient(false);
        calendar2.set(GregorianCalendar.YEAR, 2016);
        calendar2.set(GregorianCalendar.MONTH, 01);
        calendar2.set(GregorianCalendar.DATE, 12);
        Date dates = calendar2.getTime();
        return dates;
    }

    @RequestMapping(value = "addAttempt", method = RequestMethod.GET)
    public String addAttempt() {

        System.out.println("dat----" + dateValue());

        DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        ArrayList<DegreeCourse> degreeCourses = new ArrayList<>();
        degreeCourses.add(new DegreeCourse("Computer Sciencea"));
        degreeCourses.add(new DegreeCourse("Engineeringa"));
        degreeCourses.add(new DegreeCourse("Mathematicsa"));

        degreeCourseDAO.create(degreeCourses.get(0));
        degreeCourseDAO.create(degreeCourses.get(1));
        degreeCourseDAO.create(degreeCourses.get(2));

        ArrayList<ExamSession> examSessions = new ArrayList<>();

        examSessions.add(new ExamSession(dateValue(), dateValue(), "2016", degreeCourses.get(0)));
        examSessions.add(new ExamSession(dateValue(), dateValue(), "2016", degreeCourses.get(1)));
        examSessions.add(new ExamSession(dateValue(), dateValue(), "2016", degreeCourses.get(2)));

        ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
        for (ExamSession examSession : examSessions) {
            examSession.setStatus("active");
            examSessionDAO.create(examSession);
        }

        ArrayList<Exam> exams = new ArrayList<>();
        exams.add(new Exam("Theoretical Computer Sciences", 10, 23));
        exams.add(new Exam("Knowledge Managements", 10, 21));
        exams.add(new Exam("Intelligent Systems", 5, 22));
        exams.add(new Exam("Network and Securitys", 10, 18));
        exams.add(new Exam("Mobile and Social Computings", 5, 19));
        exams.add(new Exam("Models and simulations", 5, 17));
        exams.add(new Exam("Data Mining and Data Warehouses", 10, 15));
        exams.add(new Exam("Social network and new medias", 5, 12));
        exams.add(new Exam("Criptographys", 5, 11));
        ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
        for (Exam e : exams) {
            examDAO.create(e);
        }

        ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
        Professor professor = null;
        for (int i = 0; i < 5; i++) {
//			ArrayList<Professor> professors = new ArrayList<>();
            professor = new Professor("professors" + i, "123456", "professors", "professors", true);

            professor.setEmail("professors" + i + "@mat.unical.it");
            professor.setAge(21);
            professor.setDateOfBirth(dateValue());
            professorDAO.create(professor);

            AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");

            Attempt attempt = new Attempt();
            examSessionDAO.listExamRegAppeals();
            examDAO.getAllExams();
            attempt.setClassroom("MT06");
            attempt.setEndRegistrationDate(dateValue());
            exams.get(i).getId();
            attempt.setExam(exams.get(0));
            attempt.setExamDate(dateValue());
            attempt.setExamSession(examSessions.get(0));
            professor.getUserId();
            attempt.setProfessor(professor);
            attempt.setStartRegistrationDate(dateValue());
            attempt.setStatus("active");

            attemptDAO.create(attempt);

            StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
            Student student = null;
            StudyPlan businessStudyPlan = new StudyPlan("Computer Sciencea", degreeCourses.get(0));

            StudyPlanDAO studyPlanDAO = (StudyPlanDAO) context.getBean("studyPlanDAO");
            studyPlanDAO.create(businessStudyPlan);

            student = new Student("GavinKing" + i, "123456", "Gavin", "King", true, businessStudyPlan);
            student.setEmail("gezu_tse" + "@yahoo.com");
            student.setAge(23);
            student.setDateOfBirth(dateValue());
            studentDAO.create(student);

//			UserAttemptRegistrationDAO attemptRegistrationDAO = (UserAttemptRegistrationDAO) context
//					.getBean("userAttemptRegistrationDAO");
//			UserAttemptRegistration userAttemptRegistration = new UserAttemptRegistration();
//				userAttemptRegistration.setStatus("active");
//				attempt.getAttemptId();
//				userAttemptRegistration.setAttempt(attempt);
//				student.getUserId();
//				userAttemptRegistration.setStudent(student);
//				attemptRegistrationDAO.create(userAttemptRegistration);
        }

        return "redirect:/";
    }
    
    @RequestMapping(value = "addExamSession", method = RequestMethod.GET)
    public String addExamSession() throws ParseException {
    	ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
    	
    	DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
    	List<DegreeCourse> degrees = degreeCourseDAO.getAllDegrees();
    	
    	String startingD = "23-01-2017";
    	String endingD = "04-03-2017";
        String dateOfBirthFormat = "dd-mm-yyyy";
        DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);
        Date startingDate = format.parse(startingD);
        Date endingDate = format.parse(endingD);
    	
    	ExamSession e1 = new ExamSession(startingDate, endingDate, "2016/2017", degrees.get(0));
    	ExamSession e2 = new ExamSession(startingDate, endingDate, "2016/2017", degrees.get(1));
    	ExamSession e3 = new ExamSession(startingDate, endingDate, "2016/2017", degrees.get(2));
    	ExamSession e4 = new ExamSession(startingDate, endingDate, "2016/2017", degrees.get(3));
    	examSessionDAO.create(e1);
    	examSessionDAO.create(e2);
    	examSessionDAO.create(e3);
    	examSessionDAO.create(e4);
    	
    	return "redirect:/";
    }
    
    @RequestMapping(value = "addAttempts", method = RequestMethod.GET)
    public String addAttempts() throws ParseException {
    	
    	String dateOfBirthFormat = "dd-MM-yyyy";
        DateFormat format = new SimpleDateFormat(dateOfBirthFormat, Locale.ENGLISH);

    	AttemptDAO attemptDAO = (AttemptDAO) context.getBean("attemptDAO");
    	Date startingDate = format.parse("11-11-11");
     	Date endingDate = format.parse("11-11-12");
     	Date examDate = format.parse("25-11-12");
     	
     	ExamDAO examDAO = (ExamDAO) context.getBean("examDAO");
     	ArrayList<Exam> exams = (ArrayList<Exam>) examDAO.getAllExams();
     	
     	ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
     	ArrayList<Professor> profs = professorDAO.getAllProfessor();
     	
     	StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
     	ArrayList<Student> studs = (ArrayList<Student>) studentDAO.getAllStudents();

     	System.out.println("profs 0: "+profs.get(0).getUsername());
     	System.out.println("profs 1: "+profs.get(1).getUsername());
     	System.out.println("profs 2: "+profs.get(2).getUsername());
     	System.out.println("profs 3: "+profs.get(3).getUsername());
     	System.out.println("profs 4: "+profs.get(4).getUsername());
     	DegreeCourseDAO degreeCourseDAO = (DegreeCourseDAO) context.getBean("degreeCourseDAO");
        DegreeCourse dgC = new DegreeCourse("Informatica");
        degreeCourseDAO.create(dgC);
 
    	ExamSessionDAO examSessionDAO = (ExamSessionDAO) context.getBean("examSessionDAO");
        ExamSession es1 = new ExamSession(startingDate, endingDate, "anno1", dgC);
        examSessionDAO.create(es1);

        ArrayList<Attempt> attemtps2 = new ArrayList<>();
     	for(int i = 0; i < 20; i++) {
     		//da ricontrollare...non è fatto benissimo
     		Attempt a = new Attempt(examDate, "mt"+i, startingDate, endingDate, profs.get(i/5), exams.get(i/3), es1);
     		attemptDAO.create(a);
     		attemtps2.add(a);
     		System.out.println("nome prof: "+profs.get(i/5)+"...nome esame: "+exams.get(i/3).getName());
     	}
     	
     	UserAttemptRegistrationDAO userAttemptRegistrationDAO = (UserAttemptRegistrationDAO) context.getBean("userAttemptRegistrationDAO");        

     	for(int i = 0; i < attemtps2.size(); i++) {
        	System.out.println("coppie");
        	for( int j = 0; j < studs.size(); j++) {
        		System.out.println("att: "+attemtps2.get(i).getClassroom()+"...stud: "+studs.get(j).getFirstName());
        		UserAttemptRegistration userAR = new UserAttemptRegistration(attemtps2.get(i), studs.get(j));
        		userAttemptRegistrationDAO.create(userAR);
        	}
        }
     	
     	
    	return "redirect:/";
    	
    }

}
