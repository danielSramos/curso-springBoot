package br.curso.java.forum.controller;

import br.curso.java.forum.controller.repository.TopicoRepository;
import br.curso.java.forum.controller.dto.DetalhesDoTopicoDto;
import br.curso.java.forum.controller.dto.TopicoDto;
import br.curso.java.forum.controller.dto.form.AtualizacaoTopicoForm;
import br.curso.java.forum.controller.dto.form.TopicoForm;
import br.curso.java.forum.modelo.Topico;
import br.curso.java.forum.controller.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    CursoRepository cursoRepository;


    @GetMapping
    public String hello() {
        return "Testando autenticação!";
    }

    @GetMapping("/listar")
    @Cacheable(value = "listaDeTopicos")
    public ResponseEntity lista(
            @RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable paginacao) {
        Page<Topico> topicos;

        if (nomeCurso == null) {
            topicos = topicoRepository.findAll(paginacao);
        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
        }
        return new ResponseEntity(TopicoDto.converter(topicos), HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity detalhar(@PathVariable("id") Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return new ResponseEntity(new DetalhesDoTopicoDto(topico.get()), HttpStatus.OK);
        }
        return new ResponseEntity("Pagina não encontrada", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/cadastrar")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity cadastrar(@Valid @RequestBody TopicoForm form) {
        return new ResponseEntity(topicoRepository.save(form.converter(cursoRepository)), HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Topico topico = form.atualizar(id, topicoRepository);

        if (topico == null) {
            return new ResponseEntity("Topico nao encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new TopicoDto(topico), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity delete(@PathVariable("id") Long id) {

        if (topicoRepository.findById(id).isPresent()) {
            topicoRepository.deleteById(id);
            return new ResponseEntity("Topico deletado com sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity("Topico nao encontrado", HttpStatus.NOT_FOUND);
    }

}
