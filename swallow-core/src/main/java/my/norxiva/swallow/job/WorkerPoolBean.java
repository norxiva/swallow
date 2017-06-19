package my.norxiva.swallow.job;

import lombok.extern.slf4j.Slf4j;
import net.greghaines.jesque.worker.WorkerPool;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;

@Slf4j
public class WorkerPoolBean implements DisposableBean, BeanNameAware{

    private String beanName;
    private WorkerPool workerPool;

    public WorkerPoolBean(WorkerPool workerPool){
        this.workerPool = workerPool;
        working();
    }

//    @Scheduled(initialDelay = 0, fixedRate = Long.MAX_VALUE)
    private void working(){
        log.info("workerPoolBean[{}] started.", this.beanName);
        new Thread(workerPool).start();
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void destroy() throws Exception {
        log.info("destroy workerPoolBean[{}]", this.beanName);
        this.workerPool.endAndJoin(true, 30*1000);
    }
}
