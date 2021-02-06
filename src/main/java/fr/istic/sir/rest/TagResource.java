package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.TagDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/tags")
@Produces({"application/json"})
public class TagResource {
  
  @GET
  @Path("/")
  @Produces({"application/json"})
  public List<Tag> getAllTags()  {
    List<Tag> list =  new TagDao().findAll() ;
    return list;
  }

  @GET
  @Path("/{TagId}")
  @Produces({"application/json"})
  public Tag getTagById(@PathParam("TagId") Long TagId)  {
      return new TagDao().findById(TagId) ;
  }

  @GET
  @Path("/{TagId}/fiches")
  @Produces({"application/json"})
  public List<Fiche> getTagFicheCollection(@PathParam("TagId") Long TagId)  {

    Tag  Tag = new TagDao().findById(TagId);
    if ( Tag != null ) {
      return Tag.getFiches();
    }

    return new ArrayList<Fiche>() ;
  }


  @POST
  @Path("/create")
  @Consumes("application/json")
  public Response create( @Parameter(description = "Pet object that needs to be added to the store", required = true) Tag Tag) {
    // add pet
    new TagDao().create(Tag) ;
    return Response.ok().entity("SUCCESS").build();
  }


}