import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Events extends JPanel
{
    private static final long serialVersionUID = 1L;

    
    public Events(LocalDate date, Database database, JPanel mainPanel)
    {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ArrayList <Event> events = database.getEvents(dateFormatter.format(date));


        // horizontal gap, vertical gap
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.white);
        setBorder(BorderFactory.createEmptyBorder(40,20,30,20));

        int rows= 4;
        if (events.size()>4) rows= events.size();



        JPanel list = new JPanel(new GridLayout(29,1,10,10));
        list.setBackground(Color.white);

        // creates a class that acts as a scroll bar in the vertical and horizontal direction
        JScrollPane sp = new JScrollPane(list);

        //For loops creates the 4 boxes for event titles
        for (int i=0; i<events.size();i++)
        {
            final int j = i;
            JPanel event = new JPanel(new GridLayout(2,1));
            event.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10,10,10,10),
                BorderFactory.createMatteBorder(0,10,0,0, Color.decode("#00d1e8"))));

            event.setBackground(Color.decode("#f0f0f0"));
            event.setCursor(new Cursor(Cursor.HAND_CURSOR));
            event.addMouseListener(new MouseListener()
            {
                public void mouseReleased(MouseEvent e) 
                {
                    
                }
                public void mousePressed(MouseEvent e)
                {

                }
                public void mouseExited(MouseEvent e) 
                {
                    
                }
                public void mouseEntered(MouseEvent e) 
                {
                    
                }
                public void mouseClicked(MouseEvent e) 
                {
                    new EventEditor(events.get(j), database, mainPanel);
                }
            }
            );

            //returns element at a given index

            JLabel title = new JLabel(events.get(i).getTitle());
            title.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
            title.setFont(new Font("Helvetica", Font.PLAIN,18));
            title.setForeground(Color.black);
            event.add(title);

            JLabel time = new JLabel(events.get(i).getDateTimeToString());
            time.setBorder(BorderFactory.createEmptyBorder(5,15,4,15));
            time.setFont(new Font("Helvetica", Font.PLAIN,14));
            time.setForeground(Color.DARK_GRAY);
            event.add(time);

            list.add(event);
        }

        
        add(sp, BorderLayout.CENTER);

// when clicking on event button, allows creation of new event
        JButton newEvent = new JButton("New");
        newEvent.setFont(new Font("Helvetica", Font.PLAIN, 20));
        newEvent.setBackground(Color.decode("#00d1e8"));
        newEvent.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        newEvent.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new EventEditor(new Event(date), database, mainPanel);
            }
        }
        );
        add(newEvent,BorderLayout.SOUTH);

    }
}
