package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.Exam;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamDAOImp implements ExamDAO {

    private static final Logger logger = LoggerFactory.getLogger(ExamDAOImp.class);
    private DBHandler dbHandler;

    public DBHandler getDbHandler() {
        return dbHandler;
    }

    public void setDbHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public ExamDAOImp() {
    }

    @Override
    public void create(Exam exam) {
        dbHandler.create(exam);
    }

    @Override
    public void removeExam(Integer examId) {

        // Return the persistent instance of the given entity class with the given identifier
        Exam exam = (Exam) dbHandler.getSession().load(Exam.class, new Integer(examId));
        // Remove a persistent instance from the datastore.
        if (exam != null) {
            dbHandler.getSession().delete(exam);
        }
        System.out.println("finally it's works now ---" + exam);
        logger.info("Exam deleted successfully, Exam details= " + exam);
    }

    @Override
    public Exam retrieve(String examName) {
        Session session = dbHandler.getSessionFactory().openSession();
        String queryString = "from Exam where name =:ex";
        Query query = session.createQuery(queryString);
        query.setParameter("ex", examName);
        Exam e = (Exam) query.uniqueResult();
        dbHandler.close();
        return e;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Exam> getAllExams() {

        String queryString = "from Exam c order by c.name";
        Query query = dbHandler.getSession().createQuery(queryString);
        dbHandler.begin();
        List<Exam> exams = (List<Exam>) query.list();
        dbHandler.commit();
        return exams;
    }

    @Override
    public Exam getExamById(int id) {
        String hql = "from Exam where id =:id";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("id", id);
        dbHandler.begin();
        Exam exam = (Exam) query.uniqueResult();

        // Session session = dbHandler.getSessionFactory().getCurrentSession();
        // Exam exam = (Exam) session.load(Exam.class, id);
        // LOG.info("exam successfully loaded, exam info: " + exam);
        dbHandler.commit();
        return exam;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllNameExams() {

        String queryString = "select c.name from Exam c order by c.name";
        Query query = dbHandler.getSession().createQuery(queryString);
        List<String> exams = (List<String>) query.list();
        dbHandler.close();
        return exams;
    }

    @Override
    public void updateExam(Exam exam) {
        // Retrieve session from Hibernate using dbHandler.getSession()  

        // Create hql String
        String hql = "UPDATE Exam set name = :name Where id = :examId";
        // Create a Query instance for the given HQL query string.
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("name", exam.getName());
        query.setParameter("examId", exam.getId());
        // Persists to HSQLDB
        int result = query.executeUpdate();
        logger.info("Exam updated successfully, Exam Details=" + exam);
    }

    
    @Override
    public boolean exists(Exam exam) {
        return exists(exam.getName());
    }
    
    @Override
    public boolean exists(String examName) {
         return retrieve(examName) != null;
    }

}
