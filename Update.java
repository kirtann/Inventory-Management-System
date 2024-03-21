import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


class Update {
    static Connection con;

    public static void main(String[] args) {
        new Update(12115299);
    }

    Update(int uid) {

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Inventory", "root", "abhi1210@");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        Font bold = new Font("Serif", 1, 35);
        Font plan = new Font("Serif", 0, 25);

        JFrame df = new JFrame("UPDATE ITEM");
        df.setSize(400, 400);
        df.getContentPane().setBackground(new Color(0, 153, 153));
        df.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        df.setLocationRelativeTo(null);
        df.setLayout(null);


        JLabel head = new JLabel("Update Item", JLabel.CENTER);
        head.setFont(bold);

        JTextField id = new JTextField("Item Code*");
        id.setFont(plan);


        JButton update = new JButton("UPDATE");
        update.setFont(plan);
        update.setBackground(new Color(247, 183, 93));

        df.add(head);
        df.add(id);
        df.add(update);
        head.setBounds(0, 0, 400, 50);
        id.setBounds(100, 100, 200, 40);

        update.setBounds(100, 300, 200, 40);

        String tablename = "UIT"+uid;


        class MyListner implements ActionListener{
            public void actionPerformed(ActionEvent e) {

                JLabel n;
                JLabel h;
                JLabel c;
                JLabel spr;
                JLabel cpr;
                JLabel m;
                JLabel o;
                JLabel l;

                JTextField name;
                JTextField hsn;
                JTextField cat;
                JTextField sp;
                JTextField cp;
                JTextField min;
                JTextField os;
                JTextField loc;
                JButton up;


                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Inventory", "root", "abhi1210@");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                Font bold = new Font("Serif", 1, 35);

                Font plan = new Font("Serif", 0, 25);

                JFrame af = new JFrame("UPDATE ITEM");
                af.setSize(1000, 500);
                af.getContentPane().setBackground(new Color(0, 153, 153));
                af.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                af.setLocationRelativeTo(null);
                af.setLayout(null);

                JLabel head = new JLabel("Update Item", JLabel.CENTER);
                head.setFont(bold);

                name = new JTextField();
                name.setFont(plan);
                name.setBackground(Color.WHITE);

                n = new JLabel("Name@");
                n.setFont(plan);
                n.setBackground(Color.WHITE);

                hsn = new JTextField();
                hsn.setFont(plan);
                hsn.setBackground(Color.WHITE);

                h = new JLabel("HSN@");
                h.setFont(plan);
                h.setBackground(Color.WHITE);

                cat = new JTextField("Category*");
                cat.setFont(plan);
                cat.setBackground(Color.WHITE);

                c = new JLabel("Category@");
                c.setFont(plan);
                c.setBackground(Color.WHITE);

                sp = new JTextField("Sale Price*");
                sp.setFont(plan);
                sp.setBackground(Color.WHITE);

                spr = new JLabel("Sale Price@");
                spr.setFont(plan);
                spr.setBackground(Color.WHITE);

                cp = new JTextField("Purchase Price*");
                cp.setFont(plan);
                cp.setBackground(Color.WHITE);

                cpr = new JLabel("Purchase Price@");
                cpr.setFont(plan);
                cpr.setBackground(Color.WHITE);

                os = new JTextField("Opening Quantity*");
                os.setFont(plan);
                os.setBackground(Color.WHITE);

                o = new JLabel("Opening Quantity@");
                o.setFont(plan);
                o.setBackground(Color.WHITE);

                min = new JTextField("Min Stock To Maintain*");
                min.setFont(plan);
                min.setBackground(Color.WHITE);

                m = new JLabel("Min Stock@");
                m.setFont(plan);
                m.setBackground(Color.WHITE);

                loc = new JTextField("Location*");
                loc.setFont(plan);
                loc.setBackground(Color.WHITE);


                l = new JLabel("Location@");
                l.setFont(plan);
                l.setBackground(Color.WHITE);

                up = new JButton("Update");
                up.setFont(plan);
                up.setBackground(new Color(247, 183, 93));

                af.add(head);
                af.add(name);
                af.add(hsn);
                af.add(cat);
                af.add(sp);
                af.add(cp);
                af.add(os);
                af.add(min);
                af.add(loc);
                af.add(up);

                af.add(n);
                af.add(h);
                af.add(c);
                af.add(spr);
                af.add(cpr);
                af.add(o);
                af.add(m);
                af.add(l);

                head.setBounds(0, 0, 1000, 50);
                name.setBounds(130, 100, 300, 30);
                n.setBounds(10, 100, 300, 30);
                hsn.setBounds(130, 150, 300, 30);
                h.setBounds(10, 150, 300, 30);
                cat.setBounds(130, 200, 300, 30);
                c.setBounds(10, 200, 300, 30);
                sp.setBounds(670, 100, 300, 30);
                spr.setBounds(490, 100, 300, 30);
                cp.setBounds(670, 150, 300, 30);
                cpr.setBounds(490, 150, 300, 30);
                os.setBounds(670, 200, 300, 30);
                o.setBounds(490, 200, 300, 30);
                min.setBounds(130, 300, 300, 30);
                m.setBounds(0, 300, 300, 30);
                loc.setBounds(130, 350, 300, 30);
                l.setBounds(0, 350, 300, 30);
                up.setBounds(680, 300, 200, 30);

                String id_ = id.getText();
                try {
                    PreparedStatement ps = con.prepareStatement("select * from " + tablename + " where ITEM_ID = ?");
                    ps.setString(1, id_);
                    ResultSet rs = ps.executeQuery();

                    String msg = null;
                    if (rs.next()) {
                        name.setText(rs.getString(2));
                        hsn.setText(rs.getString(3));
                        cat.setText(rs.getString(7));
                        sp.setText(rs.getString(4));
                        cp.setText(rs.getString(5));
                        os.setText(rs.getString(6));
                        min.setText(rs.getString(9));
                        loc.setText(rs.getString(8));
                    } else {
                        name.setText("Name");
                        hsn.setText("HSN");
                        sp.setText("Sale @");
                        cp.setText("Purchase @");
                        cat.setText("Category @");
                        os.setText("STOCK @");
                        min.setText("Min Quantity @");
                        loc.setText("Location @");
                        msg = "Item Does Not Exists";
                        JOptionPane.showMessageDialog(df, msg);
                    }
                } catch (Exception exc) {
                    System.out.println(exc);
                }
                af.setVisible(true);
            }
        }
        MyListner ml = new MyListner();
        update.addActionListener(ml);

        df.setVisible(true);
    }
}
