package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.TableauDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.Section;
import fr.istic.sir.jpa.entities.Tableau;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
  @Operation(
          summary = "To retrieve all Tableau",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains all found Tableau",
                          content = @Content(
                                  array = @ArraySchema(schema = @Schema(implementation = Tableau.class)),
                                  mediaType = "application/json" )
                  )}
  )
  public List<Tableau> getAllTableaux()  {
    List<Tableau> list =  new TableauDao().findAll() ;
    return list;
  }

  @GET
  @Path("/{TableauId}")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve a Tableau by its Id",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains the found Tableau using its Id",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Tableau.class))
                  )}
  )
  public Tableau getTableauById(@PathParam("TableauId") Long TableauId)  {
      return new TableauDao().findById(TableauId) ;
  }

  @GET
  @Path("/{TableauId}/fiches")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve all the Fiches of the Tableau which Id is passed in path parameter",
          //description = " Passe the Section's Id in path parameter",
          responses = {
                  @ApiResponse(description = "The response contains all Fiche contained in the Tableau which Id is passed in path parameter",
                          content = @Content(
                                  mediaType = "application/json",
                                  array = @ArraySchema(schema = @Schema(implementation = Fiche.class))
                          )
                  )}
  )
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
  @Operation(
          summary = "To create a new Tableau",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains the new Tableau created.  its Id is defined",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Tableau.class))
                  )}
  )
  public Tableau create( @Parameter(description = "The new Tableau to be created", required = true) Tableau tableau) {
    new TableauDao().create(tableau) ;
    return tableau;
  }


  @PUT
  @Path("/update/{tableauId}")
  @Consumes("application/json")
  @Operation(
          summary = "To update a Tableau",
          responses = {
                  @ApiResponse(description = "The response contains the updated Tableau, the updated version",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Tableau.class))
                  )}
  )
  public Tableau update(@PathParam("tableauId")long tableauId,  @Parameter(description = "The Tableau to be updated", required = true) Tableau tableau) {
    if(tableau.getId() == tableauId) {
      new TableauDao().update(tableau);
    }
    return tableau;
  }


  @DELETE
  @Path("/delete/{Id}")
  @Produces({"application/json"})
  @Operation(
          summary = "To delete the Tableau which Id is passed in path parameter",
          description = " ",
          responses = {
                  @ApiResponse(description = "")}
  )
  public Response delete(@PathParam("Id") long Id)  {
    new TableauDao().deleteById(Id);

    return Response.ok().entity("SUCCESS").build();

  }



}