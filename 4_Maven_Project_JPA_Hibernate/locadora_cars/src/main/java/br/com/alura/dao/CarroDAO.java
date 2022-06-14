package br.com.alura.dao;

import br.com.alura.modelo.Carro;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CarroDAO {


    private EntityManager em;

    public CarroDAO(EntityManager em){
        this.em = em;
    }


    //CADASTRAR
    public void   cadastrar(Carro carro){
        this.em.persist(carro);//ele ja sabe em qual table pela anotação
    }

    //UPDATE
    public void atualizar(Carro carro){
        this.em.merge(carro);
    }

    // BUSCA POR ID
    public Carro buscarPorId(Integer id){
       return em.find(Carro.class, id);

    }

    //BUSCAR TODOS DE UMA ENTIDADE
    public List<Carro> buscarTodos(){
        String jpql = "SELECT c FROM Carro c"; //Selecione todos o objeto p da entidade(tabela) Produto
        return em.createQuery(jpql, Carro.class).getResultList();
    }

    //BUSCAR VARIOS POR ALGUM FILTRO
    public List<Carro> buscarPorNome(String nome){
        String jpql = "SELECT c FROM Carro c WHERE c.nome = ?1"; //Selecione todos o objeto p da entidade(tabela) Carro
        return em.createQuery(jpql, Carro.class)
                .setParameter(1, nome) //setar o parametro dinamico depois do :nome (named paramether)
                .getResultList();
    }

    //BUSCA FAZERNDO UMA INNERJOIN
    public List<Carro> buscaPorCategoria(String nome){
        String jpql = "SELECT c FROM Carro c WHERE c.categoria.nome = ?1";
        return em.createQuery(jpql, Carro.class)
                .setParameter(1, nome)
                .getResultList();
    }

    //BUSCAR APENAS UM ATRIBUTO EM VEZ DA ENTIDADE INTEIRA
    public BigDecimal buscarPrecoDoProdutoComNome(Integer id){
        String jpql = "SELECT c.precoHora FROM Carro c WHERE c.id = ?1";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter(1, id)
                .getSingleResult();
    }


}
