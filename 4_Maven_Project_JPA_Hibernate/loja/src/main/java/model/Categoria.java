package model;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria  {

    @EmbeddedId
    private CategoriaId id;


    public Categoria(String nome) {
        this.id = new CategoriaId(nome, "xpto");
    }

    public Categoria() {
    }



    public String getNome() {
        return this.id.getNome();
    }

}
