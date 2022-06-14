package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.Unidade;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private CargoRepository  cargoRepository;
    @Autowired
    private UnidadeRepository unidadeRepository;



    private Boolean system = true;
    private final DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public void inicial(Scanner scanner) throws ParseException {

        while (system) {
            System.out.println("Qual acao do Funcionario deseja executar");
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
                    visualizar(scanner);
                    break;
                case 4:
                    deletar(scanner);
                    break;
                case 0:
                    system=false;
                    break;
                default:
                    break;

            }
        }
    }



    private void salvar(Scanner scanner) throws ParseException {
        System.out.println("Nome:");
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(scanner.next());

        System.out.println("Id do cargo:");
        Cargo cargo = new Cargo();
        cargo.setId(scanner.nextInt());
        funcionario.setCargo(cargo);

        System.out.println("Adicionar Unidades de Trabalho:");
        unidadesDeTrabalho(scanner, funcionario);

        System.out.println("CPF:");
        String cpf = scanner.next();
        funcionario.setCpf(cpf);

        System.out.println("Salario: ");
        BigDecimal salario = new BigDecimal(scanner.nextDouble());
        funcionario.setSalario(salario);

        System.out.println("Data de contratação (formato dd/MM/yyyy):");
        String data = scanner.next();
        LocalDate dataContratação;
        dataContratação = LocalDate.parse(data, formatter);
        funcionario.setDataContratacao(dataContratação);


        funcionarioRepository.save(funcionario);
        System.out.println("Salvo!");

    }

    private void atualizar(Scanner scanner) throws ParseException {
        System.out.println("Id do funcionario a ser atualizado:");
        Funcionario funcionario = new Funcionario();
        funcionario.setId(scanner.nextInt());

        System.out.println("Nome:");
        String nome = scanner.next();
        funcionario.setNome(nome);

        System.out.println("CPF:");
        String cpf = scanner.next();
        funcionario.setCpf(cpf);

        System.out.println("Salario: ");
        BigDecimal bigDecimal = scanner.nextBigDecimal();
        funcionario.setSalario(bigDecimal);


        funcionarioRepository.save(funcionario); //save tb atualiza pelo id;
        System.out.println("Atualizado!");

    }

    //VISUALIZAR COM PAGINAÇÃO
    private void visualizar(Scanner scanner) {
        System.out.println("Qual pagina você deseja visualizar: ");
        Integer page = scanner.nextInt();

        Pageable pageable= PageRequest.of(page, 3, Sort.by(Sort.Direction.ASC,"salario"));

        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
        System.out.println("Quantidade de paginas: "+funcionarios);
        System.out.println("Pagina atual: "+funcionarios.getNumber());
        System.out.println("Quantidade total: "+funcionarios.getTotalElements());
        funcionarios.forEach(funcionario -> System.out.println(funcionario));

    }

    private void deletar(Scanner scanner) {
        System.out.println("Id:");
        int id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Deletado!");
    }

    private void unidadesDeTrabalho(Scanner scanner, Funcionario funcionario){
        boolean isTrue = true;
        List <Unidade> unidades = new ArrayList<>();

        while(isTrue){
            System.out.println("Digite o id_unidade ou 0 para sair:");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId !=0){
                Optional<Unidade> unidade = unidadeRepository.findById(unidadeId);
                funcionario.addUnidade(unidade.get());
            }else{
                isTrue=false;
            }
        }
    }





}
