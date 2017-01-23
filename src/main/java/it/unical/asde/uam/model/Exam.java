package it.unical.asde.uam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name; //, unique=true no

    @Column(name = "cfu",nullable = false)
    private int cfu;

    @Column(name = "code",nullable = false)
    private int code;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "exam")
    private Set<Attempt> attempts = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "exam")
    private Set<StudyPlanExam> studyPlanExams = new HashSet<>();

    public Exam() {
    }

    public Exam(String name, int cfu, int code) {
        super(); //id is not needed here
        this.name = name;
        this.cfu = cfu;
        this.code = code;
    }

    public Exam(int id, int cfu, int code, String name, Set<Attempt> attempts, Set<StudyPlanExam> studyPlanExams) {
        this.id = id;
        this.cfu = cfu;
        this.code = code;
        this.name = name;
//        this.attempts = attempts;
        this.studyPlanExams = studyPlanExams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Set<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(Set<Attempt> attempts) {
        this.attempts = attempts;
    }

    public Set<StudyPlanExam> getStudyPlanExams() {
        return studyPlanExams;
    }

    public void setStudyPlanExams(Set<StudyPlanExam> studyPlanExams) {
        this.studyPlanExams = studyPlanExams;
    }

}
