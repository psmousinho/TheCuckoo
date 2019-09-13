package view;

import entity.UserProfile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Constants;

public class UserResult extends JPanel {
    private UserProfile user;
    private Color background;
    private JButton btVisit;
    private JLabel usernameLabel;
    private JLabel nameLabel;
    private Home home;
    
    UserResult(UserProfile user, Color background, Home home) {
        this.user = user;
        this.background = background;
        this.home = home;
        
        initComponents();
    }

    private void initComponents() {
        setLayout(Constants.GRID_3);
        setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        
        usernameLabel = new JLabel();
        usernameLabel.setText("@" + user.getUsername());
        usernameLabel.setFont(Constants.LUCIDA);
        
        nameLabel = new JLabel();
        nameLabel.setText("(" + user.getUsername() + ")");
        nameLabel.setFont(Constants.LUCIDA);
        
        btVisit = new JButton();
        btVisit.setText("Visit");
        btVisit.setFont(Constants.LUCIDA);
        btVisit.setForeground(Constants.PURPLE);
        btVisit.setBackground(Constants.WHITE);
        btVisit.addActionListener(this::actionPerformed);
        
        add(usernameLabel);
        add(nameLabel);
        add(btVisit);
    }
    
    private void actionPerformed(ActionEvent evt) {
        home.changeScreenTemporary(new ProfileScreen(user, home, false));
    }
}