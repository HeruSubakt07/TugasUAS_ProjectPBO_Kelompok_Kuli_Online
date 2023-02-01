package S3UAS;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class pekerja{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/kuli_online";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);
    private static String sq3;
    private static String sq2;
    public static void main(String[] args) {

        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

     static void showMenu() {
        System.out.println("+=========== MENU UTAMA =========+");
        System.out.println("|   1. Masukan Data Pekerja      |");
        System.out.println("|   2. Menampilkan Data Pekerja  |");
        System.out.println("|   3. Edit Data Pekerja         |");
        System.out.println("|   4. Hapus Data Pekerja        |");
        System.out.println("|   0. Keluar                    |");
        System.out.println("|                                |");
        System.out.println("+--------------------------------+");
        System.out.print("Masukan No > ");


        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertPekerja();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updatePekerja();
                    break;
                case 4:
                    deletePekerja();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showData() {
        String sql = "SELECT * FROM pekerja";

        try {
            rs = stmt.executeQuery(sql);
            
            System.out.println("+--------------------------------+");
            System.out.println("|    DATA PEKERJA KULI ONLINE   |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                int id_pekerja = rs.getInt("id_pekerja");
                String nama_pekerja = rs.getString("nama_pekerja");
                String harga_jasa = rs.getString("harga_jasa");
                String alamat_pekerja = rs.getString("alamat_pekerja");
                String no_hp = rs.getString("no_hp");
                String email_pekerja = rs.getString("email_pekerja");
                String rekening_pekerja = rs.getString("rekening_pekerja");

                
                System.out.println(String.format("%d. %s,%s,%s,%s,%s",id_pekerja ,nama_pekerja, harga_jasa,alamat_pekerja, no_hp, email_pekerja, rekening_pekerja));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void insertPekerja() {
        try {
            // ambil input dari user
            System.out.print("id_pekerja: ");
            String id_pekerja = input.readLine().trim();
            System.out.print("nama_pekerja: ");
            String nama_pekerja = input.readLine().trim();
            System.out.print("harga_jasa: ");
            String harga_jasa = input.readLine().trim();
            System.out.print("alamat_pekerja: ");
            String alamat_pekerja = input.readLine().trim();
            System.out.print("no_hp: ");
            String no_hp = input.readLine().trim();
            System.out.print("email_pekerja: ");
            String email_pekerja = input.readLine().trim();
            System.out.print("rekening_pekerja: ");
            String rekening_pekerja= input.readLine().trim();
 
            // query simpan
            String sql = "INSERT INTO pekerja (id_pekerja, nama_pekerja, harga_jasa, alamat_pekerja,no_hp, email_pekerja, rekening_pekerja) VALUE('%s','%s','%s','%s','%s','%s','%s')";
          
            sql = String.format(sql,id_pekerja, nama_pekerja, harga_jasa, alamat_pekerja, no_hp, email_pekerja, rekening_pekerja );
         
            stmt.execute(sql);
     
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void updatePekerja() {
        try {
     
            System.out.print("ID yang mau diedit: ");
            int id_pekerja = Integer.parseInt(input.readLine());
            System.out.print("nama pekerja: ");
            String nama_pekerja = input.readLine().trim();
            System.out.print("harga jasa: ");
            String harga_jasa = input.readLine().trim();
            System.out.print("alamat pekerja : ");
            String alamat_pekerja = input.readLine().trim();
            System.out.print("no hp :");
            String no_hp = input.readLine().trim();
            System.out.print("email pekerja : ");
            String email_pekerja = input.readLine().trim();
            System.out.println("rekening pekerja :");
            String rekening_pekerja =input.readLine().trim();

            // query update
            String sql = "UPDATE pekerja SET nama_pekerja='%s', harga_jasa='%s',alamat_pekerja='%s', no_hp='%s', email_pekerja='%s', rekening_pekerja='%s' WHERE id_pekerja=%d";
            sql = String.format(sql, nama_pekerja, harga_jasa,alamat_pekerja, no_hp, email_pekerja, rekening_pekerja, id_pekerja );

            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deletePekerja() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang mau dihapus: ");
            int id_pekerja = Integer.parseInt(input.readLine());
            
            // buat query hapus
            String sql = String.format("DELETE FROM pekerja WHERE id_pekerja=%d", id_pekerja);

            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}