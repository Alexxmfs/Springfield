package com.prefeitura.iptu.iptu;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "CAD_PARCELAS_IPTU")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ParcelaIPTU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idCidadao; // ID do cidadão que vai vir do outro serviço
    private int numeroParcela; // 1 a 12
    private double valor;
    private boolean paga = false;

    @Enumerated(EnumType.STRING)
    private StatusParcela status = StatusParcela.SOLICITADO; // ou AGUARDANDO_ANALISE ou CONCLUIDO

    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
}
