package fr.istic.sir.jpa.dao;

import fr.istic.sir.jpa.dao.generic.AbstractJpaDao;
import fr.istic.sir.jpa.entities.Tableau;


public class TableauDao extends AbstractJpaDao<Long, Tableau> {
    public TableauDao(){
        super();
        this.clazz = Tableau.class ;
    }
}