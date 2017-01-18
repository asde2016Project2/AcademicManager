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

import org.aspectj.internal.lang.annotation.ajcPrivileged;

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
    

    @Column(name = "mandatory")
    private boolean mandatory;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "attempt_id")
//    private Set<Attempt> attempts = new HashSet<Attempt>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public CareerExam() {
    }

    public CareerExam(boolean done, int grade,  boolean mandatory, Student student, Exam exam) {
        this.done = done;
        this.grade = grade;
        this.mandatory = mandatory;
//        this.attempts = attempts;
        this.student = student;
        this.exam = exam;
    }
//    
//    public CareerExam(boolean done, int grade,  boolean mandatory, Student student, Exam exam) {
//        this.done = done;
//        this.grade = grade;
//        this.mandatory = mandatory;
////        this.attempts = attempts;
//        this.student = student;
//        this.exam = exam;
//    }

    public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public CareerExam(boolean done, int grade,  boolean mandatory) {
        this.done = done;
        this.grade = grade;
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

   

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

//    public Set<Attempt> getAttempts() {
//        return attempts;
//    }
//
//    public void setAttempts(Set<Attempt> attempts) {
//        this.attempts = attempts;
//    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
