/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class write {
    public static void write(String kantin,String nama,String makanan,String waktu){
        String url = "jdbc:mysql://127.0.0.1:3306/byos_network_javaers";
        String user = "root";
        String password = "";
                String sql = "INSERT INTO " + kantin + " (nama, makanan, waktu) VALUES (?, ?, ?)";
                
                try(Connection con = DriverManager.getConnection(url, user, password);
                    PreparedStatement p = con.prepareStatement(sql))
                {
                
                p.setString(1, nama);
                p.setString(2, makanan);
                p.setString(3, waktu);
                
                p.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dimasukkan");
                }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
                }
                
    }
}
