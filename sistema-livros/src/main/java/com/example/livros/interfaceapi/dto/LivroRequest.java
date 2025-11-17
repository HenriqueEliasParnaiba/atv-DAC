package com.example.livros.interfaceapi.dto;

import jakarta.validation.constraints.*;

public class LivroRequest {
    @NotBlank public String titulo;
    @NotBlank public String autor;
    @NotBlank public String isbn;
    @Min(1400) public Integer anoPublicacao;
    @Min(0) public Integer quantidadeEstoque;
}
