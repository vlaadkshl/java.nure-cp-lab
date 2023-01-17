package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class ServerGUI extends JFrame {
    private final JList<String> colorPanel;
    private final Line line;
    private Color currentColor = Color.BLACK;

    ServerGUI(HashMap<Character, Double> averages) {
        setTitle("Server App");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //  Панель зі значеннями червоного, зеленого та блакитного
        colorPanel = new JList<>();
        add(colorPanel, BorderLayout.NORTH);

        line = new Line(currentColor, new Point(50, 100));
        add(line);

        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        //  Обробка комбінації клавіш для показу середніх значень
        KeyStroke showStroke = KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK);
        inputMap.put(showStroke, "show");
        actionMap.put("show", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (averages.size() > 0) {
                    String r = "AVG(R) = " + averages.get('r') + "<br>";
                    String g = "AVG(G) = " + averages.get('g') + "<br>";
                    String b = "AVG(B) = " + averages.get('b');
                    JOptionPane.showMessageDialog(getInstance(), "<html>" + r + g + b + "</html>");
                } else {
                    JOptionPane.showMessageDialog(getInstance(), "Averages are empty. You can't get them.");
                }
            }
        });

        //  Обробка комбінації клавіш для видалення середніх значень
        KeyStroke deleteStroke = KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.ALT_DOWN_MASK);
        inputMap.put(deleteStroke, "delete");
        actionMap.put("delete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (averages.size() > 0) {
                    averages.clear();
                    JOptionPane.showMessageDialog(getInstance(), "All averages were removed!");
                } else {
                    JOptionPane.showMessageDialog(getInstance(), "Averages are empty. You can't delete them.");
                }
            }
        });

        setVisible(true);
    }

    public ServerGUI getInstance() {
        return this;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
        line.setCurrentColor(currentColor);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        colorPanel.setListData(new String[]{"R: " + currentColor.getRed(), "G: " + currentColor.getGreen(), "B: " + currentColor.getBlue()});
        line.repaint();
    }
}