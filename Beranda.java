import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.geom.RoundRectangle2D;

public class Beranda extends JFrame {

    public Beranda() {
        // Set frame utama
        setTitle("SiPaket");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Membuat panel untuk header dengan gambar
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon("asset/Header.jpg").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        headerPanel.setPreferredSize(new Dimension(1920, 400));
        headerPanel.setBackground(Color.WHITE);
        add(headerPanel, BorderLayout.NORTH);

        // Membuat panel untuk kategori tiket
        JPanel kategoriPanel = new JPanel();
        kategoriPanel.setLayout(new BorderLayout());
        kategoriPanel.setBackground(Color.WHITE);

        // Memuat font Poppins Bold
        Font customFont = loadFont("asset/Poppins-Bold.ttf", 32);

        // Menambahkan label "KATEGORI"
        JLabel kategoriLabel = new JLabel("KATEGORI", SwingConstants.CENTER);
        kategoriLabel.setFont(customFont);
        kategoriLabel.setForeground(new Color(0xEC9D24));  
        kategoriLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); 

        kategoriPanel.add(kategoriLabel, BorderLayout.NORTH);

        // Panel untuk tombol-tombol kategori
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.WHITE);

        // Menambahkan tombol-tombol kategori
        String[] kategori = {"Film", "Kesenian", "Konser"};
        for (String k : kategori) {
            // Menggunakan ukuran font yang lebih kecil untuk tombol
            RoundedButton button = new RoundedButton(k, customFont.deriveFont(24f)); 
            buttonPanel.add(button);
        }

        kategoriPanel.add(buttonPanel, BorderLayout.CENTER);

        // Menambahkan panel kategori ke frame
        add(kategoriPanel, BorderLayout.CENTER);

        // Menambahkan label "Tiket Dibeli"
        JPanel tiketPanel = new JPanel();
        tiketPanel.setLayout(new BorderLayout());
        tiketPanel.setBackground(Color.WHITE);
        
        // Label untuk "Tiket Dibeli"
        JLabel tiketLabel = new JLabel("Tiket Dibeli", SwingConstants.CENTER);
        tiketLabel.setFont(customFont);
        tiketLabel.setForeground(new Color(0xEC9D24)); 
        tiketLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        tiketPanel.add(tiketLabel, BorderLayout.NORTH);

        add(tiketPanel, BorderLayout.SOUTH);  
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
}

// Kelas tombol dengan sudut membulat
class RoundedButton extends JButton {
    public RoundedButton(String text, Font font) {
        super(text);
        setFont(font);
        setPreferredSize(new Dimension(380, 110));
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
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
        g2.setColor(getForeground());
        g2.setFont(getFont());

        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 5);

        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
        return shape.contains(x, y);
    }
}
