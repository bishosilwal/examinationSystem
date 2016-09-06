import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/*
 * AfterLogin class is loaded after student sucessfully logged in .It shows four button 
 * for question paper and one finished button to end the examination process,in  insideLogin
 * Panel.
 * The exam test is only finished whenever the student click to the finished button and thus
 *  result is save to the database instantly.
 * 
 */
public class AfterLogin extends JPanel {
	static JButton physics,english,chemistry,math,finish;
	static JPanel insideLogin;
	static DbConnection c=new DbConnection();
	static Connection con = null;
	static String query;
	static Statement st=null;
	static ResultSet result;
	static Container container;
	static int wrightAns=0;
	static int id;
	AfterLogin(int id){
		this.id=id;
		container=this.getRootPane();
		setLayout(null);
		setBounds(150, 50, 400, 400);
		setBackground(Color.lightGray);
		insideLogin=new JPanel();
		insideLogin.setLayout(null);
		insideLogin.setBounds(0, 0, 400, 400);
		insideLogin.setBackground(Color.lightGray);
		initComponent();
		insideLogin.setVisible(true);
		add(insideLogin);
	}
	public void initComponent(){
		physics=new JButton("Physics Paper");
		chemistry=new JButton("Chemistry Paper");
		english=new JButton("English Paper");
		math=new JButton("Math Paper");
		finish=new JButton("Finished");
		physics.setBounds(100,50,200,30);
		chemistry.setBounds(100,100,200,30);
		english.setBounds(100,150,200,30);
		math.setBounds(100,200,200,30);
		finish.setBounds(200,300,150,30);
		insideLogin.add(physics);
		insideLogin.add(english);
		insideLogin.add(math);
		insideLogin.add(chemistry);
		insideLogin.add(finish);
		physics.addActionListener(new QuestionHandler());
		chemistry.addActionListener(new QuestionHandler());
		math.addActionListener(new QuestionHandler());
		english.addActionListener(new QuestionHandler());
		finish.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String passResult = null;
				if((wrightAns*2)>=50){
					passResult="pass";
				}else if((wrightAns*2)<50){
					passResult="fail";
				}
				try{
					c.getConnection();
					con=(Connection) c.getDbConnection();
					st=(Statement) con.createStatement();
					query="Update students set result='"+passResult+"',totalRightAns="+wrightAns+" where id="+id;		
					st.executeUpdate(query);
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				insideLogin.setVisible(false);
				remove(insideLogin);
				setVisible(false);	
				MainFrame.makeDefault();
				MainFrame.addPanel();
				try {
					finalize();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
	}
	static public void addInsideLogin(){
		insideLogin.setVisible(true);
		container.add(insideLogin);
		container.revalidate();
		container.repaint();
	}
	static void setResult(int ans){
		wrightAns+=ans;
	}
	
	/*
	 * QuestionHandler class load the question paper by using QuestionPaper class .
	 * when the user click the question paper button then this ActionListener class
	 * QuestionHandler is removed from the respective button to make more secure while
	 * giving exam,otherwise student able to give exam of any question as many time as
	 * they want.
	 * It will load the question Paper by using QuestionPaper class with the required subject
	 * as an argument.
	 */
	 public class QuestionHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			String source=((AbstractButton) e.getSource()).getText();
			switch(source){
			case "Physics Paper":
				new QuestionPaper("physicsquestion");
				for(ActionListener l: physics.getActionListeners()){
					physics.removeActionListener(l);
				}
				break;
			case "Chemistry Paper":
				new QuestionPaper("chemistryquestion");
				for(ActionListener l: chemistry.getActionListeners()){
					chemistry.removeActionListener(l);
				}
				break;
			case "Math Paper":
				new QuestionPaper("mathquestion");
				for(ActionListener l: math.getActionListeners()){
					math.removeActionListener(l);
				}
				break;
			case "English Paper":
				new QuestionPaper("englishquestion");
				for(ActionListener l: english.getActionListeners()){
					english.removeActionListener(l);
				}
				break;
			}
			
		}
	}
	
}
