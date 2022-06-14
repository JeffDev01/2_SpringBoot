package br.com.alura.spring.data.specification;

import br.com.alura.spring.data.orm.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;


public class SpecificationFuncionario {

    //QUERIES DINAMICAS COM SPECIFICATION E CRITERIA
    //filtro por nome ou que tenha esse trecho
    public static Specification<Funcionario> nome(String nome){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"), "%"+nome+"%"));
    }

    //filtro por cpf tem que ser exatamente igual
    public static Specification<Funcionario> cpf(String cpf){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpf"), "%"+cpf+"%"));
    }

    //salario maior que o passado
    public static Specification<Funcionario> salario(Double salario){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("salario"),salario));
    }

    //data de contratação levando em conta a data especifica
    public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("nome"), dataContratacao));
    }

}
