package my.norxiva.swallow;

import my.norxiva.swallow.job.WorkerPoolBean;
import net.greghaines.jesque.Config;
import net.greghaines.jesque.ConfigBuilder;
import net.greghaines.jesque.client.Client;
import net.greghaines.jesque.client.ClientImpl;
import net.greghaines.jesque.worker.ReflectiveJobFactory;
import net.greghaines.jesque.worker.WorkerImpl;
import net.greghaines.jesque.worker.WorkerPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.Collection;

@Configuration
public class DelayJobConfig {

    @Bean
    public Config jesqueConfig(@Value("${spring.redis.host}") String host,
                         @Value("${spring.redis.port}") Integer port) {
        return new ConfigBuilder()
                .withHost(host)
                .withPort(port)
                .withPassword(null)
                .withNamespace("swallow:jesque")
                .build();
    }

    @Bean
    public Client jesqueClient(Config config, StringRedisTemplate redisTemplate) {
        return new ClientImpl(config);
    }

//    @Bean Worker worker(Config config){
//        Worker worker = new WorkerImpl(config,
//                Arrays.asList("swallow-queue"), new MapBasedJobFactory(map(entry(TestJob.class.getName(), TestJob.class))));
//
//        final Thread workerThread = new Thread(worker);
//        workerThread.start();
//        return worker;
//    }

    @Bean
    public WorkerPool workerPool(Config config) {

        Collection<String> queues = Arrays.asList("swallow-queue");
        return new WorkerPool(() -> new WorkerImpl(config, queues,
                new ReflectiveJobFactory()), 20);
    }

    @Bean(name = "workerPoolBean")
    public WorkerPoolBean workerPoolBean(WorkerPool workerPool){
        return new WorkerPoolBean(workerPool);
    }


}
