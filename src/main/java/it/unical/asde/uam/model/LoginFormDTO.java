package it.unical.asde.uam.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Francesco Bruno
 * 
 * A simple DTO class for server-side validation of login form
 * with Spring Annotations
 * 
 * 
 */
public class LoginFormDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min=3, max=30)
    private String username;

    @NotNull
    @Size(min=3,max=30)
    private String password;
    
    @NotEmpty    
    private String profileType;
  
    
    public LoginFormDTO() {
        username = "";
        password = "";       
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

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    
       
    
}
