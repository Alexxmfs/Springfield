package com.mensagem.kafka.controller;

import com.mensagem.kafka.model.Mensagem;
import com.mensagem.kafka.service.KafkaConsumerService;
import com.mensagem.kafka.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MensagemController {

    private final KafkaProducerService producerService;
    private final KafkaConsumerService consumerService;

    // Endpoint para a prefeitura publicar comunicados
    @PostMapping("/publicar")
    public String publicarMensagem(@RequestBody Mensagem mensagem) {
        producerService.enviarMensagem(mensagem);
        return "Mensagem publicada com sucesso!";
    }

    // Endpoint para os cidadãos consultarem os comunicados
    @GetMapping("/mensagens")
    public List<Mensagem> listarMensagens() {
        return consumerService.getMensagensRecebidas();
    }
}
