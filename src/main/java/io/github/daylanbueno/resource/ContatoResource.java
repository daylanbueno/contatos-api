package io.github.daylanbueno.resource;

import io.github.daylanbueno.entity.Contato;
import io.github.daylanbueno.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoResource {

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public ResponseEntity<List<Contato>> obterTodos() {
        return ResponseEntity.ok(contatoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Contato> salvar(@RequestBody Contato contato) {
        Contato novoContato = contatoRepository.save(contato);
        return ResponseEntity.ok(novoContato);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        Contato contato = contatoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        contatoRepository.deleteById(contato.getId());
    }

    @PutMapping
    public ResponseEntity<Contato>  alterar (@RequestBody Contato contato) {
        contatoRepository.findById(contato.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(contatoRepository.save(contato));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Contato>>  obterPorNome(@PathVariable  String nome) {
      return ResponseEntity.ok(contatoRepository.obterPorNome(nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> obterPorId(@PathVariable Integer id) {
        Contato contato = contatoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(contato);
    }

}
