package fr.istic.sir.jpa.dao;

import fr.istic.sir.jpa.EntityManager.EntityManagerHelper;
import fr.istic.sir.jpa.entities.Utilisateur;

import javax.persistence.EntityManager;
import java.util.List;

public class UtilisateurDao implements Dao<Utilisateur> {

    /**
     * Enregistre l'objet pour la première fois dans la base de données.
     * Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors l'objet sera enregistré et l'operation va
     * renvoyer true. Sinon l'operation renvera false.
     * @param utilisateur L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    @Override
    public boolean create(Utilisateur utilisateur) {
        boolean succes = false ;
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Utilisateur.class,utilisateur.getId())  == null ){
            em.persist(utilisateur);
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
     * @param utilisateur L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    @Override
    public boolean update(Utilisateur utilisateur) {
        boolean succes = false ;
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Utilisateur.class,utilisateur.getId())  != null ){
            em.merge(utilisateur);
            succes = true ;
            EntityManagerHelper.commit();
        }
        EntityManagerHelper.closeEntityManager();

        return succes ;
    }

    /**
     * Sauvegarde l'état de l'objet dans la base dde données
     *  Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors une nouvelle ligne sera créée, sinon
     *@param utilisateur, l'objet sera mise à jour
     */
    @Override
    public void save(Utilisateur utilisateur) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Utilisateur.class,utilisateur.getId())  != null ){
            em.merge(utilisateur);
        }else {
            em.persist(utilisateur);
        }

        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }


    /**
     * Supprimer un objet de la base de données
     * @param id l'identifiant de l'objet à supprimer
     */
    @Override
    public Utilisateur find(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        EntityManagerHelper.closeEntityManager();
        return utilisateur;
    }


    /**
     * Supprimer un objet de la base de données
     * @param id l'identifiant de l'objet à supprimer
     */
    @Override
    public void delete(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        Utilisateur utilisateur = em.find(Utilisateur.class, id);

        EntityManagerHelper.beginTransaction();
        em.remove(utilisateur);

        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }



    /**
     * Trouver et renvoie la liste des enregistrés
     * @return
     */
    @Override
    public List<Utilisateur> findAll() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<Utilisateur> utilisateurs = em.createQuery("Select t from Utilisateur t")
                .getResultList();
        em.close();
        return utilisateurs;
    }
}