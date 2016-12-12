package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.StudyPlan;
import it.unical.asde.uam.model.StudyPlanExam;
import it.unical.asde.uam.persistence.core.AbstractDAO;
import java.util.ArrayList;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class StudyPlanExamDAOImp extends AbstractDAO {

    public StudyPlanExamDAOImp() {
        super();
    }

    public ArrayList<Exam> getExamsByStudyPlan(StudyPlan sp) {

        Criterion c = Restrictions.eq("study_plan", sp.getStudyPlanId());
        return (ArrayList<Exam>) findByCriteria(c);

    }

    public ArrayList<StudyPlan> getStudyPlansForExam(Exam e) {

        Criterion c = Restrictions.eq("exam", e.getId());
        return (ArrayList<StudyPlan>) findByCriteria(c);
    }

    public String getPeriodForExam(StudyPlan studyPlan, Exam e) {

        ArrayList<Criterion> criteriaList = new ArrayList<>();
        criteriaList.add(Restrictions.eq("exam", e.getId()));
        criteriaList.add(Restrictions.eq("study_plan", studyPlan.getStudyPlanId()));

        StudyPlanExam spe = (StudyPlanExam) findOne(criteriaList.toArray(new Criterion[criteriaList.size()]));

        return spe.getPeriod();
    }

}
