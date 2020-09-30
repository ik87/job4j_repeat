package nonblocking_cache;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CacheStorageTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            try {
                throw new RuntimeException("Throw Exception in Thread");
            } catch (Exception e) {
                ex.set(e);
            }
        });
        thread.start();
        thread.join();
        assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }

    @Test
    public void whenPutTheSameThenException() throws InterruptedException, BrokenBarrierException {
        CyclicBarrier cb = new CyclicBarrier(2);
        CacheStorage cacheStorage = new CacheStorage();
        cacheStorage.add(new Base(1, "dex"));
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread th1 = new Thread(() -> {
            try {
                cb.await();
                cacheStorage.update(new Base(1, "dex"));
            } catch (Exception e) {
                ex.set(e);
            }
        });

        th1.start();

        try {
            cb.await();
            cacheStorage.update(new Base(1, "dex"));
        } catch (Exception e) {
            ex.set(e);
        }
        th1.join();

        assertThat(ex.get().getMessage(), is("OptimisticException"));
    }

}