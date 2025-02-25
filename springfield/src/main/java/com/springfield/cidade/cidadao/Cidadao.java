package com.springfield.cidade.cidadao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;

@Table(name = "CAD_CIDADAO")
@Entity(name = "CAD_CIDADAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cidadao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String bairro;

    public Cidadao(CidadaoRequestDTO data){
        this.nome = data.nome();
        this.endereco = data.endereco();
        this.bairro = data.bairro();
    }
}
