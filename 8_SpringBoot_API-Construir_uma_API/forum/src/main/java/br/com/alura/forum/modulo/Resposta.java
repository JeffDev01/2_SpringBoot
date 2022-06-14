package br.com.alura.forum.modulo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Data
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private Boolean solucao = false;

	@ManyToOne
	private Topico topico;

	@ManyToOne
	private Usuario autor;


}
