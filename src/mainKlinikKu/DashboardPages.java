/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainKlinikKu;

import java.awt.Color;
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
public class DashboardPages extends javax.swing.JFrame {

    Koneksi Konek = new Koneksi();
    private Connection Con;
    Statement Stm;
    ResultSet Rs,Rs2;
    String Sql;
    DefaultTableModel Dtm;
    
    public DashboardPages() {
        initComponents();
        
        LoadDataJenisPeliharaan();
        LoadDataTreatment();
        LoadDataJenisHewan();
        LoadDataJenisLayanan();
        
        AddDataJenisHewanBaru();
        AddDataJenisLayananBaru();
        AddDataJenisPeliharaanBaru();
        AddDataJenisTreatmentBaru();
        
        tampilDataTable();
        
        panelLokasi.setVisible(false);
        panelDataPeriksa.setVisible(false);
        panelDataLainnya.setVisible(false);
        panelDataUser.setVisible(false);
        panelHome.setVisible(true);
        
        tabHome.setBackground(Color.BLACK);
        
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
    
    private void AddDataJenisHewanBaru(){
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from load_jenis_hewan where kode = '"+textFieldKode.getText()+"'";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                cmbJenisHewan.addItem(Rs.getString("jenis_hewan"));
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal : "+e.toString());
        }
    }
    
    private void AddDataJenisPeliharaanBaru(){
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from load_jenis_peliharaan where kode = '"+textFieldKode1.getText()+"'";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                cmbJenisPeliharaan.addItem(Rs.getString("jenis_peliharaan"));
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal : "+e.toString());
        }
    }
    
    private void AddDataJenisLayananBaru(){
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from load_jenis_layanan where kode = '"+textFieldKode2.getText()+"'";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                cmbJenisLayanan.addItem(Rs.getString("layanan"));
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal : "+e.toString());
        }
    }
    
    private void AddDataJenisTreatmentBaru(){
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from load_treatment where kode = '"+textFieldKode3.getText()+"'";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                cmbJenisTreatment.addItem(Rs.getString("jenis_treatment"));
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal : "+e.toString());
        }
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
        cmbJenisLayanan.setSelectedIndex(0);
        
