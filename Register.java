package JavaMiniProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Register {

    public static void main(String[] args) {
        new Register();
    }
    static Connection con;
    Register()
    {

        //font
        Font bold = new Font("Serif",1,35);
        Font plan = new Font("Serif",0,20);


        JFrame lf = new JFrame("Register");
        lf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        lf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lf.setLocationRelativeTo(null);
        //lf.setUndecorated(true);
        lf.setBackground(new Color(223,223,223));
        lf.setLayout(null);

        ImageIcon ic2 = new ImageIcon("E:\\short term corse on java\\Desktop_Application_In_Java\\src\\Final\\lpu.png");
        lf.setIconImage(ic2.getImage());

        ImageIcon bg = new ImageIcon("E:\\short term corse on java\\Desktop_Application_In_Java\\src\\Final\\register.png");
        JLabel l = new JLabel(bg);
        lf.setContentPane(l);

        JLabel header = new JLabel("Inventory Management System",JLabel.CENTER);
        header.setFont(bold);
        lf.add(header);
        header.setBounds(445,10,1080,50);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setOpaque(false);
        //p.setBackground(new Color(86, 108, 211));
        lf.add(p); p.setBounds(30,350,400,500);

        JLabel head = new JLabel("REGISTER",JLabel.CENTER);
        head.setFont(bold);
        p.add(head);

        head.setBounds(0,10,400,50);


        JTextField name = new JTextField("Name*");name.setFont(plan);
        JTextField uid = new JTextField("UID*"); uid.setFont(plan);
        JPasswordField pass = new JPasswordField("PASSWORD*"); pass.setFont(plan);pass.setEchoChar((char)0);
        JButton submit = new JButton("SUBMIT"); submit.setFont(plan); submit.setBackground(new Color(247,183,93));

        p.add(uid);p.add(pass);p.add(submit);p.add(name);
        name.setBounds(100,90,200,50);
        uid.setBounds(100,170,200,50);
        pass.setBounds(100,250,200,50);
        submit.setBounds(140,375,125,50);

        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imsdb", "root", "12102325");
        }
        catch(Exception ex) { System.out.println(ex); }

        class MyListener implements ActionListener, FocusListener
        {
            public void actionPerformed(ActionEvent ae)
            {
                String user = uid.getText();
                char[] pwd1 = pass.getPassword();
                String pwd2 = new String(pwd1);
                String na = name.getText();
                try{

                    PreparedStatement ps = con.prepareStatement("");


                    ps.setString(1, user);
                    ResultSet rs = ps.executeQuery();
                    String msg = null;

                    if(rs.next())
                    {
                        String pass = rs.getString("password");
                        if(pwd2.equals(pass))
                        {
                            lf.setVisible(false);
                            new Home_Page(Integer.parseInt(uid.getText()),rs.getString(3));
                        }
                        else
                        {
                            msg = "Incorrect Password";
                            JOptionPane.showMessageDialog(lf, msg);
                        }
                    }
                    else
                    {
                        msg = "User ID does not Exist";
                        JOptionPane.showMessageDialog(lf, msg);
                    }


                } catch(Exception exc) { System.out.println(exc);}
            }

            @Override
            public void focusGained(FocusEvent e) {
                if(e.getSource()==uid)
                {
                    uid.setText(null);
                }
                if(e.getSource()==pass)
                {
                    pass.setText(null);
                    pass.setEchoChar('*');
                }
                if(e.getSource()==name)
                {
                    name.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(e.getSource()==name)
                {
                    if(name.getText().equals(""))
                        name.setText("Name*");
                }
                if(e.getSource()==uid)
                {
                    if(uid.getText().equals(""))
                        uid.setText("UID*");
                }
                if(e.getSource()==pass)
                {
                    if(pass.getPassword().length==0)
                    {
                        pass.setEchoChar((char)0);
                        pass.setText("Password*");
                    }
                }
            }
        }
        MyListener ml = new MyListener();
        submit.addActionListener(ml);
        uid.addFocusListener(ml);
        pass.addFocusListener(ml);
        name.addFocusListener(ml);
        lf.setVisible(true);
    }
}
