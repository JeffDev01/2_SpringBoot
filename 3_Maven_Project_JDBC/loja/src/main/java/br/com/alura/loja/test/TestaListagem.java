package br.com.alura.loja.test;

import br.com.alura.loja.factory.ConnectionFactory;

import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {


        ConnectionFactory factory= new ConnectionFactory();
        Connection connection = factory.getConexao();

        //Só uso setString se vamos inserir um parametro externo.
        PreparedStatement ps = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
        ps.execute();
        ResultSet resultSet = ps.getResultSet();

        while(resultSet.next()){
            Integer id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String descricao = resultSet.getString("descricao");
            System.out.println(id+" : "+nome+" : "+descricao);
        }





    }
}
