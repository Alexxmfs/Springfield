package com.springfield.cidade.autenticacao;

public record LoginRequestDTO(
    String username,
    String senha
) {}
