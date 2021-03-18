package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.UserDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.Section;
import fr.istic.sir.jpa.entities.User;
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

@Path("/kanban-api/users")
@Produces({"application/json"})
public class UserResource {
  
  @GET
  @Path("/")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve all Users",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains all found User",
                          content = @Content(
                                  array = @ArraySchema(schema = @Schema(implementation = User.class)),
                                  mediaType = "application/json" )
                  )}
  )
  public List<User> getAllUsers()  {
    List<User> list =  new UserDao().findAll() ;
    return list;
  }

  @GET
  @Path("/{userEmail}")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve a User by his email",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains the found User using his email",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = User.class))
                  )}
  )
  public User getUserById(@PathParam("userEmail") String userEmail)  {
      return new UserDao().findById(userEmail) ;
  }

  @GET
  @Path("/{userEmail}/fiches")
  @Produces({"application/json"})
  @Operation(
          summary = "To retrieve all the Fiches of the User whose email is passed in path parameter",
          //description = " Passe the Section's Id in path parameter",
          responses = {
                  @ApiResponse(description = "The response contains all Fiche contained in the User whose email is passed in path parameter",
                          content = @Content(
                                  mediaType = "application/json",
                                  array = @ArraySchema(schema = @Schema(implementation = Fiche.class))
                          )
                  )}
  )
  public List<Fiche> getUserFicheCollection(@PathParam("userEmail") String userEmail)  {

    User user = new UserDao().findById(userEmail);
    if ( user != null ) {
      return user.getFiches();
    }

    return new ArrayList<Fiche>() ;
  }


  @POST
  @Path("/create")
  @Consumes("application/json")
  @Operation(
          summary = "To create a new User",
          description = " ",
          responses = {
                  @ApiResponse(description = "The response contains the new User created.",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Section.class))
                  )}
  )
  public User create( @Parameter(description = "The new User to be created", required = true) User user) {
    new UserDao().create(user) ;
    return user; //Response.ok().entity("SUCCESS").build();
  }

  @POST
  @Path("/update/{userEmail}")
  @Consumes("application/json")
  @Operation(
          summary = "To update the User which email is passed in path parameter",
          responses = {
                  @ApiResponse(description = "The response contains the updated User, the updated version",
                          content = @Content(
                                  mediaType = "application/json" ,
                                  schema = @Schema(implementation = Section.class))
                  )}
  )
  public User update(@PathParam("userEmail") String email, @Parameter(description = "The User to be updated", required = true) User user) {

    if(  user.getEmail().equals(email.trim()) ) {
      new UserDao().update(user);
    }else {
      user = null ;
    }
    return user; //Response.ok().entity("SUCCESS").build();
  }



  @PUT
  @Path("/delete/{userEmail}")
  @Produces({"application/json"})
  @Operation(
          summary = "To delete the User which email is passed in path parameter",
          //description = "Passe the Section'Id in path parameter ",
          responses = {
                  @ApiResponse(description = "")}
  )
  public Response delete(@PathParam("userEmail") String userEmail)  {
    new UserDao().deleteById(userEmail);

    return Response.ok().entity("SUCCESS").build();

  }



}