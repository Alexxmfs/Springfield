package com.prefeitura.iptu.controller;

import com.prefeitura.iptu.CidadaoClient;
import com.prefeitura.iptu.CidadaoDTO;
import com.prefeitura.iptu.ParcelaIPTUResponseDTO;
import com.prefeitura.iptu.iptu.ParcelaIPTU;
import com.prefeitura.iptu.iptu.ParcelaIPTURepository;
import com.prefeitura.iptu.iptu.StatusParcela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parcelas")
public class ParcelaIPTUController {

    @Autowired
    private ParcelaIPTURepository repository;

    @Autowired
    private CidadaoClient cidadaoClient;

    @GetMapping
    public List<ParcelaIPTU> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ParcelaIPTUResponseDTO buscarPorId(@PathVariable Long id) {
        Optional<ParcelaIPTU> parcelaOpt = repository.findById(id);
        if (parcelaOpt.isPresent()) {
            ParcelaIPTU parcela = parcelaOpt.get();
            CidadaoDTO cidadao = cidadaoClient.buscarCidadaoPorId(parcela.getIdCidadao());
            return new ParcelaIPTUResponseDTO(parcela, cidadao.getNome());
        }
        return null;
    }

    @GetMapping("/cidadao/{idCidadao}")
    public List<ParcelaIPTUResponseDTO> buscarPorCidadao(@PathVariable Integer idCidadao) {
        List<ParcelaIPTU> parcelas = repository.findByIdCidadao(idCidadao);
        CidadaoDTO cidadao = cidadaoClient.buscarCidadaoPorId(idCidadao);
        return parcelas.stream()
                .map(parcela -> new ParcelaIPTUResponseDTO(parcela, cidadao.getNome()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ParcelaIPTU criar(@RequestBody ParcelaIPTU novaParcela) {
        return repository.save(novaParcela);
    }

    @PutMapping("/{id}")
    public ParcelaIPTU atualizar(@PathVariable Long id, @RequestBody ParcelaIPTU atualizada) {
        return repository.findById(id).map(parcela -> {
            parcela.setNumeroParcela(atualizada.getNumeroParcela());
            parcela.setValor(atualizada.getValor());
            parcela.setStatus(atualizada.getStatus());
            parcela.setDataVencimento(atualizada.getDataVencimento());
            parcela.setDataPagamento(atualizada.getDataPagamento());
            return repository.save(parcela);
        }).orElse(null);
    }

    @PatchMapping("/{id}/pagar")
    public ResponseEntity<String> pagar(@PathVariable Long id) {
        return repository.findById(id).map(parcela -> {
            parcela.setPaga(true);
            parcela.setStatus(StatusParcela.CONCLUIDO);
            parcela.setDataPagamento(LocalDate.now());
            repository.save(parcela);
            return ResponseEntity.ok("Parcela paga com sucesso!");
        }).orElse(ResponseEntity.status(404).body("ID desconhecido"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
