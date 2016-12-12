package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DAOImp;
import static it.unical.asde.uam.dao.DAOImp.getSession;
import java.util.List;

import org.hibernate.SessionFactory;

import it.unical.asde.uam.model.Exam;

public class ExamDAOImp extends DAOImp<Exam>  {

        
    public void create(Exam exam) {

        begin();
        getSession().save(exam);
        commit();

    }


    public Exam getExamById(int id) {

        Exam exam = (Exam) getSession().createSQLQuery("SELECT * FROM exam WHERE id=" + id).addEntity(Exam.class).uniqueResult();

        return exam;

    }

    
    public Exam getExamByName(String name) {

        Exam exam = (Exam) getSession().createSQLQuery("SELECT * FROM exam WHERE name='" + name + "'").addEntity(Exam.class).uniqueResult();
        return exam;
    }

    
    @SuppressWarnings(value = "unchecked")
    public List<Exam> getExamsOfTotCredits(int cfu) {
        List<Exam> result = (List<Exam>) getSession().createSQLQuery("SELECT * FROM exam WHERE cfu='" + cfu + "'").addEntity(Exam.class).list();
        return result;
    }

    
    public void saveUpdates(Exam exam) {
        begin();
        getSession().update(exam);
        commit();

    }

    
    public void deleteExam(Exam exam) {
        begin();
        getSession().delete(exam);
        commit();

    }

}
