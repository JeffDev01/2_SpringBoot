package br.com.alura.testes;

import br.com.alura.dao.CategoriaDAO;
import br.com.alura.dao.CarroDAO;
import br.com.alura.modelo.Carro;
import br.com.alura.modelo.Categoria;
import br.com.alura.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroVeiculoTest {

    public static void main(String[] args) {

/*        //cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        CarroDAO carroDAO = new CarroDAO(em);


        //Busca pelo id, find() para apenas um objeto
        Integer id = 5;
        Carro c = carroDAO.buscarPorId(id);
        System.out.println("BUSCA DO CARRO DO ID: "+id);
        System.out.println(c.getNome()+" "+c.getDescricao()+" : R$/hora="+c.getPrecoHora());

        //Para busca de listas
        List<Carro> todosCarros = carroDAO.buscarTodos();
        todosCarros.stream().forEach(c2 -> System.out.println(c2.getNome()));

        //Para busca de listas
        List<Carro> carrosPorNome = carroDAO.buscarPorNome("Uno");
        carrosPorNome.stream().forEach(c3 -> System.out.println(c3.getNome()));

        //Por InnerJoin
        System.out.println("POR CATEGORIA");
        List<Carro> carrosPorCategoria = carroDAO.buscaPorCategoria("SUV");
        carrosPorCategoria.stream().forEach(c3 -> System.out.println(c3.getNome()));

        //Busca de apenas um parametro do registro
        System.out.println("BUSCA APENAS DE UM VALOR DO REGISTRO");
        BigDecimal  precoProduto = carroDAO.buscarPrecoDoProdutoComNome(8);
        System.out.println(precoProduto);*/

        cadastrarProduto();

    }

    private static void cadastrarProduto() {
        Categoria categoria= new Categoria();
        categoria.setId(2);
        Carro carro = new Carro("Ferrari","Turbo", new BigDecimal("500.00"), categoria);

        EntityManager em = JPAUtil.getEntityManager();

        CarroDAO veiculoDAO = new CarroDAO(em);

        em.getTransaction().begin();
        veiculoDAO.cadastrar(carro);
        em.getTransaction().commit();
        em.close();

    }

}
