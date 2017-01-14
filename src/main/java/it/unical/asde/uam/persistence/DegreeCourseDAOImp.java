package it.unical.asde.uam.persistence;

import java.util.List;
import org.hibernate.Query;
import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.DegreeCourse;

/**
 * @author Fabrizio  
 *
*/
public class DegreeCourseDAOImp implements DegreeCourseDAO {

	public DegreeCourseDAOImp() {}

    private DBHandler dbHandler;

    public DBHandler getDbHandler() {
        return dbHandler;
    }

    public void setDbHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public void create(DegreeCourse degreeCourse) {
        dbHandler.create(degreeCourse);
    }

    @Override
    public void update(DegreeCourse degreeCourse) {
        dbHandler.update(degreeCourse);
    }

    @Override
    public void deleteDegreeCourse(DegreeCourse degreeCourse) {
        dbHandler.delete(degreeCourse);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DegreeCourse> getAllDegrees() {
        String queryString = "from DegreeCourse c order by c.name";
        Query query = dbHandler.getSession().createQuery(queryString);
        List<DegreeCourse> dgs = (List<DegreeCourse>) query.list();
        dbHandler.close();
        return dgs;
    }
    
    @Override
    public DegreeCourse getDegreeCourseByName(String nameDegree) {
        String queryString = "from DegreeCourse where name=:nameDeg";
        Query query = dbHandler.getSession().createQuery(queryString);
        query.setParameter("nameDeg", nameDegree);
        DegreeCourse dC = (DegreeCourse) query.uniqueResult();
        dbHandler.close();
        return dC;
    }
    
    @Override
    public DegreeCourse retrieve(int grades) {
      return null;
    }
}
