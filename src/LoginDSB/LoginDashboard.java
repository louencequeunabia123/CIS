package LoginDSB;

import AdminDSB.*;
import Config.*;
import RegDSB.*;
import UserDSB.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.swing.*;

public class LoginDashboard extends javax.swing.JFrame {

    public LoginDashboard() {
        initComponents();
        jButton3.setFocusable(false);
        jButton2.setFocusable(false);
        jButton4.setFocusable(false);
        showPass.setFocusable(false);
    }

    private static String xstatus, xtype;

    private boolean loginDB(String lastname, String pass) throws SQLException {
        ResultSet rs = new DBConnector().getData("select * from users where lastname = '" + lastname + "' and password = '" + pass + "'");
        if (rs.next()) {
            xstatus = rs.getString("status");
            xtype = rs.getString("type");
            Session cons = Session.getInstance();
            cons.setId(rs.getString("id"));
            cons.setEmail(rs.getString("email"));
            cons.setLastname(rs.getString("lastname"));
            cons.setFirstname(rs.getString("firstname"));
            cons.setPassword(rs.getString("password"));
            cons.setContact(rs.getString("contact"));
            cons.setType(rs.getString("type"));
            cons.setStatus(rs.getString("status"));
            return true;
        } else {
            return false;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        showPass = new javax.swing.JCheckBox();
        password = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(635, 423));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Don't Have An Account?");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 490, 270, -1));

        username.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        username.setForeground(new java.awt.Color(102, 102, 102));
        username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        username.setText("LASTNAME");
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFocusGained(evt);
            }
        });
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameMouseClicked(evt);
            }
        });
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 265, 29));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton2.setText("EXIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 530, 90, -1));

        showPass.setBackground(new java.awt.Color(255, 255, 255));
        showPass.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        showPass.setText("SHOW PASSWORD");
        showPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPassActionPerformed(evt);
            }
        });
        jPanel1.add(showPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, -1, -1));

        password.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password.setText("PASSWORD\n");
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
        });
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 265, 30));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton3.setText("LOGIN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, 85, -1));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton4.setText("SIGN UP");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, 90, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("CIS 2024");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel3.setText("COMPUTER EQUIPMENT");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        jLabel4.setText("INVENTORY SYSTEM");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("LOGIN");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 270, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/samot_2.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 350, 290));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("LOGIN");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 270, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String hashedPass = passwordHashing.hashPassword(password.getText());
            if (loginDB(username.getText(), hashedPass)) {

                if (xstatus.equalsIgnoreCase("pending")) {
                    errorMessage("WAIT FOR ADMIN APPROVAL!");
                } else if (xstatus.equalsIgnoreCase("declined")) {
                    errorMessage("YOUR ACCOUNT HAS BEEN DECLINED!");
                } else if (xstatus.equalsIgnoreCase("inactive")) {
                    errorMessage("YOUR ACCOUNT IS IN-ACTIVE!");
                } else if (!xstatus.equalsIgnoreCase("active")) {
                    errorMessage("INVALID TYPE!");
                } else {
                    if (xtype.equalsIgnoreCase("user")) {
                        successMessage("LOGIN SUCCESSFULLY!");
                        new UserDashboard().setVisible(true);
                        dispose();
                    } else if (xtype.equalsIgnoreCase("admin")) {
                        successMessage("LOGIN SUCCESSFULLY!");
                        new AdminDashboard().setVisible(true);
                        dispose();
                    } else {
                        errorMessage("ACCOUNT TYPE INVALID!");
                    }
                }
            } else {
                errorMessage("ACCOUNT NOT FOUND!");
            }
        } catch (SQLException | NoSuchAlgorithmException er) {
            System.out.println("ERROR: " + er.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        password.setText("");
    }//GEN-LAST:event_passwordFocusGained

    private void showPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassActionPerformed
        if (showPass.isSelected()) {
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
    }//GEN-LAST:event_showPassActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseClicked
        username.setText("");
    }//GEN-LAST:event_usernameMouseClicked

    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained

    }//GEN-LAST:event_usernameFocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new RegisterDashboard().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password;
    private javax.swing.JCheckBox showPass;
    public javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
