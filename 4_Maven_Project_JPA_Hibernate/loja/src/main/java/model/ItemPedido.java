package model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="item_pedido")
public class ItemPedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="preco_unitario")
    private BigDecimal precoUnitario;
    private Integer quantidade;


    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    public ItemPedido(Integer quantidade, Produto produto, Pedido pedido) {
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.precoUnitario = produto.getPrecoUnitario();
        this.produto = produto;
    }

    public ItemPedido() {
    }

    //FAZ A MULTIPLICAÇÃO DA QUANTIDADE PELO PREÇO ATUAL ANTES DE COLOCAR NO PEDIDO
    public BigDecimal getValor() {
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
