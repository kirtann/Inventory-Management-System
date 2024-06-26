import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;


class Inventry_Login {
    static Connection con;
    public static void main(String[] args) {
        new Inventry_Login();
    }

        //font
        Inventry_Login() {
            Font bold = new Font("Serif", 1, 35);
            Font plan = new Font("Serif", 0, 20);


            JFrame lf = new JFrame("SIGNIN");
            lf.setExtendedState(JFrame.MAXIMIZED_BOTH);
            lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            lf.setLocationRelativeTo(null);
            lf.setLayout(null);

            JLabel header = new JLabel("INVENTORY MANAGEMENT SYSTEM", JLabel.CENTER);
            header.setFont(new Font("Serif", 10, 34));
            header.setForeground(new Color(128, 0, 21));
            lf.add(header);
            header.setBounds(270, 10, 1080, 50);

            JPanel p = new JPanel();
            p.setLayout(null);
            p.setBackground(new Color(255, 255, 153));
            lf.add(p);
            p.setBounds(570, 150, 400, 500);

            JLabel head = new JLabel("LOGIN", JLabel.CENTER);
            head.setFont(bold);
            p.add(head);

            head.setBounds(0, 10, 400, 50);


            JTextField uid = new JTextField("UID*");
            uid.setFont(plan);
            JPasswordField pass = new JPasswordField("PASSWORD*");
            pass.setFont(plan);
            pass.setEchoChar((char) 0);
            JButton submit = new JButton("SIGNIN");
            submit.setFont(plan);
            submit.setBackground(new Color(247, 183, 93));
            JButton register = new JButton("SIGNUP");
            register.setFont(plan);
            register.setBackground(new Color(247, 183, 93));

            p.add(uid);
            p.add(pass);
            p.add(submit);
            p.add(register);
            uid.setBounds(100, 150, 200, 50);
            pass.setBounds(100, 250, 200, 50);
            submit.setBounds(50, 375, 125, 40);
            register.setBounds(220, 375, 125, 40);

            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "abhi1210@");
            } catch (Exception ex) {
                System.out.println(ex);
            }

            class MyListener implements ActionListener, FocusListener {
                public void actionPerformed(ActionEvent ae) {
                    if (ae.getSource() == submit) {
                        String user = uid.getText();
                        String pwd = new String(pass.getPassword());
                        try {

                            PreparedStatement ps = con.prepareStatement("select * from user where uid = ?");


                            ps.setString(1, user);
                            ResultSet rs = ps.executeQuery();
                            String msg = null;

                            if (rs.next()) {
                                String pass = rs.getString(3);
                                if (pwd.equals(pass)) {
                                    lf.dispose();
                                    new Home_Page(Integer.parseInt(uid.getText()), rs.getString(2));
                                } else {
                                    msg = "Incorrect Password";
                                    JOptionPane.showMessageDialog(lf, msg);
                                }
                            } else {
                                msg = "User ID does not Exist";
                                JOptionPane.showMessageDialog(lf, msg);
                            }


                        } catch (Exception exc) {
                            System.out.println(exc);
                        }
                    } else if (ae.getSource() == register) {
                        new Register();
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    if (e.getSource() == uid) {
                        uid.setText(null);
                    }
                    if (e.getSource() == pass) {
                        pass.setEchoChar('*');
                        pass.setText(null);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (e.getSource() == uid) {
                        if (uid.getText().equals(""))
                            uid.setText("UID*");
                    }
                    if (e.getSource() == pass) {
                        if (pass.getPassword().length == 0) {
                            pass.setEchoChar((char) 0);
                            pass.setText("Password*");
                        }
                    }
                }
            }
            MyListener ml = new MyListener();
            submit.addActionListener(ml);
            uid.addFocusListener(ml);
            pass.addFocusListener(ml);
            register.addActionListener(ml);
            lf.setVisible(true);
        }
    }

