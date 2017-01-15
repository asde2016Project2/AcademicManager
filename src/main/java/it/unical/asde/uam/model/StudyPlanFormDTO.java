package it.unical.asde.uam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudyPlanFormDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nameStudyPlan;
	
	private String nameDegreeCourse;
	
	private List<String> nameExams;
	
	public StudyPlanFormDTO() {
		nameExams = new ArrayList<String>();
		nameDegreeCourse = "";
		nameStudyPlan = "";
	}

	public String getNameStudyPlan() {
		return nameStudyPlan;
	}

	public void setNameStudyPlan(String nameStudyPlan) {
		this.nameStudyPlan = nameStudyPlan;
	}

	public String getNameDegreeCourse() {
		return nameDegreeCourse;
	}

	public void setNameDegreeCourse(String nameDegreeCourse) {
		this.nameDegreeCourse = nameDegreeCourse;
	}

	public ArrayList<String> getNameExams() {
		return (ArrayList<String>) nameExams;
	}

	public void setNameExams(ArrayList<String> nameExams) {
		this.nameExams = nameExams;
	}
}
