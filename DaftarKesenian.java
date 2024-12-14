import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DaftarKesenian {
    private JFrame frame;
    private JPanel mainPanel;

    public DaftarKesenian() {
        frame = new JFrame("Daftar Kesenian");
        frame.setSize(1440, 1080);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        ActionListener backButtonListener = e -> {
            // Entar isi ini back buttonnay ke mana
        };
        
        mainPanel.add(Head.createHeaderPanel(backButtonListener), BorderLayout.NORTH);

        mainPanel.add(createMainContentPanel(), BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JScrollPane createMainContentPanel() {
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBackground(Color.WHITE);

        ArrayList<Kesenian> tayang = ConnectKeDB.getKesenian("Sedang Tayang");
        mainContentPanel.add(createCategoryPanel("Segera Dimulai", tayang));
        ArrayList<Kesenian> comingsoon = ConnectKeDB.getKesenian("Coming Soon");
        mainContentPanel.add(createCategoryPanel("Coming Soon", comingsoon));

        JScrollPane scrollPane = new JScrollPane(mainContentPanel);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scrollPane;
    }

    private JPanel createCategoryPanel(String category, ArrayList<Kesenian> list) {
        JPanel categoryPanel = new JPanel(new BorderLayout());
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        categoryPanel.setBackground(Color.WHITE);

        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 34));
        categoryLabel.setForeground(new Color(236, 157, 36, 255));

        JLabel lihatSemua = new JLabel("lihat semua >");
        lihatSemua.setFont(new Font("Arial", Font.BOLD, 24));

        lihatSemua.setForeground(new Color(236, 157, 36, 255));
        lihatSemua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LihatSemua(category);
            }
        });

        JPanel headCategory = new JPanel(new BorderLayout());
        headCategory.add(categoryLabel, BorderLayout.CENTER);
        headCategory.add(lihatSemua, BorderLayout.EAST);
        headCategory.setBackground(new Color(217,217,217,255));
        headCategory.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        categoryPanel.add(headCategory, BorderLayout.NORTH);

        JPanel KonserListPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        KonserListPanel.setBackground(Color.WHITE);

        JPanel KesenianPanel = new JPanel();
        KesenianPanel.setLayout(new BoxLayout(KesenianPanel, BoxLayout.X_AXIS));
        KesenianPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 20));
        KesenianPanel.setBackground(new Color(217,217,217,255));
        KesenianPanel.add(Box.createHorizontalStrut(30));
        int i = 0;
        for (Kesenian kes : list) {
            ImageIcon poster = new ImageIcon(kes.getImage());
            Image posterImg = poster.getImage();

            Image posterImgScaled = posterImg.getScaledInstance(270, 350, Image.SCALE_SMOOTH);
            ImageIcon posterScaled = new ImageIcon(posterImgScaled);

            JLabel posterLabel = new JLabel(posterScaled);

            posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            posterLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new DetailKesenian(kes);
                }
            });
            KesenianPanel.add(posterLabel);

            KesenianPanel.add(Box.createHorizontalStrut(50));
            i++;
            if (i == 4) {
                break;
            }
        }

        categoryPanel.add(KesenianPanel, BorderLayout.CENTER);
        return categoryPanel;

    }

    // public static void main(String[] args) {
    //     new DaftarKesenian();
    // }
}