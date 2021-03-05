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
  public Tag create( @Parameter(description = "Tag object that needs to be added to the store", required = true) Tag tag) {
    new TagDao().create(tag) ;
    return tag;
  }

  @PUT
  @Path("/update")
  @Consumes("application/json")
  public Tag update( @Parameter(description = "Tag object that needs to be updated to the store", required = true) Tag tag) {
    new TagDao().update(tag) ;
    return tag;
  }



  @DELETE
  @Path("/delete/{Id}")
  @Produces({"application/json"})
  public Response delete(@PathParam("Id") long Id)  {
    new TagDao().deleteById(Id);
    return Response.ok().entity("SUCCESS").build();
  }


}