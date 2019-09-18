package view;

import entity.Post;
import entity.UserProfile;
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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import util.Constants;
import util.DBConnection;

public class Cuckoo extends JPanel {

    private Post post;
    private Home home;
    private boolean self;
    
    public Cuckoo(Post post, Home home) {
        this.post = post;
        this.home = home;
        this.self = post.getAuthor().getUsername().equals(UserProfile.CURRENT_USER.getUsername());
        initComponents();

        if (post.getPhoto() == null) {
            btImage.setEnabled(false);
        }

        btDelete.setVisible(self);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        author = new javax.swing.JLabel();
        btComment = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCommnt = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        btDelete = new javax.swing.JButton();
        btImage = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        author.setForeground(Constants.ORANGE);
        author.setText("@" + this.post.getAuthor().getUsername() + " at " + post.getDate());
        author.setToolTipText("\"Go to Profile\"");
        author.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorMouseClicked(evt);
            }
        });

        btComment.setBackground(new java.awt.Color(255, 255, 255));
        btComment.setForeground(Constants.ORANGE);
        btComment.setText("comment");
        btComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCommentActionPerformed(evt);
            }
        });

        jLabel1.setForeground(Constants.ORANGE);
        jLabel1.setText(this.post.getText());

        jScrollPane1.setVisible(false);

        txtCommnt.setColumns(20);
        txtCommnt.setRows(5);
        jScrollPane1.setViewportView(txtCommnt);

        btDelete.setBackground(new java.awt.Color(255, 255, 255));
        btDelete.setForeground(Constants.ORANGE);
        btDelete.setText("X");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        btImage.setBackground(new java.awt.Color(255, 255, 255));
        btImage.setForeground(Constants.ORANGE);
        btImage.setText("View Image");
        btImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btComment))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(author)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(btImage))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(author)
                    .addComponent(btDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btImage)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btComment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCommentActionPerformed
        if (jScrollPane1.isVisible()) {
            String comment = txtCommnt.getText();
            if (!comment.equals("")) {
                comment = comment.replaceAll("'", "''");
                
                Connection con = DBConnection.getConnection();
                try {
                    Timestamp now = new Timestamp(new Date().getTime());
                    String st = String.format("INSERT INTO commnt values('%s','%s','%s','%s','%s');",
                            UserProfile.CURRENT_USER.getUsername(), post.getAuthor().getUsername(), post.getDate(), now, comment);
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(st);
                    addTags(con, now);
                    addTopics(con, now);
                } catch (SQLException ex) {
                    Logger.getLogger(Cuckoo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            txtCommnt.setText("");
            jScrollPane1.setVisible(false);
        } else {
            jScrollPane1.setVisible(true);
        }
        this.revalidate();
    }//GEN-LAST:event_btCommentActionPerformed

    private void authorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authorMouseClicked
        home.changeScreenTemporary(new ProfilePanel(post.getAuthor(), home, self));
    }//GEN-LAST:event_authorMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        home.changeScreenTemporary(new PostScreen(post, home, self));
    }//GEN-LAST:event_formMouseClicked

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        try {
            Connection con = DBConnection.getConnection();

            deleteTags(con);
            deleteTopics(con);
            deleteComments(con);

            String st = String.format("delete from post where author = '%s' and datestamp = '%s'",
                    post.getAuthor().getUsername(), post.getDate());

            Statement stmt = con.createStatement();
            stmt.executeUpdate(st);

            stmt.close();
            home.updateCuckoos();
            home.updateNotifications();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImageActionPerformed
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(post.getPhoto()));
        JOptionPane.showMessageDialog(this, image, "Image", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_btImageActionPerformed

    private void addTags(Connection con, Timestamp now) throws SQLException {
        Pattern regex = Pattern.compile("@\\w{4,32}");
        Matcher matcher = regex.matcher(txtCommnt.getText());

        while (matcher.find()) {
            String tagUser = matcher.group().substring(1);
            String st = String.format("SELECT * FROM userprofile where login = '%s'", tagUser);
            PreparedStatement stmt = con.prepareStatement(st);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                st = String.format("INSERT INTO tagcommntuser values( '%s', '%s', '%s', '%s', '%s');",
                        UserProfile.CURRENT_USER.getUsername(), now, post.getAuthor().getUsername(), post.getDate(), tagUser);
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
                boolean notify = false;
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
            boolean topicExists = result.next();
            if (!topicExists) {
                st = String.format("INSERT INTO topic (tname, tdate) VALUES('%s', '%s');", matcher.group().substring(1), now);
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
            }
            st = String.format("INSERT INTO topiccomment VALUES('%s','%s','%s', '%s', '%s');", matcher.group().substring(1), UserProfile.CURRENT_USER.getUsername(), post.getAuthor().getUsername(), now, post.getDate());
            stmt = con.prepareStatement(st);
            stmt.executeUpdate();
            System.out.println("" + topicExists);
            if (topicExists) {
                st = String.format("update topic set tdate = '%s' where tname = '%s';", now, matcher.group().substring(1)); // Assumindo tempo crescente
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
                System.out.println("updated topic!");
            }

            stmt.close();
        }
    }

    private void deleteTags(Connection con) throws SQLException {
        String st = String.format("delete from tagpostuser where pauthor = '%s' and pdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        Statement stmt = con.createStatement();
        stmt.executeUpdate(st);
        st = String.format("delete from tagcommntuser where cpauthor = '%s' and cpdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        stmt = con.createStatement();
        stmt.executeUpdate(st);
        st = String.format("delete from notifications where pauthor = '%s' and pdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        stmt.executeUpdate(st);
        stmt.close();

        stmt.close();
    }

    private void deleteTopics(Connection con) throws SQLException {
        String st = String.format("delete from topicpost where pauthor = '%s' and pdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        Statement stmt = con.createStatement();
        stmt.executeUpdate(st);
        st = String.format("delete from topiccomment where cpauthor = '%s' and cpdate = '%s'", post.getAuthor().getUsername(), post.getDate());
        stmt = con.createStatement();
        stmt.executeUpdate(st);
        stmt.close();
    }

    private void deleteComments(Connection con) throws SQLException {
        String st = String.format("delete from notifications where cpauthor = '%s' and cpdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        Statement stmt = con.createStatement();
        stmt.executeUpdate(st);
        st = String.format("delete from commnt where pauthor = '%s' and pdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        stmt.executeUpdate(st);
        stmt.close();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel author;
    private javax.swing.JButton btComment;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea txtCommnt;
    // End of variables declaration//GEN-END:variables
}
