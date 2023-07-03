package com.beyonder.controller;

import com.beyonder.dto.AuthRestRespDTO;
import com.beyonder.dto.base.SuccessResponseDTO;
import com.beyonder.dto.request.CreateProductReqDTO;
import com.beyonder.service.ProductService;
import com.beyonder.util.CodeStatusConst;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1/products")
@Tag(name = "Product Controller", description = "CRUD Products")
public class ProductController {

    @Inject
    ProductService productService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a product", description = "Create a product and it's detail")
    public Response create(CreateProductReqDTO createProductReqDTO) {
        productService.create(createProductReqDTO);
        return Response.ok().entity(new SuccessResponseDTO(CodeStatusConst.CREATED, "Created")).build();

    }
}
