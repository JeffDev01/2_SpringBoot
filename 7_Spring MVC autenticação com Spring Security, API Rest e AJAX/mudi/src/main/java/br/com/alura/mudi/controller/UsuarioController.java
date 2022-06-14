package br.com.alura.mudi.controller;

import br.com.alura.mudi.orm.Pedido;
import br.com.alura.mudi.orm.StatusPedido;
import br.com.alura.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private PedidoRepository pedidoRepository;

    /*   Model é um objeto simplificado do HttpServletRequest feito para gerir os atributos da requisição.
     *    Principal é um objeto criado no contexto do secutiry que armazena nome do usuário logado
     * facilitando o seu uso para definir os dados do sistema que irão ser apresentados*/
    @GetMapping("home")
    public String home(Model model, Principal principal) {
        List<Pedido> pedidos = pedidoRepository.findAllByUser(principal.getName());
        model.addAttribute("listaPedidos", pedidos);
        return "usuario/home";
    }

    /*  PathVariable é um atributo definido e passado pela url, portanto o mapping da url pode também
     * ser definido por essa variável*/
    @GetMapping("home/{status}")
    public String sd(@PathVariable("status") String status, Model model, Principal principal) {
        List<Pedido> pedidos = pedidoRepository.findByStatusEUsuario(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
        model.addAttribute("listaPedidos", pedidos);
        model.addAttribute("status", status);
        return "usuario/home";
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/usuario/home";
    }
}
