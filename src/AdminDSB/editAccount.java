package AdminDSB;

import Config.DBConnector;
import Config.Session;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class editAccount extends javax.swing.JFrame {

    public File selectedFile;
    public String path2 = null;
    public String destination = "";
    public String oldPath;
    public String path;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern CONTACT_PATTERN = Pattern.compile("^[0-9]{11}$");

    public editAccount() {
        initComponents();
    }

    private boolean updateChecker() throws SQLException {
        ResultSet rs = new DBConnector().getData("select * from users where (lastname = '" + fn.getText() + "' or email = '" + email.getText() + "') and id != '" + id.getText() + "'");
        if (rs.next()) {
            String xemail = rs.getString("email");
            if (xemail.equalsIgnoreCase(email.getText())) {
                errorMessage("EMAIL HAS BEEN USED!");
            }
            String xusername = rs.getString("lastname");
            if (xusername.equalsIgnoreCase(fn.getText())) {
                errorMessage("LASTNAME HAS BEEN USED!");
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean validationChecker() {

        if (email.getText().isEmpty() || !EMAIL_PATTERN.matcher(email.getText()).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid email address!");
            return false;
        } else if (contact.getText().isEmpty() || !CONTACT_PATTERN.matcher(contact.getText()).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid contact number! Must be 11 digits.");
            return false;
        } else if (fn.getText().isEmpty() || email.getText().isEmpty() || contact.getText().isEmpty()) {
            errorMessage("FILL ALL THE REQUIREMENTS!");
            return false;
        } else if (!contact.getText().matches("\\d+")) {
            errorMessage("CONTACT MUST CONTAIN ONLY DIGITS!");
            return false;
        } else if (selectedFile == null && icon1.getIcon() == null) {
            errorMessage("PLEASE INSERT AN IMAGE FIRST!");
            return false;
        } else {
            return true;
        }
    }

    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);

            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();

            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);

            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }

        return -1;
    }

    private ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage = null;
        if (ImagePath != null) {
            MyImage = new ImageIcon(ImagePath);
        } else {
            MyImage = new ImageIcon(pic);
        }

        int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    private int FileExistenceChecker(String path) {
        File file = new File(path);
        String fileName = file.getName();

        Path filePath = Paths.get("src/ImageDB", fileName);
        boolean fileExists = Files.exists(filePath);

        if (fileExists) {
            return 1;
        } else {
            return 0;
        }

    }

    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ERROR!", JOptionPane.ERROR_MESSAGE);
    }

    private void successMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        status = new javax.swing.JComboBox<>();
        type = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        fn = new javax.swing.JTextField();
        contact = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        icon1 = new javax.swing.JLabel();
        select = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        ln = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        status.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVE", "INACTIVE", "PENDING" }));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });
        jPanel3.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 240, -1));

        type.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "USER" }));
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });
        jPanel3.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 240, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton2.setText("UPDATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 100, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton1.setText("CANCEL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 100, -1));

        email.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        email.setText("EMAIL ");
        email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailMouseClicked(evt);
            }
        });
        jPanel3.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 240, 30));

        fn.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        fn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fn.setText("FIRSTNAME");
        fn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fnFocusGained(evt);
            }
        });
        fn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fnMouseClicked(evt);
            }
        });
        jPanel3.add(fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 240, 30));

        contact.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        contact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contact.setText("CONTACT#\n");
        contact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contactFocusGained(evt);
            }
        });
        jPanel3.add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 240, 30));

        jLabel17.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel17.setText("UPDATE AN ACCOUNT");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 230, 40));

        jLabel16.setFont(new java.awt.Font("Yu Gothic", 1, 10)); // NOI18N
        jLabel16.setText("PLEASE DOUBLE CHECK DETAILS UPON CONFIRMING!");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 290, 40));

        id.setEditable(false);
        id.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        id.setText("ID");
        id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                idMouseClicked(evt);
            }
        });
        jPanel3.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 240, 30));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 200));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 260, 220));

        select.setBackground(new java.awt.Color(255, 255, 255));
        select.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        select.setText("SELECT");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel3.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 260, -1));

        remove.setBackground(new java.awt.Color(255, 255, 255));
        remove.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        remove.setText("REMOVE");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel3.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 260, -1));

        ln.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        ln.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ln.setText("LASTNAME");
        ln.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lnFocusGained(evt);
            }
        });
        ln.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnMouseClicked(evt);
            }
        });
        jPanel3.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 240, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 490));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel15.setText("SEARCH");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 90, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailMouseClicked
        email.setText("");
    }//GEN-LAST:event_emailMouseClicked

    private void contactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactFocusGained
        contact.setText("");
    }//GEN-LAST:event_contactFocusGained

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
    }//GEN-LAST:event_typeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new AdminDashboard().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if (updateChecker()) {
            } else if (!validationChecker()) {
            } else {
                new DBConnector().updateData("update users set lastname = '" + ln.getText() + "', firstname = '" + fn.getText() + "', email = '" + email.getText() + "',"
                        + "contact = '" + contact.getText() + "', type = '" + type.getSelectedItem() + "', "
                        + "status = '" + status.getSelectedItem() + "' , Image = '" + destination + "' where id = '" + id.getText() + "'");

                if (selectedFile != null) {
                    Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }

                successMessage("ACCOUNT SUCCESSFULLY UPDATED!");

                LocalDateTime currentDatetime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDate = currentDatetime.format(formatter);

                new DBConnector().insertData("insert into logs (u_id,actions,date) values ('" + Session.getInstance().getId() + "','Updated An Account','" + formattedDate + "')");

                new AdminDashboard().setVisible(true);
                dispose();
            }
        } catch (SQLException er) {
            System.out.println("Eror: " + er.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(editAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
    }//GEN-LAST:event_statusActionPerformed

    private void fnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fnMouseClicked
    }//GEN-LAST:event_fnMouseClicked

    private void fnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fnFocusGained
        fn.setText("");
    }//GEN-LAST:event_fnFocusGained

    private void idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idMouseClicked
    }//GEN-LAST:event_idMouseClicked

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/ImageDB/" + selectedFile.getName();
                path = selectedFile.getAbsolutePath();

                if (FileExistenceChecker(path) == 1) {
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path = "";
                } else {
                    icon1.setIcon(ResizeImage(path, null, icon1));
                    remove.setEnabled(true);
                    select.setEnabled(false);
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    }//GEN-LAST:event_selectActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        destination = "";
        icon1.setIcon(null);
        path = "";
        select.setEnabled(true);
        remove.setEnabled(false);
    }//GEN-LAST:event_removeActionPerformed

    private void lnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lnFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_lnFocusGained

    private void lnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lnMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField contact;
    public javax.swing.JTextField email;
    public javax.swing.JTextField fn;
    public javax.swing.JLabel icon1;
    public javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JTextField ln;
    private javax.swing.JButton remove;
    private javax.swing.JButton select;
    public javax.swing.JComboBox<String> status;
    public javax.swing.JComboBox<String> type;
    // End of variables declaration//GEN-END:variables
}
