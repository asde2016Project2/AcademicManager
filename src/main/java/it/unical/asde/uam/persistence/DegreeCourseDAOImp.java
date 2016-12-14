package it.unical.asde.uam.persistence;

import java.util.List;

import it.unical.asde.uam.dao.DAOImp;
import it.unical.asde.uam.model.DegreeCourse;
import it.unical.asde.uam.model.StudyPlan;

/**
 * @author Fabrizio  
**/

public class DegreeCourseDAOImp extends DAOImp<DegreeCourse> {
	
	public void create(DegreeCourse degreeCourse) {
		begin();
		getSession().save(degreeCourse);
		commit();
	}
	
	public DegreeCourse getDegreeCourseById(int id) {
        DegreeCourse degreeCourse = (DegreeCourse) getSession().createSQLQuery("SELECT * FROM degree_course WHERE id=" + id).addEntity(DegreeCourse.class).uniqueResult();
        return degreeCourse;
    }
	
    public DegreeCourse getExamByName(String name) {
    	DegreeCourse degreeCourse = (DegreeCourse) getSession().createSQLQuery("SELECT * FROM degree_course WHERE name='" + name + "'").addEntity(DegreeCourse.class).uniqueResult();
        return degreeCourse;
    }
    
    @SuppressWarnings(value = "unchecked")
    public List<StudyPlan> getStudyPlans() {
        List<StudyPlan> result = (List<StudyPlan>) getSession().createSQLQuery("SELECT study_plan FROM degree_course").addEntity(StudyPlan.class).list();
        return result;
    }

    public void saveUpdates(DegreeCourse degreeCourse) {
        begin();
        getSession().update(degreeCourse);
        commit();
    }
    
    public void deleteDegreeCourse(DegreeCourse degreeCourse) {
        begin();
        getSession().delete(degreeCourse);
        commit();
    }
}
