package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.SectionDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.Section;
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

@Path("/sections")
@Produces({"application/json"})
public class SectionResource {
  
  @GET
  @Path("/")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve all Sections",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains all found Sections",
                          content = @Content(
                                  array = @ArraySchema(schema = @Schema(implementation = Section.class)),
                                  mediaType = "application/json" )
                  )}
  )
  public List<Section> getAllSections()  {
    List<Section> list =  new SectionDao().findAll() ;
    return list;
  }

  @GET
  @Path("/{SectionId}")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve a Section by its Id",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains the found Section using its Id",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Section.class))
                  )}
  )
  public Section getSectionById(@PathParam("SectionId") Long SectionId)  {
      return new SectionDao().findById(SectionId) ;
  }

  @GET
  @Path("/{SectionId}/fiches")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve all the Fiches of the Section which Id is passed in path parameter",
          //description = " Passe the Section's Id in path parameter",
          responses = {
                  @ApiResponse(description = "The response contains all Fiche contained in the Section which Id is passed in path parameter",
                          content = @Content(
                                  mediaType = "application/json",
                                  array = @ArraySchema(schema = @Schema(implementation = Fiche.class))
                          )
                  )}
  )
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
  @Operation(
          summary = "To create a new Section",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains the new Section created.  its Id is defined",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Section.class))
                  )}
  )
  public Section create( @Parameter(description = "New Section to be created", required = true) Section section) {
    new SectionDao().create(section) ;
    return section;
  }


  @PUT
  @Path("/update/{sectionId}")
  @Consumes("application/json")
  @Operation(
          summary = "To update the Section which Id is passed in path parameter",
          //description = " Passe the Section'Id in path parameter",
          responses = {
                  @ApiResponse(description = "The response contains the updated Section, the updated version",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Section.class))
                  )}
  )
  public Section update(@PathParam("ficheId")long sectionId , @Parameter(description = "The Section to be updated", required = true) Section section) {
    if( section.getId() == sectionId ) {
      new SectionDao().update(section) ;
    }
    return section;
  }


  @DELETE
  @Path("/delete/{Id}")
  @Produces({"application/json"})
  @Operation(
          summary = "To delete the Section which Id is passed in path parameter",
          //description = "Passe the Section'Id in path parameter ",
          responses = {
                  @ApiResponse(description = "")}
  )
  public Response delete(@PathParam("Id") long Id)  {
    new SectionDao().deleteById(Id);

    return Response.ok().entity("SUCCESS").build();

  }



}