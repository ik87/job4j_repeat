package thread_pool;

import producer_consumer.SimpleBlockQueue;

public class Core extends Thread {
    private final SimpleBlockQueue<Runnable> tasks;

    public Core(SimpleBlockQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Runnable runnable = tasks.poll();
                runnable.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
