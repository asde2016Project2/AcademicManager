package it.unical.asde.uam.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;



	/**
	 * @author Nello  
	*
	 */
public class StudyPlanExamDAOImp implements StudyPlanExamDAO{

		public StudyPlanExamDAOImp() {
	    }

	    private DBHandler dbHandler;

	    public DBHandler getDbHandler() {
	        return dbHandler;
	    }

	    public void setDbHandler(DBHandler dbHandler) {
	        this.dbHandler = dbHandler;
	    }

	   

	    @Override
	    public void create(StudyPlanExam studyPlanExam) {
	        dbHandler.create(studyPlanExam);
	    }

	    @Override
	    public void update(StudyPlanExam studyPlanExam) {
	        dbHandler.update(studyPlanExam);
	    }

	    @Override
	    public void deleteStudyPlanExam(StudyPlanExam studyPlanExam) {
	        dbHandler.delete(studyPlanExam);
	    }

	    @SuppressWarnings("unchecked")
	    @Override
	    public List<StudyPlanExam> getAllExamsOfAstudyPlan(StudyPlan sp) {

	    	int studyPlanId = sp.getStudyPlanId();
	        String queryString = "from StudyPlanExam s where s.studyPlan.studyPlanId =:sp";
	        Query query = dbHandler.getSession().createQuery(queryString);
	        query.setParameter("sp", studyPlanId);
	        List<StudyPlanExam> dgs = (List<StudyPlanExam>) query.list();
	        dbHandler.close();
	        return dgs;
	        
	    }
	    
	    @Override
	    public StudyPlanExam retrieve(int grades) {
	      return null;
	      //TODO
	    }

}