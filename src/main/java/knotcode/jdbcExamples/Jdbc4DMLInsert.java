package knotcode.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc4DMLInsert {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String yol = "jdbc:oracle:thin:@localhost:1521/XE";
		Connection con = DriverManager.getConnection(yol, "hr", "hr");
		Statement st = con.createStatement();

		String ekleme = "Insert into bolumler Values(90,'TEST','Istanbul')";
		 st.executeUpdate(ekleme);
		
		
		String selectQery="Select * From bolumler";
		ResultSet rs=st.executeQuery(selectQery);
		while (rs.next()) {
			System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
			
			
		}
		
		
		
		
		
		
		con.close();
		st.close();
	}

}
