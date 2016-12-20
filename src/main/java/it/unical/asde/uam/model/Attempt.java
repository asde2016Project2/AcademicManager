package it.unical.asde.uam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attempt")
public class Attempt implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "attempt_id")
    private int attemptId;

    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "classroom")
    private String classroom;

    @Column(name = "startRegistrationDate")
    private Date startRegistrationDate;

    @Column(name = "endRegistrationDate")
    private Date endRegistrationDate;

//	@Column(name = "professor") //foreign key with professor??
    @ManyToOne
    @JoinColumn(name = "examSession", nullable = false)
    private Professor professor;

    public Attempt() {

        this.examDate = new Date();
        this.classroom = "";
        this.startRegistrationDate = new Date();
        this.endRegistrationDate = new Date();
        this.professor = professor;
    }

    public Attempt(int attemptId, Date examDate, String classroom,
            Date startRegistrationDate, Date endRegistrationDate, Professor professor) {
        super();

        this.examDate = examDate;
        this.classroom = classroom;
        this.startRegistrationDate = startRegistrationDate;
        this.endRegistrationDate = endRegistrationDate;
        this.professor = professor;
    }

    public int getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(int attemptId) {
        this.attemptId = attemptId;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Date getStartRegistrationDate() {
        return startRegistrationDate;
    }

    public void setStartRegistrationDate(Date startRegistrationDate) {
        this.startRegistrationDate = startRegistrationDate;
    }

    public Date getEndRegistrationDate() {
        return endRegistrationDate;
    }

    public void setEndRegistrationDate(Date endRegistrationDate) {
        this.endRegistrationDate = endRegistrationDate;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}
