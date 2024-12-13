import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Profile extends JFrame {
    public Profile() {
        // Konfigurasi frame
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

        JLabel nameLabel = new JLabel("Username");
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
                JOptionPane.showMessageDialog(Profile.this, "Fungsi Edit Profile belum diimplementasikan!");
            }
        });

        centerPanel.add(nameLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(editProfileLabel);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
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
