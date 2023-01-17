package client;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 5000);
        ExecutorService executors = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executors.submit(new IntegerGenerator(client, 2000));
        }
        executors.shutdown();
    }
}
