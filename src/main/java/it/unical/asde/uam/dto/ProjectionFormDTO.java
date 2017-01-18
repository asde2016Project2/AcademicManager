package it.unical.asde.uam.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectionFormDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ArrayList<String> nameExams;
	
	private ArrayList<String> gradeExams;
	
	private ArrayList<String> cfuExams;
	
	public ProjectionFormDTO() {
		nameExams = new ArrayList<String>();
		gradeExams = new ArrayList<String>();
		cfuExams = new ArrayList<String>();
	}

	public ArrayList<String> getNameExams() {
		return nameExams;
	}

	public void setNameExams(ArrayList<String> nameExams) {
		this.nameExams = nameExams;
	}

	public ArrayList<String> getGradeExams() {
		return gradeExams;
	}

	public void setGradeExams(ArrayList<String> gradeExams) {
		this.gradeExams = gradeExams;
	}
	
	public ArrayList<String> getCfuExams() {
		return cfuExams;
	}
	
	public void setCfuExams(ArrayList<String> cfuExams) {
		this.cfuExams = cfuExams;
	}
}
