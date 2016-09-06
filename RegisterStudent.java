import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StatementImpl;
/*
 * When user click Registration button then this class is loaded.
 * This class create a form contain all the component to get information from user while 
 * registering ,eg: name,address,email etc. 
 */
public class RegisterStudent extends JPanel implements ActionListener{
	private JLabel studentInfo,lName,lAddress,lEmail,lNumber,lGender,lPassword,lConformPass,error,lId;
	private JTextField name,address,email,number;
	private JPasswordField password,conformPass;
	private JButton signup,back;
	private JRadioButton male,female;
	private ButtonGroup gender;

	RegisterStudent(){
		setLayout(null);
		setBounds(150, 50, 400, 400);
		setBackground(Color.lightGray);
		initcomponent();
	    setVisible(true);
		
	}
	
	private void initcomponent(){
		studentInfo = new JLabel("Student Registeration Form");
		studentInfo.setBounds(150,0,300,30);
		lName= new JLabel("your Name :");
		lName.setBounds(50,40,120,20);
		lAddress= new JLabel("your address :");
		lAddress.setBounds(50,80,120,20);
		lEmail= new JLabel("Email :");
		lEmail.setBounds(50,120,120,20);
		lNumber= new JLabel("Phone Number:");
		lNumber.setBounds(50,160,120,20);
		lGender=new JLabel("Gender :");
		lGender.setBounds(50,200,120,20);
		lPassword= new JLabel("new Password :");
		lPassword.setBounds(50,240,120,20);
		lConformPass= new JLabel("conform Password :");
		lConformPass.setBounds(50,280,120,20);
		name= new JTextField();
		address= new JTextField();
		email= new JTextField();
		number=new JTextField();
		password= new JPasswordField();
		conformPass= new JPasswordField();
		male= new JRadioButton("Male",true);
		male.setBounds(180,200,100,23);
		female=new JRadioButton("Female",false);
		female.setBounds(280,200,100,23);
		gender= new ButtonGroup();
		gender.add(male);
		gender.add(female);
		name.setBounds(180,40,100,23);
		address.setBounds(180,80,100,23);
		email.setBounds(180,120,100,23);
		number.setBounds(180,160,100,23);
		password.setBounds(180,240,100,23);
		conformPass.setBounds(180,280,100,23);
		signup= new JButton("Register");
		signup.setBounds(280,370,100,25);
		back= new JButton("Back");
		back.setBounds(100,370,100,25);
		error= new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(100,310,200,20);
		lId=new JLabel("");
		lId.setBounds(200,335,200,25);
		add(lId);
		add(studentInfo);
		add(lName);
		add(name);
		add(lAddress);
		add(address);
		add(lEmail);
		add(email);
		add(lNumber);
		add(number);
		add(lGender);
		add(male);
		add(female);
		add(lPassword);
		add(password);
		add(lConformPass);
		add(conformPass);
		add(error);
		add(signup);
		add(back);
		signup.addActionListener(this);
		back.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		/*
		 * when user clicks back button it will load MainFrame by making all the input method to
		 * the default .And is user clicks signup Button first it will connect to the datebase
		 * and the create new user and store data in database
		 */
			if(e.getSource()==back){			
				setVisible(false);	
				MainFrame.makeDefault();
				MainFrame.addPanel();
				try {
					finalize();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}else if(e.getSource()==signup){
				error.setForeground(Color.RED);
				error.setText("");
				/*
				 * this if else statement is for checking null textfield,and if 
				 * it encounter null field ,sequentially starting from begining textfield ,then
				 * it will show error message respective to their textfield. 
				 */
				if(name.getText().equals("")){
					error.setText("Name can not be empty!!");
					
				}else if(address.getText().equals("")){
					error.setText("Address can not be empty!!");
				}else if(email.getText().equals("")){
					error.setText("Email can not be empty!!");
				}else if(number.getText().equals("")){
					error.setText("Phone number can not be empty!!");
				}else if(number.getText().length()<10){
					error.setText("Phone number is invalid!!");	
				}else if(password.getText().equals("")){
					error.setText("Password can not be empty!!");
				}else if(conformPass.getText().equals("")){
					error.setText("Password can not be empty!!");
				}else if(password.getText().equals(conformPass.getText())){
					/*
					 * registering new user in the database by using prepared statement
					 * and all the input information given by the user. 
					 */
					DbConnection c=new DbConnection();
					Connection con = null;
					String query,id;
					Long num;
					String dSex = null;
					Statement st=null;
					ResultSet result;
					int count = 0,year = 0;
					try {
						c.getConnection();
						con=(Connection) c.getDbConnection();
						PreparedStatement pst= (PreparedStatement) con.prepareStatement("insert into students(name,address,number,email,gender,password) values(?,?,?,?,?,?)");
						num=Long.parseLong(number.getText());
						pst.setString(1,name.getText());
						pst.setString(2,address.getText());
						pst.setLong(3,num);
						pst.setString(4,email.getText());
						if(male.isSelected()){
							dSex="male";
						}else if(female.isSelected()){
							dSex="female";
						}
						pst.setString(5,dSex);
						pst.setString(6,password.getText());
						/*
						 * since we are generating a sequential unique student Id for login method.
						 * we first connect to the admin table from database to get year information
						 * cause we are registering user by means of the  year set by admin.This method is 
						 * useful and we can also determine the year registered by student by seeing their
						 * Id since the first two digit of Id is the last Two digit of the Year .And then
						 * we get count from totalstudent table from database,which will incremented by one 
						 * whenever there is new student registered.Because of this we can also determine 
						 * the total number of student registered in the particular year.Now we append 
						 * the count value with the last two digit of year and generate unique Id for new
						 * Registeration which is used for login method also.And thus registeration process
						 * completed.
						 */
						if(pst.executeUpdate()==1){
							query="select year from admin where name='bisho silwal'";
							st= (Statement) con.createStatement();
							result=st.executeQuery(query);
							while(result.next()){
								year=result.getInt("year");
							}
							query="select count from totalstudents where year="+year;
							result=st.executeQuery(query);
							while(result.next()){
								count=result.getInt("count");
							}
							count+=1;
							query="update totalstudents set count="+count+" where year="+year;
							st.executeUpdate(query);
							error.setForeground(Color.BLACK);
							error.setText("Registeration successful");							
							id=String.valueOf(year%100);
							id=id+String.valueOf(count);
							query="update students set id="+Integer.parseInt(id)+" where name='"+name.getText()+"' and email='"+email.getText()+"'";
							st.executeUpdate(query);
							lId.setText("your ID is : "+id);
							lId.setVisible(true);
							
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
							con.close();
							st.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
					
					
				}else{
					error.setText("Password doesnot match!!");
				}
		}
	}
	

}
