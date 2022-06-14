package br.com.alura.forum.form;

import br.com.alura.forum.modulo.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AtualizacaoTopicoForm {


    @NotNull @NotEmpty @Length(min=5)
    private String titulo;

    @NotNull @NotEmpty @Length(min=5)
    private String mensagem;


    public Topico atualizar(Long id, TopicoRepository topicoRepository) {

        Topico topico =  topicoRepository.getById(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;

    }
}
