package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    @Autowired
    private CargoRepository cargoRepository;

    private Boolean system = true;


    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("Qual acao de Cargo deseja executar");
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
        System.out.println("Descricao do cargo:");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo!");

    }

    private void atualizar(Scanner scanner) {
        System.out.println("Id do cargo a ser atualizado:");
        Cargo cargo = new Cargo();
        cargo.setId(scanner.nextInt());
        System.out.println("Descricao:");
        String descricao = scanner.next();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo); //save tb atualiza pelo id;
        System.out.println("Atualizado!");

    }

    private void visualizar() {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));

    }

    private void deletar(Scanner scanner) {
        System.out.println("Id:");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Deletado!");
    }


}
