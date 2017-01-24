package it.unical.asde.uam.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Francesco Bruno
 *
 * A simple DTO class for server-side validation of login form with Spring
 * Annotations
 *
 *
 */
public class SessionFormDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 3, max = 30)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String startingDate;

    @NotNull
    @Size(min = 3, max = 30)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String endingDate;

    @NotNull
    private String degreeCourse;

    @NotNull
    private String academicYear;

    public SessionFormDTO() {
        startingDate = "";
        endingDate = "";
        degreeCourse = "";
        academicYear = "";
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getDegreeCourse() {
        return degreeCourse;
    }

    public void setDegreeCourse(String degreeCourse) {
        this.degreeCourse = degreeCourse;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

}
