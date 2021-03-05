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

@Path("/tableaux")
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
  @Path("/{TableauId}/fiches")
  @Produces({"application/json"})
  public List<Fiche> getFicheCollection(@PathParam("TableauId") Long TableauId)  {

    Tableau  tableau = new TableauDao().findById(TableauId);
    if ( tableau != null ) {
      return tableau.getFiches();
    }

    return new ArrayList<Fiche>() ;
  }


  @POST
  @Path("/create")
  @Consumes("application/json")
  public Tableau create( @Parameter(description = "Tableau object that needs to be added to the store", required = true) Tableau tableau) {
    // add pet
    new TableauDao().create(tableau) ;
    return tableau;
  }


  @PUT
  @Path("/update")
  @Consumes("application/json")
  public Tableau update( @Parameter(description = "Tableau object that needs to be updated to the store", required = true) Tableau tableau) {
    // add pet
    new TableauDao().update(tableau) ;
    return tableau;
  }


  @DELETE
  @Path("/delete/{Id}")
  @Produces({"application/json"})
  public Response delete(@PathParam("Id") long Id)  {
    new TableauDao().deleteById(Id);

    return Response.ok().entity("SUCCESS").build();

  }



}