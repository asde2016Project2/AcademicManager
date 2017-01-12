package it.unical.asde.uam.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;


	/**
	 * @author Nello  
	*
	 */
	public class StudentDAOImp implements StudentDAO{

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

	        String queryString = "from Student c order by c.name";
	        Query query = dbHandler.getSession().createQuery(queryString);
	        List<Student> dgs = (List<Student>) query.list();
	        dbHandler.close();
	        return dgs;
	    }
	    
	    
	    @Override
	    public StudyPlan getStudyPlan(Student student){
	    	    	
	    	int studentId = student.getUserId();
	    	String queryString = "SELECT S.studyPlan FROM Student S WHERE S.userId =:givenId";
	    	Query query = dbHandler.getSession().createQuery(queryString);
	        query.setParameter("givenId", studentId);
	        StudyPlan e = (StudyPlan) query.uniqueResult();
	        dbHandler.close();
	        return e;
	    	
	    }
	    
	    @Override
	    public Student retrieve(String username) {
	        String hql = "from Student where username =:username";

	        Query query = dbHandler.getSession().createQuery(hql);
	        query.setParameter("username", username);
	        Student stud = (Student) query.uniqueResult();
	        return stud;
	    }
	    
	    
	    @Override    
	    public Student retrieveForLogin(String username,String password) {
	        String hql = "from Student where username=:username AND password=:password";

	        Query query = dbHandler.getSession().createQuery(hql);
	        query.setParameter("username", username);
	        query.setParameter("password",password);
	        Student stud = (Student) query.uniqueResult();
	        return stud;
	    }


	   
	}