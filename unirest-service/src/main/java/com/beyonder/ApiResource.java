package com.beyonder;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;


import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import kong.unirest.*;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/api")
@ApplicationScoped
public class ApiResource {

    @ConfigProperty(name = "api.base.url")
    String apiBaseUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiResource.class);

    @Inject
    ObjectMapper om;

    void onStart(@Observes StartupEvent ev) {
        // Configure Unirest globally
        Unirest.config()
               .setDefaultHeader("Accept", "application/json")
               .setDefaultHeader("Content-Type", "application/json")
               .connectTimeout(1000)
               .retryAfter(true, 3);
    }
    void onShutdown(@Observes ShutdownEvent ev) {
        // Shutdown Unirest when the application is shutting down explicitly
        Unirest.shutDown();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUsers(@QueryParam("page") @DefaultValue("1") int page) {
        try {

            HttpResponse<Object> response = Unirest
                    .get( "http://localhost:8080/api/tests")
                    .queryString("page", page)
                    .asObject(Object.class);

            // Object -> DTO using mapper
            Object responseBody = response.getBody();
            RespDTO data = om.convertValue(responseBody, RespDTO.class);

            // Object -> JsonObject -> DTO using JsonObject methods
//            JsonObject jsonObject = JsonObject.mapFrom(response.getBody());
//            RespDTO data = jsonObject.mapTo(RespDTO.class);

            if (response.isSuccess()) {
                LOGGER.info("succcess : {}", page);
                return Response.ok(data).build();
            } else {
                return Response.status(response.getStatus()).entity(response.getBody()).build();
            }
        } catch (UnirestException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error fetching users: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/tests")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTests(@QueryParam("page") @DefaultValue("1") int page) {
        try {
            LOGGER.info("pagetest : {}", page);
            JsonObject resp = new JsonObject();
            resp.put("name", "joko");
            resp.put("age", true);
            resp.put("page", page);
            resp.put("dataList", List.of("good", "noodGood"));
            for (int i=1; i <= 2; i++) {
                Thread.sleep(1000);
                LOGGER.info("data : {}", i);
            }

            return Response.ok(resp).build();
        } catch (Exception e) {
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