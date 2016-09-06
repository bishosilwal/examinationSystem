import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/*
 * this class is loaded after admin sucessfully logged in.In this class there is
 * five different panel for different propose.Firstly after logged in insideAdmin Panel
 * is loaded,which contain two Button for Student panel and Question panel and other 
 * methods for setting year.Since student registeration is depends on year set by admin.
 * 
 *  
 */
public class Admin extends JPanel {
	private JButton updateYear,student,question,sInfo,deleteStudent,back,editQuestion,addQuestion,deleteQuestion,searchQuestion,setTimeout;  
	private static JPanel insideAdmin=null,studentPanel=null,questionPanel=null,studentInfoPanel=null,questionHandler=null;
	private JLabel lUpdateYear,error;
	private JTextField year,searchStudent,tDeleteStudent;
	static DbConnection c=new DbConnection();
	static Connection con = null;
	static String query;
	static Statement st=null;
	static ResultSet result;
	Admin(){
		insideAdmin=new JPanel();
		setLayout(null);
		setBounds(150, 50, 400, 400);
		setBackground(Color.lightGray);
		initcomponent();
		insideAdmin.setVisible(true);
	    setVisible(false);
	    add(insideAdmin);
	}
	//component initialization process for insideAdmin Panel
	private void initcomponent(){
		
		insideAdmin.setLayout(null);
		insideAdmin.setBounds(0, 0, 400, 400);
		insideAdmin.setBackground(Color.lightGray);
		back= new JButton("Back");
		student=new JButton("Students Info");
		question=new JButton("Question Info");
		lUpdateYear=new JLabel("set new Year:");
		year=new JTextField();
		error=new JLabel("");
		updateYear=new JButton("Set Year");
		student.setBounds(100,100,200,30);
		question.setBounds(100,200,200,30);
		lUpdateYear.setBounds(100,300,180,30);
		year.setBounds(200,305,50,23);
		updateYear.setBounds(280,300,100,30);
		back.setBounds(20,350,80,30);
		
		error.setForeground(Color.RED);
		error.setBounds(125,350,400,30);
		insideAdmin.add(student);
		insideAdmin.add(question);
		insideAdmin.add(lUpdateYear);
		insideAdmin.add(year);
		insideAdmin.add(updateYear);
		insideAdmin.add(back);
		insideAdmin.setVisible(true);
		student.addActionListener(new ButtonHandler());
		question.addActionListener(new ButtonHandler());
		//this back button is for returning to a Main Frame 
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				insideAdmin.setVisible(false);
				setVisible(false);
				MainFrame.makeDefault();
				MainFrame.addPanel();
				try {
					finalize();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				remove(insideAdmin);
			}
		});
		//to inistialize year in the year field of admin table of database
		updateYear.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			try {
				c.getConnection();
				con=(Connection) c.getDbConnection();
				Statement st=(Statement) con.createStatement();
				query="update admin set year="+Integer.parseInt(year.getText());
				if(st.executeUpdate(query)==1){
					error.setText("year is successfully updated");
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
		});
	
}
	/*
	 * while jumping from one panel to another we call this method  by giving
	 * panel name as an argument to show .eg:when we click studentInfo button ,
	 * first it will invisible InsideAdmin panel then it will call to this 
	 * method to load the new panel ie studentPanel.
	 */
	public void addPanel(JPanel p){
		add(p);
		p.setVisible(true);
	}
	/*
	 * It is very important to remove Panel when coming back to one step,because if 
	 * we dont then the older panel overlap the new panel resulting unexpecting outcome.
	 */
	public void removePanel(){
		removeStudentPanel();
		removeQuestionPanel();
	}
	public void removeQuestionPanel(){
		questionPanel.setVisible(false);
		remove(questionPanel);
	}
	public void removeStudentPanel(){
		studentPanel.setVisible(false);
		remove(studentPanel);
	}
	/*
	 * ButtonHandler class handles the click generating from two button ,studentInfo
	 * and QuestionInfo of a InsideAdmin Panel.If click is done to a studentInfo button
	 * then it will load studentPanel and if it is done to a QuestionInfo button it will
	 * load Question panel.
	 */
	class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==student){
				insideAdmin.setVisible(false);
				studentPanel=new JPanel();
				studentPanel.setLayout(null);
				studentPanel.setBackground(Color.lightGray);
				studentPanel.setBounds(0,0,400,400);
				sInfo=new JButton("Search Student");
				sInfo.setBounds(100,50,200,30);
				deleteStudent=new JButton("Delete Student");
				deleteStudent.setBounds(100,200,200,30);
				back= new JButton("Back");
				back.setBounds(20,350,80,30);
				
				error.setText("");
				studentPanel.add(sInfo);
				studentPanel.add(deleteStudent);
				studentPanel.add(back);
				studentPanel.add(error);	
				sInfo.addActionListener(new StudentHandler());
				deleteStudent.addActionListener(new StudentHandler());
				back.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						error.setText("");
						removeStudentPanel();
						insideAdmin.setVisible(true);					
						
					}
				});
					
				addPanel(studentPanel);

			}else if(e.getSource()==question){
				insideAdmin.setVisible(false);
				questionPanel= new JPanel();
				questionPanel.setLayout(null);
				questionPanel.setBackground(Color.lightGray);
				questionPanel.setBounds(0,0,400,400);
				editQuestion= new JButton("Edit Question");
				addQuestion=new JButton("Add new Question");
				deleteQuestion=new JButton("Delete Question");
				searchQuestion=new JButton("Search Question");
				back=new JButton("Back");
				error.setText("");
				searchQuestion.setBounds(100,50,200,30);
				editQuestion.setBounds(100,100,200,30);
				addQuestion.setBounds(100,150,200,30);
				deleteQuestion.setBounds(100,200,200,30);
				back.setBounds(20,350,80,30);
				searchQuestion.addActionListener(new QuestionHandler());
				editQuestion.addActionListener(new QuestionHandler());
				addQuestion.addActionListener(new QuestionHandler());
				deleteQuestion.addActionListener(new QuestionHandler());
			
				back.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						error.setText("");
						questionPanel.setVisible(false);
						remove(questionPanel);
						
						addPanel(insideAdmin);
					}
				});
				questionPanel.add(searchQuestion);
				questionPanel.add(editQuestion);
				questionPanel.add(addQuestion);
				questionPanel.add(deleteQuestion);
				questionPanel.add(back);
				questionPanel.add(error);
				addPanel(questionPanel);
			}
		}	
	}
	/*
	 * studentHandler class handles the click event generated either in search student or Delete Student 
	 * Button.After click made it will show a textfield and a button respectively. Then it will
	 * add a action listener class SearchStudentHandler  to the button  .
	 */
