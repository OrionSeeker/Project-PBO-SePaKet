// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;

// public class detailKategori implements app {
//     private JFrame frame;
//     private JPanel mainPanel;
//     private int userId;

//     public detailKategori(int userId) {
//         this.userId = userId;
//     }

//     public void launchApp() {
//         frame = new JFrame("Detail Kategori");
//         frame.setSize(1440, 1080);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(new BorderLayout());

//         mainPanel = new JPanel(new BorderLayout());

//         mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);

//         mainPanel.add(createMainContentPanel(), BorderLayout.CENTER);

//         frame.add(mainPanel, BorderLayout.CENTER);

//         frame.setVisible(true);
//     }

//     @Override
//     public JPanel createHeaderPanel(){
//         return Head.createHeaderPanel(e -> frame.dispose());
//     }
//     @Override
//     public JScrollPane createMainContentPanel() {
//         JPanel mainContentPanel = new JPanel(new BorderLayout());
//         mainContentPanel.setBackground(Color.WHITE);
//         JLabel detailKategori = new JLabel("Detail Kategori", SwingConstants.CENTER);
//         detailKategori.setFont(new Font("Arial", Font.BOLD, 40));
//         detailKategori.setForeground(new Color(236, 157, 36, 255));
//         detailKategori.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
//         mainContentPanel.add(detailKategori, BorderLayout.NORTH);

//         JPanel detail = new JPanel();
//         detail.setBackground(Color.WHITE);
//         detail.setLayout(new BoxLayout(detail, BoxLayout.X_AXIS));

//         ImageIcon gambar = new ImageIcon("asset/gambarKosong.png");
//         Image gambarImg = gambar.getImage();
//         Image gambarImgScaled = gambarImg.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
//         ImageIcon gambarIcon = new ImageIcon(gambarImgScaled);

//         detail.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 0));
//         detail.add(new JLabel(gambarIcon), BorderLayout.CENTER);

//         JPanel detailGambar = new JPanel();
//         detailGambar.setBackground(Color.WHITE);
//         detailGambar.setLayout(new BoxLayout(detailGambar, BoxLayout.Y_AXIS));

//         JLabel genreLabel = new JLabel("Judul: ");
//         genreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
//         genreLabel.setForeground(new Color(236, 157, 36, 255));
//         genreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

//         JLabel yearLabel = new JLabel("Pemain: ");
//         yearLabel.setFont(new Font("Arial", Font.PLAIN, 30));
//         yearLabel.setForeground(new Color(236, 157, 36, 255));
//         yearLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

//         JLabel ratingLabel = new JLabel("Sutradara:");
//         ratingLabel.setFont(new Font("Arial", Font.PLAIN, 30));
//         ratingLabel.setForeground(new Color(236, 157, 36, 255));
//         ratingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

//         JLabel desc = new JLabel("Sinopsos");
//         desc.setFont(new Font("Arial", Font.PLAIN, 30));
//         desc.setForeground(new Color(236, 157, 36, 255));
//         desc.setAlignmentX(Component.LEFT_ALIGNMENT);

//         JTextArea descriptionArea = new JTextArea("bla bla bla....");
//         descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
//         descriptionArea.setForeground(new Color(236, 157, 36, 255));
//         descriptionArea.setBackground(Color.WHITE);
//         descriptionArea.setLineWrap(true);
//         descriptionArea.setWrapStyleWord(true);
//         descriptionArea.setEditable(false);
//         descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);

//         JButton buttonBeli = new JButton("Beli");
//         buttonBeli.setFont(new Font("Arial", Font.PLAIN, 40));
//         buttonBeli.setBackground(new Color(236, 157, 36, 255));
//         buttonBeli.setForeground(new Color(47, 47, 128, 255));
//         buttonBeli.setAlignmentX(Component.LEFT_ALIGNMENT);
//         buttonBeli.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//         buttonBeli.addActionListener(e -> {
//             JOptionPane.showMessageDialog(frame, "Berhasil membeli film");
//         });

//         // Menambahkan komponen ke detailPanel
//         detailGambar.add(genreLabel);
//         detailGambar.add(yearLabel);
//         detailGambar.add(ratingLabel);
//         detailGambar.add(Box.createVerticalStrut(10));
//         detailGambar.add(desc);
//         detailGambar.add(descriptionArea);
//         detailGambar.add(Box.createVerticalStrut(20));
//         detailGambar.add(buttonBeli);

//         detail.add(detailGambar, BorderLayout.EAST);
//         mainContentPanel.add(detail, BorderLayout.CENTER);
        
//         JScrollPane scrollPane = new JScrollPane(mainContentPanel);
//         // Mengatur tipe scrollbar untuk hanya muncul jika diperlukan
//         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // jika tidak ingin scroll

//         return scrollPane;
//     }
// }