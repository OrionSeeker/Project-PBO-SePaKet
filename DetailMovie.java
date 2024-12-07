import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DetailMovie {
    private JFrame frame;
    private JPanel mainPanel;

    public void launchApp() {
        frame = new JFrame("DetailMovie");
        frame.setSize(1440, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);

        mainPanel.add(createMainContentPanel(), BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createHeaderPanel() {
        // Load and scale background image
        ImageIcon icon = new ImageIcon("asset/headerBaru.jpg");
        // Mengambil gambar dari icon
        Image img = icon.getImage();
        // Mengubah ukuran gambar agar sesuai keinginan
        Image imgScaled = img.getScaledInstance(1440, 150, Image.SCALE_SMOOTH);
        // Mengubah gambar menjadi icon
        ImageIcon iconScaled = new ImageIcon(imgScaled);
        // Membuat label dengan iconScaled
        JLabel headerLabel = new JLabel(iconScaled);
        // Mengatur layout dari headerLabel menjadi BorderLayout
        headerLabel.setLayout(new BorderLayout());

        ImageIcon profile = new ImageIcon("Asset/Profil.png");
        Image imgProfile = profile.getImage();
        Image imgProfileScaled = imgProfile.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon profileScaled = new ImageIcon(imgProfileScaled);

        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setOpaque(false);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
        JLabel profileLabel = new JLabel(profileScaled);
        profileLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        profilePanel.add(profileLabel, BorderLayout.EAST);

        headerLabel.add(profilePanel, BorderLayout.EAST);

        profileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showProfilePage();
            }
        });

        JPanel headerPanel = new JPanel(new BorderLayout());
        // Menambahkan headerLabel ke headerPanel
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        // Mengembalikan headerPanel

        return headerPanel;
    }

    // Fungsi untuk menampilkan profile page
    private void showProfilePage() {
        // Menghapus semua komponen dari mainPanel
        mainPanel.removeAll();
        // Mengatur layout dari mainPanel menjadi BorderLayout
        mainPanel.setLayout(new BorderLayout());

        // Panel utama untuk profile page
        JPanel topPanel = new JPanel(new BorderLayout());
        // Mengatur warna background dari topPanel menjadi DARK_GRAY
        topPanel.setBackground(new Color(47, 47, 128, 255));

        // Sama seperti penjelasan sebelumnya tapi ini untuk back button
        ImageIcon backIcon = new ImageIcon("asset/backButtonOren.png");
        Image backImg = backIcon.getImage();
        Image backImgScaled = backImg.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon backIconScaled = new ImageIcon(backImgScaled);

        // Membuat back button
        JButton backButton = new JButton(backIconScaled);
        // Mengatur border dari backButton dengan padding 20px di kiri
        backButton.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
        // Mengatur background dari backButton menjadi transparan
        backButton.setContentAreaFilled(false);
        // Mengatur border dari backButton menjadi tidak terlihat
        backButton.setBorderPainted(false);
        // Mengatur focus dari backButton menjadi tidak terlihat
        backButton.setFocusPainted(false);
        // Menambahkan action listener untuk backButton agar dapat kembali ke mainPanel
        backButton.addActionListener(e -> goBackToMainPanel());

        // Menambahkan backButton ke topPanel di bagian barat
        topPanel.add(backButton, BorderLayout.WEST);

        // Panel untuk menampilkan profile information
        JPanel centerPanel = new JPanel();
        // Mengatur warna background dari centerPanel menjadi DARK_GRAY
        centerPanel.setBackground(new Color(47, 47, 128, 255));
        // Mengatur layout dari centerPanel menjadi BoxLayout dengan arah vertikal
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Sama seperti penjelasan sebelumnya tapi ini untuk foto profile
        ImageIcon profile = new ImageIcon("asset/Profil.png");
        Image img = profile.getImage();
        Image imgScaled = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon profileScaled = new ImageIcon(imgScaled);
        JLabel profileImage = new JLabel(profileScaled);
        profileImage.setBorder(BorderFactory.createEmptyBorder(100, 0, 50, 0));
        profileImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Untuk menampilkan nama
        JLabel nameLabel = new JLabel("Username ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Untuk menampilkan id
        JLabel idLabel = new JLabel("Edit Profile");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        idLabel.setForeground(Color.WHITE);
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Menambahkan event pada label "Edit Profile" agar membuka jendela edit
        idLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showEditProfileWindow();
            }
        });

        // Untuk menambahkan spasi antara komponen
        centerPanel.add(Box.createVerticalStrut(20));
        // Menambahkan komponen profileImage ke centerPanel
        centerPanel.add(profileImage);
        // Untuk menambahkan spasi antara komponen
        centerPanel.add(Box.createVerticalStrut(10));
        // Menambahkan komponen nameLabel ke centerPanel
        centerPanel.add(nameLabel);
        // Menambahkan komponen idLabel ke centerPanel
        centerPanel.add(idLabel);

        // Menambahkan topPanel dan centerPanel ke mainPanel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Menghitung ulang tata letak komponen pada mainPanel setelah ada perubahan
        mainPanel.revalidate();
        // Menggambar ulang mainPanel agar perubahan terlihat di layar
        mainPanel.repaint();
    }

    private void showEditProfileWindow() {
        // Membuat jendela baru untuk Edit Profile
        JFrame editProfileFrame = new JFrame("Edit Profile");
        editProfileFrame.setSize(300, 200); // Ukuran frame yang kecil
        editProfileFrame.setLocationRelativeTo(null); // Agar muncul di tengah layar
        editProfileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel untuk form input username dan password
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));

        // Username field
        JTextField usernameField = new JTextField(20);
        usernameField.setText("Username"); // Contoh default text
        editPanel.add(new JLabel("Username:"));
        editPanel.add(usernameField);

        // Password field
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setText("Password"); // Contoh default text
        editPanel.add(new JLabel("Password:"));
        editPanel.add(passwordField);

        // Button untuk menyimpan perubahan
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // Simpan atau proses perubahan username dan password di sini
            System.out.println("Updated Username: " + username);
            System.out.println("Updated Password: " + password);
            // Tutup frame setelah perubahan disimpan
            editProfileFrame.dispose();
        });

        editPanel.add(saveButton);

        // Menambahkan panel ke jendela
        editProfileFrame.add(editPanel);
        editProfileFrame.setVisible(true);
    }

    // Fungsi untuk kembali ke panel utama
    private void goBackToMainPanel() {
        // Menghapus semua komponen dari mainPanel
        mainPanel.removeAll();
        // Menambahkan headerPanel ke mainPanel
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        // Menambahkan konten utama (Movies & Series) ke mainPanel
        mainPanel.add(createMainContentPanel(), BorderLayout.CENTER);
        // Menghitung ulang tata letak komponen pada mainPanel setelah ada perubahan
        mainPanel.revalidate();
        // Menggambar ulang mainPanel agar perubahan terlihat di layar
        mainPanel.repaint();

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
        Movie movie1 = new Movie("Moana 2", "......", 1994, ".....",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia quam sit amet turpis aliquam, a dignissim orci sollicitudin. Nulla facilisi. Integer id massa sit amet justo mollis convallis ac vel ligula. Proin ullamcorper metus id sem fringilla, sed tempor odio eleifend. Aliquam erat volutpat. Sed eget libero sed dui varius feugiat. Sed tristique quam at ipsum convallis, at cursus neque cursus. Integer ac turpis ac purus interdum bibendum. Vivamus scelerisque metus in dolor cursus, vel pharetra orci malesuada. Nulla ac eros in urna scelerisque scelerisque vel nec ante.",
                "asset/poster/moana2.jpg", "asset/shawshank_redemption.mp4");
        mainContentPanel.add(createCategoryPanel(movie1));
        mainContentPanel.add(Box.createVerticalStrut(10));
        mainContentPanel.add(trailerPanel(movie1));
        mainContentPanel.add(Box.createVerticalStrut(10));
        mainContentPanel.add(jadwal());
        mainContentPanel.add(Box.createVerticalStrut(30));
        // Membungkus mainContentPanel dengan JScrollPane untuk enable scrolling
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        // Mengatur tipe scrollbar untuk hanya muncul jika diperlukan
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // jika tidak ingin scroll
                                                                                         // horizontal

        // Mengembalikan scrollPane sebagai panel utama
        return scrollPane;
    }

    private JPanel createCategoryPanel(Movie movie) {

        JPanel gambarndetailFilm = new JPanel();
        gambarndetailFilm.setBackground(Color.GRAY);

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
        detailPanel.setBackground(Color.gray);
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Title: " + movie.getTitle());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        titleLabel.setForeground(new Color(236, 157, 36, 255));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Label untuk menampilkan genre, tahun, rating, sutradara, dan deskripsi film
        JLabel genreLabel = new JLabel("Genre: " + movie.getGenre());
        genreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        genreLabel.setForeground(new Color(236, 157, 36, 255));
        genreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel yearLabel = new JLabel("Year: " + movie.getYear());
        yearLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        yearLabel.setForeground(new Color(236, 157, 36, 255));
        yearLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel directorLabel = new JLabel("Director: " + movie.getDirector());
        directorLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        directorLabel.setForeground(new Color(236, 157, 36, 255));
        directorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel desc = new JLabel("Description");
        desc.setFont(new Font("Arial", Font.PLAIN, 30));
        desc.setForeground(new Color(236, 157, 36, 255));
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea descriptionArea = new JTextArea(movie.getDescription());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionArea.setForeground(new Color(236, 157, 36, 255));
        descriptionArea.setBackground(Color.GRAY);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Menambahkan komponen ke detailPanel
        detailPanel.add(Box.createVerticalStrut(10));
        detailPanel.add(titleLabel);
        detailPanel.add(genreLabel);
        detailPanel.add(yearLabel);
        // detailPanel.add(ratingLabel);
        detailPanel.add(directorLabel);
        detailPanel.add(Box.createVerticalStrut(10));
        detailPanel.add(desc);
        detailPanel.add(descriptionArea);

        gambarndetailFilm.add(detailPanel);
        return gambarndetailFilm;
    }

    private JPanel trailerPanel(Movie movie) {
        JPanel trailerPanel = new JPanel();
        trailerPanel.setBackground(Color.BLACK);
        trailerPanel.setLayout(new BorderLayout());

        JButton playButton = new JButton("Play Trailer");
        playButton.setFont(new Font("Arial", Font.PLAIN, 14));
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(Color.RED);
        playButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        playButton.addActionListener(e -> {
            // Play trailer here
            System.out.println("Playing trailer...");
        });

        trailerPanel.add(playButton, BorderLayout.CENTER);

        return trailerPanel;
    }

    private JPanel jadwal() {
        JPanel jadwalMainPanel = new JPanel();
        jadwalMainPanel.setBackground(Color.GRAY);
        jadwalMainPanel.setLayout(new BorderLayout());

        JPanel jadwalPanel = new JPanel();
        jadwalPanel.setBackground(Color.GRAY);
        jadwalPanel.setLayout(new BoxLayout(jadwalPanel, BoxLayout.Y_AXIS));

        JLabel jadwalLabel = new JLabel("Jadwal");
        jadwalLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        jadwalLabel.setForeground(new Color(236, 157, 36, 255));
        jadwalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ArrayList<String> jadwalTanggal = new ArrayList<>();
        ArrayList<String> jadwalHari = new ArrayList<>();
        jadwalTanggal.add("Senin");
        jadwalHari.add("12 Juli");
        jadwalTanggal.add("Selasa");
        jadwalHari.add("13 Juli");
        jadwalTanggal.add("Rabu");
        jadwalHari.add("14 Juli");
        jadwalTanggal.add("Kamis");
        jadwalHari.add("15 Juli");
        jadwalTanggal.add("Jumat");
        jadwalHari.add("16 Juli");
        jadwalTanggal.add("Sabtu");
        jadwalHari.add("17 Juli");
        jadwalTanggal.add("Minggu");
        jadwalHari.add("18 Juli");

        JPanel hariJam = new JPanel();
        hariJam.setBackground(Color.GRAY);
        hariJam.setLayout(new BoxLayout(hariJam, BoxLayout.X_AXIS));
        hariJam.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));

        for (int j = 0; j < jadwalTanggal.size(); j++) {
            JPanel jadwalArea = new JPanel();
            jadwalArea.setBackground(new Color(236, 157, 36, 255));
            jadwalArea.setLayout(new BoxLayout(jadwalArea, BoxLayout.Y_AXIS));
            JLabel hari = new JLabel(jadwalHari.get(j));
            hari.setFont(new Font("Arial", Font.PLAIN, 7));
            hari.setForeground(Color.WHITE);
            hari.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel tanggal = new JLabel(jadwalTanggal.get(j));
            tanggal.setFont(new Font("Arial", Font.PLAIN, 10));
            tanggal.setForeground(Color.WHITE);
            tanggal.setAlignmentX(Component.CENTER_ALIGNMENT);
            jadwalArea.add(Box.createVerticalStrut(3));
            jadwalArea.add(tanggal);
            jadwalArea.add(Box.createVerticalStrut(3));
            jadwalArea.add(hari);
            jadwalArea.add(Box.createVerticalStrut(3));
            hariJam.add(jadwalArea);
            hariJam.add(Box.createHorizontalStrut(10));
        }
        JLabel jamTayangLabel = new JLabel("Jam Tayang");
        jamTayangLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        jamTayangLabel.setForeground(new Color(236, 157, 36, 255));
        jamTayangLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ArrayList<String> jadwalJam = new ArrayList<>();
        jadwalJam.add("10:00");
        jadwalJam.add("12:00");
        jadwalJam.add("14:00");
        jadwalJam.add("16:00");
        jadwalJam.add("18:00");
        jadwalJam.add("20:00");
        jadwalJam.add("22:00");

        JPanel jamTayang = new JPanel();
        jamTayang.setBackground(Color.GRAY);
        jamTayang.setLayout(new BoxLayout(jamTayang, BoxLayout.X_AXIS));
        jamTayang.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));

        for (int j = 0; j < jadwalTanggal.size(); j++) {
            JPanel jadwalArea = new JPanel();
            jadwalArea.setBackground(new Color(236, 157, 36, 255));
            jadwalArea.setLayout(new BoxLayout(jadwalArea, BoxLayout.Y_AXIS));
            JLabel jam = new JLabel(jadwalJam.get(j));
            jam.setFont(new Font("Arial", Font.PLAIN, 7));
            jam.setForeground(Color.WHITE);
            jam.setAlignmentX(Component.CENTER_ALIGNMENT);
            jadwalArea.add(Box.createVerticalStrut(3));
            jadwalArea.add(jam);
            jadwalArea.add(Box.createVerticalStrut(3));

            jamTayang.add(jadwalArea);
            jamTayang.add(Box.createHorizontalStrut(10));
        }

        ImageIcon poster = new ImageIcon("asset/tombolBeliTiket.png");
        Image posterImg = poster.getImage();

        // Mengubah ukuran poster sesuai dengan ukuran yang diinginkan
        Image posterImgScaled = posterImg.getScaledInstance(1320, 80, Image.SCALE_SMOOTH);
        ImageIcon posterScaled = new ImageIcon(posterImgScaled);

        // Membuat JLabel untuk menampilkan poster
        JLabel tombolBeliTiket = new JLabel(posterScaled);
        tombolBeliTiket.setAlignmentX(Component.CENTER_ALIGNMENT);
        jadwalPanel.add(jadwalLabel);
        jadwalPanel.add(hariJam);
        jadwalPanel.add(jamTayangLabel);
        jadwalPanel.add(jamTayang);
        jadwalPanel.add(tombolBeliTiket);
        jadwalPanel.add(Box.createVerticalStrut(10));
        jadwalMainPanel.add(jadwalPanel, BorderLayout.CENTER);

        return jadwalMainPanel;
    }

    public static void main(String[] args) {
        DetailMovie app = new DetailMovie();
        app.launchApp();
    }
}