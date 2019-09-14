package view;

import entity.UserProfile;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import util.Constants;

public class Home extends JPanel {

    private enum HomeState {
        TIME_LINE, PROFILE, NOTIFICATIONS, SEARCH, NOTHING;
    }

    private final UserProfile user;
    private HomeState state;

    private final ProfilePanel profile;
    private final NotificationsScreen notifications;
    private final TimeLineScreen timeline;
    private final SearchScreen search;
    private final NewPost newPost;
    private JPanel temporary;
    private boolean emptySearchField;
    
    public Home(UserProfile user) {
        initComponents();
        
        this.emptySearchField = false;
        this.user = user;

        this.profile = new ProfilePanel(this.user, this, true);
        this.notifications = new NotificationsScreen(this.user);
        this.timeline = new TimeLineScreen(this.user, this);
        this.search = new SearchScreen(this.user, this);
        this.newPost = new NewPost();
        this.temporary = new JPanel();
        
        contentPanel.add(profile, "profile");
        contentPanel.add(notifications, "notifications");
        contentPanel.add(timeline, "timeline");
        contentPanel.add(search, "search");
        contentPanel.add(newPost,"newpost");

        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "timeline");
        timeline.updateCuckoos();
        state = HomeState.TIME_LINE;
        btTimeline.setForeground(Constants.WHITE);
        btTimeline.setBackground(Constants.ORANGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchField = new javax.swing.JTextField();
        topPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        btProfile = new javax.swing.JButton();
        btTimeline = new javax.swing.JButton();
        btNotifications = new javax.swing.JButton();
        btSearch = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        btSignOut = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();

        searchField.setColumns(12);
        searchField.setForeground(Constants.PURPLE);
        searchField.setText("Search for User/Topic");
        searchField.setToolTipText("Syntax: User/Topic <target>");
        searchField.setMinimumSize(new java.awt.Dimension(11, 48));
        searchField.setPreferredSize(new java.awt.Dimension(154, 48));
        searchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchFieldMouseClicked(evt);
            }
        });
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        setBackground(Constants.TEAL);

        topPanel.setBackground(Constants.PURPLE);
        topPanel.setMinimumSize(new java.awt.Dimension(100, 48));

        leftPanel.setBackground(Constants.PURPLE);
        leftPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));

        logoLabel.setBackground(Color.WHITE);
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setText("LOGO");
        logoLabel.setMaximumSize(new java.awt.Dimension(48, 48));
        logoLabel.setMinimumSize(new java.awt.Dimension(48, 48));
        logoLabel.setPreferredSize(new java.awt.Dimension(48, 48));
        leftPanel.add(logoLabel);

        btProfile.setBackground(Color.WHITE);
        btProfile.setForeground(Constants.PURPLE);
        btProfile.setText("P");
        btProfile.setToolTipText("Profile");
        btProfile.setMaximumSize(new java.awt.Dimension(48, 48));
        btProfile.setMinimumSize(new java.awt.Dimension(48, 48));
        btProfile.setPreferredSize(new java.awt.Dimension(48, 48));
        btProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProfileActionPerformed(evt);
            }
        });
        leftPanel.add(btProfile);

        btTimeline.setBackground(Color.WHITE);
        btTimeline.setForeground(Constants.PURPLE);
        btTimeline.setText("T");
        btTimeline.setToolTipText("Timeline");
        btTimeline.setMaximumSize(new java.awt.Dimension(48, 48));
        btTimeline.setMinimumSize(new java.awt.Dimension(48, 48));
        btTimeline.setPreferredSize(new java.awt.Dimension(48, 48));
        btTimeline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimelineActionPerformed(evt);
            }
        });
        leftPanel.add(btTimeline);

        btNotifications.setBackground(Color.WHITE);
        btNotifications.setForeground(Constants.PURPLE);
        btNotifications.setText("N");
        btNotifications.setToolTipText("Notifications");
        btNotifications.setMaximumSize(new java.awt.Dimension(48, 48));
        btNotifications.setMinimumSize(new java.awt.Dimension(48, 48));
        btNotifications.setPreferredSize(new java.awt.Dimension(48, 48));
        btNotifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNotificationsActionPerformed(evt);
            }
        });
        leftPanel.add(btNotifications);

        btSearch.setBackground(Color.WHITE);
        btSearch.setForeground(Constants.PURPLE);
        btSearch.setText("S");
        btSearch.setToolTipText("Search");
        btSearch.setMaximumSize(new java.awt.Dimension(48, 48));
        btSearch.setMinimumSize(new java.awt.Dimension(48, 48));
        btSearch.setPreferredSize(new java.awt.Dimension(48, 48));
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchActionPerformed(evt);
            }
        });
        leftPanel.add(btSearch);

        rightPanel.setBackground(Constants.PURPLE);

        btSignOut.setBackground(Color.WHITE);
        btSignOut.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        btSignOut.setForeground(Constants.PURPLE);
        btSignOut.setText("Sign Out");
        btSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSignOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btSignOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btSignOut, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        contentPanel.setBackground(Constants.TEAL);
        contentPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSignOutActionPerformed
        this.getParent().add(new Landing());
        this.getParent().remove(this);
        UserProfile.CURRENT_USER = null;
    }//GEN-LAST:event_btSignOutActionPerformed

    private void btTimelineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimelineActionPerformed
        if (state != HomeState.TIME_LINE) {
            state = HomeState.TIME_LINE;
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "timeline");
            timeline.updateCuckoos();
            updateButtons();
        }
    }//GEN-LAST:event_btTimelineActionPerformed

    private void btProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProfileActionPerformed
        if (state != HomeState.PROFILE) {
            state = HomeState.PROFILE;
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "profile");
            profile.updateCuckoos();
            profile.updateFollowers();
            updateButtons();
        }
    }//GEN-LAST:event_btProfileActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "search");
        search.doSearch(searchField.getText());
    }//GEN-LAST:event_searchFieldActionPerformed

    private void btSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchActionPerformed
        if (state != HomeState.SEARCH) {
            state = HomeState.SEARCH;
            leftPanel.remove(btNotifications);
            leftPanel.remove(btTimeline);
            leftPanel.remove(btProfile);
            //leftPanel.remove(btNewPost);
            leftPanel.add(searchField);
            btSearch.setText("R");
            btSearch.setToolTipText("Return");
            if(emptySearchField) {
                searchField.setText("Search for User/Topic");
                emptySearchField = false;
            }
            leftPanel.revalidate();
        } else {
            state = HomeState.NOTHING;
            leftPanel.remove(searchField);
            leftPanel.remove(btSearch);
            leftPanel.add(btProfile);
            leftPanel.add(btTimeline);
            leftPanel.add(btNotifications);
            leftPanel.add(btSearch);
            btSearch.setText("S");
            btSearch.setToolTipText("Search");
            leftPanel.revalidate();
        }
    }//GEN-LAST:event_btSearchActionPerformed

    private void btNotificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNotificationsActionPerformed
        if (state != HomeState.NOTIFICATIONS) {
            state = HomeState.NOTIFICATIONS;
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "notifications");
            notifications.updatePostTab();
            updateButtons();
        }
    }//GEN-LAST:event_btNotificationsActionPerformed

    private void searchFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchFieldMouseClicked
        if(!emptySearchField) {
            searchField.setText("");
            emptySearchField = true;
        }
    }//GEN-LAST:event_searchFieldMouseClicked

    private void updateButtons() {
        for (Component c : leftPanel.getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(Color.WHITE);
                c.setForeground(Constants.PURPLE);
            }
        }
        switch (state) {
            case TIME_LINE:
                btTimeline.setForeground(Constants.WHITE);
                btTimeline.setBackground(Constants.ORANGE);
                break;
            case PROFILE:
                btProfile.setForeground(Constants.WHITE);
                btProfile.setBackground(Constants.ORANGE);
                break;
            case NOTIFICATIONS:
                btNotifications.setForeground(Constants.WHITE);
                btNotifications.setBackground(Constants.ORANGE);
                break;
        }
    }

    public void changeScreenTemporary(JPanel target) {
        contentPanel.remove(temporary);
        temporary = target;
        contentPanel.add(temporary, "temporary");
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "temporary");
        state = HomeState.NOTHING;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btNotifications;
    private javax.swing.JButton btProfile;
    private javax.swing.JButton btSearch;
    private javax.swing.JButton btSignOut;
    private javax.swing.JButton btTimeline;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
