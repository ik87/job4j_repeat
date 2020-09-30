package share_recources.atomic;

import java.util.concurrent.BrokenBarrierException;


public class CountShareMain {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Count count = new Count();
        Thread first = new Thread(count::increment);
        Thread second = new Thread(count::increment);
        first.start();
        second.start();
        first.join();
        second.join();

        System.out.println(count.getValue());
    }
}
