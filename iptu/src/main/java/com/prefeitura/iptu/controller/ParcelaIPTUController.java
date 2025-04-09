package com.prefeitura.iptu.controller;

import com.prefeitura.iptu.iptu.ParcelaIPTU;
import com.prefeitura.iptu.iptu.StatusParcela;
import com.prefeitura.iptu.iptu.ParcelaIPTURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/parcelas")
public class ParcelaIPTUController {

    @Autowired
    private ParcelaIPTURepository repository;

    @GetMapping
    public List<ParcelaIPTU> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ParcelaIPTU buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/cidadao/{idCidadao}")
    public List<ParcelaIPTU> buscarPorCidadao(@PathVariable Integer idCidadao) {
        return repository.findByIdCidadao(idCidadao);
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
    public ParcelaIPTU pagar(@PathVariable Long id) {
        return repository.findById(id).map(parcela -> {
            parcela.setPaga(true);
            parcela.setStatus(StatusParcela.CONCLUIDO);
            parcela.setDataPagamento(LocalDate.now());
            return repository.save(parcela);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
