package knotcode.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		 //st.executeUpdate(ekleme);
		
		
		String selectQery="Select * From bolumler";
		ResultSet rs=st.executeQuery(selectQery);
		while (rs.next()) {
			System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
					
		}
		
		 /*=======================================================================
        ORNEK2: Bolumler tablosuna birden fazla yeni kayıt ekleyelim.
       ========================================================================*/ 
      
      // 1.YONTEM
   	// -----------------------------------------------
   	// Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin 
   	// yavas yapilmasina yol acar. 10000 tane veri kaydi yapildigi dusunuldugunde
      // bu kotu bir yaklasimdir.
   		
   	String [] sorgular = {"INSERT INTO bolumler VALUES(94, 'YEMHANE', 'bolu')",
   		            	  "INSERT INTO bolumler VALUES(84, 'OF','kars')",
   		            	  "INSERT INTO bolumler VALUES(74, 'OF2', 'artvin')"};
   	
   	int s2 = 0;
 	for(String each: sorgular) {
 		 //s2 = s2 + st.executeUpdate(each);
 	}
 	System.out.println(s2 + " satir eklendi");
 	
 	// 2.YONTEM (addBath ve excuteBatch() metotlari ile)
 	// ----------------------------------------------------
 	// addBatch metodu ile SQL ifadeleri gruplandirilabilir ve exucuteBatch()
 	// metodu ile veritabanina bir kere gonderilebilir.
 	// excuteBatch() metodu bir int [] dizi dondurur. Bu dizi her bir ifade sonucunda 
 	// etkilenen satir sayisini gosterir. 
 	
 	for (String each : sorgular) {
		st.addBatch(each);
	}
 	int[] s3=st.executeBatch();
 	System.out.println(s3.length+"satir eklendi");
 		
	// 3. Yontem
 	//batch methoduyla birlikte PreparedStatement kullanmak en efektif yontemdir.
 	
 	
	
		
		
		
		
		
		
		con.close();
		st.close();
	}

}
