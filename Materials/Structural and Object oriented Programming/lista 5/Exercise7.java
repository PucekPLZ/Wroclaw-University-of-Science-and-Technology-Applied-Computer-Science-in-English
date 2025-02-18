import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise7 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Exercise 7");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null); 

        String[] colors = {"White", "Red", "Green", "Blue"};
        JComboBox<String> colorDropdown = new JComboBox<>(colors);
        colorDropdown.setBounds(150, 220, 100, 30);

        colorDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColor = (String) colorDropdown.getSelectedItem();

                switch (selectedColor) {
                    case "Red":
                        frame.getContentPane().setBackground(Color.RED);
                        break;
                    case "Green":
                        frame.getContentPane().setBackground(Color.GREEN);
                        break;
                    case "Blue":
                        frame.getContentPane().setBackground(Color.BLUE);
                        break;
                    default:
                        frame.getContentPane().setBackground(Color.WHITE);
                }
            }
        });

        frame.add(colorDropdown);
        frame.setVisible(true);
    }
}
