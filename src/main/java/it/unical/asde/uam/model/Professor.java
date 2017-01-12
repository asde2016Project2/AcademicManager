package it.unical.asde.uam.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
*
* @author Nello
*/

@Entity
@Table(name="professor")
public class Professor extends User{

	private static final long serialVersionUID = 1L;
	
	public Professor(){
		super();
		
	}
	
	public Professor(String username, String password, String firstName, String lastName, boolean status) {
		super( username,  password,  firstName,  lastName,  status);
		
	}
        
        @Override
        public String toString(){
            return this.getUsername()+" "+this.getFirstName()+" "+this.getLastName();
        }

}