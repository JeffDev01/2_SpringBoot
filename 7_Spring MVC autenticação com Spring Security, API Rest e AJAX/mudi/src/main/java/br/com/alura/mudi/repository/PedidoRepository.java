package br.com.alura.mudi.repository;

import br.com.alura.mudi.orm.Pedido;
import br.com.alura.mudi.orm.StatusPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

//    @Cacheable("books")
    List<Pedido> findByStatus(StatusPedido status, Pageable pageable);

    @Query("select p from Pedido p join p.user u where u.username = :username")
    List<Pedido> findAllByUser(@Param("username") String username);

    @Query("select p from Pedido p join p.user u where u.username = :username and p.status =:status")
    List<Pedido> findByStatusEUsuario(@Param("status") StatusPedido status, @Param("username") String username);
}
