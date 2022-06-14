package br.com.alura.mudi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("oferta") //Retorna apenas o HTML para um client-side AJAX que consome JSON.
public class OfertaController {

    @GetMapping
    public String getFormularioParaOfertas(){
        return "oferta/home";
    }

}
