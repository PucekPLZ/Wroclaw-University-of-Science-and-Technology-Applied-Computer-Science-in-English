import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Exercise7 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exercise 7");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        JLabel label = new JLabel("visible");
        label.setBounds(50, 50, 200, 30);

        JCheckBox checkBox = new JCheckBox("Show/Hide Label");
        checkBox.setBounds(50, 100, 150, 30);

        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                label.setVisible(checkBox.isSelected());
            }
        });

        frame.add(label);
        frame.add(checkBox);
        frame.setVisible(true);
    }
}