import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Exercise1 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Exercise 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Hello Swing!", SwingConstants.CENTER);
        frame.add(label);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}