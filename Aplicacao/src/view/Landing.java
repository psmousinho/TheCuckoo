package view;

import entity.UserProfile;
import util.DBConnection;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import util.Constants;

public class Landing extends JPanel {

    public Landing() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        btSignUp = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        userNameTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        emptyLabel = new javax.swing.JLabel();
        btSignIn = new javax.swing.JButton();
        bottomMessageLabel = new javax.swing.JLabel();

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

        titleLabel.setBackground(Color.WHITE);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setText("The Cuckoo");
        titleLabel.setPreferredSize(new java.awt.Dimension(120, 48));
        leftPanel.add(titleLabel);

        rightPanel.setBackground(Constants.PURPLE);

        btSignUp.setBackground(Color.WHITE);
        btSignUp.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        btSignUp.setForeground(Constants.PURPLE);
        btSignUp.setText("Sign Up");
        btSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
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

        bottomPanel.setBackground(Constants.TEAL);
        bottomPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 12));

        userNameLabel.setBackground(Color.WHITE);
        userNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userNameLabel.setText("Username");
        bottomPanel.add(userNameLabel);

        userNameTextField.setForeground(Constants.TEAL);
        bottomPanel.add(userNameTextField);

        passwordLabel.setBackground(Color.WHITE);
        passwordLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setText("Password");
        bottomPanel.add(passwordLabel);

        passwordField.setForeground(Constants.TEAL);
        bottomPanel.add(passwordField);
        bottomPanel.add(emptyLabel);

        btSignIn.setBackground(Color.WHITE);
        btSignIn.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        btSignIn.setForeground(Constants.TEAL);
        btSignIn.setText("Sign In");
        btSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSignInActionPerformed(evt);
            }
        });
        bottomPanel.add(btSignIn);

        bottomMessageLabel.setBackground(Constants.TEAL);
        bottomMessageLabel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        bottomMessageLabel.setForeground(Color.WHITE);
        bottomMessageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bottomMessageLabel.setText("©2019 Cuculidae Developments Incorporated");
        bottomMessageLabel.setOpaque(true);
        bottomPanel.add(bottomMessageLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSignInActionPerformed
        String userName = userNameTextField.getText(), password = new String(passwordField.getPassword());
        try {
            Connection con = DBConnection.getConnection();
            String st = String.format("select * from userprofile where login = '%s' and passw = '%s'", userName, password);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(st);
            if(result.next()) {
                UserProfile user = new UserProfile( result.getString("realname"), result.getString("login"), result.getString("bio"), 
                                                    result.getBoolean("visibility"), result.getInt("nfollowers"), result.getInt("nfollowing"), result.getString("lasttime"));
                UserProfile.CURRENT_USER = user;
                this.getParent().add(new Home(user));
                this.getParent().remove(this);
            } else {
                showMessage("Incorrect username and/or password", Constants.ORANGE);
            }
            result.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSignInActionPerformed

    private void btSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSignUpActionPerformed
        getParent().add(new Register());
        getParent().remove(this);
    }//GEN-LAST:event_btSignUpActionPerformed

    private void showMessage(String message, Color background) {
        bottomMessageLabel.setText(message);
        bottomMessageLabel.setBackground(background);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bottomMessageLabel;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton btSignIn;
    private javax.swing.JButton btSignUp;
    private javax.swing.JLabel emptyLabel;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField userNameTextField;
    // End of variables declaration//GEN-END:variables
}
