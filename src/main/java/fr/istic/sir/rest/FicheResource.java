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
  @Path("/create")
  @Consumes("application/json")
  public Fiche createWithTag(@PathParam("tagId")long tagId ,  @Parameter(description = "Create a fiche with tag", required = true) Fiche fiche)  {
    FicheDao ficheDao = new FicheDao() ;
    ficheDao.create(fiche);
    return fiche;
  }


}