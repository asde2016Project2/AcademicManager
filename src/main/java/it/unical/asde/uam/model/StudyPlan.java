package it.unical.asde.uam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
 * @author Fabrizio
**/

@Entity
@Table(name = "study_plan")
public class StudyPlan implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int studyPlanId;
	
	@Column(name="name",unique=true,nullable=false,length=256)
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="degree_course_id", nullable=false)
	private DegreeCourse degreeCourse;
	
	
	@OneToMany(fetch= FetchType.EAGER,mappedBy="studyPlan")
	private List<StudyPlanExam> studyPlanExams= new ArrayList<>();
	
	
	public StudyPlan() {}

	public StudyPlan(String name, DegreeCourse degreeCourse) {
		super();
		this.name = name;
		this.degreeCourse = degreeCourse;
	}

	public int getStudyPlanId() {
		return studyPlanId;
	}

	public void setStudyPlanId(int studyPlanId) {
		this.studyPlanId = studyPlanId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DegreeCourse getDegreeCourse() {
		return degreeCourse;
	}

	public void setDegreeCourse(DegreeCourse degreeCourse) {
		this.degreeCourse = degreeCourse;
	}

	public List<StudyPlanExam> getStudyPlanExams() {
		return studyPlanExams;
	}

	public void setStudyPlanExams(List<StudyPlanExam> studyPlanExams) {
		this.studyPlanExams = studyPlanExams;
	}
	
	
	
}
