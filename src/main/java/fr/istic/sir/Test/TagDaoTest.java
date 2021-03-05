package fr.istic.sir.Test;

import fr.istic.sir.jpa.dao.FicheDao;
import fr.istic.sir.jpa.dao.TagDao;
import fr.istic.sir.jpa.dao.UserDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.Tag;
import fr.istic.sir.jpa.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TagDaoTest {

    TagDao tagDao;
    FicheDao ficheDao;
    UserDao userDao;
    @BeforeEach
    void init(){
        tagDao = new TagDao();
        ficheDao = new FicheDao();


    }

    @Test
    @DisplayName("Test retrieve a tag by his id")
    void getTagById(){
        //We are sure that in our database there is a tag with this id
        long id = 1;

        //We retrieve this fiche
        Tag tag = tagDao.findById(id);

        //We test the id match
        Assertions.assertEquals(tag.getId(),id);
    }

    @Test
    @DisplayName("Test retrieve all tags ")
    void getAllTags(){

        //We retrieve this fiche
        List<Tag> tags  = tagDao.findAll();

        //We test the id match
        Assertions.assertNotNull(tags,"il y a des tags dans la BD");

    }

    @Test
    @DisplayName("Test create a fiche")
    void createTag(){
        long id = 2;
        Fiche fiche = ficheDao.findById(id);

        long id1 = 3;
        Fiche fiche1 = ficheDao.findById(id1);

        List <Fiche> fiches = new ArrayList<>();
        fiches.add(fiche);
        fiches.add(fiche1);

        //We instanciate a new tag
        Tag tag = new Tag("Moins urgent",fiches);

        // We register this new tag in the database
        tagDao.create(tag);

        //We retrieve the registration of this new tag
        Tag tag1 = tagDao.findById(tag.getId());

        //We test the tags match
        Assertions.assertEquals(tag1,tag);
    }
    @Test
    @DisplayName("Test update methode ")
    void update(){
        //We instanciate a new tag
        long id = 2;
        Tag tag = tagDao.findById(id);

        //modification of the tag's libelle
        tag.setLibelle("Pas urgent");

        tagDao.update(tag);

        //We test the name match
        Assertions.assertEquals(tag.getLibelle(),"Pas urgent");
    }
    @Test
    @DisplayName("Test delete methode ")
    void deleteTag(){
        long id = 2;

        Tag tag = tagDao.findById(id);

        //deleting this tag we've retrieved

        tagDao.delete(tag);

        Tag tag1 = tagDao.findById(tag.getId());

        //We test if the object is null
        Assertions.assertNull(tag1);

    }
    @Test
    @DisplayName("Test deleteById methode ")
    void deleteByIdTag(){
        long id = 3;

        Tag tag = tagDao.findById(id);

        //deleting this tag we've retrieved

        tagDao.deleteById(tag.getId());

        Tag tag1 = tagDao.findById(tag.getId());

        //We test if the object is null
        Assertions.assertNull(tag1);

    }




}
