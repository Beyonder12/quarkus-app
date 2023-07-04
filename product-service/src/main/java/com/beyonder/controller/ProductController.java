package com.beyonder.controller;

import com.beyonder.dto.AuthRestRespDTO;
import com.beyonder.dto.base.SuccessResponseDTO;
import com.beyonder.dto.request.CreateProductReqDTO;
import com.beyonder.service.ProductService;
import com.beyonder.util.CodeStatusConst;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/v1/products")
@Tag(name = "Product Controller", description = "CRUD Products")
public class ProductController {

    private final static Logger log = LoggerFactory.getLogger(ProductController.class);
    @Inject
    ProductService productService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a product", description = "Create a product and it's detail")
    public Response create(CreateProductReqDTO createProductReqDTO) throws JsonProcessingException {
        log.info("create method called with createProductReqDTO : {}", createProductReqDTO);
        productService.create(createProductReqDTO);
        log.info("Before");
        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(new SuccessResponseDTO(CodeStatusConst.CREATED, "Created"));
        log.info("Data is : {}", data);
        System.out.println(data);
        return Response.ok().entity(new SuccessResponseDTO(CodeStatusConst.CREATED, "Created")).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all data", description = "Data can be fetched either by filter or not")
    public Response getAll(@QueryParam("name") String name, @QueryParam("code") String code ) {
        log.info("getAll method called with name : {}, code : {}", name, code);
        return Response.ok().entity(productService.getAll(name, code)).build();
    }
}
