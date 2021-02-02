package fr.istic.sir.jpa.dao.generic;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<K, T extends Serializable> {

   void create (final T entity);
 
   T update(final T entity);

   T findById(final K id);

   List<T> findAll();
 
   void delete(final T entity);
 
   void deleteById(final K entityId);
}