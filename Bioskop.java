import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Bioskop {
    private JFrame frame;
    private JPanel mainPanel;

    public Bioskop() {
        frame = new JFrame("Bioskop");
        frame.setSize(1440, 1080);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

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
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        // Mengatur layout dari mainContentPanel menjadi BoxLayout dengan arah vertikal
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        // Mengatur warna background dari mainContentPanel menjadi BLACK
        mainContentPanel.setBackground(Color.WHITE);
        // Menambahkan panel Movies ke mainContentPanel dengan data yang sudah
        ArrayList<Movie> movieList = ConnectKeDB.getAllMovie("Sedang Tayang");
        mainContentPanel.add(createCategoryPanel("Sedang Tayang", movieList));
        // Menambahkan panel series ke mainContentPanel dengan data yang sudah
        // diinputkan
        ArrayList<Movie> movieComingSoonList = ConnectKeDB.getAllMovie("Coming Soon");
        mainContentPanel.add(createCategoryPanel("Coming Soon", movieComingSoonList));

        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        // Mengatur tipe scrollbar untuk hanya muncul jika diperlukan
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // jika tidak ingin scroll

        return scrollPane;
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
        lihatSemua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LihatSemua(category);
            }
        });

        JPanel headCategory = new JPanel(new BorderLayout());
        headCategory.add(categoryLabel, BorderLayout.CENTER);
        headCategory.add(lihatSemua, BorderLayout.EAST);
        headCategory.setBackground(new Color(217,217,217,255));
        headCategory.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        categoryPanel.add(headCategory, BorderLayout.NORTH);

        // Membuat panel untuk menampilkan daftar film
        JPanel movieListPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        // Mengatur warna background dari movieListPanel menjadi GRAY
        movieListPanel.setBackground(Color.WHITE);

        // Menambahkan setiap film ke dalam movieListPanel
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.X_AXIS)); // Mengatur layout vertikal
        moviePanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 20));
        moviePanel.setBackground(new Color(217,217,217,255));
        moviePanel.add(Box.createHorizontalStrut(30));
        int i = 0;
        for (Movie movie : movieList) {
            // Mengambil gambar poster film
            ImageIcon poster = new ImageIcon(movie.getImage());
            Image posterImg = poster.getImage();

            // Mengubah ukuran poster sesuai dengan ukuran yang diinginkan
            Image posterImgScaled = posterImg.getScaledInstance(270, 350, Image.SCALE_SMOOTH);
            ImageIcon posterScaled = new ImageIcon(posterImgScaled);

            // Membuat JLabel untuk menampilkan poster
            JLabel posterLabel = new JLabel(posterScaled);

            // Menambahkan jarak antara gambar poster
            posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Membuat label di tengah
            posterLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new DetailMovie(movie);
                }
            });
            moviePanel.add(posterLabel);

            // Menambahkan sedikit jarak antar poster untuk estetika
            moviePanel.add(Box.createHorizontalStrut(50)); // Spasi antar gambar poster (dalam px)
            i++;
            if (i == 4) {
                break;
            }
        }

        categoryPanel.add(moviePanel, BorderLayout.CENTER);
        return categoryPanel;

    }

    public static void main(String[] args) {
        new Bioskop();
    }
}