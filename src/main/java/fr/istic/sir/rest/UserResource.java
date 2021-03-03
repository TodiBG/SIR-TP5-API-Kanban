package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.UserDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.User;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
@Produces({"application/json"})
public class UserResource {
  
  @GET
  @Path("/")
  @Produces({"application/json"})
  public List<User> getAllUsers()  {
    List<User> list =  new UserDao().findAll() ;
    return list;
  }

  @GET
  @Path("/{userId}")
  @Produces({"application/json"})
  public User getUserById(@PathParam("userId") String userId)  {
      return new UserDao().findById(userId) ;
  }

  @GET
  @Path("/{userId}/fiches")
  @Produces({"application/json"})
  public List<Fiche> getUserFicheCollection(@PathParam("userId") String userId)  {

    User user = new UserDao().findById(userId);
    if ( user != null ) {
      return user.getFiches();
    }

    return new ArrayList<Fiche>() ;
  }


  @POST
  @Path("/create")
  @Consumes("application/json")
  public User create( @Parameter(description = "Pet object that needs to be added to the store", required = true) User user) {
    // add pet
    new UserDao().create(user) ;
    return user; //Response.ok().entity("SUCCESS").build();
  }









}