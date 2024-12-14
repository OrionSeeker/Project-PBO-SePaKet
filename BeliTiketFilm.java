import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BeliTiketFilm {
    private JFrame frame;
    private JPanel mainPanel;
    private int totalHarga = 0; 
    private JLabel hargaLabel;
    private int idJamTayang;
    private ArrayList<Seat> pesanan = new ArrayList<>();
    private int countdownTime = 60; 
    private Thread timerThread;

    public BeliTiketFilm(int idJamTayang) {
        this.idJamTayang = idJamTayang;
        frame = new JFrame("Beli Tiket Film");
        frame.setSize(1440, 1080);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // Tambahkan WindowListener untuk menghentikan thread saat frame ditutup
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopTimerThread();
            }
        });

        mainPanel = new JPanel(new BorderLayout());

        ActionListener backButtonListener = e -> {
            frame.dispose();
        };

        mainPanel.add(Head.createHeaderPanel(backButtonListener), BorderLayout.NORTH);
        mainPanel.add(createMainContentPanel(), BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        startTimerThread();
    }

    private JScrollPane createMainContentPanel() {
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBackground(Color.WHITE);

        JPanel atasPanel = new JPanel(new BorderLayout());
        atasPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));
        atasPanel.setBackground(new Color(217, 217, 217, 100));

        JPanel infoTempat = new JPanel();
        infoTempat.setLayout(new BoxLayout(infoTempat, BoxLayout.X_AXIS));
        infoTempat.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Menambahkan info "Tidak Tersedia" dan "Tersedia"
        ImageIcon icon = new ImageIcon("asset/tidakTersedia.png");
        Image img = icon.getImage();
        Image imgScaled = img.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon iconScaled = new ImageIcon(imgScaled);
        JLabel iconTidakTersedia = new JLabel(iconScaled);

        JLabel tidakTersediaLabel = new JLabel("Tidak Tersedia");
        tidakTersediaLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tidakTersediaLabel.setForeground(new Color(236, 157, 36, 255));

        img = new ImageIcon("asset/Tersedia.png").getImage();
        imgScaled = img.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        iconScaled = new ImageIcon(imgScaled);
        JLabel iconTersedia = new JLabel(iconScaled);

        JLabel TersediaLabel = new JLabel("Tersedia");
        TersediaLabel.setFont(new Font("Arial", Font.BOLD, 20));
        TersediaLabel.setForeground(new Color(236, 157, 36, 255));

        infoTempat.add(Box.createHorizontalStrut(55));
        infoTempat.add(iconTidakTersedia);
        infoTempat.add(Box.createHorizontalStrut(3));
        infoTempat.add(tidakTersediaLabel);
        infoTempat.add(Box.createHorizontalStrut(790));
        infoTempat.add(iconTersedia);
        infoTempat.add(Box.createHorizontalStrut(3));
        infoTempat.add(TersediaLabel);

        JPanel pilihTempat = new JPanel();
        pilihTempat.setLayout(new BorderLayout());

        JPanel colums = new JPanel();
        colums.setLayout(new BoxLayout(colums, BoxLayout.Y_AXIS));

        final int hargaPerKursi = 30000;
        ArrayList<Seat> seatS = ConnectKeDB.getSeat(idJamTayang);
        int kursiTemp[] = new int[seatS.size() + 1];
        for (int i = 0; i < seatS.size(); i++) {
            kursiTemp[i] = seatS.get(i).getNoKursi();
        }
        int count = 1;
        int temp = 0;
        for (int i = 0; i < 8; i++) {
            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
            row.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            for (int j = 0; j < 17; j++) {
                boolean cek = true;
                if (kursiTemp[temp] == count) {
                    img = new ImageIcon("asset/TidakTersedia.png").getImage();
                    if (temp < kursiTemp.length - 1) {
                        temp++;
                        cek = false;
                    }
                } else {
                    img = new ImageIcon("asset/Tersedia.png").getImage();
                }
                imgScaled = img.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
                iconScaled = new ImageIcon(imgScaled);
                JLabel kursi = new JLabel(iconScaled);
                kursi.setLayout(new BorderLayout());
                JLabel kursiLabel = new JLabel("" + ((char) (i + 65)) + (j + 1));
                kursiLabel.setFont(new Font("Arial", Font.BOLD, 20));
                kursiLabel.setForeground(new Color(236, 157, 36, 255));
                kursiLabel.setHorizontalAlignment(SwingConstants.CENTER);
                kursi.add(kursiLabel, BorderLayout.CENTER);

                // Status kursi (tersedia atau tidak)
                boolean[] kursiStatus = { false }; // false = tersedia, true = tidak tersedia

                // Menambahkan MouseListener untuk memilih kursi
                final int finalI = i;
                final int finalJ = j;
                final int finalCount = count;
                if (cek) {
                    kursi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (!kursiStatus[0]) { // Jika kursi tersedia
                                // Ubah gambar kursi menjadi tidak tersedia dan tambah harga
                                ImageIcon newIcon = new ImageIcon(new ImageIcon("asset/TidakTersedia.png").getImage()
                                        .getScaledInstance(55, 55, Image.SCALE_SMOOTH));
                                kursi.setIcon(newIcon);
                                kursiStatus[0] = true; // Tandai kursi sebagai tidak tersedia
                                totalHarga += hargaPerKursi; // Tambah harga
                                String tempatDuduk = "" + ((char) (finalI + 65)) + (finalJ + 1);
                                boolean cek = true;
                                for (Seat seat : pesanan) {
                                    if (seat.getTempatDuduk().equals(tempatDuduk)) {
                                        cek = false;
                                        break;
                                    }
                                }
                                if (cek) {
                                    pesanan.add(new Seat(idJamTayang, finalCount, 0, tempatDuduk));
                                }

                            } else { // Jika kursi sudah tidak tersedia
                                // Ubah gambar kursi kembali menjadi tersedia dan kurangi harga
                                ImageIcon newIcon = new ImageIcon(new ImageIcon("asset/Tersedia.png").getImage()
                                        .getScaledInstance(55, 55, Image.SCALE_SMOOTH));
                                kursi.setIcon(newIcon);
                                kursiStatus[0] = false; // Tandai kursi sebagai tersedia
                                totalHarga -= hargaPerKursi; // Kurangi harga
                                String tempatDuduk = "" + ((char) (finalI + 65)) + (finalJ + 1);
                                for (Seat seat : pesanan) {
                                    if (seat.getTempatDuduk().equals(tempatDuduk)) {
                                        pesanan.remove(seat);
                                        break;
                                    }
                                }

                            }
                            updateTotalHarga(); // Update total harga setelah perubahan
                        }
                    });
                }

                row.add(kursi);
                row.add(Box.createHorizontalStrut(10));
                count++;
            }
            colums.add(row);
            colums.add(Box.createVerticalStrut(10));
        }
        pilihTempat.add(colums, BorderLayout.CENTER);

        img = new ImageIcon("asset/layarBioskop.png").getImage();
        imgScaled = img.getScaledInstance(1200, 70, Image.SCALE_SMOOTH);
        iconScaled = new ImageIcon(imgScaled);
        JLabel layarBioskop = new JLabel(iconScaled);

        JPanel pesanPanel = new JPanel(new BorderLayout());
        pesanPanel.add(infoTempat, BorderLayout.NORTH);
        pesanPanel.add(pilihTempat, BorderLayout.CENTER);
        pesanPanel.add(layarBioskop, BorderLayout.SOUTH);
        atasPanel.add(pesanPanel, BorderLayout.CENTER);

        JPanel bawahPanel = new JPanel(new BorderLayout());

        JPanel rincian = new JPanel();
        rincian.setLayout(new BoxLayout(rincian, BoxLayout.X_AXIS));
        rincian.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        rincian.setBackground(new Color(217, 217, 217, 100));

        JPanel rincianKiri = new JPanel();
        rincianKiri.setLayout(new BoxLayout(rincianKiri, BoxLayout.Y_AXIS));

        JLabel rincianLabel = new JLabel("Total Harga");
        rincianLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rincianLabel.setForeground(new Color(236, 157, 36, 255));

        hargaLabel = new JLabel("Rp. 0");
        hargaLabel.setFont(new Font("Arial", Font.BOLD, 20));
        hargaLabel.setForeground(new Color(236, 157, 36, 255));

        rincianKiri.add(rincianLabel);
        rincianKiri.add(Box.createVerticalStrut(5));
        rincianKiri.add(hargaLabel);

        JPanel beliPanel = new JPanel();
        beliPanel.setBackground(new Color(47, 47, 128, 255));
        beliPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        beliPanel.setLayout(new BoxLayout(beliPanel, BoxLayout.X_AXIS));

        JLabel beli = new JLabel("BELI");
        beli.setFont(new Font("Arial", Font.BOLD, 20));
        beli.setForeground(new Color(236, 157, 36, 255));

        beliPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = 0;
                boolean bool = false;
                totalHarga = pesanan.size() * hargaPerKursi;
                if (pesanan.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "Pilih kursi terlebih dahulu");
                    return;
                }
                for (Seat seat : pesanan) {
                    if (i == pesanan.size() - 1) {
                        bool = true;
                    }
                    ConnectKeDB.setSeat(seat.getIdJadwalTayang(), seat.getNoKursi(), seat.getTempatDuduk(), user.id, totalHarga, bool);
                    i++;
                }
                JOptionPane.showMessageDialog(frame, pesanan.size() + " Kursi berhasil dibeli");
                stopTimerThread(); // Hentikan timer saat tiket berhasil dibeli
                frame.dispose(); // Menutup frame
            }
        });
        beliPanel.add(beli);

        rincian.add(rincianKiri);
        rincian.add(Box.createHorizontalStrut(20));
        rincian.add(beliPanel);

        bawahPanel.add(rincian, BorderLayout.CENTER);

        mainContentPanel.add(atasPanel, BorderLayout.CENTER);
        mainContentPanel.add(Box.createVerticalStrut(20));
        mainContentPanel.add(bawahPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scrollPane;
    }

    private void updateTotalHarga() {
        hargaLabel.setText("Rp. " + totalHarga); // Update label dengan harga total
    }

    private void startTimerThread() {
        timerThread = new Thread(() -> {
            try {
                for (int i = countdownTime; i >= 0; i--) {
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    System.out.println("Timer: " + i + " detik");
                    Thread.sleep(1000); // Tunggu 1 detik
                }
                System.out.println("Waktu selesai!");
                JOptionPane.showMessageDialog(null, "Waktu pembelian tiket telah habis");
                frame.dispose();
            } catch (InterruptedException e) {
                System.out.println("Timer terganggu!");
            }
        });
        timerThread.start();
    }

    private void stopTimerThread() {
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
    }
}
