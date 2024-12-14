import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Profile extends JFrame {
    private int userId;  // Store the user ID
    
    public Profile(int userId) {
        this.userId = userId;  // Pass the user ID from the Beranda class
        setTitle("Profile");
        setSize(1440, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel atas untuk tombol kembali
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(47, 47, 128));

        // Membuat tombol kembali
        JButton backButton = createIconButton("asset/backButtonOren.png", 80, 80);
        if (backButton != null) {
            backButton.addActionListener(e -> dispose()); // Menutup frame saat tombol ditekan
            topPanel.add(backButton, BorderLayout.WEST);
        }

        // Panel tengah untuk profil
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(47, 47, 128));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel profileImage = createImageLabel("asset/Profil.png", 250, 250);
        if (profileImage != null) {
            profileImage.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));
            profileImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            centerPanel.add(profileImage);
        }

        // Fetch user data based on the userId
        loadUserData(centerPanel);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Method to load user-specific data (e.g., name)
    private void loadUserData(JPanel centerPanel) {
        try (Connection conn = ConnectKeDB.getConnection()) {
            String query = "SELECT username FROM user WHERE id = ?";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setInt(1, userId);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        String username = rs.getString("username");
                        JLabel nameLabel = new JLabel(username);
                        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
                        nameLabel.setForeground(Color.WHITE);
                        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                        JLabel editProfileLabel = new JLabel("Edit Profile");
                        editProfileLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                        editProfileLabel.setForeground(Color.WHITE);
                        editProfileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        editProfileLabel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                new EditProfile(userId);
                            }
                        });

                        // Log Out Label
                        JLabel logOutLabel = new JLabel("Log Out");
                        logOutLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                        logOutLabel.setForeground(Color.WHITE);
                        logOutLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        logOutLabel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                // Start the loading thread for log out
                                new LoadingThread(Profile.this).start();
                            }
                        });

                        centerPanel.add(nameLabel);
                        centerPanel.add(Box.createVerticalStrut(10));
                        centerPanel.add(editProfileLabel);
                        centerPanel.add(Box.createVerticalStrut(10));
                        centerPanel.add(logOutLabel);
                    } else {
                        JOptionPane.showMessageDialog(this, "User tidak ditemukan.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat data pengguna.");
        }
    }

    private JButton createIconButton(String imagePath, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JButton button = new JButton(new ImageIcon(scaledImage));
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            return button;
        } catch (Exception e) {
            System.err.println("Gagal memuat ikon: " + imagePath);
            return null;
        }
    }

    private JLabel createImageLabel(String imagePath, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Gagal memuat gambar: " + imagePath);
            return null;
        }
    }
}