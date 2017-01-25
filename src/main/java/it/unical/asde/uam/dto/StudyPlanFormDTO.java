package it.unical.asde.uam.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

public class StudyPlanFormDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String degreeCourseId;
    
    @NotNull
    @NotEmpty
    private List<String> examList;

    public StudyPlanFormDTO() {
        examList = new ArrayList<String>();
        degreeCourseId = "";
        name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegreeCourseId() {
        return degreeCourseId;
    }

    public void setDegreeCourseId(String degreeCourseId) {
        this.degreeCourseId = degreeCourseId;
    }

    public ArrayList<String> getExamList() {
        return (ArrayList<String>) examList;
    }

    public void setExamList(List<String> examList) {
        this.examList = examList;
    }

}
