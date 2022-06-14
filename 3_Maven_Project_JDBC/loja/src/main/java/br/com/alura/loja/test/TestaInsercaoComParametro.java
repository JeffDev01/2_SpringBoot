package br.com.alura.loja.test;

import br.com.alura.loja.factory.ConnectionFactory;

import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection connection = connectionFactory.getConexao()) {

            connection.setAutoCommit(false);//assim tenho controle sobre a transação

            try (
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)",
                            Statement.RETURN_GENERATED_KEYS)) {

                adicionarVariavel("SmartTV", "45 polegadas", ps);
                adicionarVariavel("Radio", "Radio de bateria", ps);

                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLPACK EXECUTADO");
                connection.rollback();
            }
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement ps) throws SQLException {
        ps.setString(1, nome);
        ps.setString(2, descricao);

//        if(nome.equals("Radio")){
//            throw new RuntimeException("Não foi possivel adicionar o produto");
//        }

        ps.execute();
        try (ResultSet rst = ps.getGeneratedKeys()) {
            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }
    }

}
