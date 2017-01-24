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
        String queryString = "from DegreeCourse";// c order by c.name";
        Query query = dbHandler.getSession().createQuery(queryString);
        dbHandler.begin();
        List<DegreeCourse> dgs = (List<DegreeCourse>) query.list();
        dbHandler.commit();
        return dgs;
    }
    
    
    @SuppressWarnings("unchecked")
	@Override
    public List<String> getAllNameDegrees() {
    	String queryString = "select c.name from DegreeCourse c order by c.name";
        Query query = dbHandler.getSession().createQuery(queryString);
        dbHandler.begin();
        List<String> dgs = (List<String>) query.list();
        dbHandler.commit();
        return dgs;
    }
    
    @Override
    public DegreeCourse retrieve(int grades) {
      return null;
    }
    
    @Override
    public DegreeCourse retrieveByName(String name) {
    	String queryString = "from DegreeCourse c where name=:name";
    	Query query = dbHandler.getSession().createQuery(queryString);
    	query.setParameter("name",name);
    	dbHandler.begin();
    	DegreeCourse degreeCourse = (DegreeCourse) query.uniqueResult();
    	dbHandler.commit();
    	return degreeCourse;
    }

    @Override
    public DegreeCourse retrieveById(int id) {
        String queryString = "from DegreeCourse c where degreeCourseId=:id";
    	Query query = dbHandler.getSession().createQuery(queryString);
    	query.setParameter("id",id);
    	dbHandler.begin();
    	DegreeCourse degreeCourse = (DegreeCourse) query.uniqueResult();
    	dbHandler.commit();
    	return degreeCourse;
    }
    
    @Override
    public DegreeCourse getDegreeCourseByName(String nameDegree) {
        String queryString = "from DegreeCourse where name=:nameDeg";
        Query query = dbHandler.getSession().createQuery(queryString);
        query.setParameter("nameDeg", nameDegree);
        dbHandler.begin();
        DegreeCourse dC = (DegreeCourse) query.uniqueResult();
        dbHandler.commit();
        return dC;
    }
    
    
}
