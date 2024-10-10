package br.com.DAO;

import br.com.DTO.UsuarioDTO;
import br.com.Views.TelaUsuario;
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

    public void editar(UsuarioDTO objUsuarioDTO) {
        String sql = "update tb_usuarios set usuario = ?, login = ?, senha = ? where id_usuario = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(4, objUsuarioDTO.getId_usuario());
            pst.setString(1, objUsuarioDTO.getNomeUsuario());
            pst.setString(2, objUsuarioDTO.getLoginUsuario());
            pst.setString(3, objUsuarioDTO.getSenhaUsuario());

            int add = pst.executeUpdate();
            if (add > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Usuario editado com sucesso");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método editar " + e);
        }
    }

    public void apagar(UsuarioDTO objUsuarioDTO) {
        String sql = "delete from tb_usuarios where id_usuario = ?";
        conexao = ConexaoDAO.conector();

        try {

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getId_usuario());
            int add = pst.executeUpdate();
            if (add > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método apagar " + e);
        }

    }

}
