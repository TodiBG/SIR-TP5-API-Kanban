package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.SectionDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.Section;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/sections")
@Produces({"application/json"})
public class SectionResource {
  
  @GET
  @Path("/")
  @Produces({"application/json"})
  public List<Section> getAllSections()  {
    List<Section> list =  new SectionDao().findAll() ;
    return list;
  }

  @GET
  @Path("/{SectionId}")
  @Produces({"application/json"})
  public Section getSectionById(@PathParam("SectionId") Long SectionId)  {
      return new SectionDao().findById(SectionId) ;
  }

  @GET
  @Path("/{SectionId}/fiches")
  @Produces({"application/json"})
  public List<Fiche> getSectionFicheCollection(@PathParam("SectionId") Long SectionId)  {

    Section  section = new SectionDao().findById(SectionId);
    if ( section != null ) {
      return section.getFiches();
    }

    return new ArrayList<Fiche>() ;
  }


  @POST
  @Path("/create")
  @Consumes("application/json")
  public Section create( @Parameter(description = "Section object that needs to be added to the store", required = true) Section section) {
    new SectionDao().create(section) ;
    return section;
  }


  @PUT
  @Path("/update")
  @Consumes("application/json")
  public Section update( @Parameter(description = "Section object that needs to be updated to the store", required = true) Section section) {
    new SectionDao().update(section) ;
    return section;
  }


  @DELETE
  @Path("/delete/{Id}")
  @Produces({"application/json"})
  public Response delete(@PathParam("Id") long Id)  {
    new SectionDao().deleteById(Id);

    return Response.ok().entity("SUCCESS").build();

  }



}