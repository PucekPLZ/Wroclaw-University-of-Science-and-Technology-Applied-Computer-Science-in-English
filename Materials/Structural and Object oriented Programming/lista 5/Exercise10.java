import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise10 {
    private static boolean running = false;
    private static int seconds = 0;
    private static Timer timer;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Exercise 10");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        JLabel timerLabel = new JLabel("Time: 0s");
        timerLabel.setBounds(100, 50, 100, 30);

        JButton startStopButton = new JButton("Start");
        startStopButton.setBounds(100, 100, 100, 30);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                timerLabel.setText("Time: " + seconds + "s");
            }
        });

        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    timer.stop();
                    startStopButton.setText("Start");
                } else {
                    timer.start();
                    startStopButton.setText("Stop");
                }
                running = !running;
            }
        });

        frame.add(timerLabel);
        frame.add(startStopButton);
        frame.setVisible(true);
    }
}
