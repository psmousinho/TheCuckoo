package view;

import entity.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import util.Constants;
import util.DBConnection;

public class NotificationsScreen extends JPanel {
    // Status 0 = TagPost; 1 = TagCmmnt; 2 = Follower Request; 3 = Follower Accept; 4 = New Follower
    private enum NotificationState {
        NOTHING, TAGPOSTS, TAGCOMMENTS, FOLLOWERS;
    }
    
    private UserProfile user;
    private Home home;
    private NotificationState state;
    private List<NotificationPost> posts;
    private List<NotificationComment> comments;
    private List<NotificationFollower> followers;
    
    public NotificationsScreen(UserProfile user, Home home) {
        this.user = user;
        this.home = home;
        this.state = NotificationState.NOTHING;
        
        posts = new LinkedList<>();
        comments = new LinkedList<>();
        followers = new LinkedList<>();
        
        initComponents();
        
        getNotifications();
        if (UserProfile.CURRENT_USER.isPrivate()) {
            btFollowers.setText("Follow Requests");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        optionsPanel = new javax.swing.JPanel();
        btTagPost = new javax.swing.JButton();
        btTagComment = new javax.swing.JButton();
        btFollowers = new javax.swing.JButton();

        optionsPanel.setBackground(Constants.TEAL);
        optionsPanel.setLayout(new java.awt.GridLayout(1, 0));

        btTagPost.setBackground(Constants.WHITE);
        btTagPost.setForeground(Constants.TEAL);
        btTagPost.setText("Tag Cuckoos");
        btTagPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTagPostActionPerformed(evt);
            }
        });
        optionsPanel.add(btTagPost);

        btTagComment.setBackground(Constants.WHITE);
        btTagComment.setForeground(Constants.TEAL);
        btTagComment.setText("Tag Comment");
        btTagComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTagCommentActionPerformed(evt);
            }
        });
        optionsPanel.add(btTagComment);

        btFollowers.setBackground(Constants.WHITE);
        btFollowers.setForeground(Constants.TEAL);
        btFollowers.setText("New Followers");
        btFollowers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFollowersActionPerformed(evt);
            }
        });
        optionsPanel.add(btFollowers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane)
            .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btTagPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTagPostActionPerformed
        if(state != NotificationState.TAGPOSTS) {
            state = NotificationState.TAGPOSTS;
            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            posts.forEach((np) -> {
                cont.add(np);
            });
            cont.revalidate();
            scrollPane.getViewport().setView(cont);
            updateButtons();
        }
    }//GEN-LAST:event_btTagPostActionPerformed

    private void btTagCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTagCommentActionPerformed
        if(state != NotificationState.TAGCOMMENTS) {
            state = NotificationState.TAGCOMMENTS;
            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            comments.forEach((nc) -> {
                cont.add(nc);
            });
            cont.revalidate();
            scrollPane.getViewport().setView(cont);
            updateButtons();
        }
    }//GEN-LAST:event_btTagCommentActionPerformed

    private void btFollowersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFollowersActionPerformed
        if(state != NotificationState.FOLLOWERS) {
            state = NotificationState.FOLLOWERS;
            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            followers.forEach((nf) -> {
                cont.add(nf);
            });
            cont.revalidate();
            scrollPane.getViewport().setView(cont);
            updateButtons();
        }
    }//GEN-LAST:event_btFollowersActionPerformed

    private int checkRelation(String source, String target) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("select * from userrel where srcuser = '" + source + "' and tgtuser = '" + target + "' ");
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            return result.getInt("status");
        } else {
            return 0;
        }
    }
    
    public void getNotifications() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from notifications inner join userprofile on userprofile.login = notifications.pauthor where target = '" + UserProfile.CURRENT_USER.getUsername() + "' and code = 0 union select * from notifications inner join userprofile on userprofile.login = notifications.cpauthor where target = '" + UserProfile.CURRENT_USER.getUsername() + "' and code = 1 union select * from notifications inner join userprofile on userprofile.login = notifications.src where target = '" + UserProfile.CURRENT_USER.getUsername() + "' and (code = 2 or code = 3 or code = 4) order by ndate desc;");
            ResultSet result = stmt.executeQuery();
            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            while (result.next()) {
                UserProfile src = new UserProfile(result.getString("realname"), result.getString("login"), result.getString("bio"), result.getBoolean("visibility"), result.getInt("nfollowers"), result.getInt("nfollowing"), result.getString("lasttime"));
                switch(result.getInt("code")) {
                    case 0: // Tag post
                        NotificationPost np;
                        if (!src.isPrivate() || checkRelation(UserProfile.CURRENT_USER.getUsername(), src.getUsername()) == 2) {
                            PreparedStatement stmt2 = con.prepareStatement("Select * from post where author = '" + result.getString("pauthor") + "' and datestamp = '" + result.getString("pdate") + "';");
                            ResultSet resultPost = stmt2.executeQuery();
                            resultPost.next();
                            Post post = new Post(src, resultPost.getString("datestamp"), resultPost.getString("ptext"));
                            np = new NotificationPost(post, home);
                            posts.add(np);
                            cont.add(np);
                        }
                        break;
                    case 1: // Tag comment
                        NotificationComment nc;
                        if (!src.isPrivate() || checkRelation(UserProfile.CURRENT_USER.getUsername(), src.getUsername()) == 2) {
                            PreparedStatement stmt2 = con.prepareStatement("Select * from post where author = '" + result.getString("cpauthor") + "' and datestamp = '" + result.getString("cpdate") + "';");
                            ResultSet resultPost = stmt2.executeQuery();
                            resultPost.next();

                            Post post = new Post(src, result.getString("cpdate"), resultPost.getString("ptext"));
                            nc = new NotificationComment(post, result.getString("cauthor"), result.getString("cdate"), home);
                            
                            comments.add(nc);
                            cont.add(nc);
                        }
                        break;
                    case 2: // Follower operation
                    case 3:
                    case 4:
                        NotificationFollower nf;
                        nf = new NotificationFollower(src, result.getString("ndate"), result.getInt("code"), home);
                        followers.add(nf);
                        cont.add(nf);
                        break;
                    default: // NOP
                        break;
                }
            }
            cont.revalidate();
            scrollPane.getViewport().setView(cont);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateButtons() {
        for (Component c : optionsPanel.getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(Color.WHITE);
                c.setForeground(Constants.TEAL);
            }
        }
        switch (state) {
            case FOLLOWERS:
                btFollowers.setForeground(Constants.WHITE);
                btFollowers.setBackground(Constants.ORANGE);
                break;
            case TAGCOMMENTS:
                btTagComment.setForeground(Constants.WHITE);
                btTagComment.setBackground(Constants.ORANGE);
                break;
            case TAGPOSTS:
            btTagPost.setForeground(Constants.WHITE);
            btTagPost.setBackground(Constants.ORANGE);
                break;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFollowers;
    private javax.swing.JButton btTagComment;
    private javax.swing.JButton btTagPost;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
