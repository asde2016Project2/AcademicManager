/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.dao;

import java.io.Serializable;
import static java.lang.Math.log;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Gezahegn
 * @param <T>
 */
public abstract class DAOImp<T extends Serializable> implements DAO<T> {

    private Class<T> persistentClass;
    private static Session session;
    Transaction tx;
    private static SessionFactory sessionFactory;

    protected DAOImp() {
        this.persistentClass = ((Class) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        Configuration config = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        sessionFactory = config.buildSessionFactory(builder.build());
    }

    public Class<T> getPersistentClass() {
        return this.persistentClass;
    }

    public static Session getSession() {
        if (session == null) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    public void begin() {
        getSession().flush();
        this.tx = getSession().beginTransaction();
    }

    @Override
    public void commit() {
        tx.commit();
    }

    @Override
    public int rollback() {
        try {
            if (!tx.wasRolledBack() && !tx.wasCommitted()) {
                tx.rollback();
            }
            return -1;
        } catch (HibernateException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void close() {
        getSession().close();
    }

    /**
     * Create a new Entity or update an existing one
     *
     * @param entity
     * @return
     */
    @Override
    public int saveOrUpdateEntity(T entity) {
        boolean persistent = false;
        try {
            begin();
            if (getSession().contains(entity)) {
                persistent = true;
            } else {
                Field field = entity.getClass().getDeclaredFields()[0];
                field.setAccessible(true);
                field.set(entity, (Object) null);
            }
            getSession().saveOrUpdate(entity);
            commit();
            getSession().clear();
            return 1;

        } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            rollback();
            if (persistent == false) {
                getSession().delete(entity);
                entity = null;
                getSession().clear();
            }
            return -1;
        } finally {
        }
    }

    public int saveOrUpdateSingleEntity(T entity) {
        boolean persistent = false;
        try {
            begin();
            if (getSession().contains(entity)) {
                persistent = true;
            } else {
                Field field = entity.getClass().getDeclaredFields()[0];
                field.setAccessible(true);
                field.set(entity, (Object) null);
            }
            getSession().saveOrUpdate(entity);
            commit();
            return 1;
        } catch (Exception ex) {
            rollback();
            if (!persistent) {
                getSession().delete(entity);
                entity = null;
            }

            ex.printStackTrace();
            return -1;
        } finally {
        }
    }

    public void clear() {
    }

    /**
     * Create a new Entity an existing one
     *
     * @param entity
     * @return
     */
    @Override
    public int saveObject(T entity) {
        boolean persistent = false;
        try {
            begin();
            if (getSession().contains(entity)) {
                persistent = true;
            } else {
                Field field = entity.getClass().getDeclaredFields()[0];
                field.setAccessible(true);
                field.set(entity, (Object) null);
            }
            Serializable id = getSession().save(entity);
            commit();
            getSession().clear();
            return Integer.parseInt(id.toString());

        } catch (Exception ex) {
            rollback();
            if (persistent == false) {
                getSession().delete(entity);
                entity = null;
                getSession().clear();
            }

            ex.printStackTrace();
            return -1;
        } finally {
        }
    }

    /**
     * Create a new Entity an existing one
     *
     * @param entity
     * @return
     */
    @Override
    public Serializable saveEntity(T entity) {
        try {
            begin();
            Serializable id = getSession().save(entity);
            commit();
            return id;
        } catch (Exception ex) {

            ex.printStackTrace();
            return -1;

        } finally {
        }
    }

    /**
     * Update Entity an existing one
     *
     * @param entity
     * @return
     */
    @Override
    public int updateEntity(T entity) {
        try {
            begin();
            getSession().merge(entity);
            commit();
            return 1;
        } catch (Exception ex) {

            ex.printStackTrace();

            return -1;
        } finally {
        }
    }

    /**
     * Delete Entity from data store
     *
     * @param entity
     * @return
     */
    @Override
    public int deleteEntity(T entity) {
        try {
            begin();
            getSession().delete(entity);
            commit();
            return 1;
        } catch (HibernateException ex) {
            rollback();
            ex.printStackTrace();

            return -1;
        }
    }

    /**
     * Retrieve an Entity from the data store
     *
     * @param id identifier of the Entity to be retrieved
     * @param lock
     * @return entity represented by the identifier provided
     */
    @Override
    public T getEntity(Serializable id, boolean lock) {
        T entity;
        if (lock) {
            entity = (T) getSession().get(getPersistentClass(), id, LockMode.UPGRADE);
        } else {
            entity = (T) getSession().get(getPersistentClass(), id);
        }
        return entity;
    }

    /**
     * Retrieve an Entity from the data store
     *
     * @param id identifier of the Entity to be retrieved
     * @param lock
     * @return entity represented by the identifier provided
     */
    public T getById(T id, boolean lock) {
        T entity;
        if (lock) {
            entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
        } else {
            entity = (T) getSession().load(getPersistentClass(), id);
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    /**
     * Use this inside subc
     *
     * @return lasses as a convenience method.
     */
    @Override
    public List<T> findByCriteria() {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        return crit.list();
    }

    @Override
    public void flush() {
    }
}
//
