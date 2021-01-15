package fr.istic.sir.jpa.dao;

import fr.istic.sir.jpa.EntityManager.EntityManagerHelper;
import fr.istic.sir.jpa.entities.Section;
import javax.persistence.EntityManager;
import java.util.List;

public class SectionDao implements Dao<Section> {

    /**
     * Enregistre l'objet pour la première fois dans la base de données.
     * Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors l'objet sera enregistré et l'operation va
     * renvoyer true. Sinon l'operation renvera false.
     * @param var_section L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    @Override
    public boolean create(Section var_section) {
        boolean succes = false ;
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Section.class,var_section.getId())  == null ){
            em.persist(var_section);
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
     * @param var_section L'objet passé en paramètre
     * @return le resultat retourné, ( true ou false)
     */
    @Override
    public boolean update(Section var_section) {
        boolean succes = false ;

        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Section.class,var_section.getId())  != null ){
            em.merge(var_section);
            succes = true ;
            EntityManagerHelper.commit();
        }


        EntityManagerHelper.closeEntityManager();

        return succes ;
    }

    /**
     * Sauvegarde l'état de l'objet dans la base dde données
     *  Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors une nouvelle ligne sera créée, sinon
     *@param var_section, l'objet sera mise à jour
     */
    @Override
    public void save(Section var_section) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();

        if(em.find(Section.class,var_section.getId())  != null ){
            em.merge(var_section);
        }else {
            em.persist(var_section);
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
    public Section find(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Section var_section = em.find(Section.class, id);
        EntityManagerHelper.closeEntityManager();
        return var_section;
    }

    /**
     * Supprimer un objet de la base de données
     * @param id l'identifiant de l'objet à supprimer
     */
    @Override
    public void delete(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        Section var_section = em.find(Section.class, id);

        EntityManagerHelper.beginTransaction();
        em.remove(var_section);

        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }



    /**
     * Trouver et renvoie la liste des enregistrés
     * @return
     */
    @Override
    public List<Section> findAll() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<Section> var_sections = em.createQuery("Select t from Section t")
                .getResultList();
        em.close();
        return var_sections;
    }
}