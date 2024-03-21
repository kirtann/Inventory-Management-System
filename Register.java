import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;


class Register {

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
        Container c = lf.getContentPane();
        //lf.setUndecorated(true);
        lf.setBackground(new Color(255,255,153));
        lf.setLayout(null);

        JLabel header = new JLabel("INVENTORY MANAGEMENT SYSTEM",JLabel.CENTER);
        header.setFont(new Font("Serif",10,34));
        header.setForeground(new Color(128,0,21));
        lf.add(header);
        header.setBounds(270,10,1080,50);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setOpaque(false);
//        p.setBackground(new Color(255,102,25));
        lf.add(p);
        p.setBounds(300,160,600,500);

        JLabel head = new JLabel("REGISTER",JLabel.CENTER);
        head.setFont(new Font("Serif",10,34));
        head.setForeground(new Color(25,25,255));
        p.add(head);

        head.setBounds(300,10,400,50);


        JTextField name = new JTextField("NAME*");name.setFont(plan);
//        JTextField uid = new JTextField("UID*"); uid.setFont(plan);
        JPasswordField pass = new JPasswordField("PASSWORD*"); pass.setFont(plan);
        pass.setEchoChar((char)0);
        JButton submit = new JButton("SUBMIT"); submit.setFont(plan); submit.setBackground(new Color(247,183,93));

        p.add(pass);p.add(submit);p.add(name);
        name.setBounds(400,90,200,50);
//        uid.setBounds(400,170,200,50);
        pass.setBounds(400,250,200,50);
        submit.setBounds(440,375,125,50);

        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Inventory", "root", "abhi1210@");
        }
        catch(Exception ex) { System.out.println(ex); }

        class MyListener implements ActionListener, FocusListener
        {
            public void actionPerformed(ActionEvent ae)
            {
                int choice = JOptionPane.showConfirmDialog(lf,"Do you want to confirm your registration?","Confirmation",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION)
                {
//                    String user = uid.getText();
                    String pwd = new String(pass.getPassword());
                    String na = name.getText();
                    if(!(pwd.equals("") || pwd.equals("PASSWORD*")) &&  !(na.equals("") || na.equals("NAME*")))
                    {
                        try
                        {

                            PreparedStatement insert = con.prepareStatement("insert into user(name,password) values (?,?)");

                            insert.setString(1,na);
                            insert.setString(2,pwd);
                            int row1 = insert.executeUpdate();
//                            PreparedStatement st = con.prepareStatement("Select uid from user where name = '"+name.getText()+"'and password ='" +pwd+"';");
//                            ResultSet rs = st.executeQuery();

                            int id = 0;
//                            while(rs.next()){
//                                id = rs.getInt(1);
//                            }
                            PreparedStatement stat = con.prepareStatement("Select max(uid) from user");
                            ResultSet res = stat.executeQuery();
                            while(res.next()){
                                id = res.getInt(1);
                            }
                            PreparedStatement createtb = con.prepareStatement("CREATE TABLE IF NOT EXISTS uit" +id+
                                    " (ITEM_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                                    " ITEM_NAME VARCHAR(30), HSN VARCHAR(10), SALE_PRICE INT, " +
                                    " PURCHASE_PRICE INT, STOCK INT, CATEGORY VARCHAR(20), " +
                                    " LOCATION VARCHAR(10),MIN_STOCK INT) AUTO_INCREMENT = 100");
                            JLabel jl = new JLabel("Your Id is : " +id);
                            jl.setBounds(1000,250,300,40);
//                            jl.setText(jl.getText()+"Please remember your Id for Future Reference!");
                            jl.setFont(new Font("Arial",10,24));
                            c.add(jl);
                            jl.setForeground(Color.red);
                            if(row1>0)
                            {
                                System.out.print("User is Registered!!!");
                            }
                            createtb.execute();
                                JOptionPane.showMessageDialog(lf,"Registration Confirmed");
                                new Inventry_Login();
                                lf.dispose();
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(lf,"Please Enter All Details!!");
                    }
                }

            }

            @Override
            public void focusGained(FocusEvent e) {
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
                        name.setText("NAME*");
                }

                if(e.getSource()==pass)
                {
                    if(pass.getPassword().length==0)
                    {
                        pass.setEchoChar((char)0);
                        pass.setText("PASSWORD*");
                    }
                }
            }
        }
        MyListener ml = new MyListener();
        submit.addActionListener(ml);
        pass.addFocusListener(ml);
        name.addFocusListener(ml);
        lf.setVisible(true);
    }
}
