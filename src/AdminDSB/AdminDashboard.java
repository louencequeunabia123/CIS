package AdminDSB;

import Config.*;
import LoginDSB.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

public class AdminDashboard extends javax.swing.JFrame {

    public File selectedFile;
    public String path2 = null;
    public String destination = "";
    public String oldPath;
    public String path;

    public AdminDashboard() {
        initComponents();
        displayUsers();
        displayProducts();
        displayLogs();
    }

    public void deleteProduct() throws NoSuchAlgorithmException, SQLException {
        int confirmation = JOptionPane.showConfirmDialog(null, "ARE YOU SURE YOU WANT TO DELETE THIS PRODUCT?", "CONFIRMATION", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            DBConnector cn = new DBConnector();
            String query = "DELETE FROM equipments WHERE e_id = '" + id.getText() + "'";
            try (PreparedStatement pstmt = cn.getConnection().prepareStatement(query)) {
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "PRODUCT DELETED SUCCESSFULLY!");
                displayUsers();
                displayProducts();
                displayLogs();
                jTabbedPane1.setSelectedIndex(1);
            }
        }
    }

    public void updateProduct() throws NoSuchAlgorithmException {
        try {
            String xpname1 = pn.getText().trim();
            String xpprice1 = pp.getText().trim();
            String xpstocks1 = ps.getText().trim();
            String xpstatus1 = pstats.getSelectedItem() == null ? "" : pstats.getSelectedItem().toString().trim();

            if (xpname1.isEmpty() || xpprice1.isEmpty() || xpstocks1.isEmpty() || xpstatus1.isEmpty()) {
                JOptionPane.showMessageDialog(null, "PLEASE FILL ALL THE FIELDS!");
            } else if (selectedFile == null && icon2.getIcon() == null) {
                JOptionPane.showMessageDialog(null, "PLEASE INSERT AN IMAGE FIRST!");
            } else {
                DBConnector cn = new DBConnector();
                cn.updateData("update equipments set name = '" + xpname1 + "', brand = '" + xpprice1 + "',quantity='" + xpstocks1 + "', "
                        + "status='" + xpstatus1 + "', image= '" + destination + "' where e_id = '" + id.getText() + "'");

                if (selectedFile != null) {
                    Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                JOptionPane.showMessageDialog(this, "PRODUCT UPDATED SUCCESSFULLY!");

                LocalDateTime currentDatetime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDate = currentDatetime.format(formatter);

                cn.insertData("insert into logs (u_id,actions,date) values ('" + Session.getInstance().getId() + "','Just Updated A Product','" + formattedDate + "')");
                displayUsers();
                displayProducts();
                displayLogs();
                jTabbedPane1.setSelectedIndex(0);

                pn.setText("");
                pp.setText("");
                ps.setText("");
                icon2.setIcon(null);
            }
        } catch (SQLException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Error!");
            System.out.println(ex.getMessage());
        }
    }

    private void displayUsers() {
        try {
            Session sess = Session.getInstance();
            ResultSet rs = new DBConnector().getData("select id,lastname,firstname,email,contact,type,status from users where status in ('active', 'inactive') and id != '" + sess.getId() + "'");
            usersTB.setModel(DbUtils.resultSetToTableModel(rs));
            aname.setText(Session.getInstance().getLastname());
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching data: " + e.getMessage());
        }
    }

    private void displayLogs() {
        try {
            ResultSet rs = new DBConnector().getData("SELECT logs.u_id , actions, date FROM logs INNER JOIN users ON logs.u_id = users.id");
            logs.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching data: " + e.getMessage());
        }
    }

    private void displayProducts() {
        try {
            ResultSet rs = new DBConnector().getData("select e_id,name,brand,quantity,status from equipments");
            products.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching data: " + e.getMessage());
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

        Path filePath = Paths.get("src/ProductsImage", fileName);
        boolean fileExists = Files.exists(filePath);

        if (fileExists) {
            return 1;
        } else {
            return 0;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        usersTB = new javax.swing.JTable();
        aname = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        products = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        remove = new javax.swing.JButton();
        id = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        pp = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        ps = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        pstats = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        icon2 = new javax.swing.JLabel();
        select = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        pn = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        logs = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 249, 239));
        setMinimumSize(new java.awt.Dimension(1098, 699));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1098, 699));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1120, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usersTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(usersTB);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 1040, 290));

        aname.setFont(new java.awt.Font("Yu Gothic", 0, 20)); // NOI18N
        aname.setText("ADMINS NAME");
        jPanel2.add(aname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 210, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton1.setText("LOGOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, 110, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 0, 15)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ssssssssss.png"))); // NOI18N
        jLabel7.setText("ADMINS DASHBOARD");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 440, 340));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton5.setText("MY ACCOUNT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 110, -1));

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton9.setText("EDIT");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 620, 120, -1));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton7.setText("PENDING");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 620, 120, 30));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton2.setText("CREATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 120, -1));

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton13.setText("VIEW EQUIPMENT INVENTORY");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 620, 340, -1));

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton11.setText("PRINT TABLE");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 620, 120, -1));

        jButton15.setBackground(new java.awt.Color(255, 255, 255));
        jButton15.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton15.setText("VIEW LOGS");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 620, 120, -1));

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(products);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 900, 410));

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton8.setText("BACK");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 250, 30));

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton6.setText("PRINT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, 320, -1));

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton10.setText("EDIT");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 490, 240, -1));

        jTabbedPane1.addTab("tab1", jPanel3);

        jPanel4.setBackground(new java.awt.Color(234, 228, 221));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Product Name");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 230, -1));

        remove.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        remove.setText("REMOVE");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel6.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 290, 170, -1));

        id.setEditable(false);
        id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        jPanel6.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 280, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Brand Name");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 230, -1));

        pp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(pp, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 230, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Quantity");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, 230, -1));

        ps.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(ps, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, 230, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Status");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, 230, -1));

        pstats.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AVAILABLE", "NOT AVAILABLE" }));
        jPanel6.add(pstats, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 450, 230, 30));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 620, 200));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 640, 220));

        select.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        select.setText("SELECT");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel6.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 170, -1));

        jButton27.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton27.setText("BACK");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 530, 110, -1));

        jButton28.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton28.setText("UPDATE");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 530, 110, -1));

        pn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnActionPerformed(evt);
            }
        });
        jPanel6.add(pn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 230, 30));

        jButton29.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton29.setText("DELETE");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 110, -1));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 680));

        jTabbedPane1.addTab("tab1", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        logs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(logs);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 810, 410));

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton12.setText("BACK");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 250, 30));

        jButton14.setBackground(new java.awt.Color(255, 255, 255));
        jButton14.setFont(new java.awt.Font("Yu Gothic", 0, 11)); // NOI18N
        jButton14.setText("PRINT");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 510, 530, -1));

        jTabbedPane1.addTab("tab1", jPanel5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Session sess = Session.getInstance();
        aname.setText("" + sess.getUsername());
        displayUsers();
        displayProducts();
    }//GEN-LAST:event_formWindowActivated

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            Session sess = Session.getInstance();
            int rowIndex = products.getSelectedRow();
            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(null, "PLEASE SELECT AN INDEX!");
            } else {

                TableModel tbl = products.getModel();

                String query = "SELECT * FROM equipments WHERE e_id = '" + tbl.getValueAt(rowIndex, 0) + "'";
                try {

                    ResultSet rs = new DBConnector().getData(query);

                    if (rs.next()) {
                        id.setText(rs.getString("e_id"));
                        pn.setText(rs.getString("name"));
                        pp.setText(rs.getString("brand"));
                        ps.setText(rs.getString("quantity"));
                        pstats.setSelectedItem(rs.getString("status"));
                        String imagePath = rs.getString("image");

                        SwingUtilities.invokeLater(() -> {
                            jTabbedPane1.setSelectedIndex(2);
                        });

                        if (imagePath != null && !imagePath.isEmpty()) {
                            icon2.setIcon(ResizeImage(imagePath, null, icon2));
                            oldPath = imagePath;
                            path = imagePath;
                            destination = imagePath;
                            select.setEnabled(false);
                            remove.setEnabled(true);
                        } else {
                            select.setEnabled(true);
                            remove.setEnabled(false);
                        }
                    } else {
                        System.out.println("No data found for id: " + sess.getId());
                    }
                } catch (SQLException er) {
                    System.out.println("ERROR: " + er.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected ERROR: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        MessageFormat header = new MessageFormat("Total Products Registered Reports");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            products.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException er) {
            System.out.println("" + er.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMouseClicked
    }//GEN-LAST:event_productsMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        MessageFormat header = new MessageFormat("Total Accounts Registered Reports");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            usersTB.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException er) {
            System.out.println("" + er.getMessage());
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new createAccounts().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new pendingAccounts().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            Session sess = Session.getInstance();
            if (sess == null || sess.getId() == null) {
                JOptionPane.showMessageDialog(null, "Please Login First!");
                LoginDashboard ld = new LoginDashboard();
                ld.setVisible(true);
                dispose();
            }

            int rowIndex = usersTB.getSelectedRow();

            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(null, "PLEASE SELECT AN INDEX!");;
            } else {
                TableModel tbl = usersTB.getModel();
                String query = "SELECT * FROM users WHERE id = '" + tbl.getValueAt(rowIndex, 0) + "'";
                DBConnector db = new DBConnector();
                ResultSet rs = db.getData(query);
                if (rs.next()) {
                    editAccount ea = new editAccount();
                    ea.id.setText(rs.getString("id"));
                    ea.email.setText(rs.getString("email"));
                    ea.ln.setText(rs.getString("lastname"));
                    ea.fn.setText(rs.getString("firstname"));
                    ea.contact.setText(rs.getString("contact"));
                    ea.status.setSelectedItem(rs.getString("status"));
                    ea.type.setSelectedItem(rs.getString("type"));
                    String imagePath = rs.getString("Image");

                    SwingUtilities.invokeLater(() -> {
                        ea.setVisible(true);
                        dispose();
                    });

                    if (imagePath != null && !imagePath.isEmpty()) {
                        ea.icon1.setIcon(ResizeImage(imagePath, null, ea.icon1));
                        ea.oldPath = imagePath;
                        ea.path = imagePath;
                        ea.destination = imagePath;
                        select.setEnabled(false);
                        remove.setEnabled(true);
                    } else {
                        select.setEnabled(true);
                        remove.setEnabled(false);
                    }
                } else {
                    System.out.println("No data found for id: " + sess.getId());
                }
            }

        } catch (SQLException er) {
            System.out.println("ERROR: " + er.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected ERROR: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            Session sess = Session.getInstance();
            if (sess == null || sess.getId() == null) {
                JOptionPane.showMessageDialog(null, "Please Login First!");
                LoginDashboard ld = new LoginDashboard();
                ld.setVisible(true);
                dispose();
            }

            String query = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement pstmt = new DBConnector().getConnection().prepareStatement(query)) {
                pstmt.setString(1, sess.getId());
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        myAccount ea = new myAccount();
                        ea.id.setText(rs.getString("id"));
                        ea.email.setText(rs.getString("email"));
                        ea.ln.setText(rs.getString("lastname"));
                        ea.ln.setText(rs.getString("firstname"));
                        ea.contact.setText(rs.getString("contact"));
                        ea.status.setSelectedItem(rs.getString("status"));
                        ea.type.setSelectedItem(rs.getString("type"));
                        String imagePath = rs.getString("Image");

                        SwingUtilities.invokeLater(() -> {
                            ea.setVisible(true);
                            dispose();
                        });

                        if (imagePath != null && !imagePath.isEmpty()) {
                            ea.imagee.setIcon(ResizeImage(imagePath, null, ea.imagee));
                            ea.oldPath = imagePath;
                            ea.path = imagePath;
                            ea.destination = imagePath;
                            select.setEnabled(false);
                            remove.setEnabled(true);
                        } else {
                            select.setEnabled(true);
                            remove.setEnabled(false);
                        }
                    } else {
                        System.out.println("No data found for id: " + sess.getId());
                    }
                }
            }
        } catch (SQLException er) {
            System.out.println("ERROR: " + er.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected ERROR: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoginDashboard ld = new LoginDashboard();
        ld.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        destination = "";
        icon2.setIcon(null);
        path = "";
        select.setEnabled(true);
        remove.setEnabled(false);
    }//GEN-LAST:event_removeActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/ProductsImage/" + selectedFile.getName();
                path = selectedFile.getAbsolutePath();

                if (FileExistenceChecker(path) == 1) {
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path = "";
                } else {
                    icon2.setIcon(ResizeImage(path, null, icon2));
                    remove.setEnabled(true);
                    select.setEnabled(false);
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    }//GEN-LAST:event_selectActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        displayProducts();
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        try {
            updateProduct();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void pnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        try {
            deleteProduct();
        } catch (NoSuchAlgorithmException | SQLException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void logsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_logsMouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        MessageFormat header = new MessageFormat("Logs Reports");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            products.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException er) {
            System.out.println("" + er.getMessage());
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton15ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel aname;
    private javax.swing.JLabel icon2;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable logs;
    private javax.swing.JTextField pn;
    private javax.swing.JTextField pp;
    private javax.swing.JTable products;
    private javax.swing.JTextField ps;
    private javax.swing.JComboBox<String> pstats;
    private javax.swing.JButton remove;
    private javax.swing.JButton select;
    private javax.swing.JTable usersTB;
    // End of variables declaration//GEN-END:variables
}
