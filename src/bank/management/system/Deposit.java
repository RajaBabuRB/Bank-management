
package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;


public class Deposit extends JFrame implements ActionListener {
  
    String pinnumber;
    JTextField amount;
    JButton deposit, back;
    Deposit(String pinnumber){
        
    this.pinnumber = pinnumber;
      setLayout(null);
    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(850, 820, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 820);
        add(image);
        
        
        JLabel  text = new JLabel("Enter the amount you want to deposit");
     text.setBounds(175, 270, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
         amount = new JTextField();
         amount.setFont(new Font("Raleway", Font.BOLD, 22));
         amount.setBounds(175, 320, 250, 25);
        image.add(amount);
    
        deposit = new   JButton("Deposit");
      deposit.setBounds(335,440,150,25);
     deposit.addActionListener(this);
      image.add(deposit);
      
        back = new   JButton("Back");
      back.setBounds(335,473,150,25);
     back.addActionListener(this);
      image.add(back);
      
        
        
    setSize(850,820);
        setUndecorated(true);
        setLocation(250,-60);
        setVisible(true);
    }
    
    
    
    
     public void actionPerformed(ActionEvent ae) {
     if (ae.getSource() == deposit){
     String number = amount.getText();
     
     Date date = new Date();
     if(number.equals("")){
     
     JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
     }else {
         try{
     Conn conn = new Conn();
     String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Deposit', '"+number+"')";
     conn.s.executeUpdate(query);
      JOptionPane.showMessageDialog(null, "Rs " +number+ " Deposit Successfully");
         setVisible(false);
         new Transactions(pinnumber).setVisible(true);
         }
         
         catch(Exception e){
         System.out.println(e);
         }
     }
     
     
     
     }else if(ae.getSource()== back){
     setVisible(false);
     new Transactions(pinnumber).setVisible(true);
     
     }
     
     
     }
     
     
     
     
    public static void main(String args[]) {
    new Deposit("");
    }
}