package com.springfield.cidade.controller;

import com.springfield.cidade.cidadao.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("/{id}")
    public ResponseEntity<CidadaoResponseDTO> getCidadaoById(@PathVariable Integer id) {
        Optional<Cidadao> cidadao = cidadaoRepository.findById(id);
        return cidadao.map(value -> ResponseEntity.ok(new CidadaoResponseDTO(value)))
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<CidadaoResponseDTO> createCidadao(@RequestBody CidadaoRequestDTO data) {
        Cidadao novoCidadao = new Cidadao(data.nome(), data.endereco(), data.bairro());
        cidadaoRepository.save(novoCidadao);
        return ResponseEntity.ok(new CidadaoResponseDTO(novoCidadao));
    }    

    @PutMapping("/{id}")
    public ResponseEntity<CidadaoResponseDTO> updateCidadao(@PathVariable Integer id, @RequestBody CidadaoRequestDTO data) {
        Optional<Cidadao> existingCidadao = cidadaoRepository.findById(id);
        if (existingCidadao.isPresent()) {
            Cidadao cidadao = existingCidadao.get();
            cidadao.setNome(data.nome());
            cidadao.setEndereco(data.endereco());
            cidadao.setBairro(data.bairro());
            cidadaoRepository.save(cidadao);
            return ResponseEntity.ok(new CidadaoResponseDTO(cidadao));
        }
        return ResponseEntity.notFound().build();
    }
}
