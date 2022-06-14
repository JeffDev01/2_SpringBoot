package br.com.alura.mudi.dto;

import br.com.alura.mudi.orm.Pedido;
import br.com.alura.mudi.orm.StatusPedido;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequisicaoNovoPedido {
    @NotBlank
    private String nomeProduto;
    @NotBlank
    private String urlProduto;
    @NotBlank
    private String urlImagem;

    private String descricao;

    public Pedido toPedido(){
        Pedido pedido = new Pedido();
        pedido.setNomeProduto(this.nomeProduto);
        pedido.setDescricao(this.descricao);
        pedido.setUrlImagem(this.urlImagem);
        pedido.setUrlProduto(this.urlProduto);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        return pedido;
    }

}
