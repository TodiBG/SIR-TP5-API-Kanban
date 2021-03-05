package fr.istic.sir.jpa.dao.generic;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

/**
 * AbstractJpaDao (DAO générique) contient les methode géneriques, inchangeables et necessaire dans classe classe DAO.
 * @param <K>, est le type (String ou Long) de l'identifiant d'une instance de la classe.
 * @param <T>, est le nom de la classe fille qui etend la classe abstract AbstractJpaDao
 */
public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

	protected Class<T> clazz;

	protected EntityManager entityManager;

	public AbstractJpaDao() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}


	/**
	 * Enregistre l'objet pour la première fois dans la base de données.
	 * Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors l'objet sera enregistré et l'operation va
	 * renvoyer true. Sinon l'operation renvera false.
	 * @param entity L'objet passé en paramètre
	 * @return le resultat retourné, ( true ou false)
	 */
	public void create(T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		entityManager.persist(entity);
		t.commit();

	}


	/**
	 * Met l'objet à jour dans la base de données.
	 * Si l'identifiant de l'objet existe bien dejà dans la base de donnée alors l'objet sera mise à jour et l'operation va
	 * renvoyer true. Sinon l'operation renvera false.
	 * @param entity L'objet passé en paramètre
	 * @return le resultat retourné, ( true ou false)
	 */
	public T update(final T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		T res = entityManager.merge(entity);
		t.commit();
		return res;

	}

	/**
	 * Trouver un objet en base de données à partir de son identifiant.
	 * @param id l'identifiant de l'objet
	 * @return l'objet trouvé
	 */
	public T findById(K id) {
		return entityManager.find(clazz, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("select e from " + clazz.getName() + " as e",clazz).getResultList();
	}

	/**
	 * Supprimer un objet de la base de données
	 * @param entity l'objet à supprimer
	 */
	public void delete(T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		entityManager.remove(entity);
		t.commit();

	}


	/**
	 * Supprimer un objet de la base de données
	 * @param id l'identifiant de l'objet à supprimer
	 */
	public void deleteById(K id) {
		T entity = findById(id);
		delete(entity);
	}


}
