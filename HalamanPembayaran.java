import javax.swing.*;
import java.awt.*;

public class HalamanPembayaran extends JFrame {

    public HalamanPembayaran(String nama, String NIK, String email, String jenisTiket, int jumlahTiket) {

        setTitle("Pembayaran Tiket");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel judulLabel = new JLabel("Detail Pembayaran");
        judulLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(judulLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel namaLabel = new JLabel("Nama Lengkap:");
        namaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(namaLabel, gbc);

        JLabel namaValue = new JLabel(nama);
        namaValue.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        mainPanel.add(namaValue, gbc);

        JLabel NIKLabel = new JLabel("NIK:");
        NIKLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(NIKLabel, gbc);

        JLabel NIKValue = new JLabel(NIK);
        NIKValue.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        mainPanel.add(NIKValue, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(emailLabel, gbc);

        JLabel emailValue = new JLabel(email);
        emailValue.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        mainPanel.add(emailValue, gbc);

        JLabel jenisTiketLabel = new JLabel("Jenis Tiket:");
        jenisTiketLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(jenisTiketLabel, gbc);

        JLabel jenisTiketValue = new JLabel(jenisTiket);
        jenisTiketValue.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        mainPanel.add(jenisTiketValue, gbc);

        JLabel jumlahTiketLabel = new JLabel("Jumlah Tiket:");
        jumlahTiketLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(jumlahTiketLabel, gbc);

        JLabel jumlahTiketValue = new JLabel(String.valueOf(jumlahTiket));
        jumlahTiketValue.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        mainPanel.add(jumlahTiketValue, gbc);

        JLabel totalHargaLabel = new JLabel("Total Harga:");
        totalHargaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(totalHargaLabel, gbc);

        int hargaPerTiket = getHargaTiket(jenisTiket);
        int totalHarga = hargaPerTiket * jumlahTiket;
        JLabel totalHargaValue = new JLabel("Rp " + totalHarga);
        totalHargaValue.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        mainPanel.add(totalHargaValue, gbc);

        JButton konfirBtn = new JButton("Konfirmasi Pembayaran");
        konfirBtn.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        konfirBtn.addActionListener(e -> {
            JFrame tiket = new HalamanKeluarTiket(nama, NIK, email, jenisTiket, jumlahTiket);
            tiket.setVisible(true);
        });
        mainPanel.add(konfirBtn, gbc);

        setVisible(true);
    }

    private int getHargaTiket(String jenisTiket) {
        switch (jenisTiket.toLowerCase()) {
            case "biasa":
                return 50000;
            case "istimewa":
                return 100000;
            case "raja":
                return 200000;
            default:
                return 0;
        }
    }
}
