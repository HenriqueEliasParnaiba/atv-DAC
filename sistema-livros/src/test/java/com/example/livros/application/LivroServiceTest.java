package com.example.livros.application;

import com.example.livros.domain.model.Livro;
import com.example.livros.domain.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LivroServiceTest {

    private LivroRepository repo;
    private LivroService service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(LivroRepository.class);
        service = new LivroService(repo);
    }

    @Test // CREATE
    void deveCriarLivroQuandoIsbnNaoExiste() {
        Livro l = new Livro(null, "T", "A", "X-1", 2020, 2);
        when(repo.existsByIsbn("X-1")).thenReturn(false);
        when(repo.save(any())).thenAnswer(inv -> {
            Livro arg = inv.getArgument(0);
            arg.setId(10L);
            return arg;
        });
        Livro salvo = service.criar(l);
        assertNotNull(salvo.getId());
        verify(repo).save(any());
    }

    @Test // READ
    void deveListarTodos() {
        when(repo.findAll()).thenReturn(List.of(new Livro(1L, "T", "A", "I", 2020, 1)));
        List<Livro> lista = service.listarTodos();
        assertEquals(1, lista.size());
        verify(repo).findAll();
    }

    @Test // UPDATE
    void deveAtualizarQuandoExisteEIsbnValido() {
        Livro existente = new Livro(1L, "Old", "A", "I-1", 2019, 1);
        Livro novos = new Livro(null, "New", "B", "I-2", 2021, 3);

        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        when(repo.existsByIsbn("I-2")).thenReturn(false);
        when(repo.update(any())).thenAnswer(inv -> inv.getArgument(0));

        Livro atualizado = service.atualizar(1L, novos);
        assertEquals("New", atualizado.getTitulo());
        assertEquals("I-2", atualizado.getIsbn());
        verify(repo).update(any());
    }

    @Test // DELETE
    void deveRemoverQuandoExiste() {
        when(repo.existsById(1L)).thenReturn(true);
        doNothing().when(repo).deleteById(1L);
        assertDoesNotThrow(() -> service.remover(1L));
        verify(repo).deleteById(1L);
    }
}
