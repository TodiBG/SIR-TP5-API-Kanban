package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.*;
import fr.istic.sir.jpa.entities.*;
import fr.istic.sir.jpa.entities.Fiche;
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

@Path("kanban-api/fiches")
@Produces({"application/json"})
public class FicheResource {
  
  @GET
  @Path("/")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve all Fiches",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains all found Fiches",
                          content = @Content(
                                  array = @ArraySchema(schema = @Schema(implementation = Fiche.class)),
                                  mediaType = "application/json" )
                  )}
          )
  public List<Fiche> getAllfiches()  { return new FicheDao().findAll()  ; }


  @GET
  @Path("/{ficheId}")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve a fiche by its Id",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains the found Fiche using its Id",
                          content = @Content(
                                  mediaType = "application/json" ,
                          schema = @Schema(implementation = Fiche.class))
                  )}
  )
  public Fiche getFicheById(@PathParam("ficheId") long ficheId)  {
      return new FicheDao().findById(ficheId) ;
  }

  @GET
  @Path("/{ficheId}/tags")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve all the tags of the Fiche which Id is passed in path parameter",
          //description = " Passe the Fiche'Id in path parameter",
          responses = {
                  @ApiResponse(description = "The response contains all Tags contained in the Fiche which Id is passed in path parameter",
                          content = @Content(
                                  mediaType = "application/json",
                                  array = @ArraySchema(schema = @Schema(implementation = Fiche.class))
                                  )
                  )}
  )
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
  @Operation(
          summary = "To create a new fiche",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains the new Fiche created.  its Id is defined",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Fiche.class))
                  )}
  )
  public Fiche create( @Parameter(description = "The new fiche to be created", required = true) Fiche fiche)  {
    FicheDao ficheDao = new FicheDao() ;

    List<Tag> tags = fiche.getTags() ;
    fiche.setTags(null);

    ficheDao.create(fiche);

    fiche.setTags(tags);

    ficheDao.update(fiche);

    return fiche;
  }


  @PUT
  @Path("/update/{ficheId}")
  @Consumes("application/json")
  @Operation(
          summary = "To update the Fiche which Id is passed in path parameter",
          //description = " Passe the Fiche'Id in path parameter",
          responses = {
                  @ApiResponse(description = "The response contains the updated Fiche, the updated version",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Fiche.class))
                  )}
  )
  public Fiche update(@PathParam("ficheId")long ficheId ,  @Parameter(description = "The Fiche to be updated", required = true) Fiche fiche)  {
    if( fiche.getId() == ficheId ) {
      FicheDao ficheDao = new FicheDao();

      ficheDao.update(fiche);
    }else {
      fiche = null ;
    }
    return fiche;
  }

  @DELETE
  @Path("/delete/{ficheId}")
  @Operation(
          summary = "To delete the fiche which Id is passed in path parameter",
          description = " ",
          responses = {
                  @ApiResponse(description = "")}
  )
  public Response delete(@PathParam("ficheId") long Id)  {
     new FicheDao().deleteById(Id);

    return Response.ok().entity("SUCCESS").build();

  }

}