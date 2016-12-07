package it.unical.asde.uam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "study_plan_exam")
public class StudyPlanExam implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "study_plan")
    private StudyPlan studyPlan;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "exam")
    private Exam exam; //, unique=true no

    @Column(nullable = false, length = 50, name="period")
    private String period; //, unique=true no

    public StudyPlanExam(StudyPlan studyPlan, Exam exam, String period) {
        super(); //id is not needed here
        this.studyPlan = studyPlan;
        this.exam = exam;
        this.period = period;
    }

    public StudyPlan getStudyPlan() {
        return studyPlan;
    }

    public void setStudyPlan(StudyPlan studyPlan) {
        this.studyPlan = studyPlan;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

}
