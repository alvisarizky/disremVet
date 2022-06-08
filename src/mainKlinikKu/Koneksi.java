/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainKlinikKu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alvis
 */
public class Koneksi {
    private Connection koneksiDb;
    public Connection getKoneksiDatabase() {
        if (koneksiDb == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver Ditemukan");
                try {
                    koneksiDb = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_klinik_ku","root","");
                    System.out.println("Koneksi Database Berhasil");
                } catch (SQLException e) {
                    System.err.println("Koneksi Databse Gagal : \n Dengan Pesan : " + e.toString());
                }
            } catch (ClassNotFoundException e) {
                System.out.print(" Class Driver database tidak di temukan : \n dengan pesan Error : " + e.toString());
            }
        }
        return koneksiDb;
    }
}
