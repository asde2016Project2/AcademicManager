package it.unical.asde.uam.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.unical.asde.uam.helper.Accepted;
import it.unical.asde.uam.helper.Booking;

@Entity
@Table(name = "user_attempt_registration")
public class UserAttemptRegistration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_at_reg_id")
    private int userAtRegId;
    
    @Column(name="status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attempt_id", nullable = false)
    private Attempt attempt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Student student;

    @Enumerated(EnumType.STRING)
	@Column(name="booking",nullable=true, columnDefinition = "varchar(32) default 'CANCEL'")
	private Booking booking = Booking.CANCEL;
    
    
    public UserAttemptRegistration() {
        this.attempt = new Attempt();
        this.student = new Student();
        this.status = "";
    }

    public UserAttemptRegistration(Attempt attempt, Student student) {
        this.attempt = attempt;
        this.student = student;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserAtRegId() {
        return userAtRegId;
    }

    public void setUserAtRegId(int userAtRegId) {
        this.userAtRegId = userAtRegId;
    }

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

    
    
    
}
