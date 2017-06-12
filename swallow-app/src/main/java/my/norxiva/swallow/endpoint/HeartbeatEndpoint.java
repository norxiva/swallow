package my.norxiva.swallow.endpoint;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("heartbeat")
public class HeartbeatEndpoint {

    @GET
    @Path("hi")
    public Response hi(@QueryParam("name") @NotNull String name){
        return Response.ok("Hi, " + name).build();
    }
}
