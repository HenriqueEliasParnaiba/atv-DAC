package com.example.livros.application;

import com.example.livros.domain.model.Livro;
import com.example.livros.domain.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repo;

    public LivroService(LivroRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Livro criar(Livro livro) {
        if (repo.existsByIsbn(livro.getIsbn())) {
            throw new IllegalArgumentException("ISBN já cadastrado");
        }
        return repo.save(livro);
    }

    public List<Livro> listarTodos() { return repo.findAll(); }

    public Livro buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
    }

    public Livro buscarPorIsbn(String isbn) {
        return repo.findByIsbn(isbn).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
    }

    @Transactional
    public Livro atualizar(Long id, Livro dados) {
        Livro existente = buscarPorId(id);
        if (!existente.getIsbn().equals(dados.getIsbn()) && repo.existsByIsbn(dados.getIsbn())) {
            throw new IllegalArgumentException("ISBN já cadastrado");
        }
        existente.setTitulo(dados.getTitulo());
        existente.setAutor(dados.getAutor());
        existente.setIsbn(dados.getIsbn());
        existente.setAnoPublicacao(dados.getAnoPublicacao());
        existente.setQuantidadeEstoque(dados.getQuantidadeEstoque());
        return repo.update(existente);
    }

    @Transactional
    public void remover(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Livro não encontrado");
        }
        repo.deleteById(id);
    }
}
