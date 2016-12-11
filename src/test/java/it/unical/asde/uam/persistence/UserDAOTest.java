package it.unical.asde.uam.persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.asde.uam.model.Administrator;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.model.User;


/**
*
* @author Nello
*/

public class UserDAOTest {
	
	
		static UserDAO userDao;
		static List<User> users;
		
		@BeforeClass
		static public void init()
		{
			userDao = new UserDAO();
			users = new ArrayList<User>();
			initDB();
		}
		
		static public void initDB()
		{
			Administrator admin = new Administrator("admin", "pw",  "albus", "silente", false);
			users.add(admin);
			userDao.create(admin);
			
			for(int i = 0; i < 10; i++)
			{
				User m = null;
				if(i%2 == 0)					
					m = new Student("student"+i, "pw"+i,  "pierino", "limiti", false, null);
				else
					m = new Professor("prof"+i, "pw"+i,  "severus", "piton", false);
				users.add(m);
				userDao.create(m);
			}			
			
			
		}
		
		
		@Test
		public void testAdminFirstName()
		{			
			 // USING HIBERNATE QUERY LANGUAGE
			assertEquals("albus", userDao.getAdminByIdHQL(1).getFirstName());
				        
			
			 // USING SQL
			//assertEquals("albus", userDao.getAdminById(1).getFirstName());
				        
		}
		
		@Test
		public void testProfessorsLastname()
		{
			User firstProf = userDao.getAllProfessors().get(0);
			assertEquals("piton", firstProf.getLastName());
		}
		
		
		@Test
		public void testStudentStudyPlan()
		{
			//TODO
		}	
		
		
	}

