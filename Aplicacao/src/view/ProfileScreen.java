package view;

import entity.*;
import java.awt.Container;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import util.Constants;
import util.DBConnection;

public class ProfileScreen extends javax.swing.JPanel {

    private UserProfile user;
    private boolean belong;
    private int status;

    public ProfileScreen(UserProfile user, boolean belong) {
        this.user = user;
        this.belong = belong;
        initComponents();

        checkRelation();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TopPanel = new javax.swing.JPanel();
        realName = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bio = new javax.swing.JTextArea();
        btAction = new javax.swing.JButton();
        nFollowers = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        myCuckoos = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(100, 210, 255));

        TopPanel.setBackground(new java.awt.Color(100, 210, 255));

        realName.setText(this.user.getName());

        userName.setText("@" + this.user.getUsername());

        bio.setBackground(new java.awt.Color(100, 210, 255));
        bio.setColumns(20);
        bio.setRows(3);
        bio.setText(this.user.getBio());

        bio.setEditable(false);
        jScrollPane1.setViewportView(bio);

        if(belong){
            btAction.setText("Edit");
        }else if(!this.user.isPrivate()) {
            btAction.setText("Fallow");
        } else {
            btAction.setText("Private");
            btAction.setEnabled(false);
        }
        btAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActionActionPerformed(evt);
            }
        });

        nFollowers.setText("Followers: " + this.user.getNumberFollowers());

        jLabel2.setText("Following: " + this.user.getNumberFollowing());

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TopPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(TopPanelLayout.createSequentialGroup()
                        .addGroup(TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(realName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btAction)
                        .addGap(26, 26, 26))
                    .addGroup(TopPanelLayout.createSequentialGroup()
                        .addComponent(nFollowers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 158, Short.MAX_VALUE))))
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TopPanelLayout.createSequentialGroup()
                        .addComponent(realName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btAction, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nFollowers)
                    .addComponent(jLabel2)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(myCuckoos)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myCuckoos, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActionActionPerformed
        if (!belong && !user.isPrivate()) {
            Connection con = DBConnection.getConnection();
            Statement stmt;
            String st;
            switch (status) {
                case 0: //na
                default:
                    st = String.format("insert into userrel values ('%s', '%s', '%s', '%d');", UserProfile.CURRENT_USER.getUsername(), this.user.getUsername(), new Timestamp(new Date().getTime()), 2);
                    status = 2;
                    break;
                case 1: //requested
                    st = String.format("update userrel set status = 0 where srcuser = '%s' and tgtuser = '%s';", UserProfile.CURRENT_USER.getUsername(), this.user.getUsername());
                    status = 0;
                    break;
                case 2: //following
                    st = String.format("update userrel set status = 0 where srcuser = '%s' and tgtuser = '%s';", UserProfile.CURRENT_USER.getUsername(), this.user.getUsername());
                    status = 0;
                    break;
                case 3: //blocked
                    //TODO: Bloqueio
                    break;
            }
        } else {
            if (!bio.isEditable()) {
                this.bio.setEditable(true);
                btAction.setText("Save");
            } else {
                btAction.setText("Edit");
                this.user.setBio(bio.getText());
                this.bio.setEditable(false);

                try {
                    Connection con = DBConnection.getConnection();
                    PreparedStatement stmt;
                    stmt = con.prepareStatement("UPDATE userprofile SET bio = ? where login = ?;");
                    stmt.setString(1, bio.getText());
                    stmt.setString(2, user.getUsername());
                    stmt.executeUpdate();
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProfileScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }//GEN-LAST:event_btActionActionPerformed

    public void updateCuckoos() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * from post WHERE author = '" + user.getUsername() + "'order by datestamp desc;");
            ResultSet result = stmt.executeQuery();
            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            while (result.next()) {
                Post post = new Post(this.user, result.getString("datestamp"), result.getString("ptext"), result.getString("foto"));
                cont.add(new Cuckoo(post));
            }
            cont.revalidate();
            myCuckoos.getViewport().setView(cont);

        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkRelation() {
        if (!belong) {
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement("select * from userrel where srcuser = '" + this.user.getUsername() + "' and tgtuser = '" + UserProfile.CURRENT_USER.getUsername() + "' ");
                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    boolean block = (result.getInt("status") == Constants.Relation.BLOCKED.getCode());
                    if (block) {
                        //TODO: mostra mensagem de bloqueio
                    } else if (user.isPrivate()) {
                        //TODO: mostrar mensagem de privado
                    }
                }

                result.close();
                stmt.close();

                stmt = con.prepareStatement("select * from userrel where srcuser = '" + UserProfile.CURRENT_USER.getUsername() + "' and tgtuser = '" + this.user.getUsername() + "' ");
                result = stmt.executeQuery();
                if (result.next()) {
                    switch (status = result.getInt("status")) {
                        case 0: //na
                        default:
                            btAction.setText("Follow");
                            break;
                        case 1: //requested
                            btAction.setText("Pending");
                            break;
                        case 2: //following
                            btAction.setText("Unfollow");
                            break;
                        case 3: //blocked
                            btAction.setText("Unblock");
                            break;
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(ProfileScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TopPanel;
    private javax.swing.JTextArea bio;
    private javax.swing.JButton btAction;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane myCuckoos;
    private javax.swing.JLabel nFollowers;
    private javax.swing.JLabel realName;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables
}
