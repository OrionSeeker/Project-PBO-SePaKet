import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginRegister extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public LoginRegister() {
        setTitle("SePaKet: Sistem Pemesanan Tiket");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 480);
        setLocationRelativeTo(null);
        setResizable(false);


        // Ini cardlayout untuk pindah antara login dan register
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Ini panel untuk login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());

        // Ini panel untuk gambar dalam login
        JPanel gambarPanel = new JPanel();
        gambarPanel.setPreferredSize(new Dimension(360, 440));
        ImageIcon gambarLogin = new ImageIcon(new ImageIcon("./Asset/gambarLogin.png").getImage().getScaledInstance(360, 440, Image.SCALE_SMOOTH));
        JLabel gambarLabel = new JLabel(gambarLogin);
        gambarPanel.add(gambarLabel);

        // INi panel untuk form login
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel usnLabel = new JLabel("Username");
        usnLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(usnLabel, gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        formPanel.add(usernameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        formPanel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton loginBtn = new JButton("Login");
        loginBtn.setPreferredSize(new Dimension(150, 30));
        loginBtn.setBackground(new Color(236,157,36));
        loginBtn.setForeground(Color.BLACK);
        formPanel.add(loginBtn, gbc);

        gbc.gridy++;
        JButton goToRegist = new JButton("Belum Punya Akun? Register!");
        goToRegist.setForeground(Color.DARK_GRAY);
        goToRegist.setBorderPainted(false);
        goToRegist.setContentAreaFilled(false);
        goToRegist.setHorizontalAlignment(SwingConstants.CENTER);

        goToRegist.addActionListener(e -> cardLayout.show(cardPanel, "regist"));

        formPanel.add(goToRegist, gbc);

        loginPanel.add(gambarPanel, BorderLayout.WEST);
        loginPanel.add(formPanel, BorderLayout.CENTER);

        cardPanel.add(loginPanel, "login");

        // INi panel untuk bagian regist
        JPanel registPanel = new JPanel();
        registPanel.setLayout(new BorderLayout());

        // Ini panel untuk gambar di regist
        JPanel gambarPanel2 = new JPanel();
        gambarPanel2.setPreferredSize(new Dimension(360, 440));
        JLabel gambarLabel2 = new JLabel(gambarLogin);
        gambarPanel2.add(gambarLabel2);

        // Ini panel untuk form regist
        JPanel formPanel2 = new JPanel();
        formPanel2.setLayout(new GridBagLayout());
        formPanel2.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JLabel titleLabel2 = new JLabel("Register");
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel2.add(titleLabel2, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel usnLabel2 = new JLabel("Username");
        usnLabel2.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel2.add(usnLabel2, gbc);

        gbc.gridx = 1;
        JTextField usernameField2 = new JTextField(20);
        formPanel2.add(usernameField2, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel passLabel2 = new JLabel("Password");
        passLabel2.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel2.add(passLabel2, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField2 = new JPasswordField(20);
        formPanel2.add(passwordField2, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton registBtn = new JButton("Register");
        registBtn.setPreferredSize(new Dimension(150, 30));
        registBtn.setBackground(new Color(236,157,36));
        registBtn.setForeground(Color.BLACK);
        formPanel2.add(registBtn, gbc);

        gbc.gridy++;
        JButton backToLogin = new JButton("Sudah Punya Akun? Login!");
        backToLogin.setForeground(Color.DARK_GRAY);
        backToLogin.setBorderPainted(false);
        backToLogin.setContentAreaFilled(false);
        backToLogin.setHorizontalAlignment(SwingConstants.CENTER);

        backToLogin.addActionListener(e -> cardLayout.show(cardPanel, "login"));

        formPanel2.add(backToLogin, gbc);
        registPanel.add(gambarPanel2, BorderLayout.WEST);
        registPanel.add(formPanel2, BorderLayout.CENTER);
        cardPanel.add(registPanel, "regist");

        add(cardPanel);
        cardLayout.show(cardPanel, "login");

        // Listener untuk login dan register
        loginBtn.addActionListener(e -> {
            String usn = usernameField.getText();
            String pwd = new String(passwordField.getPassword());

            try (Connection conn = ConnectKeDB.getConnection()) {
                String query = "SELECT * FROM user WHERE username = ? AND password = ?";
                try (PreparedStatement st = conn.prepareStatement(query)){
                    st.setString(1, usn);
                    st.setString(2, pwd);

                    try (ResultSet rs = st.executeQuery()){
                        if (rs.next()){
                            JOptionPane.showMessageDialog(this, "Login Berhasil!");
                            JFrame frameBeranda = new Beranda();
                            frameBeranda.setVisible(true);
                            frameBeranda.setLocationRelativeTo(null);
                        }
                        else {
                            JOptionPane.showMessageDialog(this, "User name atau Password salah!!!!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Gagal konek ke db");
            }
        });


        registBtn.addActionListener(e -> {
            String usn = usernameField2.getText();
            String pwd = new String(passwordField2.getPassword());
        
            try (Connection conn =ConnectKeDB.getConnection()) {
                String query = "INSERT INTO user(username, password) VALUES (?, ?)";
                try (PreparedStatement st = conn.prepareStatement(query)) {
                    st.setString(1, usn);
                    st.setString(2, pwd);
        
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Register Berhasil!!! Yuk segera login!");
                    cardLayout.show(cardPanel, "login");
                }
            }
            catch (SQLException ex) {
                if (ex.getMessage().contains("Duplicate entry")) {
                    JOptionPane.showMessageDialog(this, "User name sudah pernah dipakai...", "Register Gagal", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    ex.printStackTrace();
                    System.out.println("Gagal konek ke db");
                }
            }
        });
        
    }

}
