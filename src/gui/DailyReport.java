/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ravindi
 */
public class DailyReport extends javax.swing.JPanel {

    /**
     * Creates new form DailyReport
     */
    public DailyReport() {
        initComponents();
        loadinvoice();
        scheduleDailyRefresh();

    }

    double totalQty = 0;
    double totalCost = 0;
    double totalProfit = 0;
    double totalAmount = 0;
    double totalService = 0;

    private void loadinvoice() {
        int i = 1;

        try {
            String q = "SELECT `invoice`.`id` AS `invoice_id`, "
                    + "`stock`.`product_id` AS `stock_product_id`, "
                    + "`product_stock`.`item_name` AS `stock_product_name`, "
                    + "`product`.`item_name` AS `product_name`, "
                    + "`invoice_item`.`qty`, "
                    + "`product`.`cost`, "
                    + "`product_stock`.`price` AS `stock_price`, "
                    + "`product`.`price` AS `product_price`, "
                    + "`payment_method`.`method`, "
                    + "`invoice`.`date`, "  // Add a comma here to separate columns
                    + "`invoice`.`s_chj` "  // Correctly reference s_chj from the invoice table
                    + "FROM `invoice_item` "
                    + "INNER JOIN `invoice` ON `invoice_item`.`invoice_id` = `invoice`.`id` "
                    + "LEFT JOIN `stock` ON `invoice_item`.`stock_id` = `stock`.`id` "
                    + "LEFT JOIN `product` AS `product_stock` ON `stock`.`product_id` = `product_stock`.`id` "
                    + "LEFT JOIN `product` ON `invoice_item`.`product_id` = `product`.`id` "
                    + "LEFT JOIN `grn_item` ON `grn_item`.`stock_id` = `stock`.`id` "
                    + "LEFT JOIN `payment_method` ON `invoice`.`payment_method_id` = `payment_method`.`id` "
                    + "WHERE (`invoice_item`.`stock_id` IS NOT NULL OR `invoice_item`.`product_id` IS NOT NULL) "
                    + "AND DATE(`invoice`.`date`) = CURDATE();";


            ResultSet resultSet = MySQL.executeSearch(q);

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(i++));

                String productName = resultSet.getString("stock_product_name") != null
                        ? resultSet.getString("stock_product_name")
                        : resultSet.getString("product_name");
                row.add(productName != null ? productName : "N/A");

                String qty = resultSet.getString("invoice_item.qty") != null ? resultSet.getString("invoice_item.qty") : "0";
                row.add(qty);

                String cost = resultSet.getString("product.cost") != null ? resultSet.getString("product.cost") : "0.00";
                String sellingPrice = resultSet.getString("stock_price") != null
                        ? resultSet.getString("stock_price")
                        : (resultSet.getString("product_price") != null ? resultSet.getString("product_price") : "0.00");

                row.add(cost);
                row.add(sellingPrice);

                double profit = Double.parseDouble(sellingPrice) - Double.parseDouble(cost);
                row.add(String.format("%.2f", profit));

                String service = resultSet.getString("invoice.s_chj") != null ? resultSet.getString("invoice.s_chj") : "0.00";
                row.add(service);
                row.add(resultSet.getString("method") != null ? resultSet.getString("method") : "N/A");
                row.add(resultSet.getString("date") != null ? resultSet.getString("date") : "N/A");

                dtm.addRow(row);

                totalQty += Double.parseDouble(qty);
                totalCost += Double.parseDouble(qty) * Double.parseDouble(cost) ;
                totalProfit += profit * Double.parseDouble(qty) + Double.parseDouble(service);
                totalService +=  Double.parseDouble(service);
                 totalAmount += (Double.parseDouble(qty) * Double.parseDouble(sellingPrice)) + Double.parseDouble(service);
            }

           


            jLabel23.setText(String.valueOf(totalQty));
            jFormattedTextField2.setText(String.format("%.2f", totalCost));
            jFormattedTextField3.setText(String.format("%.2f", totalProfit));
            jFormattedTextField1.setText(String.format("%.2f", totalAmount));

            jFormattedTextField1.setEditable(false);
            jFormattedTextField2.setEditable(false);
            jFormattedTextField3.setEditable(false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        totalQty = 0;
        totalCost = 0;
        totalProfit = 0;
        totalAmount = 0;
    }

    private void scheduleDailyRefresh() {
        // Calculate the delay until midnight
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.toLocalDate().atStartOfDay().plusDays(1);
        long initialDelay = Duration.between(now, midnight).toMillis();

        // Schedule the Timer to run daily
        Timer timer = new Timer((int) initialDelay, e -> {
            loadinvoice(); // Refresh the daily report
            System.out.println("Daily report refreshed!");
        });

        timer.setRepeats(false); // Run only once
        timer.start();

        // Schedule to repeat every 24 hours
        Timer dailyTimer = new Timer(24 * 60 * 60 * 1000, e -> {
            loadinvoice();
            System.out.println("Daily report refreshed!");
        });

        dailyTimer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel27 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel29 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));

        jLabel24.setFont(new java.awt.Font("Century Schoolbook", 1, 28)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Daily Report");

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

        jTable1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice ID", "Item", "Quantity", "cost", "Selling Price", "Profit", "Service Charge", "Payment Method", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(153, 153, 255));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Summary");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI Historic", 1, 17)); // NOI18N
        jLabel25.setText("Total Quantity :");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 0, 102));
        jLabel23.setText("None");

        jLabel26.setFont(new java.awt.Font("Segoe UI Historic", 1, 17)); // NOI18N
        jLabel26.setText("Total Amount :");

        jFormattedTextField1.setForeground(new java.awt.Color(0, 0, 255));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField1.setText("0.00");
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Segoe UI Historic", 1, 17)); // NOI18N
        jLabel27.setText("Cost :");

        jFormattedTextField2.setForeground(new java.awt.Color(204, 0, 0));
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField2.setText("0.00");
        jFormattedTextField2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Segoe UI Historic", 1, 17)); // NOI18N
        jLabel29.setText("Total Profit :");

        jFormattedTextField3.setForeground(new java.awt.Color(0, 204, 51));
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField3.setText("0.00");
        jFormattedTextField3.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(jLabel25)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel26)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(42, 42, 42)
                                    .addComponent(jLabel27)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(41, 41, 41)
                                    .addComponent(jLabel29)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel26)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27)
                        .addComponent(jLabel29)
                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String path = "src//report//report.jasper";
            HashMap<String, Object> parms = new HashMap<>();

            parms.put("Parameter1", datetime);
            parms.put("Parameter2", jFormattedTextField1.getText());
            parms.put("Parameter3", jFormattedTextField2.getText());
            parms.put("Parameter4", jFormattedTextField3.getText());
            parms.put("Parameter5", jLabel23.getText());
         

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parms, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error showing report: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
