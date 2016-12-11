package it.unical.asde.uam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
*
* @author Nello
*/

@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable{
		
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id")	
	private Integer userId;
	
	@Column(name = "username", length = 255, nullable = false)
	private String username;
	
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	
	@Column(name = "first_name", length = 255, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 255, nullable = false)
	private String lastName;
	
	
	@Column(name = "status")
	private boolean status;
	
	public User(){
		super();
		this.userId = 0;
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.status = false;
	}
	
	public User(String username, String password, String firstName, String lastName, boolean status) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", status=" + status + "]";
	}


}
