import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class SemuaTiket {
    int idAkun;
    private JFrame frame;
    private JPanel mainPanel;

    public SemuaTiket(int idAkun) {
        this.idAkun = idAkun;
        frame = new JFrame("Semua Tiket");
        frame.setSize(1440, 1080);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        ActionListener backButtonListener = e -> {
            frame.dispose();
        };

        mainPanel.add(Head.createHeaderPanel(backButtonListener), BorderLayout.NORTH);

        mainPanel.add(createMainContentPanel(), BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JScrollPane createMainContentPanel() {
        // Membuat panel utama untuk konten
        JPanel mainContentPanel = new JPanel();
        // Mengatur border dari mainContentPanel dengan padding 20px di atas
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        // Mengatur layout dari mainContentPanel menjadi BoxLayout dengan arah vertikal
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        // Mengatur warna background dari mainContentPanel menjadi BLACK
        mainContentPanel.setBackground(Color.WHITE);

        JLabel bioskopLabel = new JLabel("Tiket Bioskop");
        bioskopLabel.setFont(new Font("Arial", Font.BOLD, 24));

        bioskopLabel.setForeground(new Color(236, 157, 36, 255));
        bioskopLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel bioskopLabelPanel = new JPanel();
        bioskopLabelPanel.add(bioskopLabel);

        mainContentPanel.add(bioskopLabelPanel);
        // Menambahkan panel Movies ke mainContentPanel dengan data yang sudah
        ArrayList<tiketBioskop> tiketBioskopS = ConnectKeDB.getAllTiketBioskop(idAkun);
        for (int i = 0; i <= tiketBioskopS.size(); i += 3) {
            mainContentPanel.add(tiketBioskopPanel(tiketBioskopS, i));
        }
        if (tiketBioskopS.size() == 0) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setLayout(new BorderLayout());
            emptyPanel.setBackground(Color.WHITE);
            emptyPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            JLabel emptyLabel = new JLabel("Belum ada Tiket Bioskop yang dibeli", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 17));
            emptyLabel.setForeground(new Color(236, 157, 36, 255));
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            emptyPanel.add(emptyLabel, BorderLayout.CENTER);
            mainContentPanel.add(emptyPanel);
        }
        // Menambahkan panel series ke mainContentPanel dengan data yang sudah
        // diinputkan
        JLabel kesenianLabel = new JLabel("Tiket Kesenian");
        kesenianLabel.setFont(new Font("Arial", Font.BOLD, 24));
        kesenianLabel.setForeground(new Color(236, 157, 36, 255));
        kesenianLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel kesenianLabelPanel = new JPanel();
        kesenianLabelPanel.add(kesenianLabel);

        mainContentPanel.add(kesenianLabelPanel);
        ArrayList<tiketKonserSeni> tiketKesenianS = ConnectKeDB.getAllTiketKonserSeni(idAkun, "Kesenian");
        for (int i = 0; i <= tiketKesenianS.size(); i += 3) {
            mainContentPanel.add(tiketKonserSeniPanel(tiketKesenianS, i, "Kesenian"));
        }
        if (tiketKesenianS.size() == 0) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setLayout(new BorderLayout());
            emptyPanel.setBackground(Color.WHITE);
            emptyPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            JLabel emptyLabel = new JLabel("Belum ada Tiket Kesenian yang dibeli", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 17));
            emptyLabel.setForeground(new Color(236, 157, 36, 255));
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            emptyPanel.add(emptyLabel, BorderLayout.CENTER);
            mainContentPanel.add(emptyPanel);
        }
        JLabel konserLabel = new JLabel("Tiket Konser");
        konserLabel.setFont(new Font("Arial", Font.BOLD, 24));
        konserLabel.setForeground(new Color(236, 157, 36, 255));
        konserLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel konserLabelPanel = new JPanel();
        konserLabelPanel.add(konserLabel);

        mainContentPanel.add(konserLabelPanel);
        ArrayList<tiketKonserSeni> tiketkonserS = ConnectKeDB.getAllTiketKonserSeni(idAkun, "Konser");
        for (int i = 0; i <= tiketkonserS.size(); i += 3) {
            mainContentPanel.add(tiketKonserSeniPanel(tiketkonserS, i, "Konser"));
        }
        if (tiketkonserS.size() == 0) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setLayout(new BorderLayout());
            emptyPanel.setBackground(Color.WHITE);
            emptyPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            JLabel emptyLabel = new JLabel("Belum ada Tiket Konser yang dibeli", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 17));
            emptyLabel.setForeground(new Color(236, 157, 36, 255));
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            emptyPanel.add(emptyLabel, BorderLayout.CENTER);
            mainContentPanel.add(emptyPanel);
        }
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        // Mengatur tipe scrollbar untuk hanya muncul jika diperlukan
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // jika tidak ingin scroll

        return scrollPane;
    }

    private JPanel tiketBioskopPanel(ArrayList<tiketBioskop> tiketS, int index) {
        if (tiketS.get(0).getTitle().equals("")) {
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
        JPanel tiketBioskop = new JPanel();
        tiketBioskop.setLayout(new GridBagLayout());
        tiketBioskop.add(Box.createHorizontalStrut(70));
        int i = 0;
        for (int j = index; j < tiketS.size(); j++) {
            if (i >= 3) {
                break;
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

            JLabel namaLabel = new JLabel("Nama Pemesan: " + tiketS.get(i+index).getUsername());
            namaLabel.setFont(labelFont);
            namaLabel.setForeground(textColor);
            namaLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            namaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailPanel.add(namaLabel);

            JLabel tempatDudukLabel = new JLabel("Tempat Duduk: " + tiketS.get(i+index).getTempatDuduk());
            tempatDudukLabel.setFont(labelFont);
            tempatDudukLabel.setForeground(textColor);
            tempatDudukLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            tempatDudukLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailPanel.add(tempatDudukLabel);

            JLabel studioLabel = new JLabel("Studio: " + tiketS.get(i+index).getStudio());
            studioLabel.setFont(labelFont);
            studioLabel.setForeground(textColor);
            studioLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            studioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailPanel.add(studioLabel);

            JLabel hariLabel = new JLabel("Hari: " + tiketS.get(i+index).getHari());
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
                    new ImageIcon(tiketS.get(i+index).getImage()).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
            JLabel posterLabel = new JLabel();
            posterLabel.setIcon(posterIcon);
            posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            posterInfoPanel.add(posterLabel);

            JLabel movieTitleLabel = new JLabel(tiketS.get(i+index).getTitle(), JLabel.CENTER);
            movieTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            movieTitleLabel.setForeground(new Color(70, 70, 70));
            movieTitleLabel.setBackground(Color.WHITE);
            movieTitleLabel.setOpaque(true);
            movieTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            posterInfoPanel.add(movieTitleLabel);

            JLabel movieDateLabel = new JLabel(tiketS.get(i+index).getJadwal(), JLabel.CENTER);
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
            mainContentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

            mainContentPanel.add(detailPanel, BorderLayout.NORTH);

            mainContentPanel.add(detailPanel2, BorderLayout.CENTER);
            tiketBioskop.add(mainContentPanel);
            tiketBioskop.add(Box.createHorizontalStrut(70));

            i++;
        }

        return tiketBioskop;
    }

    private JPanel tiketKonserSeniPanel(ArrayList<tiketKonserSeni> tiketS, int index, String kategori) {
        if (tiketS.get(0).equals("")) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setLayout(new BorderLayout());
            emptyPanel.setBackground(Color.WHITE);
            emptyPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            JLabel emptyLabel = new JLabel("Belum ada Tiket " + kategori + " yang dibeli", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 17));
            emptyLabel.setForeground(new Color(70, 70, 70));
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
            emptyPanel.add(emptyLabel, BorderLayout.CENTER);

            return emptyPanel;
        }
        JPanel tiketKonserSeni = new JPanel();
        tiketKonserSeni.setLayout(new GridBagLayout());
        tiketKonserSeni.add(Box.createHorizontalStrut(70));
        int i = 0;
        for (int j = index; j < tiketS.size(); j++) {
            if (i >= 3) {
                break;
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

            JLabel namaLabel = new JLabel("Nama Pemesan: " + tiketS.get(i+index).namaLengkap);
            namaLabel.setFont(labelFont);
            namaLabel.setForeground(textColor);
            namaLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            namaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailPanel.add(namaLabel);

            JLabel emailLabel = new JLabel("Email: " + tiketS.get(i+index).email);
            emailLabel.setFont(labelFont);
            emailLabel.setForeground(textColor);
            emailLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailPanel.add(emailLabel);

            JLabel nikLabel = new JLabel("NIK: " + tiketS.get(i+index).nik);
            nikLabel.setFont(labelFont);
            nikLabel.setForeground(textColor);
            nikLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            nikLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailPanel.add(nikLabel);

            JLabel jenisTiketLabel = new JLabel("Jenis Tiket: " + tiketS.get(i+index).jenisTiket);
            jenisTiketLabel.setFont(labelFont);
            jenisTiketLabel.setForeground(textColor);
            jenisTiketLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            jenisTiketLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailPanel.add(jenisTiketLabel);

            JLabel jumlahTiketLabel = new JLabel("Jumlah Tiket: " + tiketS.get(i+index).jumlahTiket);
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
                    new ImageIcon(tiketS.get(i+index).image).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
            JLabel posterLabel = new JLabel();
            posterLabel.setIcon(posterIcon);
            posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            posterInfoPanel.add(posterLabel);

            JLabel movieTitleLabel = new JLabel(tiketS.get(i+index).title, JLabel.CENTER);
            movieTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            movieTitleLabel.setForeground(new Color(70, 70, 70));
            movieTitleLabel.setBackground(Color.WHITE);
            movieTitleLabel.setOpaque(true);
            movieTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            posterInfoPanel.add(movieTitleLabel);

            JLabel movieDateLabel = new JLabel(tiketS.get(i+index).jadwal, JLabel.CENTER);
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

            JPanel bungkusDetailPanel = new JPanel();
            bungkusDetailPanel.setLayout(new BoxLayout(bungkusDetailPanel, BoxLayout.Y_AXIS));
            bungkusDetailPanel.setBackground(Color.WHITE);

            bungkusDetailPanel.add(barcodeLabel);
            bungkusDetailPanel.add(detailPanel);
            ;

            JPanel mainContentPanel = new JPanel();
            mainContentPanel.setLayout(new BorderLayout());
            mainContentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

            mainContentPanel.add(bungkusDetailPanel, BorderLayout.NORTH);

            mainContentPanel.add(detailPanel2, BorderLayout.CENTER);
            tiketKonserSeni.add(mainContentPanel);
            tiketKonserSeni.add(Box.createHorizontalStrut(70));

            i++;
        }
        return tiketKonserSeni;
    }

    public static void main(String[] args) {
        new SemuaTiket(4);
    }
}