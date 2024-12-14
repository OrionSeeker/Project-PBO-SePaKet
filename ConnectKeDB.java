import java.sql.*;
import java.util.ArrayList;

public class ConnectKeDB {
    private static final String URL = "jdbc:mysql://localhost:3306/sepaket";
    private static final String usn = "root";
    private static final String pwd = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, usn, pwd);
    }
    public static ArrayList<Movie> getAllMovie(String status) {
        // Membuat objek ArrayList untuk menyimpan data lagu
        ArrayList<Movie> movies = new ArrayList<>();
        // Query untuk mendapatkan semua data lagu dari database
        String query = "SELECT * FROM movie WHERE status = '" + status + "'";
        // Membuat koneksi ke database
        try (Connection conn = getConnection();Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(query)) {
            // Menambahkan data lagu ke ArrayList
            while (rs.next()) {
                // Mengambil data lagu dari database
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String director = rs.getString("direction");
                String genre = rs.getString("genre");
                String description = rs.getString("description");
                String image = rs.getString("image");
                String urlMovie = rs.getString("urlMovie");
                // Menambahkan data lagu ke ArrayList
                movies.add(new Movie(id, title, director, year, genre, description, image, urlMovie, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    public static ArrayList<String> getTanggalHari(int idMovie) {
        // Membuat objek ArrayList untuk menyimpan data lagu
        ArrayList<String> hari = new ArrayList<>();
        // Query untuk mendapatkan semua data lagu dari database
        String query = "SELECT DISTINCT hari FROM jadwaltayang WHERE idMovie = '" + idMovie + "'";
        // Membuat koneksi ke database
        try (Connection conn = getConnection();Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(query)) {
            // Menambahkan data lagu ke ArrayList
            while (rs.next()) {
                // Menambahkan data jadwal tayang ke ArrayList
                hari.add(rs.getString("hari"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hari;
    }
    public static ArrayList<jadwalTayang> getJamHari(String hari, int idMovie) {
        // Membuat objek ArrayList untuk menyimpan data lagu
        ArrayList<jadwalTayang> jadwalTayangs = new ArrayList<>();
        // Query untuk mendapatkan semua data lagu dari database
        String query = "SELECT * FROM jadwaltayang WHERE hari = '" + hari + "' and idMovie = '" + idMovie + "'";
        // Membuat koneksi ke database
        try (Connection conn = getConnection();Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(query)) {
            // Menambahkan data lagu ke ArrayList
            while (rs.next()) {
                // Mengambil data lagu dari database
                int id = rs.getInt("id");
                String jam = rs.getString("jamTanggal");
                String studio = rs.getString("studio");


                // Menambahkan data jadwal tayang ke ArrayList
                jadwalTayangs.add(new jadwalTayang(id, idMovie, jam, hari, studio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jadwalTayangs;
    }
    public static ArrayList<Seat> getSeat(int idJadwalTayang) {
        // Membuat objek ArrayList untuk menyimpan data lagu
        ArrayList<Seat> Seats = new ArrayList<>();
        // Query untuk mendapatkan semua data lagu dari database
        String query = "SELECT * FROM seat WHERE idJadwalTayang = '" + idJadwalTayang + "'";
        // Membuat koneksi ke database
        try (Connection conn = getConnection();Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(query)) {
            // Menambahkan data lagu ke ArrayList
            while (rs.next()) {
                // Mengambil data lagu dari database
                int noKursi = rs.getInt("noKursi");
                int status = rs.getInt("status");
                String tempatDuduk = rs.getString("tempatDuduk");


                // Menambahkan data jadwal tayang ke ArrayList
                Seats.add(new Seat(idJadwalTayang, noKursi, status, tempatDuduk));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Seats;
    }
    public static void setSeat(int idJadwalTayang, int noKursi, String tempatDuduk, int idAkun, int totalHarga, Boolean bool) {
        // Query untuk memasukkan data ke dalam database
        String insertQuery = "INSERT INTO seat(`idJadwalTayang`, `noKursi`, `tempatDuduk`) VALUES (?, ?, ?)";
        String selectStudioQuery = "SELECT studio FROM jadwaltayang WHERE id = "+idJadwalTayang;
        try (Connection conn = getConnection(); PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
    
            // Mengatur nilai parameter yang akan dimasukkan ke query
            insertStmt.setInt(1, idJadwalTayang);
            insertStmt.setInt(2, noKursi);
            insertStmt.setString(3, tempatDuduk);
    
            // Menjalankan query untuk memasukkan data
            insertStmt.executeUpdate();
            if (bool) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(selectStudioQuery);
                if (rs.next()) {
                    String studio = rs.getString("studio");
                    setTiketBioskop(idAkun, idJadwalTayang, totalHarga, studio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void setTiketBioskop(int idAkun, int idJadwalTayang, int harga, String studio) {
        // Query untuk memasukkan data ke dalam database
        String query = "INSERT INTO tiketbioskop (`idAkun`, `idJadwalTayang`, `tempatDuduk`, `harga`, `studio`) VALUES (?, ?, ?, ?, ?)";
        String query1 = "SELECT tempatDuduk FROM seat WHERE atributBantu = 'belum'";  // Memperbaiki query SELECT
    
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query);
             PreparedStatement stmt1 = conn.prepareStatement(query1)) {
    
            // Menjalankan query SELECT untuk mendapatkan kursi yang tersedia
            ResultSet rs = stmt1.executeQuery();
            StringBuilder posisi = new StringBuilder();  // Menggunakan StringBuilder untuk efisiensi
    
            while (rs.next()) {
                String tempatDuduk = rs.getString("tempatDuduk");
                posisi.append(tempatDuduk).append(",");  // Menambahkan tempat duduk ke StringBuilder
            }
    
            // Jika ada kursi yang ditemukan, hapus koma terakhir
            if (posisi.length() > 0) {
                posisi.deleteCharAt(posisi.length() - 1);  // Menghapus koma terakhir
            }
    
            // Update atributBantu untuk menandai kursi yang telah dipilih
            String query3 = "UPDATE seat SET atributBantu = 'sudah' WHERE atributBantu = 'belum'";
            try (PreparedStatement stmt3 = conn.prepareStatement(query3)) {
                stmt3.executeUpdate();  // Menjalankan update status kursi
            }
            // Menyusun data yang akan dimasukkan ke dalam tabel tiketbioskop
            stmt.setInt(1, idAkun);          // Menyimpan idAkun
            stmt.setInt(2, idJadwalTayang);  // Menyimpan idJadwalTayang
            stmt.setString(3, posisi.toString()); // Menyimpan tempat duduk yang dipilih
            stmt.setInt(4, harga);           // Menyimpan harga tiket
            stmt.setString(5, studio);   // Menyimpan studio
    
            // Menjalankan query untuk memasukkan data tiket bioskop
            stmt.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
