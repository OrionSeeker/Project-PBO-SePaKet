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
                new Profile();
            }
        });

        JPanel headerPanel = new JPanel(new BorderLayout());
        // Menambahkan headerLabel ke headerPanel
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        // Mengembalikan headerPanel

        return headerPanel;
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