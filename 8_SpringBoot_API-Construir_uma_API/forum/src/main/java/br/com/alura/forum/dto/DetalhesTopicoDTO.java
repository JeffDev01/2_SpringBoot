package br.com.alura.forum.dto;

import br.com.alura.forum.modulo.StatusTopico;
import br.com.alura.forum.modulo.Topico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalhesTopicoDTO {


    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    private StatusTopico status;
    private List<RespostaDTO> respostas;



    public DetalhesTopicoDTO(Topico topico) {
        this.id=topico.getId();
        this.titulo=topico.getTitulo();
        this.mensagem=topico.getMensagem();
        this.nomeAutor=topico.getAutor().getNome();
        this.status=topico.getStatus();
        this.respostas=new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));

    }




}
