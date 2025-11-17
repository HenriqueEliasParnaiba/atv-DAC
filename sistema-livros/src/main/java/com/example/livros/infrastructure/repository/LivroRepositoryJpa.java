package com.example.livros.infrastructure.repository;

import com.example.livros.domain.model.Livro;
import com.example.livros.domain.repository.LivroRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LivroRepositoryJpa implements LivroRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Livro save(Livro livro) {
        em.persist(livro);
        return livro;
    }

    @Override
    public Livro update(Livro livro) {
        return em.merge(livro);
    }

    @Override
    public List<Livro> findAll() {
        return em.createQuery("select l from Livro l", Livro.class).getResultList();
    }

    @Override
    public Optional<Livro> findById(Long id) {
        return Optional.ofNullable(em.find(Livro.class, id));
    }

    @Override
    public Optional<Livro> findByIsbn(String isbn) {
        TypedQuery<Livro> q = em.createQuery("select l from Livro l where l.isbn = :isbn", Livro.class);
        q.setParameter("isbn", isbn);
        List<Livro> res = q.getResultList();
        return res.isEmpty() ? Optional.empty() : Optional.of(res.get(0));
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        Long count = em.createQuery("select count(l) from Livro l where l.isbn = :isbn", Long.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(em::remove);
    }
}
