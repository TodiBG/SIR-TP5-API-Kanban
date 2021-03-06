package fr.istic.sir.rest;

import fr.istic.sir.jpa.dao.TagDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.Tag;
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

@Path("/tags")
@Produces({"application/json"})
public class TagResource {
  
  @GET
  @Path("/")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve all Tags",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains all found Tags",
                          content = @Content(
                                  array = @ArraySchema(schema = @Schema(implementation = Tag.class)),
                                  mediaType = "application/json" )
                  )}
  )
  public List<Tag> getAllTags()  {
    List<Tag> list =  new TagDao().findAll() ;
    return list;
  }

  @GET
  @Path("/{TagId}")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve a Tag by its Id",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains the found Tag using its Id",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Tag.class))
                  )}
  )
  public Tag getTagById(@PathParam("TagId") Long TagId)  {
      return new TagDao().findById(TagId) ;
  }

  @GET
  @Path("/{TagId}/fiches")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve all the Fiches of the Tag which Id is passed in path parameter",
          responses = {
                  @ApiResponse(description = "The response contains all Fiche contained in the Tag which Id is passed in path parameter",
                          content = @Content(
                                  mediaType = "application/json",
                                  array = @ArraySchema(schema = @Schema(implementation = Fiche.class))
                          )
                  )}
  )
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
  @Operation(
          summary = "To create a new Tag",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains the new Tag created.  its Id is defined",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Tag.class))
                  )}
  )
  public Tag create( @Parameter(description = "The new fiche to be created", required = true) Tag tag) {
    new TagDao().create(tag) ;
    return tag;
  }

  @PUT
  @Path("/update/{tagId}")
  @Consumes("application/json")
  @Operation(
          summary = "To update the Tag which Id is passed in path parameter",
          responses = {
                  @ApiResponse(description = "The response contains the updated Tag, the updated version",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Tag.class))
                  )}
  )
  public Tag update(@PathParam("tagId")long tagId ,  @Parameter(description = "The Tag to be updated", required = true) Tag tag) {
   if( tag.getId() == tagId) {
     new TagDao().update(tag);
   }
    return tag;
  }



  @DELETE
  @Path("/delete/{Id}")
  @Produces({"application/json"})
  @Operation(
          summary = "To delete the Tag which Id is passed in path parameter",
          responses = {
                  @ApiResponse(description = "")}
  )
  public Response delete(@PathParam("Id") long Id)  {
    new TagDao().deleteById(Id);
    return Response.ok().entity("SUCCESS").build();
  }


}