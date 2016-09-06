import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/*
 * QuestionPaper class is used to load the question paper according to the subject given
 * in the argument .At first it will connect to the database and extract 10 question with 
 * wrong answer and right answer ,and show them in a pQuestion Panel.There is another 
 * inside class MyThread to show the time remaining for question paper.
 */
public class QuestionPaper extends JFrame{
	private JLabel question[]=null,time=null;
	private JRadioButton answer[][]=null;
	private JPanel pQuestion;
	private ButtonGroup ansGroup[]=null,ans;
	private String subject;
	private JButton submit;
	Random random=null;
	DbConnection c=new DbConnection();
	Connection con = null;
	Statement st=null;
	String query=null;
	ResultSet result;
	boolean running=true;
	MyThread thread;
	QuestionPaper(String subject){
		super("Question Paper of"+subject);
		this.subject=subject;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(900,700));
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		pQuestion=new JPanel();
		pQuestion.setBounds(0, 0, 900, 700);
		pQuestion.setBackground(Color.lightGray);
		pQuestion.setLayout(null);
		initcomponent();
		pQuestion.setVisible(true);
		add(pQuestion);
		setVisible(true);
		
	}
	public void initcomponent(){
		question=new JLabel[10];
		answer= new JRadioButton[10][4];
		ansGroup=new ButtonGroup[10];
		time=new JLabel("");
		time.setBounds(300,600,300,30);
		time.setForeground(Color.RED);
		submit=new JButton("Submit");
		submit.setBounds(750,600,100,30);
		pQuestion.add(submit);
		pQuestion.add(time);
		random= new Random();
		thread =new MyThread();
		try{
			c.getConnection();
			con=(Connection) c.getDbConnection();
			st=(Statement) con.createStatement();
			query="select * from "+subject;		
			result=st.executeQuery(query);
			for(int i=0;result.next()&&i<10;i++){	
				//Initialization of question paper in a label
				question[i]=new JLabel((i+1)+". "+result.getString("question")+" ?");
				question[i].setBounds(10,10+i*60,850,20);
				pQuestion.add(question[i]);
				/*
				 * Answer are must be randomly order in a radio button so we are 
				 * using Random() class to generate random value between 0 to 4. 
				 */
				int pos=random.nextInt(4);
				ans=new ButtonGroup();
				for(int j=0;j<4;j++){				
					if(j==0){
						answer[i][j]=new JRadioButton(result.getString("rightAns"));
						answer[i][j].setActionCommand("rightAns");

					}else{
						
						answer[i][j]=new JRadioButton(result.getString("wrongAns"+j));
						answer[i][j].setActionCommand("wrongAns");
					}
					ans.add(answer[i][j]);
					
				}
				ansGroup[i]=ans;
				//Initializing answer option in a radio Button
				switch(pos){
				case 0:
					answer[i][0].setBounds(50,35+i*60,150,20);
					answer[i][3].setBounds(200,35+i*60,150,20);
					answer[i][1].setBounds(350,35+i*60,150,20);
					answer[i][2].setBounds(500,35+i*60,150,20);
					break;
				case 1:
					answer[i][1].setBounds(50,35+i*60,150,20);
					answer[i][2].setBounds(200,35+i*60,150,20);
					answer[i][0].setBounds(350,35+i*60,150,20);
					answer[i][3].setBounds(500,35+i*60,150,20);
					break;
				case 2:
					answer[i][2].setBounds(50,35+i*60,150,20);
					answer[i][0].setBounds(200,35+i*60,150,20);
					answer[i][3].setBounds(350,35+i*60,150,20);
					answer[i][1].setBounds(500,35+i*60,150,20);
					break;
				case 3:
					answer[i][3].setBounds(50,35+i*60,150,20);
					answer[i][1].setBounds(200,35+i*60,150,20);
					answer[i][2].setBounds(350,35+i*60,150,20);
					answer[i][0].setBounds(500,35+i*60,150,20);
					break;
				}
				pQuestion.add(answer[i][0]);
				pQuestion.add(answer[i][1]);
				pQuestion.add(answer[i][2]);
				pQuestion.add(answer[i][3]);
				
			}
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				running=false;
				int right=0;	
				//counting the right answer while submiting the result
					for(int i=0;i<10;i++){
						if(ansGroup[i].getSelection()!=null){
							if(ansGroup[i].getSelection().getActionCommand().equals("rightAns")){
							right++;			
							}
						}
					}
				AfterLogin.setResult(right);
				pQuestion.setVisible(false);
				remove(pQuestion);
				setVisible(false);	
			}
		});
		//initializing thread to show the time remaining 
		MyThread th=new MyThread();
		th.start();	
	}
	/*
	 * class for time remaining function.After the certain time is passed eg.10 minute,
	 * it will call the check() method and count the right answer and submit the result
	 * automatically.
	 * 
	 */
	class MyThread extends Thread{
		int currentTime[];
		java.util.Date d,tempD;
		int timeForQuestion=10;
		MyThread(){
			d= new java.util.Date();
			currentTime=new int[3]; 
			currentTime[0]=d.getHours();
			currentTime[1]=d.getMinutes();
			currentTime[2]=d.getSeconds();
			//total time for one question paper is 10 min
			currentTime[1]+=timeForQuestion;
			if(currentTime[1]>=60){
				currentTime[0]+=1;
				currentTime[1]=currentTime[1]-60;
			}
		}
		public void run(){
			while(running){
				updateTime();
				check();
			}
		}	
		public synchronized void updateTime(){
			tempD = new java.util.Date();
			int min;
			int sec;
			sec=60-tempD.getSeconds();
			if(currentTime[0]==tempD.getHours()){
				min=currentTime[1]-tempD.getMinutes();
				time.setText("Time remaining: "+min+" minute,"+sec+" second ");
			}else if(currentTime[0]==(tempD.getHours()+1)){
				min=60+currentTime[1]-tempD.getMinutes();
				time.setText("Time remaining: "+min+" minute,"+sec+" second ");
			}
		}
		public synchronized void  check(){
			tempD = new java.util.Date();
			if(currentTime[0]==tempD.getHours()&&currentTime[1]==tempD.getMinutes()&&currentTime[2]==tempD.getSeconds()){	
				time.setText("Time over!!!!");
				int wright=0;	
				for(int i=0;i<10;i++){
					if(ansGroup[i].getSelection()!=null){
						if(ansGroup[i].getSelection().getActionCommand().equals("rightAns")){
							wright++;			
						}
					}
				}
				
			AfterLogin.setResult(wright);
			pQuestion.setVisible(false);
			remove(pQuestion);
			setVisible(false);	
			running=false;
			this.stop();	
			}
		}
	}
}
