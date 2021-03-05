package fr.istic.sir.Test;

import fr.istic.sir.jpa.dao.UserDao;
import fr.istic.sir.jpa.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDaoTest {

    UserDao userDao;
    @BeforeEach
    void init(){
        userDao = new UserDao() ;
    }


    @Test
    @DisplayName("Test get methode ")
    void getUserById(){

        //We are sure there is an user in the database who owns this email
        String email = "bonaventure.gbehe@gmail.com" ;

        //We are going to retrieve this user
        User user = userDao.findById(email);

        //We test the email match
        Assertions.assertEquals(user.getEmail(), email);

    }
    @Test
    @DisplayName("Test retrieve all users ")
    void getAllUsers(){

        //We retrieve this fiche
        List<User>Users  = userDao.findAll();

        //We test the id match
        Assertions.assertNotNull(Users," There are sommes users in the BD");

    }



    @Test
    @DisplayName("Test create methode ")
    void createUser(){
        String email = "bonaventure.gbehe@gmail.com";
        //We instanciate a new user
        User user1 = new User("Todi Bonaventure", "Gbehe", email) ;

        //We register this new user in the database
        userDao.create(user1);

        //We retrieve the registration of this new user
        User user2 = userDao.findById(email);

        User user3 = userDao.findById("gbehe@gmail.com");

        //We test the users match
        Assertions.assertEquals(user2, user1);

    }
    @Test
    @DisplayName("Test update methode ")
    void UpdateUser(){
        //instantiation of a new user
        User user = new User("Ange ", "Sibomana", "angecla18@gmail.com") ;

        //registration in database
        userDao.create(user);

        //modification of the user's name
        user.setPrenom("Ange Clarisse");

        userDao.update(user);

        //We test the name match
        Assertions.assertEquals(user.getPrenom(),"Ange Clarisse");

    }

    @Test
    @DisplayName("Test delete methode ")
    void deleteUser(){
        String email = "Sam.gatoni@gmail.com";
        //instantiation of a new user
        User user = userDao.findById(email);

        //deleting this user

        userDao.delete(user);

        User user2 = userDao.findById(email);

        //we test if the object is null
        Assertions.assertNull(user2);

    }
    @Test
    @DisplayName("Test deleteUserById methode ")
    void deleteUserById(){
        String email = "nella.gatoni@gmail.com";
        //instantiation of a new user
        User user = userDao.findById(email);

        //deleting this user

        userDao.deleteById(user.getEmail());

        User user2 = userDao.findById(user.getEmail());

        //we test if the object is null
        Assertions.assertNull(user2);

    }



}
