package knotcode.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc3DDL {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String yol = "jdbc:oracle:thin:@localhost:1521/XE";
		Connection con = DriverManager.getConnection(yol, "hr", "hr");
		Statement st = con.createStatement();

		/*
		 * A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi
		 * (ResultSet) dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC’de 2
		 * alternatif bulunmaktadir. 1) execute() metodu 2) excuteUpdate() metodu.
		 * 
		 * B) - execute() metodu hertur SQL ifadesiyle kullanibilen genel bir komuttur.
		 * - execute(), Boolean bir deger dondurur. DDL islemlerin false dondururken, -
		 * DML islemlerinde true deger dondurur. - Ozellikle hangi tip SQL ifadesinin
		 * kullanilmasinin gerektiginin belli olmadigi durumlarda tercih edilmektedir.
		 * 
		 * C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin
		 * kullanilir. - bu islemlerde islemden etkilenen satir sayisini dondurur. -
		 * Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.
		 */

		// 1. yöntem
		String createQuery = "Create Table isciler (id Number(3),birim VarChar2(10),maas Number(5))";
		// st.execute(createQuery);
		// System.out.println(tablo);

		// 2. yöntem
		// int s2=st.executeUpdate(createQuery);
		// System.out.println(s2);
		// String dropQuery="Drop Table isciler purge";
		// boolean delet=st.execute(dropQuery);
		// System.out.println(delet);

		/*
		 * =======================================================================
		 * ORNEK3:isciler tablosuna yeni bir sutun {isim Varchar2(20)} ekleyiniz.
		 * ========================================================================
		 */
		String alter = "Alter Table isciler ADD isim VarChar2(20)";
		// st.execute(alter);

		String alter1 = "Alter Table isciler ADD (soy_isim VarChar2(20),sehir Varchar2(20))";
		// st.execute(alter1);
		/*
		 * =======================================================================
		 * ORNEK5:isciler tablosundaki soyisim sutunu siliniz.
		 * ========================================================================
		 */
		String alter3 = "Alter Table isciler Drop Column soy_isim";
		// st.executeUpdate(alter3);

		/*
		 * =======================================================================
		 * ORNEK6:isciler tablosununadini calisanlar olarak degistiriniz.
		 * ========================================================================
		 */
		String alter5 = "Alter Table isciler Rename To calisanlar";
		// st.execute(alter5);

		String alter6 = "DROP TABLE calisanlar PURGE";
		st.execute(alter6);
		System.out.println("calisanlar tablosu silindi..");
		
		st.close();
		con.close();
	}

}
