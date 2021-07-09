import java.awt.EventQueue;
import java.sql.*;
import java.sql.ResultSet; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class employeecurd {

	
	
	
     PreparedStatement preStatement;
	private JFrame frame;
	private JTextField txtname;
	private JTextField txtcontact;
	private JTextField txtdep;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeecurd window = new employeecurd();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void connect(){

	    try{

	          Class.forName("com.mysql.jdbc.Driver");

	          con =DriverManager.getConnection("jdbc:mysql://localhost:3306/empcurd","root","Rutika27#");

	         

	        
	    }catch(Exception e){

	          System.out.print(e.getMessage());

	    }

	}
	
	Connection con;
    Statement stmt;
    ResultSet rs;
    private JTable table;
	
	public void table_load()
	{
		try
		{
		preStatement=con.prepareStatement("select * from record");
		rs=preStatement.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e){

		e.printStackTrace();
		}
	}
	/**
	 * Create the application.
	 */
	public employeecurd() {
		initialize();
		connect();
		table_load();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 647, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Record");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(187, 11, 138, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Employee Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(29, 46, 260, 211);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Employee name");
		lblNewLabel_1.setBounds(23, 42, 101, 29);
		panel.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.setBounds(154, 46, 96, 20);
		panel.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contact Number");
		lblNewLabel_2.setBounds(22, 93, 114, 29);
		panel.add(lblNewLabel_2);
		
		txtcontact = new JTextField();
		txtcontact.setBounds(154, 93, 96, 20);
		panel.add(txtcontact);
		txtcontact.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Department");
		lblNewLabel_3.setBounds(23, 145, 101, 22);
		panel.add(lblNewLabel_3);
		
		txtdep = new JTextField();
		txtdep.setBounds(154, 146, 96, 20);
		panel.add(txtdep);
		txtdep.setColumns(10);
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String ename,econtact,edepartment;
				ename=txtname.getText();
				econtact=txtcontact.getText();
				edepartment=txtdep.getText();
				
				try
				{
					  preStatement = con.prepareStatement("insert into record(empname,contact,empdep) values(?,?,?)");
					  preStatement.setString(1,ename);
					  preStatement.setString(2,econtact);
					  preStatement.setString(3,edepartment);
					  preStatement.executeUpdate();
					  JOptionPane.showMessageDialog(null, "Record Added!");
					   table_load();
					  txtname.setText("");
					  txtcontact.setText("");
					  txtdep.setText("");
					  txtname.requestFocus();
					  
				}catch(SQLException e1){
						e1.printStackTrace();
				}
				
				
			}
		});
		save.setBounds(34, 268, 74, 32);
		frame.getContentPane().add(save);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try{

                    con.close();

                    System.exit(0);

              }catch(Exception ex){

                     System.out.println(ex.getMessage());

              }
			
			}
		});
		exit.setBounds(118, 268, 74, 32);
		frame.getContentPane().add(exit);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				txtname.setText("");
				txtcontact.setText("");
				txtdep.setText("");
			
			}
		});
		clear.setBounds(202, 268, 74, 32);
		frame.getContentPane().add(clear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(29, 311, 249, 76);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("Employee ID");
		lblNewLabel_3_1.setBounds(10, 11, 88, 23);
		panel_1.add(lblNewLabel_3_1);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				
			
			
		}
		});
		txtid.setColumns(10);
		txtid.setBounds(95, 12, 121, 20);
		panel_1.add(txtid);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
             String ename,econtact,edepartment,bid;
				
				
				ename = txtname.getText();
				econtact = txtcontact.getText();
				edepartment = txtdep.getText();
				bid  = txtid.getText();
				
				 try {
					 preStatement = con.prepareStatement("update record set empname= ?,contact=?,empdep=? where empid =?");
					 preStatement.setString(1, ename);
					 preStatement.setString(2, econtact);
					 preStatement.setString(3, edepartment);
					 preStatement.setString(4, bid);
					 preStatement.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
			            table_load();
			           
			            txtname.setText("");
			            txtcontact.setText("");
			            txtdep.setText("");
			            txtname.requestFocus();
					}
 
		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
	
			
			
			}
		});
		update.setBounds(343, 318, 74, 32);
		frame.getContentPane().add(update);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 String bid;
					bid  = txtid.getText();
					
					 try {
							preStatement= con.prepareStatement("delete from record where empid =?");
					
							preStatement.setString(1, bid);
							preStatement.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
				            table_load();
				           
				            txtname.setText("");
				            txtcontact.setText("");
				            txtdep.setText("");
				            txtname.requestFocus();
						}
		 
			            catch (SQLException e1) {
							
							e1.printStackTrace();
						}
			}
		});
		delete.setBounds(469, 318, 74, 32);
		frame.getContentPane().add(delete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(302, 46, 308, 261);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
