package br.com.alura.loja.test;

import br.com.alura.loja.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.getConexao();

        PreparedStatement ps = connection.prepareStatement("DELETE FROM produto WHERE ID > ?");
        ps.setInt(1, 2);
        ps.execute();

        Integer linhasModificadas = ps.getUpdateCount();//Retorna o numero de linhas que ele vai auterar;
        System.out.println("Quandidade de linhas modificadas: "+linhasModificadas);
    }
}
