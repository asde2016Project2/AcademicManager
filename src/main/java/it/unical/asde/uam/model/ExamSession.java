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

@Entity
@Table(name = "exam_session")
public class ExamSession implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "session_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sessionId;

	@Column(name = "startingDate")
	private Date startingDate;

	@Column(name = "endingDate")
	private Date endingDate;

	@Column(name = "academicYear")
	private int academicYear;

	@Column(name = "status")
	private String status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examSession")
	private Set<Attempt> attempts = new HashSet<Attempt>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "degree_course_id")
	private DegreeCourse degreeCourse;

	public ExamSession() {
		this.startingDate = new Date();
		this.endingDate = new Date();
		this.academicYear = 0;
	}

	public ExamSession(Date startingDate, Date endingDate, int academicYear, DegreeCourse degreeCourse) {
		super();
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.academicYear = academicYear;
		this.degreeCourse = degreeCourse;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public int getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	public Set<Attempt> getAttempts() {
		return attempts;
	}

	public void setAttempts(Set<Attempt> attempts) {
		this.attempts = attempts;
	}

	public DegreeCourse getDegreeCourse() {
		return degreeCourse;
	}

	public void setDegreeCourse(DegreeCourse degreeCourse) {
		this.degreeCourse = degreeCourse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
