import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;



public class Home_Page {
    static Connection con;
    static int columncount;
    static  DefaultTableModel tableModel;
    public static void main(String[] args) throws SQLException {
        new Home_Page(2,"Rathore");
    }
    Home_Page(int uid,String n) throws SQLException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory","root","abhi1210@");
        }
        catch(Exception ex) { System.out.println(ex); }


        Font bold = new Font("Times New Roman",1,35);
        Font itelic = new Font("Serif",2,30);
        Font plan = new Font("Serif",0,25);

        JFrame hf = new JFrame("Dashboard");
        hf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        hf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hf.setLocationRelativeTo(null);
        Container c = hf.getContentPane();
        c.setLayout(null);
        hf.setBackground(new Color(0,153,153));

        JLabel header = new JLabel("INVENTORY MANAGEMENT SYSTEM",JLabel.CENTER);
        header.setFont(bold);
//        hf.add(header);
        c.add(header);
        header.setBounds(310,10,1080,50);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(33,41,52));
        c.add(p1);
        p1.setBounds(0,0,270,1080);

//        JLabel hv = new JLabel("Welcome "+n,JLabel.CENTER); hv.setForeground(Color.WHITE);
        JLabel hv = new JLabel("Welcome "+n,JLabel.CENTER); hv.setForeground(Color.WHITE);
//        JLabel sv = new JLabel("Stock Value: 0.00Rs"); sv.setForeground(Color.WHITE);
        JLabel sv = new JLabel("Created By :"); sv.setForeground(Color.WHITE);
        JLabel lv = new JLabel("Registration No"); lv.setForeground(Color.WHITE);
        JLabel tv = new JLabel("All Rights Reserved."); tv.setForeground(Color.WHITE);
        hv.setFont(itelic);
        sv.setFont(plan);
        lv.setFont(plan);
        tv.setFont(plan);

        p1.add(sv);p1.add(lv);p1.add(hv);p1.add(tv);

        hv.setBounds(0,30,300,60);
        sv.setBounds(10,200,300,60);
        JLabel sv1 = new JLabel("Abhishek Rathore");sv1.setForeground(Color.white);
        sv1.setFont(plan);
        p1.add(sv1);
        sv1.setBounds(10,240,300,60);
        lv.setBounds(10,300,300,60);
        JLabel lv1 = new JLabel("12101413");lv1.setForeground(Color.white);
        lv1.setFont(plan);
        p1.add(lv1);
        lv1.setBounds(10,340,300,60);
        sv1.setBounds(10,240,300,60);
        tv.setBounds(10,450,300,60);

//
        JButton add = new JButton("ADD ITEM");add.setFont(plan);add.setForeground(new Color(247,183,93));
        add.setBackground(new Color(33,41,52));
        c.add(add);
        add.setBounds(320,140,200,50);
//
        JButton del = new JButton("DELETE ITEM");del.setFont(plan);del.setForeground(new Color(247,183,93));
        del.setBackground(new Color(33,41,52));
        c.add(del);
        del.setBounds(590,140,200,50);
//
        JButton ser = new JButton("SEARCH ITEM");ser.setFont(plan);ser.setForeground(new Color(247,183,93));
        ser.setBackground(new Color(33,41,52));
        c.add(ser);
        ser.setBounds(860,140,200,50);
//
        JButton up = new JButton("UPDATE ITEM");up.setFont(plan);up.setForeground(new Color(247,183,93));
        up.setBackground(new Color(33,41,52));
        c.add(up);
        up.setBounds(1130,140,200,50);
//
        JButton rf = new JButton("REFRESH");rf.setFont(plan);rf.setForeground(new Color(247,183,93));
        rf.setBackground(new Color(33,41,52));
        p1.add(rf);
        rf.setBounds(0,580,250,50);

        JPanel tp = new JPanel();
        tp.setLayout(new BorderLayout());
        JTable table;
//
//
        String tablename = "UIT"+uid;
        try
        {
            String query = "SELECT * FROM "+tablename;
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
            table.setFont(new Font("Serif",Font.BOLD,18));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setFont(new Font("Arial", Font.PLAIN, 16));
            table.setDefaultRenderer(Object.class, centerRenderer);

            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            headerRenderer.setFont(new Font("Arial", Font.BOLD, 18));
            TableColumnModel columnModel = table.getColumnModel();
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                columnModel.getColumn(i).setHeaderRenderer(headerRenderer);
            }

            JScrollPane scrollPane = new JScrollPane(table);
            tp.add(scrollPane);
            rs.close();
            smt.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        c.add(tp);
        tp.setBounds(300,250,1050,400);
        class MyListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {

                if(ae.getSource()==add)
                {
                    new Add(uid);
                }
                if(ae.getSource()==del)
                {
                    new Delete(uid);
                }
                if(ae.getSource()==up)
                {
                    new Update(uid);
                }
                if(ae.getSource()==ser)
                {
                    new Search(uid);
                }
                if(ae.getSource()==rf)
                {
                    try{
                        String query = "SELECT * FROM "+tablename;
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
                    try
                    {
                        PreparedStatement ps1 = con.prepareStatement("select PURCHASE_PRICE from "+tablename);
                        ResultSet rs1 = ps1.executeQuery();

                        int stock = 0;
                        while(rs1.next())
                        {
                            stock += Integer.parseInt(rs1.getString(1));
                        }
//                        sv.setText("Stock Value: "+stock+"Rs");

                        PreparedStatement ps2 = con.prepareStatement("Select STOCK, MIN_STOCK from "+tablename);
                        ResultSet rs2 = ps2.executeQuery();

                        int minstock = 0;
                        int totals =0;
                        while(rs2.next())
                        {
                            if(rs2.getInt(1)<rs2.getInt(2))
                                minstock++;
                            totals += rs2.getInt(1);
                        }
//                        lv.setText("Low Stocks: "+minstock);
//                        tv.setText("Total Stock: "+totals);



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
