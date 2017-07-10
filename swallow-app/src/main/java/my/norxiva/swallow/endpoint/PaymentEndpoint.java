package my.norxiva.swallow.endpoint;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.swallow.core.CryptMessage;
import my.norxiva.swallow.merchant.query.Merchant;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Component
@Path("payment")
public class PaymentEndpoint {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid @NotNull CryptMessage message){
        log.info("crypt message: {}", message);



        return Response.ok("Hi, ").build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@Valid @NotNull CryptMessage message){
        return Response.ok("Hi, ").build();
    }

    @POST
    @Path("apply")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response apply(@Valid @NotNull CryptMessage message){
        return Response.ok("").build();
    }

    @POST
    @Path("confirm")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirm(@Valid @NotNull CryptMessage message){
        return Response.ok("").build();
    }

    @POST
    @Path("cancel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancel(@Valid @NotNull CryptMessage message){
        return Response.ok("").build();
    }

    @POST
    @Path("refund")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response refund(@Valid @NotNull CryptMessage message){
        return Response.ok("").build();
    }







}
