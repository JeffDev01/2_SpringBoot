package br.com.algaworks.sistemacursos.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "curso")
@Getter
@Setter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(mappedBy = "curso") //achar o metadado com ao foregn key ja configurada
    private List<Modulo> modulo;

    @ManyToMany
    @JoinTable (name = "curso_aluno", //Nome da tabela.
    joinColumns = @JoinColumn(name = "curso_id"),//pega a pk do curso
    inverseJoinColumns = @JoinColumn(name = "aluno_id")) //pega a pk do aluno
    private List<Aluno> alunos;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return id.equals(curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
