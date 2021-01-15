package fr.istic.sir.jpa.dao;

import java.util.List;

public interface Dao<T> {
    /**
     * Enregistre l'objet pour la première fois dans la base de données.
     * Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors l'objet sera enregistré et l'operation va
     * renvoyer true. Sinon l'operation renvera false.
     * @param objet L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    boolean create(T objet);


    /**
     * Met l'objet à jour dans la base de données.
     * Si l'identifiant de l'objet existe bien dejà dans la base de donnée alors l'objet sera mise à jour et l'operation va
     * renvoyer true. Sinon l'operation renvera false.
     * @param objet L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    boolean update(T objet);


    /**
     * Sauvegarde l'état de l'objet dans la base dde données
     *  Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors une nouvelle ligne sera créée, sinon
     *  l'objet sera mise à jour
     * @param objet
     */
    void save(T objet);


    /**
     * Trouver un objet en base de données à partir de son identifiant.
     * @param id l'identifiant de l'objet
     * @return l'objet trouvé
     */
    T find(long id);

    /**
     * Supprimer un objet de la base de données
     * @param id l'identifiant de l'objet à supprimer
     */
    void delete(long id);

    /**
     * Trouver et renvoie la liste des enregistrés
     * @return
     */
    List<T> findAll();
}