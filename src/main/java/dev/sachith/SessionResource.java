package dev.sachith;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * @author sachith
 */
@Path("/sessions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class SessionResource {
    private final SessionService sessionService;

    public SessionResource(SessionService sessionService, String apiKey) {
        this.sessionService = sessionService;
    }

    @POST
    public Response createSession(Session session) {
        sessionService.createSession(session);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GET
    @Path("/{id}")
    public Session getSession(@PathParam("id") int id) {
        return sessionService.getSession(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateSession(@PathParam("id") int id, Session session) {
        sessionService.updateSession(id, session);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSession(@PathParam("id") int id) {
        sessionService.deleteSession(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}