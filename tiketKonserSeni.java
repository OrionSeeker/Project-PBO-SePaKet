import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class tiketKonserSeni extends tiket {
    String namaLengkap = "";
    String nik = "";
    String email = "";
    String jenisTiket = "";
    String jumlahTiket = "";

    public tiketKonserSeni(String namaLengkap, String nik, String email, String jenisTiket, String jumlahTiket,
            String jadwal, String image, String title, int iduser) {
        super(iduser,title, jadwal, image);
        this.namaLengkap = namaLengkap;
        this.nik = nik;
        this.email = email;
        this.jenisTiket = jenisTiket;
        this.jumlahTiket = jumlahTiket;
    }

    public String getNamaLengkap() {
        return this.namaLengkap;
    }

    public String getNik() {
        return this.nik;
    }

    public String getEmail() {
        return this.email;
    }

    public String getJenisTiket() {
        return this.jenisTiket;
    }

    public String getJumlahTiket() {
        return this.jumlahTiket;
    }

    @Override
    public JPanel tiketPanel(int idAkun, String kategori, int id) {
        String query = "SELECT tks.nama AS namaLengkap, tks.NIK, tks.email, tks.jumlah_tiket , tks.jenis_tiket AS jenisTiket, CASE WHEN tks.kategori = 'Konser' THEN k.date WHEN tks.kategori = 'Kesenian' THEN ks.date END AS jadwal, CASE WHEN tks.kategori = 'Konser' THEN k.image WHEN tks.kategori = 'Kesenian' THEN ks.image END AS image, CASE WHEN tks.kategori = 'Konser' THEN k.title WHEN tks.kategori = 'Kesenian' THEN ks.title END AS title FROM tiketkonserseni tks LEFT JOIN konser k ON tks.kategori = 'Konser' AND tks.id_acara = k.id LEFT JOIN kesenian ks ON tks.kategori = 'Kesenian' AND tks.id_acara = ks.id WHERE tks.id_user = "
                + idAkun + " AND tks.kategori = '" + kategori + "'";
        String namaLengkap = "";
        String nik = "";
        String email = "";
        String jenisTiket = "";
        String jumlahTiket = "";
        String jadwal = "";
        String image = "";
        String title = "";

        // Membuat koneksi ke database
        try (Connection conn = ConnectKeDB.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            // Menambahkan data lagu ke ArrayList
            while (rs.next()) {
                namaLengkap = rs.getString("namaLengkap");
                nik = rs.getString("NIK");
                email = rs.getString("email");
                jenisTiket = rs.getString("jenisTiket");
                jumlahTiket = rs.getString("jumlah_tiket");
                jadwal = rs.getString("jadwal");
                image = rs.getString("image");
                title = rs.getString("title");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (title.equals("")) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setLayout(new BorderLayout());
            emptyPanel.setBackground(Color.WHITE);
            emptyPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            JLabel emptyLabel = new JLabel("Belum ada Tiket " + kategori + " yang dibeli", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 17));
            emptyLabel.setForeground(new Color(236, 157, 36, 255));
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
            emptyPanel.add(emptyLabel, BorderLayout.CENTER);

            return emptyPanel;
        }
        ImageIcon gambarBarcode = new ImageIcon(
                new ImageIcon("./Asset/barcode.png").getImage().getScaledInstance(320, 150, Image.SCALE_SMOOTH));
        JLabel barcodeLabel = new JLabel();
        barcodeLabel.setIcon(gambarBarcode);

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

        JLabel namaLabel = new JLabel("Nama Pemesan: " + namaLengkap);
        namaLabel.setFont(labelFont);
        namaLabel.setForeground(textColor);
        namaLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        namaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.add(namaLabel);

        JLabel emailLabel = new JLabel("Email: " + email);
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(textColor);
        emailLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.add(emailLabel);

        JLabel nikLabel = new JLabel("NIK: " + nik);
        nikLabel.setFont(labelFont);
        nikLabel.setForeground(textColor);
        nikLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        nikLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.add(nikLabel);

        JLabel jenisTiketLabel = new JLabel("Jenis Tiket: " + jenisTiket);
        jenisTiketLabel.setFont(labelFont);
        jenisTiketLabel.setForeground(textColor);
        jenisTiketLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jenisTiketLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.add(jenisTiketLabel);

        JLabel jumlahTiketLabel = new JLabel("Jumlah Tiket: " + jumlahTiket);
        jumlahTiketLabel.setFont(labelFont);
        jumlahTiketLabel.setForeground(textColor);
        jumlahTiketLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jumlahTiketLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.add(jumlahTiketLabel);

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

        JLabel keteranganTukar = new JLabel("Tukarkan tiket digital ini dengan tiket fisik di lokasi acara",
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
        JPanel bungkusDetailPanel = new JPanel();
        bungkusDetailPanel.setLayout(new BoxLayout(bungkusDetailPanel, BoxLayout.Y_AXIS));
        bungkusDetailPanel.setBackground(Color.WHITE);

        bungkusDetailPanel.add(barcodeLabel);
        bungkusDetailPanel.add(detailPanel);
        mainContentPanel.add(bungkusDetailPanel, BorderLayout.NORTH);

        mainContentPanel.add(detailPanel2, BorderLayout.CENTER);

        return mainContentPanel;

    }

}
