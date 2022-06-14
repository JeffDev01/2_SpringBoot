package br.com.alura.gerenciador.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {
	
	private static List<Empresa> lista = new ArrayList<>();
	private static List<Usuario> listaUsuarios = new ArrayList<>();
	private static Integer chaveSequencial =1;
	
	
	static {
		Usuario u1 = new Usuario();
		u1.setLogin("a");
		u1.setSenha("a");
		
		Usuario u2 = new Usuario();
		u2.setLogin("b");
		u2.setSenha("b");
		
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
		
	}

	public void adiciona(Empresa empresa) {
		empresa.setId(chaveSequencial++);
		Banco.lista.add(empresa);
		
	}
	
	public List<Empresa> getEmpresas(){
		return Banco.lista;
	}

	public void removeEmpresa(Integer id) {
		
		Iterator<Empresa> it = lista.iterator();
		while(it.hasNext()) {
			Empresa empresa = it.next();
			if(empresa.getId() == id) {
				it.remove();
			}
		}
		
	}

	public Empresa buscaEmpresaPeloId(Integer id) {
		for(Empresa empresa : lista) {
			if(empresa.getId()==id) {
				return empresa;			}
		}
		return null;

	}

	public Usuario existeUsuario(String login, String senha) {
		for(Usuario usuario : listaUsuarios) {
			if(usuario.ehIgual(login, senha)) {
				return usuario;
			}
		}
		return null;
	}

}
