package view;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import util.Constants;
import util.SoundEffect;

public class Main extends JFrame {
    private JPanel contentPanel;
    
    public Main() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());

        initComponents();
    }
    
    private void initComponents() {
        setMinimumSize(Constants.DIMENSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPanel.add(new Landing());
        add(contentPanel);
        SoundEffect.CUCKOO.play();
    }
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
