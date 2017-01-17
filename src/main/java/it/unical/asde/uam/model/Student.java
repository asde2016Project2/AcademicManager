package it.unical.asde.uam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.unical.asde.uam.helper.Accepted;

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
	
	@Column(name = "id_number", nullable = false)
	private int idNumber;//matricola: non ci devono essere matricole uguali e non deve essere nullo

	@Lob
	@Column(name="photo",nullable=true,length=20971520)
	private byte[] photo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="accepted",nullable=true,columnDefinition = "varchar(32) default 'NOT_ACCEPTED'")
	private Accepted accepted = Accepted.NOT_ACCEPTED;
  
	
	public Student(){
		super();
		studyPlan = null;
		photo = null;
			
	}
	
	public Student(String username, String password, String firstName, String lastName, boolean status, StudyPlan  studyPlan) {
		super(username,  password,  firstName,  lastName,  status);
		this.studyPlan = studyPlan;
	}
	
	public Student(int idNumber, String username, String password, String firstName, String lastName, boolean status, StudyPlan  studyPlan) {
		super(username,  password,  firstName,  lastName,  status);
		this.studyPlan = studyPlan;
		this.idNumber = idNumber;
	}

	public StudyPlan getStudyPlan() {
		return studyPlan;
	}

	public void setStudyPlan(StudyPlan studyPlan) {
		this.studyPlan = studyPlan;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	public byte[] getPhoto() {
		return photo;
	}
	
	public void setAccepted(Accepted accepted) {
		this.accepted = accepted;
	}
	
	public Accepted getAccepted() {
		return accepted;
	}

}
