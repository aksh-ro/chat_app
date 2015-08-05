import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;

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
                InputStream is;
                is = cSocket.getInputStream();
                BufferedReader buffer = new BufferedReader( new InputStreamReader(is));
                String msg =buffer.readLine();
                System.out.println(msg);
                System.out.println(msg.charAt(0));
                if(msg.charAt(0)=='~')
                {
                    System.out.println("here");
                    int index = msg.indexOf('^');
                    String uname = msg.substring(1, index);
                    String password = msg.substring(index+1);
                    System.out.println(uname+"\n"+password);
                    String querry = "select password from login where username = '"+uname+"'";
                    Conn c1 = new Conn();
                    ResultSet rs = c1.s.executeQuery(querry);
                    if(rs.next())
                    {
                        System.out.println(rs.getString("password"));
                        if((rs.getString("password")).equals(password))
                        {
                            System.out.println("user "+uname+" wants to connect");
                            Scanner sc = new Scanner(System.in);
                            String msg2 = sc.nextLine();
                            if((msg2).equals("y"))
                            {
                                OutputStream os = cSocket.getOutputStream();
                                PrintWriter pw = new PrintWriter(os,true);
                                pw.println("allowed");
                                ServerThread st = new ServerThread(cSocket);
                                clientVector.add(cSocket);
                            }
                            else
                            {
                                OutputStream os = cSocket.getOutputStream();
                                PrintWriter pw = new PrintWriter(os,true);
                                pw.println("deniedS");
                            }
                        }
                        else
                        {
                            OutputStream os = cSocket.getOutputStream();
                            PrintWriter pw = new PrintWriter(os,true);
                            pw.println("incorrect");
                        }
                    }
                } 
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
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