//        textFieldKode1.setText("");
//        textFieldJenisPeliharaan.setText("");
//        cmbJenisHewan.setSelectedIndex(0);
//        
//        textFieldKode.setText("");
//        textFieldJenisTreatment.setText("");
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

        jPanel1 = new javax.swing.JPanel();
        tabDataLainnya = new javax.swing.JPanel();
        tabJenisPeliharaan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tabHome = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tabUser = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tabDataPeriksa = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tabLogout = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        tabExit = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        tabLokasi = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        panelLokasi = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        panelHome = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panelDataPeriksa = new javax.swing.JPanel();
        cmbJenisPeliharaan = new javax.swing.JComboBox<>();
        textFieldNamaDokter = new javax.swing.JTextField();
        cmbJenisTreatment = new javax.swing.JComboBox<>();
        textFieldSearch = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        textFieldJenisHewan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cmbJenisLayanan = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        textFieldNoUrut = new javax.swing.JTextField();
        textFieldNama = new javax.swing.JTextField();
        textFieldAlamat = new javax.swing.JTextField();
        textFieldNoHp = new javax.swing.JTextField();
        jdateTanggalPeriksa = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        textFieldNamaHewan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelDataLainnya = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        textFieldJenisHewan1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        textFieldKode = new javax.swing.JTextField();
        btnAdd1 = new javax.swing.JButton();
        textFieldJenisPeliharaan = new javax.swing.JTextField();
        cmbJenisHewan = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        btnAdd2 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        textFieldKode1 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        textFieldJenisLayanan = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        textFieldKode2 = new javax.swing.JTextField();
        btnAdd3 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        textFieldJenisTreatment = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        textFieldKode3 = new javax.swing.JTextField();
        btnAdd4 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        panelDataUser = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        textFieldPassword = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        textFieldUsername = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        tabDataLainnya.setBackground(new java.awt.Color(204, 0, 102));
        tabDataLainnya.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabDataLainnyaMouseClicked(evt);
            }
        });

        tabJenisPeliharaan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        tabJenisPeliharaan.setForeground(new java.awt.Color(255, 255, 255));
        tabJenisPeliharaan.setText("Input Data Lainnya");

        javax.swing.GroupLayout tabDataLainnyaLayout = new javax.swing.GroupLayout(tabDataLainnya);
        tabDataLainnya.setLayout(tabDataLainnyaLayout);
        tabDataLainnyaLayout.setHorizontalGroup(
            tabDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDataLainnyaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabJenisPeliharaan)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        tabDataLainnyaLayout.setVerticalGroup(
            tabDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDataLainnyaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabJenisPeliharaan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("disremVet");

        tabHome.setBackground(new java.awt.Color(204, 0, 102));
        tabHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabHomeMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Home");

        javax.swing.GroupLayout tabHomeLayout = new javax.swing.GroupLayout(tabHome);
        tabHome.setLayout(tabHomeLayout);
        tabHomeLayout.setHorizontalGroup(
            tabHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabHomeLayout.setVerticalGroup(
            tabHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabUser.setBackground(new java.awt.Color(204, 0, 102));
        tabUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabUserMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Input User");

        javax.swing.GroupLayout tabUserLayout = new javax.swing.GroupLayout(tabUser);
        tabUser.setLayout(tabUserLayout);
        tabUserLayout.setHorizontalGroup(
            tabUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(196, Short.MAX_VALUE))
        );
        tabUserLayout.setVerticalGroup(
            tabUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabDataPeriksa.setBackground(new java.awt.Color(204, 0, 102));
        tabDataPeriksa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabDataPeriksaMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Input Data periksa");

        javax.swing.GroupLayout tabDataPeriksaLayout = new javax.swing.GroupLayout(tabDataPeriksa);
        tabDataPeriksa.setLayout(tabDataPeriksaLayout);
        tabDataPeriksaLayout.setHorizontalGroup(
            tabDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDataPeriksaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabDataPeriksaLayout.setVerticalGroup(
            tabDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDataPeriksaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabLogout.setBackground(new java.awt.Color(204, 0, 102));
        tabLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabLogoutMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Log Out");

        javax.swing.GroupLayout tabLogoutLayout = new javax.swing.GroupLayout(tabLogout);
        tabLogout.setLayout(tabLogoutLayout);
        tabLogoutLayout.setHorizontalGroup(
            tabLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabLogoutLayout.setVerticalGroup(
            tabLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabExit.setBackground(new java.awt.Color(204, 0, 102));
        tabExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabExitMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Exit");

        javax.swing.GroupLayout tabExitLayout = new javax.swing.GroupLayout(tabExit);
        tabExit.setLayout(tabExitLayout);
        tabExitLayout.setHorizontalGroup(
            tabExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabExitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(252, Short.MAX_VALUE))
        );
        tabExitLayout.setVerticalGroup(
            tabExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabExitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabLokasi.setBackground(new java.awt.Color(204, 0, 102));
        tabLokasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabLokasiMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Lokasi");

        javax.swing.GroupLayout tabLokasiLayout = new javax.swing.GroupLayout(tabLokasi);
        tabLokasi.setLayout(tabLokasiLayout);
        tabLokasiLayout.setHorizontalGroup(
            tabLokasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabLokasiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabLokasiLayout.setVerticalGroup(
            tabLokasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabLokasiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabDataPeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabLokasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabDataLainnya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tabHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabDataPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabDataLainnya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196)
                .addComponent(tabLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        panelLokasi.setBackground(new java.awt.Color(204, 204, 204));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainKlinikKu/assets/Maps_disremVet.png"))); // NOI18N

        jLabel46.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel46.setText("Lokasi Dinas Peternakan dan Kesehatan Hewan Provinsi Sulawesi Selatan");

        javax.swing.GroupLayout panelLokasiLayout = new javax.swing.GroupLayout(panelLokasi);
        panelLokasi.setLayout(panelLokasiLayout);
        panelLokasiLayout.setHorizontalGroup(
            panelLokasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLokasiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLokasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                    .addGroup(panelLokasiLayout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLokasiLayout.setVerticalGroup(
            panelLokasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLokasiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel7.setText("Dinas Peternakan dan Kesehatan Hewan Provinsi Sulawesi Selatan");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Jl. Veteran Selatan No.234, Bonto Lebang, Kec. Mamajang, Kota Makassar, Sulawesi Selatan 90131");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainKlinikKu/assets/image_intansi.jpeg"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainKlinikKu/assets/image1.jpeg"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainKlinikKu/assets/image2.jpeg"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainKlinikKu/assets/image3.jpeg"))); // NOI18N

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainKlinikKu/assets/image4.jpeg"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainKlinikKu/assets/image5.jpeg"))); // NOI18N

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHomeLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHomeLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel8))
                    .addComponent(jLabel9)
                    .addGroup(panelHomeLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHomeLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(16, 16, 16)))
                .addGap(29, 29, 29))
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cmbJenisPeliharaan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Jenis Peliharaan]" }));
        cmbJenisPeliharaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisPeliharaanActionPerformed(evt);
            }
        });

        cmbJenisTreatment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Jenis Treatment]" }));

        textFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel19.setText("Cari :");

        btnSave.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel20.setText("No. Urut :");

        btnClear.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel21.setText("Tanggal Periksa :");

        jLabel22.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel22.setText("Nama :");

        jLabel23.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel23.setText("Jenis Layanan :");

        jLabel24.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel24.setText("Alamat :");

        cmbJenisLayanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Jenis Layanan]" }));

        jLabel25.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel25.setText("No. HP");

        textFieldNoUrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldNoUrutActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel26.setText("Jenis Peliharaan :");

        jLabel27.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel27.setText("Jenis Hewan :");

        jLabel28.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel28.setText("Nama Hewan :");

        jLabel29.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel29.setText("Nama Dokter :");

        jLabel30.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel30.setText("Treatment :");

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel31.setText("Data Periksa");

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

        javax.swing.GroupLayout panelDataPeriksaLayout = new javax.swing.GroupLayout(panelDataPeriksa);
        panelDataPeriksa.setLayout(panelDataPeriksaLayout);
        panelDataPeriksaLayout.setHorizontalGroup(
            panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataPeriksaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelDataPeriksaLayout.createSequentialGroup()
                        .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addGap(29, 29, 29)
                        .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textFieldNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(textFieldNama, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(textFieldNoUrut)
                            .addComponent(textFieldAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jdateTanggalPeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel23))
                        .addGap(43, 43, 43)
                        .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldNamaDokter, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                            .addComponent(textFieldNamaHewan, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                            .addComponent(cmbJenisPeliharaan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJenisTreatment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldJenisHewan)
                            .addComponent(cmbJenisLayanan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataPeriksaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete))
                    .addGroup(panelDataPeriksaLayout.createSequentialGroup()
                        .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addGroup(panelDataPeriksaLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDataPeriksaLayout.setVerticalGroup(
            panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataPeriksaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addGap(43, 43, 43)
                .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDataPeriksaLayout.createSequentialGroup()
                        .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(textFieldNoUrut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(cmbJenisPeliharaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jdateTanggalPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(textFieldJenisHewan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(textFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(textFieldNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(textFieldAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(textFieldNamaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(textFieldNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(cmbJenisLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cmbJenisTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit)
                    .addComponent(btnSave)
                    .addComponent(btnClear))
                .addGap(18, 18, 18)
                .addGroup(panelDataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(textFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel32.setText("Data Hewan");

        jLabel33.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel33.setText("Kode :");

        textFieldJenisHewan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldJenisHewan1ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel34.setText("Jenis Hewan :");

        textFieldKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldKodeActionPerformed(evt);
            }
        });

        btnAdd1.setText("Add");
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        cmbJenisHewan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Jenis Peliharaan]" }));
        cmbJenisHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisHewanActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel35.setText("Jenis Hewan :");

        btnAdd2.setText("Add");
        btnAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd2ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel36.setText("Data Jenis Peliharaan");

        jLabel37.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel37.setText("Kode :");

        textFieldKode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldKode1ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel38.setText("Jenis Peliharaan :");

        jLabel39.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel39.setText("Kode :");

        textFieldJenisLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldJenisLayananActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel40.setText("Jenis Layanan :");

        textFieldKode2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldKode2ActionPerformed(evt);
            }
        });

        btnAdd3.setText("Add");
        btnAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd3ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel41.setText("Data Layanan");

        jLabel42.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel42.setText("Kode :");

        textFieldJenisTreatment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldJenisTreatmentActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel43.setText("Jenis Treatment :");

        textFieldKode3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldKode3ActionPerformed(evt);
            }
        });

        btnAdd4.setText("Add");
        btnAdd4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd4ActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel44.setText("Data Treatment");

        javax.swing.GroupLayout panelDataLainnyaLayout = new javax.swing.GroupLayout(panelDataLainnya);
        panelDataLainnya.setLayout(panelDataLainnyaLayout);
        panelDataLainnyaLayout.setHorizontalGroup(
            panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                                    .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel34)
                                        .addComponent(jLabel33))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textFieldKode, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                        .addComponent(textFieldJenisHewan1)))))
                        .addGap(85, 85, 85)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(272, 272, 272))
                            .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                                .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                                        .addComponent(btnAdd3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9))
                                    .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                                                .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel43)
                                                    .addComponent(jLabel42))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(textFieldJenisTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                                                    .addComponent(textFieldKode3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel44)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataLainnyaLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnAdd4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                                            .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel40)
                                                .addComponent(jLabel39))
                                            .addGap(18, 18, 18)
                                            .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(textFieldJenisLayanan)
                                                .addComponent(textFieldKode2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(29, 29, 29))))
                    .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                                    .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel37)
                                        .addComponent(jLabel38)
                                        .addComponent(jLabel35))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(textFieldJenisPeliharaan, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textFieldKode1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmbJenisHewan, 0, 219, Short.MAX_VALUE)))))
                        .addGap(29, 29, 29))))
        );
        panelDataLainnyaLayout.setVerticalGroup(
            panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(35, 35, 35)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(textFieldKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(textFieldJenisHewan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd1))
                    .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(35, 35, 35)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(textFieldKode2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(textFieldJenisLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd3)))
                .addGap(38, 38, 38)
                .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(35, 35, 35)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(textFieldKode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(textFieldJenisPeliharaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(cmbJenisHewan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd2)
                            .addComponent(btnAdd4)))
                    .addGroup(panelDataLainnyaLayout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(35, 35, 35)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(textFieldKode3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDataLainnyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(textFieldJenisTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(248, 248, 248))
        );

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel14.setText("Data User");

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel15.setText("Username :");

        textFieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldPasswordActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel16.setText("Password :");

        textFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUsernameActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDataUserLayout = new javax.swing.GroupLayout(panelDataUser);
        panelDataUser.setLayout(panelDataUserLayout);
        panelDataUserLayout.setHorizontalGroup(
            panelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDataUserLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelDataUserLayout.createSequentialGroup()
                        .addGroup(panelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelDataUserLayout.createSequentialGroup()
                                .addGap(0, 309, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDataUserLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(40, 40, 40)
                                .addComponent(textFieldPassword))
                            .addGroup(panelDataUserLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(386, 386, 386))))
        );
        panelDataUserLayout.setVerticalGroup(
            panelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(35, 35, 35)
                .addGroup(panelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnAdd)
                .addContainerGap(459, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLokasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelDataLainnya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelDataPeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLokasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelDataLainnya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelDataPeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabHomeMouseClicked
        panelHome.setVisible(true);
        panelDataPeriksa.setVisible(false);
        panelDataLainnya.setVisible(false);
        panelDataUser.setVisible(false);
        panelLokasi.setVisible(false);
        
        tabHome.setBackground(Color.BLACK);
        tabDataPeriksa.setBackground(new Color(204,0, 102));
        tabDataLainnya.setBackground(new Color(204,0, 102));
        tabUser.setBackground(new Color(204,0, 102));
        tabLokasi.setBackground(new Color(204,0, 102));
    }//GEN-LAST:event_tabHomeMouseClicked

    private void tabDataPeriksaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabDataPeriksaMouseClicked
        panelLokasi.setVisible(false);
        panelDataPeriksa.setVisible(true);
        panelDataLainnya.setVisible(false);
        panelDataUser.setVisible(false);
        panelHome.setVisible(false);
        
        tabHome.setBackground(new Color(204,0, 102));
        tabDataPeriksa.setBackground(Color.BLACK);
        tabDataLainnya.setBackground(new Color(204,0, 102));
        tabUser.setBackground(new Color(204,0, 102));
        tabLokasi.setBackground(new Color(204,0, 102));
    }//GEN-LAST:event_tabDataPeriksaMouseClicked

    private void tabDataLainnyaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabDataLainnyaMouseClicked
        panelLokasi.setVisible(false);
        panelDataPeriksa.setVisible(false);
        panelDataLainnya.setVisible(true);
        panelDataUser.setVisible(false);
        panelHome.setVisible(false);
        
        tabHome.setBackground(new Color(204,0, 102));
        tabDataPeriksa.setBackground(new Color(204,0, 102));
        tabDataLainnya.setBackground(Color.BLACK);
        tabUser.setBackground(new Color(204,0, 102));
        tabLokasi.setBackground(new Color(204,0, 102));
    }//GEN-LAST:event_tabDataLainnyaMouseClicked

    private void tabUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabUserMouseClicked
        panelLokasi.setVisible(false);
        panelDataPeriksa.setVisible(false);
        panelDataLainnya.setVisible(false);
        panelDataUser.setVisible(true);
        panelHome.setVisible(false);
        
        tabHome.setBackground(new Color(204,0, 102));
        tabDataPeriksa.setBackground(new Color(204,0, 102));
        tabDataLainnya.setBackground(new Color(204,0, 102));
        tabUser.setBackground(Color.BLACK);
        tabLokasi.setBackground(new Color(204,0, 102));
    }//GEN-LAST:event_tabUserMouseClicked

    private void tabLokasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabLokasiMouseClicked
        panelHome.setVisible(false);
        panelDataPeriksa.setVisible(false);
        panelDataLainnya.setVisible(false);
        panelDataUser.setVisible(false);
        panelLokasi.setVisible(true);
        
        tabHome.setBackground(new Color(204,0, 102));
        tabDataPeriksa.setBackground(new Color(204,0, 102));
        tabDataLainnya.setBackground(new Color(204,0, 102));
        tabUser.setBackground(new Color(204,0, 102));
        tabLokasi.setBackground(Color.BLACK);
    }//GEN-LAST:event_tabLokasiMouseClicked

    private void tabLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabLogoutMouseClicked
        LoginPage login = new LoginPage();
        login.show();
        dispose();
    }//GEN-LAST:event_tabLogoutMouseClicked

    private void tabExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabExitMouseClicked
        this.dispose();
    }//GEN-LAST:event_tabExitMouseClicked

    private void textFieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldPasswordActionPerformed

    }//GEN-LAST:event_textFieldPasswordActionPerformed

    private void textFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUsernameActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from table_login where username='" +textFieldUsername.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Username Lain atau Melakukan Login", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textFieldPassword.requestFocus();
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_textFieldUsernameActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from table_login where username='" +textFieldUsername.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Username Lain atau Melakukan Login", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    Con = Konek.getKoneksiDatabase();
                    Stm = null;
                    Sql = "insert into table_login (username, password) VALUES ('"
                    +textFieldUsername.getText()+"', '"+textFieldPassword.getText()+"')";
                    Stm = Con.createStatement();
                    int AdaPenambahanRecord = Stm.executeUpdate(Sql);
                    if (AdaPenambahanRecord>0) {
                        JOptionPane.showMessageDialog(this,"Data Berhasil Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,"Data Gagal Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                    Stm.close();
                    textFieldUsername.setText("");
                    textFieldPassword.setText("");
                } catch (SQLException e) {
                    System.out.println("Koneksi Gagal " +e.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_btnAddActionPerformed

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

    private void textFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchActionPerformed
        cariDataTable();
    }//GEN-LAST:event_textFieldSearchActionPerformed

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
            btnSave.setEnabled(true);
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
        } catch (SQLException e){
            System.out.println("Koneksi Gagal " +e.toString());
        }
        tampilDataTable();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        cariDataTable();
    }//GEN-LAST:event_btnSearchActionPerformed

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
            btnSave.setEnabled(true);
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
        } catch (Exception e) {
            System.out.println("Koneksi Gagal " +e.toString());
        }
        tampilDataTable();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from table_data_periksa where no_urut='" +textFieldNoUrut.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukan Nomor Urut Baru", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
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
                } catch (SQLException e) {
                    System.out.println("Koneksi Gagal " +e.toString());
                }
                tampilDataTable();
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        kosongObjek();
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
    }//GEN-LAST:event_btnClearActionPerformed

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
            } else {
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
            
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
            btnSave.setEnabled(false);
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void textFieldJenisHewan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldJenisHewan1ActionPerformed

    }//GEN-LAST:event_textFieldJenisHewan1ActionPerformed

    private void textFieldKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldKodeActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from load_jenis_hewan where kode='" +textFieldKode.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Kode Hewan Lain", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textFieldJenisHewan.setEnabled(true);
                textFieldJenisHewan.requestFocus();
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_textFieldKodeActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from load_jenis_hewan where kode='" +textFieldKode.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Kode Hewan Lain", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    Con = Konek.getKoneksiDatabase();
                    Stm = null;
                    Sql = "insert into load_jenis_hewan (id, kode, jenis_hewan) VALUES (NULL, '"
                    +textFieldKode.getText()+"', '"+textFieldJenisHewan1.getText()+"')";
                    Stm = Con.createStatement();
                    int AdaPenambahanRecord = Stm.executeUpdate(Sql);
                    if (AdaPenambahanRecord>0) {
                        JOptionPane.showMessageDialog(this,"Data Berhasil Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,"Data Gagal Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                    Stm.close();
                    AddDataJenisHewanBaru();
                    textFieldKode.setText("");
                    textFieldJenisHewan1.setText("");
                } catch (SQLException e) {
                    System.out.println("Koneksi Gagal " +e.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void cmbJenisHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisHewanActionPerformed

    }//GEN-LAST:event_cmbJenisHewanActionPerformed

    private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from load_jenis_peliharaan where kode='" +textFieldKode1.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Kode Lain", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    Con = Konek.getKoneksiDatabase();
                    Stm = null;
                    Sql = "insert into load_jenis_peliharaan (id, kode, jenis_peliharaan, jenis_hewan) VALUES (NULL, '"
                    +textFieldKode1.getText()+"', '"+textFieldJenisPeliharaan.getText()+"', '"
                    +cmbJenisHewan.getSelectedItem()+"')";
                    Stm = Con.createStatement();
                    int AdaPenambahanRecord = Stm.executeUpdate(Sql);
                    if (AdaPenambahanRecord>0) {
                        JOptionPane.showMessageDialog(this,"Data Berhasil Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,"Data Gagal Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                    Stm.close();
                    AddDataJenisPeliharaanBaru();
                    textFieldKode1.setText("");
                    textFieldJenisPeliharaan.setText("");
                    cmbJenisHewan.setSelectedIndex(0);
                } catch (SQLException e) {
                    System.out.println("Koneksi Gagal " +e.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_btnAdd2ActionPerformed

    private void textFieldKode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldKode1ActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from load_jenis_peliharaan where kode='" +textFieldKode1.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Kode Lain", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textFieldJenisPeliharaan.requestFocus();
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_textFieldKode1ActionPerformed

    private void textFieldJenisLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldJenisLayananActionPerformed

    }//GEN-LAST:event_textFieldJenisLayananActionPerformed

    private void textFieldKode2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldKode2ActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from load_jenis_layanan where kode='" +textFieldKode2.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Kode Jenis Layanan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textFieldJenisLayanan.setEnabled(true);
                textFieldJenisLayanan.requestFocus();
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_textFieldKode2ActionPerformed

    private void btnAdd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd3ActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from load_jenis_layanan where kode='" +textFieldKode2.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Kode Jenis Layanan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    Con = Konek.getKoneksiDatabase();
                    Stm = null;
                    Sql = "insert into load_jenis_layanan (id, kode, layanan) VALUES (NULL, '"
                    +textFieldKode2.getText()+"', '"+textFieldJenisLayanan.getText()+"')";
                    Stm = Con.createStatement();
                    int AdaPenambahanRecord = Stm.executeUpdate(Sql);
                    if (AdaPenambahanRecord>0) {
                        JOptionPane.showMessageDialog(this,"Data Berhasil Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,"Data Gagal Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                    Stm.close();
                    AddDataJenisLayananBaru();
                    textFieldKode2.setText("");
                    textFieldJenisLayanan.setText("");
                } catch (SQLException e) {
                    System.out.println("Koneksi Gagal " +e.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_btnAdd3ActionPerformed

    private void textFieldJenisTreatmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldJenisTreatmentActionPerformed

    }//GEN-LAST:event_textFieldJenisTreatmentActionPerformed

    private void textFieldKode3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldKode3ActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from load_treatment where kode='" +textFieldKode3.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Kode Treatment Lain", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textFieldJenisTreatment.setEnabled(true);
                textFieldJenisTreatment.requestFocus();
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_textFieldKode3ActionPerformed

    private void btnAdd4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd4ActionPerformed
        try {
            Con = Konek.getKoneksiDatabase();
            Sql = "Select * from load_treatment where kode='" +textFieldKode3.getText() + "'";
            Stm= Con.createStatement();
            Rs2 = Stm.executeQuery(Sql);
            if (Rs2.next()){
                JOptionPane.showMessageDialog(this,"Silahkan Memasukkan Kode Treatment Lain", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    Con = Konek.getKoneksiDatabase();
                    Stm = null;
                    Sql = "insert into load_treatment (id, kode, jenis_treatment) VALUES (NULL, '"
                    +textFieldKode3.getText()+"', '"+textFieldJenisTreatment.getText()+"')";
                    Stm = Con.createStatement();
                    int AdaPenambahanRecord = Stm.executeUpdate(Sql);
                    if (AdaPenambahanRecord>0) {
                        JOptionPane.showMessageDialog(this,"Data Berhasil Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,"Data Gagal Tersimpan", "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                    Stm.close();
                    AddDataJenisTreatmentBaru();
                    textFieldKode3.setText("");
                    textFieldJenisTreatment.setText("");
                } catch (SQLException e) {
                    System.out.println("Koneksi Gagal " +e.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_btnAdd4ActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardPages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardPages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardPages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardPages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardPages().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnAdd2;
    private javax.swing.JButton btnAdd3;
    private javax.swing.JButton btnAdd4;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbJenisHewan;
    private javax.swing.JComboBox<String> cmbJenisLayanan;
    private javax.swing.JComboBox<String> cmbJenisPeliharaan;
    private javax.swing.JComboBox<String> cmbJenisTreatment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser jdateTanggalPeriksa;
    private javax.swing.JPanel panelDataLainnya;
    private javax.swing.JPanel panelDataPeriksa;
    private javax.swing.JPanel panelDataUser;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLokasi;
    private javax.swing.JPanel tabDataLainnya;
    private javax.swing.JPanel tabDataPeriksa;
    private javax.swing.JPanel tabExit;
    private javax.swing.JPanel tabHome;
    private javax.swing.JLabel tabJenisPeliharaan;
    private javax.swing.JPanel tabLogout;
    private javax.swing.JPanel tabLokasi;
    private javax.swing.JPanel tabUser;
    private javax.swing.JTextField textFieldAlamat;
    private javax.swing.JTextField textFieldJenisHewan;
    private javax.swing.JTextField textFieldJenisHewan1;
    private javax.swing.JTextField textFieldJenisLayanan;
    private javax.swing.JTextField textFieldJenisPeliharaan;
    private javax.swing.JTextField textFieldJenisTreatment;
    private javax.swing.JTextField textFieldKode;
    private javax.swing.JTextField textFieldKode1;
    private javax.swing.JTextField textFieldKode2;
    private javax.swing.JTextField textFieldKode3;
    private javax.swing.JTextField textFieldNama;
    private javax.swing.JTextField textFieldNamaDokter;
    private javax.swing.JTextField textFieldNamaHewan;
    private javax.swing.JTextField textFieldNoHp;
    private javax.swing.JTextField textFieldNoUrut;
    private javax.swing.JTextField textFieldPassword;
    private javax.swing.JTextField textFieldSearch;
    private javax.swing.JTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables
}
