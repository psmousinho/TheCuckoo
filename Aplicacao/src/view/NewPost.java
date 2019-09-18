package view;

import entity.UserProfile;
import java.awt.CardLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import util.DBConnection;
import util.Constants;

public class NewPost extends JPanel {

    private File file;
    private Home home;
    
    public NewPost(Home home) {
        this.home = home;
        
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        newPost = new javax.swing.JLabel();
        addImage = new javax.swing.JButton();
        endPost = new javax.swing.JButton();
        imagePath = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(Constants.WHITE);

        text.setColumns(20);
        text.setLineWrap(true);
        text.setRows(5);
        jScrollPane2.setViewportView(text);

        newPost.setForeground(Constants.WHITE);
        newPost.setText("New Post");

        addImage.setBackground(Constants.WHITE);
        addImage.setForeground(Constants.ORANGE);
        addImage.setText("addImage");
        addImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addImageActionPerformed(evt);
            }
        });

        endPost.setBackground(Constants.WHITE);
        endPost.setForeground(Constants.ORANGE);
        endPost.setText("Post");
        endPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endPostActionPerformed(evt);
            }
        });

        imagePath.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        imagePath.setText("ImagePath");
        imagePath.setVisible(false);

        jLabel1.setForeground(Constants.YELLOW);
        jLabel1.setText("Write something or post a image");
        jLabel1.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(endPost))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(newPost)
                                .addGap(84, 84, 84)
                                .addComponent(jLabel1))
                            .addComponent(imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPost)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addImage)
                    .addComponent(endPost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagePath)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void endPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endPostActionPerformed
        String cuckooText = text.getText();
        if (!cuckooText.equals("") || !imagePath.getText().equals("")) {
            try {
                String path = "null";
                cuckooText = cuckooText.replaceAll("'", "''");
                if (!imagePath.getText().equals("ImagePath")) {
                    moveImage();
                    path = "'" + imagePath.getText() + "'";
                }

                Connection con = DBConnection.getConnection();
                Timestamp now = new Timestamp(new Date().getTime());
                String st = String.format("INSERT INTO post values( '%s','%s','%s',%s);", UserProfile.CURRENT_USER.getUsername(), now, cuckooText, path);
                PreparedStatement stmt = con.prepareStatement(st);
                stmt.executeUpdate();

                addTags(con, now);
                addTopics(con, now);

            } catch (SQLException ex) {
                Logger.getLogger(TimeLineScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            home.updateCuckoos();
            CardLayout cl = (CardLayout) this.getParent().getLayout();
            cl.show(this.getParent(), "cuckoos");
        } else {
            jLabel1.setVisible(true);
        }
    }//GEN-LAST:event_endPostActionPerformed

    private void addImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addImageActionPerformed
        FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "PNG", "jpg", "JPEG file", "jpeg");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(filter);
        int answer = chooser.showOpenDialog(null);
        if (answer == JFileChooser.APPROVE_OPTION) {
            file = new File(chooser.getSelectedFile().getAbsolutePath());
            imagePath.setText(file.getName());
            imagePath.setVisible(true);
        }        
    }//GEN-LAST:event_addImageActionPerformed

    private void addTags(Connection con, Timestamp now) throws SQLException {
        Pattern regex = Pattern.compile("@\\w{4,32}");
        Matcher matcher = regex.matcher(this.text.getText());

        while (matcher.find()) {
            String tagUser = matcher.group().substring(1);
            String st = String.format("SELECT * FROM userprofile where login = '%s'", tagUser);
            PreparedStatement stmt = con.prepareStatement(st);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                st = String.format("INSERT INTO tagpostuser values( '%s', '%s', '%s');", UserProfile.CURRENT_USER.getUsername(), now, tagUser);
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
                boolean notify = false;
                if(!UserProfile.CURRENT_USER.isPrivate()) { // Public profile, no further action needed
                    notify = true;
                } else { // Private profile, must check is tagged user follows author
                    st = String.format("SELECT * FROM userrel where tgtuser = '%s' and srcuser = '%s' and status = 2;", UserProfile.CURRENT_USER.getUsername(), tagUser);
                    stmt = con.prepareStatement(st);
                    ResultSet rs2 = stmt.executeQuery();
                    notify = rs2.next();
                }
                if(notify) {
                    st = String.format("INSERT INTO notifications(target, src, ndate, code, pauthor, pdate) values('%s', '%s', '%s', 0, '%s', '%s');", tagUser, UserProfile.CURRENT_USER.getUsername(), now, UserProfile.CURRENT_USER.getUsername(), now);
                    stmt = con.prepareStatement(st);
                    stmt.executeUpdate();
                }
            }
        }
    }

    private void addTopics(Connection con, Timestamp now) throws SQLException {
        Pattern regex = Pattern.compile("#\\w{4,32}");
        Matcher matcher = regex.matcher(this.text.getText());

        while (matcher.find()) {
            String st = String.format("select * from topic where tname = '%s';", matcher.group().substring(1));
            PreparedStatement stmt = con.prepareStatement(st);
            ResultSet result = stmt.executeQuery();
            boolean topicExists = result.next();
            if (!topicExists) {
                st = String.format("INSERT INTO topic (tname, tdate) VALUES('%s', '%s');", matcher.group().substring(1), now);
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
            }
            st = String.format("INSERT INTO topicpost VALUES('%s','%s','%s');", matcher.group().substring(1), UserProfile.CURRENT_USER.getUsername(), now);
            stmt = con.prepareStatement(st);
            stmt.executeUpdate();
            if (topicExists) {
                st = String.format("update topic set tdate = '%s' where tname = '%s';", now, matcher.group().substring(1)); // Assumindo tempo crescente
                stmt = con.prepareStatement(st);
                stmt.executeUpdate();
            }
            stmt.close();
        }
    }

    private void moveImage() {
        if (file != null) {
            FileInputStream origem;
            FileOutputStream destino;
            FileChannel fcOrigem;
            FileChannel fcDestino;
            try {
                origem = new FileInputStream(file.getPath());
                destino = new FileOutputStream("imagens\\" + file.getName());
                fcOrigem = origem.getChannel();
                fcDestino = destino.getChannel();
                fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
                origem.close();
                destino.close();

                imagePath.setText("imagens\\" + file.getName());

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addImage;
    private javax.swing.JButton endPost;
    private javax.swing.JLabel imagePath;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel newPost;
    private javax.swing.JTextArea text;
    // End of variables declaration//GEN-END:variables
}
