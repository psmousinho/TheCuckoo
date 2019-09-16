package view;

import entity.Post;
import entity.Relation;
import entity.UserProfile;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import util.Constants;
import util.DBConnection;

public class ProfilePanel extends JPanel {

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

    public ProfilePanel(UserProfile user, Home home, boolean belong) {
        this.user = user;
        this.home = home;
        this.belong = belong;
        this.follows = false;

        initComponents();

        bottomPanel.add(myCuckoos, "cuckoos");
        if (belong) {
            newPost = new NewPost();
            bottomPanel.add(newPost, "newpost");
        }

        CardLayout cl = (CardLayout) bottomPanel.getLayout();
        cl.show(bottomPanel, "cuckoos");
        state = ProfileState.LIST_POSTS;
        if (!belong) {
            checkRelation();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myCuckoos = new javax.swing.JScrollPane();
        scrollPane = new javax.swing.JScrollPane();
        bio = new javax.swing.JTextArea();
        actionsPanel = new javax.swing.JPanel();
        btAction1 = new javax.swing.JButton();
        btAction2 = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        optionsPanel = new javax.swing.JPanel();
        btCuckoos = new javax.swing.JButton();
        btFollowers = new javax.swing.JButton();
        btFollowing = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();

        setBackground(Constants.TEAL);

        bio.setBackground(Constants.TEAL);
        bio.setColumns(20);
        bio.setForeground(new java.awt.Color(255, 255, 255));
        bio.setLineWrap(true);
        bio.setRows(3);
        scrollPane.setViewportView(bio);
        bio.setText(user.getBio());

        actionsPanel.setBackground(Constants.TEAL);
        actionsPanel.setLayout(new java.awt.GridLayout(0, 1));

        btAction1.setBackground(new java.awt.Color(255, 255, 255));
        btAction1.setForeground(Constants.TEAL);
        if(belong) {
            btAction1.setText("Edit");
        } else {
            btAction1.setText("Follow");
        }
        btAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAction1ActionPerformed(evt);
            }
        });
        actionsPanel.add(btAction1);

        btAction2.setBackground(new java.awt.Color(255, 255, 255));
        btAction2.setForeground(Constants.TEAL);
        if(belong){
            btAction2.setText("Edit Visibility");
        } else {
            btAction2.setText("Block");
        }
        btAction2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAction2ActionPerformed(evt);
            }
        });
        actionsPanel.add(btAction2);

        nameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText(user.getName());

        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("@" + user.getUsername());

        optionsPanel.setBackground(Constants.TEAL);
        optionsPanel.setLayout(new java.awt.GridLayout(1, 0));

        btCuckoos.setBackground(new java.awt.Color(255, 255, 255));
        btCuckoos.setForeground(Constants.PURPLE);
        btCuckoos.setText("Cuckoos");
        btCuckoos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCuckoosActionPerformed(evt);
            }
        });
        optionsPanel.add(btCuckoos);

        btFollowers.setBackground(new java.awt.Color(255, 255, 255));
        btFollowers.setForeground(Constants.PURPLE);
        btFollowers.setText("" + user.getNumberFollowers() + " Followers");
        btFollowers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFollowersActionPerformed(evt);
            }
        });
        optionsPanel.add(btFollowers);

        btFollowing.setBackground(new java.awt.Color(255, 255, 255));
        btFollowing.setForeground(Constants.PURPLE);
        btFollowing.setText("" + user.getNumberFollowing() + " Following");
        btFollowing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFollowingActionPerformed(evt);
            }
        });
        optionsPanel.add(btFollowing);

        bottomPanel.setBackground(Constants.ORANGE);
        bottomPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addComponent(bottomPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(optionsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(actionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAction1ActionPerformed
        if (!belong) {
            Connection con = DBConnection.getConnection();
            Statement stmt;
            String st = null, st2 = null, st3 = null;
            Timestamp now = new Timestamp(new Date().getTime());
            int notificationCode = -1;
            switch (status) {
                case 0:// nothing
                case 3://bloqueado
                    if (!user.isPrivate()) {
                        st = String.format("update userrel set status = 2, datestamp = '%s' where srcuser = '%s' and tgtuser = '%s';", now, UserProfile.CURRENT_USER.getUsername(), user.getUsername());
                        st2 = String.format("update userprofile set nfollowers = nfollowers + 1 where login = '%s';", user.getUsername());
                        user.setNumberFollowers(user.getNumberFollowers() + 1);
                        st3 = String.format("update userprofile set nfollowing = nfollowing + 1 where login = '%s';", UserProfile.CURRENT_USER.getUsername());
                        UserProfile.CURRENT_USER.setNumberFollowing(UserProfile.CURRENT_USER.getNumberFollowing() + 1);
                        status = 2;
                        notificationCode = 4;
                        btAction1.setText("Unfollow");
                    } else {
                        st = String.format("update userrel set status = 1, datestamp = '%s' where srcuser = '%s' and tgtuser = '%s';", now, UserProfile.CURRENT_USER.getUsername(), user.getUsername());
                        status = 1;
                        notificationCode = 2;
                        btAction1.setText("Pending");
                    }
                    break;
                case -1:
                    if (!user.isPrivate()) {
                        st = String.format("insert into userrel values ('%s', '%s', '%s', '%d')", UserProfile.CURRENT_USER.getUsername(), user.getUsername(), now, 2);
                        st2 = String.format("update userprofile set nfollowers = nfollowers + 1 where login = '%s';", user.getUsername());
                        user.setNumberFollowers(user.getNumberFollowers() + 1);
                        st3 = String.format("update userprofile set nfollowing = nfollowing + 1 where login = '%s';", UserProfile.CURRENT_USER.getUsername());
                        UserProfile.CURRENT_USER.setNumberFollowing(UserProfile.CURRENT_USER.getNumberFollowing() + 1);
                        status = 2;
                        notificationCode = 4;
                        btAction1.setText("Unfollow");
                    } else {
                        st = String.format("insert into userrel values ('%s', '%s', '%s', '%d')", UserProfile.CURRENT_USER.getUsername(), user.getUsername(), now, 1);
                        status = 1;
                        notificationCode = 2;
                        btAction1.setText("Pending");
                    }
                    break;
                case 1: //requested
                    st = String.format("update userrel set status = 0, datestamp = '%s' where srcuser = '%s' and tgtuser = '%s';", now, UserProfile.CURRENT_USER.getUsername(), user.getUsername());
                    status = 0;
                    notificationCode = 3;
                    btAction1.setText("Follow");
                    break;
                case 2: //following
                    st = String.format("update userrel set status = 0, datestamp = '%s' where srcuser = '%s' and tgtuser = '%s';", now, UserProfile.CURRENT_USER.getUsername(), user.getUsername());
                    st2 = String.format("update userprofile set nfollowers = nfollowers - 1 where login = '%s';", user.getUsername());
                    user.setNumberFollowers(user.getNumberFollowers() - 1);
                    st3 = String.format("update userprofile set nfollowing = nfollowing - 1 where login = '%s';", UserProfile.CURRENT_USER.getUsername());
                    UserProfile.CURRENT_USER.setNumberFollowing(UserProfile.CURRENT_USER.getNumberFollowing() - 1);
                    status = 0;
                    notificationCode = 5; // delete
                    btAction1.setText("Follow");
                    break;
                
            }
            try {
                stmt = con.createStatement();
                if (st != null) {
                    stmt.addBatch(st);
                }
                if (st2 != null) {
                    stmt.addBatch(st2);
                }
                if (st3 != null) {
                    stmt.addBatch(st3);
                }
                stmt.executeBatch();
                stmt.close();
                switch (notificationCode) {
                    case 2: // Requested
                    case 4: // New follower
                        st = String.format("INSERT INTO notifications(target, src, ndate, code) values('%s', '%s', '%s', %d);", user.getUsername(), UserProfile.CURRENT_USER.getUsername(), now, notificationCode);
                        stmt = con.createStatement();
                        stmt.executeUpdate(st);
                        stmt.close();
                        break;
                    case 3: // Unrequested
                    case 5: // Unfollow
                        st = String.format("DELETE FROM notifications where code = %d and target = '%s' and src = '%s';", notificationCode - 1, user.getUsername(), UserProfile.CURRENT_USER.getUsername());
                        stmt = con.createStatement();
                        stmt.executeUpdate(st);
                        stmt.close();
                        break;
                    default: // NOP
                        break;
                }
                updateFollowers();
                if (state == ProfileState.FOLLOWERS) {
                    myCuckoos.getViewport().setView(showFollowers());
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (belong) {
            if (!bio.isEditable()) {
                bio.setEditable(true);
                btAction1.setText("Save");
            } else {
                btAction1.setText("Edit");
                user.setBio(bio.getText());
                bio.setEditable(false);
                try {
                    Connection con = DBConnection.getConnection();
                    PreparedStatement stmt;
                    stmt = con.prepareStatement("UPDATE userprofile SET bio = ? where login = ?;");
                    stmt.setString(1, bio.getText());
                    stmt.setString(2, user.getUsername());
                    stmt.executeUpdate();
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }//GEN-LAST:event_btAction1ActionPerformed

    private void btAction2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAction2ActionPerformed
        if (belong) {
            String options[] = {"Private", "Public"};
            int selection = JOptionPane.showOptionDialog(home, "Choose your profile visibility: ", "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            switch (selection) {
                case 0: // Private
                    if (!user.isPrivate()) {
                        user.setPrivate(true);
                        try {
                            Connection con = DBConnection.getConnection();
                            PreparedStatement stmt;
                            stmt = con.prepareStatement("UPDATE userprofile SET visibility = ? where login = ?;");
                            stmt.setBoolean(1, user.isPrivate());
                            stmt.setString(2, user.getUsername());
                            stmt.executeUpdate();
                            stmt.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case 1: // Public
                    if (user.isPrivate()) {
                        user.setPrivate(false);
                        try {
                            Connection con = DBConnection.getConnection();
                            PreparedStatement stmt;
                            stmt = con.prepareStatement("UPDATE userprofile SET visibility = ? where login = ?;");
                            stmt.setBoolean(1, user.isPrivate());
                            stmt.setString(2, user.getUsername());
                            stmt.executeUpdate();
                            stmt.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                default: // Something else
                    break;
            }
        } else {
            String st = null;
            switch (status) {
                case -1:
                    st = String.format("INSERT INTO userrel VALUES ('%s', '%s', now(), 3)", UserProfile.CURRENT_USER.getUsername(), this.user.getUsername());
                    break;
                default:
                    st = String.format("UPDATE userrel SET datestamp = now(), status = 3 WHERE srcuser = '%s' and tgtuser = '%s'",
                            UserProfile.CURRENT_USER.getUsername(), this.user.getUsername());
                    break;
            }
            try {
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement();
                stmt.executeUpdate(st);
                handleBlock(con);
            } catch (SQLException ex) {
                Logger.getLogger(ProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            checkRelation();

        }
    }//GEN-LAST:event_btAction2ActionPerformed

    private void btCuckoosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCuckoosActionPerformed
        CardLayout cl = (CardLayout) bottomPanel.getLayout();
        if (state == ProfilePanel.ProfileState.LIST_POSTS) {
            cl.show(bottomPanel, "newpost");
            btCuckoos.setText("Cuckoos");
            state = ProfilePanel.ProfileState.NEW_POST;
            bottomPanel.revalidate();
        } else {
            cl.show(bottomPanel, "cuckoos");
            updateCuckoos();
            if (belong) {
                btCuckoos.setText("New Cuckoo");
            }
            state = ProfileState.LIST_POSTS;
        }
        updateButtons();
    }//GEN-LAST:event_btCuckoosActionPerformed

    private void btFollowersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFollowersActionPerformed
        if (state != ProfileState.FOLLOWERS) {
            CardLayout cl = (CardLayout) bottomPanel.getLayout();
            cl.show(bottomPanel, "cuckoos");
            Container cont = showFollowers();
            myCuckoos.getViewport().setView(cont);
            myCuckoos.getViewport().setBackground(Constants.ORANGE);
            state = ProfileState.FOLLOWERS;
            updateButtons();
        }
    }//GEN-LAST:event_btFollowersActionPerformed

    private void btFollowingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFollowingActionPerformed
        if (state != ProfileState.FOLLOWING) {
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement stmt = null;
                ResultSet result = null;
                Container cont = new Container();
                cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
                stmt = con.prepareStatement("SELECT * FROM userprofile INNER JOIN userrel ON userprofile.login = userrel.tgtuser WHERE srcuser = '" + user.getUsername() + "' AND status = 2;");
                result = stmt.executeQuery();
                int i = 0;
                while (result.next()) {
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

    private Container showFollowers() {
        Container cont = null;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet result = null;
            cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            stmt = con.prepareStatement("SELECT * FROM userprofile INNER JOIN userrel ON userprofile.login = userrel.srcuser WHERE tgtuser = '" + user.getUsername() + "' AND status = 2;");
            result = stmt.executeQuery();
            int i = 0;
            while (result.next()) {
                cont.add(new UserResult(new UserProfile(result.getString("realname"), result.getString("login"), result.getString("bio"),
                        result.getBoolean("visibility"), result.getInt("nfollowers"), result.getInt("nfollowing"), result.getString("lasttime")), i % 2 == 0 ? Constants.WHITE : Constants.GRAY, home));
                i++;
            }
            cont.revalidate();
            result.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cont;
    }

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
            myCuckoos.setPreferredSize(bottomPanel.getSize());
            myCuckoos.getViewport().setView(cont);

        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFollowers() {
        btFollowers.setText("" + user.getNumberFollowers() + " Followers");
        btFollowing.setText("" + user.getNumberFollowing() + " Following");
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
                        btCuckoos.setEnabled(false);
                        btFollowers.setEnabled(false);
                        btFollowing.setEnabled(false);
                        JLabel blockLabel = new JLabel();
                        blockLabel.setText("<html><div WIDTH=" + getWidth() + "><div align = 'center'>This user has blocked you. <b>SAD!</b></html>");
                        blockLabel.setFont(new Font("Lucida Grande", 0, 24));
                        blockLabel.setForeground(Color.WHITE);
                        blockLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        blockLabel.addComponentListener(new ComponentAdapter() {
                            @Override
                            public void componentResized(ComponentEvent evt) {
                                blockLabel.setText("<html><div WIDTH=" + getWidth() + "><div align = 'center'>This user has blocked you. <b>SAD!</b></html>");
                            }
                        });
                        myCuckoos.getViewport().setView(blockLabel);
                        result.close();
                        stmt.close();
                        return;
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
                            btAction1.setText("Follow");
                            break;
                        case 1: //requested
                            btAction1.setText("Pending");
                            break;
                        case 2: //following
                            btAction1.setText("Unfollow");
                            break;
                        case 3: //blocked
                            btAction1.setText("Follow");
                            break;
                    }
                } else {
                    status = -1;
                }

                result.close();
                stmt.close();

                if (user.isPrivate() && status != 2) {
                    btCuckoos.setEnabled(false);
                    btFollowers.setEnabled(false);
                    btFollowing.setEnabled(false);
                    JLabel privateLabel = new JLabel();
                        privateLabel.setText("<html><div WIDTH=" + getWidth() + "><div align = 'center'>This profile is private</html>");
                    privateLabel.setFont(new Font("Lucida Grande", 0, 24));
                    privateLabel.setForeground(Color.WHITE);
                    privateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    privateLabel.addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent evt) {
                            privateLabel.setText("<html><div WIDTH=" + getWidth() + "><div align = 'center'>This profile is private</html>");
                        }
                    });
                    myCuckoos.getViewport().setView(privateLabel);
                } else {
                    updateCuckoos();
                }
                stmt = con.prepareStatement("select * from userrel where srcuser = '" + user.getUsername() + "' and tgtuser = '" + UserProfile.CURRENT_USER.getUsername() + "' and status = 2 ");
                result = stmt.executeQuery();
                follows = result.next();
                if (follows) {
                    usernameLabel.setText("@" + user.getUsername() + " <Follows you>");
                }
                result.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void updateButtons() {
        for (Component c : optionsPanel.getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(Color.WHITE);
                c.setForeground(Constants.PURPLE);
            }
        }
        switch (state) {
            case FOLLOWERS:
                btCuckoos.setText("Cuckoos");
                btFollowers.setForeground(Constants.WHITE);
                btFollowers.setBackground(Constants.PURPLE);
                break;
            case FOLLOWING:
                btCuckoos.setText("Cuckoos");
                btFollowing.setForeground(Constants.WHITE);
                btFollowing.setBackground(Constants.PURPLE);
                break;
        }
    }

    private void handleBlock(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        String st = String.format("DELETE FROM commnt where author = '%s' and pauthor = '%s'", user.getUsername(), UserProfile.CURRENT_USER.getUsername());
        stmt.executeUpdate(st);
        st = String.format("DELETE FROM commnt where author = '%s' and pauthor = '%s'", UserProfile.CURRENT_USER.getUsername(), user.getUsername());
        stmt.executeUpdate(st);
        stmt.close();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JTextArea bio;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton btAction1;
    private javax.swing.JButton btAction2;
    private javax.swing.JButton btCuckoos;
    private javax.swing.JButton btFollowers;
    private javax.swing.JButton btFollowing;
    private javax.swing.JScrollPane myCuckoos;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
