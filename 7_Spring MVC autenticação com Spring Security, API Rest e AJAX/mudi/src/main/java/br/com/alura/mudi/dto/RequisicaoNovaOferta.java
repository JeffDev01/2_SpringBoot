package br.com.alura.mudi.dto;


import br.com.alura.mudi.orm.Oferta;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class RequisicaoNovaOferta {


    private Integer pedidoId;

    @Pattern(regexp = "^\\d+(\\.\\d{2})?$")
    @NotNull
    private String valor;

    @NotNull
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
    private String dataEntrega;

    private String comentario;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Oferta toOferta() {
        Oferta oferta = new Oferta();
        oferta.setComentario(this.comentario);
        oferta.setDataEntrega(LocalDate.parse(this.dataEntrega, formatter));
        oferta.setValor(new BigDecimal(this.valor));

        return oferta;
    }
}
