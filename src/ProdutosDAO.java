/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void venderProduto(String nome) {
    Connection conn = new conectaDAO().connectDB();
    String sqlSelect = "SELECT id FROM produtos WHERE nome LIKE ?";
    String sqlUpdate = "UPDATE produtos SET status = ? WHERE id = ?";
    
    try {
        PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);
        stmtSelect.setString(1, "%" + nome + "%");
        ResultSet rs = stmtSelect.executeQuery();
        
        if (rs.next()) {
            int idProduto = rs.getInt("id");  
            
            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1, "Vendido");
            stmtUpdate.setInt(2, idProduto);
            int rowsUpdated = stmtUpdate.executeUpdate();
            
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Nenhum produto atualizado!");
            }
            
            stmtUpdate.close();
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
        }

        stmtSelect.close();
        conn.close();
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender o produto: " + e.getMessage());
    }
}

    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos( nome, valor, status) VALUES "
                + "( ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir Produto: " + e.getMessage());
        }

    }

    public List<ProdutosDTO> listarProdutos() {
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<ProdutosDTO> listaP = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                listaP.add(p);
            }

            return listaP;
        } catch (Exception e) {
            return null;
        }

    }
    public List<ProdutosDTO> listarProdutosVendidos() {
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos WHERE status LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" +"Vendido"+ "%");
            ResultSet rs = stmt.executeQuery();
            List<ProdutosDTO> listaP = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                listaP.add(p);
            }

            return listaP;
        } catch (Exception e) {
            return null;
        }

    }

}
