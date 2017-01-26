package it.unical.asde.uam.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "attempt")
public class Attempt implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
//	@NotNull
	private Professor professor;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "career_exam_id", nullable = false)
	// private CareerExam careerExam;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exam_id")
	private Exam exam;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exam_session_id")
	private ExamSession examSession;
	
	

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "attempt_id")
	private List<UserAttemptRegistration> userAttemptRegistrations = new ArrayList<>();

	
	private String startingDataString;
    private String endingDataString;
	
    
	public Attempt() {
	}

	public Attempt(Date examDate, String classroom, Date startRegistrationDate, Date endRegistrationDate,
			Professor professor, Exam exam, ExamSession examSession) { // CareerExam
																		// careerExam,
		super();

		this.examDate = examDate;
		this.classroom = classroom;
		this.startRegistrationDate = startRegistrationDate;
		this.endRegistrationDate = endRegistrationDate;
		this.professor = professor;
		// this.careerExam = careerExam;
		this.exam = exam;
		this.examSession = examSession;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    	this.setStartingDataString(sdf.format(this.startRegistrationDate));
    	this.setEndingDataString(sdf.format(this.endRegistrationDate));
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

	// public CareerExam getCareerExam() {
	// return careerExam;
	// }
	//
	// public void setCareerExam(CareerExam careerExam) {
	// this.careerExam = careerExam;
	// }

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public ExamSession getExamSession() {
		return examSession;
	}

	public void setExamSession(ExamSession examSession) {
		this.examSession = examSession;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserAttemptRegistration> getUserAttemptRegistrations() {
		return userAttemptRegistrations;
	}

	public void setUserAttemptRegistrations(List<UserAttemptRegistration> userAttemptRegistrations) {
		this.userAttemptRegistrations = userAttemptRegistrations;
	}

	public String getStartingDataString() {
		return startingDataString;
	}

	public void setStartingDataString(String startingDataString) {
		this.startingDataString = startingDataString;
	}
	
	public String getEndingDataString() {
		return endingDataString;
	}

	public void setEndingDataString(String endingDataString) {
		this.endingDataString = endingDataString;
	}

	

}
