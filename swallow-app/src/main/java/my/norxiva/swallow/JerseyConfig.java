package my.norxiva.swallow;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("swallow/api")
@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        packages("my.norxiva.swallow.endpoint");
    }
}
