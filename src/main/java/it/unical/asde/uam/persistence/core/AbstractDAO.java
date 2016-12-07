package it.unical.asde.uam.persistence.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;

public abstract class AbstractDAO<T extends Serializable> {

    private Class<T> currentObjectClass;
    private SessionFactory sessionFactory;

    public void AbstractDAO() {
        Configuration config = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties());
        
        sessionFactory = config.buildSessionFactory(builder.build());

        this.currentObjectClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

    }

    public Class<T> getCurrentObjectClass() {
        return currentObjectClass;
    }

    public boolean create(T myObject) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            T serializedObject = (T) session.save(myObject);
            tx.commit();
            return true;
        }
        catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return false;
    }

    public boolean update(T myObject) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(myObject);
            tx.commit();
            return true;
        }
        catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return false;
    }

    public boolean delete(T myObject) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(myObject);
            tx.commit();
            return true;
        }
        catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return false;
    }

    
    
    public T findOne(Criterion... criterion) {

        Session session = sessionFactory.openSession();
        
        Criteria crit = session.createCriteria(getCurrentObjectClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        crit.setMaxResults(1);
        
        List result = crit.list();

        session.close();

        if (result.size() <= 0) {
            return null;
        }

        return (T) result.get(0);
    }

    public List<T> findAll() {
        return findByCriteria();
    }

    public List<T> findByCriteria(Criterion... criterion) {

        Session session = sessionFactory.openSession();        
        Criteria crit = session.createCriteria(getCurrentObjectClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

    public List<T> findByQuery(String query) {

        Session session = sessionFactory.openSession();
        List<T> result = (List<T>) session.createSQLQuery(query).addEntity(getCurrentObjectClass()).list();
        session.close();
        return result;

    }

}
