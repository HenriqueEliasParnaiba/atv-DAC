package com.example.livros.domain.repository;

import com.example.livros.domain.model.Livro;
import java.util.List;
import java.util.Optional;

public interface LivroRepository {
    Livro save(Livro livro);
    Livro update(Livro livro);
    List<Livro> findAll();
    Optional<Livro> findById(Long id);
    Optional<Livro> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    boolean existsById(Long id);
    void deleteById(Long id);
}
