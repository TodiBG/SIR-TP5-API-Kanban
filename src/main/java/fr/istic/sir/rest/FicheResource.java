package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.*;
import fr.istic.sir.jpa.entities.*;
import fr.istic.sir.jpa.entities.Fiche;
import io.swagger.v3.oas.annotations.Parameter;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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
  public Fiche getFicheById(@PathParam("ficheId") long ficheId)  {
      return new FicheDao().findById(ficheId) ;
  }

  @GET
  @Path("/{ficheId}/tags")
  @Produces({"application/json"})
  public List<Tag> getFicheTagCollection(@PathParam("ficheId") long ficheId)  {
    Fiche  fiche = new FicheDao().findById(ficheId);
    if ( fiche != null ) {
      return fiche.getTags();
    }

    return new ArrayList<>() ;
  }


  @POST
  @Path("/create")
  @Consumes("application/json")
  public Fiche create(@PathParam("tagId")long tagId ,  @Parameter(description = "Create a fiche", required = true) Fiche fiche)  {
    FicheDao ficheDao = new FicheDao() ;

    List<Tag> tags = fiche.getTags() ;
    fiche.setTags(null);

    ficheDao.create(fiche);

    fiche.setTags(tags);

    ficheDao.update(fiche);

    return fiche;
  }


  @PUT
  @Path("/update")
  @Consumes("application/json")
  public Fiche update(@PathParam("tagId")long tagId ,  @Parameter(description = "update a fiche", required = true) Fiche fiche)  {
    FicheDao ficheDao = new FicheDao() ;
    ficheDao.update(fiche);
    return fiche;
  }

  @DELETE
  @Path("/delete/{Id}")
  @Produces({"application/json"})
  public Response delete(@PathParam("Id") long Id)  {
     new FicheDao().deleteById(Id);

    return Response.ok().entity("SUCCESS").build();

  }

}