package com.example.livros.interfaceapi;

import com.example.livros.application.LivroService;
import com.example.livros.domain.model.Livro;
import com.example.livros.interfaceapi.dto.LivroRequest;
import com.example.livros.interfaceapi.dto.LivroResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<LivroResponse> criar(@Valid @RequestBody LivroRequest req) {
        Livro novo = toEntity(req);
        Livro salvo = service.criar(novo);
        return ResponseEntity.created(URI.create("/api/livros/" + salvo.getId())).body(toResponse(salvo));
    }

    @GetMapping
    public List<LivroResponse> listar() {
        return service.listarTodos().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public LivroResponse buscarPorId(@PathVariable Long id) { return toResponse(service.buscarPorId(id)); }

    @GetMapping("/isbn/{isbn}")
    public LivroResponse buscarPorIsbn(@PathVariable String isbn) { return toResponse(service.buscarPorIsbn(isbn)); }

    @PutMapping("/{id}")
    public LivroResponse atualizar(@PathVariable Long id, @Valid @RequestBody LivroRequest req) {
        Livro atualizado = service.atualizar(id, toEntity(req));
        return toResponse(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    private Livro toEntity(LivroRequest r) {
        return new Livro(null, r.titulo, r.autor, r.isbn, r.anoPublicacao, r.quantidadeEstoque);
    }

    private LivroResponse toResponse(Livro l) {
        LivroResponse resp = new LivroResponse();
        resp.id = l.getId();
        resp.titulo = l.getTitulo();
        resp.autor = l.getAutor();
        resp.isbn = l.getIsbn();
        resp.anoPublicacao = l.getAnoPublicacao();
        resp.quantidadeEstoque = l.getQuantidadeEstoque();
        return resp;
    }
}
