package dev.sachith;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

/**
 * @author sachith
 */
@Provider
class AuthenticationFilter implements ContainerRequestFilter {
    private final String apiKey;

    public AuthenticationFilter(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.equals("Bearer " + apiKey)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Invalid API Key").build());
        }
    }
}
