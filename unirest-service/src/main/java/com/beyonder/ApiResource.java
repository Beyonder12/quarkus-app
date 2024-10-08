package com.beyonder;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/api")
@ApplicationScoped
public class ApiResource {

    @ConfigProperty(name = "api.base.url")
    String apiBaseUrl;

    void onStart(@Observes StartupEvent ev) {
        // Configure Unirest globally
        Unirest.config()
               .setDefaultHeader("Accept", "application/json")
               .setDefaultHeader("Content-Type", "application/json")
               .connectTimeout(10000)
               .retryAfter(true, 3);
    }
    void onShutdown(@Observes ShutdownEvent ev) {
        // Shutdown Unirest when the application is shutting down explicily
        Unirest.shutDown();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@QueryParam("page") @DefaultValue("1") int page) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(apiBaseUrl + "/users")
                .queryString("page", page)
                .asJson();

            if (response.isSuccess()) {
                return Response.ok(response.getBody()).build();
            } else {
                return Response.status(response.getStatus()).entity(response.getBody()).build();
            }
        } catch (UnirestException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error fetching users: " + e.getMessage())
                           .build();
        }
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String userJson) {
        try {
            HttpResponse<JsonNode> response = Unirest.post(apiBaseUrl + "/users")
                .body(userJson)
                .asJson();

            if (response.isSuccess()) {
                return Response.status(Response.Status.CREATED).entity(response.getBody()).build();
            } else {
                return Response.status(response.getStatus()).entity(response.getBody()).build();
            }
        } catch (UnirestException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error creating user: " + e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") String id, String userJson) {
        try {
            HttpResponse<JsonNode> response = Unirest.put(apiBaseUrl + "/users/{id}")
                .routeParam("id", id)
                .body(userJson)
                .asJson();

            if (response.isSuccess()) {
                return Response.ok(response.getBody()).build();
            } else {
                return Response.status(response.getStatus()).entity(response.getBody()).build();
            }
        } catch (UnirestException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error updating user: " + e.getMessage())
                           .build();
        }
    }

    @DELETE
    @Path("/users/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        try {
            HttpResponse<JsonNode> response = Unirest.delete(apiBaseUrl + "/users/{id}")
                .routeParam("id", id)
                .asJson();

            if (response.isSuccess()) {
                return Response.noContent().build();
            } else {
                return Response.status(response.getStatus()).entity(response.getBody()).build();
            }
        } catch (UnirestException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error deleting user: " + e.getMessage())
                           .build();
        }
    }
}