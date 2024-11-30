import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Beranda frame = new Beranda();
            LoginRegister frame = new LoginRegister();
            frame.setVisible(true);
        });
    }
}