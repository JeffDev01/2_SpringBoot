package br.com.alura.dao;


import br.com.alura.model.Veiculo;

import javax.persistence.EntityManager;

public class VeiculoDAO {


    EntityManager em;

    public VeiculoDAO(EntityManager em){
        this.em = em;
    }

    //PERSISTIR
    public void cadastrar(Veiculo veiculo){
        this.em.persist(veiculo);
    }



}
