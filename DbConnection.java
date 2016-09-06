import java.sql.*;

/*
 * DbConnection class is used to connect to the mysql database which is located 
 * in the localhost.It has getConnection() method to load the driver,getDbConnection()
 * method to connect to the database in the localhost and closeConnection() method 
 * to close the database connection .
 */
public class DbConnection {
	 private Connection con=null;
		private String user="root";
		private String pass="";
		private String db="jdbc:mysql://localhost:3306/examinationsystem";
		public void getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
		}
		public Connection getDbConnection() throws SQLException{
			con=DriverManager.getConnection(db,user,pass);
			return (Connection) con;
		}
		public void closeConnection() throws SQLException{
			con.close();
		}
}
