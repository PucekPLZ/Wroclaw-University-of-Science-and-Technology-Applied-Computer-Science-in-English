import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise5 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Exercise 5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(null); 

        JTextField numField1 = new JTextField();
        numField1.setBounds(50, 50, 100, 30); 

        JTextField numField2 = new JTextField();
        numField2.setBounds(200, 50, 100, 30); 

        JButton calcButton = new JButton("Calculate");
        calcButton.setBounds(150, 100, 100, 30); 

        JLabel resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(50, 150, 300, 30); 

        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(numField1.getText());
                    double num2 = Double.parseDouble(numField2.getText());

                    double sum = num1 + num2;

                    resultLabel.setText("Result: " + sum);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter valid numbers.");
                }
            }
        });

        frame.add(numField1);
        frame.add(numField2);
        frame.add(calcButton);
        frame.add(resultLabel);
        frame.setVisible(true);
    }
}
