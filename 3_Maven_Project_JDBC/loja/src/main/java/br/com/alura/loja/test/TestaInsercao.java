package br.com.alura.loja.test;

import br.com.alura.loja.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.getConexao();

        Statement stm = connection.createStatement();

        stm.execute("INSERT INTO produto (nome, descricao) VALUES ('Mouse', 'Sem fio (bluetooth)')"
        , Statement.RETURN_GENERATED_KEYS); //Alem de inserir ele já returna qual o id criado.

        ResultSet rst = stm.getGeneratedKeys();//Pega o id do produto gerado
        while(rst.next()){
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi: "+id);
        }

    }
}
