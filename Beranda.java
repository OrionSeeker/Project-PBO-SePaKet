import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class Beranda extends JFrame {
    private ImageIcon headerImageIcon;
    private JLabel headerImageLabel;

    public Beranda() {
        // Mengatur frame utama
        setTitle("SiPaket");
        setSize(1440, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Membuat panel header dengan ukuran 1440x350
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1440, 350));

        // Menggunakan gambar sebagai background header
        headerImageIcon = new ImageIcon("asset/Header.jpg");
        headerImageLabel = new JLabel();
        headerImageLabel.setIcon(resizeHeaderImage(headerImageIcon, 1440, 350));
        headerImageLabel.setLayout(new BorderLayout());

        // Membuat panel kanan untuk tombol profil
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(250, 250));
        rightPanel.setOpaque(false);

        // Membuat tombol profil
        ImageIcon originalIcon = new ImageIcon("asset/profil.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon buttonIcon = new ImageIcon(scaledImage);

        JButton profileButton = new JButton(buttonIcon);
        profileButton.setPreferredSize(new Dimension(60, 60));
        profileButton.setBorderPainted(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setFocusPainted(false);

        rightPanel.add(profileButton);
        
        // Tambahkan event listener untuk tombol profil
        profileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Halaman Profil akan dibuka!");
            }
        });

        headerImageLabel.add(rightPanel, BorderLayout.EAST);
        headerPanel.add(headerImageLabel, BorderLayout.CENTER);

        // Tambahkan panel header ke frame utama
        add(headerPanel, BorderLayout.NORTH);

        // Panel utama untuk kategori tiket, tiket dibeli, tiket yang disimpan
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 
        mainPanel.setBackground(Color.WHITE);

        // Memuat font Poppins Bold
        Font customFont = loadFont("asset/Poppins-Bold.ttf", 32);

        // Panel Kategori Tiket
        JPanel kategoriPanel = createPanel("KATEGORI", new String[]{"Film", "Kesenian", "Konser"});
        kategoriPanel.setFont(customFont);
        kategoriPanel.setPreferredSize(new Dimension(1440, 600)); 
        mainPanel.add(kategoriPanel);

        // Panel Tiket Dibeli
        JPanel tiketDibeliPanel = createPanel("TIKET DIBELI", new String[]{"Film A", "Film B", "Film C"});
        tiketDibeliPanel.setFont(customFont);
        tiketDibeliPanel.setPreferredSize(new Dimension(1440, 600)); 
        mainPanel.add(tiketDibeliPanel);

        // Panel Tiket yang Disimpan
        JPanel tiketDisimpanPanel = createPanel("TIKET DISIMPAN", new String[]{"Event X", "Event Y", "Event Z"});
        tiketDibeliPanel.setFont(customFont);
        tiketDisimpanPanel.setPreferredSize(new Dimension(1440, 600)); 
        mainPanel.add(tiketDisimpanPanel);

        // Tambahkan panel utama ke frame utama
        add(mainPanel, BorderLayout.CENTER);

        // Menampilkan frame
        setVisible(true);
    }

    // Metode untuk memuat font kustom
    private Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Callback font jika gagal
            return new Font("Arial", Font.BOLD, (int) size);
        }
    }

    // Membuat panel untuk setiap kategori atau tiket
    private JPanel createPanel(String title, String[] items) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
    
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0xEC9D24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);
    
        for (String item : items) {
            RoundedButton button = new RoundedButton(item);
            button.addActionListener(e -> {
                new DetailFrame(item, "Deskripsi detail untuk " + item);
            });
            buttonPanel.add(button);
        }
    
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(1440, 600)); 
        return panel;
    }    

    // Frame detail untuk setiap tombol
    class DetailFrame extends JFrame {
        public DetailFrame(String title, String description) {
            setTitle("Detail");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());

            JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
            add(titleLabel, BorderLayout.NORTH);

            JTextArea descriptionArea = new JTextArea(description);
            descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
            descriptionArea.setLineWrap(true);
            descriptionArea.setWrapStyleWord(true);
            descriptionArea.setEditable(false);
            add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

            setVisible(true);
        }
    }

    // Membuat tombol dengan sudut membulat
    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setFont(new Font("Arial", Font.BOLD, 18));
            setPreferredSize(new Dimension(400, 50));  
            setBackground(new Color(0x2F2F80));        
            setForeground(Color.WHITE);                
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);               
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Mengatur warna latar belakang tombol
            g2.setColor(getBackground());
            
            // Membuat tombol dengan sudut membulat 20px
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));

            // Mengatur warna teks tombol
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getAscent();
            
            // Menempatkan teks di tengah tombol
            g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 5);

            g2.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            // Mengatur area klik tombol sesuai bentuk bulat
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
            return shape.contains(x, y);
        }
    }

    // Fungsi untuk mengatur ukuran gambar header
    private ImageIcon resizeHeaderImage(ImageIcon originalIcon, int targetWidth, int targetHeight) {
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    // Metode main untuk menjalankan program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Beranda();
        });
    }
}
