import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DetailMovie implements app {
    private JFrame frame;
    private JPanel mainPanel;
    Movie movie;
    int idJadwalTayang;
    boolean beliTiket = false;

    public DetailMovie(Movie movie) {
        this.movie = movie;
        frame = new JFrame("DetailMovie");
        frame.setSize(1440, 1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);

        mainPanel.add(createMainContentPanel(), BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    @Override
    public JPanel createHeaderPanel(){
        return Head.createHeaderPanel(e -> frame.dispose());
    }
    @Override
    public JScrollPane createMainContentPanel() {
        // Membuat panel utama untuk konten
        JPanel mainContentPanel = new JPanel();
        // Mengatur border dari mainContentPanel dengan padding 20px di atas
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        // Mengatur layout dari mainContentPanel menjadi BoxLayout dengan arah vertikal
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        // Mengatur warna background dari mainContentPanel menjadi putih
        mainContentPanel.setBackground(Color.WHITE);

        // Menambahkan panel Movies ke mainContentPanel dengan data yang sudah
        mainContentPanel.add(createCategoryPanel(movie));
        mainContentPanel.add(Box.createVerticalStrut(10));
        mainContentPanel.add(trailerPanel(movie));
        mainContentPanel.add(Box.createVerticalStrut(10));
        if (movie.getStatus().equals("Sedang Tayang")) {
            mainContentPanel.add(jadwal());
        }
        mainContentPanel.add(Box.createVerticalStrut(30));
        // Membungkus mainContentPanel dengan JScrollPane untuk enable scrolling
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        // Mengatur tipe scrollbar untuk hanya muncul jika diperlukan
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // jika tidak ingin scroll

        return scrollPane;
    }

    private JPanel createCategoryPanel(Movie movie) {

        JPanel gambarndetailFilm = new JPanel();
        gambarndetailFilm.setBackground(new Color(217, 217, 217, 255));

        // Menggunakan GridLayout dengan 2 baris dan 1 kolom
        gambarndetailFilm.setLayout(new GridLayout(1, 2));
        gambarndetailFilm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ImageIcon poster = new ImageIcon(movie.getImage());
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

        JLabel titleLabel = new JLabel("Title: " + movie.getTitle());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        titleLabel.setForeground(new Color(236, 157, 36, 255));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Label untuk menampilkan genre, tahun, rating, sutradara, dan deskripsi film
        JLabel genreLabel = new JLabel("Genre: " + movie.getGenre());
        genreLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        genreLabel.setForeground(new Color(236, 157, 36, 255));
        genreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel yearLabel = new JLabel("Year: " + movie.getYear());
        yearLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        yearLabel.setForeground(new Color(236, 157, 36, 255));
        yearLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel directorLabel = new JLabel("Director: " + movie.getDirector());
        directorLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        directorLabel.setForeground(new Color(236, 157, 36, 255));
        directorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel desc = new JLabel("Description");
        desc.setFont(new Font("Arial", Font.PLAIN, 25));
        desc.setForeground(new Color(236, 157, 36, 255));
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea descriptionArea = new JTextArea(movie.getDescription());
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
        detailPanel.add(yearLabel);
        detailPanel.add(Box.createVerticalStrut(3));
        detailPanel.add(directorLabel);
        detailPanel.add(Box.createVerticalStrut(3));
        detailPanel.add(desc);
        detailPanel.add(Box.createVerticalStrut(3));
        detailPanel.add(descriptionArea);

        gambarndetailFilm.add(detailPanel);
        return gambarndetailFilm;
    }

    private JPanel trailerPanel(Movie movie) {
        JPanel trailerPanel = new JPanel();
        trailerPanel.setBackground(Color.BLACK);
        trailerPanel.setLayout(new BorderLayout());

        JButton playButton = new JButton("Play Trailer");
        playButton.setFont(new Font("Arial", Font.PLAIN, 20));
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(new Color(236, 157, 36, 255));
        playButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        playButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new java.net.URI(movie.getUrlMovie()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        trailerPanel.add(playButton, BorderLayout.CENTER);

        return trailerPanel;
    }

    // Tambahkan variabel untuk menyimpan referensi ke panel jamTayang
    private JPanel jamTayangPanel;
    private JPanel lastSelectedPanel = null; // Menyimpan referensi ke panel yang terakhir dipilih
    private JPanel lastSelectedJamPanel = null;

    private JPanel jadwal() {
        JPanel jadwalMainPanel = new JPanel();
        jadwalMainPanel.setBackground(new Color(217, 217, 217, 255));
        jadwalMainPanel.setLayout(new BorderLayout());

        JPanel jadwalPanel = new JPanel();
        jadwalPanel.setBackground(new Color(217, 217, 217, 255));
        jadwalPanel.setLayout(new BoxLayout(jadwalPanel, BoxLayout.Y_AXIS));

        JLabel jadwalLabel = new JLabel("Jadwal");
        jadwalLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        jadwalLabel.setForeground(new Color(236, 157, 36, 255));
        jadwalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ArrayList<String> jadwalTanggalHari = ConnectKeDB.getTanggalHari(movie.getId());

        JPanel hariJam = new JPanel();
        hariJam.setBackground(new Color(217, 217, 217, 255));
        hariJam.setLayout(new BoxLayout(hariJam, BoxLayout.X_AXIS));
        hariJam.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));

        // Menambahkan MouseListener untuk setiap hari
        for (int j = 0; j < jadwalTanggalHari.size(); j++) {
            JPanel jadwalArea = new JPanel();
            jadwalArea.setBackground(new Color(236, 157, 36, 255)); // Warna awal
            jadwalArea.setLayout(new BoxLayout(jadwalArea, BoxLayout.Y_AXIS));
            JLabel hari = new JLabel(jadwalTanggalHari.get(j));
            hari.setFont(new Font("Arial", Font.PLAIN, 20));
            hari.setForeground(Color.WHITE);
            hari.setAlignmentX(Component.CENTER_ALIGNMENT);
            jadwalArea.add(Box.createVerticalStrut(3));
            jadwalArea.add(hari);
            jadwalArea.add(Box.createVerticalStrut(3));
            hariJam.add(jadwalArea);
            hariJam.add(Box.createHorizontalStrut(10));

            // Menambahkan MouseListener untuk mengubah latar belakang dan menampilkan jam
            // tayang
            final String selectedDay = jadwalTanggalHari.get(j);
            jadwalArea.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Ubah latar belakang jadwalArea yang dipilih
                    if (lastSelectedPanel != null) {
                        lastSelectedPanel.setBackground(new Color(236, 157, 36, 255)); // Kembalikan warna ke semula
                    }
                    jadwalArea.setBackground(new Color(47, 47, 128, 255)); // Set warna latar belakang menjadi biru
                    lastSelectedPanel = jadwalArea; // Simpan panel yang baru dipilih
                    beliTiket=false;
                    // Panggil metode untuk mendapatkan jam tayang berdasarkan hari yang dipilih
                    tampilkanJamTayang(selectedDay);
                }
            });
        }

        JLabel jamTayangLabel = new JLabel("Jam Tayang");
        jamTayangLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        jamTayangLabel.setForeground(new Color(236, 157, 36, 255));
        jamTayangLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel untuk menampilkan jam tayang, awalnya kosong
        jamTayangPanel = new JPanel();
        jamTayangPanel.setBackground(new Color(217, 217, 217, 255));
        jamTayangPanel.setLayout(new BoxLayout(jamTayangPanel, BoxLayout.X_AXIS));
        jamTayangPanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));

        // Tombol untuk membeli tiket
        ImageIcon poster = new ImageIcon("asset/tombolBeliTiket.png");
        Image posterImg = poster.getImage();
        Image posterImgScaled = posterImg.getScaledInstance(1320, 80, Image.SCALE_SMOOTH);
        ImageIcon posterScaled = new ImageIcon(posterImgScaled);
        JLabel tombolBeliTiket = new JLabel(posterScaled);
        tombolBeliTiket.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (beliTiket == true) {
                    new BeliTiketFilm(idJadwalTayang); // Menampilkan form pembelian tiket
                } else {
                    JOptionPane.showMessageDialog(frame, "Pilih Jadwal Terlebih Dahulu");
                }
            }
        });
        tombolBeliTiket.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Menambahkan semua komponen ke dalam panel
        jadwalPanel.add(jadwalLabel);
        jadwalPanel.add(hariJam);
        jadwalPanel.add(jamTayangLabel);
        jadwalPanel.add(jamTayangPanel); // Gunakan jamTayangPanel yang sudah disimpan
        jadwalPanel.add(tombolBeliTiket);
        jadwalPanel.add(Box.createVerticalStrut(10));
        jadwalMainPanel.add(jadwalPanel, BorderLayout.CENTER);

        return jadwalMainPanel;
    }

    // Metode untuk menampilkan jam tayang berdasarkan hari yang dipilih
    private void tampilkanJamTayang(String hari) {
        // Ambil daftar jam tayang berdasarkan hari yang dipilih
        ArrayList<jadwalTayang> jadwalJam = ConnectKeDB.getJamHari(hari, movie.getId());

        // Bersihkan semua komponen lama di panel jamTayang
        jamTayangPanel.removeAll();

        // Tambahkan jam tayang yang sesuai dengan hari yang dipilih
        for (int j = 0; j < jadwalJam.size(); j++) {
            JPanel jadwalArea = new JPanel();
            jadwalArea.setBackground(new Color(236, 157, 36, 255));
            jadwalArea.setLayout(new BoxLayout(jadwalArea, BoxLayout.Y_AXIS));
            JLabel jam = new JLabel(jadwalJam.get(j).getJamTanggal());
            jam.setFont(new Font("Arial", Font.PLAIN, 20));
            jam.setForeground(Color.WHITE);
            jam.setAlignmentX(Component.CENTER_ALIGNMENT);
            jadwalArea.add(Box.createVerticalStrut(3));
            jadwalArea.add(jam);
            jadwalArea.add(Box.createVerticalStrut(3));

            jamTayangPanel.add(jadwalArea);
            jamTayangPanel.add(Box.createHorizontalStrut(10));
            final int index = j;
            jadwalArea.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Ubah latar belakang jadwalArea yang dipilih
                    if (lastSelectedJamPanel != null) {
                        lastSelectedJamPanel.setBackground(new Color(236, 157, 36, 255)); // Kembalikan warna ke semula
                    }
                    jadwalArea.setBackground(new Color(47, 47, 128, 255)); // Set warna latar belakang menjadi biru
                    lastSelectedJamPanel = jadwalArea; // Simpan panel yang baru dipilih

                    beliTiket = true;
                    // Simpan idJadwalTayang yang dipilih
                    idJadwalTayang = jadwalJam.get(index).getId();
                }
            });
        }

        // Refresh panel untuk menampilkan jam tayang yang baru
        jamTayangPanel.revalidate();
        jamTayangPanel.repaint();
    }

}