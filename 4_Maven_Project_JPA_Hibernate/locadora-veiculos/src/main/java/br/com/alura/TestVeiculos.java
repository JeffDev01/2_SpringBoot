package br.com.alura;

import br.com.alura.dao.VeiculoDAO;
import br.com.alura.model.Categoria;
import br.com.alura.model.Veiculo;
import br.com.alura.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class TestVeiculos {

    public static void main(String[] args) {

        Categoria categoria = new Categoria();
        categoria.setId(1);

        Veiculo veiculo = new Veiculo("I30", "Injeção eletrônica",new BigDecimal("230.00"),categoria );

        EntityManager em = JPAUtil.getEntityManager();
        VeiculoDAO veiculoDAO = new VeiculoDAO(em);

        em.getTransaction().begin();
        veiculoDAO.cadastrar(veiculo);
        em.getTransaction().commit();
        em.close();

    }
}
