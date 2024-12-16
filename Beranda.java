import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Beranda extends JFrame {
    private int userId;
    private ImageIcon headerImageIcon;
    private JLabel headerImageLabel;
    private tiketBioskop tiketBioskopUser;
    private tiketKonserSeni tiketSeniUser; 
    private tiketKonserSeni tiketKonserUser; 
    public Beranda(int userId) {
        this.userId = userId;
        setTitle("SiPaket");
        setSize(1440, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create header panel with image background
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1440, 150));

        headerImageIcon = new ImageIcon("asset/HeaderBaru.jpg");
        headerImageLabel = new JLabel(headerImageIcon);
        headerImageLabel.setIcon(resizeHeaderImage(headerImageIcon, 1440, 150));
        headerImageLabel.setLayout(new BorderLayout());

        // Create right panel for profile button
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(250, 250));
        rightPanel.setOpaque(false);

        ImageIcon originalIcon = new ImageIcon("asset/profil.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon buttonIcon = new ImageIcon(scaledImage);

        JButton profileButton = new JButton(buttonIcon);
        profileButton.setPreferredSize(new Dimension(60, 60));
        profileButton.setBorderPainted(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setFocusPainted(false);

        // Add mouse listener for profile button
        profileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Profile(userId); // Pass userId to Profile frame
            }
        });

        rightPanel.add(profileButton);
        headerImageLabel.add(rightPanel, BorderLayout.EAST);
        headerPanel.add(headerImageLabel, BorderLayout.CENTER);

        // Add header panel to main frame
        add(headerPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Load custom font
        Font customFont = loadFont("asset/Poppins-Bold.ttf", 32);

        // Category panel
        JPanel kategoriPanel = createPanel("Kategori", new String[] { "Film", "Kesenian", "Konser" });
        kategoriPanel.setFont(customFont);
        // kategoriPanel.setPreferredSize(new Dimension(1440, 600));
        // kategoriPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(kategoriPanel);

        // Bought ticket panel (Initially empty)
        JLabel titleLabel = new JLabel("TIKET DIBELI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0xEC9D24));
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JPanel tiket = new JPanel();
        tiket.setLayout(new BorderLayout());
        tiket.setBackground(Color.WHITE);
        // tiket.setPreferredSize(new Dimension(1440, 600));
        tiket.add(titleLabel, BorderLayout.NORTH);

        JPanel tiketTiket = new JPanel();
        tiketTiket.setLayout(new GridBagLayout());
        tiketTiket.setBackground(Color.WHITE);

        // Create ticket panels

        JPanel tiketingBioskop = new JPanel(new BorderLayout());
        tiketingBioskop.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tiketingBioskop.setBackground(new Color(217, 217, 217, 255));
        tiketBioskopUser = new tiketBioskop(" "," "," "," "," "," "," ",userId);
        tiketingBioskop.add(tiketBioskopUser.tiketPanel(userId, "", userId), BorderLayout.CENTER);

        tiketTiket.add(tiketingBioskop);

        JPanel tiketKesenian = new JPanel(new BorderLayout());
        tiketKesenian.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tiketKesenian.setBackground(new Color(217, 217, 217, 255));
        tiketSeniUser = new tiketKonserSeni(" "," "," "," "," "," "," "," ",userId);
        tiketKesenian.add(tiketSeniUser.tiketPanel(userId, "Kesenian", userId), BorderLayout.CENTER);

        tiketTiket.add(Box.createHorizontalStrut(50));
        tiketTiket.add(tiketKesenian);

        JPanel tiketKonser = new JPanel(new BorderLayout());
        tiketKonser.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tiketKonser.setBackground(new Color(217, 217, 217, 255));
        tiketSeniUser = new tiketKonserSeni(" "," "," "," "," "," "," "," ",userId);
        tiketKonser.add(tiketSeniUser.tiketPanel(userId, "Konser", userId), BorderLayout.CENTER);

        tiketTiket.add(Box.createHorizontalStrut(50));
        tiketTiket.add(tiketKonser);

        tiket.add(tiketTiket, BorderLayout.CENTER);
        // Add main panel to frame
        mainPanel.add(tiket);
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);

        JLabel tampilkanSemuaTiket = new JLabel("Tampilkan Semua Tiket", SwingConstants.CENTER);
        tampilkanSemuaTiket.setFont(new Font("Arial", Font.BOLD, 18));
        tampilkanSemuaTiket.setForeground(new Color(0x2F2F80));
        footerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SemuaTiket(userId);
            }
        });
        JPanel dalamFooterPanel = new JPanel();
        dalamFooterPanel.setBackground(new Color(0xEC9D24));
        dalamFooterPanel.setBorder(BorderFactory.createEmptyBorder(0, 500, 0, 500));
        dalamFooterPanel.add(tampilkanSemuaTiket);

        footerPanel.add(dalamFooterPanel, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(footerPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        // Mengatur tipe scrollbar untuk hanya muncul jika diperlukan
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // jika tidak ingin scroll
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Method to load custom font
    private Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Fallback font if loading custom font fails
            return new Font("Arial", Font.BOLD, (int) size);
        }
    }

    // Create panel for each category or ticket
    private JPanel createPanel(String title, String[] items) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0xEC9D24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        for (String item : items) {
            RoundedButton button = new RoundedButton(item);
            button.addActionListener(e -> {
                // Buka frame sesuai kategori
                switch (item) {
                    case "Film":
                        new Bioskop();
                        break;
                    case "Kesenian":
                        new DaftarKesenian();
                        break;
                    case "Konser":
                        new DaftarKonser();
                        break;
                    default:
                        break;
                }
            });
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        return panel;
    }

    // Resize header image
    private ImageIcon resizeHeaderImage(ImageIcon originalIcon, int targetWidth, int targetHeight) {
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    // Rounded button class
    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setFont(new Font("Arial", Font.BOLD, 18));
            setPreferredSize(new Dimension(400, 165));
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

            // Set button background color
            g2.setColor(getBackground());

            // Draw button with rounded corners (20px)
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));

            // Set button text color
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getAscent();

            // Center the text in the button
            g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 5);
            g2.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            // Adjust click area for rounded button shape
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
            return shape.contains(x, y);
        }
    }
    public static void main(String[] args) {
        new Beranda(0);
    }
}