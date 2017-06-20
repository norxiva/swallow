package my.norxiva.swallow.endpoint;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import my.norxiva.swallow.CacheRepository;
import my.norxiva.swallow.aop.TestBean;
import my.norxiva.swallow.aop.TestService;
import my.norxiva.swallow.core.OrderStatus;
import my.norxiva.swallow.core.PaymentType;
import my.norxiva.swallow.core.TransactionType;
import my.norxiva.swallow.job.TestJob;
import my.norxiva.swallow.transaction.query.Order;
import my.norxiva.swallow.transaction.query.OrderRepository;
import my.norxiva.swallow.retry.RetryService;
import my.norxiva.swallow.util.SnowFlake;
import net.greghaines.jesque.Job;
import net.greghaines.jesque.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@Path("heartbeat")
public class HeartbeatEndpoint {

    private CacheRepository cacheRepository;

    private OrderRepository orderRepository;

    private SnowFlake snowFlake;

    private Client client;

    private TestService testService;

    private RetryService retryService;

    @Autowired
    public HeartbeatEndpoint(CacheRepository cacheRepository,
                             OrderRepository orderRepository,
                             SnowFlake snowFlake,
                             Client client,
                             TestService testService,
                             RetryService retryService){
        this.cacheRepository = cacheRepository;
        this.orderRepository = orderRepository;
        this.snowFlake = snowFlake;
        this.client = client;
        this.testService = testService;
        this.retryService = retryService;
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

    @POST
    @Path("queue")
    public Response queue(){

        Job job = new Job();
        job.setClassName(TestJob.class.getName());
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", "Henry");
        map.put("age", LocalDateTime.now().toString());
        Map<String, Object> vars = Maps.newHashMap();
        vars.put("params", map);
        job.setVars(vars);

        long future = System.currentTimeMillis() + 3 * 1000;

        client.delayedEnqueue("swallow-queue", job, future);

        log.info("delayed enqueue has been sent.");

        return Response.ok("Delay queue: " + vars).build();
    }


    @POST
    @Path("queue_annotation")
    public Response queueAnnotation(){

        testService.test(new TestBean("norxiva", "haha"));

//        testService.test2("norxiva2","haha2");

        return Response.ok("queueAnnotation: ").build();
    }

    @POST
    @Path("retry")
    public Response retry(){
        retryService.test();
        return Response.ok("queueAnnotation: ").build();
    }




}
