import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise8 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Exercise 8");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(null); 

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30); 

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);

        JTextField userField = new JTextField();
        userField.setBounds(150, 50, 150, 30); 

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 100, 150, 30); 

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30); 

        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(50, 190, 300, 30); 

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (username.equals("admin") && password.equals("1234")) {
                    resultLabel.setText("Login successful!");
                    resultLabel.setForeground(java.awt.Color.GREEN);
                } else {
                    resultLabel.setText("Invalid username or password.");
                    resultLabel.setForeground(java.awt.Color.RED);
                }
            }
        });

        frame.add(userLabel);
        frame.add(passLabel);
        frame.add(userField);
        frame.add(passField);
        frame.add(loginButton);
        frame.add(resultLabel);
        frame.setVisible(true);
    }
}
