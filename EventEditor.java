import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EventEditor
{
    public EventEditor(Event e, Database database, JPanel parent)
    {
        int year = e.getDate().getYear();
        int month = e.getDate().getMonthValue();

        JFrame frame = new JFrame("Calendar");
        frame.setSize(700,350);
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(Color.white);

        JPanel mainPanel = new JPanel(new BorderLayout(20,20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40,20,20,20));
        mainPanel.setBackground(Color.white);

        JPanel center = new JPanel(new GridLayout(3,2,20,20));
        center.setBackground(Color.white);

        JLabel l1 = new JLabel("Title:");
        l1.setFont(new Font("Helvetica", Font.PLAIN,20));
        l1.setHorizontalAlignment(JLabel.CENTER);
        center.add(l1);

        JTextField title = new JTextField();
        title.setFont(new Font("Helvetica", Font.PLAIN, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        center.add(title);

        JLabel l2 = new JLabel("Time:");
        l2.setFont(new Font("Helvetica", Font.PLAIN,20));
        l2.setHorizontalAlignment(JLabel.CENTER);
        center.add(l2);
        
        JTextField time = new JTextField();
        time.setFont(new Font("Helvetica", Font.PLAIN, 20));
        time.setHorizontalAlignment(JLabel.CENTER);
        center.add(time);

        JLabel l3 = new JLabel("Description:");
        l3.setFont(new Font("Helvetica", Font.PLAIN,20));
        l3.setHorizontalAlignment(JLabel.CENTER);
        center.add(l3);

        JTextField description = new JTextField();
        description.setFont(new Font("Helvetica", Font.PLAIN, 20));
        description.setHorizontalAlignment(JLabel.CENTER);
        center.add(description);

        mainPanel.add(center, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel(new GridLayout(1,2,20,20));
        bottom.setBackground(null);

        

        JButton delete = new JButton("Delete");
        delete.setFont(new Font("Helvetica", Font.PLAIN, 20));
        delete.setBackground(Color.decode("#00d1e8"));
        delete.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        bottom.add(delete,BorderLayout.SOUTH);

        JButton save = new JButton("Save");
        save.setFont(new Font("Helvetica", Font.PLAIN, 20));
        save.setBackground(Color.decode("#00d1e8"));
        save.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        bottom.add(save,BorderLayout.SOUTH);
        time.setText(e.getTimeToString());

        //used in both update and create

        if (e.getTitle()!=null)
        {
            //updating event
            title.setText(e.getTitle());
            description.setText(e.getDescription());

            save.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev)
                {
                    if(title.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Title cannot be empty");
                        return;
                    }
                    
                
                    // This section is for checking if the user has entered all necessary information and meets the required format for the calendar to work

                    e.setTitle(title.getText());
                    e.setDescription(description.getText());

                    try
                    {
                        e.setTime(time.getText());
                    }
                    catch (Exception exception)
                    {
                        JOptionPane.showMessageDialog(null, "Check time format HH:mm");
                        return;
                    }
                    database.updateEvent(e);
                    // refreshing main view (calendar & Events)
                    parent.removeAll();
                    parent.add(new Calendar(year, month, e.getDate(), parent, database));
                    parent.add(new Events(e.getDate(), database, parent));
                    parent.revalidate();
                    frame.dispose();
                }
            });
            
            delete.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    database.deleteEvent(e.getID());
                    
                    parent.removeAll();
                    parent.add(new Calendar(year, month, e.getDate(), parent, database));
                    parent.add(new Events(e.getDate(), database, parent));
                    parent.revalidate();
                    frame.dispose();
                }
            });
        }
        else
        {
            //new Event
            delete.setVisible(false);
            save.addActionListener(new ActionListener() 
            {

                public void actionPerformed(ActionEvent event)
                {
                    if(title.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Title cannot be empty");
                        return;
                    }
                    // This section is for checking if the user has entered all necessary information and meets the required format for the calendar to work
                    e.setTitle(title.getText());
                    e.setDescription(description.getText());
                    try
                    {
                        e.setTime(time.getText());
                    }
                    catch (Exception exception)
                    {
                        JOptionPane.showMessageDialog(null, "Check time format HH:mm");
                        return;
                    }
                    database.createEvent(e);
                    // refreshing main view (calendar & Events)
                    parent.removeAll();
                    parent.add(new Calendar(year, month, e.getDate(), parent, database));
                    parent.add(new Events(e.getDate(), database, parent));
                    parent.revalidate();
                    frame.dispose();
                }
                
            });
        }

        mainPanel.add(bottom, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);

        frame.setVisible(true);
    }






}