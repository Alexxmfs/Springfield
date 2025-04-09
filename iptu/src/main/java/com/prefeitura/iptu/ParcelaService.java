package com.prefeitura.iptu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelaService {

    @Autowired
    private CidadaoClient cidadaoClient;

    public void exemploBuscaCidadao(Integer idCidadao) {
        CidadaoDTO cidadao = cidadaoClient.buscarCidadaoPorId(idCidadao);
        System.out.println("Cidadão: " + cidadao.getNome());
    }
}

