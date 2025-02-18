import javax.swing.*;
import java.awt.*;

public class Exercise10 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exercise 10");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillRect(50, 50, 100, 50);
                g.setColor(Color.BLUE);
                g.fillOval(200, 50, 50, 50);
            }
        };

        frame.add(drawingPanel);
        frame.setVisible(true);
    }
}