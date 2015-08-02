import java.net.*;
import java.io.*;
import java.util.*;

public class Server
{
    public static Vector clientVector = new Vector();
    static int socketNumber = 3000;
    public static void main(String args[])
    {
        try
        {
            ServerSocket sSocket = new ServerSocket(socketNumber);
            while(true)
            {
                System.out.println("waiting for client to connect...");
                Socket cSocket = sSocket.accept();
                System.out.println("client is\n"+cSocket);
                
                ServerThread st = new ServerThread(cSocket);
                
                clientVector.add(cSocket);
                //st.start();
                /*InputStream is = cSocket.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                System.out.println("here1");
                String msg = buffer.readLine();
                System.out.println("here2");
                
                if(msg.equals("~"))
                {
                    System.out.println("Client wants to connect. Press y or n" );
                    
                    if((msg2).equals("y"))
                    {
                        OutputStream os = cSocket.getOutputStream();
                        PrintWriter pw = new PrintWriter(os,true);
                        pw.println("allowed");
                        clientVector.add(cSocket);
                        os.flush();
                        pw.flush();
                        pw.close();
                    }
                    
                } 
                else
                {
                    Iterator iter = clientVector.iterator();
                    while(iter.hasNext())
                    {
                        System.out.println("chat group");
                        
                        Socket newSocket =(Socket)iter.next();
                        OutputStream os = newSocket.getOutputStream();
                        System.out.println(newSocket);
                        System.out.println(msg);
                        PrintWriter pw = new PrintWriter(os);
                        pw.println(msg);
                        //os.write((msg.trim()).getBytes());
                        System.out.println(newSocket);
                        System.out.println(msg);
                    }
                }*/
                
                
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
    }
    
    public String login()
    {
        System.out.println("client wants to connect...\nPress y or n");
        Scanner sc = new Scanner(System.in);
        String msg2 = sc.nextLine();
        return msg2;
    }
    
    public static void sendToAll(String msg)
    {
        System.out.println("receive activated");
        Iterator iter = clientVector.iterator();
                    while(iter.hasNext())
                    {
                        System.out.println("chat group");
                        try
                        {
                            Socket newSocket =(Socket)iter.next();
                            OutputStream os = newSocket.getOutputStream();
                            System.out.println(newSocket);
                            System.out.println(msg);
                            PrintWriter pw = new PrintWriter(os,true);
                            pw.println(msg);
                            //os.write((msg.trim()).getBytes());
                            System.out.println(newSocket);
                            System.out.println(msg);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
    }
}


