package it.unical.asde.uam.model;

import java.io.Serializable;
import java.util.Base64;

import it.unical.asde.uam.helper.Accepted;

public class AcceptingStudentFormDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String photo;
	
	private String username;

	private Accepted accepted;
	
	public AcceptingStudentFormDTO(){
		photo = null;
		username = null;
		accepted = Accepted.NOT_ACCEPTED;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setAccepted(Accepted accepted) {
		this.accepted = accepted;
	}
	
	public Accepted getAccepted() {
		return accepted;
	}
	
	public byte[] decodeBase64() {
		byte[] decoded = Base64.getDecoder().decode(photo);
		return decoded;
	}
}
