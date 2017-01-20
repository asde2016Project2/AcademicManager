package it.unical.asde.uam.dto;

import java.io.Serializable;

public class StudentFormDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String age;
	private String dateOfBirth;	
	//!!!!!!!!
	private String studyPlanId;
	
	public StudentFormDTO(){
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStudyPlanId() {
		return studyPlanId;
	}

	public void setStudyPlanId(String studyPlanId) {
		this.studyPlanId = studyPlanId;
	}


}
