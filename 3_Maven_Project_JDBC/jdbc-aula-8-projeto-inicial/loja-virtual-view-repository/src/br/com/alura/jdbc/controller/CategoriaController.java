package br.com.alura.jdbc.controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.modelo.Categoria;

public class CategoriaController {

	private CategoriaDAO categoriaDAO;

	public CategoriaController(){
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.categoriaDAO = new CategoriaDAO(connection);
	}

	public List<Categoria> listar() {
		return this.categoriaDAO.listar();
	}
}
