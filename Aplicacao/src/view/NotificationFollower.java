package view;

import entity.UserProfile;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;

public class NotificationFollower extends javax.swing.JPanel {

    private UserProfile user;
    private Home home;
    private String date;
    private int type;

    public NotificationFollower(UserProfile user, String date, int type, Home home) {
        this.home = home;
        this.user = user;
        this.date = date;
        this.type = type;
        
        initComponents();
        
        boolean visible = (type == 2);
        btAccept.setVisible(visible);
        btReject.setVisible(visible);       
        
        if (type == 2) {
            message.setText("Asked to follow you");
        } else if(type == 3) {
            message.setText("Accepted your request");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        username = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblDate = new javax.swing.JLabel();
        btAccept = new javax.swing.JButton();
        btReject = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        username.setText("@" +this.user.getUsername());

        message.setText("Followed you!! Follow they back");

        lblDate.setText(this.date);

        btAccept.setText("Accept");
        btAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAcceptActionPerformed(evt);
            }
        });

        btReject.setText("Reject");
        btReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRejectActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(username)
                            .addComponent(message))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btReject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(lblDate)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(btAccept)
                    .addComponent(btReject))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        home.changeScreenTemporary(new ProfilePanel(user, home, user.getUsername().equals(UserProfile.CURRENT_USER.getUsername())));
    }//GEN-LAST:event_formMouseClicked

    private void btAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAcceptActionPerformed
        try {
            List<String> st = new ArrayList<>();
            Timestamp now = new Timestamp(new Date().getTime());
            Connection con = DBConnection.getConnection();
            st.add(String.format("update userrel set status = 2 WHERE tgtuser = '%s'and  srcuser = '%s';", UserProfile.CURRENT_USER.getUsername(), user.getUsername()));
            st.add(String.format("update userprofile set nfollowers = nfollowers + 1 where login = '%s';", UserProfile.CURRENT_USER.getUsername()));
            st.add(String.format("update userprofile set nfollowing = nfollowing + 1 where login = '%s';", user.getUsername()));
            st.add(String.format("insert into notifications(target, src, ndate, code) values('%s', '%s', '%s', 3);", user.getUsername(), UserProfile.CURRENT_USER.getUsername(), now));
            st.add(String.format("delete from notifications where target = '%s' and src = '%s' and code = 2;", UserProfile.CURRENT_USER.getUsername(), user.getUsername()));
            
            Statement stmt = con.createStatement();
            for(String s : st) {
                stmt.addBatch(s);
            }
            stmt.executeBatch();
            stmt.close();
            
            btAccept.setVisible(false);
            btReject.setVisible(false);
            home.updateNotifications();
        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAcceptActionPerformed

    private void btRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRejectActionPerformed
        try {
            List<String> st = new ArrayList<>();
            Connection con = DBConnection.getConnection();
            st.add(String.format("update userrel set status = 0 WHERE tgtuser = '%s'and  srcuser = '%s';", UserProfile.CURRENT_USER.getUsername(), user.getUsername()));
            st.add(String.format("delete from notifications where target = '%s' and src = '%s' and code = 2;", UserProfile.CURRENT_USER.getUsername(), user.getUsername()));
            
            Statement stmt = con.createStatement();
            for(String s : st) {
                stmt.addBatch(s);
            }
            stmt.executeBatch();
            stmt.close();
            
            btAccept.setVisible(false);
            btReject.setVisible(false);
            home.updateNotifications();
        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btRejectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAccept;
    private javax.swing.JButton btReject;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel message;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
