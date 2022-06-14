package vo;

import java.time.LocalDateTime;

public class RelatorioDeVendasVo {

    private String nomeProduto;
    private Long quantidadeVentida;
    private LocalDateTime dataUltimaVenda;

    public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVentida, LocalDateTime dataUltimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVentida = quantidadeVentida;
        this.dataUltimaVenda = dataUltimaVenda;
    }

    public RelatorioDeVendasVo() {
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVentida=" + quantidadeVentida +
                ", dataUltimaVenda=" + dataUltimaVenda +
                '}';
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidadeVentida() {
        return quantidadeVentida;
    }

    public LocalDateTime getDataUltimaVenda() {
        return dataUltimaVenda;
    }
}
