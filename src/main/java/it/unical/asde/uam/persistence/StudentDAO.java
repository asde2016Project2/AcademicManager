package it.unical.asde.uam.persistence;

import java.util.ArrayList;
import java.util.List;

import it.unical.asde.uam.model.CareerExam;
import it.unical.asde.uam.model.Exam;
import it.unical.asde.uam.model.Student;
import it.unical.asde.uam.model.StudyPlan;

/**
 *
 * @author Nello
 */
public interface StudentDAO {

    void create(Student student);

    void deleteStudent(Student student);

    Student retrieve(String username);

    void update(Student student);

    List<Student> getAllStudents();

    StudyPlan getStudyPlan(Student student);

    Student retrieveForLogin(String username, String password);

    List<Student> getAllStudentsToAcceptRefuse();

    boolean isEmpty();

    boolean register(Student u);

    Student retrieveByEmail(String email);

    ArrayList<Exam> getAllExamDone(int studentId);

    ArrayList<Exam> getAllExam(int studentId);

    ArrayList<Student> getStudentForStraordinaryExamSession(List<Student> studentList);

	ArrayList<CareerExam> getInformationStudent(String studentUsername);

}
