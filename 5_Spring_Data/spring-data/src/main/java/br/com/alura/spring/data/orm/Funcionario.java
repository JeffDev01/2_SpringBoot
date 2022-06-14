package br.com.alura.spring.data.orm;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "unidades")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private BigDecimal salario;
    @Column(name = "data_contratacao")
    private LocalDate dataContratacao;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;



    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "unidades_funcionarios",
            joinColumns = @JoinColumn(name="funcionario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="unidade_id", referencedColumnName = "id")
    )
    private List<Unidade> unidades;

    public void addUnidade(Unidade unidade){
        if(unidades==null){
            unidades = new ArrayList<>();
        }
        unidades.add(unidade);
    }





}
