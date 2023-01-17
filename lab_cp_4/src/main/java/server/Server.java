package server;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            Socket clientSocket = serverSocket.accept();
            InputStream in = clientSocket.getInputStream();

            List<Color> colorList = new ArrayList<>();
            Color currentColor;

            HashMap<Character, Double> averages = new HashMap<>();
            ServerGUI gui = new ServerGUI(averages);

            while (true) {
                try {
                    //  Змінюю значення поточного кольору
                    currentColor = new Color(in.read(), in.read(), in.read());

                    //  Додаю колір до списку всіх раніше отриманих кольорів,
                    //  щоб після відключення порахувати середні значення
                    colorList.add(currentColor);

                    //  Назначаю GUI отриманий колір та змушую інтерфейс перемалюватися
                    gui.setCurrentColor(currentColor);
                    gui.repaint();
                } catch (IOException err) {
                    //  Якщо підключення буде розірвано, метод in.read() надішле виключення IOException
                    System.out.println("Client was disconnected.");

                    averages.put('r', colorList.stream().mapToDouble(Color::getRed).average().orElse(0));
                    averages.put('g', colorList.stream().mapToDouble(Color::getGreen).average().orElse(0));
                    averages.put('b', colorList.stream().mapToDouble(Color::getBlue).average().orElse(0));

                    System.out.println("List: " + colorList);
                    System.out.println("AVG(R) = " + averages.get('r'));
                    System.out.println("AVG(G) = " + averages.get('g'));
                    System.out.println("AVG(B) = " + averages.get('b'));

                    break;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
