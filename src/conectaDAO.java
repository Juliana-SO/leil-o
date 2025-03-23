
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    private static final String URL = "jdbc:mysql://localhost:3306/produtos?useSSL=false"; 
    private static final String USER = "root";
    private static final String PASSWORD = "@Juli4321";

    public Connection connectDB() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão bem-sucedida!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + erro.getMessage());
        }

        return conn;
    }
    
}
