package br.com.alura.loja.test;

import br.com.alura.loja.factory.ConnectionFactory;

import java.sql.SQLException;

public class TestaPoolConexoes {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        for(int i = 0 ;i<20; i++){
            connectionFactory.getConexao();
            System.out.println("Conexão de número: "+i);
        }

    }
}
