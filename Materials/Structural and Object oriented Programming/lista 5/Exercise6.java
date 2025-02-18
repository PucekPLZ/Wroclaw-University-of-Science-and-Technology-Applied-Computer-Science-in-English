import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise6 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Exercise 6");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null); 

        JButton redButton = new JButton("Red");
        redButton.setBounds(50, 220, 80, 30);

        JButton greenButton = new JButton("Green");
        greenButton.setBounds(160, 220, 80, 30);

        JButton blueButton = new JButton("Blue");
        blueButton.setBounds(270, 220, 80, 30);

        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.RED);
            }
        });

        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.GREEN);
            }
        });

        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.BLUE);
            }
        });

        frame.add(redButton);
        frame.add(greenButton);
        frame.add(blueButton);
        frame.setVisible(true);
    }
}
