package view;

import entity.UserProfile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Constants;

public class UserResult extends JPanel {
    private UserProfile user;
    private Home home;
    
    private Color background;
    private JButton btVisit;
    private JLabel usernameLabel;
    private JLabel bioLabel;
    private JPanel topLine;
    private JPanel bottomLine;
    
    UserResult(UserProfile user, Color background, Home home) {
        this.user = user;
        this.background = background;
        this.home = home;
        
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(background);
        
        topLine = new JPanel();
        topLine.setLayout(Constants.GRID_2);
        topLine.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        topLine.setBackground(background);
        
        bottomLine = new JPanel();
        bottomLine.setLayout(Constants.GRID_1);
        bottomLine.setMaximumSize(new Dimension(Short.MAX_VALUE, 60));
        bottomLine.setBackground(background);
        
        usernameLabel = new JLabel();
        usernameLabel.setText("@" + user.getUsername());
        usernameLabel.setFont(Constants.LUCIDA);
        
        bioLabel = new JLabel();
        bioLabel.setText("<html><div WIDTH="+getWidth()+"><b>Name:</b> " + user.getName() + "<br><b>Followers:</b> " + user.getNumberFollowers() + "<br><b>Bio:</b> " + user.getBio() + "</html>");
        bioLabel.setFont(new Font("Lucida Grande", 0, 11));
        bioLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                bioLabel.setText("<html><div WIDTH=" + getWidth() + "><b>Name:</b> " + user.getName() + "<br><b>Followers:</b> " + user.getNumberFollowers() + "<br><b>Bio:</b> " + user.getBio() + "</html>");
            }
        });
        if(background == Constants.GRAY) {
            usernameLabel.setForeground(Constants.WHITE);
            bioLabel.setForeground(Constants.WHITE);
        }
        
        btVisit = new JButton();
        btVisit.setText("Visit");
        btVisit.setFont(Constants.LUCIDA);
        btVisit.setForeground(Constants.PURPLE);
        btVisit.setBackground(Constants.WHITE);
        btVisit.addActionListener(this::actionPerformed);
        
        topLine.add(usernameLabel);
        topLine.add(btVisit);
               
        bottomLine.add(bioLabel);
        
        add(topLine);
        add(bottomLine);
    }
    
    private void actionPerformed(ActionEvent evt) {
        home.changeScreenTemporary(new ProfilePanel(user, home, user.getUsername().equals(UserProfile.CURRENT_USER.getUsername())));
    }
}