import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/*
 * Execution of program start from this frame .This is only one Frame on which all panel
 * are depends.There are two Panel, admin and student.In admin panel, login method for Admin
 * is presented where User is "bisho silwal" and Password is "root".when Admin logged in
 * a new Panel frome Admin class is loaded and othere panel become invisible.
 * In student Panel ,there is a login method from which student will able to give exam and 
 * another method is registration of new Student.After student login a panel from AfterLogin
 * is loaded and in case of new registration panel from RegisterStudent class is loaded
 * 
 */
public class MainFrame extends JFrame {
	JLabel label1=new JLabel("ONLINE EXAMINATION SYSTEM");
	JLabel labelId,labelPass,studentLogin,lAdmin,lAdminName,lAdminPass;
	static JLabel lerror,aerror;
	static JTextField id,admin;
	static JPasswordField password,adminPass;
	JButton login,register,adminLogin;
	RegisterStudent reg;
	static JPanel loginPanel,adminPanel;
	Admin fullAdminPanel;
	static Container container=null;
	AfterLogin afterLogin=null;
	/*
	 * initialization for main Frame with two panels,adminpanel and studentLogin panel
	 * with required component.
	 */
	MainFrame(){
		super("ONLINE EXAMINATION SYSTEM");
		container=this.getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(700,600));
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		label1.setBounds(100, 0, 500, 50);
		label1.setFont(new java.awt.Font("Tahoma", 1, 15));
		label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		add(label1);
		adminPanel= new JPanel();
		adminPanel.setLayout(null);
		adminPanel.setSize(500, 120);
		adminPanel.setBounds(20, 460, 640,80);
		adminPanel.setBackground(Color.lightGray);
		lAdmin=new JLabel("Admin Login ");
		lAdminName=new JLabel("User:");
		lAdminPass=new JLabel("Password:");
		admin=new JTextField();
		adminPass= new JPasswordField();
		adminLogin= new JButton("Login");
		lAdmin.setBounds(300,10,200,15);
		lAdminName= new JLabel("UserName:");
		lAdminName.setBounds(100,30,100,20);
		admin.setBounds(180,30,100,23);
		lAdminPass.setBounds(300,30,100,20);
		adminPass.setBounds(380,30,100,20);
		adminLogin.setBounds(500,30,100,30);
		aerror= new JLabel("");
		aerror.setBounds(100,55,300,20);
		aerror.setForeground(Color.RED);
		adminLogin.addActionListener(new ActionListener(){
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				aerror.setVisible(false);
				DbConnection c=new DbConnection();
				Connection con = null;
				String query="select * from admin";
				String dAName = null,dAPass = null;
				ResultSet result;
				//Database connection to check admin requirement by using DbConnection class
				try {
					c.getConnection();
					con=(Connection) c.getDbConnection();
					Statement st=(Statement) con.createStatement();
					result=st.executeQuery(query);
					while(result.next()){
						dAName=result.getString("name");
						dAPass=result.getString("password");
					}
			
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					try {
						c.closeConnection();
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				/*
				 * when admin username and password become correct it will load Admin class,
				 * otherwise it will shows an error
				 * 
				 */
				if(admin.getText().equals(dAName)&&adminPass.getText().equals(dAPass)){
					loginPanel.setVisible(false);
					adminPanel.setVisible(false);	
					container.remove(loginPanel);	
					container.remove(adminPanel);
					fullAdminPanel= new Admin();
					fullAdminPanel.setVisible(true);
					container.add(fullAdminPanel);
					
				}else{
					aerror.setText("username or password didn't match!!!");
					aerror.setVisible(true);
					
					
				}
				
			}
			
		});
		adminPanel.add(lAdmin);
		adminPanel.add(lAdminName);
		adminPanel.add(admin);
		adminPanel.add(lAdminPass);
		adminPanel.add(adminPass);
		adminPanel.add(adminLogin);
		adminPanel.add(aerror);
		add(adminPanel);
		loginPanel= new JPanel();
		loginPanel.setSize(500, 500);
		loginPanel.setLayout(null);
		loginPanel.setBounds(150, 50, 400, 400);
		loginPanel.setBackground(Color.lightGray);
		initcomponent();
		setVisible(true);
		
	}
	/*
	 * initialization process for Studentlogin panel
	 * 
	 */
	private void initcomponent() {
		id= new JTextField(10);
		lerror=new JLabel("");
		password= new JPasswordField(10);
		labelId=new JLabel("User Id :");
		labelPass=new JLabel("Password :");
		studentLogin=new JLabel("Student login form");
		login= new JButton("Login");
		register= new JButton("New student register"); 
		studentLogin.setBounds(150,0,150,80);
		labelId.setBounds(50,80,50,50);
		id.setBounds(130, 95, 90, 20);
		password.setBounds(130, 165, 100, 20);
		lerror.setBounds(100,200,200,20);
		lerror.setForeground(Color.RED);
		labelPass.setBounds(50,150,100,50);
		login.setBounds(250, 230,100, 30);
		register.setBounds(100,300,200,30);
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				lerror.setText("");
				DbConnection c=new DbConnection();
				Connection con = null;
				String query;
				boolean studentFound=false;
				Statement st=null;
				ResultSet result=null;
				/*
				 * database connection for student requirement.
				 * If student Id and password is true it will load panel from AfterLogin
				 * class
				 */
				if(id.getText().equals("") || password.getText().equals("")){
					lerror.setText("Id or Password cannot be empty!!!");		
				}else{
					try{
						c.getConnection();
						con=(Connection) c.getDbConnection();
						st=(Statement) con.createStatement();
						query="select * from students"; 
						result=st.executeQuery(query);
						while(result.next()){
							if(result.getInt("id")==Integer.parseInt(id.getText())&&result.getString("password").equals(password.getText())){
								loginPanel.setVisible(false);
								adminPanel.setVisible(false);
								getContentPane().remove(loginPanel);
								getContentPane().remove(adminPanel);
								afterLogin= new AfterLogin(result.getInt("id"));
								afterLogin.setVisible(true);
								add(afterLogin);
								studentFound=true;
							}	
						}
						if(!result.next()&&!studentFound){
							lerror.setText("User ID or Password isnot correct!!");
						}
						
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (InstantiationException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		/*
		 * After register button click ,it will load Registration class to
		 * register new student
		 */
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
				loginPanel.setVisible(false);
				adminPanel.setVisible(false);
				getContentPane().remove(loginPanel);
				getContentPane().remove(adminPanel);
				reg= new RegisterStudent();
				reg.setVisible(true);
				add(reg);
			}
		});
		loginPanel.add(studentLogin);
		loginPanel.add(labelId);
		loginPanel.add(id);
		loginPanel.add(password);
		loginPanel.add(labelPass);
		loginPanel.add(login);
		loginPanel.add(register);
		loginPanel.add(lerror);
		//add(loginPanel);
		container.add(loginPanel);
	}
	/*
	 * this method is used to flush all the textfield and password field when 
	 * returning to MainFrame from another panel
	 */
	public static void makeDefault(){
		id.setText("");
		password.setText("");
		admin.setText("");
		adminPass.setText("");
		lerror.setText("");
		aerror.setText("");
		
	}
	/*
	 * this method is use to make visible adminPanel and LoginPanel when 
	 * returning to MainFrame from another panel or when 'back' button is clicked
	 * from subsequent panel. 
	 */
	public static void addPanel(){
		loginPanel.setVisible(true);
		adminPanel.setVisible(true);
		container.add(loginPanel);
		container.add(adminPanel);
		container.revalidate();
		container.repaint();
	}
	/*
	 *  main method to load the program.This is the only way to load the program,i.e
	 *  there is only one entry point to enter the program.
	 */
	public static void main(String[] args) {
		MainFrame frame=new MainFrame();
		frame.setVisible(true);
		
	}
}	
