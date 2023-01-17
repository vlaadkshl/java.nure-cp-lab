/*
 * Розробити програму, яка дозволить користувачу нарисувати на екрані прямокутник.
 * В програмі має бути можливість задавати колір прямокутника.
 * */

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            DrawingAppFrame frame = new DrawingAppFrame();
            frame.setVisible(true);
        });
    }
}
