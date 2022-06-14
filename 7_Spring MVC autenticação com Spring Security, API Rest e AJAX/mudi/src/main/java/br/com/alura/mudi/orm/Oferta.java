package br.com.alura.mudi.orm;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal valor;

    private LocalDate dataEntrega;

    private String comentario;

    @ManyToOne(cascade = CascadeType.MERGE, fetch= FetchType.LAZY)
    private Pedido pedido;


}
