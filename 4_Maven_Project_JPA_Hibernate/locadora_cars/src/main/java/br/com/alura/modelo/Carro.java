package br.com.alura.modelo;

import javax.persistence.*;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


//FAZENDO UM MAPEAMENTO DE UMA ENTIDADE
@Entity
@Table(name="carro")
public class Carro {

    //Mapeando as coludas
    @Id             //anotation indicando que esse é o id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //indicando que é auto_increment e o modo que sera feito
    private Integer id;
    private String nome;
    private String descricao;
    @Column(name = "preco_hora")
    private BigDecimal precoHora;
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @ManyToOne
    private Categoria categoria;


    public Carro(Integer id, String nome, String descricao, BigDecimal precoHora, LocalDateTime dataCadastro, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoHora = precoHora;
        this.dataCadastro = dataCadastro;
        this.categoria = categoria;
    }

    public Carro(String nome, String descricao, BigDecimal precoHora, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoHora = precoHora;
        this.categoria = categoria;
    }

    public Carro() {
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoHora() {
        return precoHora;
    }

    public void setPrecoHora(BigDecimal precoHora) {
        this.precoHora = precoHora;
    }
}
