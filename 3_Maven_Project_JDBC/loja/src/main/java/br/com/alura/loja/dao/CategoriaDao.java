package br.com.alura.loja.dao;

import br.com.alura.loja.factory.ConnectionFactory;
import br.com.alura.loja.modelo.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    private Connection connection;

    public CategoriaDao(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();

        System.out.println("CATEGORIA:"); // SOUT!!!

        String sql = "SELECT ID, NOME FROM CATEGORIA";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Categoria categoria =
                            new Categoria(rst.getInt(1), rst.getString(2));
                    categorias.add(categoria);

                }
            }

        }
        return categorias;
    }

    public List<Categoria> listarComProdutos() throws SQLException{

        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.descricao " +
                "FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID=P.CATEGORIA_ID ORDER BY C.ID";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Categoria categoria =
                            new Categoria(rst.getInt(1), rst.getString(2));
                    categorias.add(categoria);

                }
            }

        }
        return categorias;
    }
}
