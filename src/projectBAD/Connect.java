package projectBAD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	
	ResultSet rs;
	Statement stmt;
	Connection con;
	
	public Connect() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");//masukin driver mysql
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectbad","root","");//buat connect php ke admin
			stmt = con.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//buat bikin SELECT
	public ResultSet executeQuery(String query){
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	// buat INSERT, UPDATE, DELETE
	public void executeUpdate(String query){
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PreparedStatement prepareStatement(String sqlDelete) {
		// TODO Auto-generated method stub
		return null;
	}

}
