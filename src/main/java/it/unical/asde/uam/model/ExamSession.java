package it.unical.asde.uam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam_session")
public class ExamSession implements Serializable{

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
	
	public ExamSession() {
		this.startingDate = new Date();
		this.endingDate = new Date();
		this.academicYear = 0;
	}
	
	
	public ExamSession( Date startingDate, Date endingDate, int academicYear) {
		super();
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.academicYear = academicYear;
	}
	
	public int getId() {
		return sessionId;
	}
	public void setId(int id) {
		this.sessionId = id;
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
	
}
