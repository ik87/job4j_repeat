package concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        Thread second = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        thread.start();
        second.start();
        thread.join();
        second.join();
        System.out.println(Thread.currentThread().getName());
    }
}
