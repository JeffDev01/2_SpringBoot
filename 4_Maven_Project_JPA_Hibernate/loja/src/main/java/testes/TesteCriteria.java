package testes;

import dao.ProdutoDAO;
import model.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TesteCriteria {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        List<Produto> produtos = produtoDAO.buscarPorParametrosComCriteria("Redmi not 6 pro", null, null);
        for(Produto produto : produtos){
            System.out.println(produto.getNome());
        }


    }
}
