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
                String pathMovie = rs.getString("pathMovie");
                // Menambahkan data lagu ke ArrayList
                movies.add(new Movie(id, title, director, year, genre, description, image, pathMovie, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
