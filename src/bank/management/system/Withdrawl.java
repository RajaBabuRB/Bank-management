package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    String pinnumber;
    JTextField amount;
    JButton withdrawl, back;

    Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(850, 820, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 820);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setBounds(175, 270, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(175, 320, 250, 25);
        image.add(amount);

        withdrawl = new JButton("Withdraw");
        withdrawl.setBounds(335, 440, 150, 25);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        back = new JButton("Back");
        back.setBounds(335, 473, 150, 25);
        back.addActionListener(this);
        image.add(back);

        setSize(850, 820);
        setUndecorated(true);
        setLocation(250, -60);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdrawl) {
            String number = amount.getText().trim();
            Date date = new Date();

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
            } else {
                try {
                    int withdrawAmount = Integer.parseInt(number);
                    Conn conn = new Conn();

                    // Step 1: Calculate current balance
                    int balance = 0;
                    ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
                    while (rs.next()) {
                        String type = rs.getString("type");
                        int amt = Integer.parseInt(rs.getString("amount"));
                        if (type.equalsIgnoreCase("Deposit")) {
                            balance += amt;
                        } else {
                            balance -= amt;
                        }
                    }

                    // Step 2: Check if balance is sufficient
                    if (withdrawAmount > balance) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    } else {
                        // Step 3: Proceed with withdrawal
                        String query = "INSERT INTO bank VALUES('" + pinnumber + "', '" + date + "', 'Withdrawl', '" + withdrawAmount + "')";
                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Rs " + withdrawAmount + " withdrawn successfully");
                        setVisible(false);
                        new Transactions(pinnumber).setVisible(true);
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount");
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    JOptionPane.showMessageDialog(null, "Transaction failed. Please try again.");
                }
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Withdrawl("1234"); // Replace with a valid PIN for testing
    }
}