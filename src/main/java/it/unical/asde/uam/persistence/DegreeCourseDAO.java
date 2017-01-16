package it.unical.asde.uam.persistence;

import java.util.List;
import it.unical.asde.uam.model.DegreeCourse;

/**
 *
 * @author Fabrizio
*/
public interface DegreeCourseDAO {

   void create(DegreeCourse degreeCourse);

   void deleteDegreeCourse(DegreeCourse degreeCourse);

   DegreeCourse retrieve(int degreeCourse);

   void update(DegreeCourse degreeCourse);
   
   List<DegreeCourse> getAllDegrees();
   
   List<String> getAllNameDegrees();
   
   DegreeCourse retrieveByName(String name);
   
   DegreeCourse retrieveById(int id);
}