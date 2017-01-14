package it.unical.asde.uam.persistence;

import java.util.List;

import it.unical.asde.uam.model.Student;

/**
 *
 * @author Gezahegn
 */
public interface StudentDAO {

    void create(Student student);

    void removeStudent(Integer studentId);

    void updateStudent(Student student);

    Student retrieveStudent(Student student);

    boolean exists(Student student);

    List<Student> listStudent();

    Student getStudentById(int studentId);

    Integer getTotalNumberOfStudent();

    List<Student> listStudents(Integer pageNumber, Integer StudentPerPage);

    Student retrieveStudentForLogin(String username, String password);

}
