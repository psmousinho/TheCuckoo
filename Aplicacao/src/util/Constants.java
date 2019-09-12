package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Constants {
    public static final Dimension DIMENSION = new Dimension(380, 450);
    public static final Font LUCIDA = new Font("Lucida Grande", 0, 14);
    public static final GridLayout GRID_3 = new GridLayout(0, 3);

    public static final Color BLUE = new Color(0, 122, 255);
    public static final Color BROWN = new Color(162, 132, 94);
    public static final Color GRAY = new Color(142, 142, 147);
    public static final Color GREEN = new Color(40, 205, 65);
    public static final Color ORANGE = new Color(255, 149, 0);
    public static final Color PINK = new Color(255, 45, 85);
    public static final Color PURPLE = new Color(175, 82, 222);
    public static final Color RED = new Color(255, 59, 48);
    public static final Color YELLOW = new Color(255, 204, 0);
    public static final Color TEAL = new Color(100, 210, 255);
    public static final Color WHITE = new Color(255, 255, 255);

    public static final String DB_URL = "jdbc:postgresql://localhost:5432/cuckoo";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "";
    
    public static JLabel createLabel(String text, Color background) {
        JLabel label = new JLabel();
        label.setFont(LUCIDA);
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(background);
        label.setForeground(Color.BLACK);
        label.setOpaque(true);
        return label;
    }
}
