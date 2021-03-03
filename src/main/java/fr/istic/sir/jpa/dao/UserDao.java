package fr.istic.sir.jpa.dao;


import fr.istic.sir.jpa.dao.generic.AbstractJpaDao;
import fr.istic.sir.jpa.entities.User;

public class UserDao extends AbstractJpaDao<String, User> {

    public UserDao(){
        super();
        this.clazz = User.class ;
    }

}