package it.unical.asde.uam.persistence;

import java.util.List;
import it.unical.asde.uam.model.StudyPlan;


/**
 *
 * @author Fabrizio
*/
public interface StudyPlanDAO {

   void create(StudyPlan studyPlan);

   void deleteStudyPlan(StudyPlan studyPlan);

   StudyPlan retrieve(int studyPlan);

   void update(StudyPlan studyPlan);
   
   List<StudyPlan> getAllPlans();
}