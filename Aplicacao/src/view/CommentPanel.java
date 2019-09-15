package view;

import entity.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import util.DBConnection;

public class CommentPanel extends JPanel {

    private Comment comment;
    private Home home;

    public CommentPanel(Comment comment, Home home) {
        this.comment = comment;
        this.home = home;

        initComponents();

        if (UserProfile.CURRENT_USER.getUsername().equals(comment.getAuthor().getUsername())) {
            btDelete.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commentTxt = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btDelete = new javax.swing.JButton();

        commentTxt.setText(comment.getText());

        username.setText("@" + comment.getAuthor().getUsername());
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameMouseClicked(evt);
            }
        });

        btDelete.setText("X");
        btDelete.setVisible(false);
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(commentTxt)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addComponent(btDelete))))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username)
                    .addComponent(btDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(commentTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseClicked
        home.changeScreenTemporary(new ProfilePanel(comment.getAuthor(), home, false));
    }//GEN-LAST:event_usernameMouseClicked

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        try {
            Connection con = DBConnection.getConnection();

            deleteTags(con);
            deleteTopics(con);

            String st = String.format("delete from commnt where author = '%s' and pauthor = '%s' and pdate = '%s'",
                    comment.getAuthor().getUsername(), comment.getPostAuthor(), comment.getPostDate());
            Statement stmt = con.createStatement();
            stmt.executeUpdate(st);

            stmt.close();
            this.getParent().getParent().revalidate();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void deleteTags(Connection con) throws SQLException {
        String st = String.format("delete from tagcommntuser where cauthor = '%s' and cdate = '%s' and cpauthor = '%s' and cpdate = '%s'",
                comment.getAuthor().getUsername(), comment.getDate(), comment.getPostAuthor(), comment.getPostDate());
        Statement stmt = con.createStatement();
        stmt.executeUpdate(st);
        st = String.format("delete from notifications where cauthor = '%s' and cdate = '%s' and cpauthor = '%s' and cpdate = '%s'",
                comment.getAuthor().getUsername(), comment.getDate(), comment.getPostAuthor(), comment.getPostDate());
        stmt.executeUpdate(st);
        stmt.close();
    }

    private void deleteTopics(Connection con) throws SQLException {
        String st = String.format("delete from topiccomment where cauthor = '%s' and cdate = '%s'", comment.getAuthor().getUsername(), comment.getDate());
        Statement stmt = con.createStatement();
        stmt.executeUpdate(st);
        stmt.close();

        stmt.close();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDelete;
    private javax.swing.JLabel commentTxt;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
