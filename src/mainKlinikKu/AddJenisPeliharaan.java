/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainKlinikKu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author alvis
 */
public class AddJenisPeliharaan extends javax.swing.JFrame {

    Koneksi Konek = new Koneksi();
    private Connection Con;
    Statement Stm;
    ResultSet Rs,Rs2;
    String Sql;
    
    public AddJenisPeliharaan() {
        initComponents();
        
        LoadDataJenisHewan();
        
        nonAktif();
    }
    
    void kosongObjek(){
        textFieldKode.setText("");
        textFieldJenisPeliharaan.setText("");
        cmbJenisHewan.setSelectedIndex(0);
    }
    
    void nonAktif(){
        textFieldJenisPeliharaan.setEnabled(false);
        cmbJenisHewan.setEnabled(false);
        
        btnAdd.setEnabled(false);
    }
    
    void Aktif(){
        textFieldJenisPeliharaan.setEnabled(true);
        cmbJenisHewan.setEnabled(true);
        
        btnAdd.setEnabled(true);
    }

    private void LoadDataJenisHewan(){
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from load_jenis_hewan";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                cmbJenisHewan.addItem(Rs.getString("jenis_hewan"));
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal : "+e.toString());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        textFieldKode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textFieldJenisPeliharaan = new javax.swing.JTextField();
        cmbJenisHewan = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("Data Jenis Peliharaan");

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("Kode :");

        textFieldKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldKodeActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("Jenis Peliharaan :");

        cmbJenisHewan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Jenis Peliharaan]" }));
        cmbJenisHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisHewanActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Jenis Hewan :");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbJenisHewan, 0, 260, Short.MAX_VALUE)
                            .addComponent(textFieldJenisPeliharaan)
                            .addComponent(textFieldKode)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textFieldKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textFieldJenisPeliharaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbJenisHewan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(btnAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.show();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbJenisHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisHewanActionPerformed

    }//GEN-LAST:event_cmbJenisHewanActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = null;
            Sql = "insert into load_jenis_peliharaan (id, kode, jenis_peliharaan, jenis_hewan) VALUES (NULL, '"
                    +textFieldKode.getText()+"', '"+textFieldJenisPeliharaan.getText()+"', '"
                    +cmbJenisHewan.getSelectedItem()+"')";
            Stm = Con.createStatement();
            int AdaPenambahanRecord = Stm.executeUpdate(Sql);
            if (AdaPenambahanRecord>0) {
                JOptionPane.showMessageDialog(this,"Data Berhasil Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,"Data Gagal Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
            }
            Stm.close();
            kosongObjek();
            nonAktif();
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal " +e.toString());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void textFieldKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldKodeActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from load_jenis_peliharaan where kode='" +textFieldKode.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){ 
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Kode Lain", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                nonAktif();
            } else {
                Aktif();
                
                textFieldJenisPeliharaan.requestFocus();
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_textFieldKodeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddJenisPeliharaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddJenisPeliharaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddJenisPeliharaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddJenisPeliharaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddJenisPeliharaan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JComboBox<String> cmbJenisHewan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField textFieldJenisPeliharaan;
    private javax.swing.JTextField textFieldKode;
    // End of variables declaration//GEN-END:variables
}
