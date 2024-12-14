import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Beranda extends JFrame {
    private int userId; 
    private ImageIcon headerImageIcon;
    private JLabel headerImageLabel;

    public Beranda(int userId) {
        this.userId = userId;  // Assign userId from LoginRegister
        setTitle("SePaKet");
        setSize(1440, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create header panel with image background
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1440, 200));

        headerImageIcon = new ImageIcon("asset/HeaderBaru.jpg");
        headerImageLabel = new JLabel();
        headerImageLabel.setIcon(resizeHeaderImage(headerImageIcon, 1440, 200));
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
                new Profile(userId);  // Pass userId to Profile frame
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
        JPanel kategoriPanel = createPanel("KATEGORI", new String[]{"Film", "Kesenian", "Konser"});
        kategoriPanel.setFont(customFont);
        kategoriPanel.setPreferredSize(new Dimension(1440, 600));
        kategoriPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); 
        mainPanel.add(kategoriPanel);

        // Bought ticket panel
        JPanel tiketDibeliPanel = createPanel("TIKET DIBELI", new String[]{"Film A", "Film B", "Film C"});
        tiketDibeliPanel.setFont(customFont);
        tiketDibeliPanel.setPreferredSize(new Dimension(1440, 600));
        tiketDibeliPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); 
        mainPanel.add(tiketDibeliPanel);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Fetch user-specific data
        loadUserData();

        // Display the frame
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
                        new detailKategori(userId); 
                        break;
                    case "Kesenian":
                        new detailKategori(userId); 
                        break;
                    case "Konser":
                        new detailKategori(userId);
                        break;
                    default:
                        break;
                }
            });
            buttonPanel.add(button);
        }
    
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(1440, 440));
        return panel;
    }    

    // Method to load user-specific data (for example, user profile data)
    private void loadUserData() {
        try (Connection conn = ConnectKeDB.getConnection()) {
            String query = "SELECT * FROM user WHERE id = ?";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setInt(1, userId);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        String username = rs.getString("username");
                        System.out.println("Welcome, " + username); 
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
}