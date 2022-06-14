package br.com.alura.forum.configuration.validacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroDeFormularioDTO {

    private String campo;
    private String erro;


}
