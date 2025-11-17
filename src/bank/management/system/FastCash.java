package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton deposit, withdrawl, ministatement, fastcash, balanceenquiry, exit, pinchange;
    JLabel text;
    String pinnumber;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(850, 820, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 820);
        add(image);

        text = new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(205, 270, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        deposit = new JButton("RS 100");
        deposit.setBounds(150, 380, 150, 25);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("RS 500");
        withdrawl.setBounds(335, 380, 150, 25);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastcash = new JButton("RS 1000");
        fastcash.setBounds(150, 410, 150, 25);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("RS 2000");
        ministatement.setBounds(335, 410, 150, 25);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("RS 5000");
        pinchange.setBounds(150, 440, 150, 25);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry = new JButton("RS 10000");
        balanceenquiry.setBounds(335, 440, 150, 25);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit = new JButton("BACK");
        exit.setBounds(335, 470, 150, 25);
        exit.addActionListener(this);
        image.add(exit);

        setSize(850, 820);
        setLocation(250, -60);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton) ae.getSource()).getText().substring(3).trim();
            Conn c1 = new Conn();
            try {
                ResultSet rs = c1.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
                int balance = 0;
                while (rs.next()) {
                    String type = rs.getString("type");
                    int amt = Integer.parseInt(rs.getString("amount"));
                    if (type.equalsIgnoreCase("Deposit")) {
                        balance += amt;
                    } else {
                        balance -= amt;
                    }
                }

                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                Date date = new Date();
                String query = "INSERT INTO bank VALUES('" + pinnumber + "', '" + date + "', 'Withdraw', '" + amount + "')";
                c1.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public static void main(String[] args) {
        new FastCash("1234"); // Replace with a valid PIN
    }
}