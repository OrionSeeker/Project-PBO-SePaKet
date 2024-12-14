import javax.swing.*;
import java.awt.*;

public class LoadingThread extends Thread {
    private JFrame parentFrame;

    public LoadingThread(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    @Override
    public void run() {
        // Create a loading panel
        JPanel loadingPanel = new JPanel(new BorderLayout());
        loadingPanel.setBackground(new Color(47, 47, 128));

        JLabel loadingLabel = new JLabel("Logging out...");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 30));
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setString("Please wait...");
        progressBar.setStringPainted(true);

        loadingPanel.add(loadingLabel, BorderLayout.CENTER);
        loadingPanel.add(progressBar, BorderLayout.SOUTH);

        // Replace the parent frame's content pane with the loading panel
        parentFrame.setContentPane(loadingPanel);
        parentFrame.revalidate();
        parentFrame.repaint();

        // Simulate a delay for the loading screen
        try {
            Thread.sleep(2000);  // Simulate a 2-second loading time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // After loading, close all frames and show the login screen
        SwingUtilities.invokeLater(() -> {
            // Dispose all top-level windows (close all frames)
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                window.dispose(); // Close all open windows
            }

            // Open the login register screen
            LoginRegister loginRegister = new LoginRegister();
            loginRegister.setVisible(true); // Make the LoginRegister screen visible
        });
    }
}
