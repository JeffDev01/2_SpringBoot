package dao;

import model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        em.persist(produto);
    }

    public List<Produto> buscaPorNomeDaCategoria(String nome) {
        return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public Produto buscarPorId(Integer id) {
        return em.find(Produto.class, id);
    }

    // PARAMETROS DINAMICOS SOLUÇÃO TRADICIONAL, MAS EXISTE RECURSO MELHOR - CRITERIA API
    public List<Produto> buscarPorParametros(String nome, BigDecimal precoUnitario, LocalDateTime dataCadastro) {
        String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
        if (nome != null && !nome.trim().isEmpty()) {
            jpql += "AND p.nome = :nome";
        }
        if (precoUnitario != null) {
            jpql += " AND p.precoUnitario = :preco";
        }
        if (dataCadastro != null) {
            jpql += "AND p.dataCadastro = :dataCadastro";
        }
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);
        }
        if (precoUnitario != null) {
            query.setParameter("precoUnitario", precoUnitario);
        }
        if (dataCadastro != null) {
            query.setParameter("dataCadastro", dataCadastro);
        }
        return query.getResultList();
    }


    //PESQUISA DINAMICA COM CRITERIA API
    public List<Produto> buscarPorParametrosComCriteria(String nome,
                                                        BigDecimal precoUnitario, LocalDateTime dataCadastro) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = builder.and();

        if (nome != null && !nome.trim().isEmpty()) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
        }
        if (precoUnitario != null) {
            filtros = builder.and(filtros, builder.equal(from.get("precoUnitario"), precoUnitario));
        }
        if (dataCadastro != null) {
            filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
        }
        query.where(filtros);

        return em.createQuery(query).getResultList();

    }


}


