package my.norxiva.swallow.endpoint;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.swallow.CacheRepository;
import my.norxiva.swallow.core.OrderStatus;
import my.norxiva.swallow.core.PaymentType;
import my.norxiva.swallow.core.TransactionType;
import my.norxiva.swallow.order.query.Order;
import my.norxiva.swallow.order.query.OrderRepository;
import my.norxiva.swallow.order.query.Transaction;
import my.norxiva.swallow.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheValue;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
@Path("heartbeat")
public class HeartbeatEndpoint {

    private CacheRepository cacheRepository;

    private OrderRepository orderRepository;

    private SnowFlake snowFlake;

    @Autowired
    public HeartbeatEndpoint(CacheRepository cacheRepository,
                             OrderRepository orderRepository,
                             SnowFlake snowFlake){
        this.cacheRepository = cacheRepository;
        this.orderRepository = orderRepository;
        this.snowFlake = snowFlake;
    }

    @GET
    @Path("hi")
    public Response hi(@QueryParam("name") @NotNull String name){
        log.info(cacheRepository.genKey(name));
        return Response.ok("Hi, " + name).build();
    }

    @POST
    @Path("order")
    public Response order(){

        Order order = new Order();

        order.setId(snowFlake.nextId());
        order.setNo(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setMerchantId(1L);
        order.setTransactionType(TransactionType.CHARGE);
        order.setPaymentType(PaymentType.FASTPAY);
        order.setOrderTime(LocalDateTime.now());
        order.setCreateTime(order.getOrderTime());
        order.setUpdateTime(order.getOrderTime());
        order.setStatus(OrderStatus.CREATED);

        order = orderRepository.save(order);

        return Response.ok("Create Order: " + order.getId()).build();
    }




}
