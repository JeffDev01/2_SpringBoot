package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Unidade;
import br.com.alura.spring.data.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeService {

    private final UnidadeRepository unidadeRepository;

    private Boolean system = true;

    public CrudUnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("Qual acao de Unidade deseja executar");
            System.out.println("0-Sair");
            System.out.println("1-Salvar");
            System.out.println("2-Atualizar");
            System.out.println("3-Visualizar");
            System.out.println("4-Deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;

            }
        }
    }

    private void salvar(Scanner scanner) {
        Unidade unidade = new Unidade();

        System.out.println("Descricao do unidade:");
        String descricao = scanner.next();
        unidade.setDescricao(descricao);

        System.out.println("URL da unidade: ");
        unidade.setEndereco(scanner.next());

        unidadeRepository.save(unidade);
        System.out.println("Salvo!");

    }

    private void atualizar(Scanner scanner) {
        System.out.println("Id do unidade a ser atualizado:");
        Unidade unidade = new Unidade();
        unidade.setId(scanner.nextInt());
        System.out.println("Descricao:");
        String descricao = scanner.next();
        unidade.setDescricao(descricao);
        unidadeRepository.save(unidade);
        System.out.println("Atualizado!");

    }

    private void visualizar() {
        Iterable<Unidade> unidades = unidadeRepository.findAll();
        unidades.forEach(unidade -> System.out.println(unidade));

    }

    private void deletar(Scanner scanner) {
        System.out.println("Id:");
        int id = scanner.nextInt();
        unidadeRepository.deleteById(id);
        System.out.println("Deletado!");
    }


}
