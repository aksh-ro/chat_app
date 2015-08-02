import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener
{
    JButton b1;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JTextField t1;
    JPasswordField p1;
    JPasswordField p2;
    
    Register()
    {
        super("Register with Chat App");
        setLayout(null);
        getContentPane().setBackground(Color.BLUE);
        setSize(400,300);
        l1 = new JLabel("Enter Username");
        add(l1);
        l1.setBounds(50,80,130,20);
        l2 = new JLabel("Enter a Password");
        add(l2);
        l2.setBounds(50,110,130,20);;
        l3 = new JLabel("Confirm Password");
        add(l3);
        l3.setBounds(50,140,130,20);
        t1 = new JTextField(100);
        add(t1);
        t1.setBounds(200,80,130,20);
        p1 = new JPasswordField(100);
        add(p1);
        p1.setBounds(200,110,130,20);
        p2 = new JPasswordField(100);
        add(p2);
        p2.setBounds(200,140,130,20);
        b1 = new JButton("Register Me");
        b1.addActionListener(this);
        add(b1);
        b1.setBounds(130,200,130,30);
    }
    public void actionPerformed (ActionEvent ae)
    {
        
        if(p1.getText().equals(p2.getText()))
        {
            String querry = "insert into login values ('"+t1.getText()+"','"+p1.getText()+"')";
            System.out.println(querry);
            try
            {
                Conn c1 = new Conn();
                c1.s.executeUpdate(querry);
                JOptionPane.showMessageDialog(null," You have been registered");
                Login l = new Login();
                l.setVisible(true);
                this.dispose();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
            JOptionPane.showMessageDialog(null," Password don't match");
    }
}
