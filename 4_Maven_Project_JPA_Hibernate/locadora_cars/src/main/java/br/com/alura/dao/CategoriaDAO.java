package br.com.alura.dao;


import br.com.alura.modelo.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDAO {


    private EntityManager em;

    public CategoriaDAO(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }
}
