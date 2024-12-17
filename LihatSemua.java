import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LihatSemua implements app {
    private JFrame frame;
    private JPanel mainPanel;
    String status;
    public LihatSemua(String status) {
        this.status = status;
        frame = new JFrame("LihatSemua");
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
        // Mengatur warna background dari mainContentPanel menjadi BLACK
        mainContentPanel.setBackground(Color.WHITE);
        // Menambahkan panel Movies ke mainContentPanel dengan data yang sudah
        ArrayList<Movie> movieList = ConnectKeDB.getAllMovie(status);
        mainContentPanel.add(createCategoryPanel(status, movieList));
        // Mengembalikan mainContentPanel
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        // Mengatur tipe scrollbar untuk hanya muncul jika diperlukan
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // jika tidak ingin scroll

        return scrollPane;
    }

    private static JPanel createCategoryPanel(String categoryTitle, ArrayList<Movie> movieList) {
        // Membuat panel untuk kategori
        JPanel categoryPanel = new JPanel();
        categoryPanel.setBackground(Color.WHITE);
        categoryPanel.setLayout(new BorderLayout());

        // Membuat label untuk judul kategori (Movies atau Series)
        JLabel categoryLabel = new JLabel(categoryTitle);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 40));
        categoryLabel.setForeground(new Color(236, 157, 36, 255));
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        categoryPanel.add(categoryLabel, BorderLayout.NORTH);

        // Membuat list model untuk menampung Movies
        DefaultListModel<Movie> filmListModel = new DefaultListModel<>();
        for (Movie film : movieList) {
            filmListModel.addElement(film);
        }

        // Membuat JList untuk menampilkan Movies
        JList<Movie> filmList = new JList<>(filmListModel);
        // Mengatur renderer untuk menampilkan Movies
        filmList.setCellRenderer(new FilmListRenderer());
        // Mengatur warna background dari filmList menjadi BLACK
        filmList.setBackground(Color.WHITE);
        // filmList.setBorder(BorderFactory.createEmptyBorder(0, , 0, 0));
        // Mengatur layout orientation dari filmList menjadi HORIZONTAL_WRAP
        filmList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        // Mengatur visible row count dari filmList menjadi 1
        filmList.setVisibleRowCount(0);
        // Menambahkan mouse listener agar filmList dapat diklik
        filmList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Jika filmList diklik satu kali
                if (e.getClickCount() == 1) {
                    // Mendapatkan index dari film yang diklik
                    int index = filmList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        // Menampilkan detail film yang dipilih
                        Movie selectedFilm = filmList.getModel().getElementAt(index);
                        new DetailMovie(selectedFilm);
                    }
                }
            }
        });

        // Membuat scroll pane untuk filmList
        JScrollPane scrollPane = new JScrollPane(filmList);
        // Mengatur horizontal scroll bar policy dari scrollPane menjadi NEVER
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // Mengatur vertical scroll bar policy dari scrollPane menjadi ALWAYS
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // Mengatur border dari scrollPane menjadi null
        scrollPane.setBorder(null);
        // Mengatur warna background dari viewport dari scrollPane menjadi BLACK
        scrollPane.getViewport().setBackground(Color.BLACK);

        // Menambahkan scrollPane ke categoryPanel
        categoryPanel.add(scrollPane, BorderLayout.CENTER);

        // Mengembalikan categoryPanel
        return categoryPanel;
    }

    // Kelas untuk merepresentasikan film atau series
    private static class FilmListRenderer extends JPanel implements ListCellRenderer<Movie> {
        // Variabel untuk menampilkan gambar, judul, dan genre
        private JLabel imageLabel;
        private JLabel titleLabel;
        private JLabel genreLabel;

        // Konstruktor untuk membuat FilmListRenderer
        public FilmListRenderer() {
            // Mengatur layout dari FilmListRenderer menjadi BoxLayout dengan arah vertikal
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            // Mengatur ukuran dari FilmListRenderer menjadi 200 x 310
            setPreferredSize(new Dimension(450, 600));
            // Mengatur warna background dari FilmListRenderer menjadi BLACK
            setBackground(Color.BLACK);

            // Membuat label untuk menampilkan gambar
            imageLabel = new JLabel();
            // Mengatur alignment dari imageLabel menjadi di tengah
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Membuat label untuk menampilkan judul
            titleLabel = new JLabel();
            titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
            titleLabel.setForeground(new Color(236, 157, 36, 255));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Membuat label untuk menampilkan genre
            genreLabel = new JLabel();
            genreLabel.setFont(new Font("Arial", Font.BOLD, 15));
            genreLabel.setForeground(new Color(236, 157, 36, 255));
            genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Menambahkan imageLabel, titleLabel, dan genreLabel ke FilmListRenderer
            add(imageLabel);
            add(Box.createVerticalStrut(5));
            add(titleLabel);
            add(genreLabel);

            // Mengatur border dari FilmListRenderer menjadi null / kosong
            setBorder(BorderFactory.createEmptyBorder());
        }

        // Method untuk mengatur tampilan dari setiap item di filmList
        @Override
        public Component getListCellRendererComponent(JList<? extends Movie> list, Movie film, int index, boolean isSelected, boolean cellHasFocus) {
            // Mengatur gambar, judul, dan genre dari film
            imageLabel.setIcon(new ImageIcon(
                    new ImageIcon(film.getImage()).getImage().getScaledInstance(370, 500, Image.SCALE_SMOOTH)));
            titleLabel.setText(film.getTitle());
            genreLabel.setText(film.getGenre());

            // Mengatur warna background dari FilmListRenderer berdasarkan apakah item
            // tersebut dipilih atau tidak
            if (isSelected) {
                setBackground(Color.DARK_GRAY);
            } else {
                setBackground(Color.WHITE);
            }
            return this;
        }
    }

    public static void main(String[] args) {
    }
}