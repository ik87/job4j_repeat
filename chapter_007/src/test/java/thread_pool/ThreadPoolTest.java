package thread_pool;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void whenThreadPoolIncrementThenIncremented() throws InterruptedException {
        int cpu = Runtime.getRuntime().availableProcessors();
        ThreadPool threadPool = new ThreadPool(cpu, cpu);
        int[] count = {0};
        for(int i = 0; i < 10; i++) {
            threadPool.work(() -> count[0]++);
        }
        while (threadPool.countOfTasks() != 0) {
            Thread.sleep(10);
        }
        threadPool.shutdown();
        assertThat(count[0], is(10));
    }

}