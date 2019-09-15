package view;

import entity.Post;
import entity.UserProfile;
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
import javax.swing.JPanel;
import util.DBConnection;

public class Cuckoo extends JPanel {

    private Post post;
    private Home home;

    public Cuckoo(Post post, Home home) {
        this.post = post;
        this.home = home;
        initComponents();

        if (post.getPhoto() == null) {
            image.setVisible(false);
        }

        btDelete.setVisible(post.getAuthor().getUsername() == UserProfile.CURRENT_USER.getUsername());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        author = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        comment = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCommnt = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        btDelete = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        author.setText("@" + this.post.getAuthor().getUsername());
        author.setToolTipText("\"Go to Profile\"");
        author.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorMouseClicked(evt);
            }
        });

        image.setText("Image");

        comment.setText("comment");
        comment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentActionPerformed(evt);
            }
        });

        jLabel1.setText(this.post.getText());

        jScrollPane1.setVisible(false);

        txtCommnt.setColumns(20);
        txtCommnt.setRows(5);
        jScrollPane1.setViewportView(txtCommnt);

        btDelete.setText("X");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(comment))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(author)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(author)
                    .addComponent(btDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(image)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void commentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentActionPerformed
        if (jScrollPane1.isVisible()) {
            if (!txtCommnt.getText().equals("")) {

                //lidar com marcaçoes
                //lidar com topicos
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

    private void authorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authorMouseClicked
        home.changeScreenTemporary(new ProfilePanel(post.getAuthor(), home, false));
    }//GEN-LAST:event_authorMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        home.changeScreenTemporary(new PostScreen(this.post, home));
    }//GEN-LAST:event_formMouseClicked

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        try {
            Connection con = DBConnection.getConnection();

            deleteTags(con);
            deleteTopics(con);
            deleteComments(con);

            String st = String.format("delete from post where author = '%s' and datestamp = '%s'",
                    post.getAuthor().getUsername(), post.getDate());

            Statement stmt = con.createStatement();
            stmt.executeUpdate(st);

            stmt.close();
            this.getParent().getParent().revalidate();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btDeleteActionPerformed

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
                st = String.format("INSERT INTO notifications(target, src, ndate, code, cauthor, cdate, cpauthor, cpdate) values('%s','%s','%s', 1, '%s', '%s', '%s', '%s');", tagUser, UserProfile.CURRENT_USER.getUsername(), now, UserProfile.CURRENT_USER.getUsername(), now, post.getAuthor().getUsername(), post.getDate());
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
                st = String.format("INSERT INTO topic VALUES( '%s');", matcher.group().substring(1));
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
            }
            st = String.format("INSERT INTO topiccomment VALUES('%s','%s','%s', '%s', '%s');", matcher.group().substring(1), UserProfile.CURRENT_USER.getUsername(), post.getAuthor().getUsername(), now, post.getDate());
            stmt = con.prepareStatement(st);
            stmt.executeUpdate();
            stmt.close();
        }
    }

    private void deleteTags(Connection con) throws SQLException {
        String st = String.format("delete from tagpostuser where pauthor = '%s' and pdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        Statement stmt = con.createStatement();
        stmt.executeUpdate(st);
        st = String.format("delete from tagcommntuser where cpauthor = '%s' and cpdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        stmt = con.createStatement();
        stmt.executeUpdate(st);
        st = String.format("delete from notifications where pauthor = '%s' and pdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        stmt.executeUpdate(st);
        stmt.close();

        stmt.close();
    }

    private void deleteTopics(Connection con) throws SQLException {
        String st = String.format("delete from topicpost where pauthor = '%s' and pdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        Statement stmt = con.createStatement();
        stmt.executeUpdate(st);
        st = String.format("delete from topiccommnt where cpauthor = '%s' and cpdate = '%s'", post.getAuthor().getUsername(), post.getDate());
        stmt = con.createStatement();
        stmt.executeUpdate(st);
        stmt.close();
    }

    private void deleteComments(Connection con) throws SQLException {
        String st = String.format("delete from commnt where pauthor = '%s' and pdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        Statement stmt = con.createStatement();
        stmt.executeUpdate(st);
        st = String.format("delete from notifications where cpauthor = '%s' and cpdate = '%s'",
                post.getAuthor().getUsername(), post.getDate());
        stmt.executeUpdate(st);
        stmt.close();

        stmt.close();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel author;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton comment;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea txtCommnt;
    // End of variables declaration//GEN-END:variables
}
