import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SemuaTiket extends JFrame {
    private int userId;

    public SemuaTiket(int userId) {
        this.userId = userId;
        setTitle("Semua Tiket");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel untuk menampilkan tiket
        JPanel tiketPanel = new JPanel();
        tiketPanel.setLayout(new BoxLayout(tiketPanel, BoxLayout.Y_AXIS));

        // Query untuk mendapatkan semua tiket
        try (Connection conn = ConnectKeDB.getConnection()) {
            String query = "SELECT * FROM tiketbioskop WHERE user_id = ? UNION SELECT * FROM tiketkonserseni WHERE user_id = ?";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setInt(1, userId);
                st.setInt(2, userId);

                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        String tiket = rs.getString("nama_tiket");  // Sesuaikan dengan nama kolom DB
                        JLabel tiketLabel = new JLabel(tiket);
                        tiketPanel.add(tiketLabel);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Menambahkan panel ke frame
        add(new JScrollPane(tiketPanel), BorderLayout.CENTER);

        setVisible(true);
    }
}

