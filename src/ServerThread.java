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
        try
        {
            is = socket.getInputStream();
            BufferedReader buffer = new BufferedReader( new InputStreamReader(is));
            String msg =buffer.readLine();
            if((msg).equals("~"))
            {
                Server server = new Server();
                String prmsn = server.login();
                System.out.println("client logged in\n"+prmsn);
                if((prmsn).equals("y"))
                {
                    os = socket.getOutputStream();
                    PrintWriter pw = new PrintWriter(os,true);
                    pw.println("allowed");
                    
                    
                    this.start();
                    System.out.println("Thread started");
                }
                else
                {
                    System.out.println("Sorry Server dont allow u to login");
                }
            }
        }
        catch(Exception e)
        {
                
        }
       
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