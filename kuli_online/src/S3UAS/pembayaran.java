package S3UAS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class pembayaran {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/kuli_online", "root", "");
			Statement stmt = con.createStatement();
			
			// Menampilkan tabel 1
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM pengguna");
            System.out.println("+--------------------------------+");
            System.out.println("|       DATA PENGGUNA            |");
            System.out.println("+--------------------------------+");
                                         
			while (rs1.next()) {
                                        System.out.println("id_pengguna     :"+ rs1.getInt(1));
                                        System.out.println("nama_pengguna   :"+ rs1.getString(2));
                                
			}
			
			// Menampilkan tabel 2
			ResultSet rs2 = stmt.executeQuery("SELECT * FROM pekerja");
            System.out.println("+--------------------------------+");
            System.out.println("|       MENYEWA PEKERJA          |");
            System.out.println("+--------------------------------+");
			while (rs2.next()) {
                                        System.out.println("id_pekerja      :"+ rs2.getInt(1));
                                        System.out.println("nama pengguna   :"+ rs2.getString(2));      
			}
                        ResultSet rs3 = stmt.executeQuery("SELECT * FROM pembayaran");
            System.out.println("+--------------------------------+");
            System.out.println("|       DETAIL PEMBAYARAN        |");
            System.out.println("+--------------------------------+");
			while (rs3.next()) {
                            
                                        System.out.println("id pengguna     :"+rs3.getInt(1));
                                        System.out.println("id pekerja      :" +rs3.getString(2));
                                        System.out.println("total harga     :"+rs3.getString(3));
                                        System.out.println("tanggal pembayaran  :"+rs3.getString(4));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}