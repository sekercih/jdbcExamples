package knotcode.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;

public class JDBC5CRUD {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String yol = "jdbc:oracle:thin:@localhost:1521/XE";
		Connection con = DriverManager.getConnection(yol, "hr", "hr");
		Statement st = con.createStatement();

		/*
		 * =======================================================================
		 * ORNEK1: urunler adinda bir tablo olusturalim id(NUMBER(3), isim VARCHAR2(10)
		 * fiyat NUMBER(6,2)
		 * ========================================================================
		 */

		String create = "Create Table urunler( id NUMBER(3),isim VARCHAR2(10),fiyat NUMBER(6,2) )";
		//st.execute(create);

		/*
		 * =======================================================================
		 * ORNEK2: urunler tablosuna asagidaki kayitlari toplu bir sekilde ekleyelim
		 * ========================================================================
		 */
		// Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir.
		// PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari
		// icin) bir yontemdir.
		// Bunun icin;
		// 1) Veri girisine uygun bir POJO(Plain Old Java Object) sinifi olusturulur.
		// 2) POJO Class nesnelerini saklayacak bir collection olusturulur
		// 3) bir dongu ile kayitlar eklenir.

		List<Urun> kayitlar = new ArrayList<>();

		kayitlar.add(new Urun(101, "laptop", 6500));
		kayitlar.add(new Urun(102, "PC", 4500));
		kayitlar.add(new Urun(103, "Telefon", 4500));
		kayitlar.add(new Urun(104, "Anakart", 1500));
		kayitlar.add(new Urun(105, "Klavye", 200));
		kayitlar.add(new Urun(106, "Fare", 100));

		String insertQuery = "Insert Into urunler VALUES(?,?,?)";
		PreparedStatement pst = con.prepareStatement(insertQuery);

//		for (Urun urun : kayitlar) {
//			pst.setInt(1, urun.getId());
//			pst.setString(2, urun.getIsim());
//			pst.setDouble(3, urun.getFiyat());
//			pst.addBatch();
//		}

		int sonuc[] = pst.executeBatch();
		System.out.println(sonuc.length + "kayit eklendi..");

		String selectQuery = "SELECT * FROM urunler";
		ResultSet tablo1 = st.executeQuery(selectQuery);

		while (tablo1.next()) {
			System.out.println(tablo1.getInt(1) + " " + tablo1.getString(2) + " " + tablo1.getDouble(3));
		}
		// ORNEK3: urunler tablosundaki PC’nin fiyatini %10 zam yapiniz

		String updateQuery1 = "UPDATE urunler" + " SET fiyat = fiyat * 1.1" + " WHERE isim='PC'";

		int s1 = st.executeUpdate(updateQuery1);
		System.out.println(s1 + " satir guncellendi..");

		// ORNEK4: urunler tablosuna 107, Monitor, 1250 sekilnde bir kayit ekleyiniz

		kayitlar.add(new Urun(107, "Monitor", 1250));

//		for (Urun urun : kayitlar) {
//			pst.setInt(1, urun.getId());
//			pst.setString(2, urun.getIsim());
//			pst.setDouble(3, urun.getFiyat());
//			pst.addBatch();
//		}
		// int sonuc2[] = pst.executeBatch();
		// System.out.println(sonuc2.length + "kayit eklendi..");
		// ORNEK5: urunler tablosundaki Klavyeyi siliniz.
		String deleteQuery1 = "Delete urunler" + " WHERE isim='Klavye'";

		// int s3 = pst.executeUpdate(deleteQuery1);
		System.out.println(s1 + " satir guncellendi..");

		// ORNEK6: urunler tablosuna Marka adinda ve Default degeri ASUS olan yeni
		// bir sutun ekleyiniz.
		String UpdateColum = "ALTER TABLE URUNLER ADD Marka   varchar2(10) DEFAULT 'Asus' ";
		// int s4=pst.executeUpdate(UpdateColum);
		// System.out.println(s4+" satir güncellendi");

		// ORNEK7: urunler tablosundaki kayitlari sorgulayiniz.
		String querysorgula = "SELECT * FROM urunler";
		ResultSet urunlertable = st.executeQuery(querysorgula);

		while (urunlertable.next()) {
			System.out.println(
					urunlertable.getInt(1) + " " + urunlertable.getString(2) + " " + urunlertable.getDouble(3));
		}
		// ORNEK8: urunler tablosunu siliniz.
		String urunlerDrop="DROP TABLE Urunler";
		int s5=pst.executeUpdate(urunlerDrop);
		System.out.println(s5+" 1 tablo silindi");
	}

}
