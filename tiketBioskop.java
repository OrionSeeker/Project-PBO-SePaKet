import java.sql.*;
import javax.swing.*;
import java.awt.*;
public class tiketBioskop extends tiket{
    String tempatDuduk = "";
    String hari = "";
    String studio = "";
    String username = "";
    public tiketBioskop(String tempatDuduk, String jadwal, String hari, String studio, String title, String image, String username, int iduser){
        super(iduser,title, jadwal, image);
        this.tempatDuduk = tempatDuduk;
        this.hari = hari;
        this.studio = studio;
        this.username = username;
    }
    public String getTempatDuduk(){
        return this.tempatDuduk;
    }
    public String getHari(){
        return this.hari;
    }
    public String getStudio(){
        return this.studio;
    }
    public String getUsername(){
        return this.username;
    }
    @Override
    public JPanel tiketPanel(int idAkun, String kategori, int id){ 
        String query = "SELECT tb.id AS idTiket,tb.tempatDuduk,jt.jamTanggal AS jadwal,jt.hari,jt.studio,mv.title, mv.image, ak.username FROM tiketbioskop tb JOIN jadwaltayang jt ON tb.idJadwalTayang = jt.id JOIN movie mv ON jt.idMovie = mv.id JOIN user ak ON tb.idAkun = ak.id where tb.idAkun = "
                + idAkun + " AND tb.status = 'active'";
        String tempatDuduk = "";
        String jadwal = "";
        String hari = "";
        String studio = "";
        String title = "";
        String image = "";
        String username = "";
        // Membuat koneksi ke database
        try (Connection conn = ConnectKeDB.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            // Menambahkan data lagu ke ArrayList
            while (rs.next()) {
                tempatDuduk = rs.getString("tempatDuduk");
                jadwal = rs.getString("jadwal");
                hari = rs.getString("hari");
                studio = rs.getString("studio");
                title = rs.getString("title");
                image = rs.getString("image");
                username = rs.getString("username");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (title.equals("")) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setLayout(new BorderLayout());
            emptyPanel.setBackground(Color.WHITE);
            emptyPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            JLabel emptyLabel = new JLabel("Belum ada Tiket Bioskop yang dibeli", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 17));
            emptyLabel.setForeground(new Color(236, 157, 36, 255));
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
            emptyPanel.add(emptyLabel, BorderLayout.CENTER);

            return emptyPanel;
        }
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setBackground(Color.WHITE);
        detailPanel.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));

        JLabel titleLabel = new JLabel("Detail Pemesanan Tiket");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(70, 70, 70));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 0));
        detailPanel.add(titleLabel);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color textColor = new Color(60, 60, 60);

        JLabel namaLabel = new JLabel("Nama Pemesan: " + username);
        namaLabel.setFont(labelFont);
        namaLabel.setForeground(textColor);
        namaLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        namaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.add(namaLabel);

        JLabel tempatDudukLabel = new JLabel("Tempat Duduk: " + tempatDuduk);
        tempatDudukLabel.setFont(labelFont);
        tempatDudukLabel.setForeground(textColor);
        tempatDudukLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        tempatDudukLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.add(tempatDudukLabel);

        JLabel studioLabel = new JLabel("Studio: " + studio);
        studioLabel.setFont(labelFont);
        studioLabel.setForeground(textColor);
        studioLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        studioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.add(studioLabel);

        JLabel hariLabel = new JLabel("Hari: " + hari);
        hariLabel.setFont(labelFont);
        hariLabel.setForeground(textColor);
        hariLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        hariLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.add(hariLabel);

        JPanel detailPanel2 = new JPanel();
        detailPanel2.setLayout(new BoxLayout(detailPanel2, BoxLayout.Y_AXIS));
        detailPanel2.setBackground(Color.WHITE);

        JPanel posterInfoPanel = new JPanel();
        posterInfoPanel.setLayout(new BoxLayout(posterInfoPanel, BoxLayout.Y_AXIS));
        // posterInfoPanel.setBackground(new Color(245, 245, 245));
        posterInfoPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));
        posterInfoPanel.setOpaque(true);
        posterInfoPanel.setBackground(Color.WHITE);

        ImageIcon posterIcon = new ImageIcon(
                new ImageIcon(image).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
        JLabel posterLabel = new JLabel();
        posterLabel.setIcon(posterIcon);
        posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        posterInfoPanel.add(posterLabel);

        JLabel movieTitleLabel = new JLabel(title, JLabel.CENTER);
        movieTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        movieTitleLabel.setForeground(new Color(70, 70, 70));
        movieTitleLabel.setBackground(Color.WHITE);
        movieTitleLabel.setOpaque(true);
        movieTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        posterInfoPanel.add(movieTitleLabel);

        JLabel movieDateLabel = new JLabel(jadwal, JLabel.CENTER);
        movieDateLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        movieDateLabel.setForeground(new Color(120, 120, 120));
        movieDateLabel.setBackground(Color.WHITE);
        movieDateLabel.setOpaque(true);
        movieDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        posterInfoPanel.add(movieDateLabel);

        JLabel keteranganRefund = new JLabel("Tiket yang sudah dibeli tidak dapat dikembalikan", JLabel.CENTER);
        keteranganRefund.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        keteranganRefund.setForeground(Color.RED);
        keteranganRefund.setBackground(Color.WHITE);
        keteranganRefund.setOpaque(true);
        keteranganRefund.setAlignmentX(Component.CENTER_ALIGNMENT);
        posterInfoPanel.add(keteranganRefund);

        JLabel keteranganTukar = new JLabel("Tukarkan tiket digital ini dengan tiket fisik di lokasi bioskop",
                JLabel.CENTER);
        keteranganTukar.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        keteranganTukar.setForeground(Color.BLACK);
        keteranganTukar.setBackground(Color.WHITE);
        keteranganTukar.setAlignmentX(Component.CENTER_ALIGNMENT);
        posterInfoPanel.add(keteranganTukar);

        detailPanel2.add(posterInfoPanel, BorderLayout.SOUTH);
        // add(detailPanel2, BorderLayout.SOUTH);

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BorderLayout());
        // mainContentPanel.setBackground(new Color(245, 245, 245));

        mainContentPanel.add(detailPanel, BorderLayout.NORTH);

        mainContentPanel.add(detailPanel2, BorderLayout.CENTER);

        return mainContentPanel;
    }
        
}
