package com.prefeitura.iptu;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cidadao-service", url = "http://localhost:8080") // ou o nome no discovery
public interface CidadaoClient {

    @GetMapping("/cidadaos/{id}")
    CidadaoDTO buscarCidadaoPorId(@PathVariable("id") Integer id);
}
