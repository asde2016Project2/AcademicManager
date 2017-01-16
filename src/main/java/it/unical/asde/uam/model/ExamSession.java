package it.unical.asde.uam.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
    @Column(name = "exam_session_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int examSessionId;

    @Column(name = "startingDate")
    private Date startingDate;

    @Column(name = "endingDate")
    private Date endingDate;

    @Column(name = "academicYear")
    private String academicYear;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "examSession")
    private Set<Attempt> attempts= new HashSet<Attempt>();

    @ManyToOne( fetch=FetchType.LAZY)//, cascade = CascadeType.ALL)
    @JoinColumn(name="degree_course_id")
    private DegreeCourse degreeCourse;
    
    private String startingDataString;
    private String endingDataString;
    
    public ExamSession() {
        this.startingDate = new Date();
        this.endingDate = new Date();
        this.academicYear = "";
    }
    

    public ExamSession(Date startingDate, Date endingDate, String academicYear, DegreeCourse degreeCourse) {
        super();
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.academicYear = academicYear;
        this.degreeCourse = degreeCourse;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    	this.setStartingDataString(sdf.format(this.startingDate));
    	this.setEndingDataString(sdf.format(this.endingDate));
    }

    public ExamSession(Date startingDate, Date endingDate, String academicYear) {
        super();
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.academicYear = academicYear;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    	this.setStartingDataString(sdf.format(this.startingDate));
    	this.setEndingDataString(sdf.format(this.endingDate));
    }

    public int getSessionId() {
        return examSessionId;
    }

    public void setSessionId(int sessionId) {
        this.examSessionId = sessionId;
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

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
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