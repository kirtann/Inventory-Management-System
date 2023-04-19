package JavaMiniProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;



public class Home_Page {
    static Connection con;
    static int columncount;
    static  DefaultTableModel tableModel;
    public static void main(String[] args) throws SQLException {
        new Home_Page(0,"");
    }
    Home_Page(int uid,String n) throws SQLException {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop", "root", "database");
        }
        catch(Exception ex) { System.out.println(ex); }


        Font bold = new Font("Serif",1,35);
        Font itelic = new Font("Serif",2,30);
        Font plan = new Font("Serif",0,25);

        JFrame hf = new JFrame("Dashboard");
        hf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        hf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hf.setLocationRelativeTo(null);
        hf.getContentPane().setBackground(new Color(247,183,93));

        ImageIcon ic2 = new ImageIcon("E:\\short term corse on java\\Desktop_Application_In_Java\\src\\Final\\lpu.png");
        hf.setIconImage(ic2.getImage());
        hf.setLayout(null);

        JLabel header = new JLabel("Inventory Management System",JLabel.CENTER);
        header.setFont(bold);
        hf.add(header);
        header.setBounds(445,10,1080,50);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(33,41,52));
        hf.add(p1);
        p1.setBounds(0,0,300,1080);

        JLabel hv = new JLabel("Welcome "+n,JLabel.CENTER); hv.setForeground(Color.WHITE);
        JLabel sv = new JLabel("Stock Value: 0.00Rs"); sv.setForeground(Color.WHITE);
        JLabel lv = new JLabel("Low Stocks: 0"); lv.setForeground(Color.WHITE);
        JLabel tv = new JLabel("Total Stock: 0"); tv.setForeground(Color.WHITE);
        hv.setFont(itelic);
        sv.setFont(plan);
        lv.setFont(plan);
        tv.setFont(plan);

        p1.add(sv);p1.add(lv);p1.add(hv);p1.add(tv);

        hv.setBounds(0,30,300,60);
        sv.setBounds(10,200,300,60);
        lv.setBounds(10,300,300,60);
        tv.setBounds(10,400,300,60);


        JButton add = new JButton("ADD ITEM");add.setFont(plan);add.setForeground(new Color(247,183,93));
        add.setBackground(new Color(33,41,52));
        hf.add(add);
        add.setBounds(540,200,200,50);

        JButton del = new JButton("DELETE ITEM");del.setFont(plan);del.setForeground(new Color(247,183,93));
        del.setBackground(new Color(33,41,52));
        hf.add(del);
        del.setBounds(840,200,200,50);

        JButton ser = new JButton("SEARCH ITEM");ser.setFont(plan);ser.setForeground(new Color(247,183,93));
        ser.setBackground(new Color(33,41,52));
        hf.add(ser);
        ser.setBounds(1140,200,200,50);

        JButton up = new JButton("UPDATE ITEM");up.setFont(plan);up.setForeground(new Color(247,183,93));
        up.setBackground(new Color(33,41,52));
        hf.add(up);
        up.setBounds(1440,200,200,50);

        JButton rf = new JButton("REFRESH");rf.setFont(plan);rf.setForeground(new Color(247,183,93));
        rf.setBackground(new Color(33,41,52));
        p1.add(rf);
        rf.setBounds(0,900,300,50);

        JPanel tp = new JPanel();
        tp.setLayout(new BorderLayout());
        JTable table;

        int stovkvalue = 0;
        int lowstock=0;
        int totalstock =0;

        try
        {
            String query = "SELECT * FROM item";
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery(query);
            tableModel = new DefaultTableModel();
            ResultSetMetaData metaData = rs.getMetaData();
            columncount = metaData.getColumnCount();
            for(int i=1;i<=columncount;i++)
            {
                tableModel.addColumn(metaData.getColumnName(i));
            }
            while (rs.next())
            {
                Object[] row = new Object[columncount];
                for (int i = 1; i <= columncount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(row);
            }
            table = new JTable(tableModel);
            table.setFont(plan);
            JScrollPane scrollPane = new JScrollPane(table);
            tp.add(scrollPane);
            rs.close();
            smt.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        hf.add(tp);
        tp.setBounds(500,300,1200,600);
        class MyListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                try {

                    PreparedStatement ps = con.prepareStatement("select * from item");
                    ResultSet rs = ps.executeQuery();
                    String msg = null;
                } catch (Exception exc) {
                    System.out.println(exc);
                }
                if(ae.getSource()==add)
                {
                    new Add();
                }
                if(ae.getSource()==del)
                {
                    new Delete();
                }
                if(ae.getSource()==up)
                {

                }
                if(ae.getSource()==ser)
                {

                }
                if(ae.getSource()==rf)
                {
                    try{
                        String query = "SELECT * FROM item";
                        Statement smt = con.createStatement();
                        ResultSet rs = smt.executeQuery(query);
                        tableModel.setRowCount(0);

                        while (rs.next())
                        {
                            Object[] row = new Object[columncount];
                            for (int i = 1; i <= columncount; i++) {
                                row[i - 1] = rs.getObject(i);
                            }
                            tableModel.addRow(row);
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                }
            }

        }
        MyListener ml = new MyListener();
        rf.addActionListener(ml);
        add.addActionListener(ml);
        del.addActionListener(ml);
        ser.addActionListener(ml);
        up.addActionListener(ml);


        hf.setVisible(true);
    }

}
