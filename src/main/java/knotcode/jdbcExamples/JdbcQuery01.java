package knotcode.jdbcExamples;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuery01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con=DriverManager.
				getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");
		Statement st=con.createStatement();
		
		
	}

}
