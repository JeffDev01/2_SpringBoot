package dao;

import model.Pedido;
import vo.RelatorioDeVendasVo;


import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {

    private EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        em.persist(pedido);
    }

    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    public List<RelatorioDeVendasVo> relatorioDeVendas() {
        String jpql = "SELECT new vo.RelatorioDeVendasVo(" //aqui tenho que colocar o indereço inteiro do pacote
                + "produto.nome,"
                + "SUM(item.quantidade),"
                + "MAX(pedido.data))"
                + "FROM Pedido pedido "
                + "JOIN pedido.itens item "
                + "JOIN item.produto produto "
                + "GROUP BY produto.nome "
                + "ORDER BY item.quantidade DESC";

        return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
    }

    public Pedido buscarPedidoComCliente(Integer id){
            return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente where  p.id = :id", Pedido.class)
                    .setParameter("id",id)
                    .getSingleResult();


    }


}
