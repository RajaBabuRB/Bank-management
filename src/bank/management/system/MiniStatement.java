package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {
        setTitle("Mini Statement");
        setLayout(null);

        JLabel mini = new JLabel();
        mini.setBounds(20, 140, 400, 200);
        add(mini);

        JLabel bank = new JLabel("Indian Bank");
        bank.setBounds(150, 20, 200, 20);
        bank.setFont(new Font("System", Font.BOLD, 16));
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 50, 350, 20); // Increased width
        add(card);

        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 300, 20);
        add(balance);

        // Fetch card number
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM login WHERE pin = '" + pinnumber + "'");
            if (rs.next()) {
                String cardNum = rs.getString("cardnumber");
                if (cardNum != null && cardNum.length() >= 16) {
                    card.setText("Card Number: " + cardNum.substring(0, 4) + "XXXXXXXX" + cardNum.substring(12));
                } else {
                    card.setText("Card Number: Invalid");
                }
            } else {
                card.setText("Card Number: Not Found");
            }
        } catch (Exception e) {
            System.out.println("Card fetch error: " + e);
        }

        // Fetch mini statement and balance
        try {
            Conn c = new Conn();
            int bal = 0;
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
            StringBuilder statement = new StringBuilder("<html>");
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                statement.append(date).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                         .append(type).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                         .append(amount).append("<br><br>");

                if (type.equalsIgnoreCase("Deposit")) {
                    bal += Integer.parseInt(amount);
                } else {
                    bal -= Integer.parseInt(amount);
                }
            }
            statement.append("</html>");
            mini.setText(statement.toString());
            balance.setText("Your total Balance is Rs " + bal);
        } catch (Exception e) {
            System.out.println("Statement fetch error: " + e);
        }

        getContentPane().setBackground(Color.WHITE);
        setSize(400, 600);
        setLocation(20, 20);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("1234"); // Replace with a valid PIN
    }
}