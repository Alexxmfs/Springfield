package com.springfield.cidade.cidadao;

public record CidadaoRequestDTO( 
    Long id,
    String nome,
    String endereco,
    String bairro
){
    
}