package it.unical.asde.uam.dto;

import java.io.Serializable;

public class ExamReserveFormDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;
	private String username;
	private int userAttemptRegId;

	
	
	public ExamReserveFormDTO() {
		super();
	}

	public ExamReserveFormDTO(String status, String username) {
		super();
		this.status = status;
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserAttemptRegId() {
		return userAttemptRegId;
	}

	public void setUserAttemptRegId(int userAttemptRegId) {
		this.userAttemptRegId = userAttemptRegId;
	}
	

}
