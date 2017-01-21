package it.unical.asde.uam.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


/**
 *
 * @author Nello
 * 
 * A simple DTO class for server-side validation of registration form
 * with Spring Annotations
 * 
 * 
 */

public class StudentFormDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	@NotEmpty()   
	private String username;

	@NotEmpty()   
	private String password;

	@NotEmpty()   
	private String firstName;

	@NotEmpty()   
	private String lastName;

	@Size(max = 200)
	@Email()
	@NotEmpty()   
	private String email;

	
	@Min(18)
	@Max(130)
	@NotNull  
	private Integer age;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "The date of birth must be set")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Past()
	private Date dateOfBirth;

	//!!!!!!!!	
	@NotEmpty()   
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStudyPlanId() {
		return studyPlanId;
	}

	public void setStudyPlanId(String studyPlanId) {
		this.studyPlanId = studyPlanId;
	}


}
