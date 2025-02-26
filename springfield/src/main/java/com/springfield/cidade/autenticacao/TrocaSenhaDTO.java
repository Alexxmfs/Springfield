package com.springfield.cidade.autenticacao;

public record TrocaSenhaDTO(
    String username,
    String senhaAtual,
    String novaSenha
) {}
