package br.com.DAO;

import br.com.DTO.UsuarioDTO;
import java.sql.*;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public boolean inserirUsuario(UsuarioDTO objUsuarioDTO) {
        String sql = "INSERT INTO tb_usuarios(id_usuario, usuario, login, senha) VALUES(?, ?, ?, ?)";
        conexao = new ConexaoDAO().conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getId_usuario());
            pst.setString(2, objUsuarioDTO.getNomeUsuario());
            pst.setString(3, objUsuarioDTO.getLoginUsuario());
            pst.setString(4, objUsuarioDTO.getSenhaUsuario());

            pst.execute();
            pst.close();
            return true; // Inserção bem-sucedida

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir usuário: " + e);
            return false; // Falha na inserção
        }
    }
}

