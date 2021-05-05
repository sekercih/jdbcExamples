package knotcode.jdbcExamples;

import java.sql.*;

public class Jdbc2Query02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String yol = "jdbc:oracle:thin:@localhost:1521/XE";
		Connection con = DriverManager.getConnection(yol, "hr", "hr");
		Statement st = con.createStatement();

		// Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.

		String selectQuery = "SELECT * FROM bolumler";
		ResultSet tablo1 = st.executeQuery(selectQuery);

		while (tablo1.next()) {
			System.out.println(tablo1.getInt(1) + " " + tablo1.getString(2) + " " + tablo1.getString(3));
		}

		/*
		 * =======================================================================
		 * ORNEK3: SATIS ve MUHASABE bolumlerinde calisan personelin isimlerini ve
		 * maaslarini maas sirali olarak listeleyiniz
		 * ========================================================================
		 */
		System.out.println("==============================================================");
		ResultSet isim1 = st.executeQuery("SELECT * FROM personel");
		String select01 = "-----------------------------------------------------------------------------*/ \r\n"
				+ "  SELECT P.personel_isim,B.BOLUM_ISIM,P.MAAS\r\n" + "  FROM PERSONEL P \r\n"
				+ "  JOIN BOLUMLER B \r\n" + "  ON B.BOLUM_ID =P.BOLUM_ID \r\n" + " WHERE B.BOLUM_ID IN (10,30)\r\n"
				+ " ORDER BY P.MAAS DESC";
		ResultSet rst01 = st.executeQuery(select01);
		while (rst01.next()) {
			System.out.println(rst01.getString(1) + " " + rst01.getString(2) + " " + rst01.getString(3));
		}

		/*
		 * =======================================================================
		 * ORNEK4: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini ve
		 * maaslarini bolum ve maas siraali listeleyiniz. NOT: calisani olmasa bile
		 * bolum ismi gosterilmelidir.
		 * ========================================================================
		 */
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		String q3 = "Select b.bolum_isim,p.personel_isim,p.maas" + " From personel p" + " FULL JOIN bolumler b"
				+ " ON b.bolum_id=p.bolum_id" + " ORDER BY b.bolum_isim,p.maas";

		ResultSet sonuc1 = st.executeQuery(q3);
		while (sonuc1.next()) {
			System.out.println(sonuc1.getString(1) + " " + sonuc1.getString(2) + " " + sonuc1.getInt(3));
		}
		/**
		 * =======================================================================
		 * ORNEK5: Maasi en yuksek 10 kisiyinin bolumunu,adini ve maasini listeyiniz
		 * ========================================================================
		 */
		System.out.println("***********************");

		String q4 = "SELECT b.bolum_isim, p.personel_isim, p.maas" + " FROM personel p" + " JOIN bolumler b"
				+ " ON b.bolum_id = p.bolum_id" + " ORDER BY p.maas DESC";

		ResultSet sonuc4 = st.executeQuery(q4);
		int i = 1;
		while (sonuc4.next()) {
			System.out.println(sonuc4.getString(1) + sonuc4.getString(2) + sonuc4.getInt(3));
			i++;
			if (i == 11)
				break;

		}

		con.close();
		st.close();
		tablo1.close();
		sonuc1.close();

	}
}