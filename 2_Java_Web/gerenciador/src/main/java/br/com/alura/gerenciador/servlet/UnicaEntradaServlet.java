package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.alura.gerenciador.acao.Acao;
import br.alura.gerenciador.acao.AlteraEmpresa;
import br.alura.gerenciador.acao.FormNovaEmpresa;
import br.alura.gerenciador.acao.ListaEmpresas;
import br.alura.gerenciador.acao.MostraEmpresa;
import br.alura.gerenciador.acao.NovaEmpresa;
import br.alura.gerenciador.acao.RemoveEmpresa;

//@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao");
		
		String nomeClasse = "br.alura.gerenciador.acao." + paramAcao;

		String nome;
		try {
			Class classe = Class.forName(nomeClasse);// Carrega a classe com o nome do paramAcao
			Acao acao = (Acao) classe.newInstance();
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}

		String[] tipoEEndereco = nome.split(":");

		if (tipoEEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
			rd.forward(request, response);
		} else {

			response.sendRedirect(tipoEEndereco[1]);
		}


	}

}
