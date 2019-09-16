package view;

import entity.Post;
import entity.Topic;
import entity.UserProfile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Constants;
import util.DBConnection;

public class TopicResult extends JPanel {
    private Home home;
    private Topic topic;
    private boolean showingPC;
    private boolean showingPosts;
    
    private final Color background;
    
    private JLabel topicNameLabel;
    private JLabel topicInfoLabel;
    
    private JPanel topLine;
    private JPanel bottomLine;
    private JPanel pcPanel;
    
    private JButton btShowPC;
    
    TopicResult(Topic topic, Color background, Home home) {
        this.topic = topic;
        this.background = background;
        this.home = home;
        showingPC = false;
        showingPosts = false;
        
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(background);
        
        topLine = new JPanel();
        topLine.setLayout(Constants.GRID_2);
        topLine.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
        topLine.setBackground(background);
        
        bottomLine = new JPanel();
        bottomLine.setLayout(Constants.GRID_1);
        bottomLine.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
        bottomLine.setBackground(background);
        
        pcPanel = new JPanel();
        pcPanel.setLayout(new BoxLayout(pcPanel, BoxLayout.Y_AXIS));
        pcPanel.setBackground(background);
        
        topicNameLabel = new JLabel();
        topicNameLabel.setText("#" + topic.getName());
        topicNameLabel.setFont(Constants.LUCIDA);
        
        topicInfoLabel = new JLabel();
        topicInfoLabel.setText("<html><div WIDTH="+getWidth()+"><b>Last update:</b> " + topic.getDate()+ "<br></html>");
        topicInfoLabel.setFont(Constants.LUCIDA);
        topicInfoLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                topicInfoLabel.setText("<html><div WIDTH="+getWidth()+"><b>Last update:</b> " + topic.getDate()+ "<br></html>");            
            }
        });
        if(background == Constants.GRAY) {
            topicInfoLabel.setForeground(Constants.WHITE);
            topicNameLabel.setForeground(Constants.WHITE);
        }
        
        btShowPC = new JButton();
        btShowPC.setText("Show posts/comments");
        btShowPC.setFont(Constants.LUCIDA);
        btShowPC.setForeground(Constants.PURPLE);
        btShowPC.setBackground(Constants.WHITE);
        btShowPC.addActionListener(this::btShowPCActionPerformed);
        
        topLine.add(topicNameLabel);
        topLine.add(btShowPC);
               
        bottomLine.add(topicInfoLabel);

        getRelatedPosts();
        
        add(topLine);
        add(bottomLine);
    }
    
    private void btShowPCActionPerformed(ActionEvent evt) {
        if(showingPC) {
            btShowPC.setText("Show posts/comments");
            btShowPC.setForeground(Constants.PURPLE);
            btShowPC.setBackground(Constants.WHITE);
            remove(pcPanel);
            add(bottomLine);
            revalidate();
        } else {
            btShowPC.setText("Hide posts/comments");
            btShowPC.setBackground(Constants.PURPLE);
            btShowPC.setForeground(Constants.WHITE);
            remove(bottomLine);
            add(pcPanel);
            revalidate();
        }
        showingPC = !showingPC;
    }
    
    private void getRelatedPosts() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("select author, datestamp, ptext, foto from post inner join topiccomment on post.author = topiccomment.cpauthor and post.datestamp = topiccomment.cpdate where tname = 'wondering' union select author, datestamp, ptext, foto from post inner join topicpost on post.author = topicpost.pauthor and post.datestamp = topicpost.pdate where tname = 'wondering' order by datestamp desc;");
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                stmt = con.prepareStatement("SELECT * from userprofile where login = '" + result.getString("author") + "';");
                ResultSet result2 = stmt.executeQuery();
                result2.next();
                UserProfile author = new UserProfile(result2.getString("realname") ,result2.getString("login"), result2.getString("bio"), result2.getBoolean("visibility"), result2.getInt("nfollowers"), result2.getInt("nfollowing"), result2.getString(("lasttime")));
                Post post = new Post(author, result.getString("datestamp"),result.getString("ptext"),result.getString("foto"));
                pcPanel.add(new PostScreen(post, home, post.getAuthor().getUsername().equals(UserProfile.CURRENT_USER.getUsername())));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}