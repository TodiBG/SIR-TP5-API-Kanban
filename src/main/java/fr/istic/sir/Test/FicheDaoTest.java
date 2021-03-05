package fr.istic.sir.Test;

import fr.istic.sir.jpa.dao.FicheDao;
import fr.istic.sir.jpa.dao.UserDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class FicheDaoTest {

    FicheDao ficheDao;
    UserDao userDao;
    @BeforeEach
    void init(){
        ficheDao = new FicheDao();
        userDao = new UserDao() ;

    }

    @Test
    @DisplayName("Test retrieve a fiche by his id")
    void getFicheById(){
        //We are sure that in our database there is a fiche with this id
        long id = 2;

        //We retrieve this fiche
        Fiche fiche = ficheDao.findById(id);

        //We test the id match
        Assertions.assertEquals(fiche.getId(),id);

    }
    @Test
    @DisplayName("Test retrieve all fiche ")
    void getAllFiche(){

        //We retrieve this fiche
        List<Fiche> Fiche  = ficheDao.findAll();

        //We test the id match
        Assertions.assertNotNull(Fiche,"il y a des fiches dans la BD");

    }

    @Test
    @DisplayName("Test create a fiche")
    void createFiche(){
        User user2 = new User("Lena", "Mutoni", "lena.mutoni@gmail.com") ;
        userDao.create(user2);

        Date date = new Date();

        //We instanciate a new fiche
        Fiche fiche = new Fiche("Production du premier lot",date,user2,45,"Villejean","http://localhost/phpmyadmin/sql.php?server=1&db=kanban&table=Fiche","frontend");

        // We register this new fiche in the database
        ficheDao.create(fiche);

        //We retrieve the registration of this new fiche
        Fiche fiche1 = ficheDao.findById(fiche.getId());

        //We test the fiches match
        Assertions.assertEquals(fiche1,fiche);

    }
    @Test
    @DisplayName("Test update methode ")
    void updateFiche(){
        //instantiation of a new fiche
        User user3 = new User("Nella", "Gatoni", "nella.gatoni@gmail.com") ;
        userDao.create(user3);
        Date date = new Date();

        //We instanciate a new fiche
        Fiche fiche = new Fiche("Production du sixieme lot",date,user3,45,"Villejean","http://localhost/phpmyadmin/sql.php?server=1&db=kanban&table=Fiche","frontend");

        // We register this new fiche in the database
        ficheDao.create(fiche);

        //modification of the user's name
        fiche.setTemps(50);

        ficheDao.update(fiche);

        //We test the name match
        Assertions.assertEquals(fiche.getTemps(),50);
    }

    @Test
    @DisplayName("Test delete methode ")
    void deleteFiche(){

        long id = 2;

        Fiche fiche = ficheDao.findById(id);

        //deleting this fiche we've retrieved

        ficheDao.delete(fiche);

        Fiche fiche1 = ficheDao.findById(fiche.getId());

        //We test if the object is null
        Assertions.assertNull(fiche1);

    }
    @Test
    @DisplayName("Test delete methode ")
    void deleteFicheById(){
        long id = 5;

        Fiche fiche = ficheDao.findById(id);

        //deleting this fiche we've retrieved

        ficheDao.deleteById(fiche.getId());

        Fiche fiche1 = ficheDao.findById(fiche.getId());

        //We test if the object is null
        Assertions.assertNull(fiche1);

    }


}
