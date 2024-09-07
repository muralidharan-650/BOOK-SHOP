package com.javacrud;


//module-info.java
//module your.module.name {
//}
import java.awt.EventQueue;
import java.sql.*;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
		
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","muralidharan");
        }
        catch (ClassNotFoundException ex) 
        {
          ex.printStackTrace();
        }
        catch (SQLException ex) 
        {
               ex.printStackTrace();
        }
    }
	
	public void table_load()
	{
	    try 
	    {
	    pst = con.prepareStatement("select * from book");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	} 
	    catch (SQLException e) 
	     {
	        e.printStackTrace();
	  } 
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 801, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(318, 11, 173, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 59, 358, 197);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BOOK NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 21, 107, 17);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("EDITION");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 75, 107, 17);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("PRICE");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 140, 107, 17);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(114, 21, 187, 27);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(114, 75, 187, 27);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(114, 137, 187, 27);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				
				  try {
				        pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
				        pst.setString(1, bname);
				        pst.setString(2, edition);
				        pst.setString(3, price);
				        pst.executeUpdate();
				        JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
				        table_load();
				                       
				        txtbname.setText("");
				        txtedition.setText("");
				        txtprice.setText("");
				        txtbname.requestFocus();
				       }
				    catch (SQLException e1) 
				        {            
				       e1.printStackTrace();
				    }
				
			}
		});
		btnNewButton.setBounds(10, 267, 98, 51);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(141, 267, 98, 51);
		frame.getContentPane().add(btnExit);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.setBounds(270, 267, 98, 51);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(378, 59, 398, 259);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 327, 358, 51);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setBounds(10, 21, 63, 17);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
                     
                     String id = txtbid.getText();
                         pst = con.prepareStatement("select name,edition,price from book where id = ?");
                         pst.setString(1, id);
                         ResultSet rs = pst.executeQuery();
                     if(rs.next()==true)
                     {
                       
                         String name = rs.getString(1);
                         String edition = rs.getString(2);
                         String price = rs.getString(3);
                         
                         txtbname.setText(name);
                         txtedition.setText(edition);
                         txtprice.setText(price);
 
                     }   
                     else
                     {
                         txtbname.setText("");
                         txtedition.setText("");
                         txtprice.setText("");
                          
                     }
                 } 
             
              catch (SQLException ex) {
                    
                 }
			}
		});
		txtbid.setBounds(83, 21, 178, 20);
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnNewButton_1_1_1 = new JButton("Update");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String bname,edition,price,bid;
	                
	                
	                bname = txtbname.getText();
	                edition = txtedition.getText();
	                price = txtprice.getText();
	                bid  = txtbid.getText();
	                
	                 try {
	                        pst = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");
	                        pst.setString(1, bname);
	                        pst.setString(2, edition);
	                        pst.setString(3, price);
	                        pst.setString(4, bid);
	                        pst.executeUpdate();
	                        JOptionPane.showMessageDialog(null, "Record Update!!!!!");
	                        table_load();
	                       
	                        txtbname.setText("");
	                        txtedition.setText("");
	                        txtprice.setText("");
	                        txtbname.requestFocus();
	                    }
	                    catch (SQLException e1) {
	                        
	                        e1.printStackTrace();
	                    }
				
			}
		});
		btnNewButton_1_1_1.setBounds(434, 327, 98, 51);
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_2 = new JButton("Delete");
		btnNewButton_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 String bid;
		           bid  = txtbid.getText();
		           
		            try {
		                   pst = con.prepareStatement("delete from book where id =?");
		           
		                   pst.setString(1, bid);
		                   pst.executeUpdate();
		                   JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		                   table_load();
		                  
		                   txtbname.setText("");
		                   txtedition.setText("");
		                   txtprice.setText("");
		                   txtbname.requestFocus();
		               }
		               catch (SQLException e1) {
		                   
		                   e1.printStackTrace();
		               }
			}
		});
		btnNewButton_1_1_2.setBounds(629, 327, 98, 51);
		frame.getContentPane().add(btnNewButton_1_1_2);
	}
}
