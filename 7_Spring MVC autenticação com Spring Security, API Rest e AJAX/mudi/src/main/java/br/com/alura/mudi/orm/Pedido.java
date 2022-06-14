package br.com.alura.mudi.orm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeProduto;
    private String urlProduto;
    private String urlImagem;
    private String descricao;
    private BigDecimal valorNegociado;
    private LocalDate dataEntrega;
    private String comentario;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }



    public Integer getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getUrlProduto() {
        return urlProduto;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorNegociado() {
        return valorNegociado;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public StatusPedido getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(nomeProduto, pedido.nomeProduto) && Objects.equals(urlProduto, pedido.urlProduto) && Objects.equals(urlImagem, pedido.urlImagem) && Objects.equals(descricao, pedido.descricao) && Objects.equals(valorNegociado, pedido.valorNegociado) && Objects.equals(dataEntrega, pedido.dataEntrega) && status == pedido.status && Objects.equals(user, pedido.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeProduto, urlProduto, urlImagem, descricao, valorNegociado, dataEntrega, status, user);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorNegociado(BigDecimal valorNegociado) {
        this.valorNegociado = valorNegociado;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
