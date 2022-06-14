package br.com.alura.spring.data;

import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication //essa anotação le todos as anotações de spring da aplicação e as inicializam
public class SpringDataApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args); //Essa linha inicializa o framework
    }

    private Boolean system = true;
    private final CrudCargoService cargoService;
    private final CrudFuncionarioService funcionarioService;
    private final CrudUnidadeService unidadeService;
    private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

    private final RelatoriosService relatoriosService;

    //A anotação vai vereficar a existencia de um @Repository e criar umainstancia o qual já irá
    //instanciar esse objeto no construtor da SpringApplication, isso se chama injeção de dependencia

    public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService,
                                 CrudUnidadeService unidadeService, RelatoriosService relatoriosService,
                                 RelatorioFuncionarioDinamico relatorioFuncionarioDinamico
    ) {
        this.cargoService = cargoService;
        this.funcionarioService = funcionarioService;
        this.unidadeService = unidadeService;
        this.relatoriosService = relatoriosService;
        this.relatorioFuncionarioDinamico=relatorioFuncionarioDinamico;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("Qual acao voce quer executar");
            System.out.println("0-Sair");
            System.out.println("1-Cargo");
            System.out.println("2-Funcionario");
            System.out.println("3-Unidade");
            System.out.println("4-Relatorios");
            System.out.println("5-Relatorio dinâmico");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    cargoService.inicial(scanner);
                    break;
                case 2:
                    funcionarioService.inicial(scanner);
                    break;
                case 3:
                    unidadeService.inicial(scanner);
                    break;
                case 4:
                    relatoriosService.inicial(scanner);
                    break;
                case 5:
                    relatorioFuncionarioDinamico.inicial(scanner);
                    break;
                default:
                    system = false;
            }

        }
    }
}
