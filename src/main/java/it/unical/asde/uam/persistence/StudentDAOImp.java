package it.unical.asde.uam.persistence;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.Student;

/**
 *
 * @author Gezahegn
 */
public class StudentDAOImp implements StudentDAO {

    private static final Logger logger = LoggerFactory.getLogger(StudentDAOImp.class);

    private DBHandler dbHandler;

    public DBHandler getDbHandler() {
        return dbHandler;
    }

    public void setDbHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public StudentDAOImp() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void create(Student student) {

        System.out.println("information==========");
        dbHandler.create(student);
    }

    @Override
    public void removeStudent(Integer studentId) {

        Student student = (Student) dbHandler.getSession().load(Student.class, new Integer(studentId));
        // Remove a persistent instance from the datastore.
        if (student != null) {
            dbHandler.getSession().delete(student);
        }
        System.out.println("finally it's works now ---" + student);

        logger.info("Student deleted successfully, Student details= " + student);
    }

    @Override
    public void updateStudent(Student student) {
        // Retrieve session from Hibernate using dbHandler.getSession()

        // Create hql String
        String hql = "UPDATE Student set user.username = :name Where id = :userId";
        // Create a Query instance for the given HQL query string.
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("name", student.getUsername());
        query.setParameter("userId", student.getUserId());
        // Persists to HSQLDB
        query.executeUpdate();
        logger.info("Student updated successfully, Student Details=" + student);
    }

    // I changed this method because when i tried to update the class it insert
    // a new record
    // it doesn't keep the object but the object detached automatically and
    // instantiate a new
    // transient object
    
    @Override
    public Student retrieveStudent(Student student) {
        // Session session = dbHandler.getSessionFactory().openSession();
        String queryString = "from Student as student where student.username = :username";
        Query query = dbHandler.getSession().createQuery(queryString);
        query.setParameter("username", student.getUsername());
        try {
            dbHandler.begin();
            Student careerExams = (Student) query.uniqueResult();
            System.out.println("when i am gone------" + student.getUsername());
            dbHandler.commit();
            return careerExams;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean exists(Student student) {
        return retrieveStudent(student) != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> listStudent() {

        String queryString = "from Student";
        Query query = dbHandler.getSession().createQuery(queryString);
        dbHandler.begin();
        List<Student> listOfStudent = (List<Student>) query.list();
        for (Student student : listOfStudent) {
            logger.info("Student successfully populated" + student);
        }
        dbHandler.commit();
        return listOfStudent;
    }

    @Override
    public Student getStudentById(int studentId) {
        String hql = "from Student AS student where student.userId =:id";
        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("id", studentId);
        dbHandler.begin();
        Student student = (Student) query.uniqueResult();

        // Session session = dbHandler.getSessionFactory().getCurrentSession();
        // Student student = (Student) session.get(Student.class,
        // id);
        logger.info("student successfully loaded, student info: " + student);
        dbHandler.commit();
        return student;
    }

    /*
	 * retrieving the total number of students from the db
     */
    @Override
    public Integer getTotalNumberOfStudent() {
        String hql = "SELECT COUNT(*) FROM Student";
        Query query = dbHandler.getSession().createQuery(hql);
        Long singleResult = (Long) query.uniqueResult();
        Integer numOfStudents = singleResult.intValue();
        logger.info("nr of students is {} ", numOfStudents);
        return numOfStudents;

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> listStudents(Integer pageNumber, Integer StudentPerPage) {
        int start = StudentPerPage * (pageNumber - 1);

        Query query = dbHandler.getSession().createQuery("from Student");
        query.setFirstResult(start);
        query.setMaxResults(StudentPerPage);
        dbHandler.begin();
        List<Student> studentList = query.list();

        for (Student student : studentList) {
            logger.info("Student List:" + student);
        }
        dbHandler.commit();
        return studentList;
    }

    @Override
    public Student retrieveStudentForLogin(String username, String password) {
        String hql = "from Student where username=:username AND password=:password";

        Query query = dbHandler.getSession().createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        dbHandler.begin();
        Student student = (Student) query.uniqueResult();
        dbHandler.commit();
        return student;
    }

}
