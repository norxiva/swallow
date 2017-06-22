package my.norxiva.swallow.endpoint;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.swallow.core.PaymentStep;
import my.norxiva.swallow.core.CryptMessage;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Component
@Path("order")
public class OrderEndpoint {

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
    public Response find(){
        return Response.ok("Hi, ").build();
    }

    @POST
    @Path("{step}")
    public Response fast(@PathParam("step") PaymentStep step){
        return Response.ok("").build();
    }

}
