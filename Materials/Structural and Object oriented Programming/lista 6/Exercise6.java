import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise6 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exercise 6");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JTextField textField1 = new JTextField();
        textField1.setBounds(50, 30, 120, 30);
        JTextField textField2 = new JTextField();
        textField2.setBounds(200, 30, 120, 30);

        JButton addButton = new JButton("+");
        addButton.setBounds(50, 80, 50, 30);
        JButton subtractButton = new JButton("-");
        subtractButton.setBounds(110, 80, 50, 30);
        JButton multiplyButton = new JButton("*");
        multiplyButton.setBounds(170, 80, 50, 30);
        JButton divideButton = new JButton("/");
        divideButton.setBounds(230, 80, 50, 30);

        JLabel resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(50, 140, 300, 30);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(textField1.getText());
                    double num2 = Double.parseDouble(textField2.getText());
                    double result = 0;

                    if (e.getSource() == addButton) {
                        result = num1 + num2;
                    } else if (e.getSource() == subtractButton) {
                        result = num1 - num2;
                    } else if (e.getSource() == multiplyButton) {
                        result = num1 * num2;
                    } else if (e.getSource() == divideButton) {
                        if (num2 == 0) {
                            resultLabel.setText("Error: Division by zero");
                            return;
                        }
                        result = num1 / num2;
                    }
                    resultLabel.setText("Result: " + result);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Error: Invalid input");
                }
            }
        };

        addButton.addActionListener(actionListener);
        subtractButton.addActionListener(actionListener);
        multiplyButton.addActionListener(actionListener);
        divideButton.addActionListener(actionListener);

        frame.add(textField1);
        frame.add(textField2);
        frame.add(addButton);
        frame.add(subtractButton);
        frame.add(multiplyButton);
        frame.add(divideButton);
        frame.add(resultLabel);

        frame.setVisible(true);
    }
}