import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingAppFrame extends JFrame {
    private Color rectColor = Color.BLACK;
    private final JPanel paintingPanel;
    private Point startPoint;
    private Point endPoint;

    public DrawingAppFrame() {
        this.setTitle("Draw-a-Rect!");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ColorChooserButton colorChooser = new ColorChooserButton(rectColor);
        colorChooser.addColorChangedListener(newColor -> rectColor = newColor);

        this.paintingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (startPoint != null && endPoint != null) {
                    int x = Math.min(startPoint.x, endPoint.x);
                    int y = Math.min(startPoint.y, endPoint.y);
                    int width = Math.abs(startPoint.x - endPoint.x);
                    int height = Math.abs(startPoint.y - endPoint.y);

                    g.setColor(rectColor);
                    g.fillRect(x, y, width, height);
                    g.setColor(complementaryColor(rectColor));
                    g.drawRect(x, y, width, height);
                }
            }
        };
        paintingPanel.setBackground(Color.WHITE);
        paintingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                paintingPanel.repaint();
            }
        });
        add(colorChooser, BorderLayout.NORTH);
        add(paintingPanel);
    }

    Color complementaryColor(final @NotNull Color bgColor) {
        return new Color(255 - bgColor.getRed(),
                255 - bgColor.getGreen(),
                255 - bgColor.getBlue());
    }
}
