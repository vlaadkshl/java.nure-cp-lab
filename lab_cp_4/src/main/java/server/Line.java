package server;

import javax.swing.*;
import java.awt.*;

public class Line extends JComponent {
    private Color currentColor;
    private final Point startPoint;

    public Line(Color currentColor, Point startPoint) {
        this.currentColor = currentColor;
        this.startPoint = startPoint;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(currentColor);
        g.fillRect(startPoint.x, startPoint.y, 100, 5);
    }
}
