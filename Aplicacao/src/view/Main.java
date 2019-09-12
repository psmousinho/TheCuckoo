package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main extends JFrame {
    public static final Dimension DIMENSION = new Dimension(380, 450);
    public static final Color PURPLE = new Color(175, 82, 222);
    public static final Color TEAL = new Color(100, 210, 255);
    public static final Color ORANGE = new Color(255, 149, 0);
    
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/cuckoo";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "";
    
    private JPanel contentPanel;
    
    public Main() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());

        initComponents();
    }
    
    private void initComponents() {
        setMinimumSize(DIMENSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPanel.add(new Landing());
        add(contentPanel);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
