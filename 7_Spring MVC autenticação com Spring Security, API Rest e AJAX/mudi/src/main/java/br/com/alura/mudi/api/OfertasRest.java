package br.com.alura.mudi.api;

import br.com.alura.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mudi.orm.Oferta;
import br.com.alura.mudi.orm.Pedido;
import br.com.alura.mudi.repository.OfertaRepository;
import br.com.alura.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    //RequisicaoNovaOferta é a dto que leva os parametros da pagina de nova oferta
    @PostMapping//Esse recebe um JSON com dados e em seguida acessa o DB para persistir
    public Oferta criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao){
        Optional<Pedido> pedidoBuscado = pedidoRepository.findById(requisicao.getPedidoId());
        if(!pedidoBuscado.isPresent()){
            return null;
        }

        Pedido pedido = pedidoBuscado.get();
        Oferta nova =  requisicao.toOferta();
        nova.setPedido(pedido);

        ofertaRepository.save(nova);

        return nova;

    }

}
