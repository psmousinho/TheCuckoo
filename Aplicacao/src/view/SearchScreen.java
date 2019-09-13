/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.UserProfile;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import util.Constants;
import util.DBConnection;

/**
 *
 * @author aluno
 */
public class SearchScreen extends javax.swing.JPanel {

    private UserProfile user;
    private Home home;

    public SearchScreen(UserProfile user, Home home) {
        this.user = user;
        this.home = home;
        
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        contentPanel = new javax.swing.JPanel();

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(contentPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void doSearch(String request) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet result = null;
            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            int i = 0;
            switch (request.charAt(0)) {
                case '@': //User
                    stmt = con.prepareStatement("SELECT * from userprofile WHERE login LIKE '" + request.substring(1) + "%' ORDER BY nfollowers DESC;");
                    result = stmt.executeQuery();
                    while(result.next()) {
                        cont.add(new UserResult(new UserProfile(result.getString("realname"), result.getString("login"), result.getString("bio"), 
                                                    result.getBoolean("visibility"), result.getInt("nfollowers"), result.getInt("nfollowing"), result.getString("lasttime")), i % 2 == 0 ? Constants.WHITE : Constants.GRAY, home));
                        i++;
                    }
                    break;
                case '#': //Topic
                    stmt = con.prepareStatement("SELECT * from topic WHERE tname LIKE '" + request.substring(1) + "%' ORDER BY datestamp DESC;");
                    result = stmt.executeQuery();
                    while(result.next()) {
                        i++;
                    }
                    break;
                default: //Something else
                    break;
            }
            if(stmt != null && result != null) {
                cont.revalidate();
                scrollPane.getViewport().setView(cont);
                scrollPane.getViewport().setBackground(Constants.ORANGE);
                result.close();
                stmt.close();
            }
        }catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
