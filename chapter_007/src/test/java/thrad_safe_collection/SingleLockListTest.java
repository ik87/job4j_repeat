package thrad_safe_collection;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SingleLockListTest {

    @Test
    public void whenAddThenAdded() throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(2);
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> {
            try {
                cb.await();
                list.add(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        } );
        Thread second = new Thread(() -> {
            try {
                cb.await();
                list.add(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        } );
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1,2)));
    }

}