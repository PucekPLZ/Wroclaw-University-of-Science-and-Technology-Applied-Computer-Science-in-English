import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise3 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exercise 3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        JTextField textField = new JTextField();
        textField.setBounds(50, 50, 200, 30);

        JLabel label = new JLabel("Enter text above");
        label.setBounds(50, 100, 200, 30);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(textField.getText());
            }
        });

        frame.add(textField);
        frame.add(label);
        frame.setVisible(true);
    }
}
