package com.springfield.cidade.controller;

import com.springfield.cidade.cidadao.Cidadao;
import com.springfield.cidade.cidadao.CidadaoRepository;
import com.springfield.cidade.cidadao.CidadaoResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cidadaos")
public class CidadaoController {
    
    private final CidadaoRepository cidadaoRepository;

    public CidadaoController(CidadaoRepository cidadaoRepository) {
        this.cidadaoRepository = cidadaoRepository;
    }

    @GetMapping
    public List<CidadaoResponseDTO> getAllCidadaos() {
        List<Cidadao> cidadaos = cidadaoRepository.findAll();
        return cidadaos.stream().map(CidadaoResponseDTO::new).collect(Collectors.toList());
    }
}
