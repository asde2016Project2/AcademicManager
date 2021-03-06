package it.unical.asde.uam.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
*
* @author Nello
*/

@Entity
@Table(name="professor")
public class Professor extends User{

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "professor")
    private Set<Attempt> attempts = new HashSet<>();
    
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