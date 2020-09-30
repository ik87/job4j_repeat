package concurrent;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException{
/*        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                    }
                }
        );
        thread.start();
        Thread.sleep(1);
        thread.interrupt();*/

        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(4000);
        progress.interrupt();
    }
}
