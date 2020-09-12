

import javax.swing.JFrame;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.PreparedStatement;

public class Paymentinterface extends javax.swing.JFrame {
    
    String yearExpiry;
    String monthExpiry;
    String finalAmount;
    private String userId;
    private String orderId;
    private String agencyId;
    private String customerArea;
   
    static final String username = "root";
    static final String password = "Nopassword@1234";
    static final String url = "jdbc:mysql://localhost:3306/javaprojec";
    Connection conn = null;

    

    public Paymentinterface(String rate, String agencyId, String id,String customerArea) {
        this.setResizable(false);
        initComponents();
        userId = id;
        finalAmount = rate;
        this.agencyId = agencyId;
        this.customerArea=customerArea;
        ratepay.setText("Rs:" + rate);
    }

    private Paymentinterface() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void cardHolderFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cardNumFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        new Selection(userId).setVisible(true);
    }

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {
        
        JFrame f;
        f = new JFrame();
        Pattern pat = Pattern.compile("\\d{10,20}");
        Matcher matcher = pat.matcher(cardNumField.getText());
        Pattern pat2 = Pattern.compile("\\d{3}");
        Matcher matcher2 = pat2.matcher(cvvField.getText());
        
        if (cardNumField.getText().length() == 0 || cardNumField.getText().length() > 17 || cardHolderField.getText().length() == 0 || cvvField.getText().length() == 0 || cvvField.getText().length() > 5
                || monthExpiry.length() == 0 || yearExpiry.length() == 0) {
            
            JOptionPane.showMessageDialog(f, "Enter Valid Credentials", "warning", JOptionPane.WARNING_MESSAGE);
        } 
        else {

            if (matcher.matches()) {
                
                if (matcher2.matches()) {
                    
                    try{
                        
                    conn = DriverManager.getConnection(url, username, password);
                    
                    String query = "INSERT INTO  orders(customer_id,agency_id,amount,customer_area) VALUES(?,?,?,?);";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1,userId);
                    pst.setString(2,agencyId);
                    pst.setString(3,finalAmount);
                    pst.setString(4,customerArea);
                    pst.executeUpdate();
                    String getorderId="SELECT * FROM orders WHERE customer_id=? and agency_id=?;";
                    PreparedStatement pt = conn.prepareStatement(getorderId);
                    pt.setString(1,userId);
                    pt.setString(2,agencyId);
                    ResultSet rs = pt.executeQuery();
                    if (rs.next()) {
                        
                        orderId=rs.getString("order_id");
                    }
                    JOptionPane.showMessageDialog(f, "Successful");
                    this.setVisible(false);
                    new InvoiceForm(orderId).setVisible(true);
                    }
                    catch(SQLException e)
                    {
                          System.err.println(e);
                    }
                } 
                else {
                    
                    JOptionPane.showMessageDialog(f, "Enter Valid CVV", "warning", JOptionPane.WARNING_MESSAGE);
                }
            }
            else {
                
                JOptionPane.showMessageDialog(f, "Enter Valid Card Number", "warning", JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    private void expMonthActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cvvFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void expMonthFocusGained(java.awt.event.FocusEvent evt) {

        expMonth.addItem("01");
        expMonth.addItem("02");
        expMonth.addItem("03");
        expMonth.addItem("04");
        expMonth.addItem("05");
        expMonth.addItem("05");
        expMonth.addItem("06");
        expMonth.addItem("07");
        expMonth.addItem("08");
        expMonth.addItem("09");
        expMonth.addItem("10");
        expMonth.addItem("11");
        expMonth.addItem("12");
        new JFrame();
        monthExpiry = expMonth.getSelectedItem().toString();

    }

    private void expYearFocusGained(java.awt.event.FocusEvent evt) {
        expYear.addItem("21");
        expYear.addItem("22");
        expYear.addItem("23");
        expYear.addItem("24");
        expYear.addItem("25");
        expYear.addItem("26");
        expYear.addItem("27");
        expYear.addItem("28");
        expYear.addItem("29");
        expYear.addItem("30");
        expYear.addItem("31");
        expYear.addItem("32");
        expYear.addItem("33");
        new JFrame();
        yearExpiry = expYear.getSelectedItem().toString();

    }

    
    @SuppressWarnings("unchecked")
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cardNumField = new javax.swing.JTextField();
        cvvField = new javax.swing.JTextField();
        cardHolderField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        expMonth = new javax.swing.JComboBox<>();
        expYear = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        payBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        goBackBtn = new javax.swing.JButton();
        ratepay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 72, 57));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CARD NUMBER");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" CVV");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CARD HOLDER");

        cardNumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardNumFieldActionPerformed(evt);
            }
        });

        cvvField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cvvFieldActionPerformed(evt);
            }
        });

        cardHolderField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardHolderFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("EXPIRY DATE");

        expMonth.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        expMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM" }));
        expMonth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                expMonthFocusGained(evt);
            }
        });
        expMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expMonthActionPerformed(evt);
            }
        });

        expYear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        expYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YY" }));
        expYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                expYearFocusGained(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("AMOUNT");

        payBtn.setBackground(new java.awt.Color(0, 0, 0));
        payBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        payBtn.setForeground(new java.awt.Color(255, 255, 255));
        payBtn.setText("Pay");
        payBtn.setToolTipText("");
        payBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        payBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payBtnActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Payment");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel6,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout
                        .createSequentialGroup().addGap(24, 24, 24).addComponent(jLabel6,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE)));

        goBackBtn.setBackground(new java.awt.Color(0, 0, 0));
        goBackBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        goBackBtn.setForeground(new java.awt.Color(255, 255, 255));
        goBackBtn.setText("Back");
        goBackBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        goBackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackBtnActionPerformed(evt);
            }
        });

        ratepay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ratepay.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup().addGap(261, 261, 261)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cvvField, javax.swing.GroupLayout.PREFERRED_SIZE, 122,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cardHolderField, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        261, Short.MAX_VALUE)
                                                .addComponent(cardNumField))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(expMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 67,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18).addComponent(expYear,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createSequentialGroup().addGap(128, 128, 128).addGroup(jPanel1Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)))))
                        .addContainerGap(90, Short.MAX_VALUE))
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                        jPanel1Layout.createSequentialGroup().addContainerGap()
                                .addComponent(goBackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 88,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(payBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(ratepay,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cardNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cvvField, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cardHolderField, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(expMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(expYear, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(ratepay, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(goBackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(payBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel5,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(35, Short.MAX_VALUE)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addGap(0, 0, 0)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addGap(0, 0, 0)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, Short.MAX_VALUE)
                        .addGap(0, 0, 0)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Paymentinterface.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Paymentinterface.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Paymentinterface.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Paymentinterface.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Paymentinterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cardHolderField;
    private javax.swing.JTextField cardNumField;
    private javax.swing.JTextField cvvField;
    private javax.swing.JComboBox<String> expMonth;
    private javax.swing.JComboBox<String> expYear;
    private javax.swing.JButton goBackBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton payBtn;
    private javax.swing.JLabel ratepay;
    // End of variables declaration//GEN-END:variables
}
