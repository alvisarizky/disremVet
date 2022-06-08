/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klinikku;

import mainKlinikKu.Koneksi;
import mainKlinikKu.WelcomePage;
/**
 *
 * @author alvis
 */
public class KlinikKu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Koneksi Koneksi = new Koneksi();
        System.err.println(Koneksi.getKoneksiDatabase());
        WelcomePage welcome = new WelcomePage();
        welcome.setVisible(true);
    }
    
}
