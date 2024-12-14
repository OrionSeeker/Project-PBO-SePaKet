import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DetailKonser {
    private JFrame frame;
    private JPanel mainPanel;
    Konser konser;
    int idJadwalTayang;
    boolean beliTiket = false;

    public DetailKonser(Konser konser) {
        this.konser = konser;
        frame = new JFrame("Detail Konser");
        frame.setSize(1440, 1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());

        ActionListener backButtonListener = e -> {
            // Entar isi ini back buttonnay ke mana
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
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        // Mengatur layout dari mainContentPanel menjadi BoxLayout dengan arah vertikal
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        // Mengatur warna background dari mainContentPanel menjadi putih
        mainContentPanel.setBackground(Color.WHITE);
    
        // Menambahkan panel Movies ke mainContentPanel dengan data yang sudah
        mainContentPanel.add(createCategoryPanel(konser));
        mainContentPanel.add(Box.createVerticalStrut(10));
        mainContentPanel.add(Box.createVerticalStrut(30));
    
        // Panel untuk tombol Pesan Tiket
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
    
        // Membuat tombol Pesan Tiket
        JButton pesanTiketButton = new JButton("Pesan Tiket");
        pesanTiketButton.setPreferredSize(new Dimension(1300, 50));
        pesanTiketButton.setFont(new Font("Arial", Font.PLAIN, 20));
        pesanTiketButton.setBackground(new Color(236, 157, 36));
        pesanTiketButton.setForeground(Color.WHITE);
        pesanTiketButton.addActionListener(e -> {
            new PesanTiket(konser.getTitle(), konser.getDate(), konser.getImage());
        });
    
        buttonPanel.add(pesanTiketButton);  // Menambahkan tombol ke panel tombol
    
        // Menambahkan panel tombol ke mainContentPanel
        mainContentPanel.add(Box.createVerticalStrut(20));  // Memberikan ruang sebelum panel tombol
        mainContentPanel.add(buttonPanel);  // Menambahkan panel tombol ke panel utama
    
        // Membungkus mainContentPanel dengan JScrollPane untuk enable scrolling
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        // Mengatur tipe scrollbar untuk hanya muncul jika diperlukan
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // jika tidak ingin scroll
    
        return scrollPane;
    }
    

    private JPanel createCategoryPanel(Konser konser) {

        JPanel gambarndetailFilm = new JPanel();
        gambarndetailFilm.setBackground(new Color(217, 217, 217, 255));

        // Menggunakan GridLayout dengan 2 baris dan 1 kolom
        gambarndetailFilm.setLayout(new GridLayout(1, 2));
        gambarndetailFilm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ImageIcon poster = new ImageIcon(konser.getImage());
        Image posterImg = poster.getImage();

        // Mengubah ukuran poster sesuai dengan ukuran yang diinginkan
        Image posterImgScaled = posterImg.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
        ImageIcon posterScaled = new ImageIcon(posterImgScaled);

        // Membuat JLabel untuk menampilkan poster
        JLabel posterLabel = new JLabel(posterScaled);

        gambarndetailFilm.add(posterLabel);
        // Panel untuk detail film di sebelah kanan
        JPanel detailPanel = new JPanel();
        detailPanel.setBackground(new Color(217, 217, 217, 255));
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Title: " + konser.getTitle());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        titleLabel.setForeground(new Color(236, 157, 36, 255));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel genreLabel = new JLabel("Date: " + konser.getDate());
        genreLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        genreLabel.setForeground(new Color(236, 157, 36, 255));
        genreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel desc = new JLabel("Description");
        desc.setFont(new Font("Arial", Font.PLAIN, 25));
        desc.setForeground(new Color(236, 157, 36, 255));
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea descriptionArea = new JTextArea(konser.getDescription());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionArea.setForeground(new Color(236, 157, 36, 255));
        descriptionArea.setBackground(new Color(217, 217, 217, 255));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Menambahkan komponen ke detailPanel
        detailPanel.add(Box.createVerticalStrut(10));
        detailPanel.add(titleLabel);
        detailPanel.add(Box.createVerticalStrut(3));
        detailPanel.add(genreLabel);
        detailPanel.add(Box.createVerticalStrut(3));
        detailPanel.add(desc);
        detailPanel.add(Box.createVerticalStrut(3));
        detailPanel.add(descriptionArea);

        gambarndetailFilm.add(detailPanel);
        return gambarndetailFilm;
    }

}