package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DAOImp;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.StudyPlan;

/**
 * @author Fabrizio
**/

public class StudyPlanDAOImp extends DAOImp<StudyPlan> {
	
	public void create(StudyPlan studyPlan){
		begin();
		getSession().save(studyPlan);
		commit();
	}
	
	public StudyPlan getStudyPlanById(int id) {
		StudyPlan studyPlan = (StudyPlan) getSession().createSQLQuery("SELECT * FROM study_plan WHERE studyPlanId=" + id).addEntity(StudyPlan.class).uniqueResult();
        return studyPlan;
    }
	
    public StudyPlan getStudyPlanByName(String name) {
    	StudyPlan studyPlan = (StudyPlan) getSession().createSQLQuery("SELECT * FROM study_plan WHERE name='" + name + "'").addEntity(StudyPlan.class).uniqueResult();
        return studyPlan;
    }
    
    public DegreeCourse getDegreeCourse() {
    	DegreeCourse degreeCourse = (DegreeCourse) getSession().createSQLQuery("SELECT degree_course FROM study_plan").addEntity(DegreeCourse.class).list();
        return degreeCourse;
    }

    public void saveUpdates(StudyPlan studyPlan) {
        begin();
        getSession().update(studyPlan);
        commit();
    }
    
    public void deleteDegreeCourse(StudyPlan studyPlan) {
        begin();
        getSession().delete(studyPlan);
        commit();
    }
}
