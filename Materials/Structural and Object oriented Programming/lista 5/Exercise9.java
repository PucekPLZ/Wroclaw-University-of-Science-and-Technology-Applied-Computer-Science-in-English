import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise9 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Exercise 9");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null); 

        JTextField taskField = new JTextField();
        taskField.setBounds(50, 20, 200, 30);

        JButton addButton = new JButton("Add Task");
        addButton.setBounds(260, 20, 100, 30);

        DefaultListModel<String> taskListModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(50, 60, 300, 150);

        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(150, 220, 100, 30);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                if (!task.isEmpty()) {
                    taskListModel.addElement(task);
                    taskField.setText(""); 
                } else {
                    JOptionPane.showMessageDialog(frame, "Task cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "No task selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(taskField);
        frame.add(addButton);
        frame.add(scrollPane);
        frame.add(removeButton);
        frame.setVisible(true);
    }
}
