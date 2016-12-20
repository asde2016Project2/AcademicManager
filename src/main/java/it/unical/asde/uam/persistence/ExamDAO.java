/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.Exam;
import java.util.List;

/**
 *
 * @author Gezahegn
 */
public interface ExamDAO {

    void create(Exam exam);
    
    Exam getExamById(int id);

    Exam retrieve(String examName);

    void update(Exam exam);

    void delete(Exam exam);

    boolean exists(String username);
    
    List<Exam> getAllExams();

}
