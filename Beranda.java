import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Beranda extends JFrame {
    private int userId;
    private ImageIcon headerImageIcon;
    private JLabel headerImageLabel;

    public Beranda(int userId) {
        this.userId = userId;
        setTitle("SiPaket");
        setSize(1440, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create header panel with image background
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1440, 150));

        headerImageIcon = new ImageIcon("asset/HeaderBaru.jpg");
        headerImageLabel = new JLabel(headerImageIcon);
        headerImageLabel.setIcon(resizeHeaderImage(headerImageIcon, 1440, 150));
        headerImageLabel.setLayout(new BorderLayout());

        // Create right panel for profile button
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(250, 250));
        rightPanel.setOpaque(false);

        ImageIcon originalIcon = new ImageIcon("asset/profil.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon buttonIcon = new ImageIcon(scaledImage);

        JButton profileButton = new JButton(buttonIcon);
        profileButton.setPreferredSize(new Dimension(60, 60));
        profileButton.setBorderPainted(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setFocusPainted(false);

        // Add mouse listener for profile button
        profileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Profile(userId); // Pass userId to Profile frame
            }
        });

        rightPanel.add(profileButton);
        headerImageLabel.add(rightPanel, BorderLayout.EAST);
        headerPanel.add(headerImageLabel, BorderLayout.CENTER);

        // Add header panel to main frame
        add(headerPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Load custom font
        Font customFont = loadFont("asset/Poppins-Bold.ttf", 32);

        // Category panel
        JPanel kategoriPanel = createPanel("Kategori", new String[] { "Film", "Kesenian", "Konser" });
        kategoriPanel.setFont(customFont);
        // kategoriPanel.setPreferredSize(new Dimension(1440, 600));
        // kategoriPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(kategoriPanel);

        // Bought ticket panel (Initially empty)
        JLabel titleLabel = new JLabel("TIKET DIBELI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0xEC9D24));
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JPanel tiket = new JPanel();
        tiket.setLayout(new BorderLayout());
        tiket.setBackground(Color.WHITE);
        // tiket.setPreferredSize(new Dimension(1440, 600));
        tiket.add(titleLabel, BorderLayout.NORTH);

        JPanel tiketTiket = new JPanel();
        tiketTiket.setLayout(new GridBagLayout());
        tiketTiket.setBackground(Color.WHITE);

        // Create ticket panels

        JPanel tiketBioskop = new JPanel(new BorderLayout());
        tiketBioskop.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tiketBioskop.setBackground(new Color(217, 217, 217, 255));
        tiketBioskop.add(tiketBioskopPanel(userId), BorderLayout.CENTER);

        tiketTiket.add(tiketBioskop);

        JPanel tiketKesenian = new JPanel(new BorderLayout());
        tiketKesenian.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tiketKesenian.setBackground(new Color(217, 217, 217, 255));
        tiketKesenian.add(tiketKonserSeniPanel(userId, "Kesenian"), BorderLayout.CENTER);

        tiketTiket.add(Box.createHorizontalStrut(50));
        tiketTiket.add(tiketKesenian);

        JPanel tiketKonser = new JPanel(new BorderLayout());
        tiketKonser.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tiketKonser.setBackground(new Color(217, 217, 217, 255));
        tiketKonser.add(tiketKonserSeniPanel(userId, "Konser"), BorderLayout.CENTER);

        tiketTiket.add(Box.createHorizontalStrut(50));
        tiketTiket.add(tiketKonser);

        tiket.add(tiketTiket, BorderLayout.CENTER);
        // Add main panel to frame
        mainPanel.add(tiket);
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);

        JLabel tampilkanSemuaTiket = new JLabel("Tampilkan Semua Tiket", SwingConstants.CENTER);
        tampilkanSemuaTiket.setFont(new Font("Arial", Font.BOLD, 18));
        tampilkanSemuaTiket.setForeground(new Color(0x2F2F80));
        footerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SemuaTiket(userId);
            }
        });
        JPanel dalamFooterPanel = new JPanel();
        dalamFooterPanel.setBackground(new Color(0xEC9D24));
        dalamFooterPanel.setBorder(BorderFactory.createEmptyBorder(0, 500, 0, 500));
        dalamFooterPanel.add(tampilkanSemuaTiket);

        footerPanel.add(dalamFooterPanel, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(footerPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        // Mengatur tipe scrollbar untuk hanya muncul jika diperlukan
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // jika tidak ingin scroll
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Method to load custom font
    private Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Fallback font if loading custom font fails
            return new Font("Arial", Font.BOLD, (int) size);
        }
    }

    // Create panel for each category or ticket
    private JPanel createPanel(String title, String[] items) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0xEC9D24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        for (String item : items) {
            RoundedButton button = new RoundedButton(item);
            button.addActionListener(e -> {
                // Buka frame sesuai kategori
                switch (item) {
                    case "Film":
                        new Bioskop();
                        break;
                    case "Kesenian":
                        new DaftarKesenian();
                        break;
                    case "Konser":
                        new DaftarKonser();
                        break;
                    default:
                        break;
                }
            });
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        return panel;
    }

    // Resize header image
    private ImageIcon resizeHeaderImage(ImageIcon originalIcon, int targetWidth, int targetHeight) {
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    // Rounded button class
    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setFont(new Font("Arial", Font.BOLD, 18));
            setPreferredSize(new Dimension(400, 165));
            setBackground(new Color(0x2F2F80));
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Set button background color
            g2.setColor(getBackground());

            // Draw button with rounded corners (20px)
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));

            // Set button text color
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getAscent();

            // Center the text in the button
            g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 5);
            g2.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            // Adjust click area for rounded button shape
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
            return shape.contains(x, y);
        }
    }

    private JPanel tiketBioskopPanel(int idAkun) {

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

    private JPanel tiketKonserSeniPanel(int idAkun, String kategori) {

        String query = "SELECT tks.nama AS namaLengkap, tks.NIK, tks.email, tks.jumlah_tiket , tks.jenis_tiket AS jenisTiket, CASE WHEN tks.kategori = 'Konser' THEN k.date WHEN tks.kategori = 'Kesenian' THEN ks.date END AS jadwal, CASE WHEN tks.kategori = 'Konser' THEN k.image WHEN tks.kategori = 'Kesenian' THEN ks.image END AS image, CASE WHEN tks.kategori = 'Konser' THEN k.title WHEN tks.kategori = 'Kesenian' THEN ks.title END AS title FROM tiketkonserseni tks LEFT JOIN konser k ON tks.kategori = 'Konser' AND tks.id_acara = k.id LEFT JOIN kesenian ks ON tks.kategori = 'Kesenian' AND tks.id_acara = ks.id WHERE tks.id_user = " + idAkun + " AND tks.kategori = '" + kategori + "'";
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

    public static void main(String[] args) {
        new Beranda(0);
    }
}