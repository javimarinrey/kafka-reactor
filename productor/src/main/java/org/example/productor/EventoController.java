package org.example.productor;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.util.UUID;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final KafkaSender<String, String> kafkaSender;

    @Autowired
    private KafkaProducerConfig producerConfig;

    @Value("${kafka.topic}")
    private String topic;

    public EventoController(KafkaSender<String, String> kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @PostMapping
    public Mono<Void> enviar(@RequestBody Evento evento) {
        String key = evento.getId(); // o cualquier clave que uses
        String value = producerConfig.serializeEvento(evento);

        SenderRecord<String, String, Void> record =
                SenderRecord.create(new ProducerRecord<>("topic-name", key, value), null);

        return kafkaSender.send(Mono.just(record))
                .then(); // devuelve Mono<Void>
    }
}