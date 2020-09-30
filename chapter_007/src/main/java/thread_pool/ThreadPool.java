package thread_pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import producer_consumer.SimpleBlockQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Thread pool
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 2
 * @since 29.09.2020
 */
public class ThreadPool {
    private static final Logger LOGGER = LogManager.getLogger(ThreadPool.class.getName());

    private final List<Core> cores = new ArrayList<>();
    private final SimpleBlockQueue<Runnable> tasks;

    public ThreadPool(int cores, int tasks) {
        this.tasks = new SimpleBlockQueue<>(tasks);

        for(int i = 0; i < cores; i++) {
            this.cores.add(new Core(this.tasks));
        }

        for(Core c : this.cores) {
            c.start();
            LOGGER.debug("{} has been running", c.getName());
        }
    }

    public void work(Runnable job) {
        if(cores.isEmpty()) {
            throw new IllegalStateException("There aren't any working cores");
        }

        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            LOGGER.debug("work have been interrupted");
        }
    }

    public void shutdown() {
        for (Core core : this.cores) {
            core.interrupt();
            LOGGER.debug("{} have been interrupted", core.getName());
        }
        cores.clear();

    }

    public int countOfTasks() {
        return tasks.size();
    }
}
