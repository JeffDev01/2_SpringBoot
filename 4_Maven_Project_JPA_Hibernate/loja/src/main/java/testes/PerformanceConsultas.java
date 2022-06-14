package testes;

import dao.PedidoDAO;
import model.Pedido;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class PerformanceConsultas {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
//        Pedido pedido = em.find(Pedido.class, 1); //Não posso usar com LAZY, tenho q informar na QueryPlanejada

        PedidoDAO pedidoDAO = new PedidoDAO(em);
        Pedido pedido = pedidoDAO.buscarPedidoComCliente(1);
        em.close();
        System.out.println(pedido.getCliente().getDadosPessoais().getNome());




    }
}
