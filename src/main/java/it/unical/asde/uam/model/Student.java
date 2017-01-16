package it.unical.asde.uam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*
* @author Nello
*/

@Entity
@Table(name="student")
public class Student extends User {

	private static final long serialVersionUID = 1L;
		
	@ManyToOne
	@JoinColumn(name = "studyPlanId")
	private StudyPlan studyPlan;
	
	// FOR NOW, WE LEAVE THE UNIDIRECTIONAL RELATIONSHIP ONLY
	//@OneToMany(mappedBy="userId")
    //private List<CareerExam>  careerExams = new ArrayList<>();
	
	@Column(name="photo",nullable=true,length=100000)
	private String photo;
	
	public Student(){
		super();
		studyPlan = null;
		photo = null;
			
	}
	
	public Student(String username, String password, String firstName, String lastName, boolean status, StudyPlan  studyPlan) {
		super(username,  password,  firstName,  lastName,  status);
		this.studyPlan = studyPlan;
	}

	public StudyPlan getStudyPlan() {
		return studyPlan;
	}

	public void setStudyPlan(StudyPlan studyPlan) {
		this.studyPlan = studyPlan;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getPhoto() {
		return photo;
	}

}
