import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Exercise9 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exercise 9");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        String[] columnNames = {"ID", "Name", "Age"};
        Object[][] data = {
            {1, "Alicja", 25},
            {2, "Hania", 30},
            {3, "Marta", 35}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane);
        frame.setVisible(true);
    }
}