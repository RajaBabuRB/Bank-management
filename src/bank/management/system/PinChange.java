
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener  {
    
    JLabel text,pintext,repintext;
    JPasswordField pin,repin;
    JButton change,back;
    String pinnumber;
    
    PinChange(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);
     ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(850, 820, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 820);
        add(image);
        
         text = new JLabel(" CHANGE YOUR PIN ");
     text.setBounds(205, 270, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
           pintext = new JLabel("New PIN :");
     pintext.setBounds(160, 310, 700, 35);
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(pintext);
        
         pin = new JPasswordField();
         pin.setFont(new Font("Raleway", Font.BOLD, 25));
         pin.setBounds(310,315,150,25);
        image.add(pin);
        
        
        
             repintext = new JLabel("RE-Enter New PIN :");
     repintext.setBounds(160, 350, 700, 35);
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(repintext);
        
         repin = new JPasswordField();
         repin.setFont(new Font("Raleway", Font.BOLD, 25));
         repin.setBounds(310,355,150,25);
        image.add(repin);
        
        change = new JButton(" CHNAGE ");
        change.setBounds(310,430,150,30);
        change.addActionListener(this);
        image.add(change);
        
         back = new JButton(" BACK ");
      back.setBounds(310,465,150,35);
     back.addActionListener(this);
        image.add(back);
        
        
        
        
        
        
        
        
        setSize(850,820);
       setUndecorated(true);
        setLocation(250,-60);
        setVisible(true);
    
    }
    public void actionPerformed(ActionEvent ae){
    if (ae.getSource() == change){
     try{        
            String npin = pin.getText();
            String rpin = repin.getText();
            
            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }
            
            
                if (npin.equals("")){
                    JOptionPane.showMessageDialog(null, " Please Enter New PIN");
                }
                if (rpin.equals("")){
                    JOptionPane.showMessageDialog(null, " Please Re-Enter new PIN");
                }
                
                Conn c1 = new Conn();
                String q1 = "update bank set pin = '"+rpin+"' where pin = '"+pinnumber+"' ";
                String q2 = "update login set pin = '"+rpin+"' where pin = '"+pinnumber+"' ";
                String q3 = "update signupthree set pin = '"+rpin+"' where pin = '"+pinnumber+"' ";

                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);
                c1.s.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);
            
          
        }catch(Exception e){
            System.out.println(e);
        }
    }else{
    setVisible(false);
    new Transactions(pinnumber).setVisible(true);
    }

    
    }
    
    
    public static void main(String args[]) {
    new PinChange(" ").setVisible(true);
    }
}
