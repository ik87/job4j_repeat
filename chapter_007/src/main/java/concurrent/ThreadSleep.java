package concurrent;

public class ThreadSleep {
    public static void main(String[] args) {
        Thread thread = new Thread( () -> {
            try {
                System.out.println("Start loading....");
                download();
                System.out.println("Loaded");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    thread.start();
        System.out.println("Main");
    }

    public static void download() throws InterruptedException{
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.printf("%d \r", i);
            Thread.sleep(100L - (System.currentTimeMillis() - time));
            time = System.currentTimeMillis();
        }
    }

}
