
import java.sql.*;
import BCrypt.BCrypt;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public Login() {
        this.setResizable(false);
        initComponents();
    }

    static final String username = "root";
    static final String password = "Nopassword@1234";
    static final String url = "jdbc:mysql://localhost:3306/javaprojec";
    Connection conn = null;

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JFrame f;
            f = new JFrame();

            String name = userNameField.getText();
            String pass = String.valueOf(passwordField.getPassword());

            if (name.length() == 0) {
                JOptionPane.showMessageDialog(f, "Name Cannot be Empty", "warning", JOptionPane.WARNING_MESSAGE);
            } else {
                if (pass.length() == 0) {
                    JOptionPane.showMessageDialog(f, "Password Cannot be Empty", "warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    conn = DriverManager.getConnection(url, username, password);
                    // gets the details of user with the entered username
                    String query = "SELECT * FROM customer where username =? ;";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, name);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {

                        if (BCrypt.checkpw(pass, rs.getString("password"))) {
                            this.setVisible(false);
                            new Selection(rs.getString("customer_id")).setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password");
                            userNameField.setText("");
                            passwordField.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Invalid username and password", "warning",
                                JOptionPane.WARNING_MESSAGE);
                        userNameField.setText("");
                        passwordField.setText("");
                    }

                }

            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private void userNameFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void customerRegisterBtnActionPerformed(java.awt.event.ActionEvent evt) {
        new RegisterationForm().setVisible(true);
        this.setVisible(false);
    }

    private void agencyRegisterBtnActionPerformed(java.awt.event.ActionEvent evt) {
        new RegistrationAgency().setVisible(true);
        this.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        userNameField = new javax.swing.JTextField();
        customerRegisterBtn = new javax.swing.JButton();
        agencyRegisterBtn = new javax.swing.JButton();
        loginBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 72, 57));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setForeground(new java.awt.Color(153, 102, 0));
        jPanel3.setLayout(null);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(240, 200, 80, 16);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Password");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(240, 260, 70, 16);

        userNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameFieldActionPerformed(evt);
            }
        });
        jPanel3.add(userNameField);
        userNameField.setBounds(320, 190, 150, 30);

        customerRegisterBtn.setBackground(new java.awt.Color(0, 0, 0));
        customerRegisterBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        customerRegisterBtn.setForeground(new java.awt.Color(255, 255, 255));
        customerRegisterBtn.setText("Customer Register");
        customerRegisterBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        customerRegisterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerRegisterBtnActionPerformed(evt);
            }
        });
        jPanel3.add(customerRegisterBtn);
        customerRegisterBtn.setBounds(70, 410, 140, 30);

        agencyRegisterBtn.setBackground(new java.awt.Color(0, 0, 0));
        agencyRegisterBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        agencyRegisterBtn.setForeground(new java.awt.Color(255, 255, 255));
        agencyRegisterBtn.setText("Agency Register");
        agencyRegisterBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agencyRegisterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agencyRegisterBtnActionPerformed(evt);
            }
        });
        jPanel3.add(agencyRegisterBtn);
        agencyRegisterBtn.setBounds(490, 410, 160, 30);

        loginBtn.setBackground(new java.awt.Color(0, 0, 0));
        loginBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(255, 255, 255));
        loginBtn.setText("LOGIN");
        loginBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        jPanel3.add(loginBtn);
        loginBtn.setBounds(360, 300, 90, 30);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setBackground(new java.awt.Color(102, 102, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Covid Assistance Application");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel1)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel3.add(jPanel1);
        jPanel1.setBounds(0, 0, 690, 110);
        jPanel3.add(passwordField);
        passwordField.setBounds(320, 250, 150, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agencyRegisterBtn;
    private javax.swing.JButton customerRegisterBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField userNameField;
    // End of variables declaration//GEN-END:variables
}
