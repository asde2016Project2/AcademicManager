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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="degree_course")
public class DegreeCourse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="degree_course_id")
	private int degreeCourseId;
	
	@Column(name="name", unique=true, nullable=false, length=255)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="degree_course")	
	private List<StudyPlan> studyPlans = new ArrayList<StudyPlan>();
	
	public DegreeCourse(){}
	
	public DegreeCourse(String name){ 
		this.name = name; 
	}

	public int getDegreeCourseId() {
		return degreeCourseId;
	}

	public void setDegreeCourseId(int degreeCourseId) {
		this.degreeCourseId = degreeCourseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
