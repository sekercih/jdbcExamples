package knotcode.jdbcExamples;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc1Query01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 1) Ilgili driver'i yuklemeliyiz.
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2) Baglanti olusturmaliyiz.
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "hr", "hr");
		// 3) SQL komutlari icin bir Statement nesnesi olsutur.
		Statement st = con.createStatement();

		// 4) SQL ifadeleri yazabilir ve calistirabiliriz.
		// (Personel tablosundaki personel_id'si 7369 olan personelin adini
		// sorgulayiniz).

		ResultSet isim = st.executeQuery("SELECT personel_isim FROM personel WHERE personel_id=7369");

		// 5) Sonuclari aldik ve isledik.
		while (isim.next()) {
			System.out.println("Personel Adi:" + isim.getString("personel_isim"));
		}

		ResultSet isim1 = st.executeQuery("SELECT * FROM personel");

		// 5) Sonuclari aldik ve isledik.
		while (isim1.next()) {
			System.out.println(isim1.getString("personel_id") + "->" + isim1.getString("personel_isim") + " "
					+ isim1.getString("meslek") + "->" + isim1.getString("mudur_id") + "->"
					+ isim1.getString("ise_baslama") + "->" + isim1.getString("maas") + "->"
					+ isim1.getString("bolum_id"));
		}

		// 6) Olusturulan nesneleri bellekten kaldiralim.
		con.close();
		st.close();
		isim.close();
	}
}
