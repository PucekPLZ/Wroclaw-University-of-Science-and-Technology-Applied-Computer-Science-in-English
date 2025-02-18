import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise4 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Exercise 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null); 

        JLabel counterLabel = new JLabel("Counter: 0");
        counterLabel.setBounds(100, 50, 100, 30); 

        JButton button = new JButton("Click");
        button.setBounds(100, 100, 100, 30);

        final int[] counter = {0};

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter[0]++;
                counterLabel.setText("Counter: " + counter[0]);
            }
        });

        frame.add(counterLabel);
        frame.add(button);
        frame.setVisible(true);

    }
}
