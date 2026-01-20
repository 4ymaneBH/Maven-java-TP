package com.maven.restapi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.maven.restapi.dao.LivreDao;
import com.maven.restapi.model.Livre;
import java.util.List;

@Path("/livres")
public class LivreResource {
    LivreDao dao = new LivreDao();

    @GET
    @Path("/message")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "OK";
    }

    @GET
    @Path("/getallLivres")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livre> getLivres() {
        return dao.getLivres();
    }

    @GET
    @Path("/getLivreById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Livre getLivreById(@PathParam("id") int id) {
        return dao.getLivreById(id);
    }

    @GET
    @Path("/getLivreByTitre/{titre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Livre getLivreByTitre(@PathParam("titre") String titre) {
        return dao.getLivreByTitre(titre);
    }

    @POST
    @Path("/postLivre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLivre(Livre livre) {
        Livre saved = dao.saveLivre(livre);
        return Response.ok(saved).build();
    }

    @PUT
    @Path("/updateLivre/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLivre(@PathParam("id") int id, Livre livre) {
        int count = dao.updateLivre(id, livre);
        if (count == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteLivre(@PathParam("id") int id) {
        int count = dao.deleteLivre(id);
        if (count == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
