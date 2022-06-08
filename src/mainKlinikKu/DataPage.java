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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author alvis
 */
public class DataPage extends javax.swing.JFrame {

    Koneksi Konek = new Koneksi();
    private Connection Con;
    Statement Stm;
    ResultSet Rs,Rs2;
    String Sql;
    DefaultTableModel Dtm;
    
    public DataPage() {
        initComponents();
        LoadDataJenisPeliharaan();
        LoadDataTreatment();
        LoadDataJenisLayanan();
        
        tampilDataTable();
                
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnSave.setEnabled(false);
        btnClear.setEnabled(false);
        
        textFieldJenisHewan.setEnabled(false);
    }
    
    private void LoadDataJenisPeliharaan(){
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from load_jenis_peliharaan";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                cmbJenisPeliharaan.addItem(Rs.getString("jenis_peliharaan"));
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal : "+e.toString());
        }
    }
    
    private void LoadDataJenisLayanan(){
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from load_jenis_layanan";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                cmbJenisLayanan.addItem(Rs.getString("layanan"));
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal : "+e.toString());
        }
    }
    
    private void LoadDataTreatment(){
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from load_treatment";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                cmbJenisTreatment.addItem(Rs.getString("jenis_treatment"));
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal : "+e.toString());
        }
    }
    
    void nonAktifField(){
        jdateTanggalPeriksa.setEnabled(false);
        textFieldNama.setEnabled(false);
        textFieldAlamat.setEnabled(false);
        textFieldNoHp.setEnabled(false);
        cmbJenisPeliharaan.setEnabled(false);
        textFieldNamaHewan.setEnabled(false);
        textFieldNamaDokter.setEnabled(false);
        cmbJenisTreatment.setEnabled(false);
    }
    
    void AktifField(){
        jdateTanggalPeriksa.setEnabled(true);
        textFieldNama.setEnabled(true);
        textFieldAlamat.setEnabled(true);
        textFieldNoHp.setEnabled(true);
        cmbJenisPeliharaan.setEnabled(true);
        textFieldNamaHewan.setEnabled(true);
        textFieldNamaDokter.setEnabled(true);
        cmbJenisTreatment.setEnabled(true);
    }
    
    void kosongObjek(){
        textFieldNoUrut.setText("");
        textFieldNama.setText("");
        textFieldAlamat.setText("");
        textFieldNoHp.setText("");
        textFieldJenisHewan.setText("");
        textFieldNamaHewan.setText("");
        textFieldNamaDokter.setText("");
        
        jdateTanggalPeriksa.setDate(null);
        
        cmbJenisPeliharaan.setSelectedIndex(0);
        cmbJenisTreatment.setSelectedIndex(0);
    }
    
    private void AturJTable(JTable Lihat, int Lebar[]){
        try {
            Lihat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            int banyak = Lihat.getColumnCount();
            for (int i = 0; i < banyak; i++) {
                TableColumn Kolom = Lihat.getColumnModel().getColumn(i);
                Kolom.setPreferredWidth(Lebar[i]);
                Lihat.setRowHeight(30);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "salah "+e);
        }
    }
    
    private void TampilModelJTabel(){
        try {
            String[]kolom = {
                "No. Urut","Tanggal Periksa","Nama","Alamat","No. Hp",
                "Jenis Peliharaan","Jenis Hewan","Nama Hewan","Nama Dokter","Jenis Layanan","Jenis Treatment"
            };
            Dtm = new DefaultTableModel(null, kolom){
                @Override
                public boolean  isCellEditable(int rowIndex, int columnIndex){
                    return false;
                };
            };
            jTable1.setModel(Dtm);
            AturJTable(jTable1, new int[]{
                200,200,150,300,100,
                150,150,150,200,150,150
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "salah "+e);
        }
    }
    
    void tampilDataTable(){
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            TampilModelJTabel();
            try {
                Sql = "SELECT table_data_periksa.no_urut AS NoUrut, "
                        + "table_data_periksa.tanggal_periksa AS TanggalPeriksa, "
                        + "table_data_periksa.nama AS Nama, "
                        + "table_data_periksa.alamat AS Alamat, "
                        + "table_data_periksa.no_hp AS NoHp, "
                        + "table_data_periksa.jenis_peliharaan AS JenisPeliharaan, "
                        + "table_data_periksa.jenis_hewan AS JenisHewan, "
                        + "table_data_periksa.nama_hewan AS NamaHewan, "
                        + "table_data_periksa.nama_dokter AS NamaDokter, "
                        + "table_data_periksa.layanan AS Layanan, "
                        + "table_data_periksa.treatment AS Treatment "
                        + "FROM table_data_periksa";
                Rs = Stm.executeQuery(Sql);
                while (Rs.next()) {
                    Dtm.addRow(new Object[]{
                        Rs.getString("NoUrut"),
                        Rs.getString("TanggalPeriksa"),
                        Rs.getString("Nama"),
                        Rs.getString("Alamat"),
                        Rs.getString("NoHp"),
                        Rs.getString("JenisPeliharaan"),
                        Rs.getString("JenisHewan"),
                        Rs.getString("NamaHewan"),
                        Rs.getString("NamaDokter"),
                        Rs.getString("Layanan"),
                        Rs.getString("Treatment"),
                    });
                    jTable1.setModel(Dtm);
                }
            } catch (Exception e) {
                System.out.println("Ada Kesalahan " + e.toString());
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }
    
    void cariDataTable(){
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            TampilModelJTabel();
            try {
                Sql = "SELECT table_data_periksa.no_urut AS NoUrut, "
                        + "table_data_periksa.tanggal_periksa AS TanggalPeriksa, "
                        + "table_data_periksa.nama AS Nama, "
                        + "table_data_periksa.alamat AS Alamat, "
                        + "table_data_periksa.no_hp AS NoHp, "
                        + "table_data_periksa.jenis_peliharaan AS JenisPeliharaan, "
                        + "table_data_periksa.jenis_hewan AS JenisHewan, "
                        + "table_data_periksa.nama_hewan AS NamaHewan, "
                        + "table_data_periksa.nama_dokter AS NamaDokter, "
                        + "table_data_periksa.layanan AS Layanan, "
                        + "table_data_periksa.treatment AS Treatment "
                        + "FROM table_data_periksa "
                        + "WHERE nama LIKE '%"+textFieldSearch.getText()+"%'";
                Rs = Stm.executeQuery(Sql);
                while (Rs.next()) {
                    Dtm.addRow(new Object[]{
                        Rs.getString("NoUrut"),
                        Rs.getString("TanggalPeriksa"),
                        Rs.getString("Nama"),
                        Rs.getString("Alamat"),
                        Rs.getString("NoHp"),
                        Rs.getString("JenisPeliharaan"),
                        Rs.getString("JenisHewan"),
                        Rs.getString("NamaHewan"),
                        Rs.getString("NamaDokter"),
                        Rs.getString("Layanan"),
                        Rs.getString("Treatment"),
                    });
                    jTable1.setModel(Dtm);
                }
            } catch (Exception e) {
                System.out.println("Ada Kesalahan " + e.toString());
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        textFieldSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textFieldNoUrut = new javax.swing.JTextField();
        textFieldNama = new javax.swing.JTextField();
        textFieldAlamat = new javax.swing.JTextField();
        textFieldNoHp = new javax.swing.JTextField();
        jdateTanggalPeriksa = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        textFieldNamaHewan = new javax.swing.JTextField();
        cmbJenisPeliharaan = new javax.swing.JComboBox<>();
        textFieldNamaDokter = new javax.swing.JTextField();
        cmbJenisTreatment = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        textFieldJenisHewan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cmbJenisLayanan = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("Data Klinik.Ku");

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        textFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Cari :");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("No. Urut :");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("Tanggal Periksa :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("Nama :");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("Alamat :");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("No. HP");

        textFieldNoUrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldNoUrutActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Jenis Peliharaan :");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setText("Jenis Hewan :");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel10.setText("Nama Hewan :");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel11.setText("Nama Dokter :");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel12.setText("Treatment :");

        cmbJenisPeliharaan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Jenis Peliharaan]" }));
        cmbJenisPeliharaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisPeliharaanActionPerformed(evt);
            }
        });

        cmbJenisTreatment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Jenis Treatment]" }));

        btnDelete.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel13.setText("Jenis Layanan :");

        cmbJenisLayanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Jenis Layanan]" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textFieldNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(textFieldNama, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(textFieldNoUrut)
                            .addComponent(textFieldAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jdateTanggalPeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldNamaDokter, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(textFieldNamaHewan, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(cmbJenisPeliharaan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJenisTreatment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldJenisHewan)
                            .addComponent(cmbJenisLayanan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textFieldNoUrut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cmbJenisPeliharaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jdateTanggalPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(textFieldJenisHewan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(textFieldNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textFieldAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(textFieldNamaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textFieldNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(cmbJenisLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbJenisTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit)
                    .addComponent(btnSave)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.show();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbJenisPeliharaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisPeliharaanActionPerformed
        String Kode = "";
        try{
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from load_jenis_peliharaan where jenis_peliharaan='"+cmbJenisPeliharaan.getSelectedItem().toString()+"'";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                Kode= Rs.getString("jenis_hewan");
            }
            textFieldJenisHewan.setText(Kode);
        } catch(SQLException e){
            System.out.println("koneksi gagal"+e.toString());
        }
    }//GEN-LAST:event_cmbJenisPeliharaanActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        kosongObjek();
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String Tampilan="yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String TanggalPeriksa = String.valueOf(Fm.format(jdateTanggalPeriksa.getDate()));
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = null;
            Sql = "insert into table_data_periksa (id, no_urut, tanggal_periksa, nama, alamat, no_hp, "
                    + "jenis_peliharaan, jenis_hewan, nama_hewan, nama_dokter, layanan, treatment) VALUES (NULL, '"
                    +textFieldNoUrut.getText()+"', '"+TanggalPeriksa+"', '"+textFieldNama.getText()+"', '"
                    +textFieldAlamat.getText()+"', '"+textFieldNoHp.getText()+"', '"+cmbJenisPeliharaan.getSelectedItem()+"', '"
                    +textFieldJenisHewan.getText()+"', '"+textFieldNamaHewan.getText()+"', '"+textFieldNamaDokter.getText()+"', '"
                    +cmbJenisLayanan.getSelectedItem()+"', '"+cmbJenisTreatment.getSelectedItem()+"')";
            Stm = Con.createStatement();
            int AdaPenambahanRecord = Stm.executeUpdate(Sql);
            tampilDataTable();
            if (AdaPenambahanRecord>0) {
                JOptionPane.showMessageDialog(this,"Data Berhasil Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,"Data Gagal Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
            }
            Stm.close();
            kosongObjek();
            nonAktifField();
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal " +e.toString());
        }
        tampilDataTable();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String Tampilan="yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String TanggalPeriksa = String.valueOf(Fm.format(jdateTanggalPeriksa.getDate()));
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = null;
            Sql = "UPDATE table_data_periksa set tanggal_periksa = '"+TanggalPeriksa+"', nama = '"+textFieldNama.getText()+"', "
                    + "alamat = '"+textFieldAlamat.getText()+"', no_hp = '"+textFieldNoHp.getText()+"', jenis_peliharaan = '"
                    +cmbJenisPeliharaan.getSelectedItem()+"', jenis_hewan = '"+textFieldJenisHewan.getText()+"', nama_hewan = '"
                    +textFieldNamaHewan.getText()+"', nama_dokter = '"+textFieldNamaDokter.getText()+"', layanan = '"
                    +cmbJenisLayanan.getSelectedItem()+"', treatment = '"
                    +cmbJenisTreatment.getSelectedItem()+"' WHERE no_urut = '"+textFieldNoUrut.getText()+"'";
            Stm= Con.createStatement();
            int AdaPerubahanRecord= Stm.executeUpdate(Sql);
            tampilDataTable();
            if (AdaPerubahanRecord>0){
                JOptionPane.showMessageDialog(this,"Data Berhasil Di Edit", "Informasi",JOptionPane.INFORMATION_MESSAGE);
            }else
                JOptionPane.showMessageDialog(this,"Data Gagal Di Edit", "Informasi",JOptionPane.INFORMATION_MESSAGE);
            Stm.close();
            kosongObjek();
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
            nonAktifField();
        } catch (Exception e) {
            System.out.println("Koneksi Gagal " +e.toString());
        }
        tampilDataTable();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = null; 
            Sql = "delete from table_data_periksa where no_urut = '"+textFieldNoUrut.getText()+"'  ";
            Stm= Con.createStatement();
            int AdaPerubahanRecord= Stm.executeUpdate(Sql);
            tampilDataTable();
            if (AdaPerubahanRecord>0){
                JOptionPane.showMessageDialog(this,"Data Berhasil Di Hapus", "Informasi",JOptionPane.INFORMATION_MESSAGE);
            }else
                JOptionPane.showMessageDialog(this,"Data Gagal Di Hapus", "Informasi",JOptionPane.INFORMATION_MESSAGE);
            Stm.close();
            kosongObjek();
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
            nonAktifField();
        } catch (SQLException e){
            System.out.println("Koneksi Gagal " +e.toString());
        }
        tampilDataTable();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        cariDataTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void textFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchActionPerformed
        cariDataTable();
    }//GEN-LAST:event_textFieldSearchActionPerformed

    private void textFieldNoUrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldNoUrutActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from table_data_periksa where no_urut='" +textFieldNoUrut.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){ 
                JOptionPane.showMessageDialog(this,"Silahkan Melakukan Edit Informasi", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                btnSave.setEnabled(false);
                
                jdateTanggalPeriksa.setDate(Rs2.getDate("tanggal_periksa"));
                textFieldNama.setText(Rs2.getString("nama"));
                textFieldAlamat.setText(Rs2.getString("alamat"));
                textFieldNoHp.setText(Rs2.getString("no_hp"));
                cmbJenisPeliharaan.setSelectedItem(Rs2.getString("jenis_peliharaan"));
                textFieldJenisHewan.setText(Rs2.getString("jenis_hewan"));
                textFieldNamaHewan.setText(Rs2.getString("nama_hewan"));
                textFieldNamaDokter.setText(Rs2.getString("nama_dokter"));
                cmbJenisLayanan.setSelectedItem(Rs2.getString("layanan"));
                cmbJenisTreatment.setSelectedItem(Rs2.getString("treatment"));
                
                btnDelete.setEnabled(true);
                btnEdit.setEnabled(true);
                
                AktifField();
            } else {
                AktifField();
                
                btnSave.setEnabled(true);
                btnClear.setEnabled(true);
                
                jdateTanggalPeriksa.setDate(null);
                textFieldNama.setText("");
                textFieldAlamat.setText("");
                textFieldNoHp.setText("");
                cmbJenisPeliharaan.setSelectedIndex(0);
                textFieldJenisHewan.setText("");
                textFieldNamaHewan.setText("");
                textFieldNamaDokter.setText("");
                cmbJenisTreatment.setSelectedIndex(0);
                
                textFieldNama.requestFocus();
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_textFieldNoUrutActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            Dtm = (DefaultTableModel) jTable1.getModel();
            int i = jTable1.getSelectedRow();
            if (i == -1) {
                return;
            }
            
            textFieldNoUrut.setText(jTable1.getValueAt(i, 0).toString());
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)Dtm.getValueAt(i, 1));
            jdateTanggalPeriksa.setDate(date);
            textFieldNama.setText(jTable1.getValueAt(i, 2).toString());
            textFieldAlamat.setText(jTable1.getValueAt(i, 3).toString());
            textFieldNoHp.setText(jTable1.getValueAt(i, 4).toString());
            cmbJenisPeliharaan.setSelectedItem(jTable1.getValueAt(i, 5));
            textFieldJenisHewan.setText(jTable1.getValueAt(i, 6).toString());
            textFieldNamaHewan.setText(jTable1.getValueAt(i, 7).toString());
            textFieldNamaDokter.setText(jTable1.getValueAt(i, 8).toString());
            cmbJenisLayanan.setSelectedItem(jTable1.getValueAt(i, 9));
            cmbJenisTreatment.setSelectedItem(jTable1.getValueAt(i, 10));
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(DataPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbJenisLayanan;
    private javax.swing.JComboBox<String> cmbJenisPeliharaan;
    private javax.swing.JComboBox<String> cmbJenisTreatment;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser jdateTanggalPeriksa;
    private javax.swing.JTextField textFieldAlamat;
    private javax.swing.JTextField textFieldJenisHewan;
    private javax.swing.JTextField textFieldNama;
    private javax.swing.JTextField textFieldNamaDokter;
    private javax.swing.JTextField textFieldNamaHewan;
    private javax.swing.JTextField textFieldNoHp;
    private javax.swing.JTextField textFieldNoUrut;
    private javax.swing.JTextField textFieldSearch;
    // End of variables declaration//GEN-END:variables
}
