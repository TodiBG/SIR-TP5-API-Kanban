package fr.istic.sir.jpa.dao;

import fr.istic.sir.jpa.dao.generic.AbstractJpaDao;
import fr.istic.sir.jpa.entities.Tag;


public class TagDao extends AbstractJpaDao<Long, Tag> {
    public TagDao(){
        super();
        this.clazz = Tag.class ;
    }
}