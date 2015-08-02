import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.net.*;
import java.io.*;

public class Client extends JFrame implements ActionListener,Runnable
{
    JButton b1;
    JTextArea ta;
    JButton b2;
    JPanel p1;
    JTextField tf;
    List list;
    String name;
    int socketNumber =3000;
    OutputStream os;
    Socket cSocket;
    InputStream is;
    Client(String nm,Socket cs)
    {
       
       super("Welcome "+nm);
       name = nm;
       cSocket = cs;
       setLayout(new BorderLayout());
       p1 = new JPanel();
       b1 = new JButton("send");
       tf = new JTextField(50);
       p1.add(tf);
       p1.add(b1);
       add(p1,"South");
       ta = new JTextArea(10,20);
       add(ta,"Center");
       ta.setEditable(false);
       list = new List(10);
       add(list,"West");
       setSize(800,600);
       b1.addActionListener(this);
       Thread clientThread = new Thread(this);
       clientThread.start();
       
    }

    
    public void run() //listeinng the msgs from server
    {
        while(true)
        {
            try
            {
                System.out.println("client receiving 1");
                is = cSocket.getInputStream();
                System.out.println("client receiving 2");
                BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                System.out.println("client receiving 3");
                String msg = buffer.readLine();
                System.out.println("client receiving 4");
                System.out.println(msg);
                ta.append("\n"+msg);
            }
            catch (Exception e)
            {
                //e.printStackTrace();
            }
        }
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String text = tf.getText();
        String clientMsg = name+" : "+text+"\n";
        System.out.println(clientMsg);
       
        try
        {
            System.out.println("client sending 1");
            os = cSocket.getOutputStream();
            System.out.println("client sending 2");
            PrintWriter pw = new PrintWriter(os,true);
            pw.println(clientMsg);
            System.out.println("client sending 3");
            System.out.println(clientMsg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        tf.setText("");
    }
    
}