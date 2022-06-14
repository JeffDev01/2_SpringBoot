package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

@WebServlet("/empresas")
public class EmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Empresa> listaEmpresas = new Banco().getEmpresas();

		String valor = request.getHeader("Accept");

		if (valor.endsWith("xml")) {
			
			// COM XML
			XStream xstream = new XStream();
			xstream.alias("empresa", Empresa.class);
			String xml = xstream.toXML(listaEmpresas);

			response.setContentType("application/xml");
			response.getWriter().print(xml);
			
		} else if (valor.contains("json")) {

			// COM JSON
			Gson gson = new Gson();
			String json = gson.toJson(listaEmpresas);

			response.setContentType("application/json");
			response.getWriter().print(json);

		} else {
			response.setContentType("application/xml");
			response.getWriter().print("{'message: no content'}");
			
			
		}

	}

}
