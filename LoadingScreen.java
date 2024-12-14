import javax.swing.*;
import java.awt.*;

public class LoadingScreen {
    public LoadingScreen(){
        JFrame frame = new JFrame("SePaKet: Sistem Pemesanan Tiket");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720,360);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JProgressBar bar = new JProgressBar(0,100);
        bar.setValue(0);
        bar.setStringPainted(true);

        JLabel statusBar = new JLabel();

        JLabel gambarLoading = new JLabel();
        ImageIcon gambar = new ImageIcon(new ImageIcon("./Asset/LoadingScreen/1.png").getImage().getScaledInstance(720, 360, Image.SCALE_SMOOTH));
        gambarLoading.setIcon(gambar);

        Thread loadingBarThread = new Thread(new Runnable() {
            public void run(){
                for(int i=0;i<=100;i++){
                    try{
                        Thread.sleep(25);
                    }catch(InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                    bar.setValue(i);

                    if(i<=33){
                        statusBar.setText("Menghubungkan dengan database MySQL...");
                    }
                    else if(i<=66){
                        statusBar.setText("Menyiapkan UI...");
                        ImageIcon gambar = new ImageIcon(new ImageIcon("./Asset/LoadingScreen/2.png").getImage().getScaledInstance(720, 360, Image.SCALE_SMOOTH));
                        gambarLoading.setIcon(gambar);
                    }
                    else if(i<=90){
                        statusBar.setText("Menyelesaikan proses loading...");
                        ImageIcon gambar = new ImageIcon(new ImageIcon("./Asset/LoadingScreen/3.png").getImage().getScaledInstance(720, 360, Image.SCALE_SMOOTH));
                        gambarLoading.setIcon(gambar);
                    }
                    else{
                        statusBar.setText("Loading selesai, membuka aplikasi...");
                        ImageIcon gambar = new ImageIcon(new ImageIcon("./Asset/LoadingScreen/4.png").getImage().getScaledInstance(720, 360, Image.SCALE_SMOOTH));
                        gambarLoading.setIcon(gambar);
                    }
                }

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.dispose();
                        JFrame frameLogin = new LoginRegister();
                        frameLogin.setVisible(true);
                        frameLogin.setLocationRelativeTo(null);
                    }
                });
            }
        });

        panel.add(bar, BorderLayout.NORTH);
        panel.add(statusBar, BorderLayout.SOUTH);
        panel.add(gambarLoading, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        loadingBarThread.start();
    }    
}
