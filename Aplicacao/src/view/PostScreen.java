package view;

import entity.*;
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import util.DBConnection;

public class PostScreen extends JPanel {

    private Post post;
    private Home home;
    private boolean self;
    
    public PostScreen(Post post, Home home, boolean self) {
        this.post = post;
        this.home = home;
        this.self = self;
        
        initComponents();

        updateComments();
        if(post.getPhoto() == null) {
            btImage.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        author = new javax.swing.JLabel();
        comment = new javax.swing.JButton();
        txtPost = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCommnt = new javax.swing.JTextArea();
        commnts = new javax.swing.JScrollPane();
        btImage = new javax.swing.JButton();

        author.setText("@" + this.post.getAuthor().getUsername());
        author.setToolTipText("\"Go to Profile\"");
        author.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorMouseClicked(evt);
            }
        });

        comment.setText("comment");
        comment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentActionPerformed(evt);
            }
        });

        txtPost.setText(this.post.getText());

        jScrollPane1.setVisible(false);

        txtCommnt.setColumns(20);
        txtCommnt.setRows(5);
        jScrollPane1.setViewportView(txtCommnt);

        btImage.setText("View Image");
        btImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(comment)
                        .addGap(30, 30, 30))
                    .addComponent(commnts)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(author)
                            .addComponent(txtPost)
                            .addComponent(btImage))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(author)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPost)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btImage)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(commnts, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void authorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authorMouseClicked
        home.changeScreenTemporary(new ProfilePanel(post.getAuthor(), home, self));
    }//GEN-LAST:event_authorMouseClicked

    private void commentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentActionPerformed
        if (jScrollPane1.isVisible()) {
            if (!txtCommnt.getText().equals("")) {

                Connection con = DBConnection.getConnection();
                try {
                    Timestamp now = new Timestamp(new Date().getTime());
                    String st = String.format("INSERT INTO commnt values('%s','%s','%s','%s','%s');",
                            UserProfile.CURRENT_USER.getUsername(), post.getAuthor().getUsername(), post.getDate(), now, txtCommnt.getText());
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(st);
                    addTags(con, now);
                    addTopics(con, now);
                } catch (SQLException ex) {
                    Logger.getLogger(Cuckoo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            txtCommnt.setText("");
            jScrollPane1.setVisible(false);
        } else {
            jScrollPane1.setVisible(true);
        }
        this.revalidate();
    }//GEN-LAST:event_commentActionPerformed

    private void btImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImageActionPerformed
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(post.getPhoto()));
        JOptionPane.showMessageDialog(this, image, "Image", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_btImageActionPerformed

    private void addTags(Connection con, Timestamp now) throws SQLException {
        Pattern regex = Pattern.compile("@\\w{4,32}");
        Matcher matcher = regex.matcher(txtCommnt.getText());

        while (matcher.find()) {
            String tagUser = matcher.group().substring(1);
            String st = String.format("SELECT * FROM userprofile where login = '%s'", tagUser);
            PreparedStatement stmt = con.prepareStatement(st);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                st = String.format("INSERT INTO tagcommntuser values( '%s', '%s', '%s', '%s', '%s');",
                        UserProfile.CURRENT_USER.getUsername(), now, post.getAuthor().getUsername(), post.getDate(), tagUser);
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
                st = String.format("INSERT INTO notifications(target, src, ndate, code, cauthor, cdate, cpauthor, cpdate) values('%s', '%s', '%s', 1, '%s', '%s', '%s', '%s');", tagUser, UserProfile.CURRENT_USER.getUsername(), now, UserProfile.CURRENT_USER.getUsername(), now, post.getAuthor().getUsername(), post.getDate());
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
            }

        }
    }

    private void addTopics(Connection con, Timestamp now) throws SQLException {
        Pattern regex = Pattern.compile("#\\w{4,32}");
        Matcher matcher = regex.matcher(txtCommnt.getText());

        while (matcher.find()) {
            String st = String.format("select * from topic where tname = '%s';", matcher.group().substring(1));
            PreparedStatement stmt = con.prepareStatement(st);
            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                st = String.format("INSERT INTO topic (tname, tdate) VALUES('%s', '%s');", matcher.group().substring(1), now);
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
            }
            st = String.format("INSERT INTO topiccomment VALUES('%s','%s','%s', '%s', '%s');", matcher.group().substring(1), UserProfile.CURRENT_USER.getUsername(), post.getAuthor().getUsername(), now, post.getDate());
            stmt = con.prepareStatement(st);
            stmt.executeUpdate();
            stmt.close();
        }
    }

    private void updateComments() {
        try {
            Connection con = DBConnection.getConnection();
            String st = String.format("SELECT * from commnt WHERE pauthor = '%s' and pdate = '%s'", post.getAuthor().getUsername(), post.getDate());
            PreparedStatement stmt = con.prepareStatement(st);
            ResultSet result = stmt.executeQuery();
            Container cont = new Container();
            cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
            while (result.next()) {
                st = String.format("SELECT * from userprofile left join userrel on userprofile.login = userrel.tgtuser and srcuser = '%s' where userprofile.login = '%s'", UserProfile.CURRENT_USER.getUsername(), result.getString("author"));
                stmt = con.prepareStatement(st);
                ResultSet result2 = stmt.executeQuery();
                result2.next();
                if (result2.getInt("status") != 3) {
                    UserProfile author = new UserProfile(result2.getString("realname"), result2.getString("login"), result2.getString("bio"), result2.getBoolean("visibility"), result2.getInt("nfollowers"), result2.getInt("nfollowing"), result2.getString(("lasttime")));
                    Comment comment = new Comment(author, result.getString("pauthor"), result.getString("pdate"), result.getString("datestamp"), result.getString("ctext"));
                    cont.add(new CommentPanel(comment, home));
                } else {
                    continue;
                }
            }
            cont.revalidate();
            commnts.getViewport().setView(cont);

        } catch (SQLException ex) {
            Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel author;
    private javax.swing.JButton btImage;
    private javax.swing.JButton comment;
    private javax.swing.JScrollPane commnts;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtCommnt;
    private javax.swing.JLabel txtPost;
    // End of variables declaration//GEN-END:variables
}
