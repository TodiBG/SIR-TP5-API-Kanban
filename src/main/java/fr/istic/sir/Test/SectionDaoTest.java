package fr.istic.sir.Test;

import fr.istic.sir.jpa.dao.FicheDao;
import fr.istic.sir.jpa.dao.SectionDao;
import fr.istic.sir.jpa.dao.TableauDao;
import fr.istic.sir.jpa.dao.TagDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.Section;
import fr.istic.sir.jpa.entities.Tableau;
import fr.istic.sir.jpa.entities.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SectionDaoTest {

    SectionDao sectionDao;
    FicheDao ficheDao;
    TableauDao tableauDao;

    @BeforeEach
    void init(){
        sectionDao = new SectionDao();
        ficheDao = new FicheDao();
        tableauDao = new TableauDao();
    }

    @Test
    @DisplayName("Test retrieve a section by his id")
    void getSectionById(){
        //We are sure that in our database there is a tag with this id
        long id = 1;

        //We retrieve this section
        Section section = sectionDao.findById(id);

        //We test the id match
        Assertions.assertEquals(section.getId(),id);
    }

    @Test
    @DisplayName("Test retrieve all sections ")
    void getAllSections(){

        //We retrieve this fiche
        List<Section> sections  = sectionDao.findAll();

        //We test the id match
        Assertions.assertNotNull(sections,"il y a des sections dans la BD");

    }

    @Test
    @DisplayName("Test create a fiche")
    void createSection(){
        long id1 = 2;
        Fiche fiche = ficheDao.findById(id1);

        long id2 = 3;
        Fiche fiche1 = ficheDao.findById(id2);

        List <Fiche> fiches = new ArrayList<>();
        fiches.add(fiche);
        fiches.add(fiche1);

        long id = 1;
        Tableau tableau = tableauDao.findById(id);

        //We instanciate a new section
        Section section = new Section("En cours", fiches, tableau);

        // We register this new section in the database
        sectionDao.create(section);

        //We retrieve the registration of this new section
        Section section1 = sectionDao.findById(section.getId());

        //We test the sections match
        Assertions.assertEquals(section1,section);
    }

    @Test
    @DisplayName("Test update methode ")
    void updateSection(){
        //We instanciate a new section
        long id = 4;
        Section section = sectionDao.findById(id);

        //modification of the section's libelle
        section.setLibelle("Réalisé");

        sectionDao.update(section);

        //We test the name match
        Assertions.assertEquals(section.getLibelle(),"Réalisé");
    }
    @Test
    @DisplayName("Test delete methode ")
    void deleteSection(){

        //Make sure that this  Id exists in the database, otherwise the test will faill
        long id = 2;

        Section section = sectionDao.findById(id);

        //deleting this section we've retrieved

        sectionDao.delete(section);

        Section section1 = sectionDao.findById(section.getId());

        //We test if the object is null
        Assertions.assertNull(section1);

    }
    @Test
    @DisplayName("Test delete methode ")
    void deleteByIdSection(){
        //Make sure that this  Id exists in the database, otherwise the test will faill
        long id = 3;

        Section section = sectionDao.findById(id);

        //deleting this section we've retrieved
        sectionDao.deleteById(section.getId());

        Section section1 = sectionDao.findById(section.getId());

        //We test if the object is null
        Assertions.assertNull(section1);

    }

}
