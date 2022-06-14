package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        try (Connection connection = new ConnectionFactory().getConexao()) {
            String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES(?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, produto.getNome());
                ps.setString(2, produto.getDescricao());

                ps.execute();

                try (ResultSet rst = ps.getGeneratedKeys()) {
                    while (rst.next()) {
                        produto.setId(rst.getInt(1));
                    }
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {
        try (Connection connection = new ConnectionFactory().getConexao()) {
            List<Produto> produtos = new ArrayList<>();

            String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                        produtos.add(produto);
                    }
                }
            }
            return produtos;
        }
    }

    public List<Produto> buscar(Categoria ct) throws SQLException {

        System.out.println("LISTANDO PRODUTOS POR CATEGORIA"); // SOUT!!!

        try (Connection connection = new ConnectionFactory().getConexao()) {
            List<Produto> produtos = new ArrayList<>();

            String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, ct.getId());
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                        produtos.add(produto);
                    }
                }
            }
            return produtos;
        }
    }
}

