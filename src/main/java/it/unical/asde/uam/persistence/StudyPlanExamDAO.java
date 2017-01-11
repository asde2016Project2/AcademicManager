package it.unical.asde.uam.persistence;

import java.util.List;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;

/**
  *
  * @author Nello
 */

public interface StudyPlanExamDAO {
	
    void create(StudyPlanExam studyPlanExam);

    void deleteStudyPlanExam(StudyPlanExam studyPlanExam);

    StudyPlanExam retrieve(int studyPlanExam);

    void update(StudyPlanExam studyPlanExam);
    
    List<StudyPlanExam> getAllExamsOfAstudyPlan(StudyPlan sp);   
}