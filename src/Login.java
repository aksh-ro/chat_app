import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.net.*;
import java.io.*;

public class Login extends JFrame implements ActionListener
{
    JButton b1;
    JButton b2;
    JTextField t1;
    JPasswordField p1;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    static Login l;
    int socketNumber = 3000;
    OutputStream os;
    Login()
    {
        super("ChatApp");
        setLayout(new BorderLayout());
        
        setSize(800,600);
        ImageIcon img = new ImageIcon("E:\\app.jpg");
        JLabel background = new JLabel(img);
        add(background);
        background.setLayout(null);
        l1 = new JLabel("Username");
        background.add(l1);
        
        l1.setBounds(500,60 , 100, 30);
        l2 = new JLabel("Password");
        background.add(l2);
        l2.setBounds(500,120,100,30);
        t1 = new JTextField(100);
        background.add(t1);
        t1.setBounds(600,60,100,30);
        p1 = new JPasswordField(100);
        background.add(p1);
        p1.setBounds(600,120,100,30);
        b1 = new JButton("Login");
        background.add(b1);
        
        b1.setBounds(550,200,70,20);
        l3 = new JLabel("Don't have an account");
        background.add(l3);
        l3.setBounds(200,250,200,30);

        b2 = new JButton("Register Now");
        background.add(b2);
        b2.setBounds(350,250,140,25);
        b1.addActionListener(this);
        b2.addActionListener(this);
       
    }
    
    public static void main(String s[])
    {
        l = new Login();
        l.setVisible(true);
        l.setSize(800,600);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String msg = ae.getActionCommand();
        if(msg.equals("Login"))
        {
            String querry = "select password from login where username = '"+t1.getText()+"'";
            
            Conn c1 = new Conn();
            try
            {
                ResultSet rs = c1.s.executeQuery(querry);
                if(rs.next())
                {
                    System.out.println(rs.getString("password"));
                    if((rs.getString("password")).equals(p1.getText()))
                    {
                        JOptionPane.showMessageDialog(null,"user valid");
                        Socket clientSocket = new Socket("",socketNumber);
                        os = clientSocket.getOutputStream();
                        PrintWriter pw = new PrintWriter(os,true);
                        pw.println("~");
                        
                        InputStream is = clientSocket.getInputStream();
                        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                        System.out.println("wating");
                        String msg1 = buffer.readLine();
                        System.out.println("wating2");
                        pw.flush();
                        os.flush();
                        
                        if((msg1).equals("allowed"))
                        {
                            l.dispose();
                            new Client(t1.getText(),clientSocket).setVisible(true);
                            System.out.println("done");
                            
                        }
                        else
                        {
                            System.out.println("dint work");
                        }
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"user invalid");
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"user invalid");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println(e);
            }
        }
        if(msg.equals("Register Now"))
        {
            new Register().setVisible(true);
            l.dispose();
        }
    }
    
}
