

import java.sql.*;
import BCrypt.BCrypt;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterationForm extends javax.swing.JFrame {

    static final String username = "root";
    static final String password = "Nopassword@1234";
    static final String url = "jdbc:mysql://localhost:3306/javaprojec";
    Connection conn = null;

    public RegisterationForm() {
        this.setResizable(false);
        initComponents();
    }

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {

        this.setVisible(false);
        new Login().setVisible(true);
    }

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JFrame f;
            f = new JFrame();
            conn = DriverManager.getConnection(url, username, password);
            
            String pass = String.valueOf(passwordField.getPassword());
            String cpass = String.valueOf(confirmpassField.getPassword());
            Pattern pat = Pattern.compile("\\d{10}");
            Matcher matcher = pat.matcher(phoneField.getText());
            Pattern pat2 = Pattern.compile("\\d+");
            Matcher matcher2 = pat2.matcher(nameField.getText());
            
            
            if (nameField.getText().length() == 0 || pass.length() == 0 || addressField.getText().length() == 0) {
                
                JOptionPane.showMessageDialog(f, "Enter Valid Credentials", "warning", JOptionPane.WARNING_MESSAGE);
                
            } 
            else {
                
                if (pass.equals(cpass)) {
                    
                    if (matcher2.matches()) {
                        
                        JOptionPane.showMessageDialog(f, "Enter Valid Name", "warning", JOptionPane.WARNING_MESSAGE);
                        
                    } 
                    else {
                        
                        if (matcher.matches()) {
                            
                         try {
                             
                            String query = "INSERT INTO  customer(username,password,phone,address) VALUES(?,?,?,?);";
                            PreparedStatement pt = conn.prepareStatement(query);
                            pt.setString(1, nameField.getText());
                            //password encryption
                            String hashed = BCrypt.hashpw(pass, BCrypt.gensalt(12));
                            pt.setString(2, hashed);
                            pt.setString(3, phoneField.getText());
                            pt.setString(4, addressField.getText());
                            pt.executeUpdate();
                            this.setVisible(false);
                            new Login().setVisible(true);
                            JOptionPane.showMessageDialog(f, "Successfully Registered");
                            
                            } 
                         catch (SQLIntegrityConstraintViolationException e) {
                             
                                JOptionPane.showMessageDialog(f, "Enter a different username", "warning",
                                        JOptionPane.WARNING_MESSAGE);
                                nameField.setText("");
                                
                            }
                        } else {
                            JOptionPane.showMessageDialog(f, "Enter a valid Phone Number", "warning",
                                    JOptionPane.WARNING_MESSAGE);
                        }

                    }
                } else {
                    
                    JOptionPane.showMessageDialog(f, "Password doesn't match", "warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        nameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressField = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        registerBtn = new javax.swing.JButton();
        goBackBtn = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        confirmpassField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        canvas1 = new java.awt.Canvas();

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 72, 57));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });
        jPanel1.add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 105, 203, 40));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 55, 31));
        jPanel1.add(phoneField, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 203, 41));

        addressField.setColumns(20);
        addressField.setRows(5);
        jScrollPane1.setViewportView(addressField);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 203, 95));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, 31));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Phone");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, -1, 31));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registration for Customers");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Address");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, -1, 40));

        registerBtn.setBackground(new java.awt.Color(0, 0, 0));
        registerBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registerBtn.setForeground(new java.awt.Color(255, 255, 255));
        registerBtn.setText("Register");
        registerBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });
        jPanel1.add(registerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, 70, 32));

        goBackBtn.setBackground(new java.awt.Color(0, 0, 0));
        goBackBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        goBackBtn.setForeground(new java.awt.Color(255, 255, 255));
        goBackBtn.setText("Back");
        goBackBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        goBackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackBtnActionPerformed(evt);
            }
        });
        jPanel1.add(goBackBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 535, 98, 32));
        jPanel1.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 200, 30));
        jPanel1.add(confirmpassField, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 200, 30));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Confirm Password");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterationForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addressField;
    private java.awt.Canvas canvas1;
    private javax.swing.JPasswordField confirmpassField;
    private javax.swing.JButton goBackBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JButton registerBtn;
    // End of variables declaration//GEN-END:variables
}
