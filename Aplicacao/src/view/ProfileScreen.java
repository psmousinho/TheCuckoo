package view;

import entity.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JButton;
import javax.swing.JPanel;
import util.Constants;
import util.DBConnection;

public class ProfileScreen extends JPanel {

    private enum ProfileState {
        LIST_POSTS, NEW_POST, FOLLOWERS, FOLLOWING, NOTHING;
    }
    
    private Home home;
    private UserProfile user;
    private NewPost newPost;
    private boolean belong;
    private boolean follows;
    
    private int status;
    private ProfileState state;
    
    public ProfileScreen(UserProfile user, Home home, boolean belong) {
        this.user = user;
        this.home = home;
        this.belong = belong;
        this.follows = false;
        
        initComponents();
        checkRelation();
        
        bottomPanel.add(myCuckoos, "cuckoos");
        if(belong) {
            newPost = new NewPost();
            bottomPanel.add(newPost,"newpost");
        }
        CardLayout cl = (CardLayout) bottomPanel.getLayout();
        cl.show(bottomPanel, "cuckoos");
        state = ProfileState.LIST_POSTS;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myCuckoos = new javax.swing.JScrollPane();
        bioScrollPane = new javax.swing.JScrollPane();
        bio = new javax.swing.JTextArea();
        topPanel = new javax.swing.JPanel();
        realName = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        btAction = new javax.swing.JButton();
        bioLabel = new javax.swing.JLabel();
        middlePanel = new javax.swing.JPanel();
        btPost = new javax.swing.JButton();
        btFollowers = new javax.swing.JButton();
        btFollowing = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();

        bio.setBackground(new java.awt.Color(100, 210, 255));
        bio.setColumns(20);
        bio.setLineWrap(true);
        bio.setRows(3);
        bio.setText(this.user.getBio());

        bio.setEditable(false);
        bioScrollPane.setViewportView(bio);

        setBackground(new java.awt.Color(100, 210, 255));
        setPreferredSize(new java.awt.Dimension(338, 396));

        topPanel.setBackground(new java.awt.Color(100, 210, 255));

        realName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        realName.setForeground(new java.awt.Color(255, 255, 255));
        realName.setText(this.user.getName());

        userName.setForeground(new java.awt.Color(255, 255, 255));
        userName.setText("@" + this.user.getUsername());

        if(belong){
            btAction.setBackground(new java.awt.Color(255, 255, 255));
            btAction.setForeground(Constants.TEAL);
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

        bioLabel.setForeground(Constants.WHITE);
        bioLabel.setText(getBioText());
        bioLabel.setToolTipText("");
        bioLabel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                bioLabelComponentResized(evt);
            }
        });

        middlePanel.setBackground(Constants.ORANGE);
        middlePanel.setLayout(new java.awt.GridLayout(1, 0));

        btPost.setBackground(Constants.WHITE);
        btPost.setForeground(Constants.ORANGE);
        btPost.setText("New Post");
        btPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPostActionPerformed(evt);
            }
        });
        middlePanel.add(btPost);

        btFollowers.setBackground(Constants.WHITE);
        btFollowers.setForeground(Constants.ORANGE);
        btFollowers.setText("" + user.getNumberFollowers() + " Followers");
        btFollowers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFollowersActionPerformed(evt);
            }
        });
        middlePanel.add(btFollowers);

        btFollowing.setBackground(Constants.WHITE);
        btFollowing.setForeground(Constants.ORANGE);
        btFollowing.setText("" + user.getNumberFollowing() + " Following");
        btFollowing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFollowingActionPerformed(evt);
            }
        });
        middlePanel.add(btFollowing);

        bottomPanel.setBackground(new java.awt.Color(255, 255, 255));
        bottomPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(realName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAction, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(middlePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bottomPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(realName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(middlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActionActionPerformed
        if (!belong && !user.isPrivate()) {
            Connection con = DBConnection.getConnection();
            Statement stmt;
            String st = null, st2 = null, st3 = null;
            switch (status) {
                case 0: // nothing
                    st = String.format("update userrel set status = 2, datestamp = now() where srcuser = '%s' and tgtuser = '%s';", UserProfile.CURRENT_USER.getUsername(), user.getUsername());
                    st2 = String.format("update userprofile set nfollowers = nfollowers + 1 where login = '%s';", user.getUsername());
                    user.setNumberFollowers(user.getNumberFollowers() + 1);
                    st3 = String.format("update userprofile set nfollowing = nfollowing + 1 where login = '%s';", UserProfile.CURRENT_USER.getUsername());
                    UserProfile.CURRENT_USER.setNumberFollowing(UserProfile.CURRENT_USER.getNumberFollowing() + 1);
                    status = 2;
                    btAction.setText("Unfollow");
                    break;
                case -1:
                    st = String.format("insert into userrel values ('%s', '%s', '%s', '%d')", UserProfile.CURRENT_USER.getUsername(), user.getUsername(), new Timestamp(new Date().getTime()), 2);
                    st2 = String.format("update userprofile set nfollowers = nfollowers + 1 where login = '%s';", user.getUsername());
                    user.setNumberFollowers(user.getNumberFollowers() + 1);
                    st3 = String.format("update userprofile set nfollowing = nfollowing + 1 where login = '%s';", UserProfile.CURRENT_USER.getUsername());
                    UserProfile.CURRENT_USER.setNumberFollowing(UserProfile.CURRENT_USER.getNumberFollowing() + 1);
                    status = 2;
                    btAction.setText("Unfollow");
                    break;
                case 1: //requested
                    st = String.format("update userrel set status = 0, datestamp = now() where srcuser = '%s' and tgtuser = '%s';", UserProfile.CURRENT_USER.getUsername(), user.getUsername());
                    status = 0;
                    btAction.setText("Pending");
                    break;
                case 2: //following
                    st = String.format("update userrel set status = 0, datestamp = now() where srcuser = '%s' and tgtuser = '%s';", UserProfile.CURRENT_USER.getUsername(), user.getUsername());
                    st2 = String.format("update userprofile set nfollowers = nfollowers - 1 where login = '%s';", user.getUsername());
                    user.setNumberFollowers(user.getNumberFollowers() - 1);
                    st3 = String.format("update userprofile set nfollowing = nfollowing - 1 where login = '%s';", UserProfile.CURRENT_USER.getUsername());
                    UserProfile.CURRENT_USER.setNumberFollowing(UserProfile.CURRENT_USER.getNumberFollowing() - 1);
                    status = 0;
                    btAction.setText("Follow");
                    break;
                case 3: //blocked
                    //TODO: Bloqueio
                    break;
            }
            try {
                stmt = con.createStatement();
                stmt.addBatch(st);
                stmt.addBatch(st2);
                stmt.addBatch(st3);
                stmt.executeBatch();
                stmt.close();
                updateFollowers();
            } catch (SQLException ex) {
                Logger.getLogger(ProfileScreen.class.getName()).log(Level.SEVERE, null, ex);
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

    private void btPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPostActionPerformed
        CardLayout cl = (CardLayout) bottomPanel.getLayout();    
        if(state == ProfileState.LIST_POSTS) {
            cl.show(bottomPanel, "newpost");
            btPost.setText("Posts");
            state = ProfileState.NEW_POST;
            bottomPanel.revalidate();
        } else {
            cl.show(bottomPanel, "cuckoos");
            updateCuckoos();
            btPost.setText("New Post");
            state = ProfileState.LIST_POSTS;
        }
        updateButtons();
    }//GEN-LAST:event_btPostActionPerformed

    private void btFollowersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFollowersActionPerformed
        if(state != ProfileState.FOLLOWERS) {
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement stmt = null;
                ResultSet result = null;
                Container cont = new Container();
                cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
                stmt = con.prepareStatement("SELECT * FROM userprofile INNER JOIN userrel ON userprofile.login = userrel.srcuser WHERE tgtuser = '" + user.getUsername() + "' AND status = 2;");
                result = stmt.executeQuery();
                int i = 0;
                while(result.next()) {
                        cont.add(new UserResult(new UserProfile(result.getString("realname"), result.getString("login"), result.getString("bio"), 
                                                    result.getBoolean("visibility"), result.getInt("nfollowers"), result.getInt("nfollowing"), result.getString("lasttime")), i % 2 == 0 ? Constants.WHITE : Constants.GRAY, home));
                        i++;
                }
                CardLayout cl = (CardLayout) bottomPanel.getLayout();
                cl.show(bottomPanel, "cuckoos");
                //if(stmt != null && result != null) {
                    cont.revalidate();
                    myCuckoos.getViewport().setView(cont);
                    myCuckoos.getViewport().setBackground(Constants.ORANGE);
                    result.close();
                    stmt.close();
                //}
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            state = ProfileState.FOLLOWERS;
            updateButtons();
        }
    }//GEN-LAST:event_btFollowersActionPerformed

    private void btFollowingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFollowingActionPerformed
        if(state != ProfileState.FOLLOWING) {
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement stmt = null;
                ResultSet result = null;
                Container cont = new Container();
                cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
                stmt = con.prepareStatement("SELECT * FROM userprofile INNER JOIN userrel ON userprofile.login = userrel.tgtuser WHERE srcuser = '" + user.getUsername() + "' AND status = 2;");
                result = stmt.executeQuery();
                int i = 0;
                while(result.next()) {
                        cont.add(new UserResult(new UserProfile(result.getString("realname"), result.getString("login"), result.getString("bio"), 
                                                    result.getBoolean("visibility"), result.getInt("nfollowers"), result.getInt("nfollowing"), result.getString("lasttime")), i % 2 == 0 ? Constants.WHITE : Constants.GRAY, home));
                        i++;
                }
                CardLayout cl = (CardLayout) bottomPanel.getLayout();
                cl.show(bottomPanel, "cuckoos");
                //if(stmt != null && result != null) {
                    cont.revalidate();
                    myCuckoos.getViewport().setView(cont);
                    myCuckoos.getViewport().setBackground(Constants.ORANGE);
                    result.close();
                    stmt.close();
                //}
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            state = ProfileState.FOLLOWING;
            updateButtons();
        }
    }//GEN-LAST:event_btFollowingActionPerformed

    private void bioLabelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_bioLabelComponentResized
        bioLabel.setText(getBioText());
    }//GEN-LAST:event_bioLabelComponentResized

    public void updateCuckoos() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * from post WHERE author = '" + user.getUsername() + "'order by datestamp desc;");
            ResultSet result = stmt.executeQuery();
            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            while (result.next()) {
                Post post = new Post(this.user, result.getString("datestamp"), result.getString("ptext"), result.getString("foto"));
                cont.add(new Cuckoo(post, home));
            }
            cont.revalidate();
            myCuckoos.setPreferredSize(getParent().getSize());
            myCuckoos.getViewport().setView(cont);

        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFollowers() {
        btFollowers.setText("" + user.getNumberFollowers() + " Followers");
        btFollowing.setText("" + user.getNumberFollowing() + " Following");
    }
    
    private String getBioText() {
        return "<html><div WIDTH="+ bioLabel.getWidth() +">" + user.getBio() + "<br>" + (follows? "(Follows you)": "") + "</html>";
    }
        
    private void checkRelation() {
        if (!belong) {
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement("select * from userrel where srcuser = '" + user.getUsername() + "' and tgtuser = '" + UserProfile.CURRENT_USER.getUsername() + "' ");
                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    boolean block = (result.getInt("status") == Relation.BLOCKED.getCode());
                    if (block) {
                        //TODO: mostra mensagem de bloqueio
                    } else if (user.isPrivate()) {
                        //TODO: mostrar mensagem de privado
                    }
                }

                result.close();
                stmt.close();

                stmt = con.prepareStatement("select * from userrel where srcuser = '" + UserProfile.CURRENT_USER.getUsername() + "' and tgtuser = '" + user.getUsername() + "' ");
                result = stmt.executeQuery();
                if (result.next()) {
                    switch (status = result.getInt("status")) {
                        case 0: //nothing
                        case -1: //no relation
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
                }else {
                    status = -1;
                }
                
                result.close();
                stmt.close();
                
                stmt = con.prepareStatement("select * from userrel where srcuser = '" + user.getUsername() + "' and tgtuser = '" + UserProfile.CURRENT_USER.getUsername() + "' and status = 2 ");
                result = stmt.executeQuery();
                follows = result.next();
                
                result.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfileScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void updateButtons() {
        for (Component c : middlePanel.getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(Color.WHITE);
                c.setForeground(Constants.ORANGE);
            }
        }
        switch (state) {
            /*case NEW_POST:
            case LIST_POSTS:
                btPost.setForeground(Constants.WHITE);
                btPost.setBackground(Constants.ORANGE);
                break;*/
            case FOLLOWERS:
                btPost.setText("Posts");
                btFollowers.setForeground(Constants.WHITE);
                btFollowers.setBackground(Constants.ORANGE);
                break;
            case FOLLOWING:
                btPost.setText("Posts");
                btFollowing.setForeground(Constants.WHITE);
                btFollowing.setBackground(Constants.ORANGE);
                break;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea bio;
    private javax.swing.JLabel bioLabel;
    private javax.swing.JScrollPane bioScrollPane;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton btAction;
    private javax.swing.JButton btFollowers;
    private javax.swing.JButton btFollowing;
    private javax.swing.JButton btPost;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JScrollPane myCuckoos;
    private javax.swing.JLabel realName;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables
}
