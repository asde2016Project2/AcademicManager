package it.unical.asde.uam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Nello
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id")
    private Integer userId;

    @NotEmpty()    
    @Column(name = "username", length = 255, nullable = false,unique=true)
    private String username;

    @NotEmpty()
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @NotEmpty()
    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;

    @NotEmpty()
    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;
    
    @Column(name = "email", nullable = false, length = 200, unique=true)
    @Size(max = 200)
    @Email()
    @NotEmpty()
    private String email;

    @Column(name = "age", nullable = false)
    @Min(18)
    @Max(130)
    @NotNull
    private Integer age;

    @Column(name = "dateOfBirth", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "The date of birth must be set")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past()
    private Date dateOfBirth;
    @Column(name = "status")
    private boolean status;

    public User(String username, String password, String firstName, String lastName, boolean status) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    
    
    
    public User() {
        username = "";
        password = "";
        email = "";
        age = 18;
        dateOfBirth = null;
    }

    public User(String username, String password, String email, Integer age, Date dateOfBirth) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
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

    
    
    
    
    @Override
    public String toString() {
        return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
                + firstName + ", lastName=" + lastName + ", status=" + status + "]";
    }

}
