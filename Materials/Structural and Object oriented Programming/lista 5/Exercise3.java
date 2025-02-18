import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise3 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Exercise 3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null); 


        JTextField textField = new JTextField();
        textField.setBounds(50, 50, 200, 30); 

        JButton button = new JButton("Print Text");
        button.setBounds(260, 50, 100, 30); 

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                System.out.println("You entered: " + text);
            }
        });

        frame.add(textField);
        frame.add(button);
        frame.setVisible(true);
    }
}
