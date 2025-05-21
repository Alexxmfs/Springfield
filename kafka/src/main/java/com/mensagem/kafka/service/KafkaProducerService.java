package com.mensagem.kafka.service;

import com.mensagem.kafka.model.Mensagem;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, Mensagem> kafkaTemplate;

    private final String TOPICO = "comunicados-springfield";

    public void enviarMensagem(Mensagem mensagem) {
        LOGGER.info("Enviando mensagem: {}", mensagem);
        kafkaTemplate.send(TOPICO, mensagem);
    }
}
