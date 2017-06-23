package my.norxiva.swallow;

import my.norxiva.swallow.endpoint.PaymentEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        register(PaymentEndpoint.class);
    }
}
