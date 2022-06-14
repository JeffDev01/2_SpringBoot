package br.com.alura.loja.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;


public class ConnectionFactory {

    public DataSource dataSource;


    //Baixei duas dependencias para poder criar a classe que ira gerar a pool de conexões
    public ConnectionFactory() { //Quando eu crio um objeto ele ja estancia essas informações.
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("");

        //setar numero maximo de conexões
        comboPooledDataSource.setMaxPoolSize(50);

        this.dataSource = comboPooledDataSource;
    }

    public Connection getConexao() throws SQLException {

        return this.dataSource.getConnection();
    }
}
