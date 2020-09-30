package concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@ThreadSafe
public class Counter {
    @GuardedBy("this")
    private int value;


    public void increment() {
        this.value++;
    }

    public int get() {
        return this.value;
    }

    public static void main(String[] args) throws InterruptedException{
        Counter counter = new Counter();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread first = new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            counter.increment();
        });

        Thread second = new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            counter.increment();
        });

        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(counter.get());
    }
}

