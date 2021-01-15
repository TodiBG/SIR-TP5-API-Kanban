package fr.istic.sir.jpa.dao;

import fr.istic.sir.jpa.EntityManager.EntityManagerHelper;
import fr.istic.sir.jpa.entities.Fiche;

import javax.persistence.EntityManager;
import java.util.List;

public class FicheDao implements Dao<Fiche> {

    /**
     * Enregistre l'objet pour la première fois dans la base de données.
     * Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors l'objet sera enregistré et l'operation va
     * renvoyer true. Sinon l'operation renvera false.
     * @param fiche L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    @Override
    public boolean create(Fiche fiche) {
        boolean succes = false ;
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Fiche.class,fiche.getId())  == null ){
            em.persist(fiche);
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
     * @param fiche L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    @Override
    public boolean update(Fiche fiche) {
        boolean succes = false ;

        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Fiche.class,fiche.getId())  != null ){
            em.merge(fiche);
            succes = true ;
            EntityManagerHelper.commit();
        }


        EntityManagerHelper.closeEntityManager();

        return succes ;
    }


    /**
     * Sauvegarde l'état de l'objet dans la base dde données
     *  Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors une nouvelle ligne sera créée, sinon
     *@param fiche, l'objet sera mise à jour
     */
    @Override
    public void save(Fiche fiche) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Fiche.class,fiche.getId())  != null ){
            em.merge(fiche);
        }else {
            em.persist(fiche);
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
    public Fiche find(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Fiche fiche = em.find(Fiche.class, id);
        EntityManagerHelper.closeEntityManager();
        return fiche;
    }


    /**
     * Supprimer un objet de la base de données
     * @param id l'identifiant de l'objet à supprimer
     */
    @Override
    public void delete(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        Fiche fiche = em.find(Fiche.class, id);

        EntityManagerHelper.beginTransaction();
        em.remove(fiche);

        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }



    /**
     * Trouver et renvoie la liste des enregistrés
     * @return
     */
    @Override
    public List<Fiche> findAll() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<Fiche> fiches = em.createQuery("Select t from Fiche t")
                .getResultList();
        em.close();
        return fiches;
    }
}