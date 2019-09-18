package view;

import entity.*;
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import util.Constants;
import util.DBConnection;

public class PostScreen extends JPanel {

    private Post post;
    private Home home;
    private boolean self;
    private boolean commenting;
    private Container comments;
    
    public PostScreen(Post post, Home home, boolean self) {
        this.post = post;
        this.home = home;
        this.self = self;
        this.commenting = false;
        
        initComponents();

        updateComments();
        if(post.getPhoto() == null) {
            btImage.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCommnt = new javax.swing.JTextArea();
        contentPanel = new javax.swing.JPanel();
        author = new javax.swing.JLabel();
        txtPost = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        optionsPanel = new javax.swing.JPanel();
        btImage = new javax.swing.JButton();
        comment = new javax.swing.JButton();

        txtCommnt.setColumns(20);
        txtCommnt.setRows(5);

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));

        author.setForeground(Constants.ORANGE);
        author.setText("@" + post.getAuthor().getUsername() + " at " + post.getDate());
        author.setToolTipText("\"Go to Profile\"");
        author.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorMouseClicked(evt);
            }
        });

        txtPost.setForeground(Constants.ORANGE);
        txtPost.setText("<html><div WIDTH=" + txtPost.getWidth() + ">" + post.getText() + "</html>");
        txtPost.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                txtPostComponentResized(evt);
            }
        });

        optionsPanel.setBackground(Constants.WHITE);
        optionsPanel.setLayout(new java.awt.GridLayout());

        btImage.setBackground(new java.awt.Color(255, 255, 255));
        btImage.setForeground(Constants.ORANGE);
        btImage.setText("View Image");
        btImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImageActionPerformed(evt);
            }
        });
        optionsPanel.add(btImage);

        comment.setBackground(new java.awt.Color(255, 255, 255));
        comment.setForeground(Constants.ORANGE);
        comment.setText("New Comment");
        comment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentActionPerformed(evt);
            }
        });
        optionsPanel.add(comment);

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addComponent(author, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(author)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPost, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void authorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authorMouseClicked
        home.changeScreenTemporary(new ProfilePanel(post.getAuthor(), home, self));
    }//GEN-LAST:event_authorMouseClicked

    private void commentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentActionPerformed
        if (commenting) {
            commenting = false;
            comment.setText("New Comment");
            String commnt = txtCommnt.getText();
            if (!commnt.equals("")) {
                Connection con = DBConnection.getConnection();
                try {
                    Timestamp now = new Timestamp(new Date().getTime());
                    String st = String.format("INSERT INTO commnt values('%s','%s','%s','%s','%s');",
                            UserProfile.CURRENT_USER.getUsername(), post.getAuthor().getUsername(), post.getDate(), now, txtCommnt.getText());
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(st);
                    addTags(con, now);
                    addTopics(con, now);
                    updateComments();
                } catch (SQLException ex) {
                    Logger.getLogger(Cuckoo.class.getName()).log(Level.SEVERE, null, ex);
                }
                txtCommnt.setText("");
            }
            scrollPane.getViewport().setView(comments);
        } else {
            commenting = true;
            comment.setText("Comment");
            scrollPane.getViewport().setView(txtCommnt);
        }
        this.revalidate();
    }//GEN-LAST:event_commentActionPerformed

    private void btImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImageActionPerformed
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(post.getPhoto()));
        JOptionPane.showMessageDialog(this, image, "Image", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_btImageActionPerformed

    private void txtPostComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txtPostComponentResized
        txtPost.setText("<html><div WIDTH=" + txtPost.getWidth() + ">" + post.getText() + "</html>");
    }//GEN-LAST:event_txtPostComponentResized

    private void addTags(Connection con, Timestamp now) throws SQLException {
        Pattern regex = Pattern.compile("@\\w{4,32}");
        Matcher matcher = regex.matcher(txtCommnt.getText());
        boolean notify;
        while (matcher.find()) {
            notify = false;
            String tagUser = matcher.group().substring(1);
            String st = String.format("SELECT * FROM userprofile where login = '%s'", tagUser);
            PreparedStatement stmt = con.prepareStatement(st);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                st = String.format("INSERT INTO tagcommntuser values( '%s', '%s', '%s', '%s', '%s');",
                        UserProfile.CURRENT_USER.getUsername(), now, post.getAuthor().getUsername(), post.getDate(), tagUser);
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
                if(!post.getAuthor().isPrivate()) { // Public profile, no further action needed
                    notify = true;
                } else { // Private profile, must check is tagged user follows author
                    st = String.format("SELECT * FROM userrel where tgtuser = '%s' and srcuser = '%s' and status = 2;", post.getAuthor().getUsername(), tagUser);
                    stmt = con.prepareStatement(st);
                    ResultSet rs2 = stmt.executeQuery();
                    notify = rs2.next();
                }
                if(notify) {
                    st = String.format("INSERT INTO notifications(target, src, ndate, code, cauthor, cdate, cpauthor, cpdate) values('%s','%s','%s', 1, '%s', '%s', '%s', '%s');", tagUser, UserProfile.CURRENT_USER.getUsername(), now, UserProfile.CURRENT_USER.getUsername(), now, post.getAuthor().getUsername(), post.getDate());
                    stmt = con.prepareStatement(st);
                    stmt.executeUpdate();
                }
            }

        }
    }

    private void addTopics(Connection con, Timestamp now) throws SQLException {
        Pattern regex = Pattern.compile("#\\w{4,32}");
        Matcher matcher = regex.matcher(txtCommnt.getText());

        while (matcher.find()) {
            String st = String.format("select * from topic where tname = '%s';", matcher.group().substring(1));
            PreparedStatement stmt = con.prepareStatement(st);
            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                st = String.format("INSERT INTO topic (tname, tdate) VALUES('%s', '%s');", matcher.group().substring(1), now);
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
            }
            st = String.format("INSERT INTO topiccomment VALUES('%s','%s','%s', '%s', '%s');", matcher.group().substring(1), UserProfile.CURRENT_USER.getUsername(), post.getAuthor().getUsername(), now, post.getDate());
            stmt = con.prepareStatement(st);
            stmt.executeUpdate();
            stmt.close();
        }
    }

    private void updateComments() {
        try {
            Connection con = DBConnection.getConnection();
            String st = String.format("SELECT * from commnt WHERE pauthor = '%s' and pdate = '%s' order by datestamp asc", post.getAuthor().getUsername(), post.getDate());
            PreparedStatement stmt = con.prepareStatement(st);
            ResultSet result = stmt.executeQuery();
            comments = new Container();
            comments.setLayout(new BoxLayout(comments, BoxLayout.Y_AXIS));
            while (result.next()) {
                st = String.format("select * from userrel where srcuser = '%s' and tgtuser = '%s' and status = 3 union select * from userrel where srcuser = '%s' and tgtuser = '%s' and status = 3;", result.getString("author"), UserProfile.CURRENT_USER.getUsername(), UserProfile.CURRENT_USER.getUsername(), result.getString("author"));
                stmt = con.prepareStatement(st);
                ResultSet result2 = stmt.executeQuery();
                boolean show = !result2.next();
                if (show) {
                    st = String.format("select * from userprofile where login = '%s';", result.getString("author"));
                    stmt = con.prepareStatement(st);
                    result2 = stmt.executeQuery();
                    result2.next();
                    UserProfile athr = new UserProfile(result2.getString("realname"), result2.getString("login"), result2.getString("bio"), result2.getBoolean("visibility"), result2.getInt("nfollowers"), result2.getInt("nfollowing"), result2.getString(("lasttime")));
                    Comment cmmt = new Comment(athr, result.getString("pauthor"), result.getString("pdate"), result.getString("datestamp"), result.getString("ctext"));
                    comments.add(new CommentPanel(cmmt, home, self));
                } else {
                    continue;
                }
            }
            stmt.close();
            result.close();
            
            comments.revalidate();
            scrollPane.getViewport().setView(comments);
        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel author;
    private javax.swing.JButton btImage;
    private javax.swing.JButton comment;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea txtCommnt;
    private javax.swing.JLabel txtPost;
    // End of variables declaration//GEN-END:variables
}
