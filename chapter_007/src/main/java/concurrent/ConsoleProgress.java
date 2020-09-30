package concurrent;

public class ConsoleProgress implements Runnable{
    @Override
    public void run() {
        long time = System.currentTimeMillis();
        char[] process = {'\\', '|', '/', '-'};
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.printf("%s \r", process[i++]);
                Thread.sleep(500L - (System.currentTimeMillis() - time));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if(i == 4) {
                i = 0;
            }
            time = System.currentTimeMillis();
        }
    }
}
