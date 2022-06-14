package br.com.alura.mudi.api;


import br.com.alura.mudi.orm.Pedido;
import br.com.alura.mudi.orm.StatusPedido;
import br.com.alura.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //anotação define que é uma classe rest, ou seja, automaticamente devolve um JSON!
@RequestMapping("/api/pedidos")
public class PedidosRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("aguardando")
    public List<Pedido> getPedidosAguardandoOfertas(){

        Sort sort = Sort.by("id").descending();
        PageRequest paginacao = PageRequest.of(0, 4, sort);

        return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);//retorna um JSON automaticamente

    }

}
