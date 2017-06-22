package my.norxiva.swallow;

import my.norxiva.swallow.endpoint.OrderEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        register(OrderEndpoint.class);
    }
}
