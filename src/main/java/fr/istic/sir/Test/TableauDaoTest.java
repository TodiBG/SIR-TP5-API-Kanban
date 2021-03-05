package fr.istic.sir.Test;

import fr.istic.sir.jpa.dao.SectionDao;
import fr.istic.sir.jpa.dao.TableauDao;
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

public class TableauDaoTest {

    TableauDao tableauDao;
    SectionDao sectionDao;

    @Test
    @BeforeEach
    void init(){
        tableauDao = new TableauDao();
        sectionDao = new SectionDao();
    }

    @Test
    @DisplayName("Test retrieve a table by his id")
    void getTableauById(){
        //We are sure that in our database there is a table with this id
        long id = 1;

        //We retrieve this table
        Tableau tableau = tableauDao.findById(id);

        //We test the id match
        Assertions.assertEquals(tableau.getId(),id);
    }
    @Test
    @DisplayName("Test retrieve all tables ")
    void getAllTable(){

        //We retrieve this table
        List<Tableau> Tableau  = tableauDao.findAll();

        //We test the id match
        Assertions.assertNotNull(Tableau,"il y a des tableaux dans la BD");

    }
    @Test
    @DisplayName("Test create a table")
    void createTag(){
        long id = 2;
        Section section = sectionDao.findById(id);

        long id1 = 3;
        Section section1 = sectionDao.findById(id1);

        List <Section> sections = new ArrayList<>();
        sections.add(section);
        sections.add(section1);

        //We instanciate a new table
        Tableau tableau = new Tableau("Secondaire");

        // We register this new tag in the database
        tableauDao.create(tableau);

        //We retrieve the registration of this new tag
        Tableau tableau1 = tableauDao.findById(tableau.getId());

        //We test the tags match
        Assertions.assertEquals(tableau1,tableau);
    }

    @Test
    @DisplayName("Test update methode ")
    void update(){
        //We instanciate a new table
        long id = 1;
        Tableau tableau = tableauDao.findById(id);

        //modification of the table's libelle
        tableau.setLibelle("Principal");

        tableauDao.update(tableau);

        //We test the name match
        Assertions.assertEquals(tableau.getLibelle(),"Principal");
    }
    @Test
    @DisplayName("Test delete methode ")
    void deleteTableau(){
        long id = 3;

        Tableau tableau = tableauDao.findById(id);

        //deleting this table we've retrieved

        tableauDao.delete(tableau);

        Tableau tableau1 = tableauDao.findById(tableau.getId());

        //We test if the object is null
        Assertions.assertNull(tableau1);

    }
    @Test
    @DisplayName("Test delete methode ")
    void deleteTableauById(){
        long id = 4;

        Tableau tableau = tableauDao.findById(id);

        //deleting this table we've retrieved

        tableauDao.deleteById(tableau.getId());

        Tableau tableau1 = tableauDao.findById(tableau.getId());

        //We test if the object is null
        Assertions.assertNull(tableau1);

    }


}
