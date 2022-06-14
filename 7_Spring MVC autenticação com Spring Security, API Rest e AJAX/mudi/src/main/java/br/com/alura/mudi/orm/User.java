package br.com.alura.mudi.orm;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    //ESSA TABELA TEM QUE SEGUIR AS CONFIGURAÇÕES PADRÃO DE NOMENCLATURA DA SPRING SECURITY
    @Id
    private String username;
    private String password;
    private Boolean enable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;




}
