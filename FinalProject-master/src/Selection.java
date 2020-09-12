import javax.swing.JFrame;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

public class Selection extends javax.swing.JFrame {

    
    int flag = 0;
    String finalAmount;
    private String userId;
    private String agencyId;
    private String customerArea;
    

    static final String username = "root";
    static final String password = "Nopassword@1234";
    static final String url = "jdbc:mysql://localhost:3306/javaprojec";
    Connection conn = null;

    public Selection(String id) {

        userId = id;
        this.setResizable(false);
        initComponents();
        show_all_agency();
    }

    private Selection() {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getAgencyrate(String agencyname) {

        double retreivedrate = 0;
        try {
            
            new JFrame();
            conn = DriverManager.getConnection(url, username, password);
            String namequery = "select * from agency where name='" + agencyname + "';";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(namequery);
            while (rs.next()) {
                
                retreivedrate = Double.parseDouble(rs.getString("rate"));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }

        return retreivedrate;
    }

    
    public ArrayList<Agency> agencyList(String valToSearch, Boolean specific) {
        
        ArrayList<Agency> agenciesList = new ArrayList<Agency>();
        String query;
        try {
            
            new JFrame();
            conn = DriverManager.getConnection(url, username, password);
            if (specific) {
                query = "select * from agency where operationalarea ='" + valToSearch
                        + "' or  name='" + valToSearch + "' or rate <='" + valToSearch + "';";
                searchField.setText("");
            } else {
                query = "SELECT * FROM agency ";
            }
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery(query);
            Agency agency;

            while (rs.next()) {
                agency = new Agency(rs.getString("name"), rs.getString("operationalarea"),
                        rs.getString("rate"), rs.getString("phone"));
                agenciesList.add(agency);

            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return agenciesList;

    }

    public void show_all_agency() {
        ArrayList<Agency> list = agencyList(searchField.getText(), false);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(
                new Object[]{"Agency name", "Operational Area", "Rate per sqft", "Phone no"});
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getname();
            row[1] = list.get(i).getplace();
            row[2] = list.get(i).getrate();
            row[3] = list.get(i).getphone();
            model.addRow(row);
        }
        agencyTable.setModel(model);
    }

    private void show_specific_agency() {
        ArrayList<Agency> newlist = agencyList(searchField.getText(), true);
        DefaultTableModel newmodel = new DefaultTableModel();
        newmodel.setColumnIdentifiers(new Object[]{"name", "Area", "Rate per sqft", "Phone no"});
        Object[] row = new Object[4];
        for (int i = 0; i < newlist.size(); i++) {

            row[0] = newlist.get(i).getname();
            row[1] = newlist.get(i).getplace();
            row[2] = newlist.get(i).getrate();
            row[3] = newlist.get(i).getphone();
            newmodel.addRow(row);
        }
        agencyTable.setModel(newmodel);
    }

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        new Login().setVisible(true);
    }

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {
        show_specific_agency();
    }

    private void areaFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void agencyNameFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void proceedBtnActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame fid = new JFrame();
        if (flag == 0) {
            JOptionPane.showMessageDialog(fid, "Please use Check Button To Verify , Then Proceed",
                    "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String query = "SELECT * FROM agency where name=? ;";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, agencyNameField.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    agencyId = rs.getString("agency_id");
                }
                String newquery=" SELECT * from orders where customer_id='"+ userId + "' and agency_id='"+agencyId+"' ";
                PreparedStatement pt = conn.prepareStatement(newquery);
                ResultSet resultSet=pt.executeQuery();
                if (resultSet.next())
                {
                  JOptionPane.showMessageDialog(fid, "Already Paid For the same agency ",
                    "warning", JOptionPane.WARNING_MESSAGE);  
                }
                else{
                this.setVisible(false);
                new Paymentinterface(finalAmount, agencyId, userId, customerArea).setVisible(true);
            }} catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    private void checkBtnActionPerformed(java.awt.event.ActionEvent evt) {
        
        JFrame f = new JFrame();
        customerArea = areaField.getText();
        if (agencyNameField.getText().length() == 0 || customerArea.length() == 0) {
            JOptionPane.showMessageDialog(f, "Enter Valid Credentials", "warning",
                    JOptionPane.WARNING_MESSAGE);
        } 
        else {
            double customerarea = Double.parseDouble(areaField.getText());
            double newvalue = getAgencyrate(agencyNameField.getText());
            double amount = customerarea * newvalue;
            finalAmount = Double.toString(amount);
            flag = 1;
            result.setText("Rs:" + finalAmount);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        BackBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        agencyTable = new javax.swing.JTable();
        areaField = new javax.swing.JTextField();
        agencyNameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        checkBtn = new javax.swing.JButton();
        tax = new javax.swing.JLabel();
        plus = new javax.swing.JLabel();
        payable = new javax.swing.JLabel();
        fina = new javax.swing.JLabel();
        proceedBtn = new javax.swing.JButton();
        result = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        jLabel1.setText("jLabel1");

        jScrollPane3.setViewportView(jEditorPane1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 72, 57));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User Page");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(302, 302, 302))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(27, 27, 27))
        );

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        searchBtn.setBackground(new java.awt.Color(0, 0, 0));
        searchBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setText("Search");
        searchBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        BackBtn.setBackground(new java.awt.Color(0, 0, 0));
        BackBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        BackBtn.setForeground(new java.awt.Color(255, 255, 255));
        BackBtn.setText("Back");
        BackBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBtnActionPerformed(evt);
            }
        });

        agencyTable.setFocusable(false);
        agencyTable.setGridColor(new java.awt.Color(255, 255, 255));
        agencyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agencyTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                agencyTableMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(agencyTable);

        areaField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        areaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaFieldActionPerformed(evt);
            }
        });

        agencyNameField.setEditable(false);
        agencyNameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        agencyNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agencyNameFieldActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Agency name");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Enter Area (sqft)");

        checkBtn.setBackground(new java.awt.Color(0, 0, 0));
        checkBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        checkBtn.setForeground(new java.awt.Color(255, 255, 255));
        checkBtn.setText("Check");
        checkBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        checkBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBtnActionPerformed(evt);
            }
        });

        tax.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        plus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        payable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        fina.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        proceedBtn.setBackground(new java.awt.Color(0, 0, 0));
        proceedBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        proceedBtn.setForeground(new java.awt.Color(255, 255, 255));
        proceedBtn.setText("Proceed");
        proceedBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        proceedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedBtnActionPerformed(evt);
            }
        });

        result.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        result.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addComponent(BackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(agencyNameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(areaField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(checkBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(proceedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(fina, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(payable, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(plus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(76, 76, 76))))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(agencyNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(areaField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(checkBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(tax)
                        .addGap(0, 0, 0)
                        .addComponent(plus)
                        .addGap(0, 0, 0)
                        .addComponent(payable)
                        .addGap(53, 53, 53)
                        .addComponent(fina)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(proceedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agencyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agencyTableMouseClicked

        int i = agencyTable.getSelectedRow();
        TableModel model = agencyTable.getModel();
        agencyNameField.setText(model.getValueAt(i, 0).toString());
        boolean a = agencyTable.isEditing();
        if (a == false) {
            JOptionPane.showMessageDialog(null, "Enter the Area in sqft");
        }

    }//GEN-LAST:event_agencyTableMouseClicked

    private void agencyTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agencyTableMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_agencyTableMouseEntered

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Selection.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Selection.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Selection.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Selection.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Selection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBtn;
    private javax.swing.JTextField agencyNameField;
    private javax.swing.JTable agencyTable;
    private javax.swing.JTextField areaField;
    private javax.swing.JButton checkBtn;
    private javax.swing.JLabel fina;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel payable;
    private javax.swing.JLabel plus;
    private javax.swing.JButton proceedBtn;
    private javax.swing.JLabel result;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel tax;
    // End of variables declaration//GEN-END:variables

}
