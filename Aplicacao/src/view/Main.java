package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import org.postgresql.util.PSQLException;

public class Main extends JFrame {
    public static final Dimension DIMENSION = new Dimension(380, 450);
    public static final Color PURPLE = new Color(175, 82, 222);
    public static final Color TEAL = new Color(100, 210, 255);
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
    
    public static void executeUpdate(String command, String... args) throws PSQLException, Exception {
        Connection con = null;
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        // cria um preparedStatement
        PreparedStatement stmt = con.prepareStatement(command);
        // preenche os valores
        int i = 1;
        for (String a : args) {
            stmt.setString(i, a);
            i++;
        }

        // executa
        stmt.executeUpdate();
        stmt.close();
        con.close();
        
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
