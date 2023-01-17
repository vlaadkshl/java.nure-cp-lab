package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class IntegerGenerator implements Runnable {
    private final OutputStream out;
    private final long timeToSleep;

    public IntegerGenerator(Socket socket, long timeToSleep) throws IOException {
        this.out = socket.getOutputStream();
        this.timeToSleep = timeToSleep;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            while (true) {
                int integer = random.nextInt(0, 256);

                out.write(integer);
                out.flush();

                Thread.sleep(timeToSleep);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}