package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//VOCE PODE COLOCAR AS CONSULTAS DIRETO NA SUA ENTIDADE
@NamedQuery(name="Produto.produtosPorCategoria", query= "SELECT p FROM Produto p WHERE p.categoria.nome= :nome")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itens = new ArrayList<>();

    public Produto(String nome, String descricao, BigDecimal precoUnitario, LocalDateTime dataCadastro, Categoria categoria) {

        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.dataCadastro = dataCadastro;
        this.categoria = categoria;
    }

    public Produto() {
    }

    //ADICIONANDO ITEM
    public void adicionarItem(ItemPedido item) {
        item.setProduto(this);
        this.itens.add(item);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", dataCadastro=" + dataCadastro +
                ", categoria=" + categoria +
                ", itens=" + itens +
                '}';
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

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
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
}
