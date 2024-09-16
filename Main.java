import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JPanel;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args)
    {
        
        JFrame frame = new JFrame("Calendar");
        
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);
        
        JPanel mainPanel = new JPanel(new GridLayout(1,2));

        LocalDate date = LocalDate.now();
        Database database = new Database();

        mainPanel.add(new Calendar(date.getYear(), date.getMonthValue(), date, mainPanel, database));
        mainPanel.add(new Events(date, database, mainPanel));

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
}