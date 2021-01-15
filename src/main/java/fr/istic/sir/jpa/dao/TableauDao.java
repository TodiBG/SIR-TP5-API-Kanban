package fr.istic.sir.jpa.dao;

import fr.istic.sir.jpa.EntityManager.EntityManagerHelper;
import fr.istic.sir.jpa.entities.Tableau;

import javax.persistence.EntityManager;
import java.util.List;

public class TableauDao implements Dao<Tableau> {

    /**
     * Enregistre l'objet pour la première fois dans la base de données.
     * Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors l'objet sera enregistré et l'operation va
     * renvoyer true. Sinon l'operation renvera false.
     * @param tab L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    @Override
    public boolean create(Tableau tab) {
        boolean succes = false ;
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Tableau.class,tab.getId())  == null ){
            em.persist(tab);
            succes  = true ;
            em.getTransaction().commit();
        }



        EntityManagerHelper.closeEntityManager();

        return succes ;
    }


    /**
     * Met l'objet à jour dans la base de données.
     * Si l'identifiant de l'objet existe bien dejà dans la base de donnée alors l'objet sera mise à jour et l'operation va
     * renvoyer true. Sinon l'operation renvera false.
     * @param tab L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    @Override
    public boolean update(Tableau tab) {
        boolean succes = false ;

        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Tableau.class,tab.getId())  != null ){
            em.merge(tab);
            succes = true ;
            EntityManagerHelper.commit();
        }


        EntityManagerHelper.closeEntityManager();

        return succes ;
    }

    /**
     * Sauvegarde l'état de l'objet dans la base dde données
     *  Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors une nouvelle ligne sera créée, sinon
     *@param tab, l'objet sera mise à jour
     */
    @Override
    public void save(Tableau tab) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Tableau.class,tab.getId())  != null ){
            em.merge(tab);
        }else {
            em.persist(tab);
        }

        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }


    /**
     * Trouver un objet en base de données à partir de son identifiant.
     * @param id l'identifiant de l'objet
     * @return l'objet trouvé
     */
    @Override
    public Tableau find(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Tableau tab = em.find(Tableau.class, id);
        EntityManagerHelper.closeEntityManager();
        return tab;
    }

    /**
     * Supprimer un objet de la base de données
     * @param id l'identifiant de l'objet à supprimer
     */
    @Override
    public void delete(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        Tableau tab = em.find(Tableau.class, id);

        EntityManagerHelper.beginTransaction();
        em.remove(tab);

        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }



    /**
     * Trouver et renvoie la liste des enregistrés
     * @return
     */
    @Override
    public List<Tableau> findAll() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<Tableau> tabs = em.createQuery("Select t from Tableau t")
                .getResultList();
        em.close();
        return tabs;
    }
}