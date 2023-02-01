package S3UAS;
import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class pengguna{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/kuli_online";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    /**
     * @param args the command line arguments
     */
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
        System.out.println("|   1. Masukan Data Pengguna     |");
        System.out.println("|   2. Menampilkan Data Pengguna |");
        System.out.println("|   3. Edit Data Pengguna        |");
        System.out.println("|   4. Hapus Data Pengguna       |");
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
                    insertPengguna();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updatePengguna();
                    break;
                case 4:
                    deletePengguna();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showData() {
        String sql = "SELECT * FROM pengguna";

        try {
            rs = stmt.executeQuery(sql);
            
            System.out.println("+--------------------------------+");
            System.out.println("|    DATA PENGGUNA KULI ONLINE   |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                int id_pengguna = rs.getInt("id_pengguna");
                String nama_pengguna = rs.getString("nama_pengguna");
                String alamat_pengguna = rs.getString("alamat_pengguna");

                
                System.out.println(String.format("%d, %s ,%s)", id_pengguna, nama_pengguna, alamat_pengguna));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void insertPengguna() {
        try {
            // ambil input dari user
            System.out.print("id pengguna: ");
            String id_pengguna = input.readLine().trim();
            System.out.print("nama pengguna: ");
            String nama_pengguna = input.readLine().trim();
            System.out.print("alamat pengguna: ");
            String alamat_pengguna = input.readLine().trim();
            System.out.print("email pengguna: ");
            String email_pengguna = input.readLine().trim();
 
            // query simpan
            String sql = "INSERT INTO pengguna (id_pengguna,nama_pengguna, alamat_pengguna, email_pengguna) VALUE('%s', '%s', '%s','%s')";
            sql = String.format(sql,id_pengguna, nama_pengguna, alamat_pengguna, email_pengguna);

            // simpan buku
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void updatePengguna() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang mau diedit: ");
            int id_pengguna = Integer.parseInt(input.readLine());
            System.out.print("nama pengguna: ");
            String nama_pengguna = input.readLine().trim();
            System.out.print("alamat_pengguna: ");
            String alamat_pengguna = input.readLine().trim();
            System.out.print("email_pengguna: ");
            String email_pengguna = input.readLine().trim();

            // query update
            String sql = "UPDATE pengguna SET nama_pengguna='%s', alamat_pengguna='%s', email_pengguna='%s' WHERE id_pengguna=%d";
            sql = String.format(sql, nama_pengguna, alamat_pengguna, email_pengguna, id_pengguna);

            // update data buku
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deletePengguna() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang mau dihapus: ");
            int id_pengguna = Integer.parseInt(input.readLine());
            
            // buat query hapus
            String sql = String.format("DELETE FROM pengguna WHERE id_Pengguna=%d", id_pengguna);

            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}