package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DBHandler;

public class StudyPlanExamDAOImp {

    private DBHandler dBHandler;

    public DBHandler getdBHandler() {
        return dBHandler;
    }

    public void setdBHandler(DBHandler dBHandler) {
        this.dBHandler = dBHandler;
    }

    public StudyPlanExamDAOImp() {
        super();
    }

//    public List<Exam> getExamsByStudyPlan(StudyPlan sp) {
//
//        Criterion c = Restrictions.eq("study_plan", sp.getStudyPlanId());
//        return (List<Exam>) dBHandler.findByCriteria(c);
//
//    }
//
//    public ArrayList<StudyPlan> getStudyPlansForExam(Exam e) {
//
//        Criterion c = Restrictions.eq("exam", e.getId());
//        return (ArrayList<StudyPlan>) dBHandler.findByCriteria(c);
//    }
//
//    public String getPeriodForExam(StudyPlan studyPlan, Exam e) {
//
//        ArrayList<Criterion> criteriaList = new ArrayList<Criterion>();
//        criteriaList.add(Restrictions.eq("exam", e.getId()));
//        criteriaList.add(Restrictions.eq("study_plan", studyPlan.getStudyPlanId()));
//
//        StudyPlanExam spe = (StudyPlanExam) dBHandler.findOne(criteriaList.toArray(new Criterion[criteriaList.size()]));
//
//        return spe.getPeriod();
//    }

}
