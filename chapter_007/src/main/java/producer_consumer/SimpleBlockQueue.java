package producer_consumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 1.0
 * @since 28.09.2020
 */
@ThreadSafe
public class SimpleBlockQueue<T> {
    private static final Logger LOG = LogManager.getLogger(SimpleBlockQueue.class.getName());
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public SimpleBlockQueue(int capacity) {
        this.capacity = capacity;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            LOG.debug("{} offer section", Thread.currentThread().getName());
            while (queue.size() > capacity) {
                LOG.debug("{} offer wait", Thread.currentThread().getName());
                wait();
            }
            queue.offer(value);
            notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            LOG.debug("{} poll section", Thread.currentThread().getName());
            while (queue.size() == 0) {
                LOG.debug("{} poll wait", Thread.currentThread().getName());
                wait();
            }
            T result = queue.poll();
            notifyAll();
            return result;
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized int size() {
        LOG.debug("queue size is {}", queue.size());
        return queue.size();
    }
}
