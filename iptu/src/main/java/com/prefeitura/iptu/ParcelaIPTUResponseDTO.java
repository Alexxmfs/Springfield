package com.prefeitura.iptu;

import com.prefeitura.iptu.iptu.ParcelaIPTU;
import com.prefeitura.iptu.iptu.StatusParcela;
import java.time.LocalDate;

public class ParcelaIPTUResponseDTO {
    private Long id;
    private Integer idCidadao;
    private String nomeCidadao;
    private int numeroParcela;
    private double valor;
    private boolean paga;
    private StatusParcela status;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    public ParcelaIPTUResponseDTO(ParcelaIPTU parcela, String nomeCidadao) {
        this.id = parcela.getId();
        this.idCidadao = parcela.getIdCidadao();
        this.nomeCidadao = nomeCidadao;
        this.numeroParcela = parcela.getNumeroParcela();
        this.valor = parcela.getValor();
        this.paga = parcela.isPaga();
        this.status = parcela.getStatus();
        this.dataVencimento = parcela.getDataVencimento();
        this.dataPagamento = parcela.getDataPagamento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCidadao() {
        return idCidadao;
    }

    public void setIdCidadao(Integer idCidadao) {
        this.idCidadao = idCidadao;
    }

    public String getNomeCidadao() {
        return nomeCidadao;
    }

    public void setNomeCidadao(String nomeCidadao) {
        this.nomeCidadao = nomeCidadao;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public StatusParcela getStatus() {
        return status;
    }

    public void setStatus(StatusParcela status) {
        this.status = status;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
