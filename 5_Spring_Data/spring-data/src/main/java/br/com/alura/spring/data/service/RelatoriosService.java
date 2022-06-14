package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private Boolean system = true;

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("Qual acao de Cargo deseja executar");
            System.out.println("0-Sair");
            System.out.println("1-Busca funcionario por nome");
            System.out.println("2-Pesquisa funcionarios salarios");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioSalario();
                    break;

                default:
                    system = false;
                    break;

            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar: ");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioSalario() {
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Id: " + f.getId() + " | Funcionario: " + f.getNome() + " | Salario: " + f.getSalario()));
    }

}
