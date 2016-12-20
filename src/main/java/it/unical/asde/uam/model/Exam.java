package it.unical.asde.uam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column(nullable = false, length = 50)
    private String name; //, unique=true no

    @Column(nullable = false)
    private int cfu;

    @Column(nullable = false)
    private int code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exam")
    private List<CareerExam> careerExams = new ArrayList<CareerExam>();

    public Exam() {
    }

    public Exam(String name, int cfu, int code) {
        super(); //id is not needed here
        this.name = name;
        this.cfu = cfu;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<CareerExam> getCareerExams() {
        return careerExams;
    }

    public void setCareerExams(List<CareerExam> careerExams) {
        this.careerExams = careerExams;
    }

   

}
