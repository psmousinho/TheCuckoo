package view;

import entity.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import util.Constants;
import util.DBConnection;

public class CommentPanel extends JPanel {

    private Comment comment;
    private Home home;
    private boolean self;
    
    public CommentPanel(Comment comment, Home home, boolean self) {
        this.comment = comment;
        this.home = home;
        if (!self) {
            this.self = comment.getAuthor().getUsername().equals(UserProfile.CURRENT_USER.getUsername());
        } else {
            this.self = true;
        }
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commentTxt = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btDelete = new javax.swing.JButton();

        setBackground(Constants.PURPLE);
        setMaximumSize(new java.awt.Dimension(32767, 81));

        commentTxt.setBackground(Constants.PURPLE);
        commentTxt.setForeground(new java.awt.Color(255, 255, 255));
        commentTxt.setText("<html><div WIDTH=" + commentTxt.getWidth() + ">" + comment.getText() + "</html>");
        commentTxt.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                commentTxtComponentResized(evt);
            }
        });

        username.setBackground(Constants.PURPLE);
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("@" + comment.getAuthor().getUsername() + " at " + comment.getDate());
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameMouseClicked(evt);
            }
        });

        btDelete.setBackground(new java.awt.Color(255, 255, 255));
        btDelete.setForeground(Constants.PURPLE);
        btDelete.setText("X");
        btDelete.setVisible(self);
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
                .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btDelete))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(commentTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username)
                    .addComponent(btDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(commentTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseClicked
        home.changeScreenTemporary(new ProfilePanel(comment.getAuthor(), home, self));
    }//GEN-LAST:event_usernameMouseClicked

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        try {
            Connection con = DBConnection.getConnection();

            deleteTags(con);
            deleteTopics(con);

            String st = String.format("delete from commnt where author = '%s' and pauthor = '%s' and pdate = '%s' and datestamp = '%s'",
                    comment.getAuthor().getUsername(), comment.getPostAuthor(), comment.getPostDate(), comment.getDate());
            Statement stmt = con.createStatement();
            stmt.executeUpdate(st);

            stmt.close();
            setVisible(false);
            this.getParent().getParent().revalidate();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void commentTxtComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_commentTxtComponentResized
        commentTxt.setText("<html><div WIDTH=" + commentTxt.getWidth() + ">" + comment.getText() + "</html>");
    }//GEN-LAST:event_commentTxtComponentResized

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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDelete;
    private javax.swing.JLabel commentTxt;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
