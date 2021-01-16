package fr.istic.sir;

import fr.istic.sir.jpa.dao.*;
import fr.istic.sir.jpa.entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws ParseException {

        //Créer les DAO
        UtilisateurDao userDAO = new  UtilisateurDao() ;
        FicheDao ficheDao = new FicheDao() ;
        SectionDao sectionDao = new SectionDao() ;
        TagDao tagDao = new TagDao() ;
        TableauDao tableauDao = new TableauDao() ;




       Utilisateur user1 = new Utilisateur("Jean Pierre 3X", "IVABO") ;
       Utilisateur user2 = new Utilisateur("Angela CLarissa", "SIB") ;


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        Date date1 = formatter.parse("15-01-2021");
        Date date2 = formatter.parse("17-01-2021");


        userDAO.create(user1) ;
        userDAO.create(user2) ;

        //Créer des fiches
        Fiche fiche1 = new Fiche("Produiction du second lot",  date1,user1, 90, "Beaulieu", "","...." );
        Fiche fiche2 = new Fiche("Produiction du cinquième lot",  date1,user1, 45, "Beaulieu", "","...." );
        Fiche fiche3 = new Fiche("Produiction du troisième lot",  date2,user2, 120, "Beaulieu", "","...." );
        Fiche fiche4 = new Fiche("Produiction du quatrième lot",  date1,user2, 60, "Beaulieu", "","...." );

        //Créer des Tag pour Fiches
        Tag urgent = new Tag("Urgent") ;
        Tag moins_urgent = new Tag("Mois urgent") ;

        //Enregister les Tag dans la base de données
        tagDao.create(urgent);
        tagDao.create(moins_urgent) ;

        //Mettre les Tag sur les fiches
        fiche1.addTag(urgent);
        fiche2.addTag(urgent);
        fiche4.addTag(urgent);
        fiche3.addTag(moins_urgent);

        tagDao.update(urgent) ;
        tagDao.update(moins_urgent) ;


        //Enregistrer dans la base de données
        ficheDao.create(fiche1) ;
        ficheDao.create(fiche2) ;
        ficheDao.create(fiche3) ;
        ficheDao.create(fiche4) ;

        Section en_attente = new Section("En attente") ;
        Section en_cours = new Section("En cours") ;
        Section realise = new Section("Réalisé") ;

        //Enregister les section dans la base de données
        sectionDao.create(en_attente) ;
        sectionDao.create(en_cours) ;
        sectionDao.create(realise) ;


        //Affecter les fiches dans les sections ;
        fiche1.setSection(realise );
        fiche2.setSection(en_cours);
        fiche3.setSection(en_attente);
        fiche4.setSection(realise);



        //Mettre les objets fiche à jour
        ficheDao.update(fiche1) ;
        ficheDao.update(fiche2) ;
        ficheDao.update(fiche3) ;
        ficheDao.update(fiche4) ;


        //Créer un nouveau tableau
        Tableau tab = new Tableau("Principal") ;

        //Enregister le tableau dans la base de données
        tableauDao.create(tab);

        //Mettre les sections dans le tableau
        en_attente.setTab(tab);
        en_cours.setTab(tab);
        realise.setTab(tab);

        sectionDao.update(en_attente) ;
        sectionDao.update(en_cours) ;
        sectionDao.update(realise) ;














    }
}
