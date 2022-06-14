package br.com.alura.loja.test;

import br.com.alura.loja.factory.ConnectionFactory;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Produto;

import java.sql.*;
import java.util.List;

public class TestaInsecaoComProduto {

    public static void main(String[] args) throws SQLException {

        Produto comoda = new Produto("Comoda", "Comoda Vertical");

            try(Connection connection = new ConnectionFactory().getConexao()){
               ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                produtoDAO.salvar(comoda);

               List<Produto> listaProdutos = produtoDAO.listar();
               listaProdutos.stream().forEach(lp -> System.out.println(lp));
            }

        System.out.println(comoda);

    }
}
