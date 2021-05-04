package knotcode.jdbcExamples;
import java.sql.*;
public class JdbcQuery02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String yol="jdbc:oracle:thin:@localhost:1521/XE";
		Connection con = DriverManager.getConnection(yol, "hr", "hr");
Statement st = con.createStatement();
		
		// Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.

		String selectQuery = "SELECT * FROM bolumler";
		ResultSet tablo1 = st.executeQuery(selectQuery);
		
		while(tablo1.next()) {
			System.out.println(tablo1.getInt(1) + " " + tablo1.getString(2) + " "+tablo1.getString(3));
		}
		
		/*=======================================================================
				 ORNEK3: SATIS ve MUHASABE bolumlerinde calisan personelin isimlerini ve 
		  		 maaslarini maas sirali olarak listeleyiniz
				========================================================================*/
		System.out.println("==============================================================");
		ResultSet isim1 = st.executeQuery("SELECT * FROM personel");
		String select01="-----------------------------------------------------------------------------*/ \r\n" + 
				"  SELECT P.personel_isim,B.BOLUM_ISIM,P.MAAS\r\n" + 
				"  FROM PERSONEL P \r\n" + 
				"  JOIN BOLUMLER B \r\n" + 
				"  ON B.BOLUM_ID =P.BOLUM_ID \r\n" + 
				" WHERE B.BOLUM_ID IN (10,30)\r\n" + 
				" ORDER BY P.MAAS DESC";
		ResultSet rst01 = st.executeQuery(select01);
		while (rst01.next()) {
			System.out.println(rst01.getString(1)+" "+rst01.getString(2)+" "+rst01.getString(3));
		}
		
		con.close();
		st.close();
		tablo1.close();
		
		
		
		
		
		
		
		
	}}