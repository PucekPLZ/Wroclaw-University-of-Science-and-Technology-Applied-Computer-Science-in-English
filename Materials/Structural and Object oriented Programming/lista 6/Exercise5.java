import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise5 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exercise 5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBounds(50, 50, 200, 30);

        JLabel label = new JLabel("Selected: ");
        label.setBounds(50, 100, 200, 30);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Selected: " + comboBox.getSelectedItem());
            }
        });

        frame.add(comboBox);
        frame.add(label);
        frame.setVisible(true);
    }
}
