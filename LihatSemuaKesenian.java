import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LihatSemuaKesenian implements app{
    private JFrame frame;
    private JPanel mainPanel;
    String status;
    public LihatSemuaKesenian(String status) {
        this.status = status;
        frame = new JFrame("Lihat Semua Kesenian");
        frame.setSize(1440, 1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);

        mainPanel.add(createMainContentPanel(), BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    @Override
    public JPanel createHeaderPanel(){
        return Head.createHeaderPanel(e -> frame.dispose());
    }
    @Override
    public JScrollPane createMainContentPanel() {
            JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBackground(Color.WHITE);
        ArrayList<Kesenian> kesenianList = ConnectKeDB.getKesenian(status);
        mainContentPanel.add(createCategoryPanel(status, kesenianList));
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        // Mengatur tipe scrollbar untuk hanya muncul jika diperlukan
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // jika tidak ingin scroll

        return scrollPane;
    }

    private static JPanel createCategoryPanel(String categoryTitle, ArrayList<Kesenian> kesenianList) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setBackground(Color.WHITE);
        categoryPanel.setLayout(new BorderLayout());

        JLabel categoryLabel = new JLabel(categoryTitle);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 40));
        categoryLabel.setForeground(new Color(236, 157, 36, 255));
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        categoryPanel.add(categoryLabel, BorderLayout.NORTH);

        DefaultListModel<Kesenian> filmListModel = new DefaultListModel<>();
        for (Kesenian film : kesenianList) {
            filmListModel.addElement(film);
        }

        JList<Kesenian> filmList = new JList<>(filmListModel);
        filmList.setCellRenderer(new FilmListRenderer());
        filmList.setBackground(Color.WHITE);
        filmList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        filmList.setVisibleRowCount(0);
        filmList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int index = filmList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        Kesenian selectedFilm = filmList.getModel().getElementAt(index);
                        new DetailKesenian(selectedFilm);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(filmList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.BLACK);

        categoryPanel.add(scrollPane, BorderLayout.CENTER);

        return categoryPanel;
    }

    private static class FilmListRenderer extends JPanel implements ListCellRenderer<Kesenian> {
        private JLabel imageLabel;
        private JLabel titleLabel;
        private JLabel dateLabel;

        public FilmListRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setPreferredSize(new Dimension(450, 600));
            setBackground(Color.BLACK);

            imageLabel = new JLabel();
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            titleLabel = new JLabel();
            titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
            titleLabel.setForeground(new Color(236, 157, 36, 255));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            dateLabel = new JLabel();
            dateLabel.setFont(new Font("Arial", Font.BOLD, 15));
            dateLabel.setForeground(new Color(236, 157, 36, 255));
            dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            add(imageLabel);
            add(Box.createVerticalStrut(5));
            add(titleLabel);
            add(dateLabel);

            setBorder(BorderFactory.createEmptyBorder());
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Kesenian> list, Kesenian film, int index, boolean isSelected, boolean cellHasFocus) {
            imageLabel.setIcon(new ImageIcon(new ImageIcon(film.getImage()).getImage().getScaledInstance(370, 500, Image.SCALE_SMOOTH)));
            titleLabel.setText(film.getTitle());
            dateLabel.setText(film.getDate());

            if (isSelected) {
                setBackground(Color.DARK_GRAY);
            } else {
                setBackground(Color.WHITE);
            }
            return this;
        }
    }

    public static void main(String[] args) {
        new LihatSemuaKesenian("Sedang Tayang");
    }
}