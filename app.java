import javax.swing.JPanel;
import javax.swing.JScrollPane;

public interface app {
    public abstract JPanel createHeaderPanel();
    public abstract JScrollPane createMainContentPanel();
}
