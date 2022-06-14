package dao;

import factory.ConnectionFactory;
import model.Produto;

import java.sql.*;

public class ProdutoDAO {


    public void inserir(Produto produto)  {

        String sql = "INSERT INTO produto (nome, descricao) VALUES(?,?);";

        try (Connection connection = new ConnectionFactory().getConexao();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
