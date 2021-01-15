package fr.istic.sir.jpa.dao;

import fr.istic.sir.jpa.EntityManager.EntityManagerHelper;
import fr.istic.sir.jpa.entities.Tag;

import javax.persistence.EntityManager;
import java.util.List;

public class TagDao implements Dao<Tag> {

    /**
     * Enregistre l'objet pour la première fois dans la base de données.
     * Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors l'objet sera enregistré et l'operation va
     * renvoyer true. Sinon l'operation renvera false.
     * @param tag L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    @Override
    public boolean create(Tag tag) {
        boolean succes = false ;
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Tag.class,tag.getId())  == null ){
            em.persist(tag);
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
     * @param tag L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    @Override
    public boolean update(Tag tag) {
        boolean succes = false ;

        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Tag.class,tag.getId())  != null ){
            em.merge(tag);
            succes = true ;
            EntityManagerHelper.commit();
        }


        EntityManagerHelper.closeEntityManager();

        return succes ;
    }

    /**
     * Sauvegarde l'état de l'objet dans la base dde données
     *  Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors une nouvelle ligne sera créée, sinon
     *@param tag, l'objet sera mise à jour
     */
    @Override
    public void save(Tag tag) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Tag.class,tag.getId())  != null ){
            em.merge(tag);
        }else {
            em.persist(tag);
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
    public Tag find(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Tag tag = em.find(Tag.class, id);
        EntityManagerHelper.closeEntityManager();
        return tag;
    }

    /**
     * Supprimer un objet de la base de données
     * @param id l'identifiant de l'objet à supprimer
     */
    @Override
    public void delete(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        Tag tag = em.find(Tag.class, id);

        EntityManagerHelper.beginTransaction();
        em.remove(tag);

        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }



    /**
     * Trouver et renvoie la liste des enregistrés
     * @return
     */
    @Override
    public List<Tag> findAll() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<Tag> tags = em.createQuery("Select t from Tag t")
                .getResultList();
        em.close();
        return tags;
    }
}