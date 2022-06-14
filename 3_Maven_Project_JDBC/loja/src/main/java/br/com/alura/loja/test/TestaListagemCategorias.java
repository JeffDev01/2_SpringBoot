package br.com.alura.loja.test;

import br.com.alura.loja.dao.CategoriaDao;;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.factory.ConnectionFactory;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

import java.util.List;
import java.sql.*;

public class TestaListagemCategorias {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = new ConnectionFactory().getConexao()) {
            System.out.println("Executando a query de listar categoria"); // SOUT!!!
            CategoriaDao categoriaDao = new CategoriaDao(connection);
            List<Categoria> listaDeCategorias = categoriaDao.listarComProdutos();

            listaDeCategorias.stream().forEach(ct -> {
                System.out.println(ct.getNome());

            });
        }
    }
}
