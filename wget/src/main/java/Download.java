import org.apache.commons.io.FilenameUtils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Download implements Runnable {
    private final static int BUFFER = 1024;
    private URL url;
    private int speed;


    public Download(String path, int speed) throws MalformedURLException {
        url = new URL(path);
        this.speed = speed * 1024;
    }

    @Override
    public void run() {
        String filename = FilenameUtils.getName(url.getPath());
        char[] mark = {'\\', '|', '/', '―'};
        int i = 0;
        int download = 0;
        try (

                BufferedInputStream in = new BufferedInputStream(url.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ) {
            int bytesRead = 0;
            byte[] dataBuffer = new byte[BUFFER];
            int sum = 0;
            long time = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, BUFFER)) != -1) {
                sum += bytesRead;
                download += bytesRead;
                if(sum >= speed) {
                    Thread.sleep(1000L - (System.currentTimeMillis() - time));
                    sum = 0;
                }
                time = System.currentTimeMillis();
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                System.out.printf("%s %c %d kb \r", filename, mark[i++], download);
                if(i == 4) {
                    i = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("download interrupted");
        }
        System.out.printf("%s %c %d kb finish \r", filename, mark[i++], download);
    }
}
