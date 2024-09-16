import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Database 
{
    private String url = "jdbc:mysql://localhost/Calendar";
    private String user = "root";
    private String pass = "";
    // private String user = "user";
    // private String pass = "#1#2#3%1%2%3";
    private Statement statement;

    public Database()
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Event> getEvents(String date)
    {
        ArrayList<Event> events = new ArrayList<>();
        String select = "SELECT * FROM `calendar` WHERE `Date` = '"+date+"';";
        try 
        {
            ResultSet rs = statement.executeQuery(select);
            while (rs.next())
            {
                Event e = new Event();
                e.setID(rs.getInt("ID"));
                e.setTitle(rs.getString("Title"));
                e.setDescription(rs.getString("Description"));
                e.setDateTimeFromString(rs.getString("Date")+" | "+rs.getString("Time"));
                events.add(e);

            }
            
        }
        catch(SQLException exception)
            {
                exception.printStackTrace();

            }
            return events;

    }
        
            public boolean hasEvent(String date)
        {
            boolean hasEvent = false;
            String select = "SELECT * From `calendar` WHERE `Date` = '"+ date+ "';";
            try
            {
                ResultSet rs = statement.executeQuery(select);
                hasEvent = rs.next();

            }
            catch (SQLException exception)
            {
                exception.printStackTrace();

            }
            return hasEvent;
        }

        public void createEvent(Event e)
        {
            String insert = "INSERT INTO `calendar` (`Title`, `Description`, `Date`, `Time`)" + "VALUES ('"+e.getTitle()+"', '"+e.getDescription()+"', '"+e.getDateToString() + "', '" + e.getTimeToString()+"');";
            try
            {
                statement.execute(insert);
            }
            catch (SQLException exception)
            {
                exception.printStackTrace();
            }

        }
        public void updateEvent(Event e)
        {
            String update = "UPDATE `calendar` SET `Title` = '"+e.getTitle()+"', `Description` = '"+e.getDescription()+"', `Date` = '"+e.getDateToString()+"', `Time` = '"+e.getTimeToString()+"'  WHERE `ID` = "+e.getID()+" ;";

            try
            {
                statement.execute(update);

            }
            catch (SQLException exception)
            {
                exception.printStackTrace();
            }

        }
        public void deleteEvent(int ID)
        {
            String delete = "DELETE FROM `calendar` WHERE `ID` = "+ID+" ;";
            try
            {
                statement.execute(delete);

            } 
            catch (SQLException exception)
            {
                exception.printStackTrace();

            }

    }
         
}

