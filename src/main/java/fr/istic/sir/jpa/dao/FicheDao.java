package fr.istic.sir.jpa.dao;


import fr.istic.sir.jpa.dao.generic.AbstractJpaDao;
import fr.istic.sir.jpa.entities.Fiche;

import java.util.ArrayList;
import java.util.List;

public class FicheDao extends AbstractJpaDao<Long, Fiche> {
    public FicheDao(){
        super();
        this.clazz = Fiche.class ;
    }


    /**
     * Trouver les fiches selon le tableau et la section.
     * @param tableauID
     * @param sectionID
     * @return
     */
    public List<Fiche> findByTabAndSection(long tableauID, long sectionID) {
        List<Fiche> listBrute =  entityManager.createQuery("select e from " + clazz.getName() + " as e",clazz).getResultList();

         List<Fiche> list = new ArrayList<>() ;

        for (Fiche fiche : listBrute) {
            if( (fiche.getTab() != null && fiche.getTab().getId() ==tableauID ) && ( fiche.getSection() != null &&  fiche.getSection().getId() ==sectionID) ){
                list.add(fiche) ;
            }
        }

        return list ;
    }


}