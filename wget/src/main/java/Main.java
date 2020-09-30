import java.net.MalformedURLException;

public class Main {
    private static final boolean DEBUG = true;
    public static void main(String[] args) {
       if(DEBUG) {
           String url = "https://github.com/ik87/TheatreSquare/archive/master.zip";
           int speed = 200;
           try {
               new Thread(new Download(url, speed)).start();
           } catch (MalformedURLException e) {
               e.printStackTrace();
           }
       } else {
           if (args.length < 2) {
               System.out.println("add parameters [url] [speed]");
               System.exit(0);
           }
           String path = args[0];
           try {
               int speed = Integer.valueOf(args[1]);
               Thread download = new Thread(new Download(path, speed));
               download.start();
               download.join();
           } catch (NumberFormatException e) {
               System.out.println("not suitable speed");
           } catch (MalformedURLException e) {
               System.out.println("not suitable url");
           } catch (InterruptedException e) {
               System.out.println("download interrupted");
           }
       }

    }
}
