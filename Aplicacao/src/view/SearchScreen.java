package view;

import entity.Post;
import entity.Topic;
import entity.UserProfile;
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import util.Constants;
import util.DBConnection;

public class SearchScreen extends JPanel {

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
        String split[] = request.trim().split(" ");
        String args;
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        Container cont = new Container();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        int i = 0;
        try {
            switch(split[0].toLowerCase()) {
                case "user": // Search for user
                    args = request.substring(5);
                    stmt = con.prepareStatement("SELECT * from userprofile WHERE login LIKE '%" + args + "%' OR realname LIKE '%" + args + "%' OR bio LIKE '%" + args + "%'ORDER BY nfollowers DESC;");
                    result = stmt.executeQuery();
                    while(result.next()) {
                        cont.add(new UserResult(new UserProfile(result.getString("realname"), result.getString("login"), result.getString("bio"), 
                                                    result.getBoolean("visibility"), result.getInt("nfollowers"), result.getInt("nfollowing"), result.getString("lasttime")), i % 2 == 0 ? Constants.WHITE : Constants.GRAY, home));
                        i++;
                    }
                    break;
                case "topic": // Search for topic
                    args = request.substring(6);
                    stmt = con.prepareStatement("SELECT * from topic WHERE tname LIKE '%" + args + "%' ORDER BY tdate DESC;");
                    result = stmt.executeQuery();
                    while(result.next()) {
                        cont.add(new TopicResult(new Topic(result.getString("tname"), result.getString("tdate"), result.getInt("npost"), result.getInt("ncommnts")), i % 2 == 0 ? Constants.WHITE : Constants.GRAY, home));
                       // Post post = new Post(this.user, result.getString("datestamp"), result.getString("ptext"), result.getString("foto"));
                       // cont.add(new Cuckoo(post, home));
                        i++;
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(getParent(), "Please use syntax: User/Topic <target>", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
            if(stmt != null && result != null) {
                cont.revalidate();
                scrollPane.getViewport().setView(cont);
                scrollPane.getViewport().setBackground(Constants.ORANGE);
                result.close();
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
