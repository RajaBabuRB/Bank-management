package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String pinnumber;

    public BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;

        // Load and scale ATM background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(850, 820, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 820);
        add(image);

        // Back button
        back = new JButton("BACK");
        back.setBounds(310, 465, 150, 35);
        back.addActionListener(this);
        image.add(back);

        // Database connection and balance calculation
        Conn c1 = new Conn();
        int balance = 0;

        try {
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
            while (rs.next()) {
                String type = rs.getString("type");
                int amount = Integer.parseInt(rs.getString("amount"));
                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching balance: " + e);
        }

        // Display balance
        JLabel text = new JLabel("Your Current Account Balance is Rs " + balance);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 12));
        text.setBounds(170, 280, 400, 30);
        image.add(text);

        // Frame settings
        setSize(850, 820);
        setUndecorated(true);
        setLocation(250, -60);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("1234"); // Replace with a valid PIN for testing
    }
}