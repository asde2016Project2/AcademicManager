package it.unical.asde.uam.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.helper.Accepted;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;

/**
 * @author Nello
 *
 */
public class StudentDAOImp implements StudentDAO {

    public StudentDAOImp() {
    }

    private DBHandler dbHandler;

    public DBHandler getDbHandler() {
        return dbHandler;
    }

    public void setDbHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public void create(Student student) {
        dbHandler.create(student);
    }

    @Override
    public void update(Student student) {
        dbHandler.update(student);
    }

    @Override
    public void deleteStudent(Student student) {
        dbHandler.delete(student);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getAllStudents() {

        String queryString = "from Student"; //c order by c.name";
        Query query = dbHandler.getSession().createQuery(queryString);
        dbHandler.begin();
        List<Student> dgs = (List<Student>) query.list();
        dbHandler.commit();
        return dgs;
    }

    @Override
    public StudyPlan getStudyPlan(Student student) {

        int studentId = student.getUserId();
        String queryString = "SELECT S.studyPlan FROM Student S WHERE S.userId =:givenId";
        Query query = dbHandler.getSession().createQuery(queryString);
        query.setParameter("givenId", studentId);
        dbHandler.begin();
        StudyPlan e = (StudyPlan) query.uniqueResult();
        dbHandler.commit();
        return e;

    }

    @Override
    public Student retrieve(String username) {
        String hql = "from Student where username =:username";

        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("username", username);
        dbHandler.begin();
        Student stud = (Student) query.uniqueResult();
        dbHandler.commit();
        return stud;
    }

    @Override
    public Student retrieveForLogin(String username, String password) {
        String hql = "from Student where username=:username AND password=:password";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        dbHandler.begin();
        Student stud = (Student) query.uniqueResult();
        dbHandler.commit();
        return stud;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getAllStudentsToAcceptRefuse() {
        String hql = "from Student where accepted =:value";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("value", Accepted.NOT_ACCEPTED);
        dbHandler.begin();
        List<Student> students = (List<Student>) query.list();
        dbHandler.commit();
        return students;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isEmpty() {
        String queryString = "from Student";
        Query query = dbHandler.getSession().createQuery(queryString);
        dbHandler.begin();
        List<Student> students = (List<Student>) query.list();
        dbHandler.commit();
        if (students == null) {
            return true;
        }
        return (students.isEmpty());
    }

    @Override
    public boolean register(Student u) {

        //if already exist email or username
        if (retrieve(u.getUsername()) != null || retrieveByEmail(u.getEmail()) != null) {
            return false;
        }

        //create
        create(u);

        //check created
        if (retrieve(u.getUsername()) == null) {
            return false;
        }

        return true;
    }

    @Override
    public Student retrieveByEmail(String email) {
        String hql = "from Student where email =:email";

        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("email", email);
        dbHandler.begin();
        Student student = (Student) query.uniqueResult();
        dbHandler.commit();
        return student;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Exam> getAllExamDone(int studentId) {

        String hql = "from CareerExam where student.userId=:studentID AND done=:true";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("studentID", studentId);
        query.setParameter("true", true);
        dbHandler.begin();
        ArrayList<CareerExam> careerExams = (ArrayList<CareerExam>) query.list();
        dbHandler.commit();
        
        ArrayList<Exam> exams = new ArrayList<>();

        for (int i = 0; i < careerExams.size(); i++) {
            exams.add(careerExams.get(i).getExam());
        }

        return exams;
    }

    @Override
    public ArrayList<Exam> getAllExam(int studentId) {

        String hql = "from CareerExam where student.userId=:studentID";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("studentID", studentId);

        dbHandler.begin();
        ArrayList<CareerExam> careerExams = (ArrayList<CareerExam>) query.list();
        dbHandler.commit();
        
        ArrayList<Exam> exams = new ArrayList<>();

        for (int i = 0; i < careerExams.size(); i++) {
            exams.add(careerExams.get(i).getExam());
        }

        return exams;
    }

    @Override
    public ArrayList<Student> getStudentForStraordinaryExamSession(List<Student> studentList) {

        ArrayList<Student> studentExamSession = new ArrayList<>();

        for (int i = 0; i < studentList.size(); i++) {

            ArrayList<Exam> exams = new ArrayList<>();
            ArrayList<Exam> exams2 = new ArrayList<>();
            int totalCredits = 0;
            int totalCreditsEarned = 0;

//			tutti gli esami
            String hql = "from CareerExam where student.userId=:studentIDD";
            Query query = dbHandler.getSession().createQuery(hql);
            query.setParameter("studentIDD", studentList.get(i).getUserId());
         
            ArrayList<CareerExam> careerExams = (ArrayList<CareerExam>) query.list();
                        
            for (int q = 0; q < careerExams.size(); q++) {
                exams.add(careerExams.get(q).getExam());
                totalCredits = totalCredits + (careerExams.get(q).getExam().getCfu());

            }
         
//			quelli fatti
            String hql2 = "from CareerExam where student.userId=:studentID AND done=:true";
            Query query2 = dbHandler.getSession().createQuery(hql2);
            query2.setParameter("studentID", studentList.get(i).getUserId());
            query2.setParameter("true", true);

         
            ArrayList<CareerExam> careerExams2 = (ArrayList<CareerExam>) query2.list();
            
            for (int j = 0; j < careerExams2.size(); j++) {
                exams2.add(careerExams2.get(j).getExam());
                totalCreditsEarned = totalCreditsEarned + (careerExams2.get(j).getExam().getCfu());
            }
            
         

            if ((exams.size() - exams2.size() <= 2) && (exams.size() - exams2.size() >= 1)) {
                if (totalCredits - totalCreditsEarned <= 10) {
                    studentExamSession.add(studentList.get(i));
                }

            }

        }
        return studentExamSession;
    }

    @Override
    public ArrayList<CareerExam> getInformationStudent(String studentUsername) {
    	
    	String hql = "from CareerExam where student.username=:studentUN";
		Query query = dbHandler.getSession().createQuery(hql);
		query.setParameter("studentUN", studentUsername);
		
		ArrayList<CareerExam> careerExams = (ArrayList<CareerExam>) query.list();
		
		return careerExams;
    }
    
   
}
