package com.beyonder;

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

@Path("/hello")
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
        ObjectMapper omm = new ObjectMapper();
        String jsn = omm.writeValueAsString(addProductReqDTO);
        System.out.println(jsn);
        Product product = new Product();
        product.setProductName(addProductReqDTO.getName());
        product.setProductCode(addProductReqDTO.getCode());
        productList.add(product);

        for(Product prod: productList){
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(prod);
            System.out.println(json);
        }
        return Response.ok().entity(productList).build();
    }
}
