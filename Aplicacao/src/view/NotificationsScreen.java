/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.*;
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import util.DBConnection;

public class NotificationsScreen extends javax.swing.JPanel {

    private UserProfile user;
    private Home home;

    public NotificationsScreen(UserProfile user, Home home) {
        this.user = user;
        this.home = home;
        initComponents();

        updatePostTab();

        if (UserProfile.CURRENT_USER.isPrivate()) {
            followers.setText("Follow Requets");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        tagPost = new javax.swing.JButton();
        tagCommnt = new javax.swing.JButton();
        followers = new javax.swing.JButton();

        tagPost.setText("Tag Post");
        tagPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tagPostActionPerformed(evt);
            }
        });

        tagCommnt.setText("Tag Comment");
        tagCommnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tagCommntActionPerformed(evt);
            }
        });

        followers.setText("New Followers");
        followers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                followersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tagPost)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tagCommnt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(followers)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagPost)
                    .addComponent(tagCommnt)
                    .addComponent(followers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tagPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tagPostActionPerformed
        updatePostTab();
    }//GEN-LAST:event_tagPostActionPerformed

    private void tagCommntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tagCommntActionPerformed
        updateCommentTab();
    }//GEN-LAST:event_tagCommntActionPerformed

    private void followersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_followersActionPerformed
        updateFollowersTab();
    }//GEN-LAST:event_followersActionPerformed

    public void updatePostTab() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * from tagpostuser WHERE tagpostuser.taguser = '" + this.user.getUsername() + "'order by pdate desc;");
            ResultSet result = stmt.executeQuery();

            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            while (result.next()) {
                PreparedStatement stmt2 = con.prepareStatement("Select * from userprofile where login = '" + result.getString("pauthor") + "';");
                ResultSet resultAuthor = stmt2.executeQuery();
                resultAuthor.next();
                UserProfile author = new UserProfile(resultAuthor.getString("realname"), resultAuthor.getString("login"), resultAuthor.getString("bio"),
                        resultAuthor.getBoolean("visibility"), resultAuthor.getInt("nfollowers"), resultAuthor.getInt("nfollowing"), resultAuthor.getString("lasttime"));

                if (!resultAuthor.getBoolean("visibility") || checkRelation(author.getUsername()) == 2) {
                    PreparedStatement stmt3 = con.prepareStatement("Select * from post where author = '" + result.getString("pauthor") + "' and datestamp = '" + result.getString("pdate") + "';");
                    ResultSet resultPost = stmt3.executeQuery();
                    resultPost.next();
                    Post post = new Post(author, resultPost.getString("datestamp"), resultPost.getString("ptext"));

                    cont.add(new NotificationPost(post, home));
                }

            }
            cont.revalidate();
            scrollPane.getViewport().setView(cont);

        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCommentTab() {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement stmt = con.prepareStatement("SELECT * from tagcommntuser WHERE taguser = '" + this.user.getUsername() + "'order by cdate desc;");
            ResultSet result = stmt.executeQuery();

            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            while (result.next()) {

                PreparedStatement stmt2 = con.prepareStatement("Select * from userprofile where login = '" + result.getString("cpauthor") + "';");
                ResultSet resultAuthor = stmt2.executeQuery();
                resultAuthor = stmt2.executeQuery();
                resultAuthor.next();
                UserProfile postAuthor = new UserProfile(resultAuthor.getString("realname"), resultAuthor.getString("login"), resultAuthor.getString("bio"),
                        resultAuthor.getBoolean("visibility"), resultAuthor.getInt("nfollowers"), resultAuthor.getInt("nfollowing"), resultAuthor.getString("lasttime"));

                if (!postAuthor.isPrivate() || checkRelation(postAuthor.getUsername()) == 2) {
                    PreparedStatement stmt3 = con.prepareStatement("Select * from post where author = '" + result.getString("cpauthor") + "' and datestamp = '" + result.getString("cpdate") + "';");
                    ResultSet resultPost = stmt3.executeQuery();
                    resultPost.next();

                    Post post = new Post(postAuthor, result.getString("cpdate"), resultPost.getString("ptext"));

                    cont.add(new NotificationComment(post, result.getString("cauthor"), result.getString("cdate"), home));
                }

            }
            cont.revalidate();
            scrollPane.getViewport().setView(cont);

        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFollowersTab() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * from userrel WHERE tgtuser = '" + UserProfile.CURRENT_USER.getUsername() + "'and (status = 2 or status = 1) order by datestamp desc;");
            ResultSet result = stmt.executeQuery();

            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            while (result.next()) {
                PreparedStatement stmt2 = con.prepareStatement("Select * from userprofile where login = '" + result.getString("srcuser") + "';");
                ResultSet resultSrc = stmt2.executeQuery();
                resultSrc.next();
                UserProfile src = new UserProfile(resultSrc.getString("realname"), resultSrc.getString("login"), resultSrc.getString("bio"),
                        resultSrc.getBoolean("visibility"), resultSrc.getInt("nfollowers"), resultSrc.getInt("nfollowing"), resultSrc.getString("lasttime"));

                cont.add(new NotificationFollower(src, result.getString("datestamp"), result.getInt("status"), home));
            }
            cont.revalidate();
            scrollPane.getViewport().setView(cont);

        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int checkRelation(String otherUser) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("select * from userrel where srcuser = '" + otherUser + "' and tgtuser = '" + user.getUsername() + "' ");
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            return result.getInt("status");
        } else {
            return 0;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton followers;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton tagCommnt;
    private javax.swing.JButton tagPost;
    // End of variables declaration//GEN-END:variables
}
