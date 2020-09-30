package check_then_act;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CASCountTest {
    @Test
    public void whenConcurrentlyIncrementThenIncremented() throws InterruptedException {
        final int i = 100;
        CASCount casCount = new CASCount(0);
        CyclicBarrier cb = new CyclicBarrier(i);
        for (int j = 0; j < i; j++) {
            (new Thread(() -> {
                try {
                    cb.await();
                    casCount.increment();
                } catch (BrokenBarrierException | InterruptedException e) {

                }
            })).start();
        }
        Thread.sleep(1000);
        assertThat(i, is(casCount.get()));
    }

}