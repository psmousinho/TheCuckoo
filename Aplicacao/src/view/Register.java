package view;

import entity.UserProfile;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import util.DBConnection;

public class Register extends JPanel {

    public Register() {
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
        btReturn = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        userNameLabel = new javax.swing.JLabel();
        userNameTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        btRegister = new javax.swing.JButton();

        setBackground(Main.TEAL);
        setPreferredSize(new java.awt.Dimension(404, 252));

        topPanel.setBackground(Main.PURPLE);
        topPanel.setMinimumSize(new java.awt.Dimension(100, 48));

        leftPanel.setBackground(Main.PURPLE);
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
        titleLabel.setText("Join The Cuckoo");
        titleLabel.setPreferredSize(new java.awt.Dimension(120, 48));
        leftPanel.add(titleLabel);

        rightPanel.setBackground(Main.PURPLE);

        btReturn.setBackground(Color.WHITE);
        btReturn.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        btReturn.setForeground(Main.PURPLE);
        btReturn.setText("Return");
        btReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btReturn, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btReturn, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
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

        bottomPanel.setBackground(Main.TEAL);
        bottomPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 12));

        nameLabel.setBackground(Color.WHITE);
        nameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("Insert your name");
        bottomPanel.add(nameLabel);

        nameTextField.setForeground(Main.TEAL);
        bottomPanel.add(nameTextField);

        userNameLabel.setBackground(Color.WHITE);
        userNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userNameLabel.setText("Your profile username");
        bottomPanel.add(userNameLabel);

        userNameTextField.setForeground(Main.TEAL);
        bottomPanel.add(userNameTextField);

        passwordLabel.setBackground(Color.WHITE);
        passwordLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setText("and a password for login");
        bottomPanel.add(passwordLabel);

        passwordField.setForeground(Main.TEAL);
        bottomPanel.add(passwordField);

        btRegister.setBackground(Color.WHITE);
        btRegister.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        btRegister.setForeground(Main.TEAL);
        btRegister.setText("Register");
        btRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegisterActionPerformed(evt);
            }
        });
        bottomPanel.add(btRegister);

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

    private void btRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegisterActionPerformed
        String name = nameTextField.getText(), userName = userNameTextField.getText(), password = new String(passwordField.getPassword());
        
        if(UserProfile.validateUsername(userName)) {
            if(!usernameInUse(userName)) {
                if(!name.trim().equals("") && !userName.trim().equals("") && password.length() > 0) {
                    try {
                        Connection con = DBConnection.getConnection();
                        PreparedStatement stmt = con.prepareStatement("insert into userprofile (login,passw,realname) values (?,?,?)");
                        stmt.setString(1, userName);
                        stmt.setString(2, password);
                        stmt.setString(3, name);
                        stmt.executeUpdate();
                        stmt.close();
                        showMessage(String.format("Welcome to The Cuckoo %s!", name));
                    } catch (Exception ex) {               
                        JOptionPane.showMessageDialog(this, ex.getMessage() , "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                showMessage(String.format("The username %s\nis already in use...", userName));
            }
        } else {
            showMessage(String.format("The username %s\nis cannot be used...", userName));
        }
    }//GEN-LAST:event_btRegisterActionPerformed

    private void btReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturnActionPerformed
        getParent().add(new Landing());
        getParent().remove(this);
    }//GEN-LAST:event_btReturnActionPerformed

    private boolean usernameInUse(String userName) {
        try {
            Connection con = DBConnection.getConnection();
            String st = String.format("select * from userprofile where login = '%s'", userName);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(st);
            boolean valid = result.next();
            result.close();
            stmt.close();
            return valid;
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private void showMessage(String message) {
        bottomPanel.removeAll();
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("<html><div WIDTH="+getWidth()+"><div align = 'center'>" + message + "</html>");
        welcomeLabel.setFont(new Font("Lucida Grande", 0, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(welcomeLabel);
        bottomPanel.revalidate();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton btRegister;
    private javax.swing.JButton btReturn;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField userNameTextField;
    // End of variables declaration//GEN-END:variables
}
