package br.com.alura.forum.api;


import br.com.alura.forum.dto.DetalhesTopicoDTO;
import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.form.AtualizacaoTopicoForm;
import br.com.alura.forum.form.TopicoForm;
import br.com.alura.forum.modulo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosRest {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping
    @Cacheable(value="listaDeTopicos")
    public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso,
                                 @PageableDefault(sort="id", direction = Sort.Direction.DESC, page=0, size=10) Pageable paginacao) { //os parametros vem na url
        //O rest reconhece esse parametro como um atributo da requisição portanto temos
        //topicos?nomeCurso=Spring  -> sout(Spring)

//        Pageable paginacao = PageRequest.of(pagina, qtd, Sort.Direction.ASC, ordenacao); //Trocando por jeito mais simples

        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDTO.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
            return TopicoDTO.converter(topicos);
        }

    }

    /*@RequestBody -> Spring,é pra vc pegar esses parametros do corpo da requisição, ou seja
     * que tenham o mesmo nome. Portanto é importante exige que tenha os mesmos nomes na requisição.*/
    @PostMapping
    @Transactional
    @CacheEvict(value="listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        //Devolve o código 201: Criado um recurso no servidor
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));

    }

    @GetMapping("/{id}") //url dinâmica
    public ResponseEntity<DetalhesTopicoDTO> detalhar(@PathVariable("id") Long id) { //@PathVariable ja sabe q é uma url dinâmica
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            return ResponseEntity.ok(new DetalhesTopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();


    }


    @PutMapping("/{id}")//Atualiza um registro, sobrescrevendo ele, já o Pet é apenas um ou mais campos.
    @Transactional //Comita a transação automaticamente
    @CacheEvict(value="listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = form.atualizar(id, topicoRepository); //Ele ja atualiza no DB automaticamente.
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();

    }


    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value="listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }


}

