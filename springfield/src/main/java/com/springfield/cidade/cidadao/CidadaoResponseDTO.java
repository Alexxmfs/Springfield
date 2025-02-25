package com.springfield.cidade.cidadao;

public record CidadaoResponseDTO(
    Long id,
    String nome,
    String endereco,
    String bairro
){
    public CidadaoResponseDTO(Cidadao cidadao){
        this(
        cidadao.getId(),
        cidadao.getNome(),
        cidadao.getEndereco(),
        cidadao.getBairro()
        );
    }
}
