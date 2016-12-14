package it.unical.asde.uam.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_attempt_registration")
public class UserAttemptRegistration {

	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
	private Attempt attempt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
	private Student student;
	
    private int grade;
	
	
	public UserAttemptRegistration() {
		this.attempt = new Attempt();
		this.student = new Student();
		this.grade = 0;
	}
	public UserAttemptRegistration(Attempt attempt, Student student, int grade) {
		this.attempt = attempt;
		this.student = student;
		this.grade = grade;
	}
	public Attempt getAttempt() {
		return attempt;
	}
	public void setAttempt(Attempt attempt) {
		this.attempt = attempt;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}