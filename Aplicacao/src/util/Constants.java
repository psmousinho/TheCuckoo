/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author aluno
 */
public class Constants {
    public enum Relation {
        BLOCKED(3), FOLLOWING(2), REQUESTED(1), NA(0);
        
        private final int code;
        
        Relation(int code) {
            this.code = code;
        }
        
        public int getCode() {
            return code;
        }
    }
    
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
    public static final Color WHITE = new Color(255, 255, 255);

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
