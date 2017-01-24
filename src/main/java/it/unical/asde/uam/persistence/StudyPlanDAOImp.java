package it.unical.asde.uam.persistence;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.StudyPlan;


/**
 * @author Fabrizio  
 *
 */

public class StudyPlanDAOImp implements StudyPlanDAO {

		public StudyPlanDAOImp() {}

	    private DBHandler dbHandler;

	    public DBHandler getDbHandler() {
	        return dbHandler;
	    }

	    public void setDbHandler(DBHandler dbHandler) {
	        this.dbHandler = dbHandler;
	    }

	    @Override
	    public void create(StudyPlan studyPlan) {
	        dbHandler.create(studyPlan);
	    }

	    @Override
	    public void update(StudyPlan studyPlan) {
	        dbHandler.update(studyPlan);
	    }

	    @Override
	    public void deleteStudyPlan(StudyPlan studyPlan) {
	        dbHandler.delete(studyPlan);
	    }

	    @SuppressWarnings("unchecked")
	    @Override
	    public List<StudyPlan> getAllPlans() {
	    	
	        String queryString = "from StudyPlan c order by c.name";
	        Query query = dbHandler.getSession().createQuery(queryString);
	        dbHandler.begin();
	        List<StudyPlan> dgs = (List<StudyPlan>) query.list();
	        dbHandler.commit();
	        return dgs;
	    }
	    
	    @Override
	    public StudyPlan retrieve(int id) {
	    	Session session = dbHandler.getSessionFactory().openSession();
	        String queryString = "from StudyPlan where id = :id";
	        Query query = session.createQuery(queryString);
	        query.setParameter("id",id);
	        dbHandler.begin();
	        StudyPlan studyPlan = (StudyPlan) query.uniqueResult();
	        dbHandler.commit();
	        return studyPlan;
	    }
}
