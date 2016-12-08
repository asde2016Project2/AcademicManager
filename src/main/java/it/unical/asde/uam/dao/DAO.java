/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Gezahegn
 * @param <T>
 * @param <ID>
 */
public interface DAO<T extends Serializable> {
   void begin();

    void commit();

    int rollback();

    void close();


    int saveOrUpdateEntity(T entity);

    public int saveOrUpdateSingleEntity(T entity);

    public int saveObject(T entity);

    Serializable saveEntity(T entity);

    public int updateEntity(T entity);

    int deleteEntity(T entity);

    T getEntity(Serializable id, boolean lock);

    T getById(T id, boolean lock);

    List<T> findAll();

    List<T> findByCriteria();

    void flush(); 
}
