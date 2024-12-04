import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Bioskop {
    private JFrame frame;
    private JPanel mainPanel;

    public void launchApp() {
        frame = new JFrame("Bioskop");
        frame.setSize(1920, 1080);
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
        Image imgScaled = img.getScaledInstance(1550, 320, Image.SCALE_SMOOTH);
        // Mengubah gambar menjadi icon
        ImageIcon iconScaled = new ImageIcon(imgScaled);
        // Membuat label dengan iconScaled
        JLabel headerLabel = new JLabel(iconScaled);
        // Mengatur layout dari headerLabel menjadi BorderLayout
        headerLabel.setLayout(new BorderLayout());

        ImageIcon profile = new ImageIcon("Asset/Profil.png");
        Image imgProfile = profile.getImage();
        Image imgProfileScaled = imgProfile.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon profileScaled = new ImageIcon(imgProfileScaled);

        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setOpaque(false);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 200, 80));
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

    private JPanel createMainContentPanel() {
        // Membuat panel utama untuk konten
        JPanel mainContentPanel = new JPanel();
        // Mengatur border dari mainContentPanel dengan padding 20px di atas
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        // Mengatur layout dari mainContentPanel menjadi BoxLayout dengan arah vertikal
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        // Mengatur warna background dari mainContentPanel menjadi BLACK
        mainContentPanel.setBackground(Color.WHITE);
        // Menambahkan panel Movies ke mainContentPanel dengan data yang sudah
        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Moana 2", "......", 1994, ".....", ".....",
                "asset/poster/moana2.jpg", "asset/shawshank_redemption.mp4"));
        movieList.add(new Movie("Moana 2", "......", 1994, ".....", ".....",
                "asset/poster/moana2.jpg", "asset/shawshank_redemption.mp4"));
        movieList.add(new Movie("Moana 2", "......", 1994, ".....", ".....",
                "asset/poster/moana2.jpg", "asset/shawshank_redemption.mp4"));
        movieList.add(new Movie("Moana 2", "......", 1994, ".....", ".....",
                "asset/poster/moana2.jpg", "asset/shawshank_redemption.mp4"));
        mainContentPanel.add(createCategoryPanel("Sedang Tayang", movieList));
        // Menambahkan panel series ke mainContentPanel dengan data yang sudah
        // diinputkan
        mainContentPanel.add(createCategoryPanel("Coming Soon", movieList));

        // Mengembalikan mainContentPanel
        return mainContentPanel;
    }

    private JPanel createCategoryPanel(String category, ArrayList<Movie> movieList) {
        // Membuat panel untuk kategori
        JPanel categoryPanel = new JPanel(new BorderLayout());
        // Mengatur border dari categoryPanel dengan padding 20px
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Mengatur warna background dari categoryPanel menjadi GRAY
        categoryPanel.setBackground(Color.WHITE);

        // Membuat label untuk judul kategori
        JLabel categoryLabel = new JLabel(category);
        // Mengatur font dari categoryLabel menjadi Arial Bold 24px
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 34));
        // Mengatur warna dari categoryLabel menjadi WHITE
        categoryLabel.setForeground(new Color(236, 157, 36, 255));

        JLabel lihatSemua = new JLabel("lihat semua >");
        // Mengatur font dari lihatSemua menjadi Arial Bold 24px
        lihatSemua.setFont(new Font("Arial", Font.BOLD, 24));

        // Mengatur warna dari lihatSemua menjadi WHITE
        lihatSemua.setForeground(new Color(236, 157, 36, 255));

        JPanel headCategory = new JPanel(new BorderLayout());
        headCategory.add(categoryLabel, BorderLayout.CENTER);
        headCategory.add(lihatSemua, BorderLayout.EAST);
        headCategory.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        categoryPanel.add(headCategory, BorderLayout.NORTH);

        // Membuat panel untuk menampilkan daftar film
        JPanel movieListPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        // Mengatur warna background dari movieListPanel menjadi GRAY
        movieListPanel.setBackground(Color.WHITE);

        // Menambahkan setiap film ke dalam movieListPanel
        // JPanel moviePanel = new JPanel();
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.X_AXIS)); // Mengatur layout vertikal
        moviePanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 20));

        for (Movie movie : movieList) {
            // Mengambil gambar poster film
            ImageIcon poster = new ImageIcon(movie.getImage());
            Image posterImg = poster.getImage();

            // Mengubah ukuran poster sesuai dengan ukuran yang diinginkan
            Image posterImgScaled = posterImg.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
            ImageIcon posterScaled = new ImageIcon(posterImgScaled);

            // Membuat JLabel untuk menampilkan poster
            JLabel posterLabel = new JLabel(posterScaled);

            // Menambahkan jarak antara gambar poster
            posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Membuat label di tengah
            moviePanel.add(posterLabel);

            // Menambahkan sedikit jarak antar poster untuk estetika
            moviePanel.add(Box.createHorizontalStrut(75)); // Spasi antar gambar poster (dalam px)
        }

        categoryPanel.add(moviePanel, BorderLayout.CENTER);
        return categoryPanel;

    }

    public static void main(String[] args) {
        Bioskop app = new Bioskop();
        app.launchApp();
    }
}