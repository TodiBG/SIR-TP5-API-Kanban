package fr.istic.sir.Test;

import fr.istic.sir.jpa.dao.UserDao;
import fr.istic.sir.jpa.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDaoTest {

    UserDao userDao;
    @BeforeEach
    void init(){
        userDao = new UserDao() ;
    }


    @Test
    @DisplayName("Test create methode ")
    void getUserById(){

        //We are sure there is an user in the database who owns this email
        String email = "bonaventure.gbehe@gmail.com";

        //We are going to retrieve this user
        User user = userDao.findById(email);

        //We test the email match
        Assertions.assertEquals(user.getEmail(), email);

    }



    @Test
    @DisplayName("Test create methode ")
    void createUser(){
        String email = "bonaventure.gbehe@gmail.com";
        User user1 = new User("Todi Bonaventure", "Gbehe", email) ;

        userDao.create(user1);

        User user2 = userDao.findById(email);

        User user3 = userDao.findById("gbehe@gmail.com");

        Assertions.assertEquals(user2, user1);

    }


}
