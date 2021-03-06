package fr.istic.sir.rest;

import fr.istic.sir.jpa.entities.Section;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/docs")
public class SwaggerResource {

    @GET
    @Operation(summary = "The documentation for this API" )
    public byte[] documentation() {
        try {
            return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/swagger/index.html"));

        } catch (IOException e) {
            return null;
        }
    }

    @GET
    @Path("{path:.*}")
    @Operation(summary = "Other files to include in the documentation." )
    public byte[] resourceFiles(@PathParam("path") String path) {
        try {
            return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/swagger/"+path));
        } catch (IOException e) {
            return null;
        }
    }

}