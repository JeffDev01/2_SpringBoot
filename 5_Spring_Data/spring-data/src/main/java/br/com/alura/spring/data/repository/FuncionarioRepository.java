package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario,Integer>,
        JpaSpecificationExecutor<Funcionario> {

    //QUERY DERIVADA
    List<Funcionario> findByNome(String nome);


    //QUERY PAGINADA COM PROJEÇÃO, ou seja só com os campos que queremos
    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionario f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();











}
