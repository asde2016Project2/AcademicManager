/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.CareerExam;

/**
 *
 * @author Gezahegn
 */
public interface CareerExamDAO {

    void create(CareerExam careerExam);

    void deleteCareerExam(CareerExam careerExam);

    CareerExam retrieve(int careerExam);

    void update(CareerExam careerExam);
    
}
