package com.springfield.cidade.autenticacao;

public record UsuarioRequestDTO(
    Integer cidadaoId,
    String username,
    String senha
) {}
