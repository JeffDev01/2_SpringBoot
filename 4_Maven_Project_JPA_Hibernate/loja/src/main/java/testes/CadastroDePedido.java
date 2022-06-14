package testes;

import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import model.*;
import util.JPAUtil;
import vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {


        EntityManager em = JPAUtil.getEntityManager();

        //1)Criando cliente e cadastrando;
        em.getTransaction().begin();
        Cliente cliente = new Cliente("Lucas","111.111.111-11");
        ClienteDAO clienteDAO = new ClienteDAO(em);
        clienteDAO.cadastrar(cliente);
        em.getTransaction().commit();


        //Buscado o produto na tabela de produtos
        em.getTransaction().begin();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        Produto produto = produtoDAO.buscarPorId(2);
        em.getTransaction().commit();

        //Criando um pedido, como é bidirecional tenho que fazer o O ItemPedido saber que tem um pedido
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(20,  produto, pedido));

        //Cadastrando no DB o pedido;
        em.getTransaction().begin();
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        pedidoDAO.cadastrar(pedido);
        em.getTransaction().commit();


        BigDecimal  totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println(totalVendido);

        //USANDO UM OBJETO GENERICO
//        List<Object[]> relatorio = pedidoDAO.relatorioDeVendas();
//        for(Object[] obj : relatorio){
//            System.out.println(obj[0]);
//            System.out.println(obj[1]);
//            System.out.println(obj[2]);
//        }

        //USANDO UMA CLASSE VALOR OBJETO
        List<RelatorioDeVendasVo> relatorio = pedidoDAO.relatorioDeVendas();
        for(RelatorioDeVendasVo r : relatorio){
            System.out.println(r);
        }


    }

    private static void cadastrarProduto() {
        Categoria categoria = new Categoria();
        categoria.setId(1);
        LocalDateTime data = LocalDateTime.now();
        Produto produto = new Produto("Redmi Note 6 Pro", "Com 8G",
                new BigDecimal("950.00"), LocalDateTime.now(), categoria);

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        em.getTransaction().begin();
        produtoDAO.cadastrar(produto);
        em.getTransaction().commit();
        em.close();

    }
}
