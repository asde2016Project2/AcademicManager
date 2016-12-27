/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gezahegn
 */
@Entity
@Table(name = "career_exam")
public class CareerExam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "career_exam_id")
    private int careerExamId;

    @Column(name = "done")
    private boolean done;

    @Column(name = "grade")
    private int grade;
    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "mandatory")
    private boolean mandatory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "careerExam")
    private Set<Attempt> attempts = new HashSet<Attempt>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Student student;

    public CareerExam() {
    }

    public CareerExam(boolean done, int grade, Date examDate, boolean mandatory, Set<Attempt> attempts, Student student) {
        this.done = done;
        this.grade = grade;
        this.examDate = examDate;
        this.mandatory = mandatory;
        this.attempts = attempts;
        this.student = student;
    }

    public CareerExam(boolean done, int grade, Date examDate, boolean mandatory) {
        this.done = done;
        this.grade = grade;
        this.examDate = examDate;
        this.mandatory = mandatory;
    }

    public int getCareerExamId() {
        return careerExamId;
    }

    public void setCareerExamId(int careerExamId) {
        this.careerExamId = careerExamId;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Set<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(Set<Attempt> attempts) {
        this.attempts = attempts;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
