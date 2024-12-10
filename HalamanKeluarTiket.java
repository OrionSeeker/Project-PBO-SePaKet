import javax.swing.*;
import java.awt.*;

public class HalamanKeluarTiket extends JFrame {

    public HalamanKeluarTiket(String nama, String NIK, String email, String jenisTiket, int jumlahTiket) {
        setTitle("Tiket Contoh aja siii...");
        setSize(360, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel barcodePanel = new JPanel();
        // barcodePanel.setBackground(new Color(245, 245, 245));
        barcodePanel.setBackground(Color.WHITE);
        barcodePanel.setOpaque(true);
        barcodePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        barcodePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ImageIcon gambarBarcode = new ImageIcon(new ImageIcon("./Asset/barcode.png").getImage().getScaledInstance(320, 150, Image.SCALE_SMOOTH));
        JLabel barcodeLabel = new JLabel();
        barcodeLabel.setIcon(gambarBarcode);
        barcodePanel.add(barcodeLabel);

        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setBackground(Color.WHITE);
        detailPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        detailPanel.setPreferredSize(new Dimension(340, 400));
        detailPanel.setMaximumSize(new Dimension(340, 400));

        JLabel titleLabel = new JLabel("Detail Pemesanan Tiket");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(70, 70, 70));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 0));
        detailPanel.add(titleLabel);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color textColor = new Color(60, 60, 60);

        JLabel namaLabel = new JLabel("Nama Pemesan: " + nama);
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

        JLabel nikLabel = new JLabel("NIK: " + NIK);
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

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(245, 245, 245));
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        footerPanel.add(detailPanel, BorderLayout.CENTER);

        add(barcodePanel, BorderLayout.NORTH);
        // add(footerPanel, BorderLayout.CENTER);

        JPanel detailPanel2 = new JPanel();
        detailPanel2.setLayout(new BoxLayout(detailPanel2, BoxLayout.Y_AXIS));
        detailPanel2.setBackground(Color.WHITE);
        detailPanel2.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        detailPanel2.setPreferredSize(new Dimension(340, 400));
        detailPanel2.setMaximumSize(new Dimension(340, 400));

        JPanel posterInfoPanel = new JPanel();
        posterInfoPanel.setLayout(new BoxLayout(posterInfoPanel, BoxLayout.Y_AXIS));
        // posterInfoPanel.setBackground(new Color(245, 245, 245));
        posterInfoPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));
        posterInfoPanel.setOpaque(true);
        posterInfoPanel.setBackground(Color.WHITE);

        ImageIcon posterIcon = new ImageIcon(new ImageIcon("./Asset/contohPosterPesanTiket.jpg").getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
        JLabel posterLabel = new JLabel();
        posterLabel.setIcon(posterIcon);
        posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        posterInfoPanel.add(posterLabel);

        JLabel movieTitleLabel = new JLabel("Logbook 3 SIh ni", JLabel.CENTER);
        movieTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        movieTitleLabel.setForeground(new Color(70, 70, 70));
        movieTitleLabel.setBackground(Color.WHITE);
        movieTitleLabel.setOpaque(true);
        movieTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        posterInfoPanel.add(movieTitleLabel);

        JLabel movieDateLabel = new JLabel("7 Desember 2024", JLabel.CENTER);
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

        JLabel keteranganTukar = new JLabel("Tukarkan tiket digital ini dengan tiket fisik di lokasi acara", JLabel.CENTER);
        keteranganTukar.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        keteranganTukar.setForeground(Color.BLACK);
        keteranganTukar.setBackground(Color.WHITE);
        keteranganTukar.setOpaque(true);
        keteranganTukar.setAlignmentX(Component.CENTER_ALIGNMENT);
        posterInfoPanel.add(keteranganTukar);

        detailPanel2.add(posterInfoPanel, BorderLayout.SOUTH);
        // add(detailPanel2, BorderLayout.SOUTH);

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        // mainContentPanel.setBackground(new Color(245, 245, 245));

        mainContentPanel.add(footerPanel);

        mainContentPanel.add(detailPanel2);

        add(mainContentPanel, BorderLayout.CENTER);


        setVisible(true);
    }
}
