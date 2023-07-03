package com.beyonder.controller;

import com.beyonder.dto.request.AddProductReqDTO;
import com.beyonder.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Path("/v1/hello")
public class ExampleResource {
    List<Product> productList = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(AddProductReqDTO addProductReqDTO) throws JsonProcessingException {
        return Response.ok().entity(productList).build();
    }
}
