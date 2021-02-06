package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.TableauDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.Section;
import fr.istic.sir.jpa.entities.Tableau;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/Tableaux")
@Produces({"application/json"})
public class TableauResource {
  
  @GET
  @Path("/")
  @Produces({"application/json"})
  public List<Tableau> getAllTableaux()  {
    List<Tableau> list =  new TableauDao().findAll() ;
    return list;
  }

  @GET
  @Path("/{TableauId}")
  @Produces({"application/json"})
  public Tableau getTableauById(@PathParam("TableauId") Long TableauId)  {
      return new TableauDao().findById(TableauId) ;
  }

  @GET
  @Path("/{TableauId}/sections")
  @Produces({"application/json"})
  public List<Section> getTableauFicheCollection(@PathParam("TableauId") Long TableauId)  {

    Tableau  tableau = new TableauDao().findById(TableauId);
    if ( tableau != null ) {
      return tableau.getSections();
    }

    return new ArrayList<Section>() ;
  }


  @POST
  @Path("/create")
  @Consumes("application/json")
  public Response create( @Parameter(description = "Pet object that needs to be added to the store", required = true) Tableau tableau) {
    // add pet
    new TableauDao().create(tableau) ;
    return Response.ok().entity("SUCCESS").build();
  }

  

}