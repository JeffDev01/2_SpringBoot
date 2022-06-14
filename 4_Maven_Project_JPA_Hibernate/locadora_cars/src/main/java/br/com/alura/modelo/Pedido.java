package br.com.alura.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido") //quando é maiusculo, nao precisa nomear, ele entende
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime data;
    private BigDecimal valorTotal;//quando tem camel case ele ja separa por anderline

    @ManyToOne
    private Cliente cliente;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido")
    private List<ItemPedido> itens;



    public Pedido(LocalDateTime data, BigDecimal valorTotal, Cliente cliente) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
    }

    public Pedido() {
    }


    public List<ItemPedido> getPedidos() {
        return itens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
