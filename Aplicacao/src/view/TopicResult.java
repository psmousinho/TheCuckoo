package view;

import entity.Topic;
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

public class TopicResult extends JPanel {
    private Home home;
    private Topic topic;
    
    private Color background;
    private JButton btVisit;
    private JLabel topicNameLabel;
    private JLabel topicInfoLabel;
    private JPanel topLine;
    private JPanel bottomLine;
    
    TopicResult(Topic topic, Color background, Home home) {
        this.topic = topic;
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
        
        topicNameLabel = new JLabel();
        topicNameLabel.setText("#" + topic.getName());
        topicNameLabel.setFont(Constants.LUCIDA);
        
        topicInfoLabel = new JLabel();
        topicInfoLabel.setText("<html><div WIDTH="+getWidth()+"><b>Last update:</b> " + topic.getDate()+ "<br><b>Number of Posts:</b> " + topic.getNumberPosts()+ "<br><b>Number of Comments:</b> " + topic.getNumberComments() + "</html>");
        topicInfoLabel.setFont(new Font("Lucida Grande", 0, 11));
        topicInfoLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                topicInfoLabel.setText("<html><div WIDTH="+getWidth()+"><b>Last update:</b> " + topic.getDate()+ "<br><b>Number of Posts:</b> " + topic.getNumberPosts()+ "<br><b>Number of Comments:</b> " + topic.getNumberComments() + "</html>");            
            }
        });
        
        btVisit = new JButton();
        btVisit.setText("Show posts/comments");
        btVisit.setFont(Constants.LUCIDA);
        btVisit.setForeground(Constants.PURPLE);
        btVisit.setBackground(Constants.WHITE);
        btVisit.addActionListener(this::actionPerformed);
        
        topLine.add(topicNameLabel);
        topLine.add(btVisit);
               
        bottomLine.add(topicInfoLabel);
        
        add(topLine);
        add(bottomLine);
    }
    
    private void actionPerformed(ActionEvent evt) {
        //TODO show posts/comments
    }
}