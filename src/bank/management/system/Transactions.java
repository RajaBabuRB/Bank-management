
package bank.management.system;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Transactions extends JFrame implements ActionListener {
    
    JButton deposit,withdrawl,ministatement,fastcash,balanceenquiry,exit,pinchange;
    JLabel text;
    
    String pinnumber;
    
    Transactions(String pinnumber) {
    
        this.pinnumber = pinnumber;
      setLayout(null);
        
    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(850, 820, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 820);
        add(image);
    
     text = new JLabel("Please Select Your Transaction");
     text.setBounds(205, 270, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
       deposit = new   JButton("Deposit");
      deposit.setBounds(150,380,150,25);
      deposit.addActionListener(this);
      image.add(deposit);
      
           
         withdrawl = new   JButton("Withdrawl");
      withdrawl.setBounds(335,380,150,25);
      withdrawl.addActionListener(this);
      image.add(withdrawl);
      
       fastcash = new   JButton("Fast Cash");
      fastcash.setBounds(150,410,150,25);
      fastcash.addActionListener(this);
      image.add(fastcash);
      
        
       ministatement = new   JButton("Mini Statement");
      ministatement.setBounds(335,410,150,25);
      ministatement.addActionListener(this);
      image.add(ministatement);
      
      
       pinchange = new   JButton("Pin Change");
      pinchange.setBounds(150,440,150,25);
      pinchange.addActionListener(this);
      image.add(pinchange);
      
       balanceenquiry = new   JButton("Balance Enquiry");
      balanceenquiry.setBounds(335,440,150,25);
      balanceenquiry.addActionListener(this);
      image.add(balanceenquiry);
      
       exit = new   JButton("Exit");
      exit.setBounds(335,470,150,25);
      exit.addActionListener(this);
      image.add(exit);
      
      
    setSize(850,820);
    setLocation(250,-60);
     setUndecorated(true);
    setVisible(true);
        
    
    }
 public void actionPerformed(ActionEvent ae) {
 
 if(ae.getSource() == exit){
 System.exit(0);
 } else if(ae.getSource()== deposit){
 setVisible(false);
 new Deposit(pinnumber).setVisible(true);
 }else if(ae.getSource()== withdrawl){
 setVisible(false);
 new Withdrawl(pinnumber).setVisible(true);
 
 }
 else if(ae.getSource() == fastcash){
 setVisible(false);
 new FastCash(pinnumber).setVisible(true);
 
 }
 else if(ae.getSource() == pinchange){
 setVisible(false);
 new PinChange(pinnumber).setVisible(true);
 
 }else if(ae.getSource() == balanceenquiry){
 setVisible(false);
 new BalanceEnquiry(pinnumber).setVisible(true);
 
 }else if(ae.getSource() == ministatement){
// setVisible(false);
 new MiniStatement(pinnumber).setVisible(true);
 
 }
 
 } 

    public static void main(String args[]) {
       new Transactions("");
    }
}