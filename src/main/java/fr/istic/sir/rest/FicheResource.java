package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.*;
import fr.istic.sir.jpa.entities.*;
import fr.istic.sir.jpa.entities.Fiche;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Path("/fiches")
@Produces({"application/json"})
public class FicheResource {
  
  @GET
  @Path("/")
  @Produces({"application/json"})
  public List<Fiche> getAllfiches()  {
    List<Fiche> list =  new FicheDao().findAll() ;
    return list;
  }
  
  

  @GET
  @Path("/{ficheId}")
  @Produces({"application/json"})
  public Fiche getficheById(@PathParam("ficheId") long ficheId)  {
      return new FicheDao().findById(ficheId) ;
  }

  @GET
  @Path("/{ficheId}/tags")
  @Produces({"application/json"})
  public List<Tag> getficheTagCollection(@PathParam("ficheId") long ficheId)  {
    Fiche  fiche = new FicheDao().findById(ficheId);
    if ( fiche != null ) {
      return fiche.getTags();
    }

    return new ArrayList<>() ;
  }


  @POST
  @Path("/create/{tagId}")
  @Consumes("application/json")
  public Response create(@PathParam("tagId")long tagId,  @Parameter(description = "Pet object that needs to be added to the store", required = true) Fiche Inputedfiche)  {

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












    /*
    FicheDao ficheDao = new FicheDao() ;

    Tag tag = new TagDao().findById(tagId) ;

    ficheDao.update(fiche) ;


  */

    return Response.ok().entity("SUCCESS").build();
  }

/*


  SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
    Date date1 = formatter.parse("15-02-2021");
  Date date2 = formatter.parse("17-02-2021");


        ficheDAO.create(fiche1) ;
  //ficheDAO.create(fiche2) ;


     ficheDAO.create(fiche1) ;
     ficheDAO.create(fiche2) ;


  //Créer des fiches
  Fiche fiche1 = new Fiche("Produiction du lot 15",  date1,fiche1, 30, "Beaulieu", "","...." );
  Fiche fiche2 = new Fiche("Produiction du lot 15",  date1,fiche1, 45, "Beaulieu", "","...." );
  Fiche fiche3 = new Fiche("Produiction du lot 15",  date2,fiche2, 20, "Beaulieu", "","...." );
  Fiche fiche4 = new Fiche("Produiction du lot 16",  date1,fiche2, 50, "Beaulieu", "","...." );


  //Créer des Tag pour Fiches
  Tag urgent = new Tag("Moins") ;
  Tag moins_urgent = new Tag("Moins urgent") ;

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

     System.out.println("fiche : "+ fiche3.getUtilisateur().getId());


  //Enregistrer dans la base de données
         ficheDao.create(fiche1) ;
       ficheDao.create(fiche2) ;
        ficheDao.create(fiche3) ;
        ficheDao.create(fiche4) ;


*/




}