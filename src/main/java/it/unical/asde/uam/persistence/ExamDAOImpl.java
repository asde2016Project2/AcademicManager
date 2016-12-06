package it.unical.asde.uam.persistence;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import it.unical.asde.uam.model.Exam;



public class ExamDAOImpl implements ExamDAO {
	
	private SessionFactory sessionFactory;
	
	public ExamDAOImpl() {
		Configuration config = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(builder.build());
	}

	@Override
	public void create(Exam exam) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx= session.beginTransaction();
			session.save(exam);
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
		}
		session.close();		
	}

	@Override
	public Exam getExamById(int id) {
		
		Session session = sessionFactory.openSession();
		Exam exam = (Exam)session.createSQLQuery("SELECT * FROM exam WHERE id="+id).addEntity(Exam.class).uniqueResult();
		session.close();
		return exam;
		
	}

	@Override
	public Exam getExamByName(String name) {

		Session session = sessionFactory.openSession();
		Exam exam = (Exam)session.createSQLQuery("SELECT * FROM exam WHERE name='"+name+"'").addEntity(Exam.class).uniqueResult();
		session.close();
		return exam;
	}

	@Override
	@SuppressWarnings(value="unchecked")
	public List<Exam>  getExamsOfTotCredits(int cfu){
		Session session = sessionFactory.openSession();
		List<Exam> result = (List<Exam>) session.createSQLQuery("SELECT * FROM exam WHERE cfu='"+cfu+"'").addEntity(Exam.class).list();
		session.close();
		return result;
	}

	@Override
	public void saveUpdates(Exam exam) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(exam);
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}			
		
	}

	@Override
	public void deleteExam(Exam exam) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(exam);
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}		
		
	}

}
