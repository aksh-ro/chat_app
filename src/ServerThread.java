import java.net.*;
import java.io.*;

public class ServerThread extends Thread
{
    OutputStream os;
    InputStream is;
    PrintWriter pw;
    BufferedReader br;
    //String name;
    Socket socket;
    ServerThread(Socket s)
    {
       //name = nm;
        socket = s;
        this.start();
       
    }
    public void run()
    {
        while(true)
        {
            InputStream is;
            try
            {
                System.out.println("Thread receiving 1");
                is = socket.getInputStream();
                System.out.println("Thread receiving 2");
                BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                String message = buffer.readLine();
                System.out.println("Thread receiving 3");
                System.out.println(message);
                System.out.println("reveive activating");
                Server.sendToAll(message);
                System.out.println("receive completed");
            }
            catch (Exception e)
            {
                //e.printStackTrace();
            }
        }
    }
    
}