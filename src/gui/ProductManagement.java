/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Image;
import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import java.sql.PreparedStatement;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Ravindi
 */
public class ProductManagement extends javax.swing.JPanel {

    private static HashMap<String, String> Categorymap = new HashMap<>();
    private static HashMap<String, String> SCategorymap = new HashMap<>();
    private static HashMap<String, String> Potionmap = new HashMap<>();

    private int previouslySelectedRow = -1;

    public ProductManagement() {
        initComponents();
        loadCategories();
        loadSCategories();
        loadPotion();
        loadItems();
    }

    private void loadCategories() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `main_category`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("category"));
                Categorymap.put(resultSet.getString("category"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox5.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadSCategories() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `sub_category`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("sub_category"));
                SCategorymap.put(resultSet.getString("sub_category"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox4.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadPotion() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `potion`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("potion"));
                Potionmap.put(resultSet.getString("potion"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox6.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadItems() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product`"
                    + "INNER JOIN `sub_category` ON `product`.`sub_category_id` = `sub_category`.`id`"
                    + "INNER JOIN `potion` ON `product`.`potion_id` = `potion`.`id`"
                    + "INNER JOIN `p_status` ON `product`.`p_status_id` = `p_status`.`id`"
                    + "LEFT JOIN product_img ON product.id = product_img.product_id");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<Object> vector = new Vector<>();
                vector.add(resultSet.getString("id"));

                String imagePath = resultSet.getString("path");
                if (imagePath != null && !imagePath.isEmpty()) {
                    File imgFile = new File(imagePath);

                    if (imgFile.exists()) {
                        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
                        vector.add(icon);
                    } else {
                        vector.add("No Image");
                    }

                } else {
                    vector.add("No Image");
                }

                vector.add(resultSet.getString("sub_category.sub_category"));
                vector.add(resultSet.getString("item_name"));
                vector.add(resultSet.getString("potion.potion"));
                vector.add(resultSet.getString("price"));
                vector.add(resultSet.getString("cost"));
                vector.add(resultSet.getString("p_status.status"));

                model.addRow(vector);
            }

            jTable1.setRowHeight(75);

            jTable1.getColumnModel().getColumn(1).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
                if (value instanceof ImageIcon) {
                    JLabel label = new JLabel((ImageIcon) value);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return label;
                } else {
                    return new JLabel(value != null ? value.toString() : "No Image");
                }
            });

            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID column
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100); // Image column
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private void search(String str) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
    jTable1.setRowSorter(trs);
    
    // Apply case-insensitive regex filter
    trs.setRowFilter(RowFilter.regexFilter("(?i)" + str));
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jTextField7 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1120, 789));

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(347, 62));

        jLabel24.setFont(new java.awt.Font("Century Schoolbook", 1, 28)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Products  Management");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        jLabel11.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Manage Products");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/wrong.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel12.setFont(new java.awt.Font("Sitka Small", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Products Name");

        jTextField6.setBackground(new java.awt.Color(204, 204, 204));
        jTextField6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Sitka Small", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Categories");

        jComboBox5.setBackground(new java.awt.Color(204, 204, 204));
        jComboBox5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Sitka Small", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Sub Categories");

        jComboBox4.setBackground(new java.awt.Color(204, 204, 204));
        jComboBox4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(204, 204, 204));
        jButton8.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jButton8.setText("+");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Sitka Small", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Portion");

        jLabel23.setFont(new java.awt.Font("Sitka Small", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Price");

        jComboBox6.setBackground(new java.awt.Color(204, 204, 204));
        jComboBox6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jTextField7.setBackground(new java.awt.Color(204, 204, 204));
        jTextField7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Sitka Text", 1, 20)); // NOI18N
        jButton1.setText("Select Image");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(0, 0, 255));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Update Products");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 102, 0));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Add New Products");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Sitka Small", 1, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Cost");

        jTextField8.setBackground(new java.awt.Color(204, 204, 204));
        jTextField8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addGap(105, 105, 105))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Image", "Sub Category", "Item Name", "Potion", "Price", "Cost", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setToolTipText("");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 102, 51));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Change Action");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Category category = new Category(null, true);
        category.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFileChooser browseImageFile = new JFileChooser("C:\\Users\\Public\\Pictures\\Sample Pictures");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        browseImageFile.addChoosableFileFilter(fnef);
        int showOpenDialogue = browseImageFile.showOpenDialog(null);

        if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
            File selectedImageFile = browseImageFile.getSelectedFile();
            String selectedImagePath = selectedImageFile.getAbsolutePath();
            JOptionPane.showMessageDialog(null, selectedImagePath);

//             Display image on JLabel
            ImageIcon ii = new ImageIcon(selectedImagePath);
            Image image = ii.getImage().getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
            jLabel2.setIcon(new ImageIcon(image));
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        try {
            String name = jTextField6.getText();

            String price = jTextField7.getText();
            String cost = jTextField8.getText();
            String categories = String.valueOf(jComboBox5.getSelectedItem());
            String scategories = String.valueOf(jComboBox4.getSelectedItem());
            String potion = String.valueOf(jComboBox6.getSelectedItem());
            String imgpath = jLabel2.getText();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Product Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (categories.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Category", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (scategories.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select SubCategory", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (potion.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Product Potion", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (price.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Product Price", "Warning", JOptionPane.WARNING_MESSAGE);
            }else if (cost.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Product cost", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                double priceVal;
                double costVal;

                try {
                     priceVal = Double.parseDouble(price); // Validate that price is numeric
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Price must be a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    costVal = Double.parseDouble(cost); // Validate that cost is numeric
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Cost must be a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(priceVal < costVal) {
                    JOptionPane.showMessageDialog(this, "Price must be greater than cost ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product` WHERE `potion_id` = '" + potion + "'");

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(this, "This Product alredy added", "Warning", JOptionPane.WARNING_MESSAGE);

                    } else {

                        ResultSet rs = MySQL.executeSearch("SELECT MAX(id) FROM product");
                        int newId = 1; // Default ID starts at 1

                        if (rs.next() && rs.getInt(1) > 0) {
                            newId = rs.getInt(1) + 1; // Increment the last ID
                        }

                        String formattedId = String.format("%04d", newId);

                        // Insert the product details into the product table
                        MySQL.executeIUD("INSERT INTO `product` (`id`, `item_name`, `price`, `cost`, `description`, `sub_category_id`, `potion_id`, `p_status_id`) "
                                + "VALUES ('" + formattedId + "', '" + name + "', '" + price + "', '"+ cost +"','"+ "dis" +"','" + SCategorymap.get(scategories) + "', '" + Potionmap.get(potion) + "', '" + 1 + "')");

                        MySQL.executeIUD("INSERT INTO `product_img` (`path`,`product_id`) VALUES ('" + imgpath + "','" + formattedId + "')");

                        loadItems(); // Reload items
                        reset(); // Reset the form

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();

        // If clicking the same row again, deselect
        if (row == previouslySelectedRow) {
            jTable1.clearSelection();
            previouslySelectedRow = -1;

            // Clear fields
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");

            jComboBox4.setSelectedIndex(0);
            jComboBox4.setEnabled(true);

            jComboBox5.setSelectedIndex(0);
            jComboBox5.setEnabled(true);

            jComboBox6.setSelectedIndex(0);
            jComboBox6.setEnabled(true);

            jButton12.setEnabled(true);

            return; // Skip the rest of the code
        }

        // Else, normal select
        previouslySelectedRow = row;

        String name = String.valueOf(jTable1.getValueAt(row, 3));
        jTextField6.setText(name);

        String pid = String.valueOf(jTable1.getValueAt(row, 0));

        String price = String.valueOf(jTable1.getValueAt(row, 5));
        jTextField7.setText(price);

        String cost = String.valueOf(jTable1.getValueAt(row, 6));
        jTextField8.setText(cost);
        
        String sCategory = String.valueOf(jTable1.getValueAt(row, 2));
        jComboBox4.setSelectedItem(sCategory);
        jComboBox4.setEnabled(false);

        try {
            StringBuilder query = new StringBuilder("SELECT * FROM `main_category` INNER JOIN `sub_category` ON `sub_category`.`main_category_id` = `main_category`.`id` WHERE `sub_category` = '" + sCategory + "' ");
            ResultSet resultSet = MySQL.executeSearch(query.toString());
            if (resultSet.next()) {
                String category = String.valueOf(resultSet.getString("category"));
                jComboBox5.setSelectedItem(category);
                jComboBox5.setEnabled(false);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }



        String Potion = String.valueOf(jTable1.getValueAt(row, 4));
        jComboBox6.setSelectedItem(Potion);
        jComboBox6.setEnabled(false);

        jButton12.setEnabled(false);

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product` WHERE `product`.`id` = '" + pid + "' ");

            if (resultSet.next()) {

                String sc = resultSet.getString("sub_category_id");

                ResultSet rsc = MySQL.executeSearch("SELECT * FROM `sub_category` WHERE `id` = '" + sc + "' ");

                if (rsc.next()) {

                    String c = rsc.getString("main_category_id");

                    ResultSet rc = MySQL.executeSearch("SELECT `id` FROM `main_category` WHERE `id` = '" + c + "' ");

                    if (rc.next()) {
                        jComboBox5.setSelectedIndex(rc.getInt("id"));
                        jComboBox5.setEnabled(false);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        String id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        String name = jTextField6.getText();
        String price = jTextField7.getText();
        String cost = jTextField8.getText();

        if (name.isEmpty() && price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please provide either a Product Name or Price to update.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                // Dynamically build the UPDATE query
                StringBuilder query = new StringBuilder("UPDATE `product` SET ");
                boolean hasName = false, hasPrice = false, hasCost = false;

                if (!name.isEmpty()) {
                    query.append("`item_name` = ?");
                    hasName = true;
                }
                if (!price.isEmpty()) {
                    if (hasName) {
                        query.append(", "); // Add comma if both fields are being updated
                    }
                    query.append("`price` = ?");
                    hasPrice = true;
                }
                
                if (!cost.isEmpty()) {
                    if (hasPrice) {
                        query.append(", "); // Add comma if both fields are being updated
                    }
                    query.append("`cost` = ?");
                    hasCost = true;
                }

                query.append(" WHERE `id` = ?");

                // Prepare the statement
                PreparedStatement ps = MySQL.getConnection().prepareStatement(query.toString());
                int paramIndex = 1;

                if (hasName) {
                    ps.setString(paramIndex++, name); // Set item_name parameter
                }
                double priceVal = 0;
                double costVal = 0;
                if (hasPrice) {
                    try {
                        priceVal = Double.parseDouble(price); // Validate that price is numeric
                        ps.setString(paramIndex++, price); // Set price parameter
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Price must be a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                if (hasCost) {
                    try {
                        costVal = Double.parseDouble(cost); // Validate that price is numeric
                        ps.setString(paramIndex++, cost); // Set price parameter
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Cost must be a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if(priceVal < costVal) {
                    JOptionPane.showMessageDialog(this, "Price must be greater than cost ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                ps.setString(paramIndex, id); // Set id parameter

                // Execute the query
                int updatedRows = ps.executeUpdate();

                if (updatedRows > 0) {
                    loadItems();
                    reset();
                    JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No updates were made. Please check your inputs.", "Warning", JOptionPane.WARNING_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String searchString = jTextField1.getText();
        search(searchString);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            String itemName = jTable1.getValueAt(selectedRow, 3).toString();

            String status = jTable1.getValueAt(selectedRow, 7).toString(); // Column index 8 should be Status

            int newStatusId;
            String newStatus;

            if (status.equalsIgnoreCase("Active")) {
                newStatusId = 2;  // Assuming 2 is the ID for "Inactive"
                newStatus = "Inactive";
            } else {
                newStatusId = 1;  // Assuming 1 is the ID for "Active"
                newStatus = "Active";
            }

            jTable1.setValueAt(newStatus, selectedRow, 7); // Update the status in the table

            // Update the status_id in the database
            try {
                MySQL.executeIUD("UPDATE `product` SET `p_status_id` = '" + newStatusId + "' WHERE `item_name` ='" + itemName + "' ");

                JOptionPane.showMessageDialog(this, "Action has been changed", "Success", JOptionPane.INFORMATION_MESSAGE);
                reset();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error updating status in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please select a product first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField6.setText("");
        jLabel2.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jComboBox5.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jButton12.setEnabled(true);
    }
}
