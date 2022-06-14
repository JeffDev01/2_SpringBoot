package br.com.algaworks.sistemacursos.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "aula")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;


    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;









    @Override
    public boolean equals(Object o) {
        if (this == o) return true;//É o mesmo objeto, mesmo endereço de memoria
        if (o == null || this.getClass() != o.getClass()) return false; //classes diferentes portanto false
        Aula aula = (Aula) o;
        return id.equals(aula.id); //agora ele utiliza o valor em memoria e ve se o conteúdo é igual
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
