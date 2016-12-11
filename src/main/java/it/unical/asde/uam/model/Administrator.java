package it.unical.asde.uam.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
*
* @author Nello
*/

@Entity
@Table(name="administrator")
public class Administrator extends User{

	private static final long serialVersionUID = 1L;
	
	public Administrator(){
		super();
		
	}
	
	public Administrator( String username, String password, String firstName, String lastName, boolean status) {
		super( username,  password,  firstName,  lastName,  status);
		
	}

}
