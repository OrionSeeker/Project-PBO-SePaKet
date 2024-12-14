import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EditProfile extends JFrame {
    private int userId;  // Store the user ID

    public EditProfile(int userId) {
        this.userId = userId;  // Store user ID to update data

        setTitle("Edit Profile");
        setSize(720, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Layout for form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Edit Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        // Username
        JLabel usnLabel = new JLabel("Username");
        usnLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(usnLabel, gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Password
        JLabel passLabel = new JLabel("New Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(passLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Confirm Password
        JLabel confirmPassLabel = new JLabel("Confirm Password");
        confirmPassLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(confirmPassLabel, gbc);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Cancel Button
        JButton cancelButton = new JButton("Batal");
        cancelButton.setPreferredSize(new Dimension(150, 30));
        cancelButton.setBackground(new Color(204, 204, 204));
        cancelButton.setForeground(Color.BLACK);
        buttonPanel.add(cancelButton);

        // Save Button
        JButton saveButton = new JButton("Simpan");
        saveButton.setPreferredSize(new Dimension(150, 30));
        saveButton.setBackground(new Color(236, 157, 36));
        saveButton.setForeground(Color.BLACK);
        buttonPanel.add(saveButton);

        // Add buttons to the form
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Load the current username
        loadCurrentUserData(usernameField);

        // Action listeners for buttons
        saveButton.addActionListener(e -> saveProfile(usernameField, passwordField, confirmPasswordField));
        cancelButton.addActionListener(e -> cancelEditProfile());

        setVisible(true);
    }

    // Method to load current user data (e.g., username)
    private void loadCurrentUserData(JTextField usernameField) {
        try (Connection conn = ConnectKeDB.getConnection()) {
            String query = "SELECT username FROM user WHERE id = ?";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setInt(1, userId);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        usernameField.setText(rs.getString("username"));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat data pengguna.");
        }
    }

    // Method to save new profile (including new username and password)
    private void saveProfile(JTextField usernameField, JPasswordField passwordField, JPasswordField confirmPasswordField) {
        String newUsername = usernameField.getText();
        String newPassword = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Password tidak cocok!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConnectKeDB.getConnection()) {
            String query = "UPDATE user SET username = ?, password = ? WHERE id = ?";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, newUsername);
                st.setString(2, newPassword);
                st.setInt(3, userId);

                st.executeUpdate();
                JOptionPane.showMessageDialog(this, "Profile berhasil diperbarui!");
                dispose();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data pengguna.");
        }
    }

    // Method to cancel the edit operation and return to the profile screen
    private void cancelEditProfile() {
        // Dispose the edit profile window and return to the profile window
        dispose();
    }
}
