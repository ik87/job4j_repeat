package control_thread_with_use_wait;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {

    }

    public void await() {
        synchronized (monitor) {
            monitor.notifyAll();
            count++;
            while (total != count) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountBarrier countBarrier = new CountBarrier(2);
        Thread th1 = new Thread(()-> {
            countBarrier.await();
            System.out.println(Thread.currentThread().getName() + " current thread");
        });
        Thread th2 = new Thread(()-> {
            countBarrier.await();
            System.out.println(Thread.currentThread().getName() + " current thread");
        });

        th1.start();
        th2.start();
        th1.join();
        th2.join();
    }
}
