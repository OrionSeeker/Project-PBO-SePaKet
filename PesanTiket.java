import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class PesanTiket extends JFrame {
    // private int userId;

    public PesanTiket(String title, String date, String img) {
        setTitle("SePaKet: Pesan Tiket Di Sini");
        setSize(1440, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        Font customFont18 = loadFont("asset/Poppins-Bold.ttf", 18);
        Font customFont28 = loadFont("asset/Poppins-Bold.ttf", 28);
        Font customFont24 = loadFont("asset/Poppins-Bold.ttf", 24);

        ActionListener backButtonListener = e -> {
            // Entar isi ini back buttonnay ke mana
        };

        add(Head.createHeaderPanel(backButtonListener), BorderLayout.NORTH);

        ImageIcon backIcon = new ImageIcon(new ImageIcon("asset/backButtonTakTrans.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JButton backBtn = new JButton(backIcon);
        backBtn.setBorder(BorderFactory.createEmptyBorder(0, 10, 100, 200));
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusable(false);

        // JPanel backBtnPanel = new JPanel(new BorderLayout());
        // backBtnPanel.setOpaque(false);
        // backBtnPanel.add(backBtn, BorderLayout.WEST);
        // headerLabel.add(backBtnPanel, BorderLayout.WEST);

        // Main panel ini sebenernya isi utamanya, yang ada poster sama form untuk pesen
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // detail konser ini yang kiri
        JPanel detailKonser = new JPanel();
        detailKonser.setLayout(new BoxLayout(detailKonser, BoxLayout.Y_AXIS));
        detailKonser.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        detailKonser.setBackground(Color.WHITE);

        ImageIcon posterIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH));
        JLabel posterLabel = new JLabel(posterIcon);
        posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel judulLabel = new JLabel(title, SwingConstants.CENTER);
        judulLabel.setFont(customFont24);
        judulLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tanggalLabel = new JLabel(date, SwingConstants.CENTER);
        tanggalLabel.setFont(customFont18);
        tanggalLabel.setForeground(Color.GRAY);
        tanggalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        detailKonser.add(Box.createRigidArea(new Dimension(0, 20)));
        detailKonser.add(posterLabel);
        detailKonser.add(Box.createRigidArea(new Dimension(0, 10)));
        detailKonser.add(judulLabel);
        detailKonser.add(Box.createRigidArea(new Dimension(0, 3)));
        detailKonser.add(tanggalLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(detailKonser, gbc);

        // panel pesan tiket ini yang kanan
        JPanel pesanTiketPanel = new JPanel(new GridBagLayout());
        pesanTiketPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        pesanTiketPanel.setBackground(Color.WHITE);
        pesanTiketPanel.setPreferredSize(new Dimension(1000, 1200));

        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(15, 15, 15, 15);
        formGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel formLabel = new JLabel("Pemesanan Tiket", SwingConstants.CENTER);
        formLabel.setFont(customFont28);
        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formGbc.gridwidth = 2;
        formGbc.anchor = GridBagConstraints.CENTER;
        pesanTiketPanel.add(formLabel, formGbc);

        JLabel namaLabel = new JLabel("Nama Lengkap Sesuai KTP");
        namaLabel.setFont(customFont18);
        formGbc.gridx = 0;
        formGbc.gridy = 1;
        formGbc.gridwidth = 1;
        pesanTiketPanel.add(namaLabel, formGbc);

        JTextField namaField = new JTextField(20);
        namaField.setFont(customFont18);
        formGbc.gridx = 1;
        pesanTiketPanel.add(namaField, formGbc);

        JLabel NIKLabel = new JLabel("NIK");
        NIKLabel.setFont(customFont18);
        formGbc.gridx = 0;
        formGbc.gridy = 2;
        pesanTiketPanel.add(NIKLabel, formGbc);

        JTextField NIKField = new JTextField(20);
        NIKField.setFont(customFont18);
        formGbc.gridx = 1;
        pesanTiketPanel.add(NIKField, formGbc);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(customFont18);
        formGbc.gridx = 0;
        formGbc.gridy = 3;
        pesanTiketPanel.add(emailLabel, formGbc);

        JTextField emailField = new JTextField(20);
        emailField.setFont(customFont18);
        formGbc.gridx = 1;
        pesanTiketPanel.add(emailField, formGbc);

        JLabel phoneLabel = new JLabel("Nomor Telpon");
        phoneLabel.setFont(customFont18);
        formGbc.gridx = 0;
        formGbc.gridy = 4;
        pesanTiketPanel.add(phoneLabel, formGbc);

        JTextField phoneField = new JTextField(20);
        phoneField.setFont(customFont18);
        formGbc.gridx = 1;
        pesanTiketPanel.add(phoneField, formGbc);

        JLabel jenisTiketLabel = new JLabel("Jenis Tiket");
        jenisTiketLabel.setFont(customFont18);
        formGbc.gridx = 0;
        formGbc.gridy = 5;
        pesanTiketPanel.add(jenisTiketLabel, formGbc);

        JComboBox<String> jenisTiketField = new JComboBox<>(new String[]{"Biasa", "Istimewa", "raja"});
        jenisTiketField.setFont(customFont18);
        formGbc.gridx = 1;
        pesanTiketPanel.add(jenisTiketField, formGbc);

        JLabel jumlahLabel = new JLabel("Jumlah Tiket");
        jumlahLabel.setFont(customFont18);
        formGbc.gridx = 0;
        formGbc.gridy = 6;
        pesanTiketPanel.add(jumlahLabel, formGbc);

        JSpinner jumlahField = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        jumlahField.setFont(customFont18);
        formGbc.gridx = 1;
        pesanTiketPanel.add(jumlahField, formGbc);

        JButton pesanBtn = new JButton("Pesan");
        pesanBtn.setFont(customFont18);
        formGbc.gridx = 0;
        formGbc.gridy = 7;
        formGbc.gridwidth = 2;
        formGbc.anchor = GridBagConstraints.CENTER;
        pesanTiketPanel.add(pesanBtn, formGbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(pesanTiketPanel, gbc);

        setVisible(true);


        pesanBtn.addActionListener(e -> {
            JFrame frameKonfirm = new HalamanPembayaran(namaField.getText(), NIKField.getText(), emailField.getText(), (String) jenisTiketField.getSelectedItem(), (int) jumlahField.getValue(), title, date, img);
            frameKonfirm.setVisible(true);
        });
    }

    // public static void main(String[] args) {
    //     new PesanTiket();
    // }

    // Ini buat pake font poppins
    private Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.BOLD, (int) size);
        }
    }
}
