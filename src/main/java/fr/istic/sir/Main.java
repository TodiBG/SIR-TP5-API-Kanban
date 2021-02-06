package fr.istic.sir;

import fr.istic.sir.jpa.dao.*;
import fr.istic.sir.jpa.entities.*;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(API_Application.class.getName());

    public static void main( String[] args ) {


        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        API_Application app = new API_Application();

        ut.deploy(app);

        ut.start( Undertow.builder().addHttpListener(8080, "localhost") );

        logger.info("JAX-RS based micro-service running!");





        //Créer les DAO
        UtilisateurDao userDAO = new  UtilisateurDao() ;
        FicheDao ficheDao = new FicheDao() ;
        SectionDao sectionDao = new SectionDao() ;
        TagDao tagDao = new TagDao() ;
        TableauDao tableauDao = new TableauDao() ;




        Utilisateur user = new Utilisateur("Kéassemou Jopsy", "SEHI","kjops@gmail.fr") ;


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        Date date1 = null;
        try {
            date1 = formatter.parse("15-02-2021");
        } catch (ParseException e) { e.printStackTrace(); }

        userDAO.create(user) ;


        //Créer la fiche
        Fiche fiche = new Fiche("Produiction du lot 15",  date1,user, 30, "Beaulieu", "","...." );

        //Créer un Tag pour Fiche
        Tag moins_urgent = new Tag("Moins urgent") ;

        //Enregister le Tag dans la base de données
        tagDao.create(moins_urgent) ;



        //Mettre les Tag sur les fiches

        fiche.addTag(moins_urgent);

        tagDao.update(moins_urgent) ;


        //Enregistrer dans la base de données
        ficheDao.create(fiche) ;

        Section en_attente = new Section("En attente") ;


        //Enregister la section dans la base de données
        sectionDao.create(en_attente) ;


        //Affecter les fiches dans les sections ;
        fiche.setSection(en_attente);


        //Mettre la fiche à jour
        ficheDao.update(fiche) ;


        //Créer un nouveau tableau
        Tableau tab = new Tableau("Secondaire") ;

        //Enregister le tableau dans la base de données
        tableauDao.create(tab);

        //Mettre les sections dans le tableau
        en_attente.setTab(tab);

        sectionDao.update(en_attente) ;

















    }

}
