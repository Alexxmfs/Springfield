package com.mensagem.kafka.service;

import com.mensagem.kafka.model.Mensagem;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    private final List<Mensagem> mensagensRecebidas = new ArrayList<>();

    @KafkaListener(topics = "comunicados-springfield", groupId = "grupo-cidadao")
    public void consumirMensagem(Mensagem mensagem) {
        mensagensRecebidas.add(mensagem);
        System.out.println("Mensagem recebida: " + mensagem);
    }

    public List<Mensagem> getMensagensRecebidas() {
        return mensagensRecebidas;
    }
}
