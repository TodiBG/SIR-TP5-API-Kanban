package fr.istic.sir.rest;


import fr.istic.sir.jpa.dao.UtilisateurDao;
import fr.istic.sir.jpa.entities.Fiche;
import fr.istic.sir.jpa.entities.Utilisateur;
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
  public List<Utilisateur> getAllUsers()  {
    List<Utilisateur> list =  new UtilisateurDao().findAll() ;
    return list;
  }

  @GET
  @Path("/{userId}")
  @Produces({"application/json"})
  public Utilisateur getUserById(@PathParam("userId") String userId)  {
      return new UtilisateurDao().findById(userId) ;
  }

  @GET
  @Path("/{userId}/fiches")
  @Produces({"application/json"})
  public List<Fiche> getUserFicheCollection(@PathParam("userId") String userId)  {

    Utilisateur  user = new UtilisateurDao().findById(userId);
    if ( user != null ) {
      return user.getFiches();
    }

    return new ArrayList<Fiche>() ;
  }


  @POST
  @Path("/create")
  @Consumes("application/json")
  public Response create( @Parameter(description = "Pet object that needs to be added to the store", required = true) Utilisateur user) {
    // add pet
    new UtilisateurDao().create(user) ;
    return Response.ok().entity("SUCCESS").build();
  }









}