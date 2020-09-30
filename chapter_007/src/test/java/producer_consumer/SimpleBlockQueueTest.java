package producer_consumer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleBlockQueueTest {

    @Test
    public void whenProduceThenConsume() throws InterruptedException {
        SimpleBlockQueue<Integer> queue = new SimpleBlockQueue<>(2);
        List<Integer> result = new ArrayList<>();
        Thread producer = new Thread(() -> {
            IntStream.range(0, 5).forEach(x -> {
                try {
                    queue.offer(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        Thread consumer = new Thread(() -> {
            while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    result.add(queue.poll());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        consumer.start();
        producer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();

        assertThat(result, is(Arrays.asList(0,1,2,3,4)));

    }

}