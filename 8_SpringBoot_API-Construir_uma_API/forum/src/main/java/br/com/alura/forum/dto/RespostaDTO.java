package br.com.alura.forum.dto;

import br.com.alura.forum.modulo.Resposta;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class RespostaDTO {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;


    public RespostaDTO(Resposta resposta){
        this.id=resposta.getId();
        this.dataCriacao=resposta.getDataCriacao();
        this.mensagem=resposta.getMensagem();
        this.nomeAutor=resposta.getAutor().getNome();
    }

}
