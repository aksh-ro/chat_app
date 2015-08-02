import java.sql.*;
public class Conn
{
    Connection c;
    Statement s;
    Conn()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql:///chat_app","root","akyrocker");
            s=c.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}