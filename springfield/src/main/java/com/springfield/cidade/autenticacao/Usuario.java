package com.springfield.cidade.autenticacao;

import jakarta.persistence.*;
import lombok.*;
import com.springfield.cidade.cidadao.Cidadao;
import java.time.LocalDateTime;

@Entity
@Table(name = "CAD_USUARIO_CIDADAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "cidadao_id", unique = true)
    private Cidadao cidadao;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String senha;

    private boolean bloqueado = false;
    private int tentativasLogin = 0;
    private LocalDateTime ultimaTrocaSenha;
}
