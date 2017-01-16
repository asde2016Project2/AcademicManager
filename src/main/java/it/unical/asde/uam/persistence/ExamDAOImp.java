package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.Exam;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamDAOImp implements ExamDAO {

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
    public void delete(Exam exam) {
        dbHandler.delete(exam);
    }

    @Override
    public void update(Exam exam) {
        dbHandler.update(exam);
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

    @Override
    public boolean exists(String username) {
        return retrieve(username) != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Exam> getAllExams() {

        String queryString = "from Exam c order by c.name";
        Query query = dbHandler.getSession().createQuery(queryString);
        List<Exam> exams = (List<Exam>) query.list();
        dbHandler.close();
        return exams;
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
    public Exam getExamById(int id) {
        String hql = "from Exam where id =:id";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("id", id);
        Exam exam = (Exam) query.uniqueResult();
         dbHandler.close();
        // Session session = dbHandler.getSessionFactory().getCurrentSession();
        // Exam exam = (Exam) session.load(Exam.class, id);
        // LOG.info("exam successfully loaded, exam info: " + exam);
        return exam;
    }
    
}
