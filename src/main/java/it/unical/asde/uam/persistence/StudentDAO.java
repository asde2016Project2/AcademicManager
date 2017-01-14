package it.unical.asde.uam.persistence;



	import java.util.List;

	import it.unical.asde.uam.model.Student;
	import it.unical.asde.uam.model.StudyPlan;

	/**
	*
	* @author Nello
	*/
	public interface StudentDAO {

	   void create(Student student);

	   void deleteStudent(Student student);

	   Student retrieve(String username);

	   void update(Student student);
	   
	   List<Student> getAllStudents();
	   
	   StudyPlan getStudyPlan(Student student);
	   
	   Student retrieveForLogin(String username,String password);
	   
	   boolean register(Student u);
	   
	   Student retrieveByEmail(String email);
			   
	}
