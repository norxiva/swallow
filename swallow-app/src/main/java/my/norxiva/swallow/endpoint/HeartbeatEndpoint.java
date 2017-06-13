package my.norxiva.swallow.endpoint;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.swallow.CacheRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheValue;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@Path("heartbeat")
public class HeartbeatEndpoint {

    private CacheRepository cacheRepository;

    public HeartbeatEndpoint(CacheRepository cacheRepository){
        this.cacheRepository = cacheRepository;
    }

    @GET
    @Path("hi")
    public Response hi(@QueryParam("name") @NotNull String name){
        log.info(cacheRepository.genKey(name));
        return Response.ok("Hi, " + name).build();
    }




}
