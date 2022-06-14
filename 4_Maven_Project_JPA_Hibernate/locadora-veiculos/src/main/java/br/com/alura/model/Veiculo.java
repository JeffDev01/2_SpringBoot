package br.com.alura.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "veiculo")
public class Veiculo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;

    @Column(name = "preco_hora")
    private BigDecimal precoHora;

    @Column(name ="data_cadastro")
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne
    private Categoria categoria;

    public Veiculo() {
    }

    public Veiculo(String nome, String descricao, BigDecimal precoHora, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoHora = precoHora;
        this.categoria = categoria;
    }



    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