class StudentHandler implements ActionListener{
		private  JButton search;
		public void actionPerformed(ActionEvent e){
			String source=((AbstractButton) e.getSource()).getText();
			removeStudentPanel();
			switch(source){
			case "Search Student":
				searchStudent=new  JTextField();
				search= new JButton("Search Student");
				searchStudent.setBounds(50,120,100,30);
				search.setBounds(200,120,150,30);
				search.addActionListener(new SearchStudentHandler());
				studentPanel.add(searchStudent);
				break;
			case "Delete Student":
				tDeleteStudent=new  JTextField();
				search= new JButton("Delete Student");
				tDeleteStudent.setBounds(50,270,100,30);
				search.setBounds(200,270,150,30);
				search.addActionListener(new SearchStudentHandler());
				studentPanel.add(tDeleteStudent);
				break;
			}
			studentPanel.add(search);
			studentPanel.setVisible(true);
			addPanel(studentPanel);
		}
}

	/*
	 * SearchStudentHandler handles either searchStudent Button event or DeleteStudent Button
	 * event.In both cases it try to connect to the database and then handles the event.If 
	 * search student is found then new Panel resultPanel is displayed with the information of student
	 *  otherwise it will shows the error message.
	 */
	public  class SearchStudentHandler implements ActionListener{
		private JPanel resultPanel=null;
		private JLabel studentInfo,lName,name,lAddress,address,lEmail,email,lNumber,number,lGender,gender,lresult,lResult;
		JButton back;
		boolean student;
		public void actionPerformed(ActionEvent e){
			String source=((AbstractButton)e.getSource()).getText();
				try {
					error.setText("");
					student=false;
					c.getConnection();
					con=(Connection) c.getDbConnection();
					st= (Statement) con.createStatement();
					if(source.equals("Search Student")){
						query="select * from students where name='"+searchStudent.getText()+"'";
					}else if(source.equals("Delete Student")){
						query="select * from students where name='"+tDeleteStudent.getText()+"'";
					}
					result=st.executeQuery(query);
					while(result.next()){
					if(result.getString("name").equals(searchStudent.getText())){
						if(source.equals("Search Student")){
							removeStudentPanel();
							addInfo();
							name.setText(result.getString("name"));
							address.setText(result.getString("address"));
							email.setText(result.getString("email"));
							number.setText(result.getString("number"));
							gender.setText(result.getString("gender"));
							if(result.getString("result")==null){
								lresult.setText("He/She didnt took an exam yet!!");
							}else{
								lresult.setText(result.getString("result")+", He/She gives "+result.getString("totalRightAns")+" correct Answer");
							}
							resultPanel.setVisible(true);
							addPanel(resultPanel);
							student=true;
							}else if(source.equals("Delete Student")){
								query="delete from students where name='"+tDeleteStudent.getText()+"'";
								if(st.executeUpdate(query)==1){
									error.setText(tDeleteStudent.getText()+" is successfully deleted.");
								}
						}
						
					}
						
				}
				if(!result.next()&&student==false){
					error.setText("student "+searchStudent.getText()+" is not registered yet!!");
				}else{
					error.setText("");
				}
				
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
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
					
			
		}
		/*
		 * this method is used to initialize the resultpanel after the search student name is 
		 * found in the database.
		 */
		private void addInfo(){
			resultPanel= new JPanel();
			resultPanel.setLayout(null);
			resultPanel.setBounds(0, 0, 400, 400);
			resultPanel.setBackground(Color.lightGray);
			resultPanel.setLayout(null);
			studentInfo=new JLabel("Student Information");
			lName=new JLabel("Name :");
			name=new JLabel();
			lAddress=new JLabel("Address:");
			address=new JLabel();
			lEmail=new JLabel("Email :");
			email=new JLabel();
			lNumber=new JLabel("Number :");
			number=new JLabel();
			lGender=new JLabel("Gender :");
			gender=new JLabel();
			lResult=new JLabel("Result :");
			lresult=new JLabel();
			back=new JButton("back");
			studentInfo.setBounds(150,0,300,30);
			lName.setBounds(50,40,120,20);
			name.setBounds(180,40,120,20);
			lAddress.setBounds(50,80,120,20);
			address.setBounds(180,80,120,20);
			lEmail.setBounds(50,120,120,20);
			email.setBounds(180,120,200,20);
			lNumber.setBounds(50,160,120,20);
			number.setBounds(180,160,120,20);
			lGender.setBounds(50,200,120,20);
			gender.setBounds(180,200,120,20);
			lResult.setBounds(50,240,80,20);
			lresult.setBounds(135,240,275,20);
			back.setBounds(100,350,100,25);
			resultPanel.add(lName);
			resultPanel.add(name);
			resultPanel.add(lAddress);
			resultPanel.add(address);
			resultPanel.add(lEmail);
			resultPanel.add(email);
			resultPanel.add(lNumber);
			resultPanel.add(number);
			resultPanel.add(lGender);
			resultPanel.add(gender);
			resultPanel.add(lResult);
			resultPanel.add(lresult);
			resultPanel.add(back);
			back.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					error.setText("");
					resultPanel.setVisible(false);
					remove(resultPanel);
					addPanel(studentPanel);
					
				}
			});
		}
	}
	/*
	 * QuestionHandler is displayed after the clicked is made in a questionInfo Button of InsideAdmin Panel.
	 * this will show   four Button with subject name and gives the actionListener class InsideQuestionHandler 
	 * with respective arguments .
	 */
	class QuestionHandler implements ActionListener{
		JButton physics,chemistry,english,math,back;
		String source;
		public void actionPerformed(ActionEvent e){
			questionPanel.setVisible(false);
			remove(questionPanel);
			error.setText("");
			questionHandler=new JPanel();
			questionHandler.setLayout(null);
			questionHandler.setBounds(0, 0, 400, 400);
			questionHandler.setBackground(Color.lightGray);
			physics=new JButton("Physics Question");
			chemistry=new JButton("Chemistry Question");
			english=new JButton("English Question");
			math=new JButton("Math Question");
			back=new JButton("Back");
			physics.setBounds(100,50,200,30);
			chemistry.setBounds(100,100,200,30);
			english.setBounds(100,150,200,30);
			math.setBounds(100,200,200,30);
			back.setBounds(20,350,80,30);
			back.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					questionHandler.setVisible(false);
					remove(questionHandler);
					addPanel(questionPanel);
				}
			});
			questionHandler.add(physics);
			questionHandler.add(chemistry);
			questionHandler.add(english);
			questionHandler.add(math);
			questionHandler.add(back);
			questionHandler.setVisible(true);
			source=((AbstractButton) e.getSource()).getText();
			switch(source){
				case "Search Question":	
					physics.addActionListener(new InsideQuestionHandler("Search Question"));
					chemistry.addActionListener(new InsideQuestionHandler("Search Question"));
					english.addActionListener(new InsideQuestionHandler("Search Question"));
					math.addActionListener(new InsideQuestionHandler("Search Question"));
					
					break;
				case "Edit Question":
					physics.addActionListener(new InsideQuestionHandler("Edit Question"));
					chemistry.addActionListener(new InsideQuestionHandler("Edit Question"));
					english.addActionListener(new InsideQuestionHandler("Edit Question"));
					math.addActionListener(new InsideQuestionHandler("Edit Question"));
					break;
				case "Add new Question":
					physics.addActionListener(new InsideQuestionHandler("Add Question"));
					chemistry.addActionListener(new InsideQuestionHandler("Add Question"));
					english.addActionListener(new InsideQuestionHandler("Add Question"));
					math.addActionListener(new InsideQuestionHandler("Add Question"));
					
					break;
				case "Delete Question":
					physics.addActionListener(new InsideQuestionHandler("Delete Question"));
					chemistry.addActionListener(new InsideQuestionHandler("Delete Question"));
					english.addActionListener(new InsideQuestionHandler("Delete Question"));
					math.addActionListener(new InsideQuestionHandler("Delete Question"));
					
					break;
				
			}
			addPanel(questionHandler);
			
		}
	
	}	

	/*
	 * InsideQuestionHandler is used to handles the click event made by either search,edit,add or delete button
	 * 
	 */
	class InsideQuestionHandler implements ActionListener{
		JTextField search,eQuestion,eRightAns,eWrongAns1,eWrongAns2,eWrongAns3;
		JButton searchButton,back,doneEditing;
		JPanel insideQuestionHandler;
		String query,source,parentSource,action;
		boolean questionFound=false;
		JLabel question,rightAns,wrongAns1,wrongAns2,wrongAns3,error;
		ResultSet result;
		InsideQuestionHandler(String s){
			parentSource=s;			
		}
		public void actionPerformed(ActionEvent e){	
			questionHandler.setVisible(false);
			remove(questionHandler);
			insideQuestionHandler= new JPanel();
			insideQuestionHandler.setLayout(null);
			insideQuestionHandler.setBounds(0, 0, 400, 400);
			insideQuestionHandler.setBackground(Color.lightGray);
			search=new JTextField();
			search.setBounds(50,50,300,30);				
			searchButton=new JButton();
			searchButton.setBounds(100,100,150,30);
			back=new JButton("Back");
			back.setBounds(20,350,80,30);
			back.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					insideQuestionHandler.setVisible(false);
					remove(insideQuestionHandler);
					addPanel(questionHandler);
				}
			});
			question=new JLabel("");
			rightAns=new JLabel("");
			wrongAns1=new JLabel("");
			wrongAns2=new JLabel("");
			wrongAns3=new JLabel("");
			question.setBounds(50,150,400,30);
			rightAns.setBounds(50,200,400,30);
			wrongAns1.setBounds(50,230,400,30);
			wrongAns2.setBounds(50,260,400,30);
			wrongAns3.setBounds(50,290,400,30);		
			insideQuestionHandler.add(question);
			insideQuestionHandler.add(rightAns);
			insideQuestionHandler.add(wrongAns1);
			insideQuestionHandler.add(wrongAns2);
			insideQuestionHandler.add(wrongAns3);

			insideQuestionHandler.add(search);
			insideQuestionHandler.add(searchButton);
			insideQuestionHandler.add(back);
			addPanel(insideQuestionHandler);
			source=((AbstractButton) e.getSource()).getText();		
			
				
				switch(source){
				case "Physics Question":					
						searchButton.setText(parentSource);					
						action="physicsquestion";
						break;
				case "Chemistry Question":
					searchButton.setText(parentSource);
					action="chemistryquestion";
					break;
				case "Math Question":
					searchButton.setText(parentSource);
					action="mathquestion";
					break;
				case "English Question":
					searchButton.setText(parentSource);
					action="englishquestion";
					break;
			}
				switch(parentSource){
					case "Search Question":
							searchButton.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent ev){
									query="select * from "+action;
									questionFound=false;
									try {
										c.getConnection();
										con=(Connection) c.getDbConnection();
										st= (Statement) con.createStatement();
										result=st.executeQuery(query);
										while(result.next()){
											if(result.getString("question").equals(search.getText())){
												
												question.setText(search.getText()+" ?");
												rightAns.setText("right Answer="+result.getString("rightAns"));
													 wrongAns1.setText("wrongAns="+result.getString("wrongAns1"));
													 wrongAns2.setText("wrongAns="+result.getString("wrongAns2"));
														wrongAns3.setText("wrongAns="+result.getString("wrongAns3"));
														questionFound=true;	
														
											}
											
										}
										
										if(!questionFound){
					
											question.setText("Given question is not found!!");
										}
									} catch (SQLException e) {
										e.printStackTrace();

									} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
										e1.printStackTrace();
									}finally{
										try {
											con.close();
											st.close();
										} catch (SQLException e1) {
											e1.printStackTrace();
										}
									}
								}
							});
						break;
					case "Edit Question":
						question.setText("Edit Question :");
						rightAns.setText("Edit Right Answer :");
						wrongAns1.setText("Edit Wrong Answer1 :");
						wrongAns2.setText("Edit Wrong Answer2 :");
						wrongAns3.setText("Edit Wrong Answer3 :");
						doneEditing=new JButton("Done Editing");
						doneEditing.setBounds(200,350,150,30);
						eQuestion=new JTextField();
						eRightAns=new JTextField();
						eWrongAns1=new JTextField();
						eWrongAns2=new JTextField();
						eWrongAns3=new JTextField();
						error=new JLabel();
						error.setBounds(50,320,300,30);
						eQuestion.setBounds(180,150,200,25);
						eRightAns.setBounds(180,200,200,25);
						eWrongAns1.setBounds(180,230,200,25);
						eWrongAns2.setBounds(180,260,200,25);
						eWrongAns3.setBounds(180,290,200,25); 
						insideQuestionHandler.add(error);
						insideQuestionHandler.add(doneEditing);
						insideQuestionHandler.add(eQuestion);
						insideQuestionHandler.add(eRightAns);
						insideQuestionHandler.add(eWrongAns2);
						insideQuestionHandler.add(eWrongAns3);
						insideQuestionHandler.add(eWrongAns1);
						doneEditing.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent ev){
								error.setText("");
								try {
									c.getConnection();
									con=(Connection) c.getDbConnection();
									st= (Statement) con.createStatement();
									result=st.executeQuery(query);
									while(result.next()){
										if(result.getString("question").equals(search.getText())){
											query="update "+action+" set question='"+eQuestion.getText()+"',rightAns='"+eRightAns.getText()+"',wrongAns1='"+eWrongAns1.getText()+"',wrongAns2='"+eWrongAns2.getText()+"',wrongAns3='"+eWrongAns3.getText()+"' where question='"+result.getString("question")+"'";
											if(st.executeUpdate(query)==1){
												error.setText("Given question is edited successfully!!");
											}
											questionFound=true;
										}
										if(!result.next()&&!questionFound){
											error.setText("Given question is not found!!");
										}
									}
								} catch (SQLException e) {
									e.printStackTrace();

								} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
									e1.printStackTrace();
								}/*finally{
									try {
										con.close();
										st.close();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
								*/
							}
						});
						searchButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent ev){
								error.setText("");
								query="select * from "+action;
								try {
									c.getConnection();
									con=(Connection) c.getDbConnection();
									st= (Statement) con.createStatement();
									result=st.executeQuery(query);
									while(result.next()){
										if(result.getString("question").equals(search.getText())){
											eQuestion.setText(search.getText());
											eRightAns.setText(result.getString("rightAns"));
												 eWrongAns1.setText(result.getString("wrongAns1"));
												 eWrongAns2.setText(result.getString("wrongAns2"));
													eWrongAns3.setText(result.getString("wrongAns3"));
													questionFound=true;
										}
										if(!result.next()&&!questionFound){
											error.setText("Given question is not found!!");
										}
									}
								} catch (SQLException e) {
									e.printStackTrace();

								} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
									e1.printStackTrace();
								}finally{
									try {
										con.close();
										st.close();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
							}
						});
						break;
					case "Add Question":
						rightAns.setText("Edit Right Answer :");
						wrongAns1.setText("Edit Wrong Answer1 :");
						wrongAns2.setText("Edit Wrong Answer2 :");
						wrongAns3.setText("Edit Wrong Answer3 :");
						eRightAns=new JTextField();
						eWrongAns1=new JTextField();
						eWrongAns2=new JTextField();
						eWrongAns3=new JTextField();
						eRightAns.setBounds(180,200,200,25);
						eWrongAns1.setBounds(180,230,200,25);
						eWrongAns2.setBounds(180,260,200,25);
						eWrongAns3.setBounds(180,290,200,25);
						insideQuestionHandler.add(eRightAns);
						insideQuestionHandler.add(eWrongAns1);
						insideQuestionHandler.add(eWrongAns2);
						insideQuestionHandler.add(eWrongAns3);
						
						searchButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent ev){
								query="insert into "+action+" values(null,'"+search.getText()+"','"+eRightAns.getText()+"','"+eWrongAns1.getText()+"','"+eWrongAns2.getText()+"','"+eWrongAns3.getText()+"')";
								try {
									c.getConnection();
									con=(Connection) c.getDbConnection();
									st= (Statement) con.createStatement();
									if(st.executeUpdate(query)==1){	
										question.setText("Given question is added successfully!!");
									}else{
										question.setText("Given question was not added!!");
									}
								} catch (SQLException e) {
									e.printStackTrace();
								} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
									e1.printStackTrace();
								}finally{
									try {
										con.close();
										st.close();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
							}
						});
				
						break;
					case "Delete Question":
						searchButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent ev){
								query="select * from "+action;
								try {
									c.getConnection();
									con=(Connection) c.getDbConnection();
									st= (Statement) con.createStatement();
									result=st.executeQuery(query);
									while(result.next()){
										if(result.getString("question").equals(search.getText())){
											query="delete from "+action+" where question='"+search.getText()+"'";
											if(st.executeUpdate(query)==1){	
												question.setText("Given question deleted successfully!!");
											}else{
												question.setText("Given question was not deleted!!");
											}
											questionFound=true;
										}
										if(!result.next()&&!questionFound){
											question.setText("Given question is not found!!");
										}
									}
								} catch (SQLException e) {
									e.printStackTrace();
								} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
									e1.printStackTrace();
								}finally{
									try {
										con.close();
										st.close();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
							}
						});
				
						break;
			
				}
			
			
		}
		
	}

}
