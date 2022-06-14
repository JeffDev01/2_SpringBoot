package br.com.alura.forum.repository;

import br.com.alura.forum.modulo.Curso;
import br.com.alura.forum.modulo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso,Long> {


    Curso findByNome(String nomeCurso);
}
