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
    public static ArrayList<Konser> getKonser(String status) {
        ArrayList<Konser> konser = new ArrayList<>();
        String query = "SELECT * FROM konser WHERE status = '" + status + "'";
        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String image = rs.getString("image");
                String date = rs.getString("date");
                konser.add(new Konser(id, title, description, image, status, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return konser;
    }

    public static ArrayList<Kesenian> getKesenian(String status) {
        ArrayList<Kesenian> kesenian = new ArrayList<>();
        String query = "SELECT * FROM kesenian WHERE status = '" + status + "'";
        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String image = rs.getString("image");
                String date = rs.getString("date");
                String lokasi = rs.getString("lokasi");
                kesenian.add(new Kesenian(id, title, description, image, status, date, lokasi));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kesenian;
    }

    public static void beliTiketKonserKesenian(int idUser, String nama, String NIK, String email, String jenisTiket, int jumlahTiket, String namaTiket) {
        try (Connection conn = ConnectKeDB.getConnection()) {
            String querySearch1 = "SELECT id FROM konser WHERE title = ?";
            String querySearch2 = "SELECT id FROM kesenian WHERE title = ?";
            String tipe = "";
            try (PreparedStatement ps1 = conn.prepareStatement(querySearch1)) {
                ps1.setString(1, namaTiket);
                
                ResultSet rs1 = ps1.executeQuery();
                
                int idAcara = -1;
                
                if (rs1.next()) {
                    idAcara = rs1.getInt("id");
                    tipe = "Konser";

                }
                else {
                    try (PreparedStatement ps2 = conn.prepareStatement(querySearch2)) {
                        ps2.setString(1, namaTiket);
                        
                        ResultSet rs2 = ps2.executeQuery();
                        
                        if (rs2.next()) {
                            idAcara = rs2.getInt("id");
                            tipe = "Kesenian";
                        }
                    }
                }
                if (idAcara == -1) {
                    System.out.println("Tiket dengan nama " + namaTiket + " tidak ditemukan.");
                    return;
                }
                
                String queryInsert = "INSERT INTO tiketKonserSeni(id_user, nama, NIK, email, jenis_tiket, jumlah_tiket, id_acara, kategori) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement psInsert = conn.prepareStatement(queryInsert)) {
                    psInsert.setInt(1, idUser);
                    psInsert.setString(2, nama);
                    psInsert.setString(3, NIK);
                    psInsert.setString(4, email);
                    psInsert.setString(5, jenisTiket);
                    psInsert.setInt(6, jumlahTiket);
                    psInsert.setInt(7, idAcara);
                    psInsert.setString(8, tipe);
                    
                    int rowsAffected = psInsert.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        System.out.println("Okeeeee");
                    }
                    else {
                        System.out.println("Gagal insert");
                    }
                }
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<tiketBioskop> getAllTiketBioskop(int idAkun) {
        // Membuat objek ArrayList untuk menyimpan data lagu
        // Query untuk mendapatkan semua data lagu dari database
        String query = "SELECT tb.id AS idTiket,tb.tempatDuduk,jt.jamTanggal AS jadwal,jt.hari,jt.studio,mv.title, mv.image, ak.username FROM tiketbioskop tb JOIN jadwaltayang jt ON tb.idJadwalTayang = jt.id JOIN movie mv ON jt.idMovie = mv.id JOIN user ak ON tb.idAkun = ak.id where tb.idAkun = "
                + idAkun + " AND tb.status = 'active'";
        ArrayList<tiketBioskop> tiket = new ArrayList<tiketBioskop>();
        // Membuat koneksi ke database
        try (Connection conn = ConnectKeDB.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            // Menambahkan data lagu ke ArrayList
            while (rs.next()) {
                String tempatDuduk = rs.getString("tempatDuduk");
                String jadwal = rs.getString("jadwal");
                String hari = rs.getString("hari");
                String studio = rs.getString("studio");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String username = rs.getString("username");
                tiket.add(new tiketBioskop(tempatDuduk, jadwal, hari, studio, title, image,username, idAkun));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiket;
    }

    public static ArrayList<tiketKonserSeni> getAllTiketKonserSeni(int idAkun, String kategori) {
        String query = "SELECT tks.nama AS namaLengkap, tks.NIK, tks.email, tks.jumlah_tiket , tks.jenis_tiket AS jenisTiket, CASE WHEN tks.kategori = 'Konser' THEN k.date WHEN tks.kategori = 'Kesenian' THEN ks.date END AS jadwal, CASE WHEN tks.kategori = 'Konser' THEN k.image WHEN tks.kategori = 'Kesenian' THEN ks.image END AS image, CASE WHEN tks.kategori = 'Konser' THEN k.title WHEN tks.kategori = 'Kesenian' THEN ks.title END AS title FROM tiketkonserseni tks LEFT JOIN konser k ON tks.kategori = 'Konser' AND tks.id_acara = k.id LEFT JOIN kesenian ks ON tks.kategori = 'Kesenian' AND tks.id_acara = ks.id WHERE tks.id_user = "
        + idAkun + " AND tks.kategori = '" + kategori + "'";
        ArrayList<tiketKonserSeni> tiket = new ArrayList<tiketKonserSeni>();
        try (Connection conn = ConnectKeDB.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            // Menambahkan data lagu ke ArrayList
            while (rs.next()) {
                String namaLengkap = rs.getString("namaLengkap");
                String nik = rs.getString("NIK");
                String email = rs.getString("email");
                String jenisTiket = rs.getString("jenisTiket");
                String jumlahTiket = rs.getString("jumlah_tiket");
                String jadwal = rs.getString("jadwal");
                String image = rs.getString("image");
                String title = rs.getString("title");
                tiket.add(new tiketKonserSeni(namaLengkap, nik, email, jenisTiket, jumlahTiket, jadwal, image, title, idAkun));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiket;
    }
}